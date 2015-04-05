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

import object.Trainer;


public class AdminMode extends adminAbstract {

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == client_info) {
			setVisible(false);
			panel_content.removeAll();
			try {
				panel_content.add(new Client_Info());
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			setResizable(false);
			pack();
			setVisible(true);
		}
		else if (e.getSource() == program) {
			setVisible(false);
			panel_content.removeAll();
			try {
				panel_content.add(new TraingProgram());
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
			setVisible(false);
			panel_content.removeAll();
			
			try {
				panel_content.add(new Commodity_Panel());
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
			setVisible(false);
			panel_content.removeAll();
			try {
				panel_content.add(new New_Trainer());
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
