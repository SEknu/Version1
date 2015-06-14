package gui;

import java.sql.SQLException;
import java.util.Vector;

import object.Client;
import object.Commodity;
import object.Member;
import object.Program;
import object.Trainer;
import database.FileManager;

public class GuiProcess {	
	FileManager filemanager = FileManager.getInstance();
	static private GuiProcess instance = new GuiProcess();
	
	/* getInstance */
	static public GuiProcess getInstance() {
		return instance;
	}
	/* getInstance */

	// getRow의 duplication을 막기위해 빼냇음~
	public Vector<Vector<String>> getClientRow(Vector<Client> allClient) throws ClassNotFoundException, SQLException {
		Vector<Vector<String>> result = new Vector<Vector<String>>();

		for (Client c : allClient) {
			Vector<String> e = new Vector<String>();
	
			e.add(c.getName());
			e.add(c.getPhone());
			e.add(c.getTrainer());
			e.add(c.getProgram());
	
			result.add(e);
		}
	
		return result;
	}

	public Vector<Vector<String>> getCommodityRow(Vector<Commodity> com)
			throws ClassNotFoundException, SQLException {
		Vector<Vector<String>> result = new Vector<Vector<String>>();

		for (Commodity c : com) {
			Vector<String> commodity = new Vector<String>();

			commodity.add(c.getName());
			commodity.add(c.getBuyDate());
			commodity.add(String.valueOf(c.getInventory()));
			commodity.add(c.getComment());
			result.add(commodity);
		}
		return result;
	}
	
	// 정보를 받아들이는 곳
	public Vector<Client> getClient() throws ClassNotFoundException, SQLException {
		Vector<Client> allClient = new Vector<Client>();
		allClient = filemanager.getClient("all");
		return allClient;
	}
	
	public Vector<Trainer> getTrainer() throws ClassNotFoundException, SQLException {
		Vector<Trainer> allTrainer;
		allTrainer = filemanager.getTrainer("all");
		return allTrainer;
	}
	
	public Vector<Program> guiProgram() throws ClassNotFoundException, SQLException {
		Vector<Program> allProgram;
		allProgram = filemanager.getProgram("all");
		return allProgram;
	}

	public Vector<Commodity> guiCommodity() throws ClassNotFoundException, SQLException {
		Vector<Commodity> allCommodity;
		allCommodity = filemanager.getCommodity("all");
		return allCommodity;
	}

	// select
	public Vector<Client> selectClient(final String key, final String str) throws ClassNotFoundException, SQLException {
		Vector<Client> allClient;
		allClient = filemanager.selectClient(key, str);
		return allClient;
	}
	
	// gui로 부터 정보를 받아 filemanager에 넘겨주어 저장하는 작업
	public void add(Client client) throws ClassNotFoundException, SQLException {
		filemanager.add(client);
	}
	
	public void add(Member member) throws ClassNotFoundException, SQLException {
		filemanager.add(member);
	}
	
	public void add(Commodity commodity) throws ClassNotFoundException, SQLException {
		filemanager.add(commodity);
	}
	
	public void add(Program program) throws ClassNotFoundException, SQLException {
		filemanager.add(program);
	}

	public void add(Trainer trainer) throws ClassNotFoundException, SQLException {
		filemanager.add(trainer);
	}
	
	// update하는 곳
	public void update(Client client) throws ClassNotFoundException, SQLException {
		filemanager.updateClient(client);
	}
	
	public void update(Commodity commodity) throws ClassNotFoundException, SQLException {
		filemanager.updateCommodity(commodity);
	}
	
	//delete 하는곳
	public void delete(final String index, final String table) throws ClassNotFoundException, SQLException {
		filemanager.delete(index, table);
	}
	 
	
}
