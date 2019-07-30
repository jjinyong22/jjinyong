package hotel_manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

class CusModel5 extends AbstractTableModel{

	private Object[][] tableData;
	private int cols,rows;
	private String[] columnName = {"아이디","패스워드","이름","성별","가입일","휴대폰번호","이메일주소"};
	private List<CustomerInfo> list;
	CustomerInfo_delete cus_delete;
	
	CusModel5(CustomerInfo_delete cus_delete){
		this.cus_delete = cus_delete;
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


class CusModel4 extends AbstractTableModel{

	private Object[][] tableData;
	private int cols,rows;
	private String[] columnName = {"아이디","패스워드","이름","성별","가입일","휴대폰번호","이메일주소"};
	private List<CustomerInfo> list;
	CustomerInfo_delete cus_delete;
	
	CusModel4(CustomerInfo_delete cus_delete){
		this.cus_delete = cus_delete;
		CRUDprocess_emp crud = new CRUDprocess_emp();
		String id = cus_delete.texts[0].getText();
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
public class CustomerInfo_delete extends JPanel implements ActionListener,MouseListener{
	
	JLabel[] labels;
	String[] label_titles = {"회원 정보 삭제","고객 아이디","고객 아이디     ","패스워드","성별","전화번호","-","-","이메일","@"}; 
	
	JTextField[] texts;
	
	JPasswordField text_pw;
	
	JButton[] btns;
	
	ImageIcon road_icon, road_press, road_rollover, delete_icon, delete_press, delete_rollover, 
				 cancel_icon, cancel_press, cancel_rollover;
	
	JRadioButton radio_m, radio_f;
	ButtonGroup group;
	
	JComboBox phone_combo, email_combo;
	String[] phone_texts = {"010","011","012","013","014","015","016","017","018","019"};
	String[] email_texts = {"naver.com","daum.net","hanmail.net","nate.com","gmail.com","yahoo.com","korea.com"};
	
	JPanel centerPanel, northPanel, southPanel;
	
	JPanel center_northPanel;
	
	JPanel[] panels;
	
	JTable table;
	
	JScrollPane scroll;
	void makeJLabel() {
		labels = new JLabel[10];
		for(int i = 0; i < labels.length; i++) {
			labels[i] = new JLabel(label_titles[i]);
		}
	}
	
	void makeJTextField() {
		texts = new JTextField[5];
		for(int i = 0; i < texts.length; i++) {
			texts[i] = new JTextField();
		}
		texts[0] = new JTextField(20);
		texts[1] = new JTextField(20);
		text_pw = new JPasswordField(20);
		texts[2] = new JTextField(5);
		texts[3] = new JTextField(5);
		texts[4] = new JTextField(15);
	}
	
	void makeJButton() {
		btns = new JButton[3];
		for(int i = 0; i < btns.length; i++) {
			btns[i] = new JButton();
		}
	}
	
	void makeImageIcon() {
		road_icon = new ImageIcon("image\\cus_road.png");
		road_press = new ImageIcon("image\\cus_road_press.png");
		road_rollover = new ImageIcon("image\\cus_road_rollover.png");
		
		delete_icon = new ImageIcon("image\\delete.png");
		delete_press = new ImageIcon("image\\delete_press.png");
		delete_rollover = new ImageIcon("image\\delete_rollover.png");
		
		cancel_icon = new ImageIcon("image\\cancel.png");
		cancel_press = new ImageIcon("image\\cancel_press.png");
		cancel_rollover = new ImageIcon("image\\cancel_rollover.png");
		
		btns[0] = new JButton(road_icon);
		btns[0].setPressedIcon(road_press);
		btns[0].setRolloverIcon(road_rollover);
		btns[0].setBorderPainted(false);
		btns[0].setFocusPainted(false);
		btns[0].setContentAreaFilled(false);
		btns[0].addActionListener(this);
		
		btns[1] = new JButton(delete_icon);
		btns[1].setPressedIcon(delete_press);
		btns[1].setRolloverIcon(delete_rollover);
		btns[1].setBorderPainted(false);
		btns[1].setFocusPainted(false);
		btns[1].setContentAreaFilled(false);
		btns[1].addActionListener(this);
		
		btns[2] = new JButton(cancel_icon);
		btns[2].setPressedIcon(cancel_press);
		btns[2].setRolloverIcon(cancel_rollover);
		btns[2].setBorderPainted(false);
		btns[2].setFocusPainted(false);
		btns[2].setContentAreaFilled(false);
	}
	
	void makeJRadioButton() {
		radio_m = new JRadioButton("남자");
		radio_m.setBackground(Color.WHITE);
		radio_f = new JRadioButton("여자");
		radio_f.setBackground(Color.WHITE);
		
		group = new ButtonGroup();
		group.add(radio_m); group.add(radio_f);
	}
	
	void makeJComboBox() {
		phone_combo = new JComboBox();
		for(int i = 0; i < phone_texts.length; i++) {
			phone_combo.addItem(phone_texts[i]);
		}
		
		email_combo = new JComboBox();
		for(int i = 0; i < email_texts.length; i++) {
			email_combo.addItem(email_texts[i]);
		}
	}
	
	void makeJPanel() {
		northPanel = new JPanel(new GridLayout(2,1));
		northPanel.setBackground(Color.WHITE);
		
		centerPanel = new JPanel(new BorderLayout());
		centerPanel.setBackground(Color.WHITE);
		
		center_northPanel = new JPanel();
		center_northPanel.setBackground(Color.WHITE);
		
		southPanel = new JPanel(new GridLayout(8,1));
		southPanel.setBackground(Color.WHITE);
		
		panels = new JPanel[8];
		for(int i = 0; i < panels.length; i++) {
			panels[i] = new JPanel();
			panels[i].setBackground(Color.WHITE);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object obj = arg0.getSource();
		if(obj == btns[0]) {
			if(!texts[0].getText().equals("")) {
				table.setModel(new CusModel4(this));
			}else {
				table.setModel(new CusModel5(this));
			}
		}
		if(obj == btns[1]) {
			String[] str = {"삭 제","취 소"}; 
			int select = JOptionPane.showOptionDialog(this, "삭제하시겠습니까?", "삭 제", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, str, str[0]);
			if(select == JOptionPane.YES_OPTION) {
				String id = texts[1].getText();
				CRUDprocess_emp crud = new CRUDprocess_emp();
				int result = crud.deleteCustomerInfoByCusId(id);
				if(result > 0) {
					JOptionPane.showMessageDialog(this, "회원 정보가 삭제되었습니다.");
					texts[0].setText(" ");
					texts[0].setText("");
					texts[1].setText(" ");
					texts[1].setText("");
					texts[1].setEnabled(true);
					texts[2].setText(" ");
					texts[2].setText("");
					texts[2].setEnabled(true);
					radio_m.setSelected(false);
					radio_f.setSelected(false);
					radio_m.setEnabled(true);
					radio_f.setSelected(true);
					phone_combo.setSelectedIndex(0);
					phone_combo.setEnabled(true);
					texts[3].setText(" ");
					texts[3].setText("");
					texts[3].setEnabled(true);
					texts[4].setText(" ");
					texts[4].setText("");
					texts[4].setEnabled(true);
					text_pw.setText(" ");
					text_pw.setText("");
					text_pw.setEnabled(true);
					email_combo.setSelectedIndex(0);
					email_combo.setEnabled(true);
					table.setModel(new CusModel5(this));
				}else {
					JOptionPane.showMessageDialog(this, "회원 정보 삭제 과정에서 문제가 발생했습니다.");
				}
			}
		}
		if(obj == btns[2]) {
			texts[0].setText(" ");
			texts[0].setText("");
			texts[1].setText(" ");
			texts[1].setText("");
			texts[1].setEnabled(true);
			texts[2].setText(" ");
			texts[2].setText("");
			texts[2].setEnabled(true);
			radio_m.setSelected(false);
			radio_f.setSelected(false);
			radio_m.setEnabled(true);
			radio_f.setSelected(true);
			phone_combo.setSelectedIndex(0);
			phone_combo.setEnabled(true);
			texts[3].setText(" ");
			texts[3].setText("");
			texts[3].setEnabled(true);
			texts[4].setText(" ");
			texts[4].setText("");
			texts[4].setEnabled(true);
			text_pw.setText(" ");
			text_pw.setText("");
			text_pw.setEnabled(true);
			email_combo.setSelectedIndex(0);
			email_combo.setEnabled(true);
			table.setModel(new CusModel5(this));
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		int selectedRow = table.getSelectedRow();
		String cus_id = table.getValueAt(selectedRow, 0)+"";
		texts[1].setText(cus_id);
		texts[1].setEnabled(false);
		String cus_pw = table.getValueAt(selectedRow, 1)+"";
		text_pw.setText(cus_pw);
		text_pw.setEnabled(false);
		String cus_gen = (table.getValueAt(selectedRow, 3)+"");
		if(cus_gen.equals("M")) {
			radio_m.setSelected(true);
			radio_m.setEnabled(false);
		}else if(cus_gen.equals("F")) {
			radio_f.setSelected(true);
			radio_f.setEnabled(false);
		}
		String phone = table.getValueAt(selectedRow, 5)+"";
		String first_num = phone.substring(0,2);
		String middle_num = phone.substring(4,8);
		String last_num = phone.substring(9,13);
		phone_combo.setSelectedItem(first_num);
		phone_combo.setEnabled(false);
		texts[2].setText(middle_num);
		texts[2].setEnabled(false);
		texts[3].setText(last_num);
		texts[3].setEnabled(false);
		String email = table.getValueAt(selectedRow, 6)+"";
		int idx = email.indexOf("@");
		String email_id = email.substring(0,idx);
		String email_address = email.substring(idx+1);
		texts[4].setText(email_id);
		texts[4].setEnabled(false);
		email_combo.setSelectedItem(email_address);
		email_combo.setEnabled(false);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

	CustomerInfo_delete(){
//		super(str);
		
		this.setLayout(new BorderLayout());
		
		makeJLabel();
		makeJTextField();
		makeJButton();
		makeImageIcon();
		makeJRadioButton();
		makeJComboBox();
		makeJPanel();
		
		Font font = new Font("Sandoll 고딕 01 Light", Font.BOLD, 30);
		Color fontColor = new Color(116,116,116);
		labels[0].setFont(font);
		labels[0].setForeground(fontColor);
		labels[0].setHorizontalAlignment(labels[0].CENTER);
		
		Font lblFont = new Font("Sandoll 고딕 01 Light", Font.PLAIN, 15);
		Color lblFontColor = new Color(76,76,76);
		
		for(int i = 1; i < labels.length; i++) {
			labels[i].setFont(lblFont);
			labels[i].setForeground(lblFontColor);
		}
		
		northPanel.add(new JLabel(""));
		northPanel.add(labels[0]);
		
		center_northPanel.add(labels[1]); center_northPanel.add(texts[0]); center_northPanel.add(btns[0]);
		
		table = new JTable();
		table.addMouseListener(this);
		scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(800,400));
		
		centerPanel.add("North", center_northPanel);
		centerPanel.add("Center", scroll);
		
		panels[0].add(new JLabel());
		panels[1].add(labels[2]); panels[1].add(texts[1]);
		panels[2].add(labels[3]); panels[2].add(text_pw);
		panels[3].add(labels[4]); panels[3].add(radio_m); panels[3].add(radio_f);
		radio_m.setFont(lblFont); radio_m.setForeground(lblFontColor);
		radio_f.setFont(lblFont); radio_f.setForeground(lblFontColor);
		panels[4].add(labels[5]); panels[4].add(phone_combo); panels[4].add(labels[6]); panels[4].add(texts[2]); panels[4].add(labels[7]); panels[4].add(texts[3]);
		panels[5].add(labels[8]); panels[5].add(texts[4]); panels[5].add(labels[9]); panels[5].add(email_combo);
		panels[6].add(btns[1]); panels[6].add(btns[2]);
		panels[7].add(new JLabel(""));
		
		for(int i = 0; i < panels.length; i++) {
			southPanel.add(panels[i]);
		}
		
		this.add("North",northPanel);
		this.add("Center", centerPanel);
		this.add("South", southPanel);
		
		this.setSize(900,800);
		this.setVisible(true);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
//	public static void main(String[] args) {
//		new CustomerInfo_delete("회원 정보 삭제 시스템");
//	}

}
