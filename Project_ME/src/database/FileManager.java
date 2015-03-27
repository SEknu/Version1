package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import object.Client;
import object.Commodity;
import object.Plan;
import object.Program;
import object.Trainer;



public class FileManager {	
	static private FileManager instance = new FileManager();
	
	private Connection conn = null;	// DB 접속 핸들
	private Statement stmt = null;
	private String url = null;
	private String user = null;
	private String pass = null;
	
//	/* constructor */
	public FileManager() {
		try {
			Class.forName("com.mysql.jdbc.Driver");  // 1. Load the JDBC Driver
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		url = "jdbc:mysql://localhost:3306/test";
		user = "root";	// 트레이너 테이블과 유저 테이블의 id와 pw만을 select 할 수 있는 id
		pass = "me2015";	// 비밀번호
		
		try {
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/* constructor */
	
	public void disConnet() {
		try {
			conn.commit();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	/* getInstance */
	static public FileManager getInstance() {
		return instance;
	}
	/* getInstance */
	

	public void addProgram(final Program program) throws ClassNotFoundException, SQLException {
		jdbcContextWithStatementStrategy(
				new StatementStrategy() {
			@Override
			public PreparedStatement makePreparedStatement(Connection c)
					throws SQLException {
					PreparedStatement ps = c.prepareStatement("insert into program(id, name, difficulty, part_of_body, comment) values(?,?,?,?,?)");
					ps.setString(1, program.getID());
					ps.setString(2, program.getName());
					ps.setString(3, program.getDifficulty());
					ps.setString(4, program.getPartOfBody());
					ps.setString(5, program.getComment());
					return ps;
				}
			}
		);
	}
	
	public Vector<Program> getAllProgram() throws ClassNotFoundException, SQLException {
		Program program;
		ResultSet rs = null;
		Vector<Program> vectorProgram = new Vector<Program>();
		
		rs = resultSetStatementStrategy(
				new StatementStrategy() {
			@Override
			public PreparedStatement makePreparedStatement(Connection c)
					throws SQLException {
					PreparedStatement ps = c.prepareStatement("select * from program");
					return ps;
				}
			}
		);
		
		while(rs.next()) {
			program = new Program();
			program.setID(rs.getString("id"));
			program.setName(rs.getString("name"));
			program.setDifficulty(rs.getString("difficulty"));
			program.setPartOfBody(rs.getString("part_of_body"));
			program.setComment(rs.getString("comment"));
			vectorProgram.add(program);
		}
		if(rs != null) {	
			try { rs.close(); } catch(SQLException e) {}
		}	
		return vectorProgram;
	}
	
	/* Generic select, insert, update, delete */
	public Object select(String ID, String tableName) {
		try {
			String query = 	"SELECT *" +
							" FROM " + tableName +
							" WHERE id = '" + ID + "'";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if (rs.next()) {
				//System.out.println(rs.getString("id"));
				if (ID.equals(rs.getString("id"))) {
					
					if (tableName.equalsIgnoreCase("trainer")) {
						String date = null;
						Date d = rs.getDate("regi_date");
						if (d != null)
							date = d.toString();
						Trainer trainer =  new Trainer(rs.getString("id"), rs.getString("grade"), date,
								rs.getInt("attend"), rs.getInt("vacation_day"), rs.getInt("salary"),
								rs.getString("password"), rs.getString("name"), rs.getInt("age"),
								rs.getString("addr"), rs.getString("phone"));
						
						rs.close();
						stmt.close();
						return trainer;
					}
					else if (tableName.equalsIgnoreCase("client")) {
						String rDate = null, tDate = null;
						Date d = rs.getDate("regi_date");
						if (d != null)
							rDate = d.toString();
						d = rs.getDate("terminate_date");
						if (d != null)
							tDate = d.toString();
						Client client = new Client(rs.getString("id"), rs.getString("grade"), rDate,
								rs.getInt("attend"), tDate, rs.getBoolean("state"),
								rs.getInt("height"), rs.getInt("weight"), rs.getInt("fat"), rs.getInt("muscle"),
								rs.getString("password"), rs.getString("note"), rs.getString("name"), rs.getInt("age"),
								rs.getString("addr"), rs.getString("phone"), rs.getString("trainer"));
						
						rs.close();
						stmt.close();
						return client;
					}
					else if (tableName.equalsIgnoreCase("commodity")) {
						String bDate = null, fCheck = null;
						Date d = rs.getDate("buy_date");
						if (d != null)
							bDate = d.toString();
						d = rs.getDate("final_check");
						if (d != null)
							fCheck = d.toString();
						Commodity commodity = new Commodity(rs.getString("id"), rs.getString("name"), rs.getString("date"),
								rs.getInt("inventory"),
								rs.getInt("price"), rs.getInt("state"), rs.getString("comment"));
						
						rs.close();
						stmt.close();
						return commodity;
					}

					else if (tableName.equalsIgnoreCase("plan")) {
						Plan plan = new Plan(rs.getString("id"), rs.getString("client"), rs.getString("program"),
								rs.getString("time"), rs.getInt("repeat"), rs.getString("day"), rs.getString("purpose"));
						
						rs.close();
						stmt.close();
						return plan;
					}
				}
			}
			
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; // 없음
	}
	
	public int insert(Object obj, String tableName) {
		int result = -1;
		
		try {
			stmt = conn.createStatement();
		
			String sql = "INSERT INTO " + tableName;
			
			if (tableName.equalsIgnoreCase("trainer")) {
				Trainer tra = (Trainer) obj;
				sql += 	" (id, regi_date, name, age, addr, phone) VALUES ('" +
						tra.getID() + "', '" +
						tra.getRegistDate() + "', '" +
						tra.getName() + "', " +
						tra.getAge() + ", '" +
						tra.getAddress() + "', '" +
						tra.getPhone() + "')";
						
			}
			else if (tableName.equalsIgnoreCase("client")) {
				Client clt = (Client) obj;				
				sql += 	" (id, grade, regi_date, attend, terminate_date, state, " +
						"height, weight, fat, muscle, name, age, addr, phone, " +
						"password, note, purpose, week, time" +
						") VALUES ('" + 
						clt.getID() + "', '" +
						clt.getGrade() + "', '" +
						clt.getRegistDate() + "', " +
						clt.getAttendance() + ", '" +
						clt.getTerminateDate() + "', '" +
						(clt.getCurrentStatus() == true ? '1' : '0') + "', " +
						clt.getHeight() + ", " +
						clt.getWeight() + ", " +
						clt.getBodyFat() + ", " +
						clt.getBodyMuscle() + ", '" +
						clt.getName() + "', " +
						clt.getAge() + ", '" +
						clt.getAddress() + "', '" +
						clt.getPhone() + "', '" +
						clt.getPW() + "', '" +
						clt.getNote() + "', '" +
						clt.getPurpose() + "', " +
						clt.getWeek() + ", " +
						clt.getTime() + ")";
			}
			else if (tableName.equalsIgnoreCase("commodity")) {
				Commodity c = (Commodity) obj;
				sql += 	" (id, name, buy_date, inventory, price, state" +
						") VALUES ('" +
						c.getID() + "', '" +
						c.getName() + "', '" +
						c.getBuyDate() + "', " +
						c.getInventory() + ", " +
						c.getPrice() + ", '" +
						c.getState() + "')";
			}

			else if (tableName.equalsIgnoreCase("plan")) {
				obj = (Plan) obj;
			}
			
			result = stmt.executeUpdate(sql);

			conn.commit();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int update(Object obj, String tableName) {
		int result = -1;
		try {
			stmt = conn.createStatement();
			
			String sql = "UPDATE " + tableName;
			String id = "";
			
			if (tableName.equalsIgnoreCase("trainer")) {
				Trainer trainer = (Trainer)obj;
				sql += " SET " + trainer;
				id = trainer.getID();
			}
			else if (tableName.equalsIgnoreCase("client")) {
				Client client = (Client)obj;
				sql += 	" SET" +
						" attend = " + client.getAttendance() +
						", trainer = '" + client.getTrainer() + "'";
				id = client.getID();
			}
			else if (tableName.equalsIgnoreCase("commodity")) {
				Commodity commodity = (Commodity)obj;
				sql += " SET name = '" + commodity.getName();
				sql += "' , buy_date = '" + commodity.getBuyDate();
				sql += "' , inventory = '" + commodity.getInventory();
				sql += "' , price = '" + commodity.getPrice();
				sql += "' , state = '" + commodity.getState();
				sql += "' , comment = '" + commodity.getComment() +"' ";
				id = commodity.getID();
			}
			else if (tableName.equalsIgnoreCase("program")) {
				Program program = (Program)obj;
				sql += " SET " + program;
				id = program.getID();
			}
			else if (tableName.equalsIgnoreCase("plan")) {
				Plan plan = (Plan)obj;
				sql += " SET " + plan;
				id = plan.getPlanNum();
			}
			
			sql +=  " WHERE id = " + id;
			
			result = stmt.executeUpdate(sql);
	
			conn.commit();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int delete(Object obj, String tableName) {
		int result = -1;
		try {
			stmt = conn.createStatement();
			
			String id = "";
			if (tableName.equalsIgnoreCase("trainer")) {
				id = ((Trainer)obj).getID();
			}
			else if (tableName.equalsIgnoreCase("client")) {
				id = ((Client)obj).getID();
			}
			else if (tableName.equalsIgnoreCase("commodity")) {
				id = ((Commodity)obj).getID();
			}
			else if (tableName.equalsIgnoreCase("program")) {
				id = ((Program)obj).getID();
			}
			else if (tableName.equalsIgnoreCase("plan")) {
				id = ((Plan)obj).getPlanNum();
			}
			
			String sql = "DELETE FROM " + tableName +
						 " WHERE id = " + id;
			
			result = stmt.executeUpdate(sql);
			
			conn.commit();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	/* Generic select, insert, update, delete */
	
	/* another SQL */
	public int selectCount(String colName, Object con, String tableName) {
		int count = 0;
		try {
			stmt = conn.createStatement();
			String query = 	"SELECT count(*)" + 
							" FROM " + tableName;
			if (colName == null){
			}
			else if (tableName.equalsIgnoreCase("trainer")) {
			}
			else if (tableName.equalsIgnoreCase("client")) {
				if (colName.equalsIgnoreCase("week") || colName.equalsIgnoreCase("time")) {
					int condition = (Integer) con;
					query += " WHERE " + colName + "=" + condition;
				}
			}
			else if (tableName.equalsIgnoreCase("commodity")) {
			}
			else if (tableName.equalsIgnoreCase("program")) {
			}
			else if (tableName.equalsIgnoreCase("plan")) {
			}
			
			ResultSet rs = stmt.executeQuery(query);
			
			if (rs.next()) {
				count = rs.getInt(1);
				rs.close();
				stmt.close();
				
				return count;
			}
			
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count; // 없음
	}
	
	public Vector<Client> allClient() {
		Vector<Client> result = new Vector<Client>();
		try {
			String query = 	"SELECT *" +
							" FROM client";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				String rDate = null, tDate = null;
				Date d = rs.getDate("regi_date");
				if (d != null)
					rDate = d.toString();
				d = rs.getDate("terminate_date");
				if (d != null)
					tDate = d.toString();
				
				Client client = new Client(rs.getString("id"), rs.getString("grade"), rDate,
						rs.getInt("attend"), tDate, rs.getBoolean("state"),
						rs.getInt("height"), rs.getInt("weight"), rs.getInt("fat"), rs.getInt("muscle"),
						rs.getString("password"), rs.getString("note"), rs.getString("name"), rs.getInt("age"),
						rs.getString("addr"), rs.getString("phone"), rs.getString("trainer"));
				
				result.add(client);
			}
			
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public Vector<Commodity> allCommodity() {
		Vector<Commodity> result = new Vector<Commodity>();
		try {
			String query = 	"SELECT *" +
							" FROM commodity";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				String bDate = null, fcd = null;
				Date d = rs.getDate("buy_date");
				if (d != null)
					bDate = d.toString();
				Commodity c = new Commodity(
						rs.getString("id"), rs.getString("name"), rs.getString("buy_date"),
						rs.getInt("inventory"),
						rs.getInt("price"), rs.getInt("state"), rs.getString("comment"));
				
				result.add(c);
			}
			
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public Vector<Trainer> allTrainer() {
		Vector<Trainer> result = new Vector<Trainer>();
		try {
			String query = 	"SELECT *" +
							" FROM trainer";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				String rDate = null;
				Date d = rs.getDate("regi_date");
				if (d != null){
					rDate = d.toString();
				}
				Trainer c = new Trainer(
						rs.getString("id"), rs.getString("grade"), rDate, rs.getInt("attend"),
						rs.getInt("vacation_day"), rs.getInt("salary"), rs.getString("password"),
						rs.getString("name"), rs.getInt("age"), rs.getString("addr"), rs.getString("phone")
						);
								
				result.add(c);
			}
			
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	/* another SQL */
	
	
	public ArrayList<Client> getClientByName(String name)
	{
		ArrayList<Client> result = new ArrayList<Client>();
		
		/*
		for(int i=0; i<Client_List.size(); i++)
		{
			if(Client_List.get(i).getName().indexOf(name) != -1)
				result.add(Client_List.get(i));
		}
		*/
		return result;
	}
	
	//executeupdate 실행
	public void jdbcContextWithStatementStrategy(StatementStrategy stmt) throws SQLException, ClassNotFoundException {
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			c = new ConnectionMaker().makeConnection();			
			ps = stmt.makePreparedStatement(c);			
			ps.executeUpdate();					
		} catch (SQLException e) {
			throw e;
		} finally {
			if(ps != null) {
				try { ps.close(); } catch(SQLException e) {}
			}
			
			if(c!=null) {
				try { c.close(); } catch(SQLException e) {}
			}
		}
	}
	
	//executequery실행
	public ResultSet resultSetStatementStrategy(StatementStrategy stmt) throws SQLException, ClassNotFoundException {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			c = new ConnectionMaker().makeConnection();			
			ps = stmt.makePreparedStatement(c);			
			rs = ps.executeQuery();		
			return rs;
		} catch (SQLException e) {
			throw e;
		} finally {
//			if(rs != null) {	
//				try { rs.close(); } catch(SQLException e) {}
//			}
////			if(ps != null) {
////				try { ps.close(); } catch(SQLException e) {}
////			}
//			
//			if(c!=null) {
//				try { c.close(); } catch(SQLException e) {}
//			} // connection 어떻게 닫아야 하쥐 ㅠㅠㅠ
		}	
	}

	
} // end class
