package hotel_customer;


import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Reservation_calendar_checkIn extends JFrame implements WindowListener, ActionListener, ItemListener{

   private Choice chyear, chmonth;
   private JLabel yLabel, mLabel;
   GregorianCalendar gc;
   private int year, month;
   private JLabel[] dayLabel = new JLabel[7];
   private String[] day={"일","월","화","수","목","금","토"};
   private JButton[] days = new JButton[42];
   private JPanel selectPanel = new JPanel();
   private GridLayout grid = new GridLayout(7,7,5,5);
   private Calendar ca = Calendar.getInstance();
   private Dimension dimen, dimen1;
   private int xpos, ypos;
   String res_date;
   String[] checkInt;
   String[] checkOut;
   static int btn_day;
   Yoyaku yo;
   List<Yoya_batis> list;
   public Reservation_calendar_checkIn(Yoyaku res_insert){
      this.yo = res_insert; 
      setTitle("달력 - 오늘:"+ca.get(Calendar.YEAR)+"/"+(ca.get(Calendar.MONTH)+1)+"/"+ca.get(Calendar.DATE));
      setSize(450,300);
      dimen = Toolkit.getDefaultToolkit().getScreenSize();
      dimen1 = this.getSize();
      xpos = (int)(dimen.getWidth()/2 - dimen1.getWidth()/2);
      ypos = (int)(dimen.getHeight()/2 - dimen1.getHeight()/2);
      setLocation(xpos, ypos);
      setResizable(false);
      setVisible(true);
      chyear = new Choice(); chmonth = new Choice();
      yLabel = new JLabel("년"); mLabel = new JLabel("월");
      addWindowListener(this);
      init();
   }
   
   @Override
   public void actionPerformed(ActionEvent arg0) {
      String year = chyear.getSelectedItem();
      String month = chmonth.getSelectedItem();
      JButton btn = (JButton)arg0.getSource();
      String day = btn.getText();
      btn_day = Integer.parseInt(day);
      Calendar cal = Calendar.getInstance();
      int cal_mon = cal.get(Calendar.MONTH)+1;
      int cal_day = cal.get((cal.DATE));
      
      if(cal_mon >= Integer.parseInt(month) && cal_day >= Integer.parseInt(day)) {
    	  JOptionPane.showMessageDialog(null, "예약할수없습니다");
    	  return;
      }
      
      btn_day = Integer.parseInt(btn.getText());
      res_date = year+"-"+month+"-"+day;
      yo.txt[1].setText(res_date);
      dispose();//而댄룷�꼳�듃�쓽 �씠踰ㅽ듃瑜� �넻�빐 �쐢�룄�슦 醫낅즺(硫붾え由ъ뿉�꽌 �궘�젣�맖)
//      this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); --> �긽�닔濡� 援ы쁽.(�쐢�룄�슦�쓽 x踰꾪듉�쓣 �넻�빐�꽌 醫낅즺 �씠踰ㅽ듃瑜� 泥섎━�븷 �븣 �궗�슜.(湲곕뒫�� �삊媛숈쓬)
   }
   
  
   public void init(){
      select();
      calendar();
   }
   
   public void select(){
      JPanel panel = new JPanel(grid);
      for(int i=2021; i>=2015;i--){
         chyear.add(String.valueOf(i));
      }
      for(int i=1; i <=12; i++){
         chmonth.add(String.valueOf(i));
      }
      for(int i = 0; i < day.length;i++){
         dayLabel[i] = new JLabel(day[i],JLabel.CENTER);
         panel.add(dayLabel[i]);
         dayLabel[i].setBackground(Color.GRAY);
      }
      dayLabel[6].setForeground(Color.BLUE);
      dayLabel[0].setForeground(Color.RED);
      for(int i = 0; i < 42;i++){
         days[i] = new JButton("");
         if(i % 7 == 0)
            days[i].setForeground(Color.RED);
         else if(i % 7 == 6)
            days[i].setForeground(Color.BLUE);
         else
            days[i].setForeground(Color.BLACK);
         days[i].addActionListener(this);
         panel.add(days[i]);
      }
      selectPanel.add(chyear);
      selectPanel.add(yLabel);
      selectPanel.add(chmonth);
      selectPanel.add(mLabel);
      
      this.add(selectPanel,"North");
      this.add(panel, "Center");
      
      String m = (ca.get(Calendar.MONTH)+1)+"";
      String y = ca.get(Calendar.YEAR)+"";
      chyear.select(y);
      chmonth.select(m);
      chyear.addItemListener(this);
      chmonth.addItemListener(this);
   }
   public void calendar(){
      year = Integer.parseInt(chyear.getSelectedItem());
      month=Integer.parseInt(chmonth.getSelectedItem());
      gc = new GregorianCalendar(year, month-1, 1);
      int max = gc.getActualMaximum(gc.DAY_OF_MONTH);
      int week = gc.get(gc.DAY_OF_WEEK);
//      System.out.println("DAY_OF_WEEK:"+week);
      String today = Integer.toString(ca.get(Calendar.DATE));
      String today_month = Integer.toString(ca.get(Calendar.MONTH)+1);
//      System.out.println("today:"+today);
      for(int i = 0; i < days.length; i++){
         days[i].setEnabled(true);
      }
      for(int i = 0; i < week-1; i++){
         days[i].setEnabled(false);
      }
      for(int i = week; i< max+week; i++){
         days[i-1].setText((String.valueOf(i-week+1)));
         days[i-1].setBackground(Color.WHITE);
         if(today_month.equals(String.valueOf(month))){
            if(today.equals(days[i-1].getText())){
               days[i-1].setBackground(Color.CYAN);
            }
         }
      }
      for(int i = (max+week-1); i < days.length; i++){
         days[i].setEnabled(false);
      }
      
      CRUDprocess crud = new CRUDprocess();
      list = crud.selectyoya();
      Iterator it = list.iterator();
      String[] checkIn = null;
      String[] checkOut = null;
      
      while(it.hasNext()) {
    	Yoya_batis info = (Yoya_batis)it.next();
    	checkIn = info.getRes_checkin().split("-");
    	checkOut = info.getRes_checkout().split("-");
    	if(info.getRes_state().equals("예약완료")) {
    		if(checkIn[0].equals(chyear.getSelectedItem())&&checkIn[1].equals(chmonth.getSelectedItem())&&
    				yo.box2.getSelectedItem().equals(String.valueOf(info.getRes_room()))) {
    			int y  = week + Integer.parseInt(checkOut[2]);
    			for(int i = (week) + Integer.parseInt(checkIn[2])-1; i <= y-2; i++) {
    				days[i-1].setEnabled(false);
    			}
    		}
    	}
      }
      btn_day = week;
//      System.out.println("max+week:"+(max+week)+",week:"+week);
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
         for(int i = 0; i < 42; i++){
            if( !days[i].getText().equals("")){
               days[i].setText("");
               days[i].setBackground(color);
            }
         }
         calendar();
      }

   }

   public static void main(String[] args) {
//      new CalendarBySwing();

   }
}

