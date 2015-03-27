
package object;


public class Commodity {
		
	private String ID;			// ��ⱸ �ĺ���
	private String name;		// ��ⱸ �̸�
	private String buyDate;		// ������
	private int inventory;		// ���
	private int price;			// ���԰���
	private int state;		// ��ⱸ�� ���� //0 �ҷ� 1 ����
	private String comment;	// comment����
	/*
id			VARCHAR2(10),
name		VARCHAR2(10),
buy_date	DATE,
inventory	INT,
price		INT,
state		INT,
comment		VARCHAR(20),
	 */
	
	/* constructor */
	public Commodity() {
		
	}
	//�ڼ����κ�
	public Commodity(String id, String name, String bd, int inv,
					int price, int state, String comment) {
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
	public void setID(String id)
	{
		this.ID = id;
	}
	public String getID()
	{
		return this.ID;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return this.name;
	}
	
	public void setBuyDate(String bd)
	{
		this.buyDate = bd;
	}
	public String getBuyDate()
	{
		return this.buyDate;
	}
	
	public void setInventory(int inv)
	{
		this.inventory = inv;
	}
	public int getInventory()
	{
		return this.inventory;
	}
	
	public void setPrice(int price)
	{
		this.price = price;
	}
	public int getPrice()
	{
		return this.price;
	}
	
	public void setState(int state)
	{
		this.state = state;
	}
	public int getState()
	{
		return this.state;
	}
	
	public void setComment(String comment)
	{
		this.comment = comment;
	}
	public String getComment()
	{
		return this.comment;
	}
	public String getPurchase() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/* set & get function */
}