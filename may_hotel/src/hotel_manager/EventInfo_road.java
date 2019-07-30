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
import javax.swing.table.AbstractTableModel;

class EventModel1 extends AbstractTableModel{

	private Object[][] tableData;
	private int cols,rows;
	private String[] columnName = {"이벤트번호","이벤트이름","이벤트내용","이벤트시작일","이벤트종료일","이벤트이미지경로"};
	private List<EventInfo> list;
	EventInfo_road event_road;
	
	EventModel1(EventInfo_road event_road){
		this.event_road = event_road;
		String year = String.valueOf(event_road.combo.getSelectedItem());
		String month = String.valueOf(event_road.combo2.getSelectedItem());
		if(Integer.parseInt(month) < 10){
			month = "0"+month;
		}
		String date = year + "-" + month;
		CRUDprocess_emp crud = new CRUDprocess_emp();
		list = crud.selectEventInfoByDate(date);
		setData();
	}
	
	private void setData(){
		Iterator it = list.iterator();
		rows = list.size();
		cols = columnName.length;
		tableData = new Object[rows][cols];
		int r = 0;
		while(it.hasNext()){
			EventInfo info = (EventInfo)it.next();
			tableData[r][0] = info.getEvent_id();
			tableData[r][1] = info.getEvent_name();
			tableData[r][2] = info.getEvent_info();
			tableData[r][3] = info.getEvent_begin();
			tableData[r][4] = info.getEvent_close();
			tableData[r][5] = info.getEvent_image();
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

public class EventInfo_road extends JPanel implements ActionListener{
	
	JLabel label, label2, label3; 
	JButton button; 
	JPanel[] panel;
	JPanel tablePanel;
	JComboBox combo, combo2;  
	String[] Label_txt = {"이벤트 정보 조회", "년", "월"};
	
	ImageIcon road_icon, road_press, road_rollover;

	JTable table;
	
	JScrollPane scroll;
	
	void UseButton() {
		road_icon = new ImageIcon("image\\event_road.png");
		road_press = new ImageIcon("image\\event_road_press.png");
		road_rollover = new ImageIcon("image\\event_road_rollover.png");
		button = new JButton(road_icon);
		button.setPressedIcon(road_press);
		button.setRolloverIcon(road_rollover);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object obj = arg0.getSource();
		if(obj == button){
			table.setModel(new EventModel1(this));	
		}
	}
	
	EventInfo_road() {
//		super(str);
		this.setLayout(new GridLayout(2,1));
		panel = new JPanel[3];
		label = new JLabel("이벤트 정보 조회");
		label2 = new JLabel("년");
		label3 = new JLabel("월");
		
		for(int i = 0; i<panel.length; i++) {
			panel[i] = new JPanel();
			panel[i].setBackground(Color.WHITE);
		}
		
		panel[0].setLayout(new GridLayout(3,1));
		
		combo = new JComboBox();
		for(int i = 2019; i >= 1990; i--) {
			combo.addItem(i+"");
		}
		
		combo2 = new JComboBox();
		for(int i = 1; i <= 12; i++) {
			combo2.addItem(i+"");
		}
		
		UseButton();
		
		panel[0].add(new JLabel(""));
		panel[0].add(label);
		panel[0].add(new JLabel(""));
		
		label.setHorizontalAlignment(label.CENTER);
		
		Font font = new Font("Sandoll 고딕 01 Light", Font.BOLD, 30);
		Color fontColor = new Color(116,116,116);
		
		label.setFont(font); label.setForeground(fontColor);
		
		Font lblFont = new Font("Sandoll 고딕 01 Light", Font.PLAIN, 15);
		Color lblFontColor = new Color(76,76,76);
		
		label2.setFont(lblFont); label3.setFont(lblFont);
		label2.setForeground(lblFontColor); label3.setForeground(lblFontColor);
		
		panel[1].add(combo); panel[1].add(label2);  
		panel[1].add(combo2);  panel[1].add(label3);
		panel[1].add(button);
		
		panel[2].setLayout(new GridLayout(3,1));
		panel[2].add(panel[0]);
		panel[2].add(panel[1]);
		
		this.add(panel[2]);
		
		this.setBackground(Color.WHITE);
		
		table = new JTable();
		scroll = new JScrollPane(table);
		
		tablePanel = new JPanel(new BorderLayout());
		tablePanel.add(scroll);
		tablePanel.setBackground(Color.WHITE);
		
		this.add(tablePanel);
		this.setVisible(true);
		this.setSize(800, 500);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

//	public static void main(String[] args) {
//		new EventInfo_road("이벤트 정보 조회 시스템");
//	}

}
