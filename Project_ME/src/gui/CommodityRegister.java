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
import javax.swing.JPanel;
import javax.swing.JTextField;

import object.Commodity;

public class CommodityRegister extends JDialog implements ActionListener {

	JButton button_OK = new JButton("추가");
	JButton button_Cancel = new JButton("취소");

	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	// ★수정부분
	JTextField tf_Name = new JTextField(5);
	JTextField tf_Year = new JTextField(5);
	JTextField tf_Month = new JTextField(5);
	JTextField tf_Date = new JTextField(5);

	JTextField tf_Price = new JTextField(5);
	JTextField tf_Quantity = new JTextField(5);
	JTextField tf_Comment = new JTextField(5);

	public CommodityRegister() {
		button_OK.addActionListener(this);
		button_Cancel.addActionListener(this);

		setLayout(new GridLayout(6, 2));
		JPanel panel1 = new JPanel(new FlowLayout());

		add(new JLabel("이름"));
		add(tf_Name);
		add(new JLabel("구입날짜"));
		panel1.add(tf_Year);
		initJTextField(tf_Year, "yyyy");
		panel1.add(tf_Month);
		initJTextField(tf_Month, "mm");
		panel1.add(tf_Date);
		initJTextField(tf_Date, "dd");
		add(panel1);
		add(new JLabel("가격"));
		add(tf_Price);
		add(new JLabel("수량"));
		add(tf_Quantity);
		add(new JLabel("비고"));
		add(tf_Comment);

		add(button_OK);
		add(button_Cancel);

		pack();
		setModal(true);
		setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button_OK) {
			// String Date = String.format("%04d-%02d-%02d",
			// Integer.parseInt(TF_Year.getText()),
			// Integer.parseInt(TF_Month.getText()),
			// Integer.parseInt(TF_Date.getText()));
			Commodity commdity = new Commodity();
			Calendar calender = Calendar.getInstance();

			String date = this.tf_Year.getText() + "-"
					+ this.tf_Month.getText() + "-" + this.tf_Date.getText();
			int price = Integer.valueOf(this.tf_Price.getText());
			int inv = Integer.valueOf(this.tf_Quantity.getText());

			commdity.setID("" + calender.get(Calendar.HOUR_OF_DAY)
					+ calender.get(Calendar.MINUTE)
					+ calender.get(Calendar.SECOND));
			commdity.setName(this.tf_Name.getText());
			commdity.setBuyDate(date);
			commdity.setPrice(price);
			commdity.setInventory(inv);
			commdity.setState(0);
			commdity.setComment(this.tf_Comment.getText());

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

	// ★수정부분 - JTextField에 디폴트값 넣는 메소드(추가된 메소드)
	public void initJTextField(final JTextField textfield,
			final String defaultstr) {
		textfield.setText(defaultstr);
		textfield.addFocusListener(new FocusListener() {

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