package object;

import java.util.Vector;

public class Plan {
	
	private String PlanNum; 		// 플랜번호
	private String c_ID;			// 회원번호
	private String p_ID;			// 프로그램 번호
	private String hour;			// 시간
	private int tries;				// 운동횟수
	private String today_workout;	// 하루 운동량
	private String goal;			// 운동 목적
	/*
id			VARCHAR2(10),
client		VARCHAR2(10),
program		VARCHAR2(10),
time		NUMBER,
repeat		NUMBER,
day			NUMBER,
purpose		VARCHAR2(100 CHAR),
	 */
	
	/* constructor */
	public Plan() {
		
	}
	
	public Plan(String p, String c, String pID, String hour, int t, String tw, String g)
	{
		setPlanNum(p);
		setCID(c);
		setPID(pID);
		setHour(hour);
		setTries(t);
		setW(tw);
		setG(g);
	}
	
	public Plan(String p, String c, String pID)
	{
		setPlanNum(p);
		setCID(c);
		setPID(pID);
		setHour("");
		setTries(0);
		setW("");
		setG("");
		
	}
	/* constructor */
	
	public Vector<String> toStringArray()
	{
		Vector<String> result = new Vector<String>();
		result.add(getPlanNum());
		result.add(getCID());
		result.add(getPID());
		result.add(getHour());
		result.add(Integer.toString(getTries()));
		result.add(getW());
		result.add(getG());
		
		return result;
	}
	
	// set ,get 함수
	public void setPlanNum(String p)
	{
		PlanNum=p;
	}
	public String getPlanNum()
	{
		return PlanNum;
	}
	public void setCID(String c)
	{
		c_ID =c;
	}
	public String getCID()
	{
		return c_ID;
	}
	public void setPID(String s)
	{
		p_ID=s;
	}
	public String getPID()
	{
		return p_ID;
	}
	public void setHour(String h)
	{
		hour=h;
	}
	public String getHour()
	{
		return hour;
	}
	
	public void setTries(int s)
	{
		tries=s;
	}
	public int getTries()
	{
		return tries;
	}
	public void setW(String s)
	{
		today_workout=s;
	}
	public String getW()
	{
		return today_workout;
	}
	
	public void setG(String g)
	{
		goal=g;
	}
	public String getG()
	{
		return goal;
	}
} // end