package object;


public class Trainer {
	
	private String ID;			// Ʈ���̳� ��ȣ & �α��� ID
	private String grade;		// Ʈ���̳� ���
	private String Regist_Date;	// �Ի���
	private int attendance;		// �⼮��
	private int vacation;		// �ް� �ϼ�
	private int salary;			// ����
	private String PW;			// �α��ν� ��й�ȣ
	private String Name;		// �������� : �̸�
	private int Age;			// �������� : ����
	private String Address;		// �������� : �ּ�
	private String Phone;		// ������ȣ : ��ȭ��ȣ
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
	/* constructor */
	public Trainer()
	{
	}
	
	public Trainer(String id, String g, String rd, int att, int v, int sal, String pw,
					String n, int age, String addr, String phone) {
		this.setID(id);
		this.setGrade(g);
		this.setRegistDate(rd);
		this.setAttendance(att);
		this.setVacation(v);
		this.setSalary(sal);
		this.setPW(pw);
		this.setName(n);
		this.setAge(age);
		this.setAddress(addr);
		this.setPhone(phone);
	}

	public Trainer(String id, String rDate, String name, int age, String addr, String phone) {
		this.setID(id);
		this.setRegistDate(rDate);
		this.setName(name);
		this.setAge(age);
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
	
	public void setGrade(String g)
	{
		this.grade = g;
	}
	public String setGrade()
	{
		return this.grade;
	}
	
	public void setRegistDate(String rDate)
	{
		this.Regist_Date = rDate;
	}
	public String getRegistDate()
	{
		return this.Regist_Date;
	}
	
	public void setAttendance(int att)
	{
		this.attendance = att;
	}
	public int getAttendance()
	{
		return this.attendance;
	}
	
	public void setVacation(int v)
	{
		this.vacation = v;
	}
	public int getVacation()
	{
		return this.vacation;
	}
	
	public void setSalary(int sal)
	{
		this.salary = sal;
	}
	public int getSalary()
	{
		return this.salary;
	}
	
	public void setPW(String pw)
	{
		this.PW = pw;
	}
	public String getPW()
	{
		return this.PW;
	}
	
	public void setName(String n)
	{
		this.Name = n;
	}
	public String getName()
	{
		return this.Name;
	}
	
	public void setAge(int age)
	{
		this.Age = age;
	}
	public int getAge()
	{
		return this.Age;
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