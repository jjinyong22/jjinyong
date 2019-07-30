package hotel_manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class EmployeeInfo_update extends JPanel implements ActionListener {

	JLabel[] labels;
	String[] label_titles = { "직원 정보 수정", "직원 아이디", "패스워드", "패스워드 확인", "이름", "입사일", "년", "월", "일", "전화번호", "-", "-",
			"소속부서", };

	JTextField[] texts;

	JPasswordField text_pw, text_pw_ck;

	JButton[] btns;

	ImageIcon emp_road_icon, emp_road_press, emp_road_rollover, pw_ck_icon, pw_ck_press, pw_ck_rollover, update_icon,
			update_press, update_rollover, cancel_icon, cancel_press, cancel_rollover;

	JComboBox[] combos;
	String[] phone_texts = { "010", "011", "012", "013", "014", "015", "016", "017", "018", "019" };
	String[] dept_texts = { "홍보부", "고객 관리부", "객실 관리부", "영업부", "경영부" };

	JPanel centerPanel, southPanel, center_southPanel;

	JPanel northPanel;

	JPanel first_panel, second_panel;

	JPanel[] first_pan;

	JPanel[] second_pan;

	boolean flag;

	void makeJLabel() {
		labels = new JLabel[13];
		for (int i = 0; i < labels.length; i++) {
			labels[i] = new JLabel(label_titles[i]);
		}
	}

	void makeJTextField() {
		texts = new JTextField[4];
		for (int i = 0; i < texts.length; i++) {
			texts[i] = new JTextField();
		}
		texts[0] = new JTextField(20);
		texts[1] = new JTextField(20);
		texts[2] = new JTextField(5);
		texts[3] = new JTextField(5);

		text_pw = new JPasswordField(20);
		text_pw_ck = new JPasswordField(20);
	}

	void makeJButton() {
		btns = new JButton[4];
		for (int i = 0; i < btns.length; i++) {
			btns[i] = new JButton();
		}
	}

	void makeImageIcon() {
		emp_road_icon = new ImageIcon("image\\emp_road.png");
		emp_road_press = new ImageIcon("image\\emp_road_press.png");
		emp_road_rollover = new ImageIcon("image\\emp_road_rollover.png");

		pw_ck_icon = new ImageIcon("image\\pw_check.png");
		pw_ck_press = new ImageIcon("image\\pw_check_press.png");
		pw_ck_rollover = new ImageIcon("image\\pw_check_rollover.png");

		update_icon = new ImageIcon("image\\update.png");
		update_press = new ImageIcon("image\\update_press.png");
		update_rollover = new ImageIcon("image\\update_rollover.png");

		cancel_icon = new ImageIcon("image\\cancel.png");
		cancel_press = new ImageIcon("image\\cancel_press.png");
		cancel_rollover = new ImageIcon("image\\cancel_rollover.png");

		btns[0] = new JButton(emp_road_icon);
		btns[0].setPressedIcon(emp_road_press);
		btns[0].setRolloverIcon(emp_road_rollover);
		btns[0].setBorderPainted(false);
		btns[0].setFocusPainted(false);
		btns[0].setContentAreaFilled(false);
		btns[0].addActionListener(this);

		btns[1] = new JButton(pw_ck_icon);
		btns[1].setPressedIcon(pw_ck_press);
		btns[1].setRolloverIcon(pw_ck_rollover);
		btns[1].setBorderPainted(false);
		btns[1].setFocusPainted(false);
		btns[1].setContentAreaFilled(false);
		btns[1].addActionListener(this);

		btns[2] = new JButton(update_icon);
		btns[2].setPressedIcon(update_press);
		btns[2].setRolloverIcon(update_rollover);
		btns[2].setBorderPainted(false);
		btns[2].setFocusPainted(false);
		btns[2].setContentAreaFilled(false);
		btns[2].addActionListener(this);

		btns[3] = new JButton(cancel_icon);
		btns[3].setPressedIcon(cancel_press);
		btns[3].setRolloverIcon(cancel_rollover);
		btns[3].setBorderPainted(false);
		btns[3].setFocusPainted(false);
		btns[3].setContentAreaFilled(false);
		btns[3].addActionListener(this);
	}

	void makeJComboBox() {
		combos = new JComboBox[5];
		for (int i = 0; i < combos.length; i++) {
			combos[i] = new JComboBox();
		}

		for (int i = 2019; i >= 1980; i--) {
			combos[0].addItem(i + "");
		}

		for (int i = 1; i <= 12; i++) {
			combos[1].addItem(i + "");
			combos[1].addActionListener(this);
		}
		for (int i = 1; i <= 31; i++) {
			combos[2].addItem(i);
		}
		for (int i = 0; i < phone_texts.length; i++) {
			combos[3].addItem(phone_texts[i]);
		}

		for (int i = 0; i < dept_texts.length; i++) {
			combos[4].addItem(dept_texts[i]);
		}
	}

	void makeJPanel() {
		centerPanel = new JPanel(new BorderLayout());
		centerPanel.setBackground(Color.WHITE);

		center_southPanel = new JPanel();
		center_southPanel.setBackground(Color.WHITE);

		southPanel = new JPanel(new GridLayout(1, 2));
		southPanel.setBackground(Color.WHITE);

		first_panel = new JPanel(new GridLayout(7, 1));
		first_pan = new JPanel[7];
		for (int i = 0; i < first_pan.length; i++) {
			first_pan[i] = new JPanel();
			first_pan[i].setBackground(Color.WHITE);
		}

		second_panel = new JPanel(new GridLayout(7, 1));
		second_pan = new JPanel[7];
		for (int i = 0; i < second_pan.length; i++) {
			second_pan[i] = new JPanel();
			second_pan[i].setBackground(Color.WHITE);
		}

		northPanel = new JPanel(new GridLayout(2, 1));
		northPanel.setBackground(Color.WHITE);
	}

	List<EmployeeInfo> list;

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object obj = arg0.getSource();
		EmployeeInfo info;
		CRUDprocess_emp crud = new CRUDprocess_emp();
		List list;
		list = crud.selectmanager();
		Iterator it = list.iterator();
		if (obj == btns[0]) {
			if (texts[0].getText().equals("")) {
				JOptionPane.showMessageDialog(this, "직원 아이디를 입력 해 주세요");
			} else {
				while (it.hasNext()) {// 갖은거 다 출력 길이만큼
					info = (EmployeeInfo) it.next();
					if (info.getMan_id().equals(texts[0].getText())) {
						 text_pw.setText(info.getMan_pw());
						 text_pw.setEnabled(false);
						texts[1].setText(info.man_name);
						// texts[2].setText(info.man_hiredate);
						String hire = info.man_hiredate;
						String phone =  info.man_phone;
						combos[0].setSelectedItem(hire.substring(0,4));
						
						if(hire.substring(5,6).equals("0")) {
						combos[1].setSelectedIndex(Integer.parseInt(hire.substring(6,7))-1);
						}else {
							combos[1].setSelectedIndex(Integer.parseInt(hire.substring(5,7))-1);
						}
						if(hire.substring(8,9).equals("0")){
							combos[2].setSelectedIndex(Integer.parseInt(hire.substring(9,10))-1);
							}else {
								combos[2].setSelectedIndex(Integer.parseInt(hire.substring(8,10))-1);
							}
						
						combos[3].setSelectedItem(phone.substring(0,3));
						texts[2].setText(phone.substring(4,8));
						texts[3].setText(phone.substring(9,13));
						combos[4].setSelectedItem(info.man_dept);
					}
				}
			}
		}
		if (obj == btns[1]) {
			if (text_pw.getText().equals(text_pw_ck.getText())) {
				flag = true;
				JOptionPane.showMessageDialog(this, "비밀번호가 일치합니다.");
			} else {
				JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다.");
			}
		}
		if (obj == btns[2]) {
			if (texts[0].getText().equals("")) {
				JOptionPane.showMessageDialog(this, "직원 아이디를 입력 해 주세요");
			} else {
				if (text_pw_ck.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "비밀번호를 입력해 주세요");
				} else {
					if (flag == false) {
						JOptionPane.showMessageDialog(this, "비밀번호 중복 체크를 해 주세요.");
					} else {
						String man_id = texts[0].getText();
						String man_pw = text_pw_ck.getText();
						String man_name = texts[1].getText();
						
						
						String man_phone = combos[3].getSelectedItem() + "-"+ texts[2].getText() + "-"+ texts[3].getText();
						String man_dept = (String) combos[4].getSelectedItem();
						info = new EmployeeInfo();
						info.setMan_id(man_id);
						info.setMan_pw(man_pw);
						info.setMan_name(man_name);
						String year = String.valueOf(combos[0].getSelectedItem());
						String month = String.valueOf(combos[1].getSelectedItem());
						if(Integer.parseInt(month) < 10) {
							month = "0" + month;
						}
						String day = String.valueOf(combos[2].getSelectedItem());
						if(Integer.parseInt(day) < 10) {
							day = "0" + day;
						}
						info.setMan_hiredate(year+"-" +month+"-"+day);
						
						//info.setMan_hiredate(man_hiredate);
						info.setMan_phone(man_phone);
						info.setMan_dept(man_dept);
						int r = crud.updatemanager(info);
						if (r > 0) {
							JOptionPane.showMessageDialog(this, "직원정보가 변경되었습니다.");
							texts[0].setText("");
							text_pw.setText("");
							text_pw_ck.setText("");
							texts[1].setText("");
							combos[0].setSelectedIndex(0);
							combos[1].setSelectedIndex(0);
							combos[2].setSelectedIndex(0);
							texts[2].setText("");
							texts[3].setText("");
							combos[3].setSelectedIndex(0);
							combos[4].setSelectedIndex(0);
						} else {
							JOptionPane.showMessageDialog(this, "직원정보변경 과정에서 문제가 발생했습니다.");
						}
					}
				}
			}
		}
		if(obj == btns[3]) {
			texts[0].setText("");
			text_pw.setText("");
			text_pw_ck.setText("");
			texts[1].setText("");
			combos[0].setSelectedIndex(0);
			combos[1].setSelectedIndex(0);
			combos[2].setSelectedIndex(0);
			texts[2].setText("");
			texts[3].setText("");
			combos[3].setSelectedIndex(0);
			combos[4].setSelectedIndex(0);
		}
		
		if (obj == combos[1]) {
			combos[2].removeAllItems();
			int month = Integer.parseInt((String) combos[1].getSelectedItem());
			switch (month) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				for (int i = 1; i <= 31; i++) {
					combos[2].addItem(i + "");
				}
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				for (int i = 1; i <= 30; i++) {
					combos[2].addItem(i + "");
				}
				break;
			case 2:
				for (int i = 1; i <= 28; i++) {
					combos[2].addItem(i + "");
				}
			}
		}
	}

	// EmployeeInfo_info_copy info;
	EmployeeInfo_update() {// EmployeeInfo_info_copy info){
//		super(str);
		// this.info = info;
		this.setLayout(new BorderLayout());

		makeJLabel();
		makeJTextField();
		makeJButton();
		makeImageIcon();
		makeJComboBox();
		makeJPanel();

		Font font = new Font("Sandoll 고딕 01 Light", Font.BOLD, 30);
		Color fontColor = new Color(116, 116, 116);

		labels[0].setFont(font);
		labels[0].setForeground(fontColor);

		Font lblFont = new Font("Sandoll 고딕 01 Light", Font.PLAIN, 15);
		Color lblFontColor = new Color(76, 76, 76);

		for (int i = 1; i < labels.length; i++) {
			labels[i].setFont(lblFont);
			labels[i].setForeground(lblFontColor);
		}

		center_southPanel.add(labels[1]);
		center_southPanel.add(texts[0]);
		center_southPanel.add(btns[0]);

		centerPanel.add("Center", new JLabel(""));
		centerPanel.add("South", center_southPanel);

		first_pan[0].add(new JLabel(""));
		first_pan[1].add(labels[2]);
		first_pan[1].add(text_pw);
		first_pan[2].add(labels[3]);
		first_pan[2].add(text_pw_ck);
		first_pan[3].add(btns[1]);
		first_pan[4].add(labels[4]);
		first_pan[4].add(texts[1]);
		first_pan[5].add(labels[5]);
		first_pan[5].add(combos[0]);
		first_pan[5].add(labels[6]);
		first_pan[5].add(combos[1]);
		first_pan[5].add(labels[7]);
		first_pan[5].add(combos[2]);
		first_pan[5].add(labels[8]);
		first_pan[6].add(new JLabel("  "));

		first_panel.add(first_pan[0]);
		first_panel.add(first_pan[1]);
		first_panel.add(first_pan[2]);
		first_panel.add(first_pan[3]);
		first_panel.add(first_pan[4]);
		first_panel.add(first_pan[5]);
		first_panel.add(first_pan[6]);

		second_pan[0].add(new JLabel(""));
		second_pan[1].add(labels[9]);
		second_pan[1].add(combos[3]);
		second_pan[1].add(labels[10]);
		second_pan[1].add(texts[2]);
		second_pan[1].add(labels[11]);
		second_pan[1].add(texts[3]);
		second_pan[2].add(new JLabel(""));
		second_pan[3].add(labels[12]);
		second_pan[3].add(combos[4]);
		second_pan[4].add(new JLabel(""));
		second_pan[5].add(btns[2]);
		second_pan[5].add(btns[3]);
		second_pan[6].add(new JLabel("  "));

		second_panel.add(second_pan[0]);
		second_panel.add(second_pan[1]);
		second_panel.add(second_pan[2]);
		second_panel.add(second_pan[3]);
		second_panel.add(second_pan[4]);
		second_panel.add(second_pan[5]);
		second_panel.add(second_pan[6]);

		southPanel.add(first_panel);
		southPanel.add(second_panel);

		labels[0].setHorizontalAlignment(labels[0].CENTER);

		northPanel.add(new JLabel(""));
		northPanel.add(labels[0]);

		this.setBackground(Color.WHITE);

		this.add("North", northPanel);
		this.add("Center", centerPanel);
		this.add("South", southPanel);

		this.setSize(900, 650);
		this.setVisible(true);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
//	public static void main(String[] args) {
//		new EmployeeInfo_update("직원 정보 변경 시스템");
//	}

}
