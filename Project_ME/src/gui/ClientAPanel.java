package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import object.Client;

import com.sun.media.jfxmedia.logging.Logger;

import database.FileManager;

public class ClientAPanel extends JPanel implements ActionListener {

	JTable jtable;
	JScrollPane scroll;
	FileManager filemanager;
	Vector<Client> allClient;
	Vector<Vector<String>> row = new Vector<Vector<String>>();
	Vector<String> col = new Vector<String>();

	String[] colArray = { "이름", "연락처", "담당트레이너", "배정프로그램" };

	JButton button_add = new JButton("추가");
	JButton button_Remove = new JButton("삭제");
	JButton button_Search = new JButton("검색");
	JButton button_Detail = new JButton("상세보기");
	JTextField tf_Name = new JTextField(10);

	public ClientAPanel() throws ClassNotFoundException, SQLException {
		filemanager = FileManager.getInstance();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		button_add.addActionListener(this);
		button_Remove.addActionListener(this);
		button_Search.addActionListener(this);
		button_Detail.addActionListener(this);

		for (int i = 0; i < colArray.length; i++) {
			col.add(colArray[i]);
		}

		row = getRow();
		jtable = new JTable(row, col);
		scroll = new JScrollPane(jtable);

		add(scroll);
		panel.add(tf_Name);
		panel.add(button_Search);
		panel.add(button_add);
		panel.add(button_Remove);
		panel.add(button_Detail);
		add(panel);

		setSize(550, 550);
		setVisible(true);
	}

	public void patch(Vector<Vector<String>> newInfo) {
		row.removeAllElements();
		row.addAll(newInfo);
		jtable.updateUI();
	}

	public Vector<Vector<String>> getRow() throws ClassNotFoundException,
	SQLException {
		Vector<Vector<String>> result = new Vector<Vector<String>>();

		allClient = filemanager.getClient("all");
		for (Client c : this.allClient) {
			Vector<String> e = new Vector<String>();

			e.add(c.getName());
			e.add(c.getPhone());
			e.add(c.getTrainer());
			e.add(c.getProgram());

			result.add(e);
		}

		return result;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button_add) {
			new ClientRegister();
		}else if (e.getSource() == button_Search) {
			Vector<Vector<String>> result = new Vector<Vector<String>>();

			String name = this.tf_Name.getText();

			if (name != null && name.length() > 0) {
				for (Client c : this.allClient) {
					Vector<String> a = new Vector<String>();

					if (c.getName().equals(name)) {
						a.add(c.getName());
						a.add(c.getPhone());
						a.add(c.getTrainer());
						a.add(c.getProgram());

						result.add(a);
					}
				}
			}
			patch(result);
		} else if (e.getSource() == button_Remove) {
			int index = jtable.getSelectedRow();

			if (index != -1) {
				try {
					filemanager.delete(this.allClient.get(index).getId(),
							"client");
				} catch (ClassNotFoundException | SQLException e1) {
					Logger.logMsg(ERROR, "cannotDeleteClient");
				}
				try {
					this.allClient = filemanager.getClient("all");
				} catch (ClassNotFoundException | SQLException e1) {
					Logger.logMsg(ERROR, "cannotGetClient");
				}
			}
		//} else if (e.getSource() == Button_Assign) {
			// Assign_Dialog<Trainer> ad = new
			// Assign_Dialog<Trainer>(FileManager.Trainer_List);
			//
			// if(ad.selected != null)
			// {
			// FileManager.Client_List.get(jtable.getSelectedRow()).setTrainer(ad.selected.getID());
			// JOptionPane.showMessageDialog(null, "배정성공");
			// }
		//} else if (e.getSource() == Button_Program_Assign) {
			// Assign_Dialog_P<Program> ad = new
			// Assign_Dialog_P<Program>(FileManager.Program_List);
			//
			// if(ad.selected != null)
			// {
			// FileManager.Client_List.get(jtable.getSelectedRow()).getProgram().add(ad.selected);
			// JOptionPane.showMessageDialog(null, "배정성공");
			// }
		} else if (e.getSource() == button_Detail) {
			int index = jtable.getSelectedRow();
			if (index != -1) {
				// DB에서 맞는 정보 불러와서 보여주기.★수정
				new ClientDetail(this.allClient.get(index));
			}
		}
		try {
			patch(getRow());
		} catch (ClassNotFoundException|SQLException e1) {
			Logger.logMsg(ERROR, "clinetPfailtoPatch");
		}
	}
}