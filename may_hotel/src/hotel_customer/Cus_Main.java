package hotel_customer;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Cus_Main extends JPanel implements ActionListener {

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		CRUDprocess crud = new CRUDprocess();
		if (obj == bar_yo) {
			this.card.show(cus_pan, "yoya1");
		}
		if (obj == bar_yo2) {
			customer_info info = Screen.getInfo();
			yo_2.txt[0].setText(info.getCus_id());
			yo_2.txt[0].enable(false);
			this.card.show(cus_pan, "yoya2");
		}
		if (obj == bar_yo3) {
			this.card.show(cus_pan, "yoya3");
		}
		if (obj == bar_yo4) {
			this.card.show(cus_pan, "yoya4");
		}
		if(obj == bar_ev) {
			//eve.setEvent();
			this.card.show(cus_pan, "event");
		}
		if(obj == bar_home) {
			this.card.show(cus_pan, "home");
		}
		if(obj == bar_exit) {
			int i = JOptionPane.showOptionDialog(this, "종료 하시겠습니까?", "종료창", JOptionPane.YES_NO_OPTION,
					JOptionPane.INFORMATION_MESSAGE, null, yes_no, "아니오");
			if(i == 0) {
			this.card.show(cus_pan,"exit");
			Timer timer = new Timer();
			 TimerTask t_task = new TimerTask() {
				 public void run() {
					 System.exit(0);
				 }
			 };
			timer.schedule(t_task, 1000);
		}
			}
		if(obj == bar_gae) {
			card.show(cus_pan, "standard");
		}
		if(obj == bar_gae2) {
			card.show(cus_pan, "superrior");
		}	
		if(obj == bar_gae3) {
			card.show(cus_pan, "deluxe");
		}	
		if(obj == bar_gae4) {
			card.show(cus_pan, "excutive");
		}
		if(obj == bar_Modified) {
			card.show(cus_pan, "info");
		}
		if(obj == bar_logout) {
			int i = JOptionPane.showOptionDialog(this, "로그아웃 하시겠습니까?", "로그아웃 창", JOptionPane.YES_NO_OPTION,
					JOptionPane.INFORMATION_MESSAGE, null, yes_no, "아니오");
			if( i == 0) {
			info.card.show(info.card_pen, "confirm");
			screen.removeMenubar();
			screen.card.show(screen.card_pan, "login");
			screen.text.setText("");
			screen.pass.setText("");
			
			}
		}
	}
	
	void makeMenubar() {
		bar = new JMenuBar();
		menu = new JMenu("예약안내");
		menu2 = new JMenu("객실안내");
		menu3 = new JMenu("이벤트안내");
		menu4 = new JMenu("시스템");
		menu5 = new JMenu("마이페이지");
		
		bar_yo = new JMenuItem("예약조회");
		bar_yo2 = new JMenuItem("예약하기");
		bar_yo3 = new JMenuItem("예약변경");
		bar_yo4 = new JMenuItem("예약취소");
		bar_gae = new JMenuItem("STANDARD");
		bar_gae2 = new JMenuItem("SUPERIOR");
		bar_gae3 = new JMenuItem("DELUXE");
		bar_gae4 = new JMenuItem("EXECUTIVE");
		bar_ev = new JMenuItem("이벤트안내");
		bar_home = new JMenuItem("메인으로");
		bar_exit = new JMenuItem("종료하기");
		bar_logout = new JMenuItem("로그아웃");
		bar_Modified = new JMenuItem("정보수정");
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
		menu4.add(bar_logout);
		menu4.add(bar_home);
		menu5.add(bar_Modified);
		bar.add(menu);
		bar.add(menu2);
		bar.add(menu3);
		bar.add(menu5);
		bar.add(menu4);
		//screen.setJMenuBar(bar);
	};

	JMenuBar bar;
	JMenuItem bar_yo, bar_yo2, bar_yo3, bar_yo4,
	bar_gae, bar_gae2, bar_gae3, bar_gae4, bar_ev, bar_exit,bar_home,
	bar_logout, bar_Modified;
	JMenu menu, menu2, menu3, menu4, menu5;
	CardLayout card;
	JPanel cus_pan;
	Yoya_info yo_1;Yoyaku yo_2;
	Yoya_henncou yo_3;Yoya_can yo_4;
	Eventgamenn eve;
	Cus_home hom;
	Cus_end end;
	Cus_info_LookUp info;
	hotel_manager.EmployeeInfo_home emp_home;
	Cus_Roomview standard, superrior, deluxe, excutive;
	String[] yes_no = {"예","아니오"};
	Screen screen;
	
	Cus_Main(Screen screen) { 
		this.screen = screen;
		makeMenubar();
		card = new CardLayout();
		yo_1 = new Yoya_info();yo_2 = new Yoyaku();
		yo_3 = new Yoya_henncou();
		yo_4 = new Yoya_can();
		hom = new Cus_home();
		eve = new Eventgamenn();
		end = new Cus_end();
		info = new Cus_info_LookUp(this);
		standard = new Cus_Roomview(0); superrior = new Cus_Roomview(1);
		deluxe = new Cus_Roomview(2); excutive = new Cus_Roomview(3);
		emp_home = new hotel_manager.EmployeeInfo_home();
		cus_pan = new JPanel();cus_pan.setLayout(card);
		cus_pan.add(hom,"home");
		cus_pan.add(yo_1,"yoya1");
		cus_pan.add(yo_2,"yoya2");
		cus_pan.add(yo_3,"yoya3");
		cus_pan.add(yo_4,"yoya4");
		cus_pan.add(eve,"event");
		cus_pan.add(end,"exit");
		cus_pan.add(standard,"standard");
		cus_pan.add(superrior,"superrior");
		cus_pan.add(deluxe,"deluxe");
		cus_pan.add(excutive,"excutive");
		cus_pan.add(info,"info");
		cus_pan.add(emp_home,"emp_home");
		bar_yo.addActionListener(this);
		bar_yo2.addActionListener(this);
		bar_yo3.addActionListener(this);
		bar_yo4.addActionListener(this);
		bar_ev.addActionListener(this);
		bar_home.addActionListener(this);
		bar_exit.addActionListener(this);
		bar_gae.addActionListener(this);
		bar_gae2.addActionListener(this);
		bar_gae3.addActionListener(this);
		bar_gae4.addActionListener(this);
		bar_logout.addActionListener(this);
		bar_Modified.addActionListener(this);
		this.setLayout(new BorderLayout());
		this.add("Center", cus_pan);
		this.setSize(1000, 600);
		this.setVisible(true);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		//new Cus_Main("�샇�뀛愿�由� �떆�뒪�뀥");
	}

}
