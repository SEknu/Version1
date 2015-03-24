
package object;


public class Commodity {
		
	private String ID;			// 운동기구 식별자
	private String name;		// 운동기구 이름
	private String buyDate;		// 구입일
	private int inventory;		// 재고량
	private int price;			// 구입가격
	private int state;		// 운동기구의 상태 //0 불량 1 정상
	private String comment;	// comment내용
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
	//★수정부분
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