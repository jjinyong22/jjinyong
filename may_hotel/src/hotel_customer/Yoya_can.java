package hotel_customer;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

class Yo_canModel extends AbstractTableModel{
	private Object[][] tableData;
	private int rows,cols;
	private String[] columnName = {"예약번호", "예약고객아이디", "예약객실아이디", "예약날짜",
			"체크인날짜","체크아웃날짜", "조식포함여부", "총예약금액", "예약인원", "진행상황"};
	private List<Yoya_batis> list;
	
	Yo_canModel(){
		CRUDprocess crud = new CRUDprocess();
		list = crud.selectyoya();
		setData();
	}
	private void setData() {
		Iterator it = list.iterator();
		rows = list.size();
		cols = columnName.length;
		tableData = new Object[rows][cols];
		int r= 0;
		customer_info info = Screen.info;
		while(it.hasNext()) {
			Yoya_batis em = (Yoya_batis)it.next();
			String name = em.getRes_id();
			if(name.equals(info.getCus_id())) {
			tableData[r][0] = em.getRes_num();
			tableData[r][1] = em.getRes_id();
			tableData[r][2] = em.getRes_room();
			tableData[r][3] = em.getRes_date();
			tableData[r][4] = em.getRes_checkin();
			tableData[r][5] = em.getRes_checkout();
			tableData[r][6] = em.getRes_breakfast();
			tableData[r][7] = em.getRes_payment();
			tableData[r][8] = em.getRes_head();
			tableData[r][9] = em.getRes_state();
			r++;
		}
		}
	}
	@Override
	public String getColumnName(int arg0) {//���� �ٲٴ°�
		// TODO Auto-generated method stub
		return columnName[arg0];
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return cols;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return rows;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return tableData[rowIndex][columnIndex];
	}
	
}

public class Yoya_can extends JPanel implements ActionListener,MouseListener{
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int selectedRow = table.getSelectedRow();
		String room_num = (table.getValueAt(selectedRow, 2)+"");
		 int room_nu = Integer.parseInt(room_num);
	        if((room_nu == 1001)||(room_nu == 1002)||(room_nu == 1003)||(room_nu == 1004)||
	          (room_nu == 2001)||(room_nu == 2002)||(room_nu == 2003)||(room_nu == 2004)) {
	           this.box.setSelectedItem("STANDARD");
	        }else if((room_nu == 3001)||(room_nu == 3002)||(room_nu == 3003)||(room_nu == 3004)||
	                (room_nu == 4001)||(room_nu == 4002)||(room_nu == 4003)||(room_nu == 4004)) {
	           this.box.setSelectedItem("SUPERIOR");
	        }else if((room_nu == 5001)||(room_nu == 5002)||(room_nu == 5003)||(room_nu == 5004)||
	                (room_nu == 6001)||(room_nu == 6002)||(room_nu == 6003)||(room_nu == 6004)) {
	           this.box.setSelectedItem("DELUXE");
	        }else if((room_nu == 7001)||(room_nu == 7002)||(room_nu == 7003)||(room_nu == 7004)||
	                (room_nu == 8001)||(room_nu == 8002)||(room_nu == 8003)||(room_nu == 8004)) {
	        	this.box.setSelectedItem("EXECUTIVE");
	        }
	        
		txt[0].setText(table.getValueAt(selectedRow, 4)+"");
			txt[1].setText(table.getValueAt(selectedRow, 5)+"");
			String zo = table.getValueAt(selectedRow, 6)+"";
			if(zo.equals("Y")) {
			check1.setSelected(true);
			}else {
				check2.setSelected(true);
				}
			txt[2].setText(table.getValueAt(selectedRow,7)+"");
			
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == bts) {
			table.setModel(new HencouModel());
		}
		if(obj == bts2) {
			new Yoya_can_calendar_checkin(this);
		}
		if(obj == bts3) {
			new Yoya_can_calendar_checkout(this);
		}
		if(obj == bts4) {
			CRUDprocess crud = new CRUDprocess();
			int selectedRow = table.getSelectedRow();
			
			int res_num = (int)table.getValueAt(selectedRow, 0);
			String res_id = String.valueOf(table.getValueAt(selectedRow, 1));
			
//			String room = (String)box2.getSelectedItem();
//			int res_room = Integer.valueOf(room);
			int res_room = (int)table.getValueAt(selectedRow, 2);
			
			Date today = new Date();
			SimpleDateFormat test = new SimpleDateFormat("yyyy-mm-dd");
			String res_date = (String)table.getValueAt(selectedRow, 3);
			String res_checkin = txt[0].getText();
			String res_checkout = txt[1].getText();
			String res_breakfast = "";
			if(check1.isSelected()==true) {
				res_breakfast = "Y";
			}else if(check2.isSelected()==true) {
				res_breakfast = "N";
			}
			int res_payment = Integer.parseInt(txt[2].getText());//����
//			String head = (String)cus_head.getSelectedItem();
//			int res_head = Integer.valueOf(head);//�ο���
			int res_head = (int)table.getValueAt(selectedRow, 8);
			
			
			String res_state = "예약취소";
			String res_cancel = test.format(today);
			Yoya_batis yo_ya = new Yoya_batis();
			yo_ya.setRes_num(res_num);yo_ya.setRes_id(res_id);
			yo_ya.setRes_room(res_room);yo_ya.setRes_date(res_date);
			yo_ya.setRes_checkin(res_checkin);yo_ya.setRes_checkout(res_checkout);
			yo_ya.setRes_breakfast(res_breakfast);yo_ya.setRes_payment(res_payment);
			yo_ya.setRes_head(res_head);yo_ya.setRes_state(res_state);
			yo_ya.setRes_canceldate(res_cancel);
			int r = crud.updateyoya(yo_ya);
			if(r > 0) {
				JOptionPane.showMessageDialog(this, "환불이 되었습니다.");
			}else {
				JOptionPane.showMessageDialog(this, "오류가 발생하였습니다.");
			}
		}
		
		if(obj == bts5) {
			box.setSelectedItem("STANDARD");
			txt[0].setText("");
			txt[1].setText("");
			txt[2].setText("");
		}
	}

	void makeMenubar(){
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
		menu.add(bar_yo);menu.add(bar_yo2);
		menu.add(bar_yo3);menu.add(bar_yo4);
		menu2.add(bar_gae);menu2.add(bar_gae2);menu2.add(bar_gae3);menu2.add(bar_gae4);
		menu3.add(bar_ev);menu4.add(bar_exit);
		bar.add(menu);bar.add(menu2);bar.add(menu3);bar.add(menu4);
		//this.setJMenuBar(bar);
	};
	
	JMenuBar bar;JMenuItem bar_yo,bar_yo2,bar_yo3,bar_yo4,
	bar_gae,bar_gae2,bar_gae3,bar_gae4,
	bar_ev,bar_exit;
	JTable table;JScrollPane scroll;
	JMenu menu,menu2,menu3,menu4;
	JPanel[] pan;JButton bts,bts2,bts3,bts4,bts5;
	JPanel ue,sita,migi,hidari;
	JLabel[] lab;JTextField[] txt;
	JComboBox box;JCheckBox check1,check2;
	ButtonGroup group;
	String[] lab_txt = {"예약 정보  취소","객실 선택","체크인 날짜","체크아웃 날짜","조식 선택","환불 금액"};
	String[] gaes = {"STANDARD","SUPERIOR","DELUXE","EXECUTIVE"};
	ImageIcon road_icon,road_press,road_rollover,
		road_icon2,road_press2,road_rollover2,
		road_icon3,road_press3,road_rollover3,
		road_icon4,road_press4,road_rollover4,
		road_icon5,road_press5,road_rollover5;
	void makeImage() {
		road_icon = new ImageIcon("image\\예약정보1.png");
		road_press = new ImageIcon("image\\예약정보2.png");
		road_rollover = new ImageIcon("image\\예약정보3.png");
		road_icon2 = new ImageIcon("image\\선택1.png");
		road_press2 = new ImageIcon("image\\선택2.png");
		road_rollover2 = new ImageIcon("image\\선택3.png");
		road_icon3 = new ImageIcon("image\\선택1.png");
		road_press3 = new ImageIcon("image\\선택2.png");
		road_rollover3 = new ImageIcon("image\\선택3.png");
		road_icon4 = new ImageIcon("image\\환불1.png");
		road_press4 = new ImageIcon("image\\환불2.png");
		road_rollover4 = new ImageIcon("image\\환불3.png");
		road_icon5 = new ImageIcon("image\\취소1.png");
		road_press5 = new ImageIcon("image\\취소2.png");
		road_rollover5 = new ImageIcon("image\\취소3.png");
	}
	
	void makeJpanel() {
		table = new JTable();
		table.addMouseListener(this);
		table.setPreferredScrollableViewportSize(new Dimension(750,65));
		ue = new JPanel();migi = new JPanel();
		sita = new JPanel(); hidari = new JPanel();
		ue.setLayout(new GridLayout(3,1));
		sita.setLayout(new GridLayout(1,2));
		hidari.setLayout(new GridLayout(4,1));
		migi.setLayout(new GridLayout(3,1));
		pan = new JPanel[9];
		for(int i = 0;i < pan.length;i++) {
			pan[i] = new JPanel();
		}
		//pan[1].add(table);
		scroll = new JScrollPane(table);
		pan[1].add(scroll);
		this.add(ue);this.add(sita);this.add(hidari);this.add(migi);
		ue.add(pan[0]);ue.add(pan[1]);ue.add(pan[2]);
		sita.add(hidari);sita.add(migi);
		hidari.add(pan[3]);hidari.add(pan[4]);
		hidari.add(pan[5]);hidari.add(pan[6]);
		migi.add(pan[7]);migi.add(pan[8]);
		
	}
	void makeJButton() {
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
			
			bts5 = new JButton(road_icon5);
			bts5.setPressedIcon(road_press5);
			bts5.setRolloverIcon(road_rollover5);
			bts5.setContentAreaFilled(false);
			bts5.setBorderPainted(false);
			bts5.setFocusPainted(false);
			bts5.addActionListener(this);
			
			pan[2].add(bts);
			pan[4].add(bts2);pan[5].add(bts3);
			pan[8].add(bts4);pan[8].add(bts5);
	}
	void makeJLabel() {
		lab = new JLabel[6];
		for(int i = 0; i< lab.length; i++) {
			lab[i] = new JLabel(lab_txt[i]);
		}
		pan[0].add(lab[0]);pan[3].add(lab[1]);
		pan[4].add(lab[2]);pan[5].add(lab[3]);
		pan[6].add(lab[4]);pan[7].add(lab[5]);
	}
	void makeText() {
		txt = new JTextField[3];
		for(int i =0; i <txt.length;i++) {
			txt[i] = new JTextField(10);
		}
		pan[4].add(txt[0]);pan[5].add(txt[1]);
		pan[7].add(txt[2]);
	}
	void make_box_com() {
		box = new JComboBox<String>(gaes);
		group = new ButtonGroup();
		check1 = new JCheckBox("포함");
		check2 = new JCheckBox("미포함");
		
		group.add(check1);group.add(check2);
		pan[3].add(box);
		pan[6].add(check1);pan[6].add(check2);
	}
	Yoya_can(){
		//super(str);
		this.setLayout(new GridLayout(2,1));
		makeMenubar();makeJpanel();makeJLabel();
		makeText();makeImage();
		makeJButton();make_box_com();
		this.setSize(800,500);
		this.setVisible(true);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public static void main(String[] args) {
		//new Yoyaku4("ȣ�ڰ��� �ý���");
	}

}
