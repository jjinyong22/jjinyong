package hotel_manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class EmployeeInfo_insert extends JPanel implements ActionListener {
	boolean flag;
	boolean flag2;
	public void actionPerformed(ActionEvent arg0) {
		Object ob = arg0.getSource();
		CRUDprocess_emp crud = new CRUDprocess_emp();
		if(ob == btns1) {
			if(text[0].getText().equals("")) {
				JOptionPane.showMessageDialog(this, "아이디를 입력 해 주세요.");
			}else {
				EmployeeInfo info = crud.selectmanager_in(text[0].getText());
					if(info == null) {
						JOptionPane.showMessageDialog(this,"사용가능한 아이디 입니다." );
					flag = true;
					}else {
						JOptionPane.showMessageDialog(this, "중복된 아이디 입니다.");
					}
			}
		}
		if(ob == btns2) {			
			if(text1.getText().equals(text2.getText())){
				flag2 = true;
				JOptionPane.showMessageDialog(this, "패스월드가 일치합니다.");
			}else {
				JOptionPane.showMessageDialog(this, "패스워드가 일치하지 않습니다");
			}
		}
		if(ob == btns3) {
			if(text[0].getText().equals("")) {
				JOptionPane.showMessageDialog(this, "아이디를 입력 해 주세요.");	
				}else {
				if(flag == false) {
					JOptionPane.showMessageDialog(this, "아이디 중복체크를 해 주세요.");
				}else{
					if(text2.getText().equals("")) {
					JOptionPane.showMessageDialog(this,"비밀번호를 입력 해 주세요.");
				}else {
					if(flag2 == false) {
						JOptionPane.showMessageDialog(this, "비밀번호 확인 해 주세요.");
					}else{
				if(text[1].getText().equals("")) {
					JOptionPane.showMessageDialog(this, "이름을 입력 해 주세요.");
				}else {
					if(text[2].getText().equals("") && text[3].getText().equals("")) {
						JOptionPane.showMessageDialog(this, "핸드폰 번호를 입력 해 주세요.");
					}else {
						EmployeeInfo insert = new EmployeeInfo();
					insert.setMan_id(text[0].getText());insert.setMan_pw(text2.getText());
					insert.setMan_name(text[1].getText()); 
					if((int)cho2.getSelectedItem() >= 10 && (int)cho3.getSelectedItem() >= 10) {
						insert.setMan_hiredate(cho1.getSelectedItem()+"-" +cho2.getSelectedItem()+"-"+cho3.getSelectedItem());
					}else if((int)cho2.getSelectedItem() < 10 && (int)cho3.getSelectedItem() >= 10){
						insert.setMan_hiredate(cho1.getSelectedItem()+"-0" +cho2.getSelectedItem()+"-"+cho3.getSelectedItem());
					}else if((int)cho2.getSelectedItem() >= 10 && (int)cho3.getSelectedItem() < 10){
						insert.setMan_hiredate(cho1.getSelectedItem()+"-" +cho2.getSelectedItem()+"-0"+cho3.getSelectedItem());
					}else {
						insert.setMan_hiredate(cho1.getSelectedItem()+"-0" +cho2.getSelectedItem()+"-0"+cho3.getSelectedItem());
					}
					insert.setMan_phone(cho4.getSelectedItem()+ "-" + text[2].getText()+ "-" + text[3].getText());
					insert.setMan_dept(cho5.getSelectedItem()+"");
					int result = crud.insertmanager(insert);
					if(result > 0) {
						JOptionPane.showMessageDialog(this, "직원등록이 완료되었습니다.");
						text[0].setText("");
						text[1].setText("");
						text[2].setText("");
						text[3].setText("");
						text1.setText("");
						text2.setText("");
						cho1.setSelectedIndex(0);
						cho2.setSelectedIndex(0);
						cho3.setSelectedIndex(0);
						cho4.setSelectedIndex(0);
						cho5.setSelectedIndex(0);
					}else JOptionPane.showMessageDialog(this, "직원등록 과정에서 문제가 발생했습니다.");
					}
				}
					}
				}
				}
				}
			}
		if(ob == btns4){
			text[0].setText("");
			text[1].setText("");
			text[2].setText("");
			text[3].setText("");
			text1.setText("");
			text2.setText("");
			cho1.setSelectedIndex(0);
			cho2.setSelectedIndex(0);
			cho3.setSelectedIndex(0);
			cho4.setSelectedIndex(0);
			cho5.setSelectedIndex(0);
		}
		if(ob == cho2) {
			cho3.removeAllItems();
			switch((int)cho2.getSelectedItem()) {
			case 1 : 
			case 3 :
			case 5 :
			case 7 :
			case 8 :
			case 10 :
			case 12 : for(int i = 1; i <= 31; i++) {
				cho3.addItem(i);
			} break;
			case 2 : for(int i = 1; i <= 28; i++) {
				cho3.addItem(i);
			} break;
			default : for(int i = 1; i <= 30; i++) {
				cho3.addItem(i);
			}
			}	
		}
		
		
	}

	JPanel[] panels; JLabel[] labels; JTextField[] text; JPasswordField text1,text2;
	JComboBox cho1,cho2,cho3,cho4,cho5; 
	JButton btns1,btns2,btns3,btns4; ImageIcon[] image;
	
	String[] salnum = {"010","011","016","017","018","019"};
	String[] division = {"홍보부", "고객 관리부", "객실 관리부", "영업부", "경영부"};
	
	JPanel northPanel, centerPanel, southPanel;
	
	public EmployeeInfo_insert() {
//		super(str);
		
		panels = new JPanel[10];
		for(int i = 0; i < panels.length; i++) {
			panels[i] = new JPanel();
			panels[i].setBackground(Color.WHITE);
		}
		
		northPanel = new JPanel(new GridLayout(3,1));
		northPanel.setBackground(Color.WHITE);
		
		centerPanel = new JPanel(new GridLayout(4,1));
		centerPanel.setBackground(Color.WHITE);
		
		southPanel = new JPanel(new GridLayout(4,1));
		southPanel.setBackground(Color.WHITE);
		
		labels = new JLabel[8];
		for(int i = 0; i < labels.length; i++) {
			labels[i] = new JLabel();
		}
		image = new ImageIcon[12];
		for(int i = 0; i < image.length; i++) {
			image[i] = new ImageIcon();
		}// 아이디, 패스워드, 등록, 취소
		image[0] = new ImageIcon("image//id_check.png");
		image[1] = new ImageIcon("image//id_check_press.png");
		image[2] = new ImageIcon("image//id_check_rollover.png");
		image[3] = new ImageIcon("image//pw_check.png");
		image[4] = new ImageIcon("image//pw_check_press.png");
		image[5] = new ImageIcon("image//pw_check_rollover.png");
		image[6] = new ImageIcon("image//insert.png");
		image[7] = new ImageIcon("image//insert_press.png");
		image[8] = new ImageIcon("image//insert_rollover.png");
		image[9] = new ImageIcon("image//cancel.png");
		image[10] = new ImageIcon("image//cancel_press.png");
		image[11] = new ImageIcon("image//cancel_rollover.png");
		
		labels[0] = new JLabel("직원 정보 등록");
		labels[1] = new JLabel("아이디 ");
		labels[2] = new JLabel("패스워드 ");
		labels[3] = new JLabel("패스워드 확인 ");
		labels[4] = new JLabel("이름 ");
		labels[5] = new JLabel("입사일 ");
		labels[6] = new JLabel("전화번호 ");
		labels[7] = new JLabel("소속부서 ");
	
		text = new JTextField[4];
		text[0] = new JTextField(20);
		text[1] = new JTextField(20);
		text[2] = new JTextField(5);
		text[3] = new JTextField(5);
		text1 = new JPasswordField(20);
		text2 = new JPasswordField(20);
		
		btns1 = new JButton(" 아이디 확인 ");
		btns1 = new JButton(image[0]);
		btns1.setPressedIcon(image[1]);
		btns1.setRolloverIcon(image[2]);
		btns1.setBorderPainted(false);
		btns1.setFocusPainted(false);
		btns1.setContentAreaFilled(false);
		btns1.addActionListener(this);
		
		btns2 = new JButton(" 패스워드 확인 ");
		btns2 = new JButton(image[3]);
		btns2.setPressedIcon(image[4]);
		btns2.setRolloverIcon(image[5]);
		btns2.setBorderPainted(false);
		btns2.setFocusPainted(false);
		btns2.setContentAreaFilled(false);
		btns2.addActionListener(this);
		
		btns3 = new JButton(" 등 록 ");
		btns3 = new JButton(image[6]);
		btns3.setPressedIcon(image[7]);
		btns3.setRolloverIcon(image[8]);
		btns3.setBorderPainted(false);
		btns3.setFocusPainted(false);
		btns3.setContentAreaFilled(false);
		btns3.addActionListener(this);
		
		btns4 = new JButton(" 취 소 ");
		btns4 = new JButton(image[9]);
		btns4.setPressedIcon(image[10]);
		btns4.setRolloverIcon(image[11]);
		btns4.setBorderPainted(false);
		btns4.setFocusPainted(false);
		btns4.setContentAreaFilled(false);
		btns4.addActionListener(this);
		
		cho1 = new JComboBox();
		for(int i = 2019; i >= 1980; i--) {
			cho1.addItem(i);
		}
		cho2 = new JComboBox();
		for(int i = 1; i <= 12; i++) {
			cho2.addItem(i);
			cho2.addActionListener(this);
		}
		cho3 = new JComboBox();
		for(int i = 1; i <= 31; i++) {
			cho3.addItem(i);
		}
		cho4 = new JComboBox();
		for(int i = 0; i < salnum.length; i++) {
			cho4.addItem(salnum[i]);
		}
		cho5 = new JComboBox();
		for(int i = 0; i <division.length; i++) {
			cho5.addItem(division[i]);
		}
		
		labels[0].setHorizontalAlignment(labels[0].CENTER);
		
		Font font = new Font("Sandoll 고딕 01 Light", Font.BOLD, 30);
		Color fontColor = new Color(116,116,116);
		
		labels[0].setFont(font);
		labels[0].setForeground(fontColor);
		
		northPanel.add(new JLabel(""));
		northPanel.add(labels[0]);
		northPanel.add(new JLabel(""));
		
		Font lblFont = new Font("Sandoll 고딕 01 Light", Font.PLAIN, 15);
		Color lblFontColor = new Color(76,76,76);
		
		for(int i = 1; i < labels.length; i++) {
			labels[i].setFont(lblFont);
			labels[i].setForeground(lblFontColor);
		}
		
		panels[0].add(labels[1]); panels[0].add(text[0]); panels[0].add(btns1);
		
		panels[1].add(labels[2]); panels[1].add(text1);
		
		panels[2].add(labels[3]); panels[2].add(text2);	panels[2].add(btns2);
		
		panels[3].add(labels[4]); panels[3].add(text[1]);
		
		for(int i = 0; i <= 3; i++) {
			centerPanel.add(panels[i]);
		}
		
		panels[4].add(labels[5]); panels[4].add(cho1); panels[4].add(new JLabel("년")); panels[4].add(cho2); panels[4].add(new JLabel("월")); panels[4].add(cho3); panels[4].add(new JLabel("일"));
		
		panels[5].add(labels[6]); panels[5].add(cho4); panels[5].add(new JLabel("-")); panels[5].add(text[2]); panels[5].add(new JLabel("-")); panels[5].add(text[3]);
		
		panels[6].add(labels[7]); panels[6].add(cho5); 
		
		panels[7].add(btns3); panels[7].add(btns4);
		
		for(int i = 4; i <= 7; i++) {
			southPanel.add(panels[i]);
		}
		
		this.setLayout(new BorderLayout());
	
		this.add("North",northPanel);
		this.add("Center",centerPanel);
		this.add("South",southPanel);
		
		this.setSize(800,500);
		this.setVisible(true);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
		
//	public static void main(String[] args) {
//		EmployeeInfo_insert sm = new EmployeeInfo_insert("직원 등록 화면");
//
//	}

}
