package gui;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import object.Client;
import object.Program;


public class Assign_Dialog_P<T> extends JDialog implements ActionListener{

	Vector<Program> list;
	JComboBox<String> Combo = new JComboBox<String>();
	JButton Button = new JButton("배정");
	JTextField JT_perpose = new JTextField(8);
	JTextField JT_number = new JTextField(8);
	JTextField JT_time = new JTextField(8);
	
	private Client clt;
	
	public Assign_Dialog_P(Vector<Program> list, Client c) {
		clt = c;
		this.list = list;
		
		setLayout(new FlowLayout());
		/*JPanel panel = new JPanel();
		JPanel[] pan = new JPanel[4];
		for(int i=0; i<4; i++)
			pan[i] = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pan[1].add(new JLabel("목적"));
		pan[2].add(new JLabel("횟수"));
		pan[3].add(new JLabel("시간"));
		*/
		
		Button.addActionListener(this);
		
		for (Program p : list) {
			Combo.addItem(p.getName());
		}
		
		add(Combo);
		/*for(int i=1; i<4; i++)
			panel.add(pan[i]);
		add(panel);*/
		add(new JLabel("목적"));
		add(JT_perpose);
		add(new JLabel("횟수"));
		add(JT_number);
		add(new JLabel("시간"));
		add(JT_time);
		add(Button);
		setSize(150, 200);
		setModal(true);
		//pack();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Button) {
			int index = Combo.getSelectedIndex();
			
			if (index != -1) {
				//FileManager.FileManager.getInstance().update(clt, "client");
			}
		}
		dispose();
	}
}
