package hotel_manager;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class SalesInfo_road extends JPanel {

	JTabbedPane tab;
	
	SalesInfo_road_graph sales_graph;
	
	SalesInfo_road_calendar sales_calendar;
	
	void makeTabbedPane() {
		tab = new JTabbedPane();
		tab.addTab("캘린더", sales_calendar);
		tab.addTab("그래프",sales_graph );
	}
	
	SalesInfo_road(){
//		super(str);
	
		this.setLayout(new BorderLayout());
		
		sales_graph = new SalesInfo_road_graph();
		sales_calendar = new SalesInfo_road_calendar();
		
		makeTabbedPane();
		
		this.add("Center",tab);
		
		this.setBackground(Color.WHITE);
		
		this.setSize(1000, 600);
		this.setVisible(true);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
//	public static void main(String[] args) {
//		new SalesInfo_road("�������� ��ȸ �ý���");
//	}

}
