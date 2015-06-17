package gui;

import java.sql.SQLException;

public class ClientAPanel extends ClientAbstractPanel {

	public ClientAPanel() throws ClassNotFoundException, SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

	public void getClient() throws ClassNotFoundException, SQLException {
		super.vectorClient = gui.getClient();
	};

}