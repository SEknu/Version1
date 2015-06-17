package gui;

import java.sql.SQLException;

import object.Client;

public class ClientTPanel extends ClientAbstractPanel {
	private String name;

	public ClientTPanel(String name) throws ClassNotFoundException,
			SQLException {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public void getClient() throws ClassNotFoundException, SQLException {
		super.vectorClient = gui.selectClient("trainer", super.name);
	}
}
