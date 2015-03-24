package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

import object.*;

public class ModifyDialog extends JDialog implements ActionListener {

	JButton modi = new JButton("¼öÁ¤");
	JTextField plan = new JTextField(15);
	JTextField trainer = new JTextField(15);
	
	public ModifyDialog(Client c)
	{
		modi.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == modi)
		{
			
			dispose();
		}
	}

}
