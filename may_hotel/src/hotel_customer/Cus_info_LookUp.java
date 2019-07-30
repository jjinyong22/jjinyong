package hotel_customer;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Cus_info_LookUp extends JPanel implements ActionListener{
	
	JLabel title;
	JLabel id, pw;
	JTextField txt_id, txt_pw;
	JButton ok, cencle;
	JPanel[] panel;
	CardLayout card;
	JPanel card_pen;
	JPanel confirm;
	Cus_info_update update;
	customer_info customer;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if(o == ok) {
			customer_info info = Screen.getInfo();
			if(!info.getCus_id().equals(txt_id.getText())) {
				JOptionPane.showConfirmDialog(this, "아이디를 다시 입력하세요.");
			}else {
				if(!info.getCus_pw().equals(txt_pw.getText())) {
					JOptionPane.showConfirmDialog(this, "비밀번호를 다시 입력하세요.");
				}else {
					customer = Screen.getInfo();
					update.text[0].setText(customer.getCus_id());
					card.show(card_pen,"update");
				}
			}
		}else if(o == cencle){
			main.card.show(main.cus_pan, "home");
		}
	}

	void setting() {
		title = new JLabel("회원 정보 확인");
		id = new JLabel("I D : ");
		pw = new JLabel("P W : ");
		txt_id = new JTextField(10);
		txt_pw = new JTextField(10);
		txt_pw = new JPasswordField(10);
		ok = new JButton(new ImageIcon("image\\확인(1).png"));
		ok.setRolloverIcon(new ImageIcon("image\\확인(2).png"));
		ok.setBorderPainted(false);
		ok.setFocusable(false);
		ok.setContentAreaFilled(false);
		ok.addActionListener(this);
		cencle = new JButton(new ImageIcon("image\\돌아가기(1).png"));
		cencle.setRolloverIcon(new ImageIcon("image\\돌아가기(2).png"));
		cencle.setBorderPainted(false);
		cencle.setFocusable(false);
		cencle.setContentAreaFilled(false);
		cencle.addActionListener(this);
		panel = new JPanel[6];
		for(int i = 0; i < panel.length; i++) {
			panel[i] = new JPanel();
		}
		title.setFont(new Font("Serif",Font.BOLD,25));
		// End instance
		
		panel[1].add(title);
		JPanel login_pen = new JPanel(new GridLayout(2,1));
		JPanel id_pen = new JPanel(); JPanel pw_pen = new JPanel();
		id_pen.add(id); id_pen.add(txt_id);
		pw_pen.add(pw); pw_pen.add(txt_pw);
		login_pen.add(id_pen); login_pen.add(pw_pen);
		panel[2].add(login_pen);
		panel[3].add(ok); panel[3].add(cencle);
		
		confirm = new JPanel(new GridLayout(6,1));
		
		for(int i = 0; i < panel.length; i++) {
			panel[i].setBackground(Color.white);
			confirm.add(panel[i]);
		}
		
		
	}
	
	Cus_Main main;
	
	public Cus_info_LookUp(Cus_Main main) {
	//	this.update = new Cus_info_update(this);
		this.main = main;
		setting();
		card = new CardLayout();
		card_pen = new JPanel(card);
		update = new Cus_info_update(main);
		card_pen.add(confirm,"confirm");
		card_pen.add(update,"update");
		this.setLayout(new BorderLayout());
		this.add("Center",card_pen);
		this.setSize(800, 500);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		//new Cus_info_LookUp();
	}
}