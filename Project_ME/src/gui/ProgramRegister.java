package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import object.Program;
import database.FileManager;

public class ProgramRegister extends JDialog implements ActionListener {
	JButton Add_B = new JButton("추가");
	JButton Cancel_B = new JButton("취소");
	
	JTextField nameTextfield = new JTextField(20);
	JTextField partTextfield = new JTextField(20);
	JTextField difficultyTextfield = new JTextField(20);
	JTextField commentTextfield = new JTextField(20);
	
	public ProgramRegister() {
		Add_B.addActionListener(this);
		Cancel_B.addActionListener(this);
		
		setLayout(new GridLayout(5, 2));
		//JPanel panel1 = new JPanel(new FlowLayout());
		add(new JLabel("이름"));
		add(nameTextfield);
		add(new JLabel("운동 부위"));
		add(partTextfield);
		add(new JLabel("난이도"));
		add(difficultyTextfield);
		add(new JLabel("비고"));
		add(commentTextfield);
		
		add(Add_B);
		add(Cancel_B);
		
		pack();
		setModal(true);
		setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Add_B) {
			//DB부르기 ★수정
			FileManager filemanager;
			Program program = new Program();
			Calendar calender = Calendar.getInstance();
			
			program.setID("" + calender.get(Calendar.HOUR_OF_DAY) + calender.get(Calendar.MINUTE) + calender.get(Calendar.SECOND));
			program.setName(this.nameTextfield.getText());
			program.setDifficulty(this.difficultyTextfield.getText());
			program.setPartOfBody(this.partTextfield.getText());
			program.setComment(this.commentTextfield.getText());
			
			filemanager = new FileManager();
			try {
				filemanager.addProgram(program);
			} catch (ClassNotFoundException | SQLException e1) {
				JOptionPane.showMessageDialog(null, "프로그램 추가 실패");
			}			
		}		
		dispose();
	}
}
