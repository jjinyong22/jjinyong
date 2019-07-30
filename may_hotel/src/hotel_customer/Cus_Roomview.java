package hotel_customer;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class Image_room extends JPanel{
	Image img;
	
	Image_room(String path){
		img = Toolkit.getDefaultToolkit().getImage(path);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}
	
}

public class Cus_Roomview extends JPanel implements ActionListener{
	CardLayout card;
	JPanel card_pan;
	JPanel center_pan, east_pan, btn_pan;
	JScrollPane[] scroll;
	JLabel[] label;
	JTextArea[] t_area;
	JButton[] btn;
	Font f;
	static Cus_Roomview standaer, superrior, deluxe, excutive;
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if(o == btn[0]) {
			card.first(card_pan);
		}else if(o == btn[1]) {
			card.previous(card_pan);
		}else if(o == btn[2]) {
			card.next(card_pan);
		}else if(o == btn[3]) {
			card.last(card_pan);
		}
		
	}
	void setting() {

		card = new CardLayout();
		card_pan = new JPanel(card);
		// set card
		
		center_pan = new JPanel(new GridLayout(1,2));
		east_pan = new JPanel(new GridLayout(6,1));
		btn_pan = new JPanel();
		// set panel
		
		
		label = new JLabel[3];
		t_area = new JTextArea[3];
		scroll = new JScrollPane[3];
		for(int i = 0; i < label.length; i++) {
			label[i] = new JLabel();
			t_area[i] = new JTextArea();
			scroll[i] = new JScrollPane(t_area[i]);
		}
		
		label[0].setText("방 타입");
		label[1].setText("방안내");
		label[2].setText("방 이용 기간");
		// set label, text
		
		btn = new JButton[4];
		for(int i = 0; i < btn.length; i++) {
			btn[i] = new JButton();
			btn[i].addActionListener(this);
		}
		btn[0].setIcon(new ImageIcon("image\\처음1.png"));
		btn[0].setRolloverIcon(new ImageIcon("image\\처음2.png"));
		btn[0].setPressedIcon(new ImageIcon("image\\처음3.png"));
		btn[0].setBorderPainted(false);
		btn[0].setContentAreaFilled(false);
		btn[0].setFocusPainted(false);
		
		btn[1].setIcon(new ImageIcon("image\\이전1.png"));
		btn[1].setRolloverIcon(new ImageIcon("image\\이전2.png"));
		btn[1].setPressedIcon(new ImageIcon("image\\이전3.png"));
		btn[1].setBorderPainted(false);
		btn[1].setContentAreaFilled(false);
		btn[1].setFocusPainted(false);
		
		btn[2].setIcon(new ImageIcon("image\\다음1.png"));
		btn[2].setRolloverSelectedIcon(new ImageIcon("image\\다음2.png"));
		btn[2].setPressedIcon(new ImageIcon("image\\다음3.png"));
		btn[2].setBorderPainted(false);
		btn[2].setContentAreaFilled(false);
		btn[2].setFocusPainted(false);
		
		btn[3].setIcon(new ImageIcon("image\\마지막1.png"));
		btn[3].setRolloverIcon(new ImageIcon("image\\마지막2.png"));
		btn[3].setPressedIcon(new ImageIcon("image\\마지막3.png"));
		btn[3].setBorderPainted(false);
		btn[3].setContentAreaFilled(false);
		btn[3].setFocusPainted(false);
		// set button
		
		
	}
	
	void standard(){

//		card_pan.add(new Image_room("src\\image\\���Ĵٵ�1.jpg"),"1");
//		card_pan.add(new Image_room("src\\image\\���Ĵٵ�2.jpg"),"2");
//		card_pan.add(new Image_room("src\\image\\���Ĵٵ�3.jpg"),"3");

		card_pan.add(new Image_room("image\\스탠다드1.jpg"),"1");
		card_pan.add(new Image_room("image\\스탠다드2.jpg"),"2");
		card_pan.add(new Image_room("image\\스탠다드3.jpg"),"3");

		t_area[0].setText("객실 유형 : 스탠다드룸"+'\n'+
				  "위치 : 7 ~ 14층" + '\n'+
				  "침대 : 더블(킹 사이즈),트윈"+'\n'+
				  "크기 : 36 제곱미터" + '\n'+
				  "룸구성 : 침실 1, 욕실 1, 화장실 1"+'\n'+
				  "최대 수용 인원 : 3명");

		t_area[1].setText("-55인치 스마트 TV(위성 TV 48개 채널)" + '\n' +
				  "-50~100Mbps 초고속 유·무선(wifi) 무료 인터넷 제공" + '\n' +
				  "-220V, 110V 전압 사용 가능" + '\n' +
				  "-커피·차 티백 무료 제공" + '\n' +
				  "-엑스트라 베드 1개 추가 36,300원 / 1박" + '\n' +
				  "-베이비 크립(무료)");

		t_area[2].setText("체크인 시간 : 오후 3시 이후" + '\n' +
				  "체크아웃 시간 : 낮 12시");
	}
	
	void superrior() {
		card_pan.add(new Image_room("image\\슈퍼리어1.jpg"),"1");
		card_pan.add(new Image_room("image\\슈퍼리어2.jpg"),"2");
		card_pan.add(new Image_room("image\\스탠다드3.jpg"),"3");
		t_area[0].setText("객실 유형 : 슈퍼리어룸"+'\n'+
				  "위치 : 7 ~ 14층" + '\n'+
				  "침대 : 더블(킹 사이즈),트윈"+'\n'+
				  "크기 : 36 제곱미터" + '\n'+
				  "룸구성 : 침실 1, 욕실 1, 화장실 1"+'\n'+
				  "최대 수용 인원 : 3명");

		t_area[1].setText("-55인치 스마트 TV(위성 TV 48개 채널)" + '\n' +
				  "-50~100Mbps 초고속 유·무선(wifi) 무료 인터넷 제공" + '\n' +
				  "-220V, 110V 전압 사용 가능" + '\n' +
				  "-커피·차 티백 무료 제공" + '\n' +
				  "-엑스트라 베드 1개 추가 36,300원 / 1박" + '\n' +
				  "-베이비 크립(무료)");

		t_area[2].setText("체크인 시간 : 오후 3시 이후" + '\n' +
				  "체크아웃 시간 : 낮 12시");
	}
	
	void deluxe() {
		card_pan.add(new Image_room("image\\디럭스1.jpg"),"1");
		card_pan.add(new Image_room("image\\디럭스2.jpg"),"2");
		card_pan.add(new Image_room("image\\디럭스3.jpg"),"3");
		t_area[0].setText("객실 유형 : 디럭스룸"+'\n'+
				  "위치 : 7 ~ 14층" + '\n'+
				  "침대 : 더블(킹 사이즈),트윈"+'\n'+
				  "크기 : 36 제곱미터" + '\n'+
				  "룸구성 : 침실 1, 욕실 1, 화장실 1"+'\n'+
				  "최대 수용 인원 : 3명");

		t_area[1].setText("-55인치 스마트 TV(위성 TV 48개 채널)" + '\n' +
				  "-50~100Mbps 초고속 유·무선(wifi) 무료 인터넷 제공" + '\n' +
				  "-220V, 110V 전압 사용 가능" + '\n' +
				  "-커피·차 티백 무료 제공" + '\n' +
				  "-엑스트라 베드 1개 추가 36,300원 / 1박" + '\n' +
				  "-베이비 크립(무료)");

		t_area[2].setText("체크인 시간 : 오후 3시 이후" + '\n' +
				  "체크아웃 시간 : 낮 12시");
	}
	
	void excutive() {
		card_pan.add(new Image_room("image\\익스큐티브1.jpg"),"1");
		card_pan.add(new Image_room("image\\익스큐티브2.jpg"),"2");
		card_pan.add(new Image_room("image\\익스큐티브3.jpg"),"3");
		card_pan.add(new Image_room("image\\익스큐티브4.jpg"),"4");
		t_area[0].setText("객실 유형 : "+'\n'+
				  "위치 : 7 ~ 14층" + '\n'+
				  "침대 : 더블(킹 사이즈),트윈"+'\n'+
				  "크기 : 36 제곱미터" + '\n'+
				  "룸구성 : 침실 1, 욕실 1, 화장실 1"+'\n'+
				  "최대 수용 인원 : 3명");

		t_area[1].setText("-55인치 스마트 TV(위성 TV 48개 채널)" + '\n' +
				  "-50~100Mbps 초고속 유·무선(wifi) 무료 인터넷 제공" + '\n' +
				  "-220V, 110V 전압 사용 가능" + '\n' +
				  "-커피·차 티백 무료 제공" + '\n' +
				  "-엑스트라 베드 1개 추가 36,300원 / 1박" + '\n' +
				  "-베이비 크립(무료)");

		t_area[2].setText("체크인 시간 : 오후 3시 이후" + '\n' +
				  "체크아웃 시간 : 낮 12시");
	}
	
	void insert() {
		for(int i = 0; i < 3; i++) {
			east_pan.add(label[i]);
			east_pan.add(scroll[i]);
		}
		// east_pan
		
		for(int i = 0; i < btn.length; i++) {
			btn_pan.add(btn[i]);
		}
		// btn_pan
		
		center_pan.add(card_pan);
		center_pan.add(east_pan);
		//center_pan
		
	}
	void setFrame() {
		this.setSize(800,500);
		this.setLocation(500, 250);
		this.setVisible(true);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
	
	
	Cus_Roomview(int i){
		setting();
		if(i == 0) {
			standard();
		}else if(i == 1) {
			superrior();
		}else if(i == 2) {
			deluxe();
		}else if(i == 3) {
			excutive();
		}
		insert();
		this.setLayout(new BorderLayout());
		this.add("Center",center_pan);
		this.add("South", btn_pan);
		setFrame();
		}
	
	public static void main(String[] args) {
		//new Cus_Roomview(0);

	}

}
