package gui;
import gui.CommodityRegister;

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

public class CommodityPanel extends JPanel implements ActionListener{
	FileManager filemanager;
	
	JTable jtable;
	JScrollPane scroll;
	
	Vector<Commodity> allCommo = new Vector<Commodity>();
	Vector<Vector<String>> row = new Vector<Vector<String>>();
	Vector<String> col = new Vector<String>();
	//�ڼ����κ�
	String[] colArray = {"�̸�","�����","�ļ�����","���"};	
	JButton Button_Add = new JButton("�߰�1");
	JButton Button_Delete = new JButton("����");
	JButton Button_Info = new JButton("�󼼺���");
	//�ڼ����κ�
	public CommodityPanel() throws ClassNotFoundException, SQLException
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
	//�ڼ����κ�
	public Vector<Vector<String>> getRow() throws ClassNotFoundException, SQLException
	{
		Vector<Vector<String>> result = new Vector<Vector<String>>();	
		filemanager = new FileManager(); //�޼ҵ� �������� ���� ��ü ����
		allCommo = filemanager.getCommodity("all");
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
			new CommodityRegister();
		}	
		else if(e.getSource() == Button_Delete) {
			int index = jtable.getSelectedRow();
			System.out.println(index);
			if(index != -1)
			{
				try {
					filemanager.delete(this.allCommo.get(index).getID(), "commodity");
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				index = -1;
			}
		}
		else if(e.getSource() == Button_Info) {
			int index = jtable.getSelectedRow();
			new CommodityDetail(this.allCommo.get(index));
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