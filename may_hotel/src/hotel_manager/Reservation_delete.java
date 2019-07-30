package hotel_manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

class ResModel3 extends AbstractTableModel{

	private Object[][] tableData;
	private int cols, rows;
	private String[] columnName = { "예약번호", "고객아이디", "객실호수", "예약날짜", "체크인날짜", "체크아웃날짜", "조식", "예약인원", "총결제금액", "진행상황" };
	private List<ReservationInfo> list;
	Reservation_delete res_delete;
	
	ResModel3(Reservation_delete res_delete){
		this.res_delete = res_delete;
		CRUDprocess_emp crud = new CRUDprocess_emp();
		String res_id = res_delete.texts[0].getText();
		list = crud.selectReservationInfoByCus(res_id);
		setData();
	}
	
	private void setData() {
		Iterator it = list.iterator();
		rows = list.size();
		cols = columnName.length;
		tableData = new Object[rows][cols];
		int r = 0;
		while (it.hasNext()) {
			ReservationInfo info = (ReservationInfo) it.next();
			tableData[r][0] = info.getRes_num();
			tableData[r][1] = info.getRes_id();
			tableData[r][2] = info.getRes_room();
			tableData[r][3] = info.getRes_date();
			tableData[r][4] = info.getRes_checkin();
			tableData[r][5] = info.getRes_checkout();
			tableData[r][6] = info.getRes_breakfast();
			tableData[r][7] = info.getRes_head();
			tableData[r][8] = info.getRes_payment();
			tableData[r][9] = info.getRes_state();
			r++;
		}
	}
	
	@Override
	public String getColumnName(int arg0) {
		return columnName[arg0];
	}

	@Override
	public int getColumnCount() {
		return cols;
	}

	@Override
	public int getRowCount() {
		return rows;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		return tableData[arg0][arg1];
	}
	
}

public class Reservation_delete extends JPanel implements ActionListener, MouseListener{
	
	JLabel[] labels;
	String[] labels_title = {"예약 정보 취소","고객 아이디","고객 아이디","객실 선택","체크인 날짜    ","체크아웃 날짜","조식 선택","※1인당 15,000원","총 환불금액","호"};
	
	JTextField[] texts;
	
	JComboBox room_type, room_id, cus_head;
	String[] room_types = {"STANDARD","SUPERIOR","DELUXE","EXECUTIVE"};
	
	String[] standard_id = {"1001","1002","1003","1004","2001","2002","2003","2004"};
	String[] superior_id = {"3001","3002","3003","3004","4001","4002","4003","4004"};
	String[] deluxe_id = {"5001","5002","5003","5004","6001","6002","6003","6004"};
	String[] executive_id = {"7001","7002","7003","7004","8001","8002","8003","8004"};
	
	JRadioButton breakfast_y, breakfast_n;
	
	ButtonGroup group;
	
	JButton[] btns;
	
	ImageIcon road_icon, road_press, road_rollover, choice_icon, choice_press, choice_rollover,
			 delete_icon, delete_press, delete_rollover, cancel_icon, cancel_press, cancel_rollover;
	
	JPanel centerPanel, southPanel;
	
	JPanel center_southPanel;
	
	JPanel south_firstPanel, south_secondPanel;
	
	JPanel[] first_panels;//4개
	
	JPanel[] second_panels;//5개
	
	JPanel northPanel;
	
	JTable table;
	
	JScrollPane scroll;
	
	void makeJLabel() {
		labels = new JLabel[10];
		for(int i = 0; i < labels.length; i++) {
			labels[i] = new JLabel(labels_title[i]);
		}
	}
	
	void makeJTextField() {
		texts = new JTextField[6];
		for(int i = 0; i < texts.length; i++) {
			texts[0] = new JTextField(20);
			texts[1] = new JTextField(20);
			texts[2] = new JTextField(15);
			texts[3] = new JTextField(15);
			texts[4] = new JTextField(20);
			texts[5] = new JTextField(20);
		}
	}
	
	void makeJComboBox() {
		room_type = new JComboBox();
		for(int i = 0; i < room_types.length; i++) {
			room_type.addItem(room_types[i]);
			room_type.addActionListener(this);
		}
		
		room_id = new JComboBox();
		room_id.addItem("----------");
		
		cus_head = new JComboBox();
		for(int i = 1; i <= 10; i++) {
			cus_head.addItem(i + "명");
		}
	}
	
	void makeJRadioButton() {
		breakfast_y = new JRadioButton("포함");
		breakfast_y.setBackground(Color.WHITE);
		breakfast_n = new JRadioButton("미포함");
		breakfast_n.setBackground(Color.WHITE);
		group = new ButtonGroup();
		group.add(breakfast_y); group.add(breakfast_n);
	}

	void makeImageicon() {
		road_icon = new ImageIcon("image\\res_road.png");
		road_press = new ImageIcon("image\\res_road_press.png");
		road_rollover = new ImageIcon("image\\res_road_rollover.png");
		
		choice_icon = new ImageIcon("image\\choice.png");
		choice_press = new ImageIcon("image\\choice_press.png");
		choice_rollover = new ImageIcon("image\\choice_rollover.png");
		
		delete_icon = new ImageIcon("image\\delete.png");
		delete_press = new ImageIcon("image\\delete_press.png");
		delete_rollover = new ImageIcon("image\\delete_rollover.png");
		
		cancel_icon = new ImageIcon("image\\cancel.png");
		cancel_press = new ImageIcon("image\\cancel_press.png");
		cancel_rollover = new ImageIcon("image\\cancel_rollover.png");
	}
	
	void maeJButton() {
		btns = new JButton[5];
		for(int i = 0; i < btns.length; i++) {
			btns[i] = new JButton();
		}
		btns[0] = new JButton(road_icon);
		btns[0].setPressedIcon(road_press);
		btns[0].setRolloverIcon(road_rollover);
		btns[0].setBorderPainted(false);
		btns[0].setFocusPainted(false);
		btns[0].setContentAreaFilled(false);
		btns[0].addActionListener(this);
		
		btns[1] = new JButton(choice_icon);
		btns[1].setPressedIcon(choice_press);
		btns[1].setRolloverIcon(choice_rollover);
		btns[1].setBorderPainted(false);
		btns[1].setFocusPainted(false);
		btns[1].setContentAreaFilled(false);
		
		btns[2] = new JButton(choice_icon);
		btns[2].setPressedIcon(choice_press);
		btns[2].setRolloverIcon(choice_rollover);
		btns[2].setBorderPainted(false);
		btns[2].setFocusPainted(false);
		btns[2].setContentAreaFilled(false);
		
		btns[3] = new JButton(delete_icon);
		btns[3].setPressedIcon(delete_press);
		btns[3].setRolloverIcon(delete_rollover);
		btns[3].setBorderPainted(false);
		btns[3].setFocusPainted(false);
		btns[3].setContentAreaFilled(false);
		btns[3].addActionListener(this);
		
		btns[4] = new JButton(cancel_icon);
		btns[4].setPressedIcon(cancel_press);
		btns[4].setRolloverIcon(cancel_rollover);
		btns[4].setBorderPainted(false);
		btns[4].setFocusPainted(false);
		btns[4].setContentAreaFilled(false);
		btns[4].addActionListener(this);
	}
	
	void makeJPanel() {
		centerPanel = new JPanel(new BorderLayout());
		centerPanel.setBackground(Color.WHITE);
		southPanel = new JPanel(new GridLayout(1,2));
		
		center_southPanel = new JPanel(new FlowLayout());
		center_southPanel.setBackground(Color.WHITE);
		
		south_firstPanel = new JPanel(new GridLayout(6,1));
		south_secondPanel = new JPanel(new GridLayout(6,1));
		
		first_panels = new JPanel[6];
		for(int i = 0; i < first_panels.length; i++) {
			first_panels[i] = new JPanel();
			first_panels[i].setBackground(Color.WHITE);
		}
		
		second_panels = new JPanel[6];
		for(int i = 0; i < second_panels.length; i++) {
			second_panels[i] = new JPanel();
			second_panels[i].setBackground(Color.WHITE);
		}
		
		northPanel = new JPanel(new GridLayout(2,1));
		northPanel.setBackground(Color.WHITE);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object obj = arg0.getSource();
		if(obj == room_type) {
			if(room_type.getSelectedIndex()==0) {
				room_id.removeAllItems();
				for(int i = 0; i < standard_id.length; i++) {
					room_id.addItem(standard_id[i]);
				}
			}
			if(room_type.getSelectedIndex()==1) {
				room_id.removeAllItems();
				for(int i = 0; i < superior_id.length; i++) {
					room_id.addItem(superior_id[i]);
				}
			}
			if(room_type.getSelectedIndex()==2) {
				room_id.removeAllItems();
				for(int i = 0; i < deluxe_id.length; i++) {
					room_id.addItem(deluxe_id[i]);
				}
			}
			if(room_type.getSelectedIndex()==3) {
				room_id.removeAllItems();
				for(int i = 0; i < executive_id.length; i++) {
					room_id.addItem(executive_id[i]);
				}
			}
		}
		if(obj == btns[0]) {
			if(texts[0].getText().equals("")) {
				JOptionPane.showMessageDialog(this, "고객 아이디를 입력해 주세요.");
			}else {
				table.setModel(new ResModel3(this));
			}
		}
		if(obj == btns[3]) {
			if(!texts[2].getText().equals("")&&!texts[3].getText().equals("")){
				String[] str = {"취 소","이 전"}; 
			int select = JOptionPane.showOptionDialog(this, "예약을 취소 하시겠습니까?", "취 소", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, str, str[0]); 
			if(select == JOptionPane.YES_OPTION) {
				int num = (Integer)table.getValueAt(table.getSelectedRow(), 0);
				Calendar cal = Calendar.getInstance();
				String year = String.valueOf(cal.get(cal.YEAR));
				String month = String.valueOf(cal.get(cal.MONTH)+1);
				String date = String.valueOf(cal.get(cal.DATE));
				if(Integer.parseInt(month) < 10) {
					month = "0"+month;
				}
				if(Integer.parseInt(date) < 10) {
					date = "0"+date;
				}
				String res_cancel = year+"-" + month +"-"+ date;
				CRUDprocess_emp crud = new CRUDprocess_emp();
				ReservationInfo info = new ReservationInfo();
				info.setRes_num(num);
				info.setRes_cancel(res_cancel);
				int result = crud.updateReservationInfoResCancel(info);
				if(result > 0) {
					JOptionPane.showMessageDialog(this, "예약이 취소되었습니다." );
					texts[0].setText(" ");
					texts[0].setText("");
					texts[1].setText(" ");
					texts[1].setText("");
					texts[1].setEnabled(true);
					room_type.setSelectedIndex(0);
					room_type.setEnabled(true);
					room_id.setSelectedItem("----------");
					room_id.setEnabled(true);
					texts[2].setText(" ");
					texts[2].setText("");
					texts[2].setEnabled(true);
					texts[3].setText(" ");
					texts[3].setText("");
					texts[3].setEnabled(true);
					breakfast_y.setSelected(false);
					breakfast_n.setSelected(false);
					breakfast_y.setEnabled(true);
					breakfast_n.setEnabled(true);
					cus_head.setSelectedIndex(0);
					cus_head.setEnabled(true);
					texts[4].setText(" ");
					texts[4].setText("");
					texts[4].setEnabled(true);
					table.setModel(new ResModel3(this));
				}else {
					JOptionPane.showMessageDialog(this, "예약취소 과정에서 문제가 발생했습니다.");
				}
			}else {
				JOptionPane.showMessageDialog(this,"체크인 및 체크아웃 날짜를 설정해 주세요");
			}
			}else{}
		}
		if(obj == btns[4]) {
			texts[0].setText(" ");
			texts[0].setText("");
			texts[1].setText(" ");
			texts[1].setText("");
			texts[1].setEnabled(true);
			room_type.setSelectedIndex(0);
			room_type.setEnabled(true);
			room_id.setSelectedItem("----------");
			room_id.setEnabled(true);
			texts[2].setText(" ");
			texts[2].setText("");
			texts[2].setEnabled(true);
			texts[3].setText(" ");
			texts[3].setText("");
			texts[3].setEnabled(true);
			breakfast_y.setSelected(false);
			breakfast_n.setSelected(false);
			breakfast_y.setEnabled(true);
			breakfast_n.setEnabled(true);
			cus_head.setSelectedIndex(0);
			cus_head.setEnabled(true);
			texts[4].setText(" ");
			texts[4].setText("");
			texts[4].setEnabled(true);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		int selectedRow = table.getSelectedRow();
		String res_id = table.getValueAt(selectedRow, 1) + "";
		texts[1].setText(res_id);
		texts[1].setEnabled(false);
		String checkin = table.getValueAt(selectedRow, 4) + "";
		texts[2].setText(checkin);
		texts[2].setEnabled(false);
		String checkout = table.getValueAt(selectedRow, 5) + "";
		texts[3].setText(checkout);
		texts[3].setEnabled(false);
		String payment = table.getValueAt(selectedRow, 8) + "";
		texts[4].setText(payment);
		texts[4].setEnabled(false);
		String room_num = (table.getValueAt(selectedRow, 2) + "");
		int room_nu = Integer.parseInt(room_num);
		if ((room_nu == 1001) || (room_nu == 1002) || (room_nu == 1003) || (room_nu == 1004) || (room_nu == 2001)
				|| (room_nu == 2002) || (room_nu == 2003) || (room_nu == 2004)) {
			this.room_type.setSelectedItem("STANDARD");
		} else if ((room_nu == 3001) || (room_nu == 3002) || (room_nu == 3003) || (room_nu == 3004) || (room_nu == 4001)
				|| (room_nu == 4002) || (room_nu == 4003) || (room_nu == 4004)) {
			this.room_type.setSelectedItem("SUPERIOR");
		} else if ((room_nu == 5001) || (room_nu == 5002) || (room_nu == 5003) || (room_nu == 5004) || (room_nu == 6001)
				|| (room_nu == 6002) || (room_nu == 6003) || (room_nu == 6004)) {
			this.room_type.setSelectedItem("DELUXE");
		} else if ((room_nu == 7001) || (room_nu == 7002) || (room_nu == 7003) || (room_nu == 7004) || (room_nu == 8001)
				|| (room_nu == 8002) || (room_nu == 8003) || (room_nu == 8004)) {
			this.room_type.setSelectedItem("EXECUTIVE");
		}
		room_type.setEnabled(false);
		String breakfast = (String.valueOf(table.getValueAt(selectedRow, 6)));
		if (breakfast.equals("N")) {
			this.breakfast_n.setSelected(true);
			breakfast_n.setEnabled(false);
		} else if (breakfast.equals("Y")) {
			this.breakfast_y.setSelected(true);
			breakfast_y.setEnabled(false);
		}
		String head = String.valueOf(table.getValueAt(table.getSelectedRow(), 7));
		int res_head = Integer.parseInt(head) - 1;
		this.cus_head.setSelectedIndex(res_head);
		cus_head.setEnabled(false);

		String room_id = String.valueOf(table.getValueAt(selectedRow, 2));
		this.room_id.removeAllItems();
		this.room_id.addItem(room_id);
		this.room_id.setEnabled(false);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

	Reservation_delete(){
//		super(str);
		
		this.setLayout(new BorderLayout());
		
		makeJLabel();
		makeJTextField();
		makeJComboBox();
		makeJRadioButton();
		makeImageicon();
		maeJButton();
		makeJPanel();
		
		center_southPanel.add(labels[1]);
		center_southPanel.add(texts[0]);
		center_southPanel.add(btns[0]);
		
		table = new JTable();
		scroll = new JScrollPane(table);
		
		centerPanel.add("Center",scroll);
		centerPanel.add("South",center_southPanel);
		
		first_panels[0].add(new JLabel(""));
		first_panels[1].add(labels[2]); first_panels[1].add(texts[1]);
		first_panels[2].add(labels[3]); first_panels[2].add(room_type); first_panels[2].add(room_id); first_panels[2].add(labels[9]);
		first_panels[3].add(labels[4]); first_panels[3].add(texts[2]);
		first_panels[4].add(labels[5]); first_panels[4].add(texts[3]);
		first_panels[5].add(new JLabel(""));
		
		second_panels[0].add(new JLabel(""));
		second_panels[1].add(labels[6]); second_panels[1].add(breakfast_y); second_panels[1].add(breakfast_n);
		second_panels[2].add(labels[7]); second_panels[2].add(new JLabel("      ")); second_panels[2].add(cus_head);
		second_panels[3].add(labels[8]); second_panels[3].add(texts[4]);
		second_panels[4].add(btns[3]); second_panels[4].add(btns[4]);
		second_panels[5].add(new JLabel(""));
		
		
		for(int i = 0; i < first_panels.length; i++) {
			south_firstPanel.add(first_panels[i]);
		}
		
		for(int i = 0; i < second_panels.length; i++) {
			south_secondPanel.add(second_panels[i]);
		}
		
		southPanel.add(south_firstPanel); southPanel.add(south_secondPanel);
		
		Font font = new Font("Sandoll 고딕 01 Light", Font.BOLD, 30);
		Color fontColor = new Color(116,116,116);
		labels[0].setFont(font);
		labels[0].setForeground(fontColor);
		labels[0].setHorizontalAlignment(labels[0].CENTER);
		
		Font lblFont = new Font("Sandoll 고딕 01 Light", Font.PLAIN, 15);
		Color lblFontColor = new Color(76,76,76);
		for(int i = 1; i < labels.length; i++) {
			labels[i].setFont(lblFont);
			labels[i].setForeground(lblFontColor);
		}
		
		breakfast_y.setFont(lblFont);
		breakfast_y.setForeground(lblFontColor);
		breakfast_n.setFont(lblFont);
		breakfast_n.setForeground(lblFontColor);
		
		Color breakfastColor = new Color(255,94,0);
		labels[7].setForeground(breakfastColor);
		
		northPanel.add(new JLabel(""));
		northPanel.add(labels[0]);
		
		southPanel.setBackground(Color.WHITE);
		
		table.addMouseListener(this);
		
		this.add("North",northPanel);
		this.add("Center", centerPanel);
		this.add("South", southPanel);
		
		this.setSize(900,650);
		this.setVisible(true);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
//	public static void main(String[] args) {
//		new Reservation_delete("예약 정보 취소 시스템");
//	}

}
