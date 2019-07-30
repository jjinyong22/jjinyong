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
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

class BackImage_event1 extends JPanel{
	Image img;
	
	void setImage(String path) {
		img = Toolkit.getDefaultToolkit().getImage(path);
	}

	@Override
	public void paint(Graphics arg0) {
		arg0.clearRect(0, 0, this.getWidth(), this.getHeight());
		if(img != null) {
			arg0.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
}
class LoadActionListener_event1 implements ActionListener{
	
	EventInfo_insert panel;
	BackImage_event1 image;
	FileDialog fileDialog;
//	EmployeeInfo_main main;
	
	hotel_customer.Screen main;
	
	static String fileName;
	
	static BufferedInputStream bis;
	
	LoadActionListener_event1(EventInfo_insert panel, BackImage_event1 image, hotel_customer.Screen main){
		this.panel = panel;
		this.image = image;
		this.main = main;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		fileDialog = new FileDialog(main,"이미지 파일 열기",FileDialog.LOAD);
		fileDialog.setVisible(true);
		fileName = fileDialog.getFile();
		String path = fileDialog.getDirectory()+fileDialog.getFile();
		panel.imagePath.setText(path);
		image.setImage(path);
		image.repaint();
		try {
			bis = new BufferedInputStream(new FileInputStream(path));
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(panel, "이미지 파일을 로드할 수 없습니다.");
		}
	}
}

class SaveActionListener_event1 implements ActionListener{
	FileDialog fileDialog;
	EventInfo_insert panel;
	BufferedOutputStream bos;
//	EmployeeInfo_main main;
	
	hotel_customer.Screen main;
	
	SaveActionListener_event1(EventInfo_insert panel, hotel_customer.Screen main){
		this.panel = panel;
		this.main = main;
	}
	
	static String path;

	@Override
	public void actionPerformed(ActionEvent arg0) {
		fileDialog = new FileDialog(main,"이미지 파일 저장", FileDialog.SAVE);
		fileDialog.setFile(LoadActionListener_event1.fileName);
		fileDialog.setVisible(true);
		path = fileDialog.getDirectory()+fileDialog.getFile();
		panel.imagePath.setText(path);
		try {
			bos = new BufferedOutputStream(new FileOutputStream(path));
			int data = 0;
			while((data = LoadActionListener_event1.bis.read()) != -1) {
				bos.write(data);
			}
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(panel, "이미지 파일 저장에 실패 하였습니다.");
		}finally {
			try {
				bos.close();
				LoadActionListener_event1.bis.close();
			}catch(Exception exe) {
				
			}
		}
	}
	
}

public class EventInfo_insert extends JPanel implements ActionListener{
	JLabel[] label; JLabel labels; 
	JButton[] btns; 
	JComboBox combo; JTable table; 
	JTextField textfield, textfield2, textfield3; 
	JTextArea textarea;
	JScrollPane scroll;
	JMenuBar menubar; JMenu[] menu;
	JMenuItem[] menuitem;
	String[] label_txt = {"이벤트 정보 등록", "제목  ", "이벤트 오픈날짜  ", "이벤트 종료날짜  "};
	
	ImageIcon choice, choice_press, choice_rollover, insert, insert_press, insert_rollover, cancel, cancel_press, cancel_rollover;

	JPanel centerPanel, northPanel, southPanel;
	
	JPanel center_first, center_second;
	
	JPanel second_southPanel;
	
	JTextField imagePath;
	
	JPanel pathPanel, imgbtnPanel;
	
	JPanel first_centerPanel, first_southPanel;
	
	JPanel namePanel, date1Panel, date2Panel, infoPanel;
	
	JPanel imgPanel;
	
	JPanel second_northPanel;
	
	JLabel imageLabel;
	
	BackImage_event1 image;
	
//	EmployeeInfo_main main;
	
	hotel_customer.Screen main;
	ImageIcon image_road, image_road_press, image_road_rollover, image_save, image_save_press, image_save_rollover;
	void makePanel() {
		northPanel = new JPanel(new GridLayout(3,1));
		northPanel.setBackground(Color.WHITE);
		
		centerPanel = new JPanel(new GridLayout(1,2));
		centerPanel.setBackground(Color.WHITE);
		
		southPanel = new JPanel();
		southPanel.setBackground(Color.WHITE);
		
		center_first = new JPanel(new BorderLayout());
		center_first.setBackground(Color.WHITE);
		
		center_second = new JPanel(new BorderLayout());
		center_second.setBackground(Color.WHITE);
		
		second_southPanel = new JPanel(new GridLayout(2,1));
		second_southPanel.setBackground(Color.WHITE);
		
		second_northPanel = new JPanel(new GridLayout(2,1));
		second_northPanel.setBackground(Color.WHITE);
		
		pathPanel = new JPanel();
		pathPanel.setBackground(Color.WHITE);
		
		imgbtnPanel = new JPanel();
		imgbtnPanel.setBackground(Color.WHITE);
		
		imgPanel = new JPanel(new BorderLayout());
		imgPanel.setBackground(Color.WHITE);
		
		image = new BackImage_event1();
		
		imgPanel.add("Center",image);
		
		first_centerPanel = new JPanel(new GridLayout(3,1));
		first_centerPanel.setBackground(Color.WHITE);
		
		namePanel = new JPanel();
		namePanel.setBackground(Color.WHITE);
		date1Panel = new JPanel();
		date1Panel.setBackground(Color.WHITE);
		date2Panel = new JPanel();
		date2Panel.setBackground(Color.WHITE);
		
		first_southPanel = new JPanel();
		first_southPanel.setBackground(Color.WHITE);
		
		infoPanel = new JPanel();
		infoPanel.setBackground(Color.WHITE);
	}
	
	void UseButton() {
		choice = new ImageIcon("image\\choice.png");
		choice_press = new ImageIcon("image\\choice_press.png");
		choice_rollover = new ImageIcon("image\\choice_rollover.png");
		
		insert = new ImageIcon("image\\insert.png");
		insert_press = new ImageIcon("image\\insert_press.png");
		insert_rollover = new ImageIcon("image\\insert_rollover.png");
		
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
		btns = new JButton[6];
		for(int i = 0; i < btns.length; i++) {
			btns[i] = new JButton();
		}
		
		btns[0] = new JButton(choice);
		btns[0].setPressedIcon(choice_press);
		btns[0].setRolloverIcon(choice_rollover);
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
		
		btns[2] = new JButton(insert);
		btns[2].setPressedIcon(insert_press);
		btns[2].setRolloverIcon(insert_rollover);
		btns[2].setBorderPainted(false);
		btns[2].setFocusPainted(false);
		btns[2].setContentAreaFilled(false);
		btns[2].addActionListener(this);
		
		btns[3] = new JButton(cancel);
		btns[3].setPressedIcon(cancel_press);
		btns[3].setRolloverIcon(cancel_rollover);
		btns[3].setBorderPainted(false);
		btns[3].setFocusPainted(false);
		btns[3].setContentAreaFilled(false);
		
		btns[4] = new JButton(image_road);
		btns[4].setPressedIcon(image_road_press);
		btns[4].setRolloverIcon(image_road_rollover);
		btns[4].setBorderPainted(false);
		btns[4].setFocusPainted(false);
		btns[4].setContentAreaFilled(false);
		
		btns[5] = new JButton(image_save);
		btns[5].setPressedIcon(image_save_press);
		btns[5].setRolloverIcon(image_save_rollover);
		btns[5].setBorderPainted(false);
		btns[5].setFocusPainted(false);
		btns[5].setContentAreaFilled(false);
		
	}
	
	void makeLabel() {
		label = new JLabel[4];
		for(int i = 0; i<label.length; i++) {
			label[i] = new JLabel(label_txt[i]);
		}

		Font font = new Font("Sandoll 고딕 01 Light", Font.BOLD, 30);
		Color fontColor = new Color(116,116,116);
		
		label[0].setFont(font);
		label[0].setForeground(fontColor);
		
		imageLabel = new JLabel("이벤트 이미지");
		
		Font lblFont = new Font("Sandoll 고딕 01 Light", Font.PLAIN, 15);
		Color lblFontColor = new Color(76,76,76);
		
		Font imgFont = new Font("Sandoll 고딕 01 Light", Font.BOLD, 23);
		Color imgFontColor = new Color(76,76,76);
		
		imageLabel.setFont(imgFont);
		imageLabel.setForeground(imgFontColor);
		
		for(int i = 1; i < label.length; i++) {
			label[i].setFont(lblFont);
			label[i].setForeground(lblFontColor);
		}
	}
	void makeRest() {
		textfield = new JTextField(20);
		textfield2 = new JTextField(20);
		textfield3 = new JTextField(20);
		textarea = new JTextArea(10,30);
		scroll = new JScrollPane(textarea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		namePanel.add(label[1]); namePanel.add(textfield);
		date1Panel.add(label[2]); date1Panel.add(textfield2); date1Panel.add(btns[0]);
		date2Panel.add(label[3]); date2Panel.add(textfield3); date2Panel.add(btns[1]);
		
		first_centerPanel.add(namePanel);
		first_centerPanel.add(date1Panel);
		first_centerPanel.add(date2Panel);
		
		infoPanel.add(scroll);
		
		first_southPanel.add(infoPanel);
		
		center_first.add("Center",first_centerPanel);
		center_first.add("South",first_southPanel);
		
		imageLabel.setHorizontalAlignment(imageLabel.CENTER);
		
		second_northPanel.add(imageLabel);
		second_northPanel.add(new JLabel(""));
		
		center_second.add("West",new JLabel("                "));
		center_second.add("North", second_northPanel);
		center_second.add("Center", imgPanel);
		center_second.add("East",new JLabel("                 "));
		
		imagePath = new JTextField(30);
		
		pathPanel.add(imagePath);
		
		imgbtnPanel.add(btns[4]); imgbtnPanel.add(btns[5]);
		
		second_southPanel.add(pathPanel); second_southPanel.add(imgbtnPanel);
		
		center_second.add("South",second_southPanel);
		
		centerPanel.add(center_first);
		centerPanel.add(center_second);
		
		southPanel.add(btns[2]); southPanel.add(btns[3]);
		
		label[0].setHorizontalAlignment(label[0].CENTER);
		
		northPanel.add(new JLabel(""));
		northPanel.add(label[0]);
		northPanel.add(new JLabel(""));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == btns[0]) {
			new EventRoad_calendar_open(this);
		}
		if(obj == btns[1]) {
			new EventRoad_calendar_close(this);
		}
		if(obj == btns[2]) {
			if(textfield.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "이벤트 제목을 입력해 주세요.");
			}else {
				if(!textfield2.getText().equals("")&&!textfield3.getText().equals("")) {
					String[] str = {"등 록","취 소"}; 
					int select = JOptionPane.showOptionDialog(this, "등록하시겠습니까?", "등 록", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, str, str[0]);
					if(select == JOptionPane.YES_OPTION) {
						CRUDprocess_emp crud = new CRUDprocess_emp();
						int event_num = crud.selectMaxEventNum()+1;
						String event_name = textfield.getText();
						String event_begin = textfield2.getText();
						String event_close = textfield3.getText();
						String event_info = textarea.getText();
						String event_image = imagePath.getText();
						EventInfo info = new EventInfo();
						info.setEvent_id(event_num);
						info.setEvent_name(event_name);
						info.setEvent_info(event_info);
						info.setEvent_begin(event_begin);
						info.setEvent_close(event_close);
						info.setEvent_image(event_image);
						int result = crud.insertIntoEventInfo(info);
						if(result > 0) {
							JOptionPane.showMessageDialog(this, "이벤트가 등록되었습니다.");
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
						}else {
							JOptionPane.showMessageDialog(this, "이벤트 등록 과정에서 문제가 발생했습니다.");
						}
					}
				}else {
					JOptionPane.showMessageDialog(this, "이벤트 시작 및 종료날짜를 설정해 주세요");
				}
			}
		
		}
		if(obj == btns[3]) {
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

	EventInfo_insert(hotel_customer.Screen main) {
//		super(str);
		this.setLayout(new BorderLayout());
		
		makePanel();	
		makeLabel();
		UseButton();	
		makeButton();
		makeRest();
		
		btns[4].addActionListener(new LoadActionListener_event1(this,image,main));
		
		btns[5].addActionListener(new SaveActionListener_event1(this,main));
		
		this.add("North", northPanel);
		this.add("Center", centerPanel);
		this.add("South",southPanel);
		 
		this.setVisible(true);
		this.setSize(800, 500);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}

//	public static void main(String[] args) {
//		new EventInfo_insert("이벤트  ver1.0");
//	}

}

