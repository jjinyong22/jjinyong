package hotel_manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;


class ResModel extends AbstractTableModel{

	private Object[][] tableData;
	private int cols,rows;
	private String[] columnName = {"예약번호","고객아이디","객실호수","예약날짜",
			"체크인날짜","체크아웃날짜","조식","예약인원","총결제금액","진행상황"};
private List<ReservationInfo> list;
	
	ResModel(){
		CRUDprocess_emp crud = new CRUDprocess_emp();
		list = crud.selectReservationInfo();
		setData();
	}
	
	private void setData() {
		Iterator it = list.iterator();
		rows = list.size();
		cols = columnName.length;
		tableData = new Object[rows][cols];
		int r = 0;
		while(it.hasNext()) {
			ReservationInfo info = (ReservationInfo)it.next();
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

public class Reservation_road_table extends JPanel implements ActionListener,MouseListener{
	
	JTabbedPane tabs; // �� ����
	
	JLabel[] labels; // ���̺�(6��)
	String[] labels_title ={"예약 정보 조회","고객 아이디","  객실 선택  ","    체크인 날짜  "," 체크아웃 날짜  ","  진행 상황  "};
	
	JTextField[] texts;
	
	JComboBox[] combo;
	
	String[] room_title = {"STANDARD","SUPERIOR","DELUXE","EXECUTIVE"};
	String[] res_title = {"예약완료","체크인","체크아웃","예약취소"};
	
	JButton[] btns;
	
	String[] btn_title = {"선택","선택","예약 정보 조회"};
	
	JPanel[] panels;
	
	JPanel centerPanel, northPanel;
	
	JPanel center_firstPan, center_secondPan;
	
	JTable table;
	JScrollPane table_scroll;
	
	Font mainFont, font, btn_font, road_font;
	
	ImageIcon road_icon, road_press, road_rollover, choice_icon, choice_press, choice_rollover, res_update, res_update_press, res_update_rollover;
	
	EmployeeInfo_main emp_main;
	
	void makeJLabel() {
		labels = new JLabel[6];
		for(int i = 0; i < labels.length; i++) {
			labels[i] = new JLabel(labels_title[i]);
			labels[i].setHorizontalAlignment(labels[i].CENTER);
		}
	}
	
	void makeJTextField() {
		texts = new JTextField[3];
		for(int i = 0; i < texts.length; i++) {
			texts[0] = new JTextField(20);
			texts[1] = new JTextField(15);
			texts[2] = new JTextField(15);
		}
	}
	
	void makeJComboBox() {
		combo = new JComboBox[2];
		for(int i = 0; i < combo.length; i++) {
			combo[i] = new JComboBox();
			combo[i].setPreferredSize(new Dimension(230,25));
		}//�޺��ڽ� ����
		for(int i = 0; i < room_title.length; i++) {
			combo[0].addItem(room_title[i]);
		}//���� �޺��ڽ� ���� �߰�
		for(int i = 0; i < res_title.length; i++) {
			combo[1].addItem(res_title[i]);
		}//�������� �޺��ڽ� ���� �߰�
	}
	
	void makeJButton() {
		btns = new JButton[4];
		for(int i = 0; i < 2; i++) {
			btns[i] = new JButton();
		}
//		btns[0].setPreferredSize(new Dimension(70,25));
//		btns[1].setPreferredSize(new Dimension(70,25));
//		btns[2].setPreferredSize(new Dimension(200,35));
	}
	
	void makeImageIcon() {
		road_icon = new ImageIcon("image\\res_road.png");
		road_press = new ImageIcon("image\\res_road_press.png");
		road_rollover = new ImageIcon("image\\res_road_rollover.png");
		
		choice_icon = new ImageIcon("image\\choice.png");
		choice_press = new ImageIcon("image\\choice_press.png");
		choice_rollover = new ImageIcon("image\\choice_rollover.png");
		
		res_update = new ImageIcon("image\\res_update.png");
		res_update_press = new ImageIcon("image\\res_update_press.png");
		res_update_rollover = new ImageIcon("image\\res_update_rollover.png");
		
		btns[0] = new JButton(choice_icon);
		btns[0].setPressedIcon(choice_press);
		btns[0].setRolloverIcon(choice_rollover);
		btns[0].setContentAreaFilled(false);
		btns[0].setBorderPainted(false);
		btns[0].setFocusPainted(false);
		btns[0].addActionListener(this);
		
		btns[1] = new JButton(choice_icon);
		btns[1].setPressedIcon(choice_press);
		btns[1].setRolloverIcon(choice_rollover);
		btns[1].setContentAreaFilled(false);
		btns[1].setBorderPainted(false);
		btns[1].setFocusPainted(false);
		btns[1].addActionListener(this);
		
		btns[2] = new JButton(road_icon);
		btns[2].setPressedIcon(road_press);
		btns[2].setRolloverIcon(road_rollover);
		btns[2].setContentAreaFilled(false);
		btns[2].setBorderPainted(false);
		btns[2].setFocusPainted(false);
		btns[2].addActionListener(this);
		
		btns[3] = new JButton(res_update);
		btns[3].setPressedIcon(res_update_press);
		btns[3].setRolloverIcon(res_update_rollover);
		btns[3].setContentAreaFilled(false);
		btns[3].setBorderPainted(false);
		btns[3].setFocusPainted(false);
		btns[3].addActionListener(this);
	}
	
	void makeTabs() {
		tabs = new JTabbedPane();
	}
	
	void makeJPanel() {
		northPanel = new JPanel(new GridLayout(3,1));
		northPanel.setBackground(Color.WHITE);
		
		centerPanel = new JPanel(new GridLayout(2,1));
		centerPanel.setBackground(Color.WHITE);
		
		center_firstPan = new JPanel(new GridLayout(6,1));
		center_firstPan.setBackground(Color.WHITE);
		
		center_secondPan = new JPanel(new BorderLayout());
		center_secondPan.setBackground(Color.WHITE);
		
		panels = new JPanel[6];
		for(int i = 0; i < panels.length; i++) {
			panels[i] = new JPanel();
			panels[i].setBackground(Color.WHITE);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object obj = arg0.getSource();
		if(obj == btns[0]) {
			new ReservationRoad_calendar_checkIn(this);
		}
		if(obj == btns[1]) {
			new ReservationRoad_calendar_checkOut(this);
		}
		if(obj == btns[2]) {
			table.setModel(new ResModel());
		}
		if(obj == btns[3]) {
			String res_state = table.getValueAt(table.getSelectedRow(), 9)+"";
			if(!res_state.equals("����Ϸ�")) {
				JOptionPane.showMessageDialog(this, "������ �� ���� ���������Դϴ�.");
			}else {
				emp_main.card.show(emp_main.empMain_pan, "res_update");
				String cus_id = texts[0].getText();
				String res_checkin = texts[1].getText();
				String res_checkout = texts[2].getText();
				emp_main.res_update.texts[0].setText(cus_id);
				emp_main.res_update.texts[1].setText(cus_id);
				emp_main.res_update.texts[2].setText(res_checkin);
				emp_main.res_update.texts[3].setText(res_checkout);
				int room_index = combo[0].getSelectedIndex();
				emp_main.res_update.room_type.setSelectedIndex(room_index);
				emp_main.res_update.room_id.setSelectedItem(table.getValueAt(table.getSelectedRow(), 2));
				emp_main.res_update.texts[4].setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 8)));
				String breakfast = (String.valueOf(table.getValueAt(table.getSelectedRow(), 6)));
				if(breakfast.equals("N")) {
					emp_main.res_update.breakfast_n.setSelected(true);
				}else if(breakfast.equals("Y")) {
					emp_main.res_update.breakfast_y.setSelected(true);
				}
				String head = String.valueOf(table.getValueAt(table.getSelectedRow(),7));
				int res_head = Integer.parseInt(head) - 1;
				emp_main.res_update.cus_head.setSelectedIndex(res_head);
				
				String room_id = String.valueOf(table.getValueAt(table.getSelectedRow(), 2));
				emp_main.res_update.room_id.removeAllItems();
				emp_main.res_update.room_id.addItem(room_id);
				texts[0].setText(" ");
				texts[0].setText("");
				texts[0].setEnabled(true);
				combo[0].setSelectedIndex(0);
				combo[0].setEnabled(true);
				texts[1].setText(" ");
				texts[1].setText("");
				texts[1].setEnabled(true);
				texts[2].setText(" ");
				texts[2].setText("");
				texts[2].setEnabled(true);
				combo[1].setSelectedIndex(0);
				combo[1].setEnabled(true);
				table.setModel(new ResModel());
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int selectedRow = table.getSelectedRow();
		int columnCount = table.getSelectedColumn();
		for(int i = 0; i < columnCount; i++) {
			this.texts[0].setText(table.getValueAt(selectedRow, 1)+"");
			this.texts[1].setText(table.getValueAt(selectedRow, 4)+"");
			this.texts[2].setText(table.getValueAt(selectedRow, 5)+"");
			this.combo[1].setSelectedItem(table.getValueAt(selectedRow, 9)+"");
			//����ȣ�� �ε��� : 2
			String room_num = (table.getValueAt(selectedRow, 2)+"");
			int room_nu = Integer.parseInt(room_num);
			if((room_nu == 1001)||(room_nu == 1002)||(room_nu == 1003)||(room_nu == 1004)||
			  (room_nu == 2001)||(room_nu == 2002)||(room_nu == 2003)||(room_nu == 2004)) {
				this.combo[0].setSelectedItem("STANDARD");
			}else if((room_nu == 3001)||(room_nu == 3002)||(room_nu == 3003)||(room_nu == 3004)||
					  (room_nu == 4001)||(room_nu == 4002)||(room_nu == 4003)||(room_nu == 4004)) {
				this.combo[0].setSelectedItem("SUPERIOR");
			}else if((room_nu == 5001)||(room_nu == 5002)||(room_nu == 5003)||(room_nu == 5004)||
					  (room_nu == 6001)||(room_nu == 6002)||(room_nu == 6003)||(room_nu == 6004)) {
				this.combo[0].setSelectedItem("DELUXE");
			}else if((room_nu == 7001)||(room_nu == 7002)||(room_nu == 7003)||(room_nu == 7004)||
					  (room_nu == 8001)||(room_nu == 8002)||(room_nu == 8003)||(room_nu == 8004)) {
				this.combo[0].setSelectedItem("EXECUTIVE");
			}
			texts[0].setEnabled(false);
			texts[1].setEnabled(false);
			texts[2].setEnabled(false);
			btns[0].setEnabled(false);
			btns[1].setEnabled(false);
			combo[0].setEnabled(false);
			combo[1].setEnabled(false);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	Reservation_road_table(EmployeeInfo_main emp_main){
//		super(str);
		
		this.setLayout(new BorderLayout());
		
		this.emp_main = emp_main;
		
		makeJLabel();
		makeJTextField();
		makeJComboBox();
		makeJButton();
		makeTabs();
		makeJPanel();
		makeImageIcon();
		
		table = new JTable();
		table.addMouseListener(this);
		table_scroll = new JScrollPane(table);
		
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
		
		road_icon = new ImageIcon("image\\btn01.png");
		choice_icon = new ImageIcon("image\\btn02.png");
		
		ImageIcon choice_press = new ImageIcon("image\\btn02_press.png");
		ImageIcon choice_rollover = new ImageIcon("image\\btn02_rollover.png");
		
		ImageIcon road_press = new ImageIcon("image\\btn01_press.png");
		ImageIcon road_rollover = new ImageIcon("image\\btn01_rollover.png");
		
		panels[0].add(labels[1]); panels[0].add(texts[0]);
		panels[1].add(labels[2]); panels[1].add(combo[0]);
		panels[2].add(labels[3]); panels[2].add(texts[1]); panels[2].add(btns[0]);
		panels[3].add(labels[4]); panels[3].add(texts[2]); panels[3].add(btns[1]);
		panels[4].add(labels[5]); panels[4].add(combo[1]);
		panels[5].add(btns[2]); panels[5].add(btns[3]);
		
		for(int i = 0; i < panels.length; i++) {
			center_firstPan.add(panels[i]);
		}
		
		northPanel.add(new JLabel(""));
		northPanel.add(labels[0]);
		northPanel.add(new JLabel(""));
		
		center_secondPan.add("Center", table_scroll);
		
		centerPanel.add(center_firstPan);
		centerPanel.add(center_secondPan);
		
		this.add("North",northPanel);
		this.add("Center", centerPanel);
		
		this.setSize(900,650);
		this.setVisible(true);
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
//	public static void main(String[] args) {
//		new Reservation_road_table("�������� ��ȸ ȭ��");
//	}

}
