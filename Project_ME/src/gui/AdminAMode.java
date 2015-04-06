package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class AdminAMode extends AdminAbstract {	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == client_info) {
			panel_content.removeAll();
			try {
				panel_content.add(new ClientAPanel());
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			setResizable(false);
			pack();
			setVisible(true);
		}
		else if (e.getSource() == program) {
			panel_content.removeAll();
			try {
				panel_content.add(new ProgramPanel());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			setResizable(false);
			pack();
			setVisible(true);
		}
		else if (e.getSource() == commodity) {
			panel_content.removeAll();
			try {
				panel_content.add(new CommodityPanel());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			setResizable(false);
			pack();
			setVisible(true);
		}
		else if (e.getSource() == new_trainer) {
			panel_content.removeAll();
			try {
				panel_content.add(new TrainerAPanel());
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			setResizable(false);
			pack();
			setVisible(true);
		}
	
		else if (e.getSource() == logout) {
			dispose();
			new LogIn();
		}
	}
}
