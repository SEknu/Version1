package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class Client_Info extends JPanel implements ActionListener {

	JTable jtable;
	JScrollPane scroll;
	
	Vector<Client> allClient;
	Vector<Vector<String>> row = new Vector<Vector<String>>();
	Vector<String> col = new Vector<String>();
	
	String[] colArray = {"�̸�","����ó", "Ư�̻���", "���Ʈ���̳�"};
	
	JButton Button_Search = new JButton("�˻�");
	JButton Button_Remove = new JButton("����");
	JButton Button_Assign = new JButton("Ʈ���̳� ����");
	JButton Button_Program_Assign = new JButton("���α׷� ����");
	JButton Button_Detail = new JButton("������ ����");
	JTextField TF_Name = new JTextField(10);
	
	/* constructor */
	public Client_Info() {
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
	/* constructor */

	@Override
	public void actionPerformed(ActionEvent e) {
		// �˻�
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
		}
		// ����
		else if (e.getSource() == Button_Remove) {
			int index = jtable.getSelectedRow();
			
			if (index != -1) {
				Client obj = new Client();
				obj.setID(this.allClient.get(index).getID());
				FileManager.getInstance().delete(obj, "client");
				this.allClient = FileManager.getInstance().allClient();
				Patch(getRow());
			}
		}
		// Ʈ���̳� ����
		else if(e.getSource() == Button_Assign) {
			int index = jtable.getSelectedRow();
			Assign_Dialog<Trainer> ad;
			if (index != -1)
				ad = new Assign_Dialog<Trainer>(FileManager.getInstance().allTrainer(), this.allClient.get(index));
		}
		// ���α׷� ����
		else if(e.getSource() == Button_Program_Assign) {
//			Assign_Dialog_P<Program> ad = new Assign_Dialog_P<Program>(FileManager.getInstance().allProrgam());			
			
//			if(ad.selected != null) {
//				FileManager.getInstance().Client_List.get(jtable.getSelectedRow()).getProgram().add(ad.selected);
//				JOptionPane.showMessageDialog(null, "��������");
//			}
		}
		// ������ ����
		else if(e.getSource() == Button_Detail) {
			int index = jtable.getSelectedRow();
			if (index != -1) {
				//DB���� �´� ���� �ҷ��ͼ� �����ֱ�.�ڼ���
				new Detail_Panel(this.allClient.get(index));
			}
		}	
	}
	

	/* my function */
	public void Patch(Vector<Vector<String>> info) {
		row.removeAllElements();
		row.addAll(info);
		jtable.updateUI();
	}
	
	public Vector<Vector<String>> getRow() {
		Vector<Vector<String>> result = new Vector<Vector<String>>();

		this.allClient = FileManager.getInstance().allClient();
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
	/* my function */
}