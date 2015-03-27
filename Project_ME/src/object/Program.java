package object;

public class Program {

	private String ID;
	private String name;
	private String difficulty;
	private String partOfBody;
	private String comment;
	/*
id				VARCHAR2(10),
name			VARCHAR(10),
difficulty		CHAR(6),
part_of_body	VARCHAR(20),
comment		VARCHAR2(200),
아직 밑의 제약사항은 없음 ㅠㅠ
PRIMARY KEY (id),
FOREIGN KEY (commodity) REFERENCES commodity (id)
	 */
	@Override
	public String toString()
	{
		return name;
	}
	
	/* constructor */
	public Program() {
		
	}
	
	public Program(String id, String name, String d, String p, String c) {
		this.setID(id);
		this.setName(name);
		this.setDifficulty(d);
		this.setPartOfBody(p);
		this.setComment(c);
	}
	
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
	
	public void setDifficulty(String d)
	{
		this.difficulty = d;
	}
	public String getDifficulty()
	{
		return this.difficulty;
	}
	
	public void setPartOfBody(String p)
	{
		this.partOfBody = p;
	}
	public String getPartOfBody()
	{
		return this.partOfBody;
	}
	
	public void setComment(String c)
	{
		this.comment = c;
	}
	public String getComment()
	{
		return this.comment;
	}
	/* set & get function */
}
