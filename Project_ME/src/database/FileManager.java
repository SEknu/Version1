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

// select 메소드 필요~ get을 수정하던지 해야지..

public class FileManager {	
	static private FileManager instance = new FileManager();
	
	/* getInstance */
	static public FileManager getInstance() {
		return instance;
	}
	/* getInstance */
	
	// client 수정해주세요~
	/*****************client ********************/
	public Vector<Client> getClient(final String id) throws ClassNotFoundException, SQLException {
		Client client;
		ResultSet rs = null;
		Vector<Client> vectorClient = new Vector<Client>();
			
		rs = resultSetStatementStrategy(
				new StatementStrategy() {
			@Override
			public PreparedStatement makePreparedStatement(Connection c)
					throws SQLException {
					PreparedStatement ps;
					if(id.equals("all")) {
						ps = c.prepareStatement("select * from client");
					} else {
						ps = c.prepareStatement("select * from client where id = " + id);
					}
					return ps;
				}
			}
		);
		
		// client 수정할 부분!
		while(rs.next()) {
			client = new Client();
			client.setId(rs.getString("id"));
			client.setLoginId(rs.getString("loginId"));
			client.setPwd(rs.getString("pwd"));
			client.setRegistDate(rs.getString("regi_date"));
			client.setRegistperiod(rs.getString("regi_period"));
			client.setTerminateDate(rs.getString("terminate_date"));
			client.setName(rs.getString("name"));
			client.setAge(rs.getInt("age"));
			client.setBirthday(rs.getString("birthday"));
			client.setAddress(rs.getString("address"));
			client.setPhone(rs.getString("phone"));
			client.setHeight(rs.getInt("height"));
			client.setWeight(rs.getInt("weight"));
			client.setBodyFat(rs.getInt("bodyFat"));
			client.setBodyMuscle(rs.getInt("bodyMuscle"));
			client.setTrainer(rs.getString("trainer"));
			client.setProgram(rs.getString("program"));
			client.setNote(rs.getString("note"));
			client.setCurrentStatus(rs.getInt("status"));
			vectorClient.add(client);
		}
		if(rs != null) {	
			try { rs.close(); } catch(SQLException e) {}
		}	
		return vectorClient;
	}
	
	public void addClient(final Client client) throws ClassNotFoundException, SQLException {
		jdbcContextWithStatementStrategy(
				new StatementStrategy() {
			@Override
			public PreparedStatement makePreparedStatement(Connection c)
					throws SQLException {                     //여기 수정 바람
					PreparedStatement ps = c.prepareStatement("insert into client(id, loginId, pwd, regi_date, regi_period, terminate_date, name, age, birthday, address, phone, height, weight, bodyFat, bodyMuscle, trainer, program, note, status) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

					ps.setString(1, client.getId());
					ps.setString(2, client.getLoginId());
					ps.setString(3, client.getPwd());
					ps.setString(4, client.getRegistDate());
					ps.setString(5, client.getRegistperiod());
					ps.setString(6, client.getTerminateDate());
					ps.setString(7, client.getName());
					ps.setInt(8, client.getAge());
					ps.setString(9, client.getBirthday());
					ps.setString(10, client.getAddress());
					ps.setString(11, client.getPhone());
					ps.setInt(12, client.getHeight());
					ps.setInt(13, client.getWeight());
					ps.setInt(14, client.getBodyFat());
					ps.setInt(15, client.getBodyMuscle());
					ps.setString(16, client.getTrainer());
					ps.setString(17, client.getProgram());
					ps.setString(18, client.getNote());
					ps.setInt(19, client.isCurrentStatus());
					return ps;
				}
			}
		);
	}
	
	public void updateClient(final Client client) throws ClassNotFoundException, SQLException {
		jdbcContextWithStatementStrategy(
				new StatementStrategy() {
			@Override
			public PreparedStatement makePreparedStatement(Connection c)
					throws SQLException {
					PreparedStatement ps = c.prepareStatement("update client set loginId=?, " + "pwd=?, regi_date=?, regi_period=?, terminate_date=?, name=?, age=?, birthday=?, address=?, phone=?, height=?, weight=?, bodyFat=?, bodyMuscle=?, trainer=?, program=?, note=?, status=? where id=?");

					ps.setString(1, client.getLoginId());
					ps.setString(2, client.getPwd());
					ps.setString(3, client.getRegistDate());
					ps.setString(4, client.getRegistperiod());
					ps.setString(5, client.getTerminateDate());
					ps.setString(6, client.getName());
					ps.setInt(7, client.getAge());
					ps.setString(8, client.getBirthday());
					ps.setString(9, client.getAddress());
					ps.setString(10, client.getPhone());
					ps.setInt(11, client.getHeight());
					ps.setInt(12, client.getWeight());
					ps.setInt(13, client.getBodyFat());
					ps.setInt(14, client.getBodyMuscle());
					ps.setString(15, client.getTrainer());
					ps.setString(16, client.getProgram());
					ps.setString(17, client.getNote());
					ps.setInt(18, client.isCurrentStatus());
					ps.setString(19, client.getId());
					return ps;
				}
			}
		);
	}
	
	/*****************trainer********************/
	/*get all commodity or get commodity by searching id*/
	public Vector<Trainer> getTrainer(final String id) throws ClassNotFoundException, SQLException {
		Trainer trainer;
		ResultSet rs = null;
		Vector<Trainer> vectorTrainer = new Vector<Trainer>();
			
		rs = resultSetStatementStrategy(
				new StatementStrategy() {
			@Override
			public PreparedStatement makePreparedStatement(Connection c)
					throws SQLException {
					PreparedStatement ps;
					if(id.equals("all")) {
						ps = c.prepareStatement("select * from trainer");
					} else {
						ps = c.prepareStatement("select * from trainer where id = " + id);
					}
					return ps;
				}
			}
		);
		
		while(rs.next()) {
			trainer = new Trainer();
			trainer.setID(rs.getString("id"));
			trainer.setName(rs.getString("name"));
			trainer.setRegistDate(rs.getString("regi_date"));
			trainer.setSalary(rs.getInt("salary"));
			trainer.setAddress(rs.getString("addr"));
			trainer.setPhone(rs.getString("phone"));
			vectorTrainer.add(trainer);
		}
		if(rs != null) {	
			try { rs.close(); } catch(SQLException e) {}
		}	
		return vectorTrainer;
	}
	
	public void addTrainer(final Trainer trainer) throws ClassNotFoundException, SQLException {
		jdbcContextWithStatementStrategy(
				new StatementStrategy() {
			@Override
			public PreparedStatement makePreparedStatement(Connection c)
					throws SQLException {
					PreparedStatement ps = c.prepareStatement("insert into trainer(id, name, regi_date, salary, addr, phone) values(?,?,?,?,?,?)");
					ps.setString(1, trainer.getID());
					ps.setString(2, trainer.getName());
					ps.setString(3, trainer.getRegistDate());
					ps.setInt(4, trainer.getSalary());
					ps.setString(5, trainer.getAddress());
					ps.setString(6, trainer.getPhone());
					return ps;
				}
			}
		);
	}
	
	/*****************commodity********************/
	/*get all commodity or get commodity by searching id*/
	public Vector<Commodity> getCommodity(final String id) throws ClassNotFoundException, SQLException {
		Commodity commodity;
		ResultSet rs = null;
		Vector<Commodity> vectorCommodity = new Vector<Commodity>();
			
		rs = resultSetStatementStrategy(
				new StatementStrategy() {
			@Override
			public PreparedStatement makePreparedStatement(Connection c)
					throws SQLException {
					PreparedStatement ps;
					if(id.equals("all")) {
						ps = c.prepareStatement("select * from commodity");
					} else {
						ps = c.prepareStatement("select * from commodity where id = " + id);
					}
					return ps;
				}
			}
		);
		
		while(rs.next()) {
			commodity = new Commodity();
			commodity.setID(rs.getString("id"));
			commodity.setName(rs.getString("name"));
			commodity.setBuyDate(rs.getString("buy_date"));
			commodity.setInventory(rs.getInt("inventory"));
			commodity.setPrice(rs.getInt("price"));
			commodity.setState(rs.getInt("state"));
			commodity.setComment(rs.getString("comment"));
			vectorCommodity.add(commodity);
		}
		if(rs != null) {	
			try { rs.close(); } catch(SQLException e) {}
		}	
		return vectorCommodity;
	}
	
	/*add commodity*/
	public void addCommodity(final Commodity commodity) throws ClassNotFoundException, SQLException {
		jdbcContextWithStatementStrategy(
				new StatementStrategy() {
			@Override
			public PreparedStatement makePreparedStatement(Connection c)
					throws SQLException {
					PreparedStatement ps = c.prepareStatement("insert into commodity(id, name, buy_date, inventory, price, state, comment) values(?,?,?,?,?,?,?)");
					ps.setString(1, commodity.getID());
					ps.setString(2, commodity.getName());
					ps.setString(3, commodity.getBuyDate());
					ps.setInt(4, commodity.getInventory());
					ps.setInt(5, commodity.getPrice());
					ps.setInt(6, commodity.getState());
					ps.setString(7, commodity.getComment());
					return ps;
				}
			}
		);
	}

	/*update commodity*/
	public void updateCommodity(final Commodity commodity) throws ClassNotFoundException, SQLException {
		jdbcContextWithStatementStrategy(
				new StatementStrategy() {
			@Override
			public PreparedStatement makePreparedStatement(Connection c)
					throws SQLException {
					PreparedStatement ps = c.prepareStatement("update commodity set name=?, "
							+ "buy_date=?, inventory=?, price=?, state=?, comment=? where id=?");
					ps.setString(1, commodity.getName());
					ps.setString(2, commodity.getBuyDate());
					ps.setInt(3, commodity.getInventory());
					ps.setInt(4, commodity.getPrice());
					ps.setInt(5, commodity.getState());
					ps.setString(6, commodity.getComment());
					ps.setString(7, commodity.getID());
					return ps;
				}
			}
		);
	}
	
	/*****************program********************/
	/*program*/
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
	
	public Vector<Program> getProgram(final String id) throws ClassNotFoundException, SQLException {
		Program program;
		ResultSet rs = null;
		Vector<Program> vectorProgram = new Vector<Program>();
		
		rs = resultSetStatementStrategy(
				new StatementStrategy() {
			@Override
			public PreparedStatement makePreparedStatement(Connection c)
					throws SQLException {
				PreparedStatement ps;
					if(id.equals("all")) {
						ps = c.prepareStatement("select * from program");
					} else {
						ps = c.prepareStatement("select * from program where id = ?");
						ps.setString(1, id);
					}
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
	
	public void updateProgram(final Program program) throws ClassNotFoundException, SQLException {
		jdbcContextWithStatementStrategy(
				new StatementStrategy() {
			@Override
			public PreparedStatement makePreparedStatement(Connection c)
					throws SQLException {
					PreparedStatement ps = c.prepareStatement("update program set name=?, "
							+ "difficulty=?, part_of_body=?, comment=? where id=?");
					ps.setString(1, program.getName());
					ps.setString(2, program.getDifficulty());
					ps.setString(3, program.getPartOfBody());
					ps.setString(4, program.getComment());
					ps.setString(5, program.getID());
					return ps;
				}
			}
		);
	}
	/*****************delete********************/
	/*delete*/
	public void delete(final String id, final String table) throws ClassNotFoundException, SQLException {
		jdbcContextWithStatementStrategy(
				new StatementStrategy() {
			@Override
			public PreparedStatement makePreparedStatement(Connection c)
					throws SQLException {
					PreparedStatement ps = c.prepareStatement("delete from " + table + " where id=?");
					ps.setString(1, id);
					return ps;
				}
			}
		);
	}
			
	/* Generic select, insert, update, delete */
	
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
