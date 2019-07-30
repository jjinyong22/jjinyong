package hotel_customer;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Cus_signUp extends JPanel implements ActionListener {
	JPanel[] panel;
	JLabel title;
	JLabel[] label;
	String[] l_txt = { "* I D", "* P W", "P W 확인", "* 성별", "* 핸드폰 번호", "이메일", "체크된 부분은 필수요소 입니다." };
	JLabel name_label;
	JTextField name_text;
	JButton[] btn;
	JTextField[] text;
	JComboBox check, phone;
	JRadioButton M, F;
	ButtonGroup group;
	JLabel[] gen;
	Font f = new Font("Sandoll", Font.PLAIN, 15);
	boolean flag;
	boolean flag2;

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		CRUDprocess crud = new CRUDprocess();
		if (o == btn[0]) {
			if (text[0].getText().equals("")) {
				JOptionPane.showMessageDialog(this, "아이디를 입력 해 주세요.");
			} else {
				customer_info info = crud.selectCustomerInfo(text[0].getText());
				if (info == null) {
					JOptionPane.showMessageDialog(this, "사용가능한 아이디 입니다.");
					;
					flag = true;
				} else {
					JOptionPane.showMessageDialog(this, "중복된 아이디 입니다.");
				}
			}
		}
		if (o == btn[1]) {
			if (text[1].getText().equals(text[2].getText())) {
				JOptionPane.showMessageDialog(this, "패스워드가 일치합니다.");
				flag2 = true;
			} else {
				JOptionPane.showMessageDialog(this, "패스워드가 일치하지 않습니다.");
			}
		}

		if (o == btn[2]) {
			main.card.show(main.card_pan, "login");
		}
		if (o == btn[3]) {
			for (int i = 0; i < text.length; i++) {
				try {
					text[i].setText("");
				} catch (Exception exception) {

				}
			}name_text.setText("");
			M.setSelected(false);F.setSelected(false);
		}
		if (o == btn[4]) {
			if (text[0].getText().equals("")) {
				JOptionPane.showMessageDialog(this, "아이디를 입력 해 주세요.");
			} else {
				if (flag == false) {
					JOptionPane.showMessageDialog(this, "아이디 중복체크를 해 주세요.");
				} else {
					if (name_text.getText().equals("")) {
						JOptionPane.showMessageDialog(this, "이름을 입력 해 주세요.");
					} else {
						if (text[2].getText().equals("")) {
							JOptionPane.showMessageDialog(this, "비밀번호를 입력 해 주세요.");
						} else {
							if (flag2 == false) {
								JOptionPane.showMessageDialog(this, "비밀번호를 확인 해 주세요.");
							} else {
								customer_info insert = new customer_info();
								insert.setCus_id(text[0].getText());
								insert.setCus_name(text[1].getText());
								insert.setCus_pw(text[2].getText());
								if (M.isSelected() == false && F.isSelected() == false) {
									JOptionPane.showMessageDialog(this, "성별을 선택해 주세요.");
								} else {
									if (M.isSelected() == true) {
										insert.setCus_gen("M");
									} else if (F.isSelected() == true) {
										insert.setCus_gen("F");
									}
									Calendar cal = Calendar.getInstance();
									int year = cal.YEAR;
									int month = cal.MONTH + 1;
									int day = cal.DAY_OF_MONTH;
									String date = year + "-" + month + "-" + day;
									insert.setCus_joindate(date);
									insert.setEmail(text[5].getText() + "@" + check.getSelectedItem());
									if(text[3].getText().equals("") && text[4].getText().equals("")) {
										JOptionPane.showMessageDialog(this, "핸드폰 번호를 입력 해 주세요.");
									}else {
									String phone_num = (String) phone.getSelectedItem() + "-"
											+ (String) text[3].getText() + "-" + (String) text[4].getText();

									insert.setPhone(phone_num);
									int result = crud.insertCustomerInfo(insert);
									if (result > 0) {
										JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
										for (int i = 0; i < text.length; i++) {
											try {
												text[i].setText("");
											} catch (Exception exception) {

											}
										}name_text.setText("");
										M.setSelected(false);F.setSelected(false);
										main.card.show(main.card_pan, "login");
									} else
										JOptionPane.showMessageDialog(null, "오류가 발생하였습니다.");
								}
							}
						}
					}
				}
			}
		}
	}
	}
	void setting() {
		panel = new JPanel[10];
		for (int i = 0; i < panel.length; i++) {
			panel[i] = new JPanel();
		} // End panel

		title = new JLabel("고객 회원 가입");
		title.setFont(new Font("Serif", Font.BOLD, 30));
		// End title

		label = new JLabel[7];
		for (int i = 0; i < label.length; i++) {
			label[i] = new JLabel(l_txt[i]);
			label[i].setFont(f);
		}
		// End label

		btn = new JButton[5];
		btn[0] = new JButton(new ImageIcon("image\\중복확인1.png"));
		btn[0].setRolloverIcon(new ImageIcon("image\\중복확인2.png"));
		btn[0].setBorderPainted(false);
		btn[0].setContentAreaFilled(false);
		btn[0].setFocusPainted(false);
		btn[1] = new JButton(new ImageIcon("image\\패스워드확인1.png"));
		btn[1].setRolloverIcon(new ImageIcon("image\\패스워드확인2.png"));
		btn[1].setBorderPainted(false);
		btn[1].setContentAreaFilled(false);
		btn[1].setFocusPainted(false);
		btn[2] = new JButton(new ImageIcon("image\\메인으로1.png"));
		btn[2].setRolloverIcon(new ImageIcon("image\\메인으로2.png"));
		btn[2].setBorderPainted(false);
		btn[2].setContentAreaFilled(false);
		btn[2].setFocusPainted(false);
		btn[3] = new JButton(new ImageIcon("image\\취소1.png"));
		btn[3].setRolloverIcon(new ImageIcon("image\\취소2.png"));
		btn[3].setBorderPainted(false);
		btn[3].setContentAreaFilled(false);
		btn[3].setFocusPainted(false);
		btn[4] = new JButton(new ImageIcon("image\\회원등록1.png"));
		btn[4].setRolloverIcon(new ImageIcon("image\\회원등록2.png"));
		btn[4].setBorderPainted(false);
		btn[4].setContentAreaFilled(false);
		btn[4].setFocusPainted(false);
		for (int i = 0; i < btn.length; i++) {
			btn[i].addActionListener(this);
		}
		// End button

		text = new JTextField[7];
		text[0] = new JTextField(10);
		text[1] = new JTextField(10);
		text[2] = new JTextField(10);
		text[3] = new JTextField(4);
		text[4] = new JTextField(4);
		text[5] = new JTextField(5);
		text[1] = new JPasswordField(10);
		text[2] = new JPasswordField(10);
		// End textField
		String[] email = { "naver.com", "daum.com", "gmail.com", "nate.com", "dreamWiz.com" };
		check = new JComboBox(email);

		String[] num = { "010", "011", "016", "017" };
		phone = new JComboBox(num);
		// End ComboBox

		group = new ButtonGroup();
		M = new JRadioButton();
		F = new JRadioButton();
		group.add(M);
		group.add(F);
		gen = new JLabel[2];
		gen[0] = new JLabel("남자");
		gen[0].setFont(f);
		gen[1] = new JLabel("여자");
		gen[1].setFont(f);
		// End gender

		name_text = new JTextField(10);

	}

	void setPanel() {
		panel[0].add(title);

		panel[1].add(label[0]);
		panel[1].add(new JLabel("	"));
		panel[1].add(text[0]);
		panel[1].add(btn[0]);

		panel[2].add(name_label = new JLabel("* 이름"));
		panel[2].add(new JLabel("	"));
		panel[2].add(name_text);

		panel[3].add(label[1]);
		panel[3].add(new JLabel("	"));
		panel[3].add(text[1]);

		panel[4].add(new JLabel("					"));
		panel[4].add(label[2]);
		panel[4].add(new JLabel("	"));
		panel[4].add(text[2]);
		panel[4].add(btn[1]);

		panel[5].add(label[3]);
		panel[5].add(new JLabel("	"));
		panel[5].add(M);
		panel[5].add(gen[0]);
		panel[5].add(new JLabel("   "));
		panel[5].add(F);
		panel[5].add(gen[1]);

		panel[6].add(label[4]);
		panel[6].add(new JLabel("	"));
		panel[6].add(phone);
		panel[6].add(new JLabel(" - "));
		panel[6].add(text[3]);
		panel[6].add(new JLabel(" - "));
		panel[6].add(text[4]);

		panel[7].add(label[5]);
		panel[7].add(new JLabel("	"));
		panel[7].add(text[5]);
		panel[7].add(new JLabel("@"));
		panel[7].add(check);

		panel[8].add(label[6]);

		panel[9].add(btn[2]);
		panel[9].add(new JLabel("  "));
		panel[9].add(btn[3]);
		panel[9].add(new JLabel("  "));
		panel[9].add(btn[4]);

		for (int i = 0; i < panel.length; i++) {
			panel[i].setBackground(Color.white);
			this.add(panel[i]);
		}
	}

	Screen main;

	public Cus_signUp(Screen main) {
		this.main = main;
		this.setLayout(new GridLayout(10, 1));
		setting();
		setPanel();
		this.setSize(800, 500);
		this.setVisible(true);
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		// new Cus_signUp();
	}

}