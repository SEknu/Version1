package object;

import java.util.Vector;

public class Trainer {
	
	private String id;				// 트레이너 번호
	private String loginId;			// 로그인ID
	private String pwd;				// 로그인 PW
	private String registDate;		// 입사일
	private int salary;				// 월급
	private String name;			// 개인정보 : 이름
	private String address;			// 개인정보 : 주소
	private String phone;			// 개인전호 : 전화번호
	private String comment;			// 비고
	//client에서 foreign key로 설정해놓으면 필요없을까요..??
	private Vector<Client> clients; // 배정회원


	/* constructor */
	public Trainer() {
	}
	
	public Trainer(String id, String loginId, String pwd, String name, String rDate, String phone, String addr, int salary) {
		this.setID(id);
		this.setLoginId(loginId);
		this.setPwd(pwd);
		this.setName(name);
		this.setRegistDate(rDate);
		this.setPhone(phone);
		this.setAddress(addr);
		this.setSalary(salary);
	}
	/* constructor */
	
	/* set & get function */
	public String getID() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getRegistDate() {
		return registDate;
	}

	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Vector<Client> clients() {
		return clients;
	}
	
	public void setClients(Vector<Client> clients) {
		this.clients = clients;
	}
	/* set & get function */

}