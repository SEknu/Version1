package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import object.Client;
import object.Commodity;
import object.Member;
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
			client = getClientInfo(rs);
			vectorClient.add(client);
		}
		if(rs != null) {	
			try { rs.close(); } catch(SQLException e) {}
		}	
		return vectorClient;
	}

	public Vector<Client> selectClient(final String col, final String str) throws ClassNotFoundException, SQLException {
		Client client;
		ResultSet rs = null;
		Vector<Client> vectorClient = new Vector<Client>();
			
		rs = resultSetStatementStrategy(
				new StatementStrategy() {
			@Override
			public PreparedStatement makePreparedStatement(Connection c)
					throws SQLException {
					PreparedStatement ps = c.prepareStatement("select * from client where "+ col + "='" + str + "'");
					return ps;
				}
			}
		);
		
		// client 수정할 부분!
		while(rs.next()) {
			client = getClientInfo(rs);
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
					PreparedStatement ps = c.prepareStatement("insert into client(loginId, pwd, regi_date, regi_period, terminate_date, name, age, birthday, address, phone, height, weight, bodyFat, bodyMuscle, trainer, program, comment, status, id) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

					return setClientInfo(client, ps);
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
					PreparedStatement ps = c.prepareStatement("update client set loginId=?, " 
					+ "pwd=?, regi_date=?, regi_period=?, terminate_date=?, name=?, age=?, birthday=?, address=?, phone=?, height=?, weight=?, bodyFat=?, bodyMuscle=?, trainer=?, program=?, comment=?, status=? where id=?");

					return setClientInfo(client, ps);
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
			trainer = getTrainerInfo(rs);
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
					PreparedStatement ps = c.prepareStatement("insert into trainer(loginId, pwd, name, regi_date, salary, addr, phone, id) values(?,?,?,?,?,?,?,?)");
					return setTrainerInfo(trainer, ps);
				}
			}
		);
	}
	
	public Vector<Trainer> selectTrainer(final String col,final String str) throws SQLException, ClassNotFoundException {
		Trainer trainer;
		ResultSet rs = null;
		Vector<Trainer> vectorTrainer = new Vector<Trainer>();
			
		rs = resultSetStatementStrategy(
				new StatementStrategy() {
			@Override
			public PreparedStatement makePreparedStatement(Connection c)
					throws SQLException {
					PreparedStatement ps = c.prepareStatement("select * from trainer where "+ col + "='" + str + "'");
					return ps;
				}
			}
		);
		
		// client 수정할 부분!
		while(rs.next()) {
			trainer = getTrainerInfo(rs);
			vectorTrainer.add(trainer);
		}
		if(rs != null) {	
			try { rs.close(); } catch(SQLException e) {}
		}	
		return vectorTrainer;
	}
	

	/*****************Member********************/
	/*get all commodity or get commodity by searching id*/
	public Vector<Member> getMember(final String id) throws ClassNotFoundException, SQLException {
		Member member;
		ResultSet rs = null;
		Vector<Member> vectorMember = new Vector<Member>();
			
		rs = resultSetStatementStrategy(
				new StatementStrategy() {
			@Override
			public PreparedStatement makePreparedStatement(Connection c)
					throws SQLException {
					PreparedStatement ps;
					if(id.equals("all")) {
						ps = c.prepareStatement("select * from member");
					} else {
						ps = c.prepareStatement("select * from member where id = " + id);
					}
					return ps;
				}
			}
		);
		
		while(rs.next()) {
			member = getMemberInfo(rs);
			vectorMember.add(member);
		}
		if(rs != null) {	
			try { rs.close(); } catch(SQLException e) {}
		}	
		return vectorMember;
	}
	
	public void addMember(final Member member) throws ClassNotFoundException, SQLException {
		jdbcContextWithStatementStrategy(
				new StatementStrategy() {
			@Override
			public PreparedStatement makePreparedStatement(Connection c)
					throws SQLException {
					PreparedStatement ps = c.prepareStatement("insert into member(loginId, pwd, position, id) values(?,?,?,?)");
					return setMemberInfo(member, ps);
				}
			}
		);
	}
	
	public Vector<Member> selectMember(final String col,final String str) throws SQLException, ClassNotFoundException {
		Member member;
		ResultSet rs = null;
		Vector<Member> vectorMember = new Vector<Member>();
			
		rs = resultSetStatementStrategy(
				new StatementStrategy() {
			@Override
			public PreparedStatement makePreparedStatement(Connection c)
					throws SQLException {
					PreparedStatement ps = c.prepareStatement("select * from member where "+ col + "='" + str + "'");
					return ps;
				}
			}
		);
		
		// 수정할 부분!
		while(rs.next()) {
			member = getMemberInfo(rs);
			vectorMember.add(member);
		}
		if(rs != null) {	
			try { rs.close(); } catch(SQLException e) {}
		}	
		return vectorMember;
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
			commodity = getCommodityInfo(rs);
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
					PreparedStatement ps = c.prepareStatement("insert into commodity(name, buy_date, inventory, price, state, comment, id) values(?,?,?,?,?,?,?)");
					return setCommodityInfo(commodity, ps);
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
					return setCommodityInfo(commodity, ps);
				}
			}
		);
	}
	
	public Vector<Commodity> selectCommodity(final String col, final String str) throws SQLException, ClassNotFoundException {
		Commodity commodity;
		ResultSet rs = null;
		Vector<Commodity> vectorCommodity = new Vector<Commodity>();
		
		rs = resultSetStatementStrategy(
				new StatementStrategy() {
			@Override
			public PreparedStatement makePreparedStatement(Connection c)
					throws SQLException {
				PreparedStatement ps = c.prepareStatement("select * from commodity where "+ col + "='" + str + "'");
					return ps;
				}
			}
		);
		
		while(rs.next()) {
			commodity = getCommodityInfo(rs);
			vectorCommodity.add(commodity);
		}
		if(rs != null) {	
			try { rs.close(); } catch(SQLException e) {}
		}	
		return vectorCommodity;
	}
	
	/*****************program********************/
	/*program*/
	public void addProgram(final Program program) throws ClassNotFoundException, SQLException {
		jdbcContextWithStatementStrategy(
				new StatementStrategy() {
			@Override
			public PreparedStatement makePreparedStatement(Connection c)
					throws SQLException {
					PreparedStatement ps = c.prepareStatement("insert into program(name, difficulty, part_of_body, comment, id) values(?,?,?,?,?)");
					return setProgramInfo(program, ps);
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
			program = getProgramInfo(rs);
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
					return setProgramInfo(program, ps);
				}
			}
		);
	}
	
	public Vector<Program> selectProgram(final String col, final String str) throws ClassNotFoundException, SQLException {
		Program program;
		ResultSet rs = null;
		Vector<Program> vectorProgram = new Vector<Program>();
		
		rs = resultSetStatementStrategy(
				new StatementStrategy() {
			@Override
			public PreparedStatement makePreparedStatement(Connection c)
					throws SQLException {
				PreparedStatement ps = c.prepareStatement("select * from program where "+ col + "='" + str + "'");
					return ps;
				}
			}
		);
		
		while(rs.next()) {
			program = getProgramInfo(rs);
			vectorProgram.add(program);
		}
		if(rs != null) {	
			try { rs.close(); } catch(SQLException e) {}
		}	
		return vectorProgram;
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
	
	private Client getClientInfo(ResultSet rs) throws SQLException {
		Client client;
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
		client.setComment(rs.getString("comment"));
		client.setCurrentStatus(rs.getInt("status"));
		return client;
	}
	private PreparedStatement setClientInfo(final Client client,
			PreparedStatement ps) throws SQLException {
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
		ps.setString(17, client.getComment());
		ps.setInt(18, client.isCurrentStatus());
		ps.setString(19, client.getId());
		return ps;
	}

	private Trainer getTrainerInfo(ResultSet rs)
			throws SQLException {
		Trainer trainer;
		trainer = new Trainer();
		trainer.setID(rs.getString("id"));
		trainer.setLoginId(rs.getString("loginId"));
		trainer.setPwd(rs.getString("pwd"));
		trainer.setName(rs.getString("name"));
		trainer.setRegistDate(rs.getString("regi_date"));
		trainer.setSalary(rs.getInt("salary"));
		trainer.setAddress(rs.getString("addr"));
		trainer.setPhone(rs.getString("phone"));
		return trainer;
	}
	private PreparedStatement setTrainerInfo(final Trainer trainer,
			PreparedStatement ps) throws SQLException {
		ps.setString(1, trainer.getLoginId());
		ps.setString(2, trainer.getPwd());
		ps.setString(3, trainer.getName());
		ps.setString(4, trainer.getRegistDate());
		ps.setInt(5, trainer.getSalary());
		ps.setString(6, trainer.getAddress());
		ps.setString(7, trainer.getPhone());
		ps.setString(8, trainer.getID());
		return ps;
	}
	
	private Member getMemberInfo(ResultSet rs)
			throws SQLException {
		Member member;
		member = new Member();
		member.setID(rs.getString("id"));
		member.setLoginId(rs.getString("loginId"));
		member.setPwd(rs.getString("pwd"));
		member.setPosition(rs.getString("position"));
		return member;
	}
	private PreparedStatement setMemberInfo(final Member member,
			PreparedStatement ps) throws SQLException {
		ps.setString(1, member.getLoginId());
		ps.setString(2, member.getPwd());
		ps.setString(3, member.getPosition());
		ps.setString(4, member.getID());
		return ps;
	}
	
	private Commodity getCommodityInfo(ResultSet rs) throws SQLException {
		Commodity commodity;
		commodity = new Commodity();
		commodity.setID(rs.getString("id"));
		commodity.setName(rs.getString("name"));
		commodity.setBuyDate(rs.getString("buy_date"));
		commodity.setInventory(rs.getInt("inventory"));
		commodity.setPrice(rs.getInt("price"));
		commodity.setState(rs.getInt("state"));
		commodity.setComment(rs.getString("comment"));
		return commodity;
	}	
	private PreparedStatement setCommodityInfo(final Commodity commodity,
			PreparedStatement ps) throws SQLException {
		ps.setString(1, commodity.getName());
		ps.setString(2, commodity.getBuyDate());
		ps.setInt(3, commodity.getInventory());
		ps.setInt(4, commodity.getPrice());
		ps.setInt(5, commodity.getState());
		ps.setString(6, commodity.getComment());
		ps.setString(7, commodity.getID());
		return ps;
	}
	
	private PreparedStatement setProgramInfo(final Program program,
			PreparedStatement ps) throws SQLException {
		ps.setString(1, program.getName());
		ps.setString(2, program.getDifficulty());
		ps.setString(3, program.getPartOfBody());
		ps.setString(4, program.getComment());
		ps.setString(5, program.getID());
		return ps;
	}	
	private Program getProgramInfo(ResultSet rs) throws SQLException {
		Program program;
		program = new Program();
		program.setID(rs.getString("id"));
		program.setName(rs.getString("name"));
		program.setDifficulty(rs.getString("difficulty"));
		program.setPartOfBody(rs.getString("part_of_body"));
		program.setComment(rs.getString("comment"));
		return program;
	}
	
} // end class
