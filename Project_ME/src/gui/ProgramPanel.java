package gui;

import gui.ProgramRegister;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.sun.media.jfxmedia.logging.Logger;

import object.Client;
import object.Program;
import database.FileManager;

public class ProgramPanel extends JPanel implements ActionListener{

	JTable jtable;
	JScrollPane scroll;

	Vector<Vector<String>> row = new Vector<Vector<String>>();
	Vector<String> col = new Vector<String>();
	Vector<Program> vectorProgram;
	
	String[] colArray = {"이름", "운동부위", "난이도", "비고"};
	String[] selectionArray = {"이름", "운동부위", "난이도"};
	JButton addButton = new JButton("추가");
	JButton deleteButton = new JButton("삭제");
	JButton detailButton = new JButton("상세보기");
	JButton searchButton = new JButton("검색");
	JComboBox selectionList = new JComboBox(selectionArray);
	JTextField searchTextField = new JTextField(10);
	
	GuiProcess gui = GuiProcess.getInstance();
		
	public ProgramPanel() throws ClassNotFoundException, SQLException	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	
		JPanel panel = new JPanel();
		
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
		selectionList.setSelectedIndex(0);
		
		addButton.addActionListener(this);
		deleteButton.addActionListener(this);
		detailButton.addActionListener(this);
		searchButton.addActionListener(this);
		
		panel.add(selectionList);
		panel.add(searchTextField);
		panel.add(searchButton);
		panel.add(addButton);
		panel.add(deleteButton);
		panel.add(detailButton);
		add(scroll);
		add(panel);
		setSize(500, 550);
		setVisible(true);
	}
	
	public void Patch(Vector<Vector<String>> New)
	{
		row.removeAllElements();
		row.addAll(New);
		jtable.updateUI();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String col = null;
		if(e.getSource() == addButton)  {
			new ProgramRegister();
			try {
				vectorProgram = gui.getProgram();
			} catch (ClassNotFoundException | SQLException e1) {
				JOptionPane.showMessageDialog(null, "데이터베이스 오류");
			}
		}
		else if(e.getSource() == deleteButton) {
			int index = jtable.getSelectedRow();
			try {
				gui.delete(vectorProgram.get(index).getID(), "program");
				vectorProgram = gui.getProgram();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "데이터베이스 오류");
			} catch (ArrayIndexOutOfBoundsException e2) {
				JOptionPane.showMessageDialog(null, "삭제할 운동 프로그램이 없습니다.");
			}
		} else if(e.getSource() == detailButton) {
			int index = jtable.getSelectedRow();
			new ProgramDetail(this.vectorProgram.get(index));
		} else if (e.getSource() == searchButton) {					
			if(selectionList.getSelectedItem().equals(selectionArray[0])) {
				col = "name";
			} else if(selectionList.getSelectedItem().equals(selectionArray[1])) {
				col = "part_of_body";
			} else if(selectionList.getSelectedItem().equals(selectionArray[2])) {
				col = "difficulty";				
			} 
			String str = this.searchTextField.getText();
					
			if (str != null && str.length() > 0) {
				try {
					vectorProgram = gui.selectProgram(col, str);
				} catch (ClassNotFoundException | SQLException e1) {
					JOptionPane.showMessageDialog(null, "데이터베이스 접근실패");
				}
			} else {
				try {
					vectorProgram = gui.getProgram();
				} catch (ClassNotFoundException | SQLException e1) {
					JOptionPane.showMessageDialog(null, "데이터베이스 접근실패");
				}
			}
		} 
		
		try {
			Patch(gui.getRowPrograme(vectorProgram));
		} catch (ClassNotFoundException | SQLException e1) {
			JOptionPane.showMessageDialog(null, "불러오기 실패");
		}
	}
}

