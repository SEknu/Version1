package object;

public class Member {
	
	private String id;		// id
	private String loginId;	// �α���ID
	private String pwd;		// �α��� PW
	private String position;// ������, Ʈ���̳�, ȸ������

	/* constructor */
	public Member() {
	}
	
	public Member(String id, String loginId, String pwd, String position) {
		this.setID(id);
		this.setLoginId(loginId);
		this.setPwd(pwd);
		this.setPosition(position);
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
	
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	/* set & get function */

}
