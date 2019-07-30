package hotel_manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

class RoomModel3 extends AbstractTableModel{

	String[] columnName = {  "객실호수","객실종류", "객실예약금(1인기준)", "예약가능여부", "수용인원","객실이미지" };
	Object[][] tableData;
	List<RoomInfo> list;
	int rows, cols;
	RoomInfo_delete room_update;
	
	RoomModel3(RoomInfo_delete room_update){
		this.room_update = room_update;
		CRUDprocess_emp crud = new CRUDprocess_emp();
		String type = (String)room_update.room_combo.getSelectedItem();
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

class BackImage3 extends JPanel{

	Image ima;
	
	void setImage(String path){
		ima = Toolkit.getDefaultToolkit().getImage(path);
	}
	
	@Override
	public void paint(Graphics arg0) {
		arg0.clearRect(0, 0, this.getWidth(), this.getHeight());
		if(ima != null) {
			arg0.drawImage(ima, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
}

class LoadActionListener3 implements ActionListener {

	RoomInfo_delete panel;
	BackImage3 image;
	FileDialog fileDialog;
//	EmployeeInfo_main main;
	
	hotel_customer.Screen main;
	
	static String fileName;
	
	static BufferedInputStream bis;
	
	LoadActionListener3(RoomInfo_delete panel, BackImage3 image, hotel_customer.Screen main){
		this.panel = panel;
		this.image = image;
		this.main = main;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		fileDialog = new FileDialog(main,"이미지  파일 열기", FileDialog.LOAD);
		fileDialog.setVisible(true);
		fileName = fileDialog.getFile();
		String path = fileDialog.getDirectory()+fileDialog.getFile();
		panel.image_text.setText(path);
		image.setImage(path);
		image.repaint();
		try {
			bis = new BufferedInputStream(new FileInputStream(path));
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(panel, "이미지 파일을 로드할 수 없습니다.");
		}
	}
}

class SaveActionListener3 implements ActionListener{

	FileDialog fileDialog;
	RoomInfo_delete panel;
	BufferedOutputStream bos;
//	EmployeeInfo_main main;
	
	hotel_customer.Screen main;
	
	SaveActionListener3(RoomInfo_delete panel, hotel_customer.Screen main){
		this.panel = panel;
		this.main = main;
	}
	
	static String path;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		fileDialog = new FileDialog(main,"이미지 파일 저장",FileDialog.SAVE);
		fileDialog.setFile(LoadActionListener2.fileName);
		fileDialog.setVisible(true);
		path = fileDialog.getDirectory()+fileDialog.getFile();
		panel.image_text.setText(path);
		try {
			bos = new BufferedOutputStream(new FileOutputStream(path));
			int data = 0;
			while((data = LoadActionListener2.bis.read()) != -1) {
				bos.write(data);
			}
		}catch(Exception ex){
			JOptionPane.showMessageDialog(panel, "이미지 파일 저장에 실패 하였습니다.");
		}finally {
			try {
				bos.close();
				LoadActionListener2.bis.close();
			}catch(Exception exe){
				
			}
		}
	}
}

public class RoomInfo_delete extends JPanel implements ActionListener, MouseListener{
	
	JLabel[] label; 
	String[] label_txt ={"객실 정보 삭제", "객실 선택", "객실 호수", "호","예약 금액",
			"※상기 금액은 1박 기준 금액입니다.※", "수용 인원", "명","예약기능 여부", "객실 이미지 업로드"};
	
	JRadioButton res_ok, res_no; ButtonGroup buttongroup;
	
	JButton[] btns; 
	
	JPanel[] first_pan;
	
	JPanel northPanel, centerPanel, southPanel;
	
	JPanel firstPanel, secondPanel;
	
	JPanel second_centerPanel, second_southPanel, second_northPanel;
	
	JPanel image_textPan, image_btnPan;
	
	JPanel center_northPanel;
	
	JPanel center_southPanel;
	
	JPanel center_centerPanel;
	
	JPanel north_northPanel;
	
	JPanel north_roomChoicePanel;
	
	JTextField room_text, price_text, image_text;
	
	JTable table;
	
	JTextField room_idText;
	static JComboBox room_combo, head_combo;
	
	String[] room_texts = {"STANDARD", "SUPERIOR", "DELUXE", "EXECUTIVE" };
	
	String[] standard_id = { "1001", "1002", "1003", "1004", "2001", "2002", "2003", "2004", };
	String[] superior_id = { "3001", "3002", "3003", "3004", "4001", "4002", "4003", "4004" };
	String[] deluxe_id = { "5001", "5002", "5003", "5004", "6001", "6002", "6003", "6004" };
	String[] executive_id = { "7001", "7002", "7003", "7004", "8001", "8002", "8003", "8004" };
	
	String[] head_texts = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

	ImageIcon room_road, room_road_press, room_road_rollover, select, select_press, select_rollover,
				  image_road, image_road_press, image_road_rollover, image_save, image_save_press, image_save_rollover,
				  delete, delete_press, delete_rollover, cancel, cancel_press, cancel_rollover;
	
	BackImage3 image;
	
//	EmployeeInfo_main main;
	
	hotel_customer.Screen main;
	
	JScrollPane scroll;
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int selectedRow = table.getSelectedRow();
		 String room_id = table.getValueAt(selectedRow, 0)+"";
		 room_idText.setText(room_id);
		 room_idText.setEnabled(false);
		 String room_price = table.getValueAt(selectedRow, 2)+"";
		 price_text.setText(room_price);
		 String room_head = table.getValueAt(selectedRow, 4)+"";
		 head_combo.setSelectedItem(room_head);
		 String room_check = table.getValueAt(selectedRow, 3)+"";
		 if(room_check.equals("Y")) {
			 res_ok.setSelected(true);
		 }else if(room_check.equals("N")) {
			 res_no.setSelected(true);
		 }
		 String room_image = table.getValueAt(selectedRow, 5)+"";
		 image_text.setText(room_image);
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object obj = arg0.getSource();
		if (obj == btns[0]) {
			table.setModel(new RoomModel3(this));
		}
		
		if(obj == btns[3]) {
			if(price_text.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "객실의 가격정보를 입력해주세요.");
			}else {
				String[] str = {"삭 제","취 소"}; 
				int select = JOptionPane.showOptionDialog(this, "삭제하시겠습니까?", "삭 제", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, str, str[0]); 
				if(select == JOptionPane.YES_OPTION) {
					CRUDprocess_emp crud = new CRUDprocess_emp();
					int room_id = Integer.parseInt(room_idText.getText());
					int result = crud.deleteRoomInfoByRoomId(room_id);
					if(result > 0) {
						JOptionPane.showMessageDialog(this, "객실정보가 삭제되었습니다.");
						room_combo.setSelectedIndex(0);
						room_idText.setText(" ");
						room_idText.setText("");
						room_idText.setEnabled(true);
						price_text.setText(" ");
						price_text.setText("");
						head_combo.setSelectedIndex(0);
						res_ok.setSelected(false);
						res_no.setSelected(false);
						image_text.setText(" ");
						image_text.setText("");
						image.removeAll();
					}else {
						JOptionPane.showMessageDialog(this, "객실정보 삭제과정에서 문제가 발생했습니다.");
					}
				}
			}
		}
		
		if(obj == btns[4]) {
			room_combo.setSelectedIndex(0);
			room_text.setText(" ");
			room_text.setText("");
			room_text.setEnabled(true);
			price_text.setText(" ");
			price_text.setText("");
			head_combo.setSelectedIndex(0);
			res_ok.setSelected(false);
			res_no.setSelected(false);
			image_text.setText(" ");
			image_text.setText("");
			image.removeAll();
		}
		
	}


	void makePanel() {
	
		northPanel = new JPanel(new BorderLayout());
		northPanel.setBackground(Color.WHITE);
		centerPanel = new JPanel(new BorderLayout());
		centerPanel.setBackground(Color.WHITE);
		
		north_northPanel = new JPanel(new GridLayout(2,1));
	//	north_northPanel = new JPanel(new GridLayout(3,1));
		north_northPanel.setBackground(Color.WHITE);
		
		north_roomChoicePanel = new JPanel();
		north_roomChoicePanel.setBackground(Color.WHITE);
		
		center_southPanel = new JPanel();
		center_southPanel.setBackground(Color.WHITE);
		center_centerPanel = new JPanel(new GridLayout(1,2));
		center_centerPanel.setBackground(Color.WHITE);
		
		firstPanel = new JPanel(new GridLayout(5,1));
		firstPanel.setBackground(Color.WHITE);
		secondPanel = new JPanel(new BorderLayout());
		secondPanel.setBackground(Color.WHITE);
		
		second_centerPanel = new JPanel(new BorderLayout());
		second_centerPanel.setBackground(Color.WHITE);
		
		second_southPanel = new JPanel(new GridLayout(2,1));
		second_southPanel.setBackground(Color.WHITE);
		
		second_northPanel = new JPanel(new GridLayout(3,1));
		second_northPanel.setBackground(Color.WHITE);
		
		image_textPan = new JPanel();
		image_textPan.setBackground(Color.WHITE);
		image_btnPan = new JPanel();
		image_btnPan.setBackground(Color.WHITE);
		
		first_pan = new JPanel[5];
		for(int i = 0; i < first_pan.length; i++) {
			first_pan[i] = new JPanel();
			first_pan[i].setBackground(Color.WHITE);
		}
		
		center_northPanel = new JPanel();
		center_northPanel.setBackground(Color.WHITE);
		
	}
	
	void makeLabel() {
		label = new JLabel[10];
		for(int i = 0; i<label.length; i++) {
			label[i] = new JLabel(label_txt[i]);
		}
	}
	
	void makeImageIcon() {
		room_road = new ImageIcon("image\\room_road.png");
		room_road_press = new ImageIcon("image\\room_road_press.png");
		room_road_rollover = new ImageIcon("image\\room_road_rollover.png");
		
		select = new ImageIcon("image\\select.png");
		select_press = new ImageIcon("image\\select_press.png");
		select_rollover = new ImageIcon("image\\select_rollover.png");
		
		image_road = new ImageIcon("image\\image_road.png");
		image_road_press = new ImageIcon("image\\image_road_press.png");
		image_road_rollover = new ImageIcon("image\\image_road_rollover.png");
		
		image_save = new ImageIcon("image\\image_save.png");
		image_save_press = new ImageIcon("image\\image_save_press.png");
		image_save_rollover = new ImageIcon("image\\image_save_rollover.png");
		
		delete = new ImageIcon("image\\delete.png");
		delete_press = new ImageIcon("image\\delete_press.png");
		delete_rollover = new ImageIcon("image\\delete_rollover.png");
		
		cancel = new ImageIcon("image\\cancel.png");
		cancel_press = new ImageIcon("image\\cancel_press.png");
		cancel_rollover = new ImageIcon("image\\cancel_rollover.png");
	}
	
	void makeButton() {
		btns = new JButton[6];
		for(int i = 0; i < btns.length; i++) {
			btns[i] = new JButton();
		}
		btns[0] = new JButton(room_road);
		btns[0].setPressedIcon(room_road_press);
		btns[0].setRolloverIcon(room_road_rollover);
		btns[0].setBorderPainted(false);
		btns[0].setFocusPainted(false);
		btns[0].setContentAreaFilled(false);
		btns[0].addActionListener(this);
		
		btns[1] = new JButton(image_road);
		btns[1].setPressedIcon(image_road_press);
		btns[1].setRolloverIcon(image_road_rollover);
		btns[1].setBorderPainted(false);
		btns[1].setFocusPainted(false);
		btns[1].setContentAreaFilled(false);
		btns[1].addActionListener(this);
		
		btns[2] = new JButton(image_save);
		btns[2].setPressedIcon(image_save_press);
		btns[2].setRolloverIcon(image_save_rollover);
		btns[2].setBorderPainted(false);
		btns[2].setFocusPainted(false);
		btns[2].setContentAreaFilled(false);
		btns[2].addActionListener(this);
		
		btns[3] = new JButton(delete);
		btns[3].setPressedIcon(delete_press);
		btns[3].setRolloverIcon(delete_rollover);
		btns[3].setBorderPainted(false);
		btns[3].setFocusPainted(false);
		btns[3].setContentAreaFilled(false);
		btns[3].addActionListener(this);
		
		btns[4] = new JButton(cancel);
		btns[4].setPressedIcon(cancel_press);
		btns[4].setRolloverIcon(cancel_rollover);
		btns[4].setBorderPainted(false);
		btns[4].setFocusPainted(false);
		btns[4].setContentAreaFilled(false);
		btns[4].addActionListener(this);
		
		btns[5] = new JButton(select);
		btns[5].setPressedIcon(select_press);
		btns[5].setRolloverIcon(select_rollover);
		btns[5].setBorderPainted(false);
		btns[5].setFocusPainted(false);
		btns[5].setContentAreaFilled(false);
		btns[5].addActionListener(this);
	}
	void makeRest() {
		room_text = new JTextField(10);
		price_text = new JTextField(20);
		image_text = new JTextField(30);
		room_idText = new JTextField(10);
		
		room_combo = new JComboBox<String>(room_texts);
		room_combo.addActionListener(this);
		head_combo = new JComboBox<String>(head_texts);
		
		res_ok = new JRadioButton("예약 가능");
		res_ok.setBackground(Color.WHITE);
		res_no = new JRadioButton("예약 불가능");
		res_no.setBackground(Color.WHITE);
		buttongroup = new ButtonGroup();
		buttongroup.add(res_ok);
		buttongroup.add(res_no);
	}
//	EmployeeInfo_main main
	
	public RoomInfo_delete() {
	//super(str);
		
		this.setLayout(new BorderLayout());
		
		makePanel();
		makeLabel();
		makeImageIcon();
		makeButton();
		makeRest();
		
		image = new BackImage3();
		
		btns[1].addActionListener(new LoadActionListener3(this, image, main));
		
		btns[2].addActionListener(new SaveActionListener3(this, main));
		
		north_roomChoicePanel.add(label[1]); north_roomChoicePanel.add(room_combo);
		north_roomChoicePanel.add(btns[0]);
		
		label[0].setHorizontalAlignment(label[0].CENTER);
		
		Font font = new Font("Sandoll 고딕 01 Light", Font.BOLD, 30);
		Color fontColor = new Color(116,116,116);
		
		label[0].setFont(font);
		label[0].setForeground(fontColor);
		
		Font lblFont = new Font("Sandoll 고딕 01 Light", Font.PLAIN, 15);
		Color lblFontColor = new Color(76,76,76);
		
		res_ok.setFont(lblFont); res_no.setFont(lblFont);
		res_ok.setForeground(lblFontColor); res_no.setForeground(lblFontColor);
		
		for(int i = 1; i < label.length; i++) {
			label[i].setFont(lblFont);
			label[i].setForeground(lblFontColor);
		}
		
		Color priceInfoColor = new Color(255,94,0);
		
		label[5].setForeground(priceInfoColor);
		
		Font imageFont = new Font("Sandoll 고딕 01 Light", Font.BOLD, 23);
		
		label[9].setFont(imageFont);
		
		north_northPanel.add(label[0]);
		north_northPanel.add(north_roomChoicePanel);
		table = new JTable();
		table.addMouseListener(this);
		scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(800,75));
		
		
		
		northPanel.add("North",north_northPanel);
		northPanel.add("Center", new JLabel("            "));
		northPanel.add("South",scroll);
		
		centerPanel.add("North",center_northPanel);
		centerPanel.add("Center",new JLabel(""));
		
		first_pan[0].add(label[2]); first_pan[0].add(room_idText); first_pan[0].add(label[3]);
		first_pan[1].add(label[4]); first_pan[1].add(price_text);
		first_pan[2].add(label[5]); 
		first_pan[3].add(label[6]); first_pan[3].add(head_combo); first_pan[3].add(label[7]);
		first_pan[4].add(label[8]); first_pan[4].add(res_ok); first_pan[4].add(res_no);
		
		for(int i = 0; i < first_pan.length; i++) {
			firstPanel.add(first_pan[i]);
		}
		
		second_centerPanel.add("Center",image);
		
		label[9].setHorizontalAlignment(label[9].CENTER);
		
		second_northPanel.add(new JLabel(""));
		second_northPanel.add(label[9]);
		second_northPanel.add(new JLabel(""));
		
		secondPanel.add("North",second_northPanel);
		secondPanel.add("Center",second_centerPanel);
		secondPanel.add("West", new JLabel("                        "));
		secondPanel.add("East", new JLabel("                        "));
		
		image_textPan.add(image_text);
		image_btnPan.add(btns[1]); image_btnPan.add(btns[2]);
		
		second_southPanel.add(image_textPan); second_southPanel.add(image_btnPan);
		
		secondPanel.add("South",second_southPanel);
		
		center_centerPanel.add(firstPanel); center_centerPanel.add(secondPanel);
		
		center_southPanel.add(btns[3]); center_southPanel.add(btns[4]);
		
		centerPanel.add("Center", center_centerPanel);
		centerPanel.add("South", center_southPanel);
		
		this.add("North", northPanel);
		this.add("Center",centerPanel);
		//this.add("Center",scroll);
		this.setVisible(true);
		this.setSize(800, 500);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
	//	new Gaeksil3();
	}

}