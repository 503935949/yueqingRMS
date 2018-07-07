//package com.yueqingRMS.util;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class DbFunctionUtil {
//
//    public static int YYYY_MM_DD_HH_MM_SS = 0;
//    public static int YYYY_MM_DD = 1;
//    public static int YYYY_MM = 2;
//    public static int DD = 3;
//    public static int HH_MM_SS = 4;
//    public static int HH_MM = 5;
//
//    public static int FIELD = 0;
//    public static int PARAM = 1;
//
//    public DbFunctionUtil() {
//
//    }
//
//    public static int getDbType() {
//        String driver = PropertiesConfigUtil.getValue("jdbc.driver");
//        int flag = 0;
//        if (driver.toLowerCase().indexOf("mysql") != -1) {
//            flag = 0;
//        } else if (driver.toLowerCase().indexOf("oracle") != -1) {
//            flag = 1;
//        }
//        return flag;
//    }
//
//    private static String mysqlDate(int datefmt) {
//        String sql = "";
//        switch (datefmt) {
//            case 0:
//                sql += "%Y-%m-%d %H:%i:%s";
//                break;
//            case 1:
//                sql += "%Y-%m-%d";
//                break;
//            case 2:
//                sql += "%Y-%m";
//                break;
//            case 3:
//                sql += "%d";
//                break;
//            case 4:
//                sql += "%H:%i:%s";
//        }
//        return sql;
//    }
//
//    private static String oracleDate(int datefmt) {
//        String sql = "";
//        switch (datefmt) {
//            case 0:
//                sql += "yyyy-MM-dd HH24:mi:ss";
//                break;
//            case 1:
//                sql += "yyyy-MM-dd";
//                break;
//            case 2:
//                sql += "yyyy-MM";
//                break;
//            case 3:
//                sql += "dd";
//                break;
//            case 4:
//                sql += "HH24:mi:ss";
//                break;
//            case 5:
//            	sql += "HH24:mi";
//        }		
//        return sql;
//    }
//
//    private static String variable(String param, int type) {
//        String sql = "";
//        switch (type) {
//            case 0:
//                sql = param;
//                break;
//            case 1:
//                sql = "'" + param + "'";
//                break;
//        }
//        return sql;
//    }
//    
//    private static String variable(String param) {
//        return param;
//    }
//    
//    /**
//     * 格式化时间
//     * 
//     * @param param
//     *            属性名
//     * @param paramType
//     *            key:属性值，value:转换后的值
//     * @return
//     */
//    public static String decode(Map<String,String> paramType,String param) {
//        String sql = "";
//        String elseSql = "";
//        switch (getDbType()) {
//            case 0:
//                sql += "case ";
//                for(String key : paramType.keySet()){
//                    if(!key.equals("")){
//                        sql += " when " + param + "=" + key +" then " + paramType.get(key);
//                    }else{
//                        elseSql = " else " + paramType.get(key) + "";
//                    }
//                }
//                //sql += elseSql + ")";
//                sql += elseSql + " end";
//                break;
//            case 1:
//                sql += "decode("+param;
//                for(String key : paramType.keySet()){
//                    if(!key.equals("")){
//                        sql += "," + key + ","+paramType.get(key);
//                    }else{
//                        elseSql = "," + paramType.get(key);
//                    }
//                }
//                sql += elseSql + ")";
//                break;
//        }
//        return sql;
//    }
//    
//    public static void main(String[] args) {
//        Map<String,String> paramType = new HashMap();
//        paramType.put("'0'", "'未读'");
//        paramType.put("'1'", "'已读'");
//       // paramType.put("", "'&&&&&'");
//        System.out.println(DbFunctionUtil.decode(paramType, "n.status"));
//    }
//
//    /**
//     * 格式化时间
//     * 
//     * @param param
//     *            属性名或者参数
//     * @param type
//     *            0 属性 1 参数
//     * @param datefmt
//     *            0=yyyy-MM-dd HH24:mi:ss 1=yyyy-MM-dd 2=yyyy-MM 3=dd 4=HH24:mi:ss
//     * @return
//     */
//    public static String formatDate(String param, int type, int datefmt) {
//        String sql = "";
//        switch (getDbType()) {
//            case 0:
//                sql = " DATE_FORMAT(" + param + ",'" + mysqlDate(datefmt) + "') ";
//                break;
//            case 1:
//                sql = " TO_CHAR(" + param + ",'" + oracleDate(datefmt) + "') ";
//                break;
//        }
//        return sql;
//    }
//    
//    /**
//     * 格式化时间
//     * 
//     * @param param
//     *            属性名或者参数
//     * @param type
//     *            0 属性 1 参数
//     * @param datefmt
//     *            0=yyyy-MM-dd HH24:mi:ss 1=yyyy-MM-dd 2=yyyy-MM 3=dd 4=HH24:mi:ss
//     * @return
//     */
//    public static String toDate(String param,  int datefmt) {
//        String sql = "";
//        switch (getDbType()) {
//            case 0:
//                sql = " str_to_date('" + param + "','" + mysqlDate(datefmt) + "') ";
//                break;
//            case 1:
//                sql = " TO_DATE('" + param + "','" + oracleDate(datefmt) + "') ";
//                break;
//        }
//        return sql;
//    }
//    /**
//     * 格式化时间 传入的参数是 ？
//     * 
//     * @param param
//     *            属性名或者参数
//     * @param type
//     *            0 属性 1 参数
//     * @param datefmt
//     *            0=yyyy-MM-dd HH24:mi:ss 1=yyyy-MM-dd 2=yyyy-MM 3=dd 4=HH24:mi:ss
//     * @return
//     */
//    public static String toDateP(String param,  int datefmt) {
//        String sql = "";
//        switch (getDbType()) {
//            case 0:
//                sql = " STR_TO_DATE(" + param + ",'" + mysqlDate(datefmt) + "') ";
//                break;
//            case 1:
//                sql = " TO_DATE(" + param + ",'" + oracleDate(datefmt) + "') ";
//                break;
//        }
//        return sql;
//    }
//    
//    public static String getSysDate() {
//        String sql = "";
//        switch (getDbType()) {
//            case 0:
//                sql = "now()";
//                break;
//            case 1:
//                sql = "sysdate";
//                break;
//        }
//        return sql;
//    }
//
//    /**
//     * 日期计算-添加，返回时间格式 年-月-日 时:分:秒
//     * 
//     * @param param
//     *            参数 时间格式 年-月-日 时:分:秒
//     * @param type
//     *            0 属性 1 参数
//     * @param diff
//     *            差值
//     * @param datetype
//     *            类型 : day、month
//     * @return
//     */
//    public static String dateAdd(String param, int type, String diff, String datetype) {
//        String sql = "";
//        switch (getDbType()) {
//            case 0:
//                if (datetype.equals("day")) {
//                    sql = " DATE_ADD(" + variable(param, type) + ",INTERVAL " + diff + " DAY) ";
//                } else if (datetype.equals("month")) {
//                    sql = " DATE_ADD(" + variable(param, type) + ",INTERVAL " + diff + " MONTH) ";
//                }
//                break;
//            case 1:
//                if (datetype.equals("day")) {
//                    sql = " TO_DATE(" + variable(param, type) + ",'yyyy-MM-dd HH24:mi:ss') + " + diff;
//                } else if (datetype.equals("month")) {
//                    sql = " ADD_MONTHS(TO_DATE(" + variable(param, type) + ",'yyyy-MM-dd HH24:mi:ss')," + diff + ") ";
//                }
//                break;
//        }
//        return sql;
//    }
//
//    /**
//     * 日起计算-减少，返回时间格式 年-月-日 时:分:秒
//     * 
//     * @param param
//     *            参数 时间格式 年-月-日 时:分:秒
//     * @param type
//     *            0 属性 1 参数
//     * @param diff
//     *            差值
//     * @param datetype
//     *            类型 : day、month
//     * @return
//     */
//    public static String dateSub(String param, int type, String diff, String datetype) {
//        String sql = "";
//        switch (getDbType()) {
//            case 0:
//                if (datetype.equals("day")) {
//                    sql = " DATE_SUB(" + variable(param, type) + ",INTERVAL " + diff + " DAY) ";
//                } else if (datetype.equals("month")) {
//                    sql = " DATE_SUB(" + variable(param, type) + ",INTERVAL " + diff + " MONTH) ";
//                }
//                break;
//            case 1:
//                if (datetype.equals("day")) {
//                    sql = " TO_DATE(" + variable(param, type) + ",'yyyy-MM-dd HH24:mi:ss') - " + diff;
//                } else if (datetype.equals("month")) {
//                    sql = " ADD_MONTHS(TO_DATE(" + variable(param, type) + ",'yyyy-MM-dd HH24:mi:ss'),-" + diff + ") ";
//                }
//                break;
//        }
//        return sql;
//    }
//
//    /**
//     * 非空判断
//     * 
//     * @param param
//     *            需要判断的参数
//     * @param result
//     *            参数为空返回的值
//     * @return
//     */
//    public static String isNull(String param, String result) {
//        String sql = "";
//        switch (getDbType()) {
//            case 0:
//                sql = " IFNULL(" + param + ",'" + result + "')";
//                break;
//            case 1:
//                sql = " NVL(" + param + ",'" + result + "')";
//                break;
//        }
//        return sql;
//    }
//
//    /**
//     * 按拼音顺序排序
//     * 
//     * @param param
//     *            属性名或者参数
//     * @param type
//     *            0 属性 1 参数
//     * @return
//     */
//    public static String pinyinOrder(String param, int type) {
//        String sql = "";
//        switch (getDbType()) {
//            case 0:
//                sql = " CONVERT(" + variable(param, type) + " USING gbk)";
//                break;
//            case 1:
//                sql = " nlssort(" + variable(param, type) + ", 'NLS_SORT=SCHINESE_PINYIN_M')";
//                break;
//        }
//        return sql;
//    }
//
//    /**
//     * 函数 WM_CONCAT or GROUP_CONCAT
//     * 
//     * @param param
//     * @param type
//     * @return
//     */
//    public static String concat(String param, int type) {
//        String sql = "";
//        switch (getDbType()) {
//            case 0:
//                sql = " GROUP_CONCAT(" + param + ")";
//                break;
//            case 1:
//                sql = " WM_CONCAT(" + param + ")";
//                break;
//        }
//        return sql;
//    }
//    
//    /**
//     * @description 空FROM字符串
//     *              oracle对应from dual;
//     *              mysql可以没有from语句。
//     * @return
//     */
//    public static String getEmptyFrom() {
//        String reStr = "";               
//        switch (getDbType()) {
//            case SysConstants.DB_CONSTANTS.DB_TYPE_MYSQL: reStr = "";break;
//            case SysConstants.DB_CONSTANTS.DB_TYPE_ORACLE: reStr = " from dual ";break;
//            default: reStr = "";
//        }
//        
//        return reStr;
//    }
//    
//    /**
//     * @description 数据库字符串截取函数
//     * @param srcType 为0，代表srcString为字段名；为1，代表srcString为字符串
//     * @param startIndex 该参数请按照oracle标志输入，以0为起始索引
//     * @return
//     */
//    public static String subStr(String srcString, int srcType, int startIndex, int endIndex) {
//        String reStr = "";
//        switch (getDbType()) {
//            case SysConstants.DB_CONSTANTS.DB_TYPE_MYSQL:
//                reStr = " substr(" + variable(srcString, srcType) + "," + (startIndex + 1) + "," + endIndex + ")";
//                break;
//            case SysConstants.DB_CONSTANTS.DB_TYPE_ORACLE: 
//                reStr = " substr(" + variable(srcString, srcType) + ", " + startIndex + ", " + endIndex + ") ";break;
//            default: reStr = "";
//        }        
//        return reStr;
//    }
//    
//    public static String contactStr(int type,String... str){
//    	StringBuilder sql = new StringBuilder();
//        switch (getDbType()) {
//            case 0:
//            	sql.append(" concat(");
//                for(String param : str){
//                	sql.append(param).append(",");
//                }
//                sql = sql.deleteCharAt(sql.length() - 1);
//                sql.append(")");
//                break;
//            case 1:
//            	for(String param : str){
//                	sql.append(param).append("||");
//                }
//            	sql = sql.delete(sql.length()-2, sql.length());
//                break;
//        }
//        return sql.toString();
//    }
//}
