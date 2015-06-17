package object;

public class Commodity {

	private String ID; // ��ⱸ �ĺ���
	private String name; // ��ⱸ �̸�
	private String buyDate; // ������
	private int inventory; // ���
	private int price; // ���԰���
	private int state; // ��ⱸ�� ���� //0 �ҷ� 1 ����

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	private String comment; // comment����

	/*
	 * id VARCHAR2(10), name VARCHAR2(10), buy_date DATE, inventory INT, price
	 * INT, state INT, comment VARCHAR(20),
	 */

	/* constructor */
	public Commodity() {

	}

	// �ڼ����κ�
	public Commodity(String id, String name, String bd, int inv, int price,
			int state, String comment) {
		this.setID(id);
		this.setName(name);
		this.setBuyDate(bd);
		this.setInventory(inv);
		this.setPrice(price);
		this.setState(state);
		this.setComment(comment);
	}
	/* constructor */

	/* set & get function */
}