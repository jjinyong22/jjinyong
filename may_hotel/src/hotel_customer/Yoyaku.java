package hotel_customer;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

class BackImage extends JPanel {

	Image ima;
	Yoyaku yo;

	BackImage(String path, Yoyaku yo) {
		this.yo = yo;
		ima = Toolkit.getDefaultToolkit().getImage(path);
	}

	@Override
	public void paint(Graphics arg0) {
		arg0.drawImage(ima, 0, 0, this.getWidth(), this.getHeight(), this);

	}
}

public class Yoyaku extends JPanel implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		CRUDprocess crud = new CRUDprocess();
		Date today = new Date();
		int price = 0;
		if (rad[0].isSelected() == true) {
			for (int j = 0; j <= 3; j++) {
				if (box1.getSelectedIndex() == j) {
					for (int i = 0; i <= 4; i++) {
						if (cus_head.getSelectedIndex() == i) {
							txt[3].setText((150000 + j * 50000) + ((i + 1) * 15000) + "");
						}
					}
				}
			}
		}
		if (rad[1].isSelected() == true) {
			for (int i = 0; i <= 3; i++) {
				if (box1.getSelectedIndex() == i) {
					txt[3].setText(150000 + i * 50000 + "");
				}
			}
		}
		if (rad[0].isSelected() == true) {
			if (obj == cus_head) {
				for (int j = 0; j <= 3; j++) {
					if (box1.getSelectedIndex() == j) {
						for (int i = 0; i <= 4; i++) {
							if (cus_head.getSelectedIndex() == i) {
								txt[3].setText((150000 + j * 50000) + ((i + 1) * 15000) + "");
							}
						}
					}
				}
			}
		}
		if (obj == box1) {
			if (box1.getSelectedIndex() == 0) {
				box2.removeAllItems();
				for (int i = 0; i < flo_txt.length; i++) {
					box2.addItem(flo_txt[i]);
				}
				imagePanel.revalidate();
				imagePanel.repaint();
				imagePanel.add("Center", image_panel[0]);
				txt[3].setText("150000");
				if (rad[0].isSelected() == true) {
					for (int i = 0; i <= 4; i++) {
						if (cus_head.getSelectedIndex() == i) {
							txt[3].setText(150000 + (i + 1) * 15000 + "");
						}
					}
				}
			}
			if (box1.getSelectedIndex() == 1) {
				box2.removeAllItems();
				for (int i = 0; i < 8; i++) {
					box2.addItem(flo_txt2[i]);
				}
				imagePanel.revalidate();
				imagePanel.repaint();
				imagePanel.add("Center", image_panel[1]);
				txt[3].setText("200000");
				if (rad[0].isSelected() == true) {
					for (int i = 0; i <= 4; i++) {
						if (cus_head.getSelectedIndex() == i) {
							txt[3].setText(200000 + (i + 1) * 15000 + "");
						}
					}
				}
			}
			if (box1.getSelectedIndex() == 2) {
				box2.removeAllItems();
				for (int i = 0; i < 8; i++) {
					box2.addItem(flo_txt3[i]);
				}
				imagePanel.revalidate();
				imagePanel.repaint();
				imagePanel.add("Center", image_panel[2]);
				txt[3].setText("250000");
				if (rad[0].isSelected() == true) {
					for (int i = 0; i <= 4; i++) {
						if (cus_head.getSelectedIndex() == i) {
							txt[3].setText(250000 + (i + 1) * 15000 + "");
						}
					}
				}

			}
			if (box1.getSelectedIndex() == 3) {
				box2.removeAllItems();
				for (int i = 0; i < 8; i++) {
					box2.addItem(flo_txt4[i]);
				}
				imagePanel.revalidate();
				imagePanel.repaint();
				imagePanel.add("Center", image_panel[3]);
				txt[3].setText("300000");
				if (rad[0].isSelected() == true) {
					for (int i = 0; i <= 4; i++) {
						if (cus_head.getSelectedIndex() == i) {
							txt[3].setText(300000 + (i + 1) * 15000 + "");
						}
					}
				}
			}
		} else if (obj == bts) {
			new Reservation_calendar_checkIn(this);
		} else if (obj == bts2) {
			new Reservation_calendar_checkOut(this);
		}
		if (obj == bts3) {
			if(flag) {
			int res_num = crud.max_yoya() + 1;// (int)Math.random()*15000;

			String res_id = txt[0].getText();// �����̵�
			String room = (String) box2.getSelectedItem();// ���ఴ���޺��ڽ�
			int res_room = Integer.valueOf(room);// ȣ��

			//SimpleDateFormat test = new SimpleDateFormat("yyyy-mm-dd");
			Calendar cal = Calendar.getInstance();
			String res_date = String.valueOf(cal.get(cal.YEAR)) + "-" + String.valueOf(cal.get(cal.MONTH)+1) + 
					"-" + String.valueOf(cal.get(cal.DATE));
			//String res_date = test.format(today);// ���೯¥
			String res_checkin = txt[1].getText();// üũ�� ��¥
			if (res_checkin.equals("")) {
				JOptionPane.showMessageDialog(this, "체크인 날짜를 선택 해 주세요");
			}else {
			String res_checkout = txt[2].getText();// üũ�ƿ� ��¥
			if (res_checkout.equals("")) {
				JOptionPane.showMessageDialog(this, "체크아웃 날짜를 선택 해 주세요");
			}
			else {
			String res_breakfast = ""; // ���� ������ ����

			if (rad[0].isSelected() == true) {// ������ư ���� ������
				res_breakfast = "Y";
			} else if (rad[1].isSelected() == true) {
				res_breakfast = "N";
			}

			int res_payment = Integer.parseInt(txt[3].getText()); // ����

			String head = (String) cus_head.getSelectedItem();
			int res_head = Integer.valueOf(head);// �ο���

			String res_state = "예약완료";
			String res_canceldate = "null";
			Yoya_batis yo_ba = new Yoya_batis();
			yo_ba.setRes_num(res_num);
			yo_ba.setRes_id(res_id);
			yo_ba.setRes_room(res_room);
			yo_ba.setRes_date(res_date);
			yo_ba.setRes_checkin(res_checkin);
			yo_ba.setRes_checkout(res_checkout);
			yo_ba.setRes_breakfast(res_breakfast);
			yo_ba.setRes_payment(res_payment);
			yo_ba.setRes_head(res_head);
			yo_ba.setRes_state(res_state);
			yo_ba.setRes_canceldate(res_canceldate);
			int r = crud.insertyoya(yo_ba);
			if (r > 0) {
				JOptionPane.showMessageDialog(this, "예약이 완료 되엇습니다.");
				txt[1].setText("");
				box1.setSelectedItem("STANDARD");
				txt[2].setText("");
				txt[3].setText("");
				cus_head.setSelectedItem("1");
				rad[1].setSelected(true);
				txt[3].setText("");
				imagePanel.revalidate();
				imagePanel.repaint();
				imagePanel.add("Center", image_panel[0]);
			} else {
				JOptionPane.showMessageDialog(this, "발생발생 오류가 발생");
			}
		}
		}
			}else {
				JOptionPane.showMessageDialog(this, "예약 날짜를 다시 선택해 주세요.");
			}
		}
		if (obj == bts4) {
			txt[1].setText("");
			box1.setSelectedItem("STANDARD");
			cus_head.setSelectedItem("1");
			txt[2].setText("");
			txt[3].setText("");
			rad[1].setSelected(true);
			imagePanel.revalidate();
			imagePanel.repaint();
			imagePanel.add("Center", image_panel[0]);
			
		}
	}

	void makeMenubar() {
		bar = new JMenuBar();
		menu = new JMenu("예약안내");
		menu2 = new JMenu("객실안내");
		menu3 = new JMenu("이벤트안내");
		menu4 = new JMenu("시스템종료");
		bar_yo = new JMenuItem("예약조회");
		bar_yo2 = new JMenuItem("예약하기");
		bar_yo3 = new JMenuItem("예약변경");
		bar_yo4 = new JMenuItem("예약취소");
		bar_gae = new JMenuItem("STANDARD");
		bar_gae2 = new JMenuItem("SUPERIOR");
		bar_gae3 = new JMenuItem("DELUXE");
		bar_gae4 = new JMenuItem("EXECUTIVE");
		bar_ev = new JMenuItem("이벤트조회");
		bar_exit = new JMenuItem("종료하기");
		menu.add(bar_yo);
		menu.add(bar_yo2);
		menu.add(bar_yo3);
		menu.add(bar_yo4);
		menu2.add(bar_gae);
		menu2.add(bar_gae2);
		menu2.add(bar_gae3);
		menu2.add(bar_gae4);
		menu3.add(bar_ev);
		menu4.add(bar_exit);
		bar.add(menu);
		bar.add(menu2);
		bar.add(menu3);
		bar.add(menu4);
		// this.setJMenuBar(bar);
	};

	JMenuBar bar;
	JMenuItem bar_yo, bar_yo2, bar_yo3, bar_yo4, bar_gae, bar_gae2, bar_gae3, bar_gae4, bar_ev, bar_exit;
	JMenu menu, menu2, menu3, menu4;
	JPanel[] pan;
	JPanel WestPanel, EastPanel;
	JPanel[] image_panel;
	JPanel imagePanel;
	JButton bts, bts2, bts3, bts4;
	JLabel[] lab;
	JRadioButton[] rad;
	ButtonGroup group;
	JTextField[] txt;
	JComboBox cus_head;
	JComboBox box1, box2;
	Yoya_henncou yo_hen;
	boolean flag;
	String[] gaes = { "STANDARD", "SUPERIOR", "DELUXE", "EXECUTIVE" };

	String[] flo_txt = { "1001", "1002", "1003", "1004", "2001", "2002", "2003", "2004", };
	String[] flo_txt2 = { "3001", "3002", "3003", "3004", "4001", "4002", "4003", "4004" };
	String[] flo_txt3 = { "5001", "5002", "5003", "5004", "6001", "6002", "6003", "6004" };
	String[] flo_txt4 = { "7001", "7002", "7003", "7004", "8001", "8002", "8003", "8004" };
	String[] yotxt = {"예약 정보 등록", "고객 아이디", "객실 선택", "체크인 날짜", "체크아웃 날짜", "조식 선택", "예약 인원", "총 예약금액", "객실 이미지" };
	ImageIcon road_icon, road_press, road_rollover, road_icon2, road_press2, road_rollover2, road_icon3, road_press3,
			road_rollover3, road_icon4, road_press4, road_rollover4;

	void makeiamge() {
		road_icon = new ImageIcon("image\\선택1.png");
		road_press = new ImageIcon("image\\선택2.png");
		road_rollover = new ImageIcon("image\\선택3.png");

		road_icon2 = new ImageIcon("image\\선택1.png");
		road_press2 = new ImageIcon("image\\선택2.png");
		road_rollover2 = new ImageIcon("image\\선택3.png");

		road_icon3 = new ImageIcon("image\\예약1.png");
		road_press3 = new ImageIcon("image\\예약2.png");
		road_rollover3 = new ImageIcon("image\\예약3.png");

		road_icon4 = new ImageIcon("image\\취소1.png");
		road_press4 = new ImageIcon("image\\취소2.png");
		road_rollover4 = new ImageIcon("image\\취소3.png");

	}

	void makePanel() {
		image_panel = new JPanel[4];
		for (int i = 0; i < image_panel.length; i++) {
			image_panel[0] = new BackImage("image\\room1.jpg", this);
			image_panel[1] = new BackImage("image\\room2.jpg", this);
			image_panel[2] = new BackImage("image\\room3.jpg", this);
			image_panel[3] = new BackImage("image\\room4.jpg", this);
		}
		imagePanel = new JPanel();
		imagePanel.setLayout(new BorderLayout());
		WestPanel = new JPanel();
		EastPanel = new JPanel();
		WestPanel.setLayout(new GridLayout(8, 1));
		EastPanel.setLayout(new BorderLayout());
		pan = new JPanel[11];
		for (int i = 0; i < pan.length; i++) {
			pan[i] = new JPanel();
		}
		this.add("North", pan[0]);
		WestPanel.add(pan[1]);
		WestPanel.add(pan[2]);
		WestPanel.add(pan[3]);
		WestPanel.add(pan[4]);
		WestPanel.add(pan[5]);
		WestPanel.add(pan[6]);
		WestPanel.add(pan[7]);
		this.add("West", WestPanel);
		EastPanel.add("North", pan[8]);
		EastPanel.add("Center", pan[9]);
		EastPanel.add("South", pan[10]);
		pan[9].setLayout(new BorderLayout());
		pan[9].add("Center", imagePanel);
		this.add("Center", EastPanel);
	}

	void makeButton() {
		bts = new JButton(road_icon);
		bts.setPressedIcon(road_press);
		bts.setRolloverIcon(road_rollover);
		bts.setContentAreaFilled(false);
		bts.setBorderPainted(false);
		bts.setFocusPainted(false);
		bts.addActionListener(this);

		bts2 = new JButton(road_icon2);
		bts2.setPressedIcon(road_press2);
		bts2.setRolloverIcon(road_rollover2);
		bts2.setContentAreaFilled(false);
		bts2.setBorderPainted(false);
		bts2.setFocusPainted(false);
		bts2.addActionListener(this);

		bts3 = new JButton(road_icon3);
		bts3.setPressedIcon(road_press3);
		bts3.setRolloverIcon(road_rollover3);
		bts3.setContentAreaFilled(false);
		bts3.setBorderPainted(false);
		bts3.setFocusPainted(false);
		bts3.addActionListener(this);

		bts4 = new JButton(road_icon4);
		bts4.setPressedIcon(road_press4);
		bts4.setRolloverIcon(road_rollover4);
		bts4.setContentAreaFilled(false);
		bts4.setBorderPainted(false);
		bts4.setFocusPainted(false);
		bts4.addActionListener(this);

		pan[3].add(bts);
		pan[4].add(bts2);
		pan[10].add("South", bts3);
		pan[10].add("South", bts4);
	}

	void makejLabel() {
		lab = new JLabel[9];
		for (int i = 0; i < lab.length; i++) {
			lab[i] = new JLabel(yotxt[i]);
		}
		pan[0].add(lab[0]);
		for (int i = 1; i <= 7; i++) {
			pan[i].add(lab[i]);
		}
		pan[8].add("North", lab[8]);
	}

	void makeJTextfield() {
		txt = new JTextField[4];
		for (int i = 0; i < txt.length; i++) {
			txt[i] = new JTextField(10);
		}
		pan[1].add(txt[0]);
		pan[3].add(txt[1]);
		pan[4].add(txt[2]);
		pan[7].add(txt[3]);
	}

	void makebts_combax() {
		JLabel ho = new JLabel("호");
		rad = new JRadioButton[2];
		rad[0] = new JRadioButton("포함");
		rad[1] = new JRadioButton("미포함");
		rad[1].setSelected(true);
		rad[0].addActionListener(this);
		rad[1].addActionListener(this);
		group = new ButtonGroup();
		group.add(rad[0]);
		group.add(rad[1]);

		box1 = new JComboBox();
		for (int i = 0; i < gaes.length; i++) {
			box1.addItem(gaes[i]);
		}
		box1.addActionListener(this);
		box2 = new JComboBox();
		for(int i = 0;i < flo_txt.length;i++) {
		box2.addItem(flo_txt[i]);
		}
		cus_head = new JComboBox();
		for (int i = 1; i <= 5; i++) {
			cus_head.addItem(i + "");
		}
		cus_head.addActionListener(this);
		pan[2].add(box1);
		pan[2].add(box2);
		pan[2].add(ho);
		pan[5].add(rad[0]);
		pan[5].add(rad[1]);
		// pan[2].add();
	}

	Yoyaku() {
		// super();
		this.setLayout(new BorderLayout());
		makeMenubar();
		makePanel();
		makejLabel();
		makebts_combax();
		makeJTextfield();
		makeiamge();
		makeButton();
		JLabel won = new JLabel("원");
		pan[7].add(won);
		pan[6].add(cus_head);
		JLabel money = new JLabel("* 1인당 15,000원");
		pan[5].add(money);
		JLabel hito = new JLabel("명");
		pan[6].add(hito);
		this.setSize(800, 500);
		this.setVisible(true);
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		// new Yoyaku();
	}

}
