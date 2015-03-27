package gui;
import gui.Register_Commodity_Dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import object.Commodity;
import database.FileManager;

public class Commodity_Panel extends JPanel implements ActionListener{
	FileManager filemanager;
	
	JTable jtable;
	JScrollPane scroll;
	
	Vector<Commodity> allCommo = new Vector<Commodity>();
	Vector<Vector<String>> row = new Vector<Vector<String>>();
	Vector<String> col = new Vector<String>();
	//★수정부분
	String[] colArray = {"이름","재고량","파손유무","비고"};	
	JButton Button_Add = new JButton("추가1");
	JButton Button_Delete = new JButton("삭제");
	JButton Button_Info = new JButton("상세보기");
	//★수정부분
	public Commodity_Panel() throws ClassNotFoundException, SQLException
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		
		for(int i=0; i<colArray.length; i++)
			col.add(colArray[i]);
		
		row = getRow();
		
		jtable = new JTable(row, col);
		scroll = new JScrollPane(jtable);
		
		
		Button_Add.addActionListener(this);
		Button_Delete.addActionListener(this);
		Button_Info.addActionListener(this);
		
		add(scroll);
		panel.add(Button_Add);
		panel.add(Button_Delete);
		panel.add(Button_Info);
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
	//★수정부분
	public Vector<Vector<String>> getRow() throws ClassNotFoundException, SQLException
	{
		Vector<Vector<String>> result = new Vector<Vector<String>>();	
		filemanager = new FileManager(); //메소드 수정으로 인한 객체 생성
		allCommo = filemanager.getAllCommodity();
		for (Commodity c : this.allCommo) {
			Vector<String> commodity = new Vector<String>();	
			commodity.add(c.getName());
			commodity.add(String.valueOf(c.getInventory()));
			commodity.add(String.valueOf(c.getState()));
			commodity.add(c.getComment());
			result.add(commodity);
		}

		return result;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Button_Add) {
			new Register_Commodity_Dialog();
		}	
		else if(e.getSource() == Button_Delete) {
			int index = jtable.getSelectedRow();
			System.out.println(index);
			if(index != -1)
			{
				try {
					filemanager.deletetemp(this.allCommo.get(index).getID(), "commodity");
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				index = -1;
			}
		}
		else if(e.getSource() == Button_Info) {
			
		}
		try {
			Patch(getRow());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}