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

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getRegistDate() {
		return Regist_Date;
	}

	public void setRegistDate(String regist_Date) {
		Regist_Date = regist_Date;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}


}