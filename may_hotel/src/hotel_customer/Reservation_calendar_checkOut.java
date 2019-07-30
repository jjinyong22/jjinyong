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


public class Reservation_calendar_checkOut extends JFrame implements WindowListener, ActionListener, ItemListener{

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
   String[] checkIn;
   String[] checkOut;
   Yoyaku yo;
   int endDay;
   List list;
   public Reservation_calendar_checkOut(Yoyaku yo){
      this.yo = yo; 
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
      int i_day = Integer.parseInt(day);
      
      int startDay = Reservation_calendar_checkIn.btn_day;
      
      
      if(startDay >= i_day) {
    	  JOptionPane.showMessageDialog(this, "예약할수없습니다.");
      }
      
     int check_start = endDay+startDay;
     int check_end = endDay+i_day-1;
     System.out.println(check_start + " " + check_end);
      for(int i = check_start; i <= check_end ; i++) {
    	  System.out.println(days.length+"  ,  "+i);
    	  if(!days[i-1].isEnabled()) {
    		  JOptionPane.showMessageDialog(this, "예약할수 없습니다2.");
    		  yo.flag = false;
    		  return;
    	  }else {
    		  yo.flag = true;
    	  }
    	  
//    	  Reservation_calendar_checkIn.btn_day = 0;
//    	  endDay = 0;
      }
      
      res_date = year+"-"+month+"-"+day;
      yo.txt[2].setText(res_date);
      dispose();
   }
   public void init(){
      select();
      calendar();
   }
   
   public void select(){
      JPanel panel = new JPanel(grid);
      for(int i=2020; i>=2000;i--){
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
    
      while(it.hasNext()) {
    	Yoya_batis info = (Yoya_batis)it.next();
    	checkIn = info.getRes_checkin().split("-");
    	checkOut = info.getRes_checkout().split("-");
      if(info.getRes_state().equals("예약완료")){
    	if(checkIn[0].equals(chyear.getSelectedItem())&&checkIn[1].equals(chmonth.getSelectedItem())&&
				yo.box2.getSelectedItem().equals(String.valueOf(info.getRes_room()))) {
			int y  = week + Integer.parseInt(checkOut[2]);
			for(int i = week + Integer.parseInt(checkIn[2]); i <= y-1; i++) {
				days[i-1].setEnabled(false);
    			}
    		}
    	}
      endDay = week;
      }
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