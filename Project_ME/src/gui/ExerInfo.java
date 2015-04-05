package gui;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import object.Program;
import database.FileManager;

public class ExerInfo extends JPanel {

	public ExerInfo() throws ClassNotFoundException, SQLException
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		panel.add(new JLabel("Program"));
		
		Vector<Program> program = FileManager.getInstance().getProgram("all");
		
		for (Program p : program) {
			panel.add(new JLabel(p.getName() + " " + p.getDifficulty()));
		}
		
		add(panel);
		setVisible(true);
	}
}
