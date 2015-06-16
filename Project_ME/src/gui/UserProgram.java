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
	
	String[] colArray = {"�̸�", "�����", "���̵�", "���"};
	
	public UserProgram(Client loginClient) throws ClassNotFoundException, SQLException
	{
		gui = GuiProcess.getInstance();
		trainer = loginClient.getTrainer();
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel trainerPanel = new JPanel();
		JPanel programPanel = new JPanel();
		
		//��, �� �߰�
		for(int i=0; i<colArray.length; i++)
			col.add(colArray[i]);	
		try {
			vectorProgram = gui.getProgram();
		} catch (ClassNotFoundException | SQLException e1) {
			JOptionPane.showMessageDialog(null, "�����ͺ��̽� ����");
		}
		row = gui.getRowPrograme(vectorProgram);
		
		//���̺� ����
		jtable = new JTable(row, col);
		scroll = new JScrollPane(jtable);
		
		trainerPanel.add(new JLabel("���� Ʈ���̳�  "));
		if(loginClient.getTrainer() != null)
			trainerPanel.add(new JLabel(loginClient.getTrainer()));
		else
			trainerPanel.add(new JLabel("����"));
		programPanel.add(new JLabel("���� ���α׷� ���"));
		
		add(trainerPanel);
		add(programPanel);
		add(scroll);
		//Vector<Program> program = loginClient.getProgram("all");
		
		setVisible(true);
		
	}
}
