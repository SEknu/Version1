package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import object.Commodity;
import database.FileManager;

public class Commodity_Panel extends JPanel implements ActionListener{

	JTable jtable;
	JScrollPane scroll;
	
	Vector<Commodity> allCommo = new Vector<Commodity>();
	Vector<Vector<String>> row = new Vector<Vector<String>>();
	Vector<String> col = new Vector<String>();
	//★수정부분
	String[] colArray = {"이름","구입날짜","재고량","비고"};	
	JButton Button_Add = new JButton("추가");
	JButton Button_Delete = new JButton("삭제");
	JButton Button_Info = new JButton("상세보기");
	//★수정부분
	public Commodity_Panel()
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
	public Vector<Vector<String>> getRow()
	{
		Vector<Vector<String>> result = new Vector<Vector<String>>();
		
		allCommo = database.FileManager.getInstance().allCommodity();
		for (Commodity c : this.allCommo) {
			Vector<String> commodity = new Vector<String>();
			
			commodity.add(c.getName());
			commodity.add(c.getBuyDate());
			commodity.add(String.valueOf(c.getInventory()));
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
			
			if(index != -1)
			{
				database.FileManager.getInstance().delete(this.allCommo.get(index), "commodity");
				index = -1;
			}
		}
		//★수정부분 상세보기 버튼 추가
		else if(e.getSource() == Button_Info) {
			int index = jtable.getSelectedRow();
			if (index != -1) {
				//DB에서 맞는 정보 불러와서 보여주기.★수정
				new Detail_Commodity_Panel(this.allCommo.get(index));
			}
		}
		Patch(getRow());
	}
}
