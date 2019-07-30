package hotel_manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

class CusModel1 extends AbstractTableModel{

	private Object[][] tableData;
	private int cols,rows;
	private String[] columnName = {"아이디","패스워드","이름","성별","가입일","휴대폰번호","이메일주소"};
	private List<CustomerInfo> list;
	CustomerInfo_road_table cus_road;
	
	CusModel1(CustomerInfo_road_table cus_road){
		this.cus_road = cus_road;
		CRUDprocess_emp crud = new CRUDprocess_emp();
		list = crud.selectCustomerInfo();
		setData();
	}
	
	private void setData() {
		Iterator it = list.iterator();
		rows = list.size();
		cols = columnName.length;
		tableData = new Object[rows][cols];
		int r = 0;
		while(it.hasNext()) {
			CustomerInfo info  = (CustomerInfo) it.next();
			tableData[r][0] = info.getCus_id();
			tableData[r][1] = info.getCus_pw();
			tableData[r][2] = info.getCus_name();
			tableData[r][3] = info.getCus_gen();
			tableData[r][4] = info.getCus_joindate();
			tableData[r][5] = info.getCus_phone();
			tableData[r][6] = info.getCus_email();
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

class CusModel2 extends AbstractTableModel{

	private Object[][] tableData;
	private int cols,rows;
	private String[] columnName = {"아이디","패스워드","이름","성별","가입일","휴대폰번호","이메일주소"};
	private List<CustomerInfo> list;
	CustomerInfo_road_table cus_road;
	
	CusModel2(CustomerInfo_road_table cus_road){
		this.cus_road = cus_road;
		CRUDprocess_emp crud = new CRUDprocess_emp();
		String id = cus_road.cusId_text.getText();
		list = crud.selectCustomerInfoByCusID(id);
		setData();
	}
	
	private void setData() {
		Iterator it = list.iterator();
		rows = list.size();
		cols = columnName.length;
		tableData = new Object[rows][cols];
		int r = 0;
		while(it.hasNext()) {
			CustomerInfo info  = (CustomerInfo) it.next();
			tableData[r][0] = info.getCus_id();
			tableData[r][1] = info.getCus_pw();
			tableData[r][2] = info.getCus_name();
			tableData[r][3] = info.getCus_gen();
			tableData[r][4] = info.getCus_joindate();
			tableData[r][5] = info.getCus_phone();
			tableData[r][6] = info.getCus_email();
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

class CusModel3 extends AbstractTableModel{

	private Object[][] tableData;
	private int cols,rows;
	private String[] columnName = {"아이디","패스워드","이름","성별","가입일","휴대폰번호","이메일주소"};
	private List<CustomerInfo> list;
	CustomerInfo_road_table cus_road;
	
	CusModel3(CustomerInfo_road_table cus_road){
		this.cus_road = cus_road;
		CRUDprocess_emp crud = new CRUDprocess_emp();
		String email = cus_road.cusEmail_text.getText()+"@"+cus_road.email_addr.getSelectedItem();
		list = crud.selectCustomerInfoByCusEmail(email);
		setData();
	}
	
	private void setData() {
		Iterator it = list.iterator();
		rows = list.size();
		cols = columnName.length;
		tableData = new Object[rows][cols];
		int r = 0;
		while(it.hasNext()) {
			CustomerInfo info  = (CustomerInfo) it.next();
			tableData[r][0] = info.getCus_id();
			tableData[r][1] = info.getCus_pw();
			tableData[r][2] = info.getCus_name();
			tableData[r][3] = info.getCus_gen();
			tableData[r][4] = info.getCus_joindate();
			tableData[r][5] = info.getCus_phone();
			tableData[r][6] = info.getCus_email();
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

public class CustomerInfo_road_table extends JPanel implements ActionListener{
	
	JLabel[] labels;
	String[] label_titles = {"회원 정보 조회","고객 아이디","고객 이메일","@"};
	
	JTextField cusId_text, cusEmail_text;
	
	JComboBox email_addr;
	String[] email = {"naver.com","daum.net","hanmail.net","nate.com","gmail.com","yahoo.com","korea.com"};
	
	JButton btn;
	
	ImageIcon road_icon, road_press, road_rollover;
	
	JPanel firstPanel,secondPanel;
	
	JPanel[] panels;
	
	JTable table;
	
	JScrollPane scroll;
	void makeJLabel() {
		labels = new JLabel[4];
		for(int i = 0; i < labels.length; i++) {
			labels[i] = new JLabel(label_titles[i]); 
		}
	}
	
	void makeJTextField() {
		cusId_text = new JTextField(20);
		cusEmail_text = new JTextField(10);
	}
	
	void makeJComboBox() {
		email_addr = new JComboBox();
		for(int i = 0; i < email.length; i++) {
			email_addr.addItem(email[i]);
		}
	}
	
	void makeJButton() {
		btn = new JButton();
	}
	
	void makeImageIcon() {
		road_icon = new ImageIcon("image\\cus_road.png");
		road_press = new ImageIcon("image\\cus_road_press.png");
		road_rollover = new ImageIcon("image\\cus_road_rollover.png");
		
		btn = new JButton(road_icon);
		btn.setPressedIcon(road_press);
		btn.setRolloverIcon(road_rollover);
		btn.setContentAreaFilled(false);
		btn.setFocusPainted(false);
		btn.setBorderPainted(false);
		btn.addActionListener(this);
	}
	
	void makeJPanel() {
		firstPanel = new JPanel();
		firstPanel.setLayout(new GridLayout(7,1));
		firstPanel.setBackground(Color.WHITE);
		
		secondPanel = new JPanel(new BorderLayout());
		secondPanel.setBackground(Color.WHITE);
		
		panels = new JPanel[7];
		for(int i = 0; i < panels.length; i++) {
			panels[i] = new JPanel();
			panels[i].setBackground(Color.WHITE);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object obj = arg0.getSource();
		if(obj == btn) {
			if(!cusId_text.getText().equals("")) {
				table.setModel(new CusModel2(this));
			}else if(!cusEmail_text.getText().equals("")) {
				table.setModel(new CusModel3(this));
			}else {
				table.setModel(new CusModel1(this));
			}
		}
	}

	CustomerInfo_road_table(){
//		super(str);
		
		this.setLayout(new GridLayout(2,1));
		
		makeJLabel();
		makeJTextField();
		makeJComboBox();
		makeJButton();
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
		panels[2].add(new JLabel(""));
		panels[3].add(labels[1]); panels[3].add(cusId_text);
		panels[4].add(labels[2]); panels[4].add(cusEmail_text); panels[4].add(labels[3]); panels[4].add(email_addr);
		panels[5].add(btn);
		panels[6].add(new JLabel(""));
		
		for(int i = 0; i < panels.length; i++) {
			firstPanel.add(panels[i]);
		}
		
		table = new JTable();
		scroll = new JScrollPane(table);
		
		secondPanel.add("Center",scroll);
		
		this.add(firstPanel);
		this.add(secondPanel);
		
		this.setSize(900,650);
		this.setVisible(true);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
//	public static void main(String[] args) {
//		new CustomerInfo_road_table("회원 정보 조회 시스템");
//	}

}
