package com.yueqingRMS.business.system.domain;

import com.yueqingRMS.platform.tools.page.Page;



/**   
*    
* 项目名称：yueqingRMS   
* 类名称：UserDomain   
* 类描述：   
* 创建人：林曌   
* 创建时间：2017年7月18日 下午4:03:23   
* 修改人：   
* 修改时间：2017年7月18日 下午4:03:23   
* 修改备注：   
* @version    
*    
*/
public class UserDomain extends Page {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 621034699214581576L;
	
	/**
	 *  studentId 学生ID
	 */
	private String studentId;
	/**
	 *  studentName 学生姓名
	 */
	private String studentName;
	
	/**
	 * sax	学生性别
	 */
	private String sax;
	
	/**
	 * classId 班级
	 */
	private String classId;
	
	/**
	 * phone 联系电话
	 */
	private String phone;
	
	/**
	 * email 邮箱地址
	 */
	private String email;
	
	/**
	 * address 通讯地址
	 */
	private String address;
	
	
	
	/**
	 * @return studentId
	 */
	public String getStudentId() {
		return studentId;
	}



	/**
	 * @param studentId
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}



	/**
	 * @return studentName
	 */
	public String getStudentName() {
		return studentName;
	}



	/**
	 * @param studentName
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}



	/**
	 * @return sax
	 */
	public String getSax() {
		return sax;
	}



	/**
	 * @param sax
	 */
	public void setSax(String sax) {
		this.sax = sax;
	}



	/**
	 * @return classId
	 */
	public String getClassId() {
		return classId;
	}



	/**
	 * @param classId
	 */
	public void setClassId(String classId) {
		this.classId = classId;
	}



	/**
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}



	/**
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}



	/**
	 * @return email
	 */
	public String getEmail() {
		return email;
	}



	/**
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}



	/**
	 * @return address
	 */
	public String getAddress() {
		return address;
	}



	/**
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}



	/**
	 * @return serialVersionUID
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
