package gui;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import object.Client;
import object.Trainer;
import database.FileManager;


public class ClientTrainer<T> extends JDialog implements ActionListener{

	FileManager filemanager;
	
	Vector<Trainer> list;
	JComboBox<String> Combo = new JComboBox<String>();
	JButton Button = new JButton("배정");
	
	private Client clt;
	
	public ClientTrainer(Vector<Trainer> list, Client c)
	{
		filemanager = FileManager.getInstance();
		clt = c;
		this.list = list;
		setLayout(new FlowLayout());
		
		Button.addActionListener(this);
		Combo.addItem("배정안함");
		for (Trainer t : list) {
			Combo.addItem(t.toString());
		}
		
		add(Combo);
		add(Button);
		
		setModal(true);
		pack();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Button) {
			int index = Combo.getSelectedIndex();
			
			if (index != -1) {
				clt.setTrainer(Combo.getItemAt(index));
				try {
					filemanager.updateClient(clt);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		dispose();
	}
}
