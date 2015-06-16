package gui;

import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import object.Client;
import object.Program;

public class UserProgram extends JPanel {

	private String trainer;
	private GuiProcess gui;
	
	public UserProgram(Client loginClient) throws ClassNotFoundException, SQLException
	{
		gui = GuiProcess.getInstance();
		trainer = loginClient.getTrainer();
		
		//JPanel panel = new JPanel();
		//panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
		
		panel1.add(new JLabel("배정 트레이너:"));
		panel1.add(new JLabel(trainer));
		panel2.add(new JLabel("배정 프로그램"));
		
		Vector<Program> program = gui.getProgram();
		//Vector<Program> program = loginClient.getProgram("all");
		
		panel2.add(new JLabel("이름  " + "운동부위  " + "난이도  " + "비고"));
		for (Program p : program) {
			panel2.add(new JLabel(p.getName() + "   " + p.getPartOfBody() + "   " + p.getDifficulty() + "   " + p.getComment()));
		}
		
		add(panel1);
		add(panel2);
		setVisible(true);
	}
}
