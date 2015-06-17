package object;

public class Program {

	private String ID;
	private String name;
	private String difficulty;
	private String partOfBody;
	private String comment;

	/*
	 * id VARCHAR2(10), name VARCHAR(10), difficulty CHAR(6), part_of_body
	 * VARCHAR(20), comment VARCHAR2(200), 아직 밑의 제약사항은 없음 ㅠㅠ PRIMARY KEY (id),
	 * FOREIGN KEY (commodity) REFERENCES commodity (id)
	 */
	@Override
	public String toString() {
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

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String getPartOfBody() {
		return partOfBody;
	}

	public void setPartOfBody(String partOfBody) {
		this.partOfBody = partOfBody;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
