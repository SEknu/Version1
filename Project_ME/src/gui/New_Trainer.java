package gui;

import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import object.Trainer;

public class New_Trainer extends JPanel {
	JTable jtable;
	JScrollPane scroll;

	Vector<Trainer> allTrainer = new Vector<Trainer>();
	Vector<Vector<String>> row = new Vector<Vector<String>>();
	Vector<String> col = new Vector<String>();
	
	String[] colArray = {"이름","전화번호","주소","입사일"};
/*	JButton Button_Add = new JButton("등록");
	JButton Button_Delete = new JButton("삭제");
*/	
	public New_Trainer() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		
	//	Button_Add.addActionListener(this);
	//	Button_Delete.addActionListener(this);
		
		for(int i=0; i<colArray.length; i++)
			col.add(colArray[i]);
		
		row = getRow();
		jtable = new JTable(row, col);
		scroll = new JScrollPane(jtable);
		
		add(scroll);
	//	panel.add(Button_Add);
	//	panel.add(Button_Delete);
		add(panel);
		
		setSize(500, 550);
		setVisible(true);
	}
	
	public void Patch(Vector<Vector<String>> New) {
		row.removeAllElements();
		row.addAll(New);
		jtable.updateUI();
	}
	
	public Vector<Vector<String>> getRow() {
		Vector<Vector<String>> result = new Vector<Vector<String>>();
		
		allTrainer = database.FileManager.getInstance().allTrainer();
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
}