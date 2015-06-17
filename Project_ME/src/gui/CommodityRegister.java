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

import com.sun.media.jfxmedia.logging.Logger;

import object.Commodity;

public class CommodityRegister extends JDialog implements ActionListener {
	GuiProcess gui;

	JButton okButton = new JButton("추가");
	JButton cancelButton = new JButton("취소");

	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	// ★수정부분
	JTextField nameTextfield = new JTextField(5);
	JTextField yearTextfield = new JTextField(5);
	JTextField monthTextfield = new JTextField(5);
	JTextField dateTextfield = new JTextField(5);

	JTextField priceTextfield = new JTextField(5);
	JTextField quantityTextfield = new JTextField(5);
	JTextField commentTextfield = new JTextField(5);

	public CommodityRegister() {
		gui = GuiProcess.getInstance();

		okButton.addActionListener(this);
		cancelButton.addActionListener(this);

		setLayout(new GridLayout(6, 2));
		JPanel panel1 = new JPanel(new FlowLayout());

		add(new JLabel("이름"));
		add(nameTextfield);
		add(new JLabel("구입날짜"));
		panel1.add(yearTextfield);
		initJTextField(yearTextfield, "yyyy");
		panel1.add(monthTextfield);
		initJTextField(monthTextfield, "mm");
		panel1.add(dateTextfield);
		initJTextField(dateTextfield, "dd");
		add(panel1);
		add(new JLabel("가격"));
		add(priceTextfield);
		add(new JLabel("수량"));
		add(quantityTextfield);
		add(new JLabel("비고"));
		add(commentTextfield);

		add(okButton);
		add(cancelButton);

		pack();
		setModal(true);
		setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == okButton) {
			// String Date = String.format("%04d-%02d-%02d",
			// Integer.parseInt(TF_Year.getText()),
			// Integer.parseInt(TF_Month.getText()),
			// Integer.parseInt(TF_Date.getText()));
			Commodity commdity = new Commodity();
			Calendar calender = Calendar.getInstance();

			String date = this.yearTextfield.getText() + "-"
					+ this.monthTextfield.getText() + "-"
					+ this.dateTextfield.getText();
			int price = Integer.valueOf(this.priceTextfield.getText());
			int inv = Integer.valueOf(this.quantityTextfield.getText());

			commdity.setID("" + calender.get(Calendar.HOUR_OF_DAY)
					+ calender.get(Calendar.MINUTE)
					+ calender.get(Calendar.SECOND));
			commdity.setName(this.nameTextfield.getText());
			commdity.setBuyDate(date);
			commdity.setPrice(price);
			commdity.setInventory(inv);
			commdity.setState(0);
			commdity.setComment(this.commentTextfield.getText());

			try {
				gui.add(commdity);
			} catch (ClassNotFoundException | SQLException e1) {
				Logger.logMsg(ERROR, "failToAddCommodity");
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