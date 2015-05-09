package gui;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import object.Commodity;
import database.FileManager;


public class CommodityRegister extends JDialog implements ActionListener{

	JButton Button_OK = new JButton("추가");
	JButton button_Cancel = new JButton("취소");
	
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	//★수정부분
	JTextField TF_Name = new JTextField(5);
	JTextField TF_Year = new JTextField(5);
	JTextField TF_Month = new JTextField(5);
	JTextField TF_Date = new JTextField(5);
	
	JTextField TF_Price = new JTextField(5);
	JTextField TF_Quantity = new JTextField(5);
	JTextField TF_Comment = new JTextField(5);
	
	public CommodityRegister() {
		Button_OK.addActionListener(this);
		button_Cancel.addActionListener(this);
		
		setLayout(new GridLayout(6, 2));
		JPanel panel1 = new JPanel(new FlowLayout());
		
		add(new JLabel("이름"));
		add(TF_Name);
		add(new JLabel("구입날짜"));
		panel1.add(TF_Year);
		initJTextField(TF_Year,"yyyy");
		panel1.add(TF_Month);
		initJTextField(TF_Month,"mm");
		panel1.add(TF_Date);
		initJTextField(TF_Date,"dd");
		add(panel1);
		add(new JLabel("가격"));
		add(TF_Price);
		add(new JLabel("수량"));
		add(TF_Quantity);
		add(new JLabel("비고"));
		add(TF_Comment);
		
		add(Button_OK);
		add(button_Cancel);
		
		pack();
		setModal(true);
		setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Button_OK) {
			//String Date = String.format("%04d-%02d-%02d", Integer.parseInt(TF_Year.getText()), Integer.parseInt(TF_Month.getText()), Integer.parseInt(TF_Date.getText()));
			Commodity commdity = new Commodity();
			Calendar calender = Calendar.getInstance();
			
			String date = this.TF_Year.getText() + "-" + this.TF_Month.getText() + "-" + this.TF_Date.getText();
			int price = Integer.valueOf(this.TF_Price.getText());
			int inv = Integer.valueOf(this.TF_Quantity.getText());
			
			commdity.setID("" + calender.get(Calendar.HOUR_OF_DAY) + calender.get(Calendar.MINUTE) + calender.get(Calendar.SECOND));
			commdity.setName(this.TF_Name.getText());
			commdity.setBuyDate(date);
			commdity.setPrice(price);
			commdity.setInventory(inv);
			commdity.setState(0);
			commdity.setComment(this.TF_Comment.getText());
			
			try {
				database.FileManager.getInstance().addCommodity(commdity);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		
		dispose();
	}
	
	//★수정부분 - JTextField에 디폴트값 넣는 메소드(추가된 메소드) 
	public void initJTextField(final JTextField textfield, final String defaultstr)
	{
		textfield.setText(defaultstr);
		textfield.addFocusListener(new FocusListener(){
			
			@Override
			public void focusGained(FocusEvent e) {
				textfield.setText("");
			}
			
			@Override
			public void focusLost(FocusEvent e) {
			}
		});
	}
	
}