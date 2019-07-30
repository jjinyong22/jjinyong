package hotel_manager;

import java.awt.BorderLayout;
import java.awt.Color;
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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.AbstractTableModel;

class EventModel2 extends AbstractTableModel {

	private Object[][] tableData;
	private int cols, rows;
	private String[] columnName = { "이벤트번호", "이벤트이름", "이벤트내용", "이벤트시작일", "이벤트종료일", "이벤트이미지경로" };
	private List<EventInfo> list;
	EventInfo_update event_update;

	EventModel2(EventInfo_update event_update) {
		this.event_update = event_update;
		String year = String.valueOf(event_update.combo.getSelectedItem());
		String month = String.valueOf(event_update.combo2.getSelectedItem());
		if (Integer.parseInt(month) < 10) {
			month = "0" + month;
		}
		String date = year + "-" + month;
		CRUDprocess_emp crud = new CRUDprocess_emp();
		list = crud.selectEventInfoByDate(date);
		setData();
	}

	private void setData() {
		Iterator it = list.iterator();
		rows = list.size();
		cols = columnName.length;
		tableData = new Object[rows][cols];
		int r = 0;
		while (it.hasNext()) {
			EventInfo info = (EventInfo) it.next();
			tableData[r][0] = info.getEvent_id();
			tableData[r][1] = info.getEvent_name();
			tableData[r][2] = info.getEvent_info();
			tableData[r][3] = info.getEvent_begin();
			tableData[r][4] = info.getEvent_close();
			tableData[r][5] = info.getEvent_image();
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

class BackImage_event2 extends JPanel {
	Image img;

	void setImage(String path) {
		img = Toolkit.getDefaultToolkit().getImage(path);
	}

	@Override
	public void paint(Graphics arg0) {
		arg0.clearRect(0, 0, this.getWidth(), this.getHeight());
		if (img != null) {
			arg0.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
}

class LoadActionListener_event2 implements ActionListener {

	EventInfo_update panel;
	BackImage_event2 image;
	FileDialog fileDialog;
//	EmployeeInfo_main main;
	
	hotel_customer.Screen main;

	static String fileName;

	static BufferedInputStream bis;

	LoadActionListener_event2(EventInfo_update panel, BackImage_event2 image, hotel_customer.Screen main) {
		this.panel = panel;
		this.image = image;
		this.main = main;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		fileDialog = new FileDialog(main, "이미지 파일 열기", FileDialog.LOAD);
		fileDialog.setVisible(true);
		fileName = fileDialog.getFile();
		String path = fileDialog.getDirectory() + fileDialog.getFile();
		panel.imagePath.setText(path);
		image.setImage(path);
		image.repaint();
		try {
			bis = new BufferedInputStream(new FileInputStream(path));
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(panel, "이미지 파일을 로드할 수 없습니다.");
		}
	}
}


class SaveActionListener_event2 implements ActionListener {
	FileDialog fileDialog;
	EventInfo_update panel;
	BufferedOutputStream bos;
//	EmployeeInfo_main main;
	
	hotel_customer.Screen main;

	SaveActionListener_event2(EventInfo_update panel, hotel_customer.Screen main) {
		this.panel = panel;
		this.main = main;
	}

	static String path;

	@Override
	public void actionPerformed(ActionEvent arg0) {
		fileDialog = new FileDialog(main, "이미지 파일 저장", FileDialog.SAVE);		fileDialog.setFile(LoadActionListener_event1.fileName);
		fileDialog.setVisible(true);
		path = fileDialog.getDirectory() + fileDialog.getFile();
		panel.imagePath.setText(path);
		try {
			bos = new BufferedOutputStream(new FileOutputStream(path));
			int data = 0;
			while ((data = LoadActionListener_event2.bis.read()) != -1) {
				bos.write(data);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(panel, "이미지 파일 저장에 실패 하였습니다.");
		} finally {
			try {
				bos.close();
				LoadActionListener_event2.bis.close();
			} catch (Exception exe) {

			}
		}
	}

}

public class EventInfo_update extends JPanel implements ActionListener, MouseListener {
	JLabel labels;
	JLabel[] label;

	JButton[] btns;

	JPanel firstPanel, secondPanel;

	JPanel first_northPanel, first_centerPanel;

	JPanel second_center, second_south;

	JPanel second_first, second_second;

	JPanel second_northPanel, second_centerPanel, second_southPanel;

	JPanel roadPanel;

	JPanel[] firsts;

	JPanel imgBtnPanel;

	JPanel imgPathPanel;

	JTextField textfield, textfield2, textfield3;

	JTextArea textarea;

	JScrollPane scroll;

	JComboBox combo, combo2;

	String[] label_txt = { "이벤트 정보 변경", "년", "월", "제 목", "이벤트 오픈날짜", "이벤트 종료날짜" };
	JLabel imageLabel;

	JTextField imagePath;

	ImageIcon event_road, event_road_press, event_road_rollover, choice, choice_press, choice_rollover, update,
			update_press, update_rollover, cancel, cancel_press, cancel_rollover;

	ImageIcon image_road, image_road_press, image_road_rollover, image_save, image_save_press, image_save_rollover;

	BackImage_event2 image;

//	EmployeeInfo_main main;
	
	hotel_customer.Screen main;

	JTable table;

	JScrollPane table_scroll;

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object obj = arg0.getSource();
		if (obj == btns[1]) {
			new EventUpdate_calendar_open(this);
		}
		if (obj == btns[2]) {
			System.out.println("선택됨");
			new EventUpdate_calendar_close(this);
		}
		if (obj == btns[0]) {
			table.setModel(new EventModel2(this));
		}
		if (obj == btns[3]) {
			if (textfield.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "이벤트 제목을 입력해 주세요.");
			} else {
				if (!textfield2.getText().equals("") && !textfield3.getText().equals("")) {
					String[] str = { "변 경", "취 소" };
					int select = JOptionPane.showOptionDialog(this, "변경하시겠습니까?", "변 경", JOptionPane.YES_NO_OPTION,
							JOptionPane.INFORMATION_MESSAGE, null, str, str[0]);
					if (select == JOptionPane.YES_OPTION) {
						String id = table.getValueAt(table.getSelectedRow(), 0) + "";
						CRUDprocess_emp crud = new CRUDprocess_emp();
						EventInfo info = new EventInfo();
						info.setEvent_id(Integer.parseInt(id));
						info.setEvent_name(textfield.getText());
						info.setEvent_begin(textfield2.getText());
						info.setEvent_close(textfield3.getText());
						info.setEvent_info(textarea.getText());
						info.setEvent_image(imagePath.getText());
						int result = crud.updateEventInfo(info);
						if (result > 0) {
							JOptionPane.showMessageDialog(this, "이벤트 정보가 변경되었습니다.");
							textfield.setText(" ");
							textfield.setText("");
							textfield2.setText(" ");
							textfield2.setText("");
							textfield3.setText(" ");
							textfield3.setText("");
							textarea.setText(" ");
							textarea.setText("");
							imagePath.setText(" ");
							imagePath.setText("");
							image.removeAll();
						} else {
							JOptionPane.showMessageDialog(this, "이벤트 변경 과정에서 문제가 발생했습니다.");
						}
					}
				} else {
					JOptionPane.showMessageDialog(this, "이벤트 시작 및 종료날짜를 설정해 주세요");
				}
			}
		}
		if (obj == btns[4]) {
			textfield.setText(" ");
			textfield.setText("");
			textfield2.setText(" ");
			textfield2.setText("");
			textfield3.setText(" ");
			textfield3.setText("");
			textarea.setText(" ");
			textarea.setText("");
			imagePath.setText(" ");
			imagePath.setText("");
			image.removeAll();
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		int selectedRow = table.getSelectedRow();
		String event_name = table.getValueAt(selectedRow, 1) + "";
		String event_begin = table.getValueAt(selectedRow, 3) + "";
		String event_close = table.getValueAt(selectedRow, 4) + "";
		String event_info = table.getValueAt(selectedRow, 2) + "";
		String event_image = table.getValueAt(selectedRow, 5) + "";
		if (event_image.equals("")) {
			event_image = "";
		}
		textfield.setText(event_name);
		textfield2.setText(event_begin);
		textfield3.setText(event_close);
		textarea.setText(event_info);
		imagePath.setText(event_image);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	void makePanel() {
		firstPanel = new JPanel(new BorderLayout());
		firstPanel.setBackground(Color.WHITE);

		secondPanel = new JPanel(new BorderLayout());
		secondPanel.setBackground(Color.WHITE);

		second_center = new JPanel(new GridLayout(1, 2));
		second_center.setBackground(Color.WHITE);

		second_south = new JPanel();
		second_south.setBackground(Color.WHITE);

		first_northPanel = new JPanel(new GridLayout(4, 1));
		first_northPanel.setBackground(Color.WHITE);

		first_centerPanel = new JPanel(new BorderLayout());
		first_centerPanel.setBackground(Color.WHITE);

		second_first = new JPanel(new GridLayout(6, 1));
		second_first.setBackground(Color.WHITE);

		second_second = new JPanel(new BorderLayout());
		second_second.setBackground(Color.WHITE);

		second_southPanel = new JPanel(new GridLayout(2, 1));
		second_southPanel.setBackground(Color.WHITE);

		second_centerPanel = new JPanel(new BorderLayout());
		second_centerPanel.setBackground(Color.WHITE);

		image = new BackImage_event2();

		second_southPanel = new JPanel(new GridLayout(2, 1));
		second_southPanel.setBackground(Color.WHITE);

		second_northPanel = new JPanel(new GridLayout(2, 1));
		second_northPanel.setBackground(Color.WHITE);

		roadPanel = new JPanel();
		roadPanel.setBackground(Color.WHITE);

		firsts = new JPanel[6];
		for (int i = 0; i < firsts.length; i++) {
			firsts[i] = new JPanel();
			firsts[i].setBackground(Color.WHITE);
		}

		imgBtnPanel = new JPanel();
		imgBtnPanel.setBackground(Color.WHITE);

		imgPathPanel = new JPanel();
		imgPathPanel.setBackground(Color.WHITE);
	}

	void makeLabel() {
		label = new JLabel[6];
		for (int i = 0; i < label.length; i++) {
			label[i] = new JLabel(label_txt[i]);
		}

		Font font = new Font("Sandoll 고딕 01 Light", Font.BOLD, 30);
		Color fontColor = new Color(116, 116, 116);

		label[0].setFont(font);
		label[0].setForeground(fontColor);

		Font lblFont = new Font("Sandoll 고딕 01 Light", Font.PLAIN, 15);
		Color lblFontColor = new Color(76, 76, 76);

		for (int i = 1; i < label.length; i++) {
			label[i].setFont(lblFont);
			label[i].setForeground(lblFontColor);
		}

	}

	void UseButton() {
		event_road = new ImageIcon("image\\event_road.png");
		event_road_press = new ImageIcon("image\\event_road_press.png");
		event_road_rollover = new ImageIcon("image\\event_road_rollover.png");

		choice = new ImageIcon("image\\choice.png");
		choice_press = new ImageIcon("image\\choice_press.png");
		choice_rollover = new ImageIcon("image\\choice_rollover.png");

		update = new ImageIcon("image\\update.png");
		update_press = new ImageIcon("image\\update_press.png");
		update_rollover = new ImageIcon("image\\update_rollover.png");

		cancel = new ImageIcon("image\\cancel.png");
		cancel_press = new ImageIcon("image\\cancel_press.png");
		cancel_rollover = new ImageIcon("image\\cancel_rollover.png");

		image_road = new ImageIcon("image\\image_road.png");
		image_road_press = new ImageIcon("image\\image_road_press.png");
		image_road_rollover = new ImageIcon("image\\image_road_rollover.png");

		image_save = new ImageIcon("image\\image_save.png");
		image_save_press = new ImageIcon("image\\image_save_press.png");
		image_save_rollover = new ImageIcon("image\\image_save_rollover.png");
	}

	void makeButton() {
		btns = new JButton[7];
		for (int i = 0; i < btns.length; i++) {
			btns[i] = new JButton();
		}

		btns[0] = new JButton(event_road);
		btns[0].setPressedIcon(event_road_press);
		btns[0].setRolloverIcon(event_road_rollover);
		btns[0].setBorderPainted(false);
		btns[0].setFocusPainted(false);
		btns[0].setContentAreaFilled(false);
		btns[0].addActionListener(this);

		btns[1] = new JButton(choice);
		btns[1].setPressedIcon(choice_press);
		btns[1].setRolloverIcon(choice_rollover);
		btns[1].setBorderPainted(false);
		btns[1].setFocusPainted(false);
		btns[1].setContentAreaFilled(false);
		btns[1].addActionListener(this);

		btns[2] = new JButton(choice);
		btns[2].setPressedIcon(choice_press);
		btns[2].setRolloverIcon(choice_rollover);
		btns[2].setBorderPainted(false);
		btns[2].setFocusPainted(false);
		btns[2].setContentAreaFilled(false);
		btns[2].addActionListener(this);

		btns[3] = new JButton(update);
		btns[3].setPressedIcon(update_press);
		btns[3].setRolloverIcon(update_rollover);
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

		btns[5] = new JButton(image_road);
		btns[5].setPressedIcon(image_road_press);
		btns[5].setRolloverIcon(image_road_rollover);
		btns[5].setBorderPainted(false);
		btns[5].setFocusPainted(false);
		btns[5].setContentAreaFilled(false);
		btns[5].addActionListener(new LoadActionListener_event2(this, image, main));

		btns[6] = new JButton(image_save);
		btns[6].setPressedIcon(image_save_press);
		btns[6].setRolloverIcon(image_save_rollover);
		btns[6].setBorderPainted(false);
		btns[6].setFocusPainted(false);
		btns[6].setContentAreaFilled(false);
		btns[6].addActionListener(new SaveActionListener_event2(this, main));
	}

	void makeRest() {
		textfield = new JTextField(20);
		textfield2 = new JTextField(15);
		textfield3 = new JTextField(15);
		textarea = new JTextArea(2, 30);

		scroll = new JScrollPane(textarea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		imageLabel = new JLabel("이벤트 이미지");

		Font imgFont = new Font("Sandoll 고딕 01 Light", Font.BOLD, 23);
		Color imgFontColor = new Color(76, 76, 76);

		imageLabel.setFont(imgFont);
		imageLabel.setForeground(imgFontColor);

		combo = new JComboBox();
		for (int i = 2019; i >= 1990; i--) {
			combo.addItem(i + "");
		}

		combo2 = new JComboBox();
		for (int i = 1; i <= 12; i++) {
			combo2.addItem(i + "");
		}

		roadPanel.add(combo);
		roadPanel.add(label[1]);
		roadPanel.add(combo2);
		roadPanel.add(label[2]);
		roadPanel.add(btns[0]);

		label[0].setHorizontalAlignment(label[0].CENTER);

		first_northPanel.add(new JLabel(""));
		first_northPanel.add(label[0]);
		first_northPanel.add(new JLabel(""));
		first_northPanel.add(roadPanel);

		table = new JTable();
		table.addMouseListener(this);
		table_scroll = new JScrollPane(table);

		first_centerPanel.add("Center", table_scroll);

		firstPanel.add("North", first_northPanel);
		firstPanel.add("Center", first_centerPanel);

		firsts[0].add(new JLabel(""));
		firsts[1].add(label[3]);
		firsts[1].add(textfield);
		firsts[2].add(label[4]);
		firsts[2].add(textfield2);
		firsts[2].add(btns[1]);
		firsts[3].add(label[5]);
		firsts[3].add(textfield3);
		firsts[3].add(btns[2]);
		firsts[4].add(scroll);
		firsts[5].add(new JLabel(""));

		second_first.add(firsts[0]);
		second_first.add(firsts[1]);
		second_first.add(firsts[2]);
		second_first.add(firsts[3]);
		second_first.add(firsts[4]);
		second_first.add(firsts[5]);

		imageLabel.setHorizontalAlignment(imageLabel.CENTER);

		second_northPanel.add(imageLabel);
		second_northPanel.add(new JLabel(""));

		second_centerPanel.add("Center", image);

		imgBtnPanel.add(btns[5]);
		imgBtnPanel.add(btns[6]);

		imagePath = new JTextField(30);

		imgPathPanel.add(imagePath);

		second_southPanel.add(imgPathPanel);
		second_southPanel.add(imgBtnPanel);

		second_second.add("North", second_northPanel);
		second_second.add("Center", second_centerPanel);
		second_second.add("South", second_southPanel);
		second_second.add("West", new JLabel("              "));
		second_second.add("East", new JLabel("              "));

		second_center.add(second_first);
		second_center.add(second_second);

		second_south.add(btns[3]);
		second_south.add(btns[4]);

		secondPanel.add("Center", second_center);
		secondPanel.add("South", second_south);
	}

	EventInfo_update(hotel_customer.Screen main) {
		// super(str);
		this.setLayout(new GridLayout(2, 1));

		makePanel();
		makeLabel();
		UseButton();
		makeButton();
		makeRest();

		this.add(firstPanel);
		this.add(secondPanel);

		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	// public static void main(String[] args) {
	// new EventInfo_update("�̺�Ʈ ���� ȭ�� ver1.0");
	// }

}
