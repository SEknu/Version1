package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import object.Client;
import object.Program;

public class UserPanel extends JPanel implements ActionListener{

	private int regiDateYear;
	private int regiDateMonth;
	private int regiDateDate;
	private int experDateYear;
	private int experDateMonth;
	private int experDateDate;
	private int Attendance;
	private String rank;
	private String trainer;
	
	//JButton Logout = new JButton("Log out");
	//JTextField lg = new JTextField(15);
	
	JComboBox<String> combo = new JComboBox<String>();
	Vector<Program> program;
	JButton Button_Program_Delete = new JButton("삭제");
		
	public UserPanel(Client login) throws ClassNotFoundException, SQLException {
		if (login.getRegistDate() != null) {
			String date = login.getRegistDate();
			this.regiDateYear = Integer.valueOf(date.substring(0, 4));
			this.regiDateMonth = Integer.valueOf(date.substring(5, 7));
			this.regiDateDate = Integer.valueOf(date.substring(8, 10));
		}
		if (login.getTerminateDate() != null) {
			String date = login.getTerminateDate();
			this.experDateYear = Integer.valueOf(date.substring(0, 4));
			this.experDateMonth = Integer.valueOf(date.substring(5, 7));
			this.experDateDate = Integer.valueOf(date.substring(8, 10));
		}
		//this.Attendance = login.getAttendance();
		this.trainer = login.getTrainer();
		
		Button_Program_Delete.addActionListener(this);
		//Logout.addActionListener(this);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		//regi_date_year = login.getDate().getYear()+1900;
		//regi_date_month = login.getDate().getMonth()+1;
		//regi_date_date = login.getDate().getDate();
		
		panel1.add(new JLabel("등록일"));
		panel1.add(new JLabel(regiDateYear + "년 " + regiDateMonth + "월 "
				+ regiDateDate + "일"));
		
		experDateYear = regiDateYear;
		if (regiDateMonth + 3 > 12)
			experDateYear++;
		
		experDateMonth = (regiDateMonth + 3);
		if (experDateMonth > 12)
			experDateMonth = experDateMonth % 12;
		
		experDateDate = regiDateDate;
		
		JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel2.add(new JLabel("만료일"));
		panel2.add(new JLabel(experDateYear + "년 " + experDateMonth + "월 " +
				experDateDate + "일"));
		
		JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel3.add(new JLabel("출석일"));
		panel3.add(new JLabel(Integer.toString(this.Attendance)));
		
		JPanel panel4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel4.add(new JLabel("등급"));  //
		if (Attendance > 10)
		{
			rank = "우수회원";
		}
		else
			rank = "일반회원";
		panel4.add(new JLabel(rank));
		
		JPanel panel5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel5.add(new JLabel("트레이너"));
		panel5.add(new JLabel( this.trainer ));
		
		//JPanel panel6 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		//panel6.add(lg);
		//panel6.add(Logout);
		
		program = database.FileManager.getInstance().getProgram("all");
		
		for (Program p : program) {
			combo.addItem(p.getName());
		}
		
		JPanel panel7 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel7.add(combo);
		panel7.add(Button_Program_Delete);
		
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);
		panel.add(panel4);
		panel.add(panel5);
		panel.add(panel7);
		//panel.add(panel6);
		
		
		add(panel);
		//pack();
		//setResizable(false);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean logout = false;
		
		/*if (e.getSource() == Logout) {
			logout = FileManager.LogOut(lg.getText());
			
			if (logout == true)
				dispose();
		}*/
		
		if(e.getSource() == Button_Program_Delete) {
			int index = combo.getSelectedIndex();
			if (index != -1) {
				combo.remove(combo.getSelectedIndex());
			}
			combo.updateUI();
		}
	}
}