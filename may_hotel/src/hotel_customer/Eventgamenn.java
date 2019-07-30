package hotel_customer;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;


class gaImage1 extends JPanel{
	
	Image ima;
	gaImage1(String PATH){
		ima = Toolkit.getDefaultToolkit().getImage(PATH);
	}
	@Override
	public void paint(Graphics arg0) {
		arg0.drawImage(this.ima, 0, 0, this.getWidth(), this.getHeight(), this);

	}
}
public class Eventgamenn extends JPanel implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == bts) {
			//this.card.show(mainpan, "eve1");
			card.first(mainpan);
		}else if (obj == bts2) {
			//this.card.show(mainpan, "eve2");
			card.previous(mainpan);
		}else if (obj == bts3) {
			//this.card.show(mainpan, "eve3");
			card.next(mainpan);
		}else if (obj == bts4) {
			//this.card.show(mainpan, "eve4");
			card.last(mainpan);	
		}
	}
	CRUDprocess crud;
	JMenuBar bar;
	JMenuItem bar_yo, bar_yo2, bar_yo3, bar_yo4, bar_gae, bar_gae2, bar_gae3, bar_gae4, bar_ev, bar_exit;
	JMenu menu, menu2, menu3, menu4;
	JPanel[] pan;
	JPanel rightpan,leftpan;
	CardLayout card;
	JPanel mainpan,firstpan;
	JLabel lab1,lab2,lab3,lab4;
	JButton bts,bts2,bts3,bts4;
	ImageIcon road_icon, road_press, road_rollover,
				road_icon2, road_press2, road_rollover2,
				road_icon3, road_press3, road_rollover3,
				road_icon4, road_press4, road_rollover4;
	/////////////////////////////////////////////////////
	JPanel eve2, eve3, eve4;
	List<event_info> list;
	JPanel btn_pan;
	JPanel[] west_pan;
	JPanel[] east_pan;
	JLabel[] title;
	JPanel[] title_pan;
	JTextArea[] text;
	JLabel[] beginEnd;
	
	void setEvent() {
		crud = new CRUDprocess();	
	}
	void makeImageIcon() {
		road_icon = new ImageIcon("image\\처음1.png");
		road_press = new ImageIcon("image\\처음2.png");
		road_rollover = new ImageIcon("image\\처음3.png");
		road_icon2 = new ImageIcon("image\\이전1.png");
		road_press2 = new ImageIcon("image\\이전2.png");
		road_rollover2 = new ImageIcon("image\\이전3.png");
		road_icon3 = new ImageIcon("image\\다음1.png");
		road_press3 = new ImageIcon("image\\다음2.png");
		road_rollover3 = new ImageIcon("image\\다음3.png");
		road_icon4 = new ImageIcon("image\\마지막1.png");
		road_press4 = new ImageIcon("image\\마지막2.png");
		road_rollover4 = new ImageIcon("image\\마지막3.png");
	}
	
	void makeJPanel() {
		pan = new JPanel[list.size()];
		west_pan = new JPanel[list.size()];
		east_pan = new JPanel[list.size()];
		title = new JLabel[list.size()];
		title_pan = new JPanel[list.size()];
		text = new JTextArea[list.size()];
		beginEnd = new JLabel[list.size()];
		for(int i = 0; i < list.size(); i++) {
			pan[i] = new JPanel();
			west_pan[i] = new gaImage1(list.get(i).event_image);
			east_pan[i] = new JPanel();
			title[i] = new JLabel();
			title_pan[i] = new JPanel();
			text[i] = new JTextArea();
			beginEnd[i] = new JLabel();
			
			pan[i].setLayout(new GridLayout(1,2));
			east_pan[i].setLayout(new BorderLayout());
			
			title[i].setText(list.get(i).event_name);
			title_pan[i].add(title[i]);
			beginEnd[i].setText(list.get(i).event_begin + "~" + list.get(i).event_close);
			
			east_pan[i].add("North",title_pan[i]);
			east_pan[i].add("Center",text[i]);
			east_pan[i].add("South",beginEnd[i]);
			
			pan[i].add(west_pan[i]);
			pan[i].add(east_pan[i]);
			
			mainpan.add(pan[i], i+"");
		}
		
	}
	void makebutton() {
			
			bts = new JButton(road_icon);
			bts2 = new JButton(road_icon2);
			bts3 = new JButton(road_icon3);
			bts4 = new JButton(road_icon4);
			
			bts.setPressedIcon(road_press);//��ư��������
			bts.setRolloverIcon(road_rollover);//��ư���콺�ø���
			bts.setContentAreaFilled(false);//ä������
			bts.setBorderPainted(false);//�ܰ��� ���ش�
			bts.setFocusPainted(false);
			
			bts2.setPressedIcon(road_press2);//��ư��������
			bts2.setRolloverIcon(road_rollover2);//��ư���콺�ø���
			bts2.setContentAreaFilled(false);//ä������
			bts2.setBorderPainted(false);//�ܰ��� ���ش�
			bts2.setFocusPainted(false);
		
			bts3.setPressedIcon(road_press3);
			bts3.setRolloverIcon(road_rollover3);
			bts3.setContentAreaFilled(false);
			bts3.setBorderPainted(false);
			bts3.setFocusPainted(false);
			
			bts4.setPressedIcon(road_press4);
			bts4.setRolloverIcon(road_rollover4);
			bts4.setContentAreaFilled(false);
			bts4.setBorderPainted(false);
			bts4.setFocusPainted(false);
			
			btn_pan = new JPanel(new GridLayout(1,4));
			btn_pan.add(bts);
			btn_pan.add(bts2);
			btn_pan.add(bts3); 
			btn_pan.add(bts4);
	}
	
	public Eventgamenn() {
	//super(str);
		//this.setLayout(new GridLayout(1,2));
		card = new CardLayout();
		mainpan = new JPanel();
		mainpan.setLayout(card);
//		mainpan.add(firstpan,"eve1");
//		mainpan.add(eve2,"eve2");
//		mainpan.add(eve3,"eve3");
//		mainpan.add(eve4,"eve4");
//		this.add("Center",mainpan);
		CRUDprocess crud = new CRUDprocess();
		list = crud.selectEvent();
		makeImageIcon();
		makeJPanel();
		makebutton();
		bts.addActionListener(this);
		bts2.addActionListener(this);
		bts3.addActionListener(this);
		bts4.addActionListener(this);
		this.setLayout(new BorderLayout());	
		this.add("Center",mainpan);
		this.add("South",btn_pan);
		
		this.setSize(1000, 500);
		this.setVisible(true);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		//new Eventgamenn();
	}

}
