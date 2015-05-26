package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import com.sun.media.jfxmedia.logging.Logger;


public class AdminAMode extends AdminAbstract {	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == clientButton) {
			contentpanel.removeAll();
			try {
				contentpanel.add(new ClientAPanel());
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				Logger.logMsg(ERROR, "addClientPanel");	
			}
			setResizable(false);
			pack();
			setVisible(true);
		}
		else if (e.getSource() == programButton) {
			contentpanel.removeAll();
			try {
				contentpanel.add(new ProgramPanel());
			} catch (ClassNotFoundException | SQLException e1) {
				Logger.logMsg(ERROR, "addProgramPanel");
			}
			setResizable(false);
			pack();
			setVisible(true);
		}
		else if (e.getSource() == commodityButton) {
			contentpanel.removeAll();
			try {
				contentpanel.add(new CommodityPanel());
			} catch (ClassNotFoundException | SQLException e1) {
				Logger.logMsg(ERROR, "addCommodityPanel");
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
				Logger.logMsg(ERROR, "addTrainerPanel");
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
