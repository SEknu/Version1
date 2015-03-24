package gui.program;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import object.Program;
import database.FileManager;

public class Register_Program extends JDialog implements ActionListener {
	JButton Add_B = new JButton("추가");
	JButton Cancel_B = new JButton("취소");
	
	JTextField Name_TF = new JTextField(20);
	JTextField Part_TF = new JTextField(20);
	JTextField Difficulty_TF = new JTextField(20);
	JTextField Comment_TF = new JTextField(20);
	
	public Register_Program() {
		Add_B.addActionListener(this);
		Cancel_B.addActionListener(this);
		
		setLayout(new GridLayout(5, 2));
		//JPanel panel1 = new JPanel(new FlowLayout());
		add(new JLabel("이름"));
		add(Name_TF);
		add(new JLabel("운동 부위"));
		add(Part_TF);
		add(new JLabel("난이도"));
		add(Difficulty_TF);
		add(new JLabel("비고"));
		add(Comment_TF);
		
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
			program.setName(this.Name_TF.getText());
			program.setDifficulty(this.Difficulty_TF.getText());
			program.setPartOfBody(this.Part_TF.getText());
			program.setComment(this.Comment_TF.getText());
			
			filemanager = new FileManager();
			try {
				filemanager.addProgram(program);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
		}		
		dispose();
	}
}
