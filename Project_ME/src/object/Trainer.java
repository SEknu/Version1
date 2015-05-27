package object;

import java.util.Vector;

public class Trainer {
	
	private String id;				// Ʈ���̳� ��ȣ
	private String loginId;			// �α���ID
	private String pwd;				// �α��� PW
	private String registDate;		// �Ի���
	private int salary;				// ����
	private String name;			// �������� : �̸�
	private String address;			// �������� : �ּ�
	private String phone;			// ������ȣ : ��ȭ��ȣ
	private String comment;			// ���
	//client���� foreign key�� �����س����� �ʿ�������..??
	private Vector<Client> clients; // ����ȸ��


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