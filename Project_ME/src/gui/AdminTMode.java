package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.media.jfxmedia.logging.Logger;

import object.Trainer;


public class AdminTMode extends AdminAbstract {

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == clientButton) {
			setVisible(false);
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
			setVisible(false);
			contentpanel.removeAll();
			try {
				contentpanel.add(new ProgramPanel());
			} catch (ClassNotFoundException|SQLException e1) {
				Logger.logMsg(ERROR, "TaddProgramPanel");
			}
			setResizable(false);
			pack();
			setVisible(true);
		}
		else if (e.getSource() == commodityButton) {
			setVisible(false);
			contentpanel.removeAll();
			
			try {
				contentpanel.add(new CommodityPanel());
			} catch (ClassNotFoundException|SQLException e1) {
				Logger.logMsg(ERROR, "TaddCommodityPanel");
			}

			setResizable(false);
			pack();
			setVisible(true);
		}
		else if (e.getSource() == trainerButton) {
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

		else if (e.getSource() == logoutButton) {
			dispose();
			new LogIn();
		}
	}
}
