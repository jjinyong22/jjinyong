package hotel_customer;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class Screen extends JFrame implements ActionListener{
	JMenu menu;
	
	public void removeMenubar() {
		this.setJMenuBar(null);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object o = arg0.getSource();
		CRUDprocess crud = new CRUDprocess();
		if(rbtns[0].isSelected()) {
			if(o == btns[0]) {
				if(text.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "아이디를 입력해 주세요.");
				}else {
					String id = text.getText();
					String pw = pass.getText();
					ManagerInfo info = new ManagerInfo();
					info.setMan_id(id);
					info.setMan_pw(pw);
					ManagerInfo info2 = new ManagerInfo();
					info2 = crud.selectManIdAndPw(info);
					if(info2 == null) {
						JOptionPane.showMessageDialog(this, "잘못된 아이디/패스워드 입니다.");
					}else {
						if(!text.getText().equals("master")) {
							main.card.show(main.cus_pan, "emp_home");
							card.show(card_pan, "emp_main");
							this.setJMenuBar(emp_main.mb);
							emp_main.emp_menu.setEnabled(false);
							emp_main.sales_menu.setEnabled(false);
						}else if(text.getText().equals("master")){
							emp_main.card.show(emp_main.empMain_pan, "emp_home");
							card.show(card_pan, "emp_main");
							this.setJMenuBar(emp_main.mb);
							emp_main.emp_menu.setEnabled(true);
							emp_main.sales_menu.setEnabled(true);
						}
					}
				}
			}else if(o == btns[1]) {
				text.setText("");
				pass.setText("");
			}else if(o == btns[2]) {
				text.setText("");
				pass.setText("");
				card.show(card_pan, "signup");
			}
		}
		if(rbtns[1].isSelected()) {
			if(o == btns[0]) {
				if(text.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "아이디를 입력해 주세요.");
				}else {
					String id = text.getText();
					info = crud.selectCustomerInfo(id);
					if(info == null) {
						JOptionPane.showMessageDialog(this, "잘못된 아이디/패스워드 입니다.");
					}else {
						String pw = info.getCus_pw();
						System.out.println(pw);
						String pw2 = new String(pass.getPassword());
						System.out.println(pw2);
						if(!pw.equals(pw2)) {
							JOptionPane.showMessageDialog(this,"잘못된 아이디/패스워드 입니다.");
						}else {
							main.card.show(main.cus_pan, "home");
							card.show(card_pan, "main");
							this.setJMenuBar(main.bar);
						}
					}
				}
			}else if(o == btns[1]) {
				text.setText("");
				pass.setText("");
			}else if(o == btns[2]) {
				text.setText("");
				pass.setText("");
				card.show(card_pan, "signup");
			}
		}
	}

	JPanel[] panels;
	public JTextField text; JButton[] btns;
	JLabel[] labels; JRadioButton[] rbtns; ButtonGroup btg; 
	Font MainFont; Font font,fonts; 
	ImageIcon[] image; public JPasswordField pass;
	public CardLayout card;
	JPanel login_pan;
	Cus_signUp signup_pan;
	public JPanel card_pan;
	Cus_Main main;
	hotel_manager.EmployeeInfo_main emp_main;
	static customer_info info;
	
	public static customer_info getInfo() {
		return info;
	}
	
	public Screen(String type) {
	 super(type);
	 
	 card = new CardLayout();
	 card_pan = new JPanel(card);
	 signup_pan = new Cus_signUp(this);
	 login_pan = new JPanel(new GridLayout(7,1));
	 
	 image = new ImageIcon[9];
	 for(int i = 0; i < image.length; i++) {
		 image[i] = new ImageIcon();
	 }
	 
	 panels = new JPanel[7];
	 for(int i = 0; i < panels.length; i++) {
		 panels[i] = new JPanel();
	 }
	 MainFont = new Font("Sandoll 고딕 01 Light", Font.BOLD, 33); 
	 font = new Font("Sandoll 고딕 01 Light", Font.BOLD, 22);  
	 fonts = new Font("Sandoll 고딕 01 Light", Font.BOLD, 18);
	 labels = new JLabel[3];
	 for(int i = 0; i < labels.length; i++) {
		 labels[i] = new JLabel();
	 }
	 labels[0] = new JLabel("HMS SYSTEM.ver1.0");
	 labels[0].setFont(MainFont);
	 labels[1] = new JLabel(" I D ");
	 labels[1].setFont(font);
	 labels[2] = new JLabel(" P W ");
	 labels[2].setFont(font);
	 
	 rbtns = new JRadioButton[2];
	 rbtns[0] = new JRadioButton("관리자 로그인");
	 rbtns[0].setFont(fonts);
	 rbtns[1] = new JRadioButton("회원 로그인");
	 rbtns[1].setFont(fonts);
	 btg = new ButtonGroup();
	 btg.add(rbtns[0]); btg.add(rbtns[1]);
	 
	 
	 text = new JTextField(20);
	 pass = new JPasswordField(20);
	 
	 btns = new JButton[3];
	 image[0] = new ImageIcon("image\\login.png");
	 image[1] = new ImageIcon("image\\login_press.png");
	 image[2] = new ImageIcon("image\\login_rollover.png");
	 image[3] = new ImageIcon("image\\cancel.png");
	 image[4] = new ImageIcon("image\\cancel_press.png");
	 image[5] = new ImageIcon("image\\cancel_rollover.png");
	 image[6] = new ImageIcon("image\\join.png");
	 image[7] = new ImageIcon("image\\join_press.png");
	 image[8] = new ImageIcon("image\\join_rollover.png");
	 
	 btns[0] = new JButton("로그인");
	 btns[0] = new JButton(image[0]);
	 btns[0].setPressedIcon(image[1]);
	 btns[0].setRolloverIcon(image[2]);
	 btns[0].setBorderPainted(false);
	 btns[0].setFocusPainted(false);
	 btns[0].setContentAreaFilled(false);
	 btns[0].addActionListener(this);
	 		 
	 btns[1] = new JButton("취소");
	 btns[1] = new JButton(image[3]);
	 btns[1].setPressedIcon(image[4]);
	 btns[1].setRolloverIcon(image[5]);
	 btns[1].setBorderPainted(false);
	 btns[1].setFocusPainted(false);
	 btns[1].setContentAreaFilled(false);
	 btns[1].addActionListener(this);
	 
	 btns[2] = new JButton("회원등록");
	 btns[2] = new JButton(image[6]);
	 btns[2].setPressedIcon(image[7]);
	 btns[2].setRolloverIcon(image[8]);
	 btns[2].setBorderPainted(false);
	 btns[2].setFocusPainted(false);
	 btns[2].setContentAreaFilled(false);
	 btns[2].addActionListener(this);
	 
	 panels[0].add(new JLabel(""));
	 panels[1].add(labels[0]);
	 panels[2].add(rbtns[0]); panels[2].add(rbtns[1]);
	 panels[3].add(labels[1]);	panels[3].add(text);
	 panels[4].add(labels[2]);	panels[4].add(pass);
	 panels[5].add(btns[0]); panels[5].add(btns[1]); panels[5].add(btns[2]);
	 panels[6].add(new JLabel(""));
	
	 for(int i = 0; i < panels.length; i++) {
		 login_pan.add(panels[i]);
	 } 	 
	 panels[0].setBackground(Color.WHITE);
	 panels[1].setBackground(Color.WHITE);
	 panels[2].setBackground(Color.WHITE);
	 panels[3].setBackground(Color.WHITE);
	 panels[4].setBackground(Color.WHITE);
	 panels[5].setBackground(Color.WHITE);
	 panels[6].setBackground(Color.WHITE);
	 main = new Cus_Main(this);
	 emp_main = new hotel_manager.EmployeeInfo_main(this);
	 card_pan.add(login_pan,"login");
	 card_pan.add(signup_pan,"signup");
	 card_pan.add(main, "main");
	 card_pan.add(emp_main,"emp_main");
	 this.add("Center",card_pan);
	 this.setSize(1000,650);
	 this.setVisible(true);
	 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 }
public static void main(String[] args) {
	Screen sc = new Screen("호텔 예약 관리 프로그램");
	}

}
