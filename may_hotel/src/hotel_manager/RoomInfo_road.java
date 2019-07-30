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
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

class RoomModel extends AbstractTableModel {
	String[] columnName = {  "객실호수","객실종류", "객실예약금(1인기준)", "예약가능여부", "수용인원","객실이미지" };
	Object[][] tableData;
	List<RoomInfo> list;
	int rows, cols;
	RoomInfo_road room_road;

	public RoomModel(RoomInfo_road room_road) {
		this.room_road = room_road;
		CRUDprocess_emp crud = new CRUDprocess_emp();
		String type = (String)room_road.combo.getSelectedItem();
		list = crud.selectRoomInfoByRoomType(type);
		init();
	}

	void init() {
		Iterator<RoomInfo> it = list.iterator();
		rows = list.size();
		cols = columnName.length;
		tableData = new Object[rows][cols];
		int r = 0;
		while (it.hasNext()) {
			RoomInfo info = (RoomInfo) it.next();
			tableData[r][0] = info.getRoom_id();
			tableData[r][1] = info.getRoom_type();
			tableData[r][2] = info.getRoom_price();
			tableData[r][3] = info.getRoom_check();
			tableData[r][4] = info.getRoom_head();
			tableData[r][5] = info.getRoom_image();
			r++;
			}
		}

	@Override
	public String getColumnName(int arg0) {
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
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return tableData[arg0][arg1];
	}

}

public class RoomInfo_road extends JPanel implements ActionListener{
	String[] room = { "STANDARD", "SUPERIOR", "DELUXE", "EXECUTIVE" };
	JLabel label, labels;
	JButton button;
	JComboBox combo;
	JTable table;
	JMenuBar menubar;
	JMenu[] menu;
	JMenuItem[] menuitem;
	JScrollPane scroll;
	ImageIcon road_icon, road_press, road_rollover;
	JPanel choicePan, northPan;

	/*
	 * void makeMenu() { menubar = new JMenuBar(); menu = new JMenu[7]; menuitem =
	 * new JMenuItem[15]; for(int i = 0; i<menu.length; i++) { menu[i] = new
	 * JMenu(MenuName[i]); } for(int i = 0; i < menuitem.length; i++) { menuitem[i]
	 * = new JMenuItem(menuitem_txt[i]); } //this.setJMenuBar(menubar);
	 * menubar.add(menu[0]); menubar.add(menu[1]); menubar.add(menu[2]);
	 * menubar.add(menu[3]); menubar.add(menu[4]); menubar.add(menu[5]);
	 * menubar.add(menu[6]); menu[0].add(menuitem[0]); menu[0].add(menuitem[1]);
	 * menu[0].add(menuitem[2]); menu[0].add(menuitem[3]); menu[1].add(menuitem[4]);
	 * menu[1].add(menuitem[5]); menu[1].add(menuitem[6]); menu[1].add(menuitem[7]);
	 * menu[2].add(menuitem[8]); menu[2].add(menuitem[9]);
	 * menu[2].add(menuitem[10]); menu[2].add(menuitem[11]);
	 * menu[3].add(menuitem[12]); menu[3].add(menuitem[13]);
	 * menu[6].add(menuitem[14]);
	 * 
	 * }
	 */
	void UseButton() {
		road_icon = new ImageIcon("image\\room_road.png");
		road_press = new ImageIcon("image\\room_road_press.png");
		road_rollover = new ImageIcon("image\\room_road_rollover.png");

		button = new JButton(road_icon);
		button.setPressedIcon(road_press);
		button.setRolloverIcon(road_rollover);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object obj = arg0.getSource();
		if(obj == button) {
			table.setModel(new RoomModel(this));
		}
	}

	public RoomInfo_road() {
//		super(str);
		combo = new JComboBox();
		label = new JLabel("객실 정보 조회");
		labels = new JLabel("객실 선택");
		table = new JTable();
		UseButton();

		table = new JTable();
		scroll = new JScrollPane(table);
		scroll.setBackground(Color.WHITE);
		for (int i = 0; i < room.length; i++) {
			combo.addItem(room[i]);
		}
		// UseButton();

		label.setHorizontalAlignment(label.CENTER);

		Font font = new Font("Sandoll 고딕 01 Light", Font.BOLD, 30);
		Color fontColor = new Color(116, 116, 116);

		label.setFont(font);
		label.setForeground(fontColor);

		Font lblFont = new Font("Sandoll 고딕 01 Light", Font.PLAIN, 15);
		Color lblFontColor = new Color(76, 76, 76);

		labels.setFont(lblFont);
		labels.setForeground(lblFontColor);

		// North
		northPan = new JPanel(new GridLayout(5, 1));
		northPan.setBackground(Color.WHITE);
		northPan.add(new JLabel(""));
		northPan.add(label);
		northPan.add(new JLabel(""));

		choicePan = new JPanel();
		choicePan.setBackground(Color.WHITE);
		choicePan.add(labels);
		choicePan.add(combo);
		northPan.add(choicePan);

		northPan.add(button);

		// makeMenu();
		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		this.add("North", northPan);
		this.add("Center", scroll);
		this.setVisible(true);
		this.setSize(800, 500);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

//	public static void main(String[] args) {
//		new RoomInfo_road("���� ���� ��ȸ �ý���");
//	}

}
