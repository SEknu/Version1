package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class AdminAMode extends AdminAbstract {	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == clientButton) {
			contentpanel.removeAll();
			try {
				contentpanel.add(new ClientAPanel());
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			setResizable(false);
			pack();
			setVisible(true);
		}
		else if (e.getSource() == programButton) {
			contentpanel.removeAll();
			try {
				contentpanel.add(new ProgramPanel());
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
		else if (e.getSource() == commodityButton) {
			contentpanel.removeAll();
			try {
				contentpanel.add(new CommodityPanel());
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
		else if (e.getSource() == trainerButton) {
			contentpanel.removeAll();
			try {
				contentpanel.add(new TrainerAPanel());
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			setResizable(false);
			pack();
			setVisible(true);
		}
	
		else if (e.getSource() == logoutButton) {
			dispose();
			new LogIn();
		}
	}
}
