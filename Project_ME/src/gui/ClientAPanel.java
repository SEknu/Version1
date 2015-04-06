package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import object.Client;
import object.Program;
import object.Trainer;
import database.FileManager;

public class ClientAPanel extends JPanel implements ActionListener {

	JTable jtable;
	JScrollPane scroll;
	FileManager filemanager;
	Vector<Client> allClient;
	Vector<Vector<String>> row = new Vector<Vector<String>>();
	Vector<String> col = new Vector<String>();
	
	String[] colArray = {"이름","연락처", "특이사항", "담당트레이너"};
	
	JButton Button_Search = new JButton("검색");
	JButton Button_Remove = new JButton("삭제");
	JButton Button_Assign = new JButton("트레이너 배정");
	JButton Button_Program_Assign = new JButton("프로그램 배정");
	JButton Button_Detail = new JButton("상세정보 보기");
	JTextField TF_Name = new JTextField(10);
	
	public ClientAPanel() throws ClassNotFoundException, SQLException {
		filemanager = new FileManager();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		JPanel panel2 = new JPanel();
		Button_Search.addActionListener(this);
		Button_Remove.addActionListener(this);
		Button_Assign.addActionListener(this);
		Button_Program_Assign.addActionListener(this);
		Button_Detail.addActionListener(this);
		
		for(int i=0; i<colArray.length; i++)
			col.add(colArray[i]);

		this.allClient = filemanager.getClient("all");
		row = getRow();
		jtable = new JTable(row, col);
		scroll = new JScrollPane(jtable);
		
		add(scroll);
		panel.add(TF_Name);
		panel.add(Button_Search);
		panel.add(Button_Remove);
		panel2.add(Button_Assign);
		panel2.add(Button_Program_Assign);
		panel2.add(Button_Detail);
		add(panel);
		add(panel2);
		
		setSize(550, 550);
		setVisible(true);
	}
	
	public void Patch(Vector<Vector<String>> New) {
		row.removeAllElements();
		row.addAll(New);
		jtable.updateUI();
	}
	
	public Vector<Vector<String>> getRow() {
		Vector<Vector<String>> result = new Vector<Vector<String>>();
		
		for (Client c : this.allClient) {
			Vector<String> e = new Vector<String>();
			
			e.add(c.getName());
			e.add(c.getPhone());
			e.add(c.getNote());
			e.add(c.getTrainer());
			
			result.add(e);
		}
			
		return result;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Button_Search) {
			Vector<Vector<String>> result = new Vector<Vector<String>>();
			
			String name = this.TF_Name.getText();
			
			if (name != null && name.length() > 0) {
				for (Client c : this.allClient) {
					Vector<String> a = new Vector<String>();
				
					if (c.getName().equals(name)) {
						a.add(c.getName());
						a.add(c.getPhone());
						a.add(c.getNote());
						a.add(c.getTrainer());
						
						result.add(a);
					}
				}
				
				Patch(result);
			}
		} else if (e.getSource() == Button_Remove) {
			int index = jtable.getSelectedRow();
			
			if (index != -1) {
				Client obj = new Client();
				
				//obj.setID(this.allClient.get(index).getID());
				try {
					filemanager.delete(this.allClient.get(index).getID(), "client");
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					FileManager filemanager = new FileManager();
					this.allClient = filemanager.getClient("all");
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Patch(getRow());
			}
		} else if(e.getSource() == Button_Assign) {
//			Assign_Dialog<Trainer> ad = new Assign_Dialog<Trainer>(FileManager.Trainer_List);
//			
//			if(ad.selected != null)
//			{
//				FileManager.Client_List.get(jtable.getSelectedRow()).setTrainer(ad.selected.getID());
//				JOptionPane.showMessageDialog(null, "배정성공");
//			}
		} else if(e.getSource() == Button_Program_Assign) {
//			Assign_Dialog_P<Program> ad = new Assign_Dialog_P<Program>(FileManager.Program_List);
//			
//			if(ad.selected != null)
//			{
//				FileManager.Client_List.get(jtable.getSelectedRow()).getProgram().add(ad.selected);
//				JOptionPane.showMessageDialog(null, "배정성공");
//			}
		} else if(e.getSource() == Button_Detail) {
			int index = jtable.getSelectedRow();
			if (index != -1) {
				//DB에서 맞는 정보 불러와서 보여주기.★수정
				new ClientDetail(this.allClient.get(index));
			}
		}
	}
}