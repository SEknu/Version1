package gui;

import java.awt.event.ActionEvent;
import java.sql.SQLException;

import com.sun.media.jfxmedia.logging.Logger;

public class AdminTMode extends AdminAbstract {
	private String id;

	public AdminTMode(String id) {
		// TODO Auto-generated constructor stub
		this.id = id;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == clientButton) {
			setVisible(false);
			contentpanel.removeAll();
			try {
				contentpanel.add(new ClientTPanel(id));
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			setResizable(false);
			pack();
			setVisible(true);
		} else if (e.getSource() == programButton) {
			setVisible(false);
			contentpanel.removeAll();
			try {
				contentpanel.add(new ProgramPanel());
			} catch (ClassNotFoundException | SQLException e1) {
				Logger.logMsg(ERROR, "TaddProgramPanel");
			}
			setResizable(false);
			pack();
			setVisible(true);
		} else if (e.getSource() == commodityButton) {
			setVisible(false);
			contentpanel.removeAll();

			try {
				contentpanel.add(new CommodityPanel());
			} catch (ClassNotFoundException | SQLException e1) {
				Logger.logMsg(ERROR, "TaddCommodityPanel");
			}

			setResizable(false);
			pack();
			setVisible(true);
		} else if (e.getSource() == trainerButton) {
			setVisible(false);
			contentpanel.removeAll();
			try {
				contentpanel.add(new TrainerTPanel());
			} catch (ClassNotFoundException | SQLException e1) {
				Logger.logMsg(ERROR, "TaddTrainerPanel");
			}
			setResizable(false);
			pack();
			setVisible(true);
		}

		else if (e.getSource() == passwordButton) {
			try {
				new PasswordModify(id);
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
