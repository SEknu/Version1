package gui;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import object.*;


public class Assign_Dialog<T> extends JDialog implements ActionListener{

	Vector<Trainer> list;
	JComboBox<String> Combo = new JComboBox<String>();
	JButton Button = new JButton("น่มค");
	
	private Client clt;
	
	public Assign_Dialog(Vector<Trainer> list, Client c)
	{
		clt = c;
		this.list = list;
		setLayout(new FlowLayout());
		
		Button.addActionListener(this);
		
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
				clt.setTrainer((this.list.get(index)).getID());
				
				database.FileManager.getInstance().update(clt, "client");
			}
		}
		dispose();
	}
}
