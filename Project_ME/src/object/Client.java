package object;

import java.util.Calendar;


public class Client {
	private String ID = null; 				// 회원번호 & 로그인 ID
	private String grade = null;			// 회원 등급
	private String Regist_Date = null; 		// 등록날짜
	private int attendance = 0;				// 출석 일수
	private String Terminate_Date = null;	// 만료일자
	private boolean currentStatus = false;	// 현재 등록 여부
	private int height = 0;					// 신체정보 : 키
	private int weight = 0;					// 신쳊정보 : 몸무게
	private int bodyFat = 0;				// 신체정보 : 체지방
	private int bodyMuscle = 0;				// 신체정보 : 근육량
	private String PW = null;				// 로그인 PW
	private String note= null;				// 특이사항
	private String Name = null;				// 개인정보 : 이름
	private int Age = 0;					// 개인정보 : 나이
	private String Address = null;			// 개인정보 : 주소
	private String Phone = null;			// 개인전호 : 전화번호
	private String trainer = null;			// 배정 트레이너
	private String purpose = null;			// 운동 목적
	private int week = 0;					// 주 몇 회 운동하는가?
	private int time = 0;					// 운동은 하루에 몇 시간을 하는가?
	/*
id				VARCHAR2(10),
grade			CHAR(10),
regi_date		DATE,
attend			NUMBER,
terminate_date	DATE,
state			CHAR(1),
height			NUMBER,
weight			NUMBER,
fat				NUMBER,
muscle			NUMBER,
name			VARCHAR2(10),
age				NUMBER,
addr			VARCHAR2(40 CHAR),
phone			CHAR(12),
password		VARCHAR2(12),
note			VARCHAR2(200 CHAR),
trainer			VARCHAR2(10),
	 */
	
	/* constructor */
	public Client() {
		
	}
	
	public Client(String id, String grade, String rd, int att, String td,
			boolean s, int h, int w, int f, int m, String pw, String note,
			String name, int age, String addr, String phone, String t) {
		this.setID(id);
		this.setName(name);
		this.setAddress(addr);
		this.setPhone(phone);
		this.setGrade(grade);
		this.setRegistDate(rd);
		this.setAttendance(att);
		this.setTerminateDate(td);
		this.setCurrentStatus(s);
		this.setHeight(h);
		this.setWeight(w);
		this.setBodyFat(f);
		this.setBodyMuscle(m);
		this.setPW(pw);
		this.setNote(note);
		this.setAge(age);
		this.setTrainer(t);
	}
	
	public Client(String id, String name, String addr, String phone, int h, int w, int f, int m,
			String purpose, int week, int time) {
		this.setID(id);
		this.setName(name);
		this.setAddress(addr);
		this.setPhone(phone);
		this.setHeight(h);
		this.setWeight(w);
		this.setBodyFat(f);
		this.setBodyMuscle(m);
		
		// 기본값 정하기		
		this.setGrade("");
		/**/
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		/**/
		this.setRegistDate(year + "-" + month + "-" + day);
		this.setAttendance(0);
		/**/
		if (month + 3 > 12)
			++year;
		month += 3;
		month = month % 12;
		if (month == 0)
			month = 1;
		/**/
		this.setTerminateDate(year + "-" + month + "-" + day);
		this.setCurrentStatus(true);
		this.setPW("");
		this.setNote("");
		this.setAge(0);
//		this.setTrainer(null);
	}
	/* constructor */
	
	/*
	public Vector<String> toStringVector()
	{
		Vector<String> result = new Vector<String>();
		
		result.add(Integer.toString(getID()));
		result.add(getGrade());
		result.add(getRegistDate().toString());
		result.add(Integer.toString(getAttendance()));
		result.add(getTerminateDate().toString());
		result.add(Boolean.toString(geCurrentStatus()));
		result.add(Integer.toString(getHeight()));
		result.add(Integer.toString(getWeight()));
		result.add(Integer.toString(getBodyFat()));
		result.add(Integer.toString(getBodyMuscle()));
		result.add(getPW());
		result.add(getNote());
		result.add(getName());
		result.add(Integer.toString(getAge()));
		result.add(getAddress());
		result.add(getPhone());
		result.add(Integer.toString(getTrainer()));
		
		return result;
	}
	*/
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
	
	public void setGrade(String grade)
	{
		this.grade = grade;
	}
	public String getGrade()
	{
		return this.grade;
	}
	
	public void setRegistDate(String rd)
	{
		this.Regist_Date = rd;
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
	
	public void setTerminateDate(String td)
	{
		this.Terminate_Date = td;
	}
	public String getTerminateDate()
	{
		return this.Terminate_Date;
	}
	
	public void setCurrentStatus(boolean s)
	{
		this.currentStatus = s;
	}
	public boolean getCurrentStatus()
	{
		return this.currentStatus;
	}
	
	public void setHeight(int h)
	{
		this.height = h;
	}
	public int getHeight()
	{
		return this.height;
	}
	
	public void setWeight(int w)
	{
		this.weight = w;
	}
	public int getWeight()
	{
		return this.weight;
	}
	
	public void setBodyFat(int f)
	{
		this.bodyFat = f;
	}
	public int getBodyFat()
	{
		return this.bodyFat;
	}
	
	public void setBodyMuscle(int m)
	{
		this.bodyMuscle = m;
	}
	public int getBodyMuscle()
	{
		return this.bodyMuscle;
	}
	
	public void setPW(String pw)
	{
		this.PW = pw;
	}
	public String getPW()
	{
		return this.PW;
	}
	
	public void setNote(String note)
	{
		this.note = note;
	}
	public String getNote()
	{
		return this.note;
	}
	
	public void setName(String name)
	{
		this.Name = name;
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
	
	public void setTrainer(String t)
	{
		this.trainer = t;
	}
	public String getTrainer()
	{
		return this.trainer;
	}
	
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getPurpose() {
		return this.purpose;
	}
	
	public void setWeek(int week) {
		this.week = week;
	}
	public int getWeek() {
		return this.week;
	}
	
	public void setTime(int time) {
		this.time = time;
	}
	public int getTime() {
		return this.time;
	}
	/* set & get function */

	// 출석 늘리기
	public void incAttendance() {
		this.setAttendance(getAttendance()+1);
		database.FileManager.getInstance().update(this, "client");
	}
	
	@Override
	public String toString() {
		String out = this.getID() + " " +
				this.getGrade() + " " +
				this.getRegistDate() + " " +
				this.getAttendance() + " " +
				this.getTerminateDate() + " " +
				this.getCurrentStatus() + " " +
				this.getHeight() + " " +
				this.getWeight() + " " +
				this.getBodyFat() + " " +
				this.getBodyMuscle() + " " +
				this.getPW() + " " +
				this.getNote() + " " +
				this.getAge() + " " +
				this.getAddress() + " " +
				this.getPhone() + " " +
				this.getTrainer() + " " +
				this.getPurpose() + " " +
				this.getWeek() + " " +
				this.getTime();
		
		return out;
	}
}
