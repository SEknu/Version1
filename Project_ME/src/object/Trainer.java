package object;

public class Trainer {
	
	private String ID;			// 트레이너 번호 & 로그인 ID
	private String Regist_Date;	// 입사일
	private int salary;			// 월급
	private String Name;		// 개인정보 : 이름
	private String Address;		// 개인정보 : 주소
	private String Phone;		// 개인전호 : 전화번호
	/*
id				VARCHAR2(10),
grade			CHAR(10),
regi_date		DATE,
attend			NUMBER,
vacation_day	NUMBER,
salary			NUMBER,
password		VARCHAR2(12),
name			VARCHAR2(10),
age				NUMBER,
addr			VARCHAR2(40 CHAR),
phone			CHAR(12),
	 */
/* 수정
id				VARCHAR2(10),
`name` 			VARCHAR(10) NOT NULL,
`regi_date`		DATE,
`salary`		INT,
`age`			INT,
`addr`			VARCHAR(50),
`phone`			CHAR(12), 
*/

	/* constructor */
	public Trainer()
	{
	}
	
	public Trainer(String id, String rd, int sal, String n, String addr, String phone) {
		this.setID(id);
		//this.setGrade(g);
		this.setRegistDate(rd);
		//this.setAttendance(att);
		//this.setVacation(v);
		this.setSalary(sal);
		//this.setPW(pw);
		this.setName(n);
		//this.setAge(age);
		this.setAddress(addr);
		this.setPhone(phone);
	}
	
	public Trainer(String id, String rDate, String name,String addr, String phone) {
		this.setID(id);
		this.setRegistDate(rDate);
		this.setName(name);
		this.setAddress(addr);
		this.setPhone(phone);
	}
	/* constructor */

	/* set & get function */
	public void setID(String id)
	{
		this.ID = id;
	}
	public String getID()
	{
		return this.ID;
	}
	
	public void setRegistDate(String rDate)
	{
		this.Regist_Date = rDate;
	}
	public String getRegistDate()
	{
		return this.Regist_Date;
	}
	
	public void setSalary(int sal)
	{
		this.salary = sal;
	}
	public int getSalary()
	{
		return this.salary;
	}
	
	public void setName(String n)
	{
		this.Name = n;
	}
	public String getName()
	{
		return this.Name;
	}
	
	public void setAddress(String addr)
	{
		this.Address = addr;
	}
	public String getAddress()
	{
		return this.Address;
	}
	
	public void setPhone(String phone)
	{
		this.Phone = phone;
	}
	public String getPhone()
	{
		return this.Phone;
	}	
	/* set & get function */
	
	public String toString() {
		return this.getName();
	}
}