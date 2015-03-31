package gui;

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

import object.Trainer;
import database.FileManager;

public class New_Trainer_Add extends JPanel implements ActionListener {
	JTable jtable;
	JScrollPane scroll;
	FileManager filemanager;
	Vector<Trainer> allTrainer = new Vector<Trainer>();
	Vector<Vector<String>> row = new Vector<Vector<String>>();
	Vector<String> col = new Vector<String>();
	
	String[] colArray = {"이름","전화번호","주소","입사일"};
	JButton Button_Add = new JButton("등록");
	JButton Button_Delete = new JButton("삭제");
	
	public New_Trainer_Add() throws ClassNotFoundException, SQLException {
		filemanager = new FileManager();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		
		Button_Add.addActionListener(this);
		Button_Delete.addActionListener(this);
		
		for(int i=0; i<colArray.length; i++)
			col.add(colArray[i]);
		
		row = getRow();
		jtable = new JTable(row, col);
		scroll = new JScrollPane(jtable);
		
		add(scroll);
		panel.add(Button_Add);
		panel.add(Button_Delete);
		add(panel);
		
		setSize(500, 550);
		setVisible(true);
	}
	
	public void Patch(Vector<Vector<String>> New) {
		row.removeAllElements();
		row.addAll(New);
		jtable.updateUI();
	}
	
	public Vector<Vector<String>> getRow() throws ClassNotFoundException, SQLException {
		Vector<Vector<String>> result = new Vector<Vector<String>>();

		allTrainer = filemanager.getTrainer("all");
		for (Trainer t : this.allTrainer) {
			Vector<String> v = new Vector<String>();
			
			v.add(t.getName());
			v.add(t.getPhone());
			v.add(t.getAddress());
			v.add(t.getRegistDate());
			
			result.add(v);
		}
			
		return result;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Button_Add) {
			new Add_trainer();
			
			try {
				Patch(getRow());
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if(e.getSource() == Button_Delete) {
			int index = jtable.getSelectedRow();
			
			if (index != -1){
				int delete = JOptionPane.showConfirmDialog(null, "정말 삭제하시겠습니까?", "", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if(delete==JOptionPane.YES_OPTION)
					try {
						filemanager.delete(this.allTrainer.get(index).getID(), "trainer");
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
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
}
