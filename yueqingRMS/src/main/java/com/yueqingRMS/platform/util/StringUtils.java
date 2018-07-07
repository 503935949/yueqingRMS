package com.yueqingRMS.platform.util;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;


/**
 * 对数组和字符串操作
 * 
 * @author renhui
 * 
 */
/**
 * @author Administrator
 * 
 */
public class StringUtils {
	
	

	/** 返回字符串的字节长度 */
	public static int length(String strExp) {
		return strExp.getBytes().length;
	}

	/**
	 * 计算一个串中包含的字串的个数
	 * 
	 * @param str
	 * @param subStr
	 * @return
	 */
	public static int countSubStr(String str, String subStr) {
		if (str == null || subStr == null)
			return 0;
		int iCount = 0;
		int iPos = 0;
		while ((iPos = str.indexOf(subStr, iPos)) >= 0) {
			iCount++;
			iPos++;
		}
		return iCount;
	}

	/**
	 * 取一个字符串的左边的指定长度的字符或汉字
	 * 
	 * @param strExp
	 *            指定的字符串
	 * @param intLen
	 *            指定所取的长度
	 * @return 截取的串
	 */
	public static String left(String strExp, int intLen) {
		if (intLen <= 0)
			return "";

		if (length(strExp) <= intLen)
			return strExp;

		if (length(strExp) == strExp.length())
			return strExp.substring(0, intLen);

		int intCLoop = 0;
		int intBLoop = 0;
		byte abytTemp[] = strExp.getBytes();

		while (true) {
			if (abytTemp[intBLoop] > 127 || abytTemp[intBLoop] < 0) {
				if (intBLoop + 2 > intLen) {
					break;
				} else {
					intBLoop++;
					intBLoop++;
				}
			} else {
				if (intBLoop + 1 > intLen) {
					break;
				} else {
					intBLoop++;
				}
			}
			intCLoop++;
		}

		return strExp.substring(0, intCLoop);
	}

	/**
	 * 取一个字符串的右边的指定长度的字符或汉字
	 * 
	 * @param strExp
	 *            指定的字符串
	 * @param intLen
	 *            指定所取的长度
	 * @return 截取的串
	 */
	public static String right(String strExp, int intLen) {
		if (intLen <= 0)
			return "";

		if (length(strExp) <= intLen)
			return strExp;

		if (length(strExp) == strExp.length())
			return strExp.substring(strExp.length() - intLen);

		int intCLoop = 0;
		int intBLoop = 0;
		byte abytTemp[] = strExp.getBytes();

		while (intBLoop < length(strExp) - intLen) {
			if (abytTemp[intBLoop] > 127 || abytTemp[intBLoop] < 0) {
				intBLoop++;
				intBLoop++;
			} else {
				intBLoop++;
			}
			intCLoop++;

		}
		return strExp.substring(intCLoop);
	}

	/**
	 * 串截取
	 * 
	 * @param strExp
	 * @param intFrom
	 * @return
	 */
	public static String substring(String strExp, int intFrom) {
		if (intFrom <= 0)
			return strExp;

		if (length(strExp) <= intFrom)
			return "";

		int intCLoop = 0;
		int intBLoop = 0;
		byte abytTemp[] = strExp.getBytes();

		while (true) {
			if (abytTemp[intBLoop] > 127 || abytTemp[intBLoop] < 0) {
				if (intBLoop + 2 > intFrom) {
					break;
				} else {
					intBLoop++;
					intBLoop++;
				}
			} else {
				if (intBLoop + 1 > intFrom) {
					break;
				} else {
					intBLoop++;
				}
			}
			intCLoop++;
		}

		return strExp.substring(intCLoop);
	}

	/**
	 * 从某一位置后进行串截取
	 * 
	 * @param strExp
	 * @param intFrom
	 * @param intLen
	 * @return
	 */
	public static String substring(String strExp, int intFrom, int intLen) {
		if (intLen <= 0)
			return "";

		strExp = substring(strExp, intFrom);
		if (strExp.length() > 0) {
			strExp = left(strExp, intLen);
		}
		return strExp;
	}
	
	/**
	 * 如果是空值传入sql,替换为null(达梦数据库用)
	 * @param strExp
	 * @param strFind
	 * @param strRep
	 * @return
	 */
	public static String replaceNull(String strExp, String strFind, String strRep) {
		if (strFind == null || strFind.length() == 0)
			return strExp;
		int intFrom = strExp.indexOf(strFind, 0);
		if (intFrom >= 0) {
			int intTo = intFrom + strFind.length();
			if(strRep != null && !"".equals(strRep)){
				strExp = strExp.substring(0, intFrom) + strRep + strExp.substring(intTo);
			}else{
				String befStr = strExp.substring(intFrom - 1, intFrom);
				String afterStr = strExp.substring(intTo, intTo + 1);
				if("'".equals(befStr) && "'".equals(afterStr)){
					strExp = strExp.substring(0, intFrom - 1) + "NULL" + strExp.substring(intTo + 1);
				}else{
					strExp = strExp.substring(0, intFrom) + strRep + strExp.substring(intTo);
				}
			}
		}
		return strExp;
	}
	
	/**
	 * 替换一个串中的指定的第一个子串
	 * 
	 * @param strExp
	 * @param strFind
	 * @param strRep
	 * @return
	 */
	public static String replaceFirst(String strExp, String strFind, String strRep) {
		if (strFind == null || strFind.length() == 0)
			return strExp;
		int intFrom = strExp.indexOf(strFind, 0);
		if (intFrom >= 0) {
			int intTo = intFrom + strFind.length();
			strExp = strExp.substring(0, intFrom) + strRep + strExp.substring(intTo);
		}
		return strExp;
	}

	/**
	 * 替换一个串中的指定的子串
	 * 
	 * @param strExp
	 * @param strFind
	 * @param strRep
	 * @return
	 */
	public static String replace(String strExp, String strFind, String strRep) {
		int intFrom, intTo;
		String strHead, strTail;

		if (strFind == null || strFind.length() == 0)
			return strExp;

		intFrom = strExp.indexOf(strFind, 0);
		while (intFrom >= 0) {
			intTo = intFrom + strFind.length();
			strHead = strExp.substring(0, intFrom);
			strTail = strExp.substring(intTo);
			strExp = strHead + strRep + strTail;
			intTo = intFrom + strRep.length();
			intFrom = strExp.indexOf(strFind, intTo);
		}

		return strExp;
	}

	/**
	 * 左裁减字符（串）
	 * 
	 * @param strExp
	 * @param strTrim
	 *            裁减的字符串
	 * @return
	 */
	public static String ltrim(String strExp, String strTrim) {
		int i;

		if (strExp == null || strExp.length() == 0)
			strExp = "";

		if (strTrim == null || strTrim.length() == 0)
			return strExp;

		char chrTrim = strTrim.charAt(0);

		for (i = 0; i < strExp.length(); i++) {
			if (strExp.charAt(i) == chrTrim)
				break;
		}

		return strExp.substring(i);
	}

	/**
	 * 右裁减
	 * 
	 * @param strExp
	 * @param strTrim
	 *            裁减的字符串
	 * @return
	 */
	public static String rtrim(String strExp, String strTrim) {
		int i;

		if (strExp == null || strExp.length() == 0)
			strExp = "";

		int intLen = strExp.length();

		if (strTrim == null || strTrim.length() == 0)
			return strExp;

		char chrTrim = strTrim.charAt(0);

		for (i = 0; i < strExp.length(); i++) {
			if (strExp.charAt(intLen - i - 1) == chrTrim)
				break;
		}

		return strExp.substring(0, intLen - i);
	}

	/**
	 * 左右裁减指定字符(串)
	 * 
	 * @param strExp
	 * @param strTrim
	 *            裁减的字符串
	 * @return
	 */
	public static String trim(String strExp, String strTrim) {
		return ltrim(rtrim(strExp, strTrim), strTrim);
	}

	/**
	 * 左补齐
	 * 
	 * @param strExp
	 * @param intLen
	 *            补齐后的长度
	 * @param strPad
	 *            要补齐的字符串
	 * @return
	 */
	public static String lpad(String strExp, int intLen, String strPad) {
		if (strExp == null)
			strExp = "";

		while (strExp.length() < intLen) {
			strExp = strPad + strExp;
		}
		return right(strExp, intLen);
	}

	/**
	 * 右补齐
	 * 
	 * @param strExp
	 * @param intLen
	 *            补齐后的长度
	 * @param strPad
	 *            要补齐的字符串
	 * @return
	 */
	public static String rpad(String strExp, int intLen, String strPad) {
		if (strExp == null)
			strExp = "";

		while (strExp.length() < intLen) {
			strExp = strExp + strPad;
		}
		return left(strExp, intLen);
	}

	/**
	 * 将字符串String转换为BigDecimal,如果为空则使用defaultNum值
	 * 
	 * @param value
	 * @param defaultNum
	 * @return
	 */
	public static BigDecimal stringToBigDecimal(String value, int defaultNum) {

		if (value != null && !value.equals("")) {
			BigDecimal num = new BigDecimal(defaultNum);
			try {
				double numDouble = Double.parseDouble(value);
				num = new BigDecimal(numDouble);
			} catch (Exception ignored) {
			}
			return num;
		} else {
			return new BigDecimal(defaultNum);
		}
	}

	/**
	 * 将一个串转换为BigDecimal类型
	 * 
	 * @param value
	 * @return
	 */
	public static BigDecimal stringToBigDecimal(String value) {
		return stringToBigDecimal(value, 0);
	}
	
	/**
	 * 此函数判断字符串是否为NULL或""，如果是返回'',否则返回原值(拼sql时用)
	 * 
	 * @param sourceStr
	 * @return
	 */
	public static String emptyToSql(String sourceStr) {
		if (sourceStr != null && !"".equals(sourceStr)) {
			return sourceStr.trim();
		} else {
			return "''";
		}
	}

	/**
	 * 此函数判断字符串是否为NULL，如果为空返回"",否则返回原值
	 * 
	 * @param sourceStr
	 * @return
	 */
	public static String NulltoBlank(String sourceStr) {
		if (sourceStr == null) {
			return "";
		} else {
			return sourceStr.trim();
		}
	}

	public static String NulltoHtml(String sourceStr) {
		if (sourceStr == null || "".equals(sourceStr)) {
			return "&nbsp;";
		} else {
			return sourceStr;
		}
	}
	
	/**
	 * strChk字符串为空时的赋值""
	 * 
	 * @param strChk
	 * @return
	 */
	public static String nvl(String strChk) {
		return nvl(strChk, "");
	}

	/**
	 * strChk字符串为空时的赋值为strExp
	 * 
	 * @param strChk
	 * @param strExp
	 * @return
	 */
	public static String nvl(String strChk, String strExp) {
		if (isNull(strChk))
			return strExp;
		return strChk;
	}

	/**
	 * 返回变换后的整数，如果失败则返回默认值
	 * 
	 * @param strIn
	 * @param iV
	 * @return
	 */
	public static int toInt(String strIn, int iV) {
		if (strIn == null)
			return iV;
		try {
			return Integer.parseInt(strIn);
		} catch (Exception ex) {
			return iV;
		}
	}

	/**
	 * 将16进制的字符串转换成整数
	 * 
	 * @param strIn
	 *            如果为16进制，则必须为0X或者0x开头，否则认为是10进制数字
	 * @param iV
	 *            如果转换失败的返回值
	 * @return
	 */
	public static int hex2Int(String strIn, int iV) {
		int radix = 10; // 默认10进制

		if (strIn != null && (strIn.startsWith("0X") || strIn.startsWith("0x"))) {
			strIn = strIn.substring(2);
			radix = 16;
		}

		// 考虑支持16进制
		try {
			return Integer.parseInt(strIn, radix);
		} catch (Exception ex) {
			return iV;
		}

	}

	/**
	 * 返回变换后的双浮点数，如果失败则返回默认值
	 * 
	 * @param strIn
	 *            要转换的串
	 * @param iV
	 *            默认值
	 * @return
	 */
	public static float toFloat(String strIn, float iV) {
		if (strIn == null)
			return iV;
		try {
			return Double.valueOf(strIn).floatValue();
		} catch (Exception ex) {
			return iV;
		}
	}

	/**
	 * 返回变换后的双浮点数，如果失败则返回默认值
	 * 
	 * @param strIn
	 *            要转换的串
	 * @param iV
	 *            默认值
	 * @return
	 */
	public static double toDouble(String strIn, double iV) {
		if (strIn == null)
			return iV;
		try {
			return Double.valueOf(strIn).doubleValue();
		} catch (Exception ex) {
			return iV;
		}
	}

	/**
	 * 返回变换后的long，如果失败则返回默认值
	 * 
	 * @param strIn
	 *            要转换的串
	 * @param iV
	 *            默认值
	 * @return
	 */
	public static double toLong(String strIn, long iV) {
		if (strIn == null)
			return iV;
		try {
			return Long.parseLong(strIn);
		} catch (Exception ex) {
			return iV;
		}
	}

	/** 判断Vector是否为空或者size<1* */
	@SuppressWarnings("rawtypes")
	public static boolean isNull(Vector<?> vExp) {
		if (vExp == null) {
			vExp = new Vector();
		}
		if (vExp.size() < 1) {
			return true;
		} else {
			return false;
		}
	}

	/** 判断字符串是否为空或长度是否为0 */
	public static boolean isNull(String strExp) {
		if (strExp == null || strExp.length() == 0)
			return true;
		return false;
	}

	/**
	 * 判断是否是年合法月日
	 * 
	 * @param intYear
	 * @param intMonth
	 * @param intDay
	 * @return
	 */
	public static boolean isDate(int intYear, int intMonth, int intDay) {
		try {
			Calendar cal = new GregorianCalendar();
			cal.setLenient(false);
			cal.set(intYear, intMonth, intDay);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 判断字符串是否是合法日期串（YYYY/MM/DD）
	 * 
	 * @param strExp
	 * @return
	 */
	public static boolean isDate(String strExp) {
		if (strExp.length() != 10)
			return false;

		try {
			Calendar cal = new GregorianCalendar();
			cal.setLenient(false);
			cal.set(Integer.parseInt(strExp.substring(0, 4)), Integer.parseInt(strExp
					.substring(5, 7)) - 1, Integer.parseInt(strExp.substring(8, 10)));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 判断某一指定格式的日期串是否合法
	 * 
	 * @param dateStr
	 * @param formatStr
	 * @return
	 */
	public static boolean isDate(String dateStr, String formatStr) {
		String strDate = transDateString(dateStr, formatStr, "YYYY/MM/DD");
		if (strDate == null)
			return false;
		return isDate(strDate);
	}


	/** 判断字符串是否是一个float */
	public static boolean isFloat(String strExp) {
		if (isNull(strExp))
			return false;

		try {
			Float.valueOf(strExp);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/** 判断字符串是否是一个在规定长度内的integer */
	public static boolean isInt(String strExp, int intWidth) {
		if (isNull(strExp) || intWidth <= 0 || strExp.length() > intWidth)
			return false;
		return isInt(strExp);
	}

	/** 判断字符串是否是一个integer */
	public static boolean isInt(String strExp) {
		if (isNull(strExp))
			return false;
		try {
			Integer.valueOf(strExp);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/** 判断字符串是否是一个数值 */
	public static boolean isNumeric(String strExp) {
		if (isFloat(strExp) || isInt(strExp))
			return true;
		return false;
	}

	/**
	 * 判断一个串是否在一个串数组中，如果在则返回在串数组中的index，否则返回-1
	 * 
	 * @param arr
	 *            串数组
	 * @param inStr
	 *            要查找的串
	 * @return
	 */
	public static int inStringArray(String[] arr, String inStr) {
		int iRet = -1;
		if (arr == null || inStr == null)
			return iRet;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals(inStr))
				return i;
		}
		return iRet;
	}

	/** 判断字符串是否在规定长度内 */
	public static boolean isLegalLen(String strExp, int intWidth) {
		if (isNull(strExp))
			return true;

		if (strExp.length() > intWidth)
			return false;
		return true;
	}

	/** 判断字符串是否是一个电子信箱 */
	public static boolean isEmail(String strExp) {
		int intFind = strExp.indexOf("@");
		if (intFind <= 0 || intFind >= strExp.length() - 1)
			return false;
		return true;
	}

	/**
	 * 解析固定分割的串的各个单元，并将其存放在Vector中返回
	 * 
	 * @param s
	 * @param delim
	 * @return
	 */
	public static Vector<String> parseStringTokenizer(String s, String delim) {
		Vector<String> vRet = new Vector<String>();
		for (;;) {
			int iPos = s.indexOf(delim);
			if (iPos < 0) {
				if (s != null && !"".equals(s))
					vRet.addElement(s);
				break;
			}
			vRet.addElement(s.substring(0, iPos));
			s = s.substring(iPos + delim.length());
			if (s == null)
				break;
		}
		return vRet;
	}

	public static String getFieldValueByStr(java.lang.reflect.Field field, Object obj)
			throws IllegalAccessException, IllegalArgumentException {
		String v = "";
		if (field.getType().equals("double")) {
			v = field.getDouble(obj) + "";
		} else if (field.getType().equals("int")) {
			v = field.getInt(obj) + "";
		} else if (field.getType().equals("float")) {
			v = field.getFloat(obj) + "";
		} else if (field.getType().equals("boolean")) {
			v = field.getBoolean(obj) + "";
		} else if (field.getType().equals("byte")) {
			v = field.getByte(obj) + "";
		} else if (field.getType().equals("char")) {
			v = field.getChar(obj) + "";
		} else if (field.getType().equals("long")) {
			v = field.getLong(obj) + "";
		} else if (field.getType().equals("short")) {
			v = field.getShort(obj) + "";
		} else
			v = field.get(obj) + "";
		return v;
	}

	/**
	 * 将指定格式的日期串转换为另外一种格式
	 * 
	 * @param sDate
	 *            源日期串
	 * @param sFormat,源串的格式，如:"YYYY/MM/DD:HH24MISS"
	 * @param dFormat,目的格式，如:"HH24MISS:YYYY年MM月DD日"
	 * @return 转换后的串串，如果有误则返回null
	 */
	public static String transDateString(String sDate, String sFormat, String dFormat) {
		String retStr = "";
		try {
			if (sDate == null || "".equals(sDate))
				return sDate;
			if (sFormat == null || "".equals(sFormat))
				return sDate;
			if (dFormat == null || "".equals(dFormat))
				return sDate;
			sFormat = sFormat.toUpperCase();
			dFormat = dFormat.toUpperCase();
			sFormat = replace(sFormat, "HH24", "HH");
			dFormat = replace(dFormat, "HH24", "HH");
			retStr = dFormat;
			int iPos = sFormat.indexOf("YYYY");
			String year = "";
			if (iPos > -1)
				year = sDate.substring(iPos, iPos + 4);
			iPos = sFormat.indexOf("MM");
			String month = "";
			if (iPos > -1)
				month = sDate.substring(iPos, iPos + 2);
			iPos = sFormat.indexOf("DD");
			String day = "";
			if (iPos > -1)
				day = sDate.substring(iPos, iPos + 2);
			String hour = "";
			iPos = sFormat.indexOf("HH");
			if (iPos > -1)
				hour = sDate.substring(iPos, iPos + 2);
			String minute = "";
			iPos = sFormat.indexOf("MI");
			if (iPos > -1)
				minute = sDate.substring(iPos, iPos + 2);
			String second = "";
			iPos = sFormat.indexOf("SS");
			if (iPos > -1)
				second = sDate.substring(iPos, iPos + 2);
			year = rpad(year, 4, "0");
			month = rpad(month, 2, "0");
			day = rpad(day, 2, "0");
			hour = rpad(hour, 2, "0");
			minute = rpad(minute, 2, "0");
			second = rpad(second, 2, "0");
			retStr = replace(retStr, "YYYY", year);
			retStr = replace(retStr, "MM", month);
			retStr = replace(retStr, "DD", day);
			retStr = replace(retStr, "HH", hour);
			retStr = replace(retStr, "MI", minute);
			retStr = replace(retStr, "SS", second);
		} catch (Exception e) {
			System.err.println("StringB.transDateString() 方法错误，相关信息如下：");
			e.printStackTrace();
			return null;
		}
		return retStr;
	}
	
	
	/**
	 * 替换一个串中的指定的子串
	 * 
	 * @param strExp
	 * @param strFind
	 * @param strRep
	 * @return
	 */
	public static String tickParamName(String strExp, String stick) {
		int intFrom=-1, intTo=-1;
		String strTail;
		if (stick == null || stick.length() == 0)
			return strExp;
		//第一个定位标记
		intFrom = strExp.indexOf(stick, 0);
		if(intFrom >= 0) {
			intTo = intFrom + stick.length();			
			strTail = strExp.substring(intTo);
			//第二个标记
			intTo = strTail.indexOf(stick, 0);
		}else{
			return "";
		}
		//
		if(intTo == -1){
			return "";
		}
		return strExp.substring(intFrom, intFrom+intTo+stick.length()+1);
	}
	
	
	/**
	 * 检查字符串是否为空
	 * @param str 字符串
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null) {
			return true;
		} else if (str.length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 检查字符串是否为空。空返回true,非空返回false
	 * 
	 * @param ret
	 * @return
	 */
	public static boolean checkEmptyString(String ret) {
		boolean isTrue = false;
		if (ret == null || "".equals(ret.trim())) {
			isTrue = true;
		}
		return isTrue;
	}

	/**
	 * 功能描述:将字符串数组练成字符串(不去除重复数据用逗号分隔)
	 * 
	 * @param arr
	 *            字符串数组
	 * @return 返回值 :String 字符串
	 */
	public static String changArrToStr(String[] arr) {
		String ret = "";
		for (int i = 0; arr != null && i < arr.length; i++) {
			if (ret.length() > 0)
				ret += ",";
			ret += arr[i].trim();
		}
		return ret;
	}


	/**
	 * 功能描述:将字符串数组练成字符串(不去除重复数据用\n分隔)
	 * 
	 * @param arr
	 *            字符串数组
	 * @return 返回值 :String 字符串
	 */
	public static String changArrToStrWithLn(String[] arr) {
		String ret = "";
		for (int i = 0; arr != null && i < arr.length; i++) {
			if (ret.length() > 0)
				ret += "\n";
			ret += arr[i].trim();
		}
		return ret;
	}

	/**
	 * 功能描述:将字符串数组前后添加特定字符练成字符串(不去除重复数据用逗号分隔)
	 * 
	 * @param arr
	 *            字符串数组
	 * @param addStr
	 *            添加指定的字符
	 * @return 返回值 :String 字符串
	 */
	public static String changArrToStr(String[] arr, String addStr) {
		String ret = "";
		for (int i = 0; arr != null && i < arr.length; i++) {
			if (ret.length() > 0)
				ret += ",";
			ret += addStr + arr[i].trim() + addStr;
		}
		return ret;
	}

	/**
	 * 功能描述:将二纬字符串数组某列练成字符串(去除重复数据用逗号分隔)
	 * 
	 * @param arr
	 *            字符串数组
	 * @param iIndex
	 *            需要转换的列的索引
	 * @return 返回值 :String 字符串
	 */
	public static String changArrToStr(String[][] arr, int iIndex) {
		String ret = ",";
		for (int i = 0; arr != null && i < arr.length; i++) {
			if (ret.indexOf("," + arr[i][iIndex] + ",") < 0)
				ret += arr[i][iIndex] + ",";
		}
		if (!"".equals(ret) && ret.length() > 1)
			ret = trim(ret, ",");
		return ret;
	}

	/**
	 * 功能描述:将二纬字符串数组某列练成字符串(去除重复数据用逗号分隔，并用单引号括住)，
	 * 
	 * @param arr
	 *            字符串数组
	 * @param iIndex
	 *            需要转换的列的索引
	 * @return 返回值 :String 字符串
	 */
	public static String changArrToStrComma(String[][] arr, int iIndex) {
		String ret = ",'";
		for (int i = 0; arr != null && i < arr.length; i++) {
			if (ret.indexOf(",'" + arr[i][iIndex] + "',") < 0)
				ret += arr[i][iIndex] + "','";
		}
		if (!"".equals(ret) && ret.length() > 2) {
			ret = ret.substring(1, ret.length() - 2);
		}
		return ret;
	}

	/**
	 * 功能描述:将二纬字符串数组某列练成字符串(不去除重复数据用逗号分隔)
	 * 
	 * @param arr
	 *            字符串数组
	 * @param iIndex
	 *            需要转换的列的索引
	 * @return 返回值 :String 字符串
	 */
	public static String changArrToStrAll(String[][] arr, int iIndex) {
		String ret = "";
		for (int i = 0; arr != null && i < arr.length; i++) {
			if (ret.length() > 0)
				ret += ",";
			ret += arr[i][iIndex];
		}
		return ret;
	}

	/**
	 * 功能描述:将二纬字符串数组某列练成字符串(不去除重复数据用逗号分隔)
	 * 
	 * @param arr
	 *            对象数组
	 * @param p1
	 *            需要转换的列的名称
	 * @return 返回值 :String 字符串
	 */
//	public static String changArrToStrAll(Object[] arr, String p1) {
//		String ret = "";
//		for (int i = 0; arr != null && i < arr.length; i++) {
//			if (ret.length() > 0)
//				ret += ",";
//			ret += ReflectUtil.getStringFromObj(arr[i], p1);
//		}
//		return ret;
//	}

	/**
	 * 功能描述:将二纬字符串数组某列练成字符串(除重复数据用逗号分隔)
	 * 
	 * @param arr
	 *            对象数组
	 * @param p1
	 *            需要转换的列的名称
	 * @return 返回值 :String 字符串
	 */
//	public static String changArrToStrComma(Object[] arr, String p1) {
//		String ret = ",'";
//		for (int i = 0; arr != null && i < arr.length; i++) {
//			String tmp = ReflectUtil.getStringFromObj(arr[i], p1);
//			if (ret.indexOf(",'" + tmp + "',") < 0)
//				ret += tmp + "','";
//		}
//		if (!"".equals(ret) && ret.length() > 2) {
//			ret = ret.substring(1, ret.length() - 2);
//		}
//		return ret;
//	}

	/**
	 * 功能描述:将二纬字符串数组转成下拉列表字符串
	 * 
	 * @param arr
	 *            字符串数组
	 * @return 返回值 :String 字符串
	 */
	public static String changArrsToStrAll(String[][] arr) {
		String ret = "";
		for (int i = 0; arr != null && i < arr.length; i++) {
			ret += arr[i][0] + "," + arr[i][1] + ";";
		}
		if (ret.length() > 1)
			ret = ret.substring(0, ret.length() - 1);
		return ret;
	}

	/**
	 * 获得日期的中文描述
	 * 
	 * @param n
	 *            负向前，正向后
	 * @return
	 */
	public static String getCNPrivousDate(int n) {
		String sDate = DateUtil4Timeslecte.getDiffDay(n, DateUtil4Timeslecte.getNowDate());
		String resCN = sDate.substring(0, 4) + "年" + sDate.substring(4, 6)
				+ "月" + sDate.substring(6, 8) + "日";
		return resCN;
	}

	/**
	 * 获得月份的中文描述
	 * 
	 * @param n
	 *            负向前，正向后
	 * @return
	 */
	public static String getPrivousMonthCn(int n) {
		String sMonth = DateUtil4Timeslecte.getDiffMonth(n, DateUtil4Timeslecte.getNowDate());
		String resCN = sMonth.substring(0, 4) + "年" + sMonth.substring(4, 6)
				+ "月";
		return resCN;
	}

	/**
	 * 获得日期的中文描述
	 * 
	 * @param sDate
	 *            日期值
	 * @param cycle
	 *            日期周期
	 * @return
	 */
	public static String getCnDate(String sDate, String cycle) {
		if (sDate == null || "".equals(sDate))
			return "";

		String cnDate = sDate;
		if ("4".equals(cycle)) {
			// 月
			if (sDate.length() != 6)
				return sDate;

			cnDate = sDate.substring(0, 4) + "年" + sDate.substring(4, 6) + "月";

		} else if ("6".equals(cycle)) {
			// 日
			if (sDate.length() != 8)
				return sDate;

			cnDate = sDate.substring(0, 4) + "年" + sDate.substring(4, 6) + "月"
					+ sDate.substring(6, 8) + "日";
		}

		return cnDate;
	}

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
	public static String emptyConvert(String str , String str1){
		return isNotEmpty(str)?str:str1;
	}
	
    public static String trim(String str) {
        return str == null ? null : str.trim();
    }
 
    public static String replaceOnce(String text, String searchString, String replacement) {
        return replace(text, searchString, replacement, 1);
    }
    
    public static String replace(String text, String searchString, String replacement, int max) {
        if (isEmpty(text) || isEmpty(searchString) || replacement == null || max == 0) {
            return text;
        }
        int start = 0;
        int end = text.indexOf(searchString, start);
        if (end == -1) {
            return text;
        }
        int replLength = searchString.length();
        int increase = replacement.length() - replLength;
        increase = (increase < 0 ? 0 : increase);
        increase *= (max < 0 ? 16 : (max > 64 ? 64 : max));
        StringBuffer buf = new StringBuffer(text.length() + increase);
        while (end != -1) {
            buf.append(text.substring(start, end)).append(replacement);
            start = end + replLength;
            if (--max == 0) {
                break;
            }
            end = text.indexOf(searchString, start);
        }
        buf.append(text.substring(start));
        return buf.toString();
    }
    
    public static boolean startsWith(String str, String prefix) {
        return startsWith(str, prefix, false);
    }
    public static boolean startsWithIgnoreCase(String str, String prefix) {
        return startsWith(str, prefix, true);
    }
    public static boolean startsWith(String str, String prefix,boolean ignoreCase) {
        return startsOrEndsWith(str, prefix, ignoreCase,false);
    }
    
    public static boolean endsWith(String str, String suffix) {
        return endsWith(str, suffix, false);
    }
    public static boolean endsWithIgnoreCase(String str, String suffix) {
        return endsWith(str, suffix, true);
    }
    public static boolean endsWith(String str, String prefix,boolean ignoreCase) {
        return startsOrEndsWith(str, prefix, ignoreCase,true);
    }

    private static boolean startsOrEndsWith(String str, String subStr,boolean ignoreCase,  boolean  endWidth) {
        if (str == null || subStr == null) {
            return (str == null && subStr == null);
        }
        if (subStr.length() > str.length()) {
            return false;
        }
        int strOffset=0;
        if (endWidth){
        	strOffset = str.length() - subStr.length();
        }
       	return str.regionMatches(ignoreCase, strOffset, subStr, 0, subStr.length());
    }
    
    public static final String EMPTY = "";
    public static String join(String[] array) {
        return join(array, null);
    }
    public static String join(String[] list, String separator) {
    	separator=separator==null?EMPTY:separator;
        StringBuffer buff = new StringBuffer(5 * list.length);
        for (int i = 0; i < list.length; i++) {
            String s = list[i];
        	if (i > 0) {
                buff.append(separator);
            }
            if (s != null) {
            	buff.append(s);
            }
        }
        return buff.toString();

    }

    public static String[] split2Array(String s, char separatorChar){
    	return split2Array(s,separatorChar,false);
    }
    public static String[] split2Array(String s, char separatorChar, boolean trim) {
        if (s == null) {
            return null;
        }
        if (s.length() == 0) {
            return new String[0];
        }
        ArrayList<String> list = new ArrayList<String>();
        StringBuffer buff = new StringBuffer(s.length());
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == separatorChar) {
                String e = buff.toString();
                list.add(trim ? e.trim() : e);
                buff.setLength(0);
            } else if (c == '\\' && i < s.length() - 1) {
                buff.append(s.charAt(++i));
            } else {
                buff.append(c);
            }
        }
        String e = buff.toString();
        list.add(trim ? e.trim() : e);
        String[] array = new String[list.size()];
        list.toArray(array);
        return array;
    }

	public static void main(String[] args) {
	}
}