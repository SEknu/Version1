package gui;

import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import object.Client;
import object.Program;

public class UserProgram extends JPanel {

	private String trainer;
	private GuiProcess gui;
	JTable jtable;
	JScrollPane scroll;
	
	Vector<Vector<String>> row = new Vector<Vector<String>>();
	Vector<String> col = new Vector<String>();
	Vector<Program> vectorProgram;
	
	String[] colArray = {"이름", "운동부위", "난이도", "비고"};
	
	public UserProgram(Client loginClient) throws ClassNotFoundException, SQLException
	{
		gui = GuiProcess.getInstance();
		trainer = loginClient.getTrainer();
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel trainerPanel = new JPanel();
		JPanel programPanel = new JPanel();
		
		//행, 열 추가
		for(int i=0; i<colArray.length; i++)
			col.add(colArray[i]);	
		try {
			vectorProgram = gui.getProgram();
		} catch (ClassNotFoundException | SQLException e1) {
			JOptionPane.showMessageDialog(null, "데이터베이스 오류");
		}
		row = gui.getRowPrograme(vectorProgram);
		
		//테이블 생성
		jtable = new JTable(row, col);
		scroll = new JScrollPane(jtable);
		
		trainerPanel.add(new JLabel("배정 트레이너  "));
		if(loginClient.getTrainer() != null)
			trainerPanel.add(new JLabel(loginClient.getTrainer()));
		else
			trainerPanel.add(new JLabel("없음"));
		programPanel.add(new JLabel("배정 프로그램 목록"));
		
		add(trainerPanel);
		add(programPanel);
		add(scroll);
		//Vector<Program> program = loginClient.getProgram("all");
		
		setVisible(true);
		
	}
}
