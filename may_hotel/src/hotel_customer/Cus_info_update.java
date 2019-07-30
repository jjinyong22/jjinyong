package hotel_customer;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Cus_info_update extends JPanel implements ActionListener{
	
	JPanel[] panel;
	JLabel title;
	JLabel[] label;
	String[] l_txt = {"I D","P W","P W 확인","성별","핸드폰 번호","이메일","체크된 부분은 필수요소 입니다."};
	JButton[] btn;
	JTextField[] text;
	JComboBox check, phone;
	JRadioButton M, F;
	ButtonGroup group;
	JLabel[] gen;
	JTextField txt_mail;
	Font f_1 = new Font("Sandoll", Font.BOLD, 20);
	Font f = new Font("Sandoll",Font.PLAIN,15);
	boolean flag;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		CRUDprocess crud = new CRUDprocess();
		if(o == btn[0]) {
			if(text[2].getText().equals("")) {
				JOptionPane.showMessageDialog(this, "비밀번호를 입력 해 주세요");
			}else {
			if(!flag) {
				JOptionPane.showMessageDialog(null, "비밀번호를 확인해주세요.");
			}else {
				customer_info info = new customer_info();
				customer = Screen.info;
				info.setCus_name(customer.getCus_name());
				info.setCus_id(text[0].getText());
				info.setCus_pw(text[2].getText());
				if (M.isSelected() == false && F.isSelected() == false) {
					JOptionPane.showMessageDialog(this, "성별을 선택해 주세요.");
				} else {
				if(M.isSelected()) {
					info.setCus_gen("M");
				}else if(F.isSelected()) {
					info.setCus_gen("F");
				}
				if(text[3].getText().equals("") && text[4].getText().equals("")) {
					JOptionPane.showMessageDialog(this, "핸드폰 번호를 입력 해 주세요.");
				}else {
				info.setPhone((String)phone.getSelectedItem()+"-"+text[3].getText()+"-"+text[4].getText());
				info.setEmail(text[5].getText()+"@"+(String)check.getSelectedItem());
				info.setCus_joindate(customer.getCus_joindate());
				
				int result = crud.updateCus_info(info);
				if(result > 0) {
					JOptionPane.showMessageDialog(null, "수정이 완료되었습니다.");
					main.card.show(main.cus_pan, "home");
					
				}else {
					JOptionPane.showMessageDialog(null, "오류가 발생하였습니다.");
				}
			}}
			}
			}
		}else if(o == btn[1]) {
			
		}else if(o == btn[2]) {
			if(text[2].getText().equals("")) {
				JOptionPane.showMessageDialog(this, "비밀번호를 입력 해 주세요");
			}else {
			if(!text[1].getText().equals(text[2].getText())) {
				JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다.");
			}else {
				flag = true;
				JOptionPane.showMessageDialog(this, "비밀번호가 일치합니다");
			}
		}
		}
	}
	
	void setfont() {
		title.setFont(f_1);
		for(int i = 0; i < label.length; i++) {
			label[i].setFont(f);
		}
		gen[0].setFont(f); gen[1].setFont(f);
	}
	
	void setting() {
		panel = new JPanel[9];
		for(int i = 0; i < panel.length; i++) {
			panel[i] = new JPanel();
		}//End panel
		
		title = new JLabel("회원 정보 수정");
		title.setFont(new Font("Serif",Font.BOLD,30));
		//End title
	
		label = new JLabel[7];
		for(int i = 0; i < label.length; i++) {
			label[i] = new JLabel(l_txt[i]);
		}
		//End label
		btn = new JButton[3];
		btn[0] = new JButton(new ImageIcon("image\\확인(1).png"));
		btn[0].setRolloverIcon(new ImageIcon("image\\확인(2).png"));
		btn[0].setBorderPainted(false);
		btn[0].setFocusable(false);
		btn[0].setContentAreaFilled(false);
		btn[1] = new JButton(new ImageIcon("image\\돌아가기(1).png"));
		btn[1].setRolloverIcon(new ImageIcon("image\\돌아가기(2).png"));
		btn[1].setBorderPainted(false);
		btn[1].setFocusable(false);
		btn[1].setContentAreaFilled(false);
		btn[2] = new JButton(new ImageIcon("image\\확인(파)1.png"));
		btn[2].setRolloverIcon(new ImageIcon("image\\확인(파)2.png"));
		btn[2].setBorderPainted(false);
		btn[2].setFocusable(false);
		btn[2].setContentAreaFilled(false);
		for(int i = 0; i < btn.length; i++) {
			btn[i].addActionListener(this);
		}
		//End button
		
		text = new JTextField[7];
		text[0] = new JTextField(10);
		text[1] = new JTextField(10);
		text[2] = new JTextField(10);
		text[3] = new JTextField(4);
		text[4] = new JTextField(4);
		text[5] = new JTextField(5);
		txt_mail = new JTextField(10);
		text[1] = new JPasswordField(10);
		text[2] = new JPasswordField(10);
		//End textField
		String[] email = {"naver.com","daum.com","gmail.com", "nate.com", "dreamWiz.com"};
		check = new JComboBox();
		for(int i = 0; i < email.length; i++) {
			check.addItem(email[i]);
		}
		
		String[] num = {"010","011","016","017"};
		phone = new JComboBox(num);
		//End ComboBox
		
		group = new ButtonGroup();
		M = new JRadioButton();
		F = new JRadioButton();
		group.add(M); group.add(F);
		gen = new JLabel[2];
		gen[0] = new JLabel("남자"); gen[1] = new JLabel("여자");
		//End gender
		
	}
	void setPanel() {
		panel[0].add(title);
		
		panel[1].add(new JLabel("*"));
		panel[1].add(label[0]);
		panel[1].add(new JLabel("	"));
		panel[1].add(text[0]);
		panel[1].add(btn[0]);
		
		panel[2].add(new JLabel("     *"));
		panel[2].add(label[1]);
		panel[2].add(new JLabel("	"));
		panel[2].add(text[1]);
		
		panel[3].add(new JLabel("*"));
		panel[3].add(label[2]);
		panel[3].add(new JLabel("	"));
		panel[3].add(text[2]);
		panel[3].add(btn[2]);
		
		panel[4].add(new JLabel("*"));
		panel[4].add(label[3]);
		panel[4].add(new JLabel("	"));
		panel[4].add(M);
		panel[4].add(gen[0]);
		panel[4].add(new JLabel("   "));
		panel[4].add(F);
		panel[4].add(gen[1]);
		
		panel[5].add(new JLabel("*"));
		panel[5].add(label[4]);
		panel[5].add(new JLabel("	"));
		panel[5].add(phone);
		panel[5].add(new JLabel(" - "));
		panel[5].add(text[3]);
		panel[5].add(new JLabel(" - "));
		panel[5].add(text[4]);
		
		panel[6].add(label[5]);
		panel[6].add(new JLabel("	"));
		panel[6].add(text[5]);
		panel[6].add(new JLabel("@"));
		panel[6].add(check);
		
		panel[7].add(label[6]);
		
		panel[8].add(btn[0]);
		panel[8].add(new JLabel("  "));
		panel[8].add(btn[1]);
		
		for(int i = 0; i < panel.length; i++) {
			panel[i].setBackground(Color.white);
			this.add(panel[i]);
		}
	}
	Cus_Main main;
	customer_info customer;
	public Cus_info_update(Cus_Main main){
		this.main = main;
		this.setLayout(new GridLayout(9,1));
		setting();
		setPanel();
		
		text[0].enable(false);
		setfont();
		this.setSize(800,500);
		this.setVisible(true);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		//new Cus_info_update();
	}
}
