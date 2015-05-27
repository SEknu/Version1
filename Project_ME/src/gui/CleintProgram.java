package gui;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import database.FileManager;
import object.Client;
import object.Program;


public class CleintProgram<T> extends JDialog implements ActionListener{

	FileManager filemanager;
	
	Vector<Program> list;
	JComboBox<String> combo = new JComboBox<String>();
	JButton button = new JButton("배정");
	
	private Client clt;
	
	public CleintProgram(Vector<Program> list, Client c) {
		filemanager = FileManager.getInstance();
		clt = c;
		this.list = list;
		
		setLayout(new FlowLayout());
		
		button.addActionListener(this);
		combo.addItem(" ");
		for (Program p : list) {
			combo.addItem(p.getName());
		}
		
		add(combo);
		add(button);
		setModal(true);
		pack();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int index = combo.getSelectedIndex();
		
		if (index != -1) {
			clt.setProgram(combo.getItemAt(index));
			try {
				filemanager.updateClient(clt);
				JOptionPane.showMessageDialog(null, "저장했습니다.");
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		dispose();
	}
}
