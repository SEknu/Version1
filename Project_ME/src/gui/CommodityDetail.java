package gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import object.Commodity;
import database.FileManager;

////�ڼ����κ� ���ο� Ŭ����(��ⱸ���� �󼼺����)
public class CommodityDetail  extends JDialog implements ActionListener {

	Commodity commodity;
	String year, month, day;
	
	JButton Button_Modify = new JButton("����"); //������ư
	JButton Button_Save = new JButton("����"); //�����ư
	JButton Button_Cancel = new JButton("���"); //��ҹ�ư
	JCheckBox checkbox = new JCheckBox(); //üũ�ڽ� - �ļ�����üũ��
	JPanel panel3 = new JPanel();  
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel[] pan = new JPanel[6];
	
	public CommodityDetail(Commodity commodity)	{
		this.commodity = commodity;
		
		//��ư�߰�
		Button_Cancel.addActionListener(this);
		Button_Save.addActionListener(this);
		Button_Modify.addActionListener(this);
		//��¥�� ��,��,�Ϸ� ����
		String date[] = commodity.getBuyDate().split("-"); 
		year = date[0];
		month = date[1];
		day = date[2];
		//�г� ���̾ƿ�����
		for(int i=0; i<6; i++)
			pan[i] = new JPanel(new GridLayout(1,2,8,8));
		//���� �ٱ� ���̾ƿ� ����
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		panel2.setLayout(new FlowLayout());
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
		//DB���� �´����� �ҷ��ͼ� �����ֱ� �ڼ���
		pan[0].add(new JLabel("�̸� : "));
		pan[1].add(new JLabel("���Գ�¥ : "));
		pan[2].add(new JLabel("����� : "));
		pan[3].add(new JLabel("���� : "));
		pan[4].add(new JLabel("�ļ�����  "));
		pan[5].add(new JLabel("���: " ));
		
		//���� �ʿ� -> text�ʵ� ũ�Ⱑ �� Ŀ��..
		pan[0].add(new JTextField(commodity.getName(),15));
		pan[1].add(new JTextField(year,5));
		pan[1].add(new JTextField(month,5));
		pan[1].add(new JTextField(day,5));
		pan[2].add(new JTextField(Integer.toString(commodity.getInventory()),15));
		pan[3].add(new JTextField(Integer.toString(commodity.getPrice()),15));
		pan[4].add(checkbox);
		pan[5].add(new JTextField(commodity.getComment(),15));
		//�г�1�� ���� �ְ�
		for(int i=0; i<6; i++)
			panel1.add(pan[i]);
		//�гο� ��ư�� �߰� �� ���� ���� �гο� �߰�
		panel2.add(Button_Modify);
		panel2.add(Button_Save);
		panel2.add(Button_Cancel);
		panel3.add(panel1);
		panel3.add(panel2);
		add(panel3);
		//üũ�ڽ� DB���� ���� �о ����
		if(commodity.getState() == 1) {
			checkbox.setSelected(true);
		}
		//���� ���ϵ��� ��Ȱ��ȭ
		Button_Save.setEnabled(false);
		Button_Cancel.setEnabled(false);
		for(int i = 0; i < 6; i++)
			pan[i].getComponent(1).setEnabled(false);
		pan[1].getComponent(2).setEnabled(false);
		pan[1].getComponent(3).setEnabled(false);
		pack();
		setModal(true);
		setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Button_Modify) {
			//���� ��ư Ŭ���� ��� â Ȱ��ȭ
			Button_Save.setEnabled(true);
			Button_Cancel.setEnabled(true);
			for(int i =0; i<6; i++)
				pan[i].getComponent(1).setEnabled(true);
			pan[1].getComponent(2).setEnabled(true);
			pan[1].getComponent(3).setEnabled(true);
		}	
		//�����ư Ŭ����
		else if(e.getSource() == Button_Save) {
			JTextField namefield = (JTextField) pan[0].getComponent(1);
			JTextField yearfield = (JTextField) pan[1].getComponent(1);
			JTextField monthfield = (JTextField) pan[1].getComponent(2);
			JTextField dayfield = (JTextField) pan[1].getComponent(3);
			JTextField Inventoryfield = (JTextField) pan[2].getComponent(1);
			JTextField pricefield = (JTextField) pan[3].getComponent(1);
			JTextField commentfield = (JTextField) pan[5].getComponent(1);
			//commodity���� ����
			commodity.setName(namefield.getText());
			commodity.setBuyDate(yearfield.getText()+"-"+monthfield.getText()+"-"+dayfield.getText());
			if(!Isdate(yearfield.getText(), monthfield.getText(), dayfield.getText())) {
				return ;
			}
			commodity.setInventory(Integer.parseInt(Inventoryfield.getText()));
			commodity.setPrice(Integer.parseInt(pricefield.getText()));
			commodity.setComment(commentfield.getText());
			if(checkbox.isSelected()) {
				commodity.setState(1);
			}
			else {
				commodity.setState(0);
			}
			//DB�� ����.
			try {
				FileManager.getInstance().updateCommodity(commodity);
				JOptionPane.showMessageDialog(null, "�����߽��ϴ�.");
				Button_Save.setEnabled(false);
				Button_Cancel.setEnabled(false);
				for(int i = 0; i < 6; i++)
					pan[i].getComponent(1).setEnabled(false);
				pan[1].getComponent(2).setEnabled(false);
				pan[1].getComponent(3).setEnabled(false);
			} catch (ClassNotFoundException | SQLException e1) {
				JOptionPane.showMessageDialog(null, "Database �������");
			}				
		}
		//��ҹ�ư Ŭ����.
		else if(e.getSource() == Button_Cancel) {
			int result = JOptionPane.showConfirmDialog(null, "������ ����Ͻðڽ��ϱ�?", null, JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null);
			if(result == 0)	{
				//�����ʿ� jtextfeild������ ������������ ���ư����� �ؾߵ�.
				Button_Save.setEnabled(false);
				Button_Cancel.setEnabled(false);
				for(int i = 0; i < 6; i++)
					pan[i].getComponent(1).setEnabled(false);
				pan[1].getComponent(2).setEnabled(false);
				pan[1].getComponent(3).setEnabled(false);
			}
		}
	}
	
	//�ڼ����κ� - ��¥ ������ �߸��Ǿ��� ���(�߰��� �޼ҵ�)
	public boolean Isdate(String syear, String smonth, String sday)	{
		int year, month, day;
		try	{
			year = Integer.parseInt(syear);
			month = Integer.parseInt(smonth);
			day = Integer.parseInt(sday);
			
			if(year <=0 || month <=0 || month > 12 || day <=0 || day > 31){
				JOptionPane.showMessageDialog(null, "��¥�� ��Ȯ�ϰ� �Է��ϼ���.");
				return false;
			}
		} 
		catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "���Գ�¥�� ���ڸ� �Է��ϼž��մϴ�.");
			return false;
		}	
		return true;
	}
}