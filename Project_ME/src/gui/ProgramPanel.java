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
	
	String[] colArray = {"�̸�", "�����", "���̵�", "���"};
	String[] selectionArray = {"�̸�", "�����", "���̵�"};
	JButton addButton = new JButton("�߰�");
	JButton deleteButton = new JButton("����");
	JButton detailButton = new JButton("�󼼺���");
	JButton searchButton = new JButton("�˻�");
	JComboBox selectionList = new JComboBox(selectionArray);
	JTextField searchTextField = new JTextField(10);
	
	GuiProcess gui = GuiProcess.getInstance();
		
	public ProgramPanel() throws ClassNotFoundException, SQLException	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	
		JPanel panel = new JPanel();
		
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
				JOptionPane.showMessageDialog(null, "�����ͺ��̽� ����");
			}
		}
		else if(e.getSource() == deleteButton) {
			int index = jtable.getSelectedRow();
			try {
				gui.delete(vectorProgram.get(index).getID(), "program");
				vectorProgram = gui.getProgram();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "�����ͺ��̽� ����");
			} catch (ArrayIndexOutOfBoundsException e2) {
				JOptionPane.showMessageDialog(null, "������ � ���α׷��� �����ϴ�.");
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
					JOptionPane.showMessageDialog(null, "�����ͺ��̽� ���ٽ���");
				}
			} else {
				try {
					vectorProgram = gui.getProgram();
				} catch (ClassNotFoundException | SQLException e1) {
					JOptionPane.showMessageDialog(null, "�����ͺ��̽� ���ٽ���");
				}
			}
		} 
		
		try {
			Patch(gui.getRowPrograme(vectorProgram));
		} catch (ClassNotFoundException | SQLException e1) {
			JOptionPane.showMessageDialog(null, "�ҷ����� ����");
		}
	}
}

