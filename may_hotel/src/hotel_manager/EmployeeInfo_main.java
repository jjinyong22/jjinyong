package hotel_manager;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class EmployeeInfo_main extends JPanel implements ActionListener {

	public JMenuBar mb;
	JMenuItem res_road_item, res_insert_item, res_update_item, res_delete_item,
				   res_road_table_item, res_road_calendar_item,
				  room_road_item, room_insert_item, room_update_item, room_delete_item,
				  event_road_item, event_insert_item, event_update_item, event_delete_item,
				  cus_road_item, cus_delete_item,
				  emp_road_item, emp_insert_item, emp_update_item, emp_delete_item,
				  sales_road_item,
				  sys_out_item, log_out_item;
	public JMenu res_menu, room_menu, event_menu, cus_menu, emp_menu, sales_menu, systemout;
	public CardLayout card;
	public JPanel empMain_pan;

	String[] yes_no = {"예","아니오"};
	
	EmployeeInfo_home home;
	
	Reservation_road_calendar res_road_calendar;
	Reservation_road_table res_road_table;
	
	Reservation_insert res_insert;
	Reservation_update res_update;
	Reservation_delete res_delete;
	
	CustomerInfo_road_table cus_road;
	CustomerInfo_delete cus_delete;
	
	EmployeeInfo_delete emp_delete;
	EmployeeInfo_update emp_update;
	EmployeeInfo_road emp_road;
	EmployeeInfo_insert emp_insert;
	
	SalesInfo_road sales_road;
	
	RoomInfo_road room_road;
	RoomInfo_insert room_insert;
	RoomInfo_update room_update;
	RoomInfo_delete room_delete;
	
	EventInfo_road event_road;
	EventInfo_insert event_insert;
	EventInfo_update event_update;
	EventInfo_delete event_delete;
	
	hotel_customer.Screen main;
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == res_road_calendar_item) {
			this.card.show(empMain_pan, "res_calendar");
		}
		if(obj == res_road_table_item) {
			this.card.show(empMain_pan, "res_table");
		}
		if(obj == res_insert_item) {
			this.card.show(empMain_pan, "res_insert");
		}
		if(obj == res_update_item) {
			this.card.show(empMain_pan, "res_update");
		}
		if(obj == res_delete_item) {
			this.card.show(empMain_pan, "res_delete");
		}
		if(obj == cus_road_item) {
			this.card.show(empMain_pan, "cus_road");
		}
		if(obj == cus_delete_item) {
			this.card.show(empMain_pan, "cus_delete");
		}
		if(obj == emp_road_item) {
			this.card.show(empMain_pan, "emp_road");
		}
		if(obj == emp_insert_item) {
			this.card.show(empMain_pan, "emp_insert");
		}
		if(obj == emp_update_item) {
			this.card.show(empMain_pan, "emp_update");
		}
		if(obj == emp_delete_item) {
			this.card.show(empMain_pan, "emp_delete");
		}
		if(obj == sales_road_item) {
			this.card.show(empMain_pan, "sales_road");
		}
		if(obj ==room_road_item) {
			this.card.show(empMain_pan, "room_road");
		}
		if(obj == room_insert_item) {
			this.card.show(empMain_pan, "room_insert");
		}
		if(obj == room_update_item) {
			this.card.show(empMain_pan, "room_update");
		}
		if(obj == room_delete_item) {
			this.card.show(empMain_pan, "room_delete");
		}
		if(obj == event_road_item) {
			this.card.show(empMain_pan, "event_road");
		}
		if(obj == event_insert_item) {
			this.card.show(empMain_pan, "event_insert");
		}
		if(obj == event_update_item) {
			this.card.show(empMain_pan, "event_update");
		}
		if(obj == event_delete_item) {
			this.card.show(empMain_pan, "event_delete");
		}
		if(obj == log_out_item) {
			main.card.show(main.card_pan, "login");
			main.text.setText(" ");
			main.text.setText("");
			main.pass.setText(" ");
			main.pass.setText("");
		}
		if(obj == sys_out_item) {
			String[] str = {"종료","취소"};
			int check = JOptionPane.showOptionDialog(this, "정말로 종료하시겠습니까?", "시스템종료", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, str, "종료");
			if(check == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}

	void makeMenubar() {
		mb = new JMenuBar();
		
		res_menu = new JMenu("예약안내");
		room_menu = new JMenu("객실안내");
		event_menu = new JMenu("이벤트안내");
		cus_menu = new JMenu("회원관리");
		emp_menu = new JMenu("직원관리");
		sales_menu = new JMenu("매출관리");
		systemout = new JMenu("시스템종료");
		
		res_road_item = new JMenu("예약조회");
		res_insert_item = new JMenuItem("예약하기");
		res_insert_item.addActionListener(this);
		res_update_item = new JMenuItem("예약변경");
		res_update_item.addActionListener(this);
		res_delete_item = new JMenuItem("예약취소");
		res_delete_item.addActionListener(this);
		
		res_road_calendar_item = new JMenuItem("캘린더조회");
		res_road_calendar_item.addActionListener(this);
		res_road_table_item = new JMenuItem("테이블조회");
		res_road_table_item.addActionListener(this);
		
		room_road_item = new JMenuItem("객실조회");
		room_road_item.addActionListener(this);
		room_insert_item = new JMenuItem("객실등록");
		room_insert_item.addActionListener(this);
		room_update_item = new JMenuItem("객실변경");
		room_update_item.addActionListener(this);
		room_delete_item = new JMenuItem("객실삭제");
		room_delete_item.addActionListener(this);
		
		event_road_item = new JMenuItem("이벤트조회");
		event_road_item.addActionListener(this);
		event_insert_item = new JMenuItem("이벤트등록");
		event_insert_item.addActionListener(this);
		event_update_item = new JMenuItem("이벤트변경");
		event_update_item.addActionListener(this);
		event_delete_item = new JMenuItem("이벤트삭제");
		event_delete_item.addActionListener(this);
		
		cus_road_item = new JMenuItem("회원조회");
		cus_road_item.addActionListener(this);
		cus_delete_item = new JMenuItem("회원삭제");
		cus_delete_item.addActionListener(this);
		
		emp_road_item = new JMenuItem("직원조회");
		emp_road_item.addActionListener(this);
		emp_insert_item = new JMenuItem("직원등록");
		emp_insert_item.addActionListener(this);
		emp_update_item = new JMenuItem("직원변경");
		emp_update_item.addActionListener(this);
		emp_delete_item = new JMenuItem("직원삭제");
		emp_delete_item.addActionListener(this);
		
		sales_road_item = new JMenuItem("매출조회");
		sales_road_item.addActionListener(this);
		
		sys_out_item = new JMenuItem("시스템종료");
		sys_out_item.addActionListener(this);
		
		log_out_item = new JMenuItem("로그아웃");
		log_out_item.addActionListener(this);
		
		res_road_item.add(res_road_calendar_item);
		res_road_item.add(res_road_table_item);
		
		res_menu.add(res_road_item);
		res_menu.add(res_insert_item);
		res_menu.add(res_update_item);
		res_menu.add(res_delete_item);
		
		room_menu.add(room_road_item);
		room_menu.add(room_insert_item);
		room_menu.add(room_update_item);
		room_menu.add(room_delete_item);
		
		event_menu.add(event_road_item);
		event_menu.add(event_insert_item);
		event_menu.add(event_update_item);
		event_menu.add(event_delete_item);
		
		cus_menu.add(cus_road_item);
		cus_menu.add(cus_delete_item);
		
		emp_menu.add(emp_road_item);
		emp_menu.add(emp_insert_item);
		emp_menu.add(emp_update_item);
		emp_menu.add(emp_delete_item);

		sales_menu.add(sales_road_item);
		
		systemout.add(log_out_item);
		systemout.add(sys_out_item);
		
		mb.add(res_menu);
		mb.add(room_menu);
		mb.add(event_menu);
		mb.add(cus_menu);
		mb.add(emp_menu);
		mb.add(sales_menu);
		mb.add(systemout);
		
//		this.setJMenuBar(mb);
	};


	public EmployeeInfo_main(hotel_customer.Screen main) { 
//		super(str);
		
		this.main = main;
		
		this.setLayout(new BorderLayout());
		
		makeMenubar();
		
		card = new CardLayout();
		
		home = new EmployeeInfo_home();
		
		res_road_calendar = new Reservation_road_calendar();
		res_road_table = new Reservation_road_table(this);
		res_insert = new Reservation_insert();
		res_update = new Reservation_update();
		res_delete = new Reservation_delete();
		
		cus_road = new CustomerInfo_road_table();
		cus_delete = new CustomerInfo_delete();
		
		emp_insert = new EmployeeInfo_insert();
		emp_update = new EmployeeInfo_update();
		emp_delete = new EmployeeInfo_delete();
		emp_road = new EmployeeInfo_road();
		
		sales_road = new SalesInfo_road();
	
		room_road = new RoomInfo_road();
		room_insert = new RoomInfo_insert(main);
		room_update = new RoomInfo_update();
		room_delete = new RoomInfo_delete();
		
		event_road = new EventInfo_road();
		event_insert = new EventInfo_insert(main);
		event_update = new EventInfo_update(main);
		event_delete = new EventInfo_delete(main);
		
		empMain_pan = new JPanel();
		empMain_pan.setLayout(card);
		
		empMain_pan.add(home,"home");
		empMain_pan.add(res_road_calendar,"res_calendar");
		empMain_pan.add(res_road_table,"res_table");
		empMain_pan.add(res_insert,"res_insert");
		empMain_pan.add(res_update,"res_update");
		empMain_pan.add(res_delete,"res_delete");
		empMain_pan.add(cus_road,"cus_road");
		empMain_pan.add(cus_delete,"cus_delete");
		empMain_pan.add(emp_road,"emp_road");
		empMain_pan.add(emp_insert,"emp_insert");
		empMain_pan.add(emp_update,"emp_update");
		empMain_pan.add(emp_delete,"emp_delete");
		empMain_pan.add(sales_road,"sales_road");
		empMain_pan.add(room_road,"room_road");
		empMain_pan.add(room_insert,"room_insert");
		empMain_pan.add(room_update,"room_update");
		empMain_pan.add(room_delete,"room_delete");
		empMain_pan.add(event_road,"event_road");
		empMain_pan.add(event_insert,"event_insert");
		empMain_pan.add(event_update,"event_update");
		empMain_pan.add(event_delete,"event_delete");
		
		this.add("Center", empMain_pan);
		this.setSize(1000, 650);
		this.setVisible(true);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

//	public static void main(String[] args) {
//		new EmployeeInfo_main();
//	}

}

