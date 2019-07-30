package hotel_manager;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class Sales_Calendar_road extends JPanel implements WindowListener, ActionListener, ItemListener{
	private Choice chyear, chmonth;
	private JLabel yLabel, mLabel;
	GregorianCalendar gc;
	private int year, month;
	private JLabel[] dayLabel = new JLabel[7];
	private String[] day={"일","월","화","수","목","금","토"};
	private JButton[] days = new JButton[42];//7���� 6���̹Ƿ� 42���� ��ư�ʿ�
	private JPanel selectPanel = new JPanel();
	private GridLayout grid = new GridLayout(7,7,5,5);//��,��,����,������
	private Calendar ca = Calendar.getInstance();
	private Dimension dimen, dimen1;
	private int xpos, ypos;
	private Font font;
	SalesInfo_road_calendar sales_calendar;
	List<ReservationInfo> month_list;
	List<ReservationInfo> day_list;
	
	public Sales_Calendar_road(SalesInfo_road_calendar sales_calendar){
		//setTitle("�޷� - ����:"+ca.get(Calendar.YEAR)+"/"+(ca.get(Calendar.MONTH)+1)+"/"+ca.get(Calendar.DATE));
		setSize(550,500);
		this.sales_calendar = sales_calendar;
		this.setLayout(new BorderLayout());
		dimen = Toolkit.getDefaultToolkit().getScreenSize();
		dimen1 = this.getSize();
		xpos = (int)(dimen.getWidth()/2 - dimen1.getWidth()/2);
		ypos = (int)(dimen.getHeight()/2 - dimen1.getHeight()/2);
		setLocation(xpos, ypos);//ȭ���� ����� ���
		//setResizable(false);
		setVisible(true);
		chyear = new Choice(); chmonth = new Choice();
		yLabel = new JLabel("년"); mLabel = new JLabel("월");
		//addWindowListener(this);
		init();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String year = chyear.getSelectedItem();
		String month = chmonth.getSelectedItem();
		JButton btn = (JButton)arg0.getSource();
		String day = btn.getText();
		//String date = year+"-"+month+"-"+day;
		
		CRUDprocess_emp crud = new CRUDprocess_emp();
		day_list = crud.selectReservationInfo();
		Iterator it = day_list.iterator();
		int dayPay = 0;
		while(it.hasNext()) {
			ReservationInfo info = (ReservationInfo)it.next();
			String[] date = info.getRes_checkout().split("-");
			if(info.getRes_state().equals("예약완료")) {
				if(date[2].equals(day)) {
					dayPay += Integer.parseInt(info.getRes_payment());
				}
			}
		}
		sales_calendar.texts[0].setText(dayPay+"");
		
		
	}
	public void init(){
		select();
		calendar();
	}
	
	public void select(){
		JPanel panel = new JPanel(grid);//7�� 7���� �׸��巹�̾ƿ�
		for(int i=2020; i>=2000;i--){
			chyear.add(String.valueOf(i));
		}
		for(int i=1; i <=12; i++){
			chmonth.add(String.valueOf(i));
		}
		for(int i = 0; i < day.length;i++){//���� �̸��� ���̺� ���
			dayLabel[i] = new JLabel(day[i],JLabel.CENTER);
			panel.add(dayLabel[i]);
			dayLabel[i].setBackground(Color.GRAY);//��ǻ� �ǹ̰� ����. �ٲ��� ����.
			
		}
		dayLabel[6].setForeground(Color.BLUE);//����� ����
		dayLabel[0].setForeground(Color.RED);//�Ͽ��� ����
		for(int i = 0; i < 42;i++){//��� 42���� ��ư�� ����
			days[i] = new JButton("");//������ ���� ��ư ����
			if(i % 7 == 0)
				days[i].setForeground(Color.RED);//�Ͽ��� ��ư�� ��
			else if(i % 7 == 6)
				days[i].setForeground(Color.BLUE);//����� ��ư�� ��
			else
				days[i].setForeground(Color.BLACK);
			days[i].addActionListener(this);
			panel.add(days[i]);
		}
		selectPanel.add(chyear);
		selectPanel.add(yLabel);
		selectPanel.add(chmonth);
		selectPanel.add(mLabel);
		
		this.add("North",selectPanel);//������ ���� ������ �� �ִ� ȭ���� ��ܿ� ���
		this.add("Center", panel);
		selectPanel.setBackground(Color.WHITE);
		panel.setBackground(Color.WHITE);
		
		String m = (ca.get(Calendar.MONTH)+1)+"";
		String y = ca.get(Calendar.YEAR)+"";
		chyear.select(y);
		chmonth.select(m);
		chyear.addItemListener(this);
		chmonth.addItemListener(this);
	}
	public void calendar(){
		
		font = new Font("Sandoll 고딕 01 Light",Font.BOLD, 20);
		Font dayFont = new Font("Sandoll 고딕 01 Light",Font.PLAIN, 20);
		Color fontColor = new Color(116,116,116);
		
		for(int i = 0; i < dayLabel.length; i++) {
			dayLabel[i].setFont(font);
			dayLabel[i].setForeground(fontColor);
		}
		
		for(int i = 0; i < days.length; i++) {
			days[i].setFont(dayFont);
		}
		
		year = Integer.parseInt(chyear.getSelectedItem());
		month=Integer.parseInt(chmonth.getSelectedItem());
		gc = new GregorianCalendar(year, month-1, 1);
		int max = gc.getActualMaximum(gc.DAY_OF_MONTH);//�ش� ���� �ִ� �� �� ȹ��
		int week = gc.get(gc.DAY_OF_WEEK);//�ش� ���� ���� ����
//		System.out.println("DAY_OF_WEEK:"+week);
		String today = Integer.toString(ca.get(Calendar.DATE));//���� ��¥ ȹ��
		String today_month = Integer.toString(ca.get(Calendar.MONTH)+1);//������ �� ȹ��
//		System.out.println("today:"+today);
		for(int i = 0; i < days.length; i++){
			days[i].setEnabled(true);
		}
		for(int i = 0; i < week-1; i++){//������ ������ ��ư�� ��Ȱ��ȭ
			days[i].setEnabled(false);
		}
		for(int i = week; i< max+week; i++){
			days[i-1].setText((String.valueOf(i-week+1)));
			days[i-1].setBackground(Color.WHITE);
			if(today_month.equals(String.valueOf(month))){//������ ���� �ް� ���� ���� ���
				if(today.equals(days[i-1].getText())){//��ư�� ��¥�� ���ó�¥�� ��ġ�ϴ� ���
					days[i-1].setBackground(Color.CYAN);//��ư�� ���� ����
				}
			}
		}
		for(int i = (max+week-1); i < days.length; i++){//��¥�� ���� ��ư�� ��Ȱ��ȭ
			days[i].setEnabled(false);
		}
//		System.out.println("max+week:"+(max+week)+",week:"+week);
		CRUDprocess_emp crud = new CRUDprocess_emp();
		month_list = crud.selectReservationInfo();
		Iterator it = month_list.iterator();
		int monthPay = 0;
		while(it.hasNext()) {
			ReservationInfo info = (ReservationInfo)it.next();
			String[] date = info.getRes_checkout().split("-");
			if(info.getRes_state().equals("예약완료")) {
				if(date[0].equals(chyear.getSelectedItem()) && date[1].equals(chmonth.getSelectedItem())) {
					monthPay += Integer.parseInt(info.getRes_payment());
				}
			}
		}
		sales_calendar.texts[1].setText(monthPay+"");
	}
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		arg0.getWindow().dispose();

	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		Color color = this.getBackground();
		if(arg0.getStateChange()==ItemEvent.SELECTED){
			for(int i = 0; i < 42; i++){//���̳� ���� ���õǸ� ������ �޷��� ����� ���� �׸���.
				if( !days[i].getText().equals("")){
					days[i].setText("");//������ ��¥�� ����
					days[i].setBackground(color);//�޷��� ������ ������ ������ ��ư�� ������ ������.
				}
			}
			calendar();
		}

	}
}

public class SalesInfo_road_calendar extends JPanel implements ActionListener{
	
	JLabel[] labels;
	String[] label_title = {"매출 정보 조회","오늘의 매출","이달의 매출"};
	
	JTextField[] texts;
	
	JComboBox year, month;
	
	ImageIcon road_icon;
	
	JPanel[] panels;
	
	JPanel[] result;
	
	JPanel northPanel, westPanel, eastPanel;
	
	Font mainFont, font;
	
	Sales_Calendar_road calendar;
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

	}
	
	void makeJLabel() {
		labels = new JLabel[3];
		for(int i = 0; i < labels.length; i++) {
			labels[i] = new JLabel(label_title[i]);
		}
	}
	
	void makeJTextField() {
		texts = new JTextField[2];
		for(int i = 0; i < texts.length; i++) {
			texts[i] = new JTextField(20);
		}
	}
	
	void makeComboBox() {
		year = new JComboBox();
		for(int i = 2019; i >= 1990; i--) {
			year.addItem(i + "");
			year.setPreferredSize(new Dimension(80,25));
		}
		month = new JComboBox();
		for(int i = 1; i <= 12; i++) {
			month.addItem(i + "");
			month.setPreferredSize(new Dimension(80,25));
		}
	}
	
//	void makeJButton() {
//		road_icon = new ImageIcon("image\\btn02.png");
//		ImageIcon road_press = new ImageIcon("image\\btn02_press.png");
//		ImageIcon road_rollover = new ImageIcon("image\\btn02_rollover.png");
//		road = new JButton(road_icon);
//		road.setBorderPainted(false);
//		road.setContentAreaFilled(false);
//		road.setFocusPainted(false);
//		road.setPressedIcon(road_press);
//		road.setRolloverIcon(road_rollover);
//		road.addActionListener(this);
//	}
	
	void makeJPanel() {
		panels = new JPanel[3];
		for(int i = 0; i < panels.length; i++) {
			panels[i] = new JPanel();
			panels[i].setBackground(Color.WHITE);
		}
		result = new JPanel[4];
		for(int i = 0; i < result.length; i++) {
			result[i] = new JPanel(new FlowLayout());
			result[i].setBackground(Color.WHITE);
		}
		
		northPanel = new JPanel(new GridLayout(3,1));
		westPanel = new JPanel();
		eastPanel = new JPanel();
	}
	
	SalesInfo_road_calendar(){
//		super(str);
		this.setLayout(new BorderLayout());
		
		makeJLabel();
		makeComboBox();
//		makeJButton();
		makeJPanel();
		makeJTextField();
		
		mainFont = new Font("Sandoll 고딕 01 Light",Font.BOLD, 30);
		Color mainColor = new Color(116,116,116);
		labels[0].setFont(mainFont);
		labels[0].setForeground(mainColor);
		
		font = new Font("Sandoll 고딕 01 Light",Font.PLAIN, 15);
		Color fontColor = new Color(76,76,76);
		labels[1].setFont(font); labels[1].setForeground(fontColor);
		labels[2].setFont(font); labels[2].setForeground(fontColor);
		
		panels[0].add(labels[0]);
		
		panels[1].setLayout(new GridLayout(4,1));
	
		result[0].add(labels[1]); result[0].add(texts[0]);
		result[1].add(labels[2]); result[1].add(texts[1]);
		
		panels[1].add(new JLabel(""));
		panels[1].add(result[0]);
		panels[1].add(result[1]);
		panels[1].add(new JLabel(""));
		
		this.setBackground(Color.WHITE);
		
		calendar = new Sales_Calendar_road(this);
		
		northPanel.add(new JLabel(""));
		northPanel.add(panels[0]);
		northPanel.add(new JLabel(""));
		northPanel.setBackground(Color.WHITE);
		
		westPanel.add(new JLabel("                         "));
		westPanel.setBackground(Color.WHITE);
		eastPanel.add(new JLabel("                         "));
		eastPanel.setBackground(Color.WHITE);
		
		this.add("North",northPanel);
		this.add("Center",calendar);
		this.add("South",panels[1]);
		this.add("West",westPanel);
		this.add("East", eastPanel);
		
		this.setBackground(Color.WHITE);
		
		this.setSize(1000, 600);
		this.setVisible(true);
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
//	public static void main(String[] args) {
//		new SalesInfo_road_calendar("�������� ��ȸ ȭ��");
//	}

}
