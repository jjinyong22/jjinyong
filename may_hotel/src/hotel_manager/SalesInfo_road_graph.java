package hotel_manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Paint_graph extends JPanel {
	Image i;
	List<ReservationInfo> list;
	SalesInfo_road_graph graph;
	//int jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec;
	int[] month;
	Paint_graph(SalesInfo_road_graph graph){
		this.graph = graph;
	}
	@Override
	public void paint(Graphics g) {
	//	setMonth();
		g.setColor(Color.BLACK);//
		g.drawLine(100, 250, 650, 250);//
		g.drawLine(100, 250, 100, 10);
		CRUDprocess_emp crud = new CRUDprocess_emp();
		for (int cnt = 1; cnt <= 12; cnt++) {
			if (cnt % 2 == 0) {
				g.drawString("￦" + String.format("%,d",cnt * 400000) + "", 25, 255 - 23 * cnt);
			}
			g.drawString(cnt + "월", 65 + 45 * cnt, 265);
		}
		
		list = crud.selectReservationInfo();
		Iterator it = list.iterator();
		while(it.hasNext()) {
			ReservationInfo info = (ReservationInfo)it.next();
			String[] date = info.getRes_checkout().split("-");
			month = new int[12];
			if(info.getRes_state().equals("예약완료")) {
				if(date[0].equals(graph.year.getSelectedItem())) {
					switch(Integer.parseInt(date[1])) {
					case 1 :
						//jan += info.getRes_payment();
						month[0] += Integer.parseInt(info.getRes_payment());
						break;
					case 2 :
//						feb += info.getRes_payment();
						month[1] += Integer.parseInt(info.getRes_payment());
						break;
					case 3 :
//						mar += info.getRes_payment();
						month[2] +=Integer.parseInt(info.getRes_payment());
						break;
					case 4 :
//						apr += info.getRes_payment();
						month[3] += Integer.parseInt(info.getRes_payment());
						break;
					case 5 :
//						may += info.getRes_payment();
						month[4] += Integer.parseInt(info.getRes_payment());
						break;
					case 6 : 
//						jun += info.getRes_payment();
						month[5] += Integer.parseInt(info.getRes_payment());
						break;
					case 7 :
//						jul += info.getRes_payment();
						month[6] += Integer.parseInt(info.getRes_payment());
						break;
					case 8 :
//						aug += info.getRes_payment();
						month[7] += Integer.parseInt(info.getRes_payment());
						break;
					case 9 :
//						sep += info.getRes_payment();
						month[8] += Integer.parseInt(info.getRes_payment());
						break;
					case 10 :
//						oct += info.getRes_payment();
						month[9] += Integer.parseInt(info.getRes_payment());
						break;
					case 11 :
//						nov += info.getRes_payment();
						month[10] +=Integer.parseInt(info.getRes_payment());
						break;
					case 12 :
//						dec += info.getRes_payment();
						month[11] += Integer.parseInt(info.getRes_payment());
					}
				}
			}
		}
		
		for (int cnt = 1; cnt <= 12; cnt++) {
			
			g.setColor(new Color(67,116,217));
			if(month[cnt-1]>0) {
			g.fillRect(65 + 45 * cnt, 250 - (40 + (month[cnt-1]/20000) * 2),
					25, 40 + ((month[cnt-1]/20000) * 2));
			}
		}
	}
	/*void setMonth(){
		jan = 0; feb = 0; mar = 0; apr = 0; may = 0; jun = 0; jul = 0;
		aug = 0; sep = 0; oct = 0; nov = 0; dec = 0;
	}*/
}

public class SalesInfo_road_graph extends JPanel implements ItemListener{


	Paint_graph pg;

	JLabel label_title, label_year;

	JComboBox year;

	JPanel info;

	JPanel westPanel, eastPanel, southPanel;

	Font mainFont, font;

	ImageIcon choice, choice_press, choice_rollover;

	

	void setting() {
		pg = new Paint_graph(this);
		label_title = new JLabel("매출 정보 조회");
		label_year = new JLabel("년도");
		year = new JComboBox();

		choice = new ImageIcon("image\\choice.png");
		choice_press = new ImageIcon("image\\choice_press.png");
		choice_rollover = new ImageIcon("image\\choice_rollover.png");

		for (int i = Calendar.getInstance().get(Calendar.YEAR); i >= 2000; i--) {
			year.addItem(i + "");
			info = new JPanel(new GridLayout(3, 1));

			mainFont = new Font("Sandoll 고딕 01 Light", Font.BOLD, 30);
			Color mainColor = new Color(116, 116, 116);

			label_title.setFont(mainFont);
			label_title.setForeground(mainColor);

			font = new Font("Sandoll 고딕 01 Light", Font.PLAIN, 15);
			Color fontColor = new Color(76, 76, 76);

			label_year.setFont(font);
			label_year.setForeground(fontColor);

			JPanel title_pen = new JPanel();
			title_pen.add(label_title);
			title_pen.setBackground(Color.WHITE);
			JPanel combo_pen = new JPanel();
			combo_pen.add(label_year);
			combo_pen.add(year);
			combo_pen.setBackground(Color.WHITE);

			info.add(new JLabel(""));
			info.add(title_pen);
			info.add(combo_pen);
			info.setBackground(Color.WHITE);
		}
	}

	public SalesInfo_road_graph() {
//		super(str);
		setting();
		this.setLayout(new BorderLayout());
		year.addItemListener(this);
		westPanel = new JPanel();
		westPanel.add(new JLabel("                                            "));
		westPanel.setBackground(Color.WHITE);

		eastPanel = new JPanel();
		eastPanel.add(new JLabel("                                            "));
		eastPanel.setBackground(Color.WHITE);

		southPanel = new JPanel(new GridLayout(4, 1));
		southPanel.add(new JLabel("                    "));
		southPanel.add(new JLabel("                    "));
		southPanel.add(new JLabel("                    "));
		southPanel.add(new JLabel("                    "));
		southPanel.setBackground(Color.WHITE);

		pg.setBackground(Color.WHITE);

		this.add("West", westPanel);
		this.add("East", eastPanel);
		this.add("North", info);
		this.add("Center", pg);
		this.add("South", southPanel);
		this.setSize(1000, 600);
		this.setVisible(true);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		pg.repaint();
		
	}

//	public static void main(String[] args) {
//		new SalesInfo_road_graph("���� ���� ��ȸ �ý���");
//
//	}

}
