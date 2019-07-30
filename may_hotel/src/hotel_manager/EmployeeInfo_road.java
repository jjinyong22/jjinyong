package hotel_manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

class Manager_Model extends AbstractTableModel{
	   
	   private Object[][] tableData;
	   private int rows,cols;
	   private String[] columnName = {"직원 아이디","직원 비밀번호","직원 이름","직원 입사일",
	         "직원 핸드폰 번호","직원 소속"};
	   private List<EmployeeInfo> list;
	   EmployeeInfo_road road;
	   Manager_Model(EmployeeInfo_road road){
	      this.road = road;
	      //Employee_search search = new Employee_search();
	      //road = new EmployeeInfo_road();
	      //search.setMan_id(road.texts[0].getText());
	      //search.setMan_name(road.texts[1].getText());
	      HashMap<String,Object> copy = new HashMap<String,Object>();
	   
	      if(! road.texts[0].getText().equals("")) {
	         copy.put("man_id", road.texts[0].getText());
	      }else {
	         copy.put("man_id", null);
	      }
	      if(! road.texts[1].getText().equals("")) {
	         copy.put("man_name",road.texts[1].getText());
	      }else {
	         copy.put("man_name",null);
	      }
	      if(! road.dept_combo.getSelectedItem().equals("부서")) {
	         copy.put("man_dept",(String)road.dept_combo.getSelectedItem());
	      }else {
	         copy.put("man_dept",null);
	      }
	      CRUDprocess_emp crud = new CRUDprocess_emp();
	      list = crud.selectmanager_con(copy);
	      setData();
	   }
	   private void setData() {
	      Iterator it = list.iterator();
	      rows = list.size();
	      cols = columnName.length;
	      tableData = new Object[rows][cols];
	      int r= 0;
	      while(it.hasNext()) {
	         
	    	  EmployeeInfo em = (EmployeeInfo)it.next();
	         String man_id = em.getMan_id();
	         String man_name = em.getMan_name();
	         String man_dept = em.getMan_dept();
//	         String txt_id = road.texts[0].getText();
//	         String txt_name = road.texts[1].getText();
//	         String txt_dept = (String)road.dept_combo.getSelectedItem();
	         //if(man_id.equals(txt_id)){
	         tableData[r][0] = em.getMan_id();
	         tableData[r][1] = em.getMan_pw();
	         tableData[r][2] = em.getMan_name();
	         tableData[r][3] = em.getMan_hiredate();
	         tableData[r][4] = em.getMan_phone();
	         tableData[r][5] = em.getMan_dept();
	         r++;
	         //}
	         //if(man_name.equals(txt_name)) {
//	            tableData[r][0] = em.getMan_id();
//	            tableData[r][1] = em.getMan_pw();
//	            tableData[r][2] = em.getMan_name();
//	            tableData[r][3] = em.getMan_hiredate();
//	            tableData[r][4] = em.getMan_phone();
//	            tableData[r][5] = em.getMan_dept();
//	            r++;
//	         //}
//	         //if(man_dept.equals(txt_dept)) {
//	            tableData[r][0] = em.getMan_id();
//	            tableData[r][1] = em.getMan_pw();
//	            tableData[r][2] = em.getMan_name();
//	            tableData[r][3] = em.getMan_hiredate();
//	            tableData[r][4] = em.getMan_phone();
//	            tableData[r][5] = em.getMan_dept();
//	            r++;
	         }
	      }
	   //}
	   
	   @Override
	   public String getColumnName(int arg0) {//제목 바꾸는거
	      // TODO Auto-generated method stub
	      return columnName[arg0];
	   }
	   @Override
	   public int getRowCount() {
	      // TODO Auto-generated method stub
	      return rows;
	   }

	   @Override
	   public int getColumnCount() {
	      // TODO Auto-generated method stub
	      return cols;
	   }

	   @Override
	   public Object getValueAt(int rowIndex, int columnIndex) {
	      return tableData[rowIndex][columnIndex];
	   }
	   
	}

	public class EmployeeInfo_road extends JPanel implements ActionListener {
	   
	   @Override
	   public void actionPerformed(ActionEvent e) {
	      //if(texts[0].getText().equals("") && texts[1].getText().equals("") ) {
	      //   JOptionPane.showMessageDialog(this, "아이디를 입력해 주세요");
	      //}else {
	         table.setModel(new Manager_Model(this));
	      //}
	   }
	   JLabel[] labels;
	   String[] label_texts = {"직원 정보 조회","직원 아이디","직원 이름","소속부서"};
	   
	   JTextField[] texts;
	   
	   JComboBox dept_combo;
	   String[] dept_texts = {"부서","홍보부","고객 관리부","객실 관리부","영업부","경영부"}; 
	   
	   JButton btn;
	   
	   ImageIcon emp_road, emp_road_press, emp_road_rollover;
	   
	   JPanel firstPanel, secondPanel;
	   
	   JPanel[] panels;
	   
	   JTable table;
	   
	   JScrollPane scroll;
	   
	   void makeJLabel() {
	      labels = new JLabel[4];
	      for(int i = 0; i < labels.length; i++) {
	         labels[i] = new JLabel(label_texts[i]);
	      }
	   }
	   
	   void makeJTextField() {
	      texts = new JTextField[2];
	      for(int i = 0; i < texts.length; i++) {
	         texts[i] = new JTextField(20);
	      }
	   }
	   
	   void makeJComboBox() {
	      dept_combo = new JComboBox();
	      for(int i = 0; i < dept_texts.length; i++) {
	         dept_combo.addItem(dept_texts[i]);
	      }
	   }
	   void makeImageIcon() {
	      emp_road = new ImageIcon("image\\emp_road.png");
	      emp_road_press = new ImageIcon("image\\emp_road_press.png");
	      emp_road_rollover = new ImageIcon("image\\emp_road_rollover.png");
	      table = new JTable();
	      table.setPreferredScrollableViewportSize(new Dimension(750,70));
	      btn = new JButton(emp_road);
	      btn.setPressedIcon(emp_road_press);
	      btn.setRolloverIcon(emp_road_rollover);
	      btn.setBorderPainted(false);
	      btn.setFocusPainted(false);
	      btn.setContentAreaFilled(false);
	      btn.addActionListener(this);
	      scroll = new JScrollPane(table);
	   }
	   
	   void makeJPanel() {
	      firstPanel = new JPanel(new GridLayout(7,1));
	      firstPanel.setBackground(Color.WHITE);
	      secondPanel = new JPanel(new BorderLayout());
	      secondPanel.setBackground(Color.WHITE);
	      
	      panels = new JPanel[7];
	      for(int i = 0; i < panels.length; i++) {
	         panels[i] = new JPanel();
	         panels[i].setBackground(Color.WHITE);
	      }
	   }
	   
	   EmployeeInfo_road(){
//	      super(str);
	      this.setLayout(new GridLayout(2,1));
	      
	      makeJLabel();
	      makeJTextField();
	      makeJComboBox();
	      makeImageIcon();
	      makeJPanel();
	      
	      Font font = new Font("Sandoll 고딕 01 Light", Font.BOLD, 30);
	      Color fontColor = new Color(116,116,116);
	      
	      labels[0].setFont(font);
	      labels[0].setForeground(fontColor);
	      
	      Font lblFont = new Font("Sandoll 고딕 01 Light", Font.PLAIN, 15);
	      Color lblFontColor = new Color(76,76,76);
	      
	      for(int i = 1; i < labels.length; i++) {
	         labels[i].setFont(lblFont);
	         labels[i].setForeground(lblFontColor);
	      }
	      
	      panels[0].add(new JLabel(""));
	      panels[1].add(labels[0]);
	      panels[2].add(labels[1]); panels[2].add(texts[0]);
	      panels[3].add(labels[2]); panels[3].add(texts[1]);
	      panels[4].add(labels[3]); panels[4].add(dept_combo);
	      panels[5].add(btn);
	      
	      for(int i = 0; i < panels.length; i++) {
	         firstPanel.add(panels[i]);
	      }
	      
	      secondPanel.add("Center",scroll);
	      
	      this.add(firstPanel);
	      this.add(secondPanel);
	      
	      this.setSize(1000, 600);
	      this.setVisible(true);
//	      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   }
	   
	//   public static void main(String[] args) {
//	      new EmployeeInfo_road("직원정보 조회 시스템");
	//   }

	}