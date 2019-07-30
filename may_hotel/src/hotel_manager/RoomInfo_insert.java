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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
class BackImage extends JPanel{
	
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

class LoadActionListener implements ActionListener{

	RoomInfo_insert panel;
	BackImage image;
	FileDialog fileDialog;
//	EmployeeInfo_main main;
	
	hotel_customer.Screen main;
	
	static String fileName;
	
	static BufferedInputStream bis;
	
	LoadActionListener(RoomInfo_insert panel, BackImage image, hotel_customer.Screen main){
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

class SaveActionListener implements ActionListener{

	FileDialog fileDialog;
	RoomInfo_insert panel;
	BufferedOutputStream bos;
//	EmployeeInfo_main main;
	
	hotel_customer.Screen main;
	
	SaveActionListener(RoomInfo_insert panel, hotel_customer.Screen main){
		this.panel = panel;
		this.main = main;
	}
	
	static String path;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		fileDialog = new FileDialog(main,"이미지 파일 저장", FileDialog.SAVE);
		fileDialog.setFile(LoadActionListener.fileName);
		fileDialog.setVisible(true);
		path = fileDialog.getDirectory()+fileDialog.getFile();
		panel.image_text.setText(path);
		try {
			bos = new BufferedOutputStream(new FileOutputStream(path));
			int data = 0;
			while((data = LoadActionListener.bis.read()) != -1) {
				bos.write(data);
			}
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(panel, "이미지 파일 저장에 실패 하였습니다");
		}finally {
			try {
				bos.close();
				LoadActionListener.bis.close();
			}catch(Exception exe) {
				
			}
		}
	}
	
}

public class RoomInfo_insert extends JPanel implements ActionListener{
	JLabel[] label; 
	
	JButton[] btns;
	
	JTextField room_text, price_text, image_text;
	
	JComboBox room_combo, head_combo;
 
	String[] label_txt = {"객실 정보 등록", "객실 선택", "객실 호수", "  호",
			"예약 금액" , "※상기 금액은 1박 기준 입니다.", "수용 인원     ", "     명", "객실 이미지 업로드"};
	
	String[] room_texts = {"STANDARD", "SUPERIOR", "DELUXE", "EXECUTIVE" };
	
	String[] head_texts = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

	ImageIcon select, select_press, select_rollover, image_road, image_road_press, image_road_rollover,
			      image_save, image_save_press, image_save_rollover, insert, insert_press, insert_rollover, cancel, cancel_press, cancel_rollover;
	
	JPanel[] panel;
	
	JPanel northPanel, centerPanel, southPanel;
	JPanel center_firstPan, center_secondPan;
	JPanel second_westPanel, second_eastPanel, second_northPanel, second_centerPanel, second_southPanel;
	
	JPanel image_btnPanel;
	JPanel image_textPanel;
	
	BackImage image;
	
//	EmployeeInfo_main main;
	
	hotel_customer.Screen main;
	
	void makePanel() {
		northPanel = new JPanel(new GridLayout(3,1));
		northPanel.setBackground(Color.WHITE);
		centerPanel = new JPanel(new GridLayout(1,2));
		centerPanel.setBackground(Color.WHITE);
		southPanel = new JPanel();
		southPanel.setBackground(Color.WHITE);
		
		center_firstPan = new JPanel(new GridLayout(7,1));
		center_firstPan.setBackground(Color.WHITE);
		center_secondPan = new JPanel(new BorderLayout());
		center_secondPan.setBackground(Color.WHITE);
		
		second_westPanel = new JPanel();
		second_westPanel.setBackground(Color.WHITE);
		second_eastPanel = new JPanel();
		second_eastPanel.setBackground(Color.WHITE);
		second_northPanel = new JPanel();
		second_northPanel.setBackground(Color.WHITE);
		second_centerPanel = new JPanel(new BorderLayout());
		second_centerPanel.setBackground(Color.WHITE);
		second_southPanel = new JPanel(new GridLayout(2,1));
		second_southPanel.setBackground(Color.WHITE);
		
		image_btnPanel = new JPanel();
		image_btnPanel.setBackground(Color.WHITE);
		
		image_textPanel = new JPanel();
		image_textPanel.setBackground(Color.WHITE);
		
		panel = new JPanel[7];
		for(int i = 0; i<panel.length; i++) {
			panel[i] = new JPanel();
			panel[i].setBackground(Color.WHITE);
		}
	}
	
	void makeLabel() {
		label = new JLabel[9];
		for(int i = 0; i<label.length; i++) {
			label[i] = new JLabel(label_txt[i]);
		}
	}
	
	void UseButton() {
		select = new ImageIcon("image\\select.png");
		select_press = new ImageIcon("image\\select_press.png");
		select_rollover = new ImageIcon("image\\select_rollover.png");
		
		image_road = new ImageIcon("image\\image_road.png");
		image_road_press = new ImageIcon("image\\image_road_press.png");
		image_road_rollover = new ImageIcon("image\\image_road_rollover.png");
		
		image_save = new ImageIcon("image\\image_save.png");
		image_save_press = new ImageIcon("image\\image_save_press.png");
		image_save_rollover = new ImageIcon("image\\image_save_rollover.png");
		
		insert = new ImageIcon("image\\insert.png");
		insert_press = new ImageIcon("image\\insert_press.png");
		insert_rollover = new ImageIcon("image\\insert_rollover.png");
		
		cancel = new ImageIcon("image\\cancel.png");
		cancel_press = new ImageIcon("image\\cancel_press.png");
		cancel_rollover = new ImageIcon("image\\cancel_rollover.png");
	
	}
	
	void makeButton() {
		btns = new JButton[5];	
		for(int i = 0; i < btns.length; i++) {
			btns[i] = new JButton();
		}
		btns[0] = new JButton(select);
		btns[0].setPressedIcon(select_press);
		btns[0].setRolloverIcon(select_rollover);
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
		
		btns[2] = new JButton(image_save);
		btns[2].setPressedIcon(image_save_press);
		btns[2].setRolloverIcon(image_save_rollover);
		btns[2].setBorderPainted(false);
		btns[2].setFocusPainted(false);
		btns[2].setContentAreaFilled(false);
		
		btns[3] = new JButton(insert);
		btns[3].setPressedIcon(insert_press);
		btns[3].setRolloverIcon(insert_rollover);
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
	}
	
	void makeTextbox() {
		room_text = new JTextField(10);
		price_text = new JTextField(20);
		image_text = new JTextField(30);
		
		room_combo = new JComboBox<String>(room_texts);
		head_combo = new JComboBox<String>(head_texts);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object obj = arg0.getSource();
		if(obj == btns[0]) {
			String room_type = (String)room_combo.getSelectedItem();
			int room_id = Integer.parseInt(room_text.getText());
			CRUDprocess_emp crud = new CRUDprocess_emp();
			RoomInfo2 info2 = new RoomInfo2();
			info2.setRoom_type(room_type);
			info2.setRoom_id(room_id);
			RoomInfo info = new RoomInfo();
			info = crud.selectRoomInfoByRoomId(info2);
			if(info != null) {
				JOptionPane.showMessageDialog(this, "이미 등록되어 있는 객실입니다.");
				room_text.setText(" ");
				room_text.setText("");
			}else {
				JOptionPane.showMessageDialog(this, "등록가능한 객실입니다.");
			}
		}
		if(obj == btns[3]) {
			if(room_text.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "객실 호수를 확인해 주세요");
			}else {
				if(price_text.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "객실의 가격정보를 입력해주세요.");
				}else {
					String[] str = {"등 록","취 소"}; 
					int select = JOptionPane.showOptionDialog(this, "등록하시겠습니까?", "등 록", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, str, str[0]); 
					if(select == JOptionPane.YES_OPTION) {
						Integer room_id = Integer.parseInt(room_text.getText());
						String room_type = (String)room_combo.getSelectedItem();
						Integer room_price = Integer.parseInt(price_text.getText());
						String room_check = "Y";
						Integer room_head = head_combo.getSelectedIndex()+1;
						String room_image = image_text.getText();
						RoomInfo info = new RoomInfo();
						info.setRoom_id(room_id);
						info.setRoom_type(room_type);
						info.setRoom_price(room_price);
						info.setRoom_check(room_check);
						info.setRoom_head(room_head);
						info.setRoom_image(room_image);
						CRUDprocess_emp crud = new CRUDprocess_emp();
						int result = crud.insertIntoRoomInfo(info);
						if(result > 0) {
							JOptionPane.showMessageDialog(this, "객실 정보가 등록되었습니다.");
							room_combo.setSelectedIndex(0);
							room_text.setText(" ");
							room_text.setText("");
							price_text.setText(" ");
							price_text.setText("");
							head_combo.setSelectedIndex(0);
							image_text.setText(" ");
							image_text.setText("");
							image.removeAll();
						}else {
							JOptionPane.showMessageDialog(this, "객실 등록 과정에서 문제가 발생했습니다.");
						}
					}
				}
			}
		}
		if(obj == btns[4]) {
			room_combo.setSelectedIndex(0);
			room_text.setText(" ");
			room_text.setText("");
			price_text.setText(" ");
			price_text.setText("");
			head_combo.setSelectedIndex(0);
			image_text.setText(" ");
			image_text.setText("");
			image.removeAll();
		}
	}


	public RoomInfo_insert(hotel_customer.Screen main) {
//		super(str);

		this.setLayout(new BorderLayout());
		
		makePanel();
		makeLabel();
		UseButton();
		makeButton();
		makeTextbox();
		
		image = new BackImage();
		
		btns[1].addActionListener(new LoadActionListener(this, image, main));
		
		btns[2].addActionListener(new SaveActionListener(this,main));
		
		Font font = new Font("Sandoll 고딕 01 Light", Font.BOLD, 30);
		Color fontColor = new Color(116,116,116);
		
		label[0].setFont(font);
		label[0].setForeground(fontColor);
		label[0].setHorizontalAlignment(label[0].CENTER);
		
		northPanel.add(new JLabel(""));
		northPanel.add(label[0]);
		northPanel.add(new JLabel(""));
		
		Font lblFont = new Font("Sandoll 고딕 01 Light", Font.PLAIN, 15);
		Color lblFontColor = new Color(76,76,76);
		
		for(int i = 1; i < label.length; i++) {
			label[i].setFont(lblFont);
			label[i].setForeground(lblFontColor);
		}
		
		Color infoColor = new Color(255,94,0);
		label[5].setForeground(infoColor);
		
		Font imageFont = new Font("Sandoll 고딕 01 Light", Font.BOLD, 23);
		
		label[8].setFont(imageFont);
		
		panel[0].add(new JLabel(""));
		panel[1].add(label[1]); panel[1].add(room_combo);
		panel[2].add(label[2]); panel[2].add(room_text); panel[2].add(label[3]); panel[2].add(btns[0]);
		panel[3].add(label[4]); panel[3].add(price_text);
		panel[4].add(label[5]);
		panel[5].add(label[6]); panel[5].add(head_combo); panel[5].add(label[7]);
		panel[6].add(new JLabel(""));
		
		for(int i = 0; i < panel.length; i++) {
			center_firstPan.add(panel[i]);
		}
		
		second_northPanel.add(label[8]);
		second_centerPanel.add("Center",image);
		
		image_textPanel.add(image_text);
		image_btnPanel.add(btns[1]); image_btnPanel.add(btns[2]);
		second_southPanel.add(image_textPanel);
		second_southPanel.add(image_btnPanel);
		
		second_westPanel.add(new JLabel("                    "));
		second_eastPanel.add(new JLabel("                    "));
		
		center_secondPan.add("North", second_northPanel);
		center_secondPan.add("Center", second_centerPanel);
		center_secondPan.add("South", second_southPanel);
		center_secondPan.add("West", second_westPanel);
		center_secondPan.add("East", second_eastPanel);
		
		centerPanel.add(center_firstPan); centerPanel.add(center_secondPan);
		
		southPanel.add(btns[3]); southPanel.add(btns[4]);
		
		this.add("North", northPanel);
		this.add("Center", centerPanel);
		this.add("South", southPanel);
		
		this.setVisible(true);
		this.setSize(800, 500);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}

//	public static void main(String[] args) {
//		new RoomInfo_insert("�������� ��� �ý���");
//	}

}