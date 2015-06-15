package gui;

import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTable;

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

	public Vector<Vector<String>> getRowTrainer(Vector<Trainer> allTrainer) throws ClassNotFoundException, SQLException {
		Vector<Vector<String>> result = new Vector<Vector<String>>();
		
		for (Trainer t : allTrainer) {
			Vector<String> v = new Vector<String>();
			
			v.add(t.getName());
			v.add(t.getPhone());
			v.add(t.getAddress());
			v.add(t.getRegistDate());
			
			result.add(v);
		}
			
		return result;
	}
	
	public Vector<Vector<String>> getRowPrograme(Vector<Program> prog) throws ClassNotFoundException, SQLException
	{
		Vector<Vector<String>> result = new Vector<Vector<String>>();
		
		for (Program p : prog) {
			Vector<String> program = new Vector<String>();
			
			program.add(p.getName());
			program.add(p.getPartOfBody());
			program.add(p.getDifficulty());
			program.add(p.getComment());
					
			result.add(program);
		}		
		return result;
	}
	
	
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
	
	public Vector<Program> getProgram() throws ClassNotFoundException, SQLException {
		Vector<Program> allProgram;
		allProgram = filemanager.getProgram("all");
		return allProgram;
	}

	public Vector<Commodity> getCommodity() throws ClassNotFoundException, SQLException {
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
	
	public Vector<Program> selectProgram(final String key, final String str) throws ClassNotFoundException, SQLException {
		Vector<Program> allProgram;
		allProgram = filemanager.selectProgram(key, str);
		return allProgram;
	}
	
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
	

	/******************* update ********************/
	public void update(Client client) throws ClassNotFoundException, SQLException {
		filemanager.updateClient(client);
	}
	
	public void update(Commodity commodity) throws ClassNotFoundException, SQLException {
		filemanager.updateCommodity(commodity);
	}
	
	public void update(Trainer trainer) throws ClassNotFoundException, SQLException {
		filemanager.updateTrainer(trainer);
	}
	
	public void update(Program program) throws ClassNotFoundException, SQLException {
		filemanager.updateProgram(program);
	}
	public void update(Member member) throws ClassNotFoundException, SQLException {
		filemanager.updateMember(member);
	}

	public void delete(final String index, final String table) throws ClassNotFoundException, SQLException {
		filemanager.delete(index, table);
	}
	 

}