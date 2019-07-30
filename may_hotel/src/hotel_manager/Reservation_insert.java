package hotel_manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

class ImageClass extends JPanel {
	Image img;
	Reservation_insert res_insert;

	ImageClass(String path, Reservation_insert res_insert) {
		this.res_insert = res_insert;
		img = Toolkit.getDefaultToolkit().getImage(path);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);

	}
}

public class Reservation_insert extends JPanel implements ActionListener {

	JLabel[] labels;
	String[] label_titles = { "예약 정보 등록", "고객 아이디", "객실 선택", "체크인 날짜    ", "체크아웃 날짜", "조식 선택", "※ 1인당 15,000원 ",
			"총 예약금액   ", "객실 이미지", "호","명" };

	JTextField[] texts;

	JComboBox room_type;
	String[] room_types = { "STANDARD", "SUPERIOR", "DELUXE", "EXECUTIVE" };

	JComboBox room_id;
	String[] standard_id = { "1001", "1002", "1003", "1004", "2001", "2002", "2003", "2004", };
	String[] superior_id = { "3001", "3002", "3003", "3004", "4001", "4002", "4003", "4004" };
	String[] deluxe_id = { "5001", "5002", "5003", "5004", "6001", "6002", "6003", "6004" };
	String[] executive_id = { "7001", "7002", "7003", "7004", "8001", "8002", "8003", "8004" };

	JComboBox cus_head;

	JRadioButton breakfast_y, breakfast_n;

	ButtonGroup group;

	JButton[] btns;

	JPanel first_panel, second_panel;

	JPanel center_panel;

	JPanel[] panels;

	JPanel[] image_panel;

	JPanel imagePanel;

	JPanel northPanel;

	JPanel second_northPanel, second_southPanel;

	ImageIcon choice_icon, choice_press, choice_rollover, reservation_icon, reservation_press, reservation_rollover,
			cancel_icon, cancel_press, cancel_rollover;

	Font mainFont, imageFont, font;

	ReservationInsert_calendar_checkIn calendar;

	String cal_date;

	void makeJLabels() {
		labels = new JLabel[11];
		for (int i = 0; i < labels.length; i++) {
			labels[i] = new JLabel(label_titles[i]);
		}
	}

	void makeJTextField() {
		texts = new JTextField[4];
		for (int i = 0; i < texts.length; i++) {
			texts[0] = new JTextField(15);
			texts[1] = new JTextField(15);
			texts[2] = new JTextField(15);
			texts[3] = new JTextField(15);
		}
	}

	void makeJComboBox() {
		room_type = new JComboBox();
		for (int i = 0; i < room_types.length; i++) {
			room_type.addItem(room_types[i]);
			room_type.addActionListener(this);
		}
		room_id = new JComboBox();
		room_id.addItem("----------");
		cus_head = new JComboBox();
		for (int i = 1; i <= 10; i++) {
			cus_head.addItem(i);
		}
	}

	void makeJRadioButton() {
		group = new ButtonGroup();
		breakfast_y = new JRadioButton("포함");
		breakfast_y.setBackground(Color.WHITE);
		breakfast_y.addActionListener(this);
		breakfast_n = new JRadioButton("미포함");
		breakfast_n.setBackground(Color.WHITE);
		breakfast_n.addActionListener(this);
		group.add(breakfast_y);
		group.add(breakfast_n);
	}

	void makeJButton() {
		btns = new JButton[4];
		for (int i = 0; i < btns.length; i++) {
			btns[i] = new JButton();
		}
	}

	void makeImageIcon() {
		choice_icon = new ImageIcon("image\\choice.png");
		choice_press = new ImageIcon("image\\choice_press.png");
		choice_rollover = new ImageIcon("image\\choice_rollover.png");

		reservation_icon = new ImageIcon("image\\reservation.png");
		reservation_press = new ImageIcon("image\\reservation_press.png");
		reservation_rollover = new ImageIcon("image\\reservation_rollover.png");

		cancel_icon = new ImageIcon("image\\cancel.png");
		cancel_press = new ImageIcon("image\\cancel_press.png");
		cancel_rollover = new ImageIcon("image\\cancel_rollover.png");

		btns[0] = new JButton(choice_icon);
		btns[0].setPressedIcon(choice_press);
		btns[0].setRolloverIcon(choice_rollover);
		btns[0].setBorderPainted(false);
		btns[0].setContentAreaFilled(false);
		btns[0].setFocusPainted(false);
		btns[0].addActionListener(this);

		btns[1] = new JButton(choice_icon);
		btns[1].setPressedIcon(choice_press);
		btns[1].setRolloverIcon(choice_rollover);
		btns[1].setBorderPainted(false);
		btns[1].setContentAreaFilled(false);
		btns[1].setFocusPainted(false);
		btns[1].addActionListener(this);

		btns[2] = new JButton(reservation_icon);
		btns[2].setPressedIcon(reservation_press);
		btns[2].setRolloverIcon(reservation_rollover);
		btns[2].setBorderPainted(false);
		btns[2].setContentAreaFilled(false);
		btns[2].setFocusPainted(false);
		btns[2].addActionListener(this);

		btns[3] = new JButton(cancel_icon);
		btns[3].setPressedIcon(cancel_press);
		btns[3].setRolloverIcon(cancel_rollover);
		btns[3].setBorderPainted(false);
		btns[3].setContentAreaFilled(false);
		btns[3].setFocusPainted(false);
		btns[3].addActionListener(this);
	}

	void makeJPanel() {
		center_panel = new JPanel();
		center_panel.setLayout(new GridLayout(1, 2));
		center_panel.setBackground(Color.WHITE);

		first_panel = new JPanel();
		first_panel.setLayout(new GridLayout(9, 1));
		first_panel.setBackground(Color.WHITE);

		second_panel = new JPanel();
		second_panel.setLayout(new BorderLayout());
		second_panel.setBackground(Color.WHITE);

		panels = new JPanel[9];
		for (int i = 0; i < panels.length; i++) {
			panels[i] = new JPanel();
			panels[i].setBackground(Color.WHITE);
		}

		image_panel = new JPanel[4];
		for (int i = 0; i < image_panel.length; i++) {
			image_panel[0] = new ImageClass("image\\room1.jpg", this);
			image_panel[1] = new ImageClass("image\\room2.jpg", this);
			image_panel[2] = new ImageClass("image\\room3.jpg", this);
			image_panel[3] = new ImageClass("image\\room4.jpg", this);
		}

		imagePanel = new JPanel();
		imagePanel.setLayout(new BorderLayout());

		// 이미지 패널 상하 여백
		JPanel image_northPanel = new JPanel();
		image_northPanel.setLayout(new GridLayout(3, 1));
		image_northPanel.setBackground(Color.WHITE);
		image_northPanel.add(new JLabel("                                     "));
		image_northPanel.add(new JLabel("                                     "));
		image_northPanel.add(new JLabel("                                     "));

		JPanel image_southPanel = new JPanel();
		image_southPanel.setLayout(new GridLayout(3, 1));
		image_southPanel.setBackground(Color.WHITE);
		image_southPanel.add(new JLabel("                                     "));
		image_southPanel.add(new JLabel("                                     "));
		image_southPanel.add(new JLabel("                                     "));

		imagePanel.add("North", image_northPanel);
		imagePanel.add("South", image_southPanel);

		northPanel = new JPanel(new GridLayout(2, 1));

		second_northPanel = new JPanel(new GridLayout(2, 1));

		second_southPanel = new JPanel(new GridLayout(2, 1));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object obj = arg0.getSource();
		if (obj == room_type) {
			if (room_type.getSelectedIndex() == 0) {
				room_id.removeAllItems();
				for (int i = 0; i < standard_id.length; i++) {
					room_id.addItem(standard_id[i]);
				}
				imagePanel.revalidate();// 컴포넌트를 다시 그릴 때 호출되는 메서드
				imagePanel.repaint();// 이미지를 다시 그릴 때 호출되는 메서드
				imagePanel.add("Center", image_panel[0]);
			}
			if (room_type.getSelectedIndex() == 1) {
				room_id.removeAllItems();
				for (int i = 0; i < 8; i++) {
					room_id.addItem(superior_id[i]);
				}
				imagePanel.revalidate();// 컴포넌트를 다시 그릴 때 호출되는 메서드
				imagePanel.repaint();// 이미지를 다시 그릴 때 호출되는 메서드
				imagePanel.add("Center", image_panel[1]);
			}
			if (room_type.getSelectedIndex() == 2) {
				room_id.removeAllItems();
				for (int i = 0; i < 8; i++) {
					room_id.addItem(deluxe_id[i]);
				}
				imagePanel.revalidate();// 컴포넌트를 다시 그릴 때 호출되는 메서드
				imagePanel.repaint();// 이미지를 다시 그릴 때 호출되는 메서드
				imagePanel.add("Center", image_panel[2]);
			}
			if (room_type.getSelectedIndex() == 3) {
				room_id.removeAllItems();
				for (int i = 0; i < 8; i++) {
					room_id.addItem(executive_id[i]);
				}
				imagePanel.revalidate();// 컴포넌트를 다시 그릴 때 호출되는 메서드
				imagePanel.repaint();// 이미지를 다시 그릴 때 호출되는 메서드
				imagePanel.add("Center", image_panel[3]);
			}
		}
		if (obj == btns[0]) {
			new ReservationInsert_calendar_checkIn(this);
		}
		if (obj == btns[1]) {
			new ReservationInsert_calendar_checkOut(this);
		}
		if (obj == btns[2]) {
			if(!texts[1].getText().equals("")&&!texts[2].getText().equals("")) {
				String id = texts[0].getText();
				CRUDprocess_emp crud = new CRUDprocess_emp();
				String cus_id = crud.selectCusID(id);
				if (cus_id != null) {
					String[] str = { "예 약", "취 소" };
					int select = JOptionPane.showOptionDialog(this, "예약하시겠습니까?", "예 약", JOptionPane.YES_NO_OPTION,
							JOptionPane.INFORMATION_MESSAGE, null, str, str[0]);
					if (select == JOptionPane.YES_OPTION) {
						int res_num = crud.selectMaxResNum() + 1;
						String res_room = String.valueOf(room_id.getSelectedItem());
						Calendar cal = Calendar.getInstance();
						String year = String.valueOf(cal.get(cal.YEAR));
						String month = String.valueOf(cal.get(cal.MONTH) + 1);
						String date = String.valueOf(cal.get(cal.DATE));
						if (Integer.parseInt(month) < 10) {
							month = "0" + month;
						}
						if (Integer.parseInt(date) < 10) {
							date = "0" + date;
						}
						String res_date = year + "-" + month + "-" + date;
						String res_checkin = texts[1].getText();
						String res_checkout = texts[2].getText();
						String res_breakfast = null;
						if (breakfast_y.isSelected() == true) {
							res_breakfast = "Y";
						} else if (breakfast_n.isSelected() == true) {
							res_breakfast = "N";
						}
						String res_payment = texts[3].getText();
						String res_head = String.valueOf(cus_head.getSelectedIndex() + 1);
						String res_state = "예약완료";
						ReservationInfo info = new ReservationInfo();
						info.setRes_num(res_num);
						info.setRes_id(cus_id);
						info.setRes_room(res_room);
						info.setRes_date(res_date);
						info.setRes_cancel(null);
						info.setRes_checkin(res_checkin);
						info.setRes_checkout(res_checkout);
						info.setRes_breakfast(res_breakfast);
						info.setRes_head(res_head);
						info.setRes_payment(res_payment);
						info.setRes_state(res_state);
						int result = crud.insertIntoReservationInfo(info);
						if (result > 0) {
							JOptionPane.showMessageDialog(this, "예약이 완료되었습니다.");
							texts[0].setText(" ");
							texts[0].setText("");
							room_type.setSelectedIndex(0);
							room_id.setSelectedItem("--------------");;
							texts[1].setText(" ");
							texts[1].setText("");
							texts[2].setText(" ");
							texts[2].setText("");
							breakfast_y.setSelected(false);
							breakfast_n.setSelected(false);
							cus_head.setSelectedIndex(0);
							texts[3].setText(" ");
							texts[3].setText("");
							texts[4].setText(" ");
							texts[4].setText("");
							texts[4].setEnabled(true);
							imagePanel.removeAll();
						} else {
							JOptionPane.showMessageDialog(this, "예약 과정에서 문제가 발생했습니다.");
						}
					} else {}
				} else {
					JOptionPane.showMessageDialog(this, "아이디가 존재하지 않습니다.");
					texts[0].setText(" ");
					texts[0].setText("");
				}
			}else {
				JOptionPane.showMessageDialog(this, "체크인 및 체크아웃 날짜 설정을 해주세요");
			}
		}
		int price = 0;
		if (breakfast_y.isSelected() == true) {
			for (int j = 0; j <= 3; j++) {
				if (cus_head.getSelectedIndex() == j) {
					for (int i = 0; i <= 4; i++) {
						if (cus_head.getSelectedIndex() == i) {
							texts[3].setText((150000 + j * 50000) + ((i + 1) * 15000) + "");
							texts[3].setEnabled(false);
						}
					}
				}
			}
		}
		if (breakfast_n.isSelected() == true) {
			for (int i = 0; i <= 3; i++) {
				if (cus_head.getSelectedIndex() == i) {
					texts[3].setText(150000 + i * 50000 + "");
					texts[3].setEnabled(false);
				}
			}
		}
		if (breakfast_y.isSelected() == true) {
			if (obj == cus_head) {
				for (int j = 0; j <= 3; j++) {
					if (cus_head.getSelectedIndex() == j) {
						for (int i = 0; i <= 4; i++) {
							if (cus_head.getSelectedIndex() == i) {
								texts[3].setText((150000 + j * 50000) + ((i + 1) * 15000) + "");
								texts[3].setEnabled(false);
							}
						}
					}
				}
			}
		}
		
		if(obj == btns[3]) {
			texts[0].setText(" ");
			texts[0].setText("");
			room_type.setSelectedIndex(0);
			room_id.setSelectedItem("--------------");;
			texts[1].setText(" ");
			texts[1].setText("");
			texts[2].setText(" ");
			texts[2].setText("");
			breakfast_y.setSelected(false);
			breakfast_n.setSelected(false);
			cus_head.setSelectedIndex(0);
			texts[3].setText(" ");
			texts[3].setText("");
			texts[4].setText(" ");
			texts[4].setText("");
			texts[4].setEnabled(true);
			imagePanel.removeAll();
		}
	}

	Reservation_insert() {
		this.setLayout(new BorderLayout());

		makeJLabels();

		Color fontColor = new Color(116, 116, 116);
		Color lblFontColor = new Color(76, 76, 76);
		Color breakfastColor = new Color(255, 94, 0);

		mainFont = new Font("Sandoll 고딕 01 Light", Font.BOLD, 30);
		imageFont = new Font("Sandoll 고딕 01 Light", Font.BOLD, 23);
		font = new Font("Sandoll 고딕 01 Light", Font.PLAIN, 15);

		labels[0].setHorizontalAlignment(labels[0].CENTER);
		labels[0].setFont(mainFont);
		labels[0].setForeground(fontColor);
		labels[8].setHorizontalAlignment(labels[8].CENTER);
		labels[8].setFont(imageFont);
		labels[8].setForeground(fontColor);

		for (int i = 1; i <= 7; i++) {
			labels[i].setFont(font);
			labels[i].setForeground(lblFontColor);
		}

		labels[6].setForeground(breakfastColor);

		makeJTextField();
		makeJComboBox();
		makeJRadioButton();

		breakfast_y.setFont(font);
		breakfast_y.setForeground(lblFontColor);
		breakfast_n.setFont(font);
		breakfast_n.setForeground(lblFontColor);

		makeJButton();
		makeImageIcon();
		makeJPanel();

		panels[0].add(new JLabel(""));
		panels[1].add(labels[1]);
		panels[1].add(texts[0]);
		panels[2].add(labels[2]);
		panels[2].add(room_type);
		panels[2].add(room_id);
		panels[2].add(labels[9]);
		panels[3].add(labels[3]);
		panels[3].add(texts[1]);
		panels[3].add(btns[0]);
		panels[4].add(labels[4]);
		panels[4].add(texts[2]);
		panels[4].add(btns[1]);
		panels[5].add(labels[5]);
		panels[5].add(breakfast_y);
		panels[5].add(breakfast_n);
		panels[6].add(labels[6]);
		panels[6].add(new JLabel("      "));
		panels[6].add(cus_head);
		panels[6].add(labels[10]);
		panels[7].add(labels[7]);
		panels[7].add(texts[3]);
		panels[8].add(new JLabel(""));

		first_panel.add(panels[0]);
		first_panel.add(panels[1]);
		first_panel.add(panels[2]);
		first_panel.add(panels[3]);
		first_panel.add(panels[4]);
		first_panel.add(panels[5]);
		first_panel.add(panels[6]);
		first_panel.add(panels[7]);
		first_panel.add(panels[8]);

		first_panel.setBackground(Color.WHITE);

		second_northPanel.add(new JLabel(""));
		second_northPanel.add(labels[8]);
		second_northPanel.setBackground(Color.WHITE);

		second_panel.add("North", second_northPanel);
		second_panel.add("Center", imagePanel);
		second_panel.add("East", new JLabel("        "));
		second_panel.add("West", new JLabel("   "));

		JPanel btn_panel = new JPanel(new FlowLayout());
		btn_panel.add(btns[2]);
		btn_panel.add(btns[3]);
		btn_panel.setBackground(Color.WHITE);

		second_southPanel.add(btn_panel);
		second_southPanel.add(new JLabel(""));
		second_southPanel.setBackground(Color.WHITE);

		second_panel.add("South", second_southPanel);

		center_panel.add(first_panel);
		center_panel.add(second_panel);
		center_panel.setBackground(Color.WHITE);

		this.setBackground(Color.WHITE);

		northPanel.add(new JLabel(""));
		northPanel.add(labels[0]);

		northPanel.setBackground(Color.WHITE);

		this.add("North", northPanel);
		this.add("Center", center_panel);

		this.setSize(900, 650);
		this.setVisible(true);
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

//	public static void main(String[] args) {
//		new Reservation_insert("예약 정보 등록");
//	}

}
