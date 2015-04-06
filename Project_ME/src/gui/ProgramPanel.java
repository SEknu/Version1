package gui;

import gui.ProgramRegister;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import object.Program;
import database.FileManager;

public class ProgramPanel extends JPanel implements ActionListener{

	JTable jtable;
	JScrollPane scroll;

	Vector<Vector<String>> row = new Vector<Vector<String>>();
	Vector<String> col = new Vector<String>();
	
	String[] colArray = {"운동", "운동부위", "난이도", "비고"};
	JButton add_B = new JButton("추가");
	JButton delete_B = new JButton("삭제");
	
	FileManager filemanager = FileManager.getInstance();
	
	
	public ProgramPanel() throws ClassNotFoundException, SQLException
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	
		JPanel panel = new JPanel();
		
		//행, 열 추가
		for(int i=0; i<colArray.length; i++)
			col.add(colArray[i]);	
		
		row = getRow();
		
		//테이블 생성
		jtable = new JTable(row, col);
		scroll = new JScrollPane(jtable);
		
		add_B.addActionListener(this);
		delete_B.addActionListener(this);
		
		
		panel.add(add_B);
		panel.add(delete_B);
		add(panel);
		add(scroll);
		setSize(500, 550);
		setVisible(true);
	}
	
	public void Patch(Vector<Vector<String>> New)
	{
		row.removeAllElements();
		row.addAll(New);
		jtable.updateUI();
	}
	
	
	public Vector<Vector<String>> getRow() throws ClassNotFoundException, SQLException
	{
		Vector<Vector<String>> result = new Vector<Vector<String>>();
		
		try {
			Vector<String> vectorString = new Vector<String>();
			Vector<Program> vectorProgram = filemanager.getProgram("all");
				
			for (Program p : vectorProgram) {
				Vector<String> program = new Vector<String>();
				
				program.add(p.getName());
				program.add(p.getPartOfBody());
				program.add(p.getDifficulty());
				program.add(p.getComment());
						
				result.add(program);
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == add_B)  {
			new ProgramRegister();
		}
		else if(e.getSource() == delete_B) {
			int index = jtable.getSelectedRow();
			try {
				filemanager.delete(filemanager.getProgram("all").get(index).getID(), "program");
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ArrayIndexOutOfBoundsException e2) {
				JOptionPane.showMessageDialog(null, "삭제할 운동 프로그램이 없습니다.");
			}
		}
		
		try {
			Patch(getRow());
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}

