package object;

public class Client {
	private String id = null; // DB�ĺ���
	private String loginId = null; // �α���ID
	private String pwd = null; // �α��� PW
	private String registDate = null; // ��ϳ�¥
	private String registperiod = null; // ��ϱⰣ
	private String terminateDate = null; // ��������
	private String name = null; // �������� : �̸�
	private int age = 0; // �������� : ����
	private String birthday = null; // �������� : �������
	private String address = null; // �������� : �ּ�
	private String phone = null; // ������ȣ : ��ȭ��ȣ
	private int height = 0; // ��ü���� : Ű
	private int weight = 0; // ��ü���� : ������
	private int bodyFat = 0; // ��ü���� : ü����
	private int bodyMuscle = 0; // ��ü���� : ������
	private String trainer = null; // ���� Ʈ���̳�
	private String program = null; // ���� ���α׷�
	private String comment = null; // Ư�̻���
	private int currentStatus = 0; // ���� ��� ����  0 - �̵�� , 1 - ���

	/*
	 * id VARCHAR2(10), loginId VARCHAR2(10), pwd VARCHAR2(10), regi_date DATE,
	 * regi_period DATE, terminate_date DATE, name VARCHAR2(10), age NUMBER,
	 * birthday DATE, address VARCHAR2(40 CHAR), phone CHAR(12), height NUMBER,
	 * weight NUMBER, bodyFat NUMBER, bodyMuscle NUMBER, trainer VARCHAR2(10),
	 * program VARCHAR2(10), note VARCHAR2(200 CHAR), status CHAR(1)
	 */

	/* constructor */
	public Client() {

	}

	public Client(String id, String loginId, String pwd, String registDate,
			String registperiod, String terminateDate, String name, int age,
			String birthday, String address, String phone, int height,
			int weight, int bodyFat, int bodyMuscle, String trainer,
			String program, String comment, int currentStatus) {
		super();
		this.setId(id);
		this.setLoginId(loginId);
		this.setPwd(pwd);
		this.setRegistDate(registDate);
		this.setRegistperiod(registperiod);
		this.setTerminateDate(terminateDate);
		this.setName(name);
		this.setAge(age);
		this.setBirthday(birthday);
		this.setAddress(address);
		this.setPhone(phone);
		this.setHeight(height);
		this.setWeight(weight);
		this.setBodyFat(bodyFat);
		this.setBodyMuscle(bodyMuscle);
		this.setTrainer(trainer);
		this.setProgram(program);
		this.setComment(comment);
		this.setCurrentStatus(currentStatus);
	}

	public Client(String id, String loginId, String pwd, String registDate,
			String registperiod, String terminateDate, String name, int age,
			String birthday, String address, String phone, int height,
			int weight, int bodyFat, int bodyMuscle, String comment) {
		this(id, loginId, pwd, registDate, registperiod, terminateDate, name,
				age, birthday, address, phone, height, weight, bodyFat,
				bodyMuscle, null, null, comment, 1);

	}

	/* constructor */

	/*
	 * public Vector<String> toStringVector() { Vector<String> result = new
	 * Vector<String>();
	 * 
	 * result.add(Integer.toString(getID())); result.add(getGrade());
	 * result.add(getRegistDate().toString());
	 * result.add(Integer.toString(getAttendance()));
	 * result.add(getTerminateDate().toString());
	 * result.add(Boolean.toString(geCurrentStatus()));
	 * result.add(Integer.toString(getHeight()));
	 * result.add(Integer.toString(getWeight()));
	 * result.add(Integer.toString(getBodyFat()));
	 * result.add(Integer.toString(getBodyMuscle())); result.add(getPW());
	 * result.add(getNote()); result.add(getName());
	 * result.add(Integer.toString(getAge())); result.add(getAddress());
	 * result.add(getPhone()); result.add(Integer.toString(getTrainer()));
	 * 
	 * return result; }
	 */
	/* constructor */

	/* set & get function */

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getRegistperiod() {
		return registperiod;
	}

	public void setRegistperiod(String registperiod) {
		this.registperiod = registperiod;
	}

	public String getTerminateDate() {
		return terminateDate;
	}

	public void setTerminateDate(String terminateDate) {
		this.terminateDate = terminateDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
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

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getBodyFat() {
		return bodyFat;
	}

	public void setBodyFat(int bodyFat) {
		this.bodyFat = bodyFat;
	}

	public int getBodyMuscle() {
		return bodyMuscle;
	}

	public void setBodyMuscle(int bodyMuscle) {
		this.bodyMuscle = bodyMuscle;
	}

	public String getTrainer() {
		return trainer;
	}

	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int isCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(int currentStatus) {
		this.currentStatus = currentStatus;
	}

	/* set & get function */

	@Override
	public String toString() {
		String out = this.getId() + " " + this.getLoginId() + " "
				+ this.getPwd() + " " + this.getRegistDate() + " "
				+ this.getRegistperiod() + " " + this.getTerminateDate() + " "
				+ this.getName() + " " + this.getAge() + " "
				+ this.getBirthday() + " " + this.getAddress() + " "
				+ this.getPhone() + " " + this.getHeight() + " "
				+ this.getWeight() + " " + this.getBodyFat() + " "
				+ this.getBodyMuscle() + " " + this.getTrainer() + " "
				+ this.getProgram() + " " + this.getComment() + " "
				+ this.isCurrentStatus();

		return out;
	}
}
