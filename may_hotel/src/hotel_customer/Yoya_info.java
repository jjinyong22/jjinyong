package hotel_customer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

class YoyaModel extends AbstractTableModel{
	private Object[][] tableData;
	private int rows,cols;
	private String[] columnName = {"예약번호", "예약고객아이디", "예약객실아이디", "예약날짜",
			"체크인날짜","체크아웃날짜", "조식포함여부", "총예약금액", "예약인원", "진행상황"};
	private List<Yoya_batis> list;
	
	YoyaModel(){
		CRUDprocess crud = new CRUDprocess();
		list = crud.selectyoya();
		setData();
	}
	private void setData() {
		Iterator it = list.iterator();
		rows = list.size();
		cols = columnName.length;
		tableData = new Object[rows][cols];
		int r= 0;
		customer_info info = Screen.info;
		while(it.hasNext()) {
			Yoya_batis em = (Yoya_batis)it.next();
			String name = em.getRes_id();
			
			if(name.equals(info.getCus_id())) {
			
			tableData[r][0] = em.getRes_num();
			tableData[r][1] = em.getRes_id();
			tableData[r][2] = em.getRes_room();
			tableData[r][3] = em.getRes_date();
			tableData[r][4] = em.getRes_checkin();
			tableData[r][5] = em.getRes_checkout();
			tableData[r][6] = em.getRes_breakfast();
			tableData[r][7] = em.getRes_payment();
			tableData[r][8] = em.getRes_head();
			tableData[r][9] = em.getRes_state();
			r++;
			}
			}
	}
	@Override
	public String getColumnName(int arg0) {//���� �ٲٴ°�
		// TODO Auto-generated method stub
		return columnName[arg0];
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return cols;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return rows;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return tableData[rowIndex][columnIndex];
	}
	
}
public class Yoya_info extends JPanel implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		table.setModel(new YoyaModel());
	}

	void makeMenubar() {
		bar = new JMenuBar();
		menu = new JMenu("예약안내");
		menu2 = new JMenu("객실안내");
		menu3 = new JMenu("이벤트안내");
		menu4 = new JMenu("시스템종료");
		bar_yo = new JMenuItem("예약조회");
		bar_yo2 = new JMenuItem("예약하기");
		bar_yo3 = new JMenuItem("예약변경");
		bar_yo4 = new JMenuItem("예약취소");
		bar_gae = new JMenuItem("STANDARD");
		bar_gae2 = new JMenuItem("SUPERIOR");
		bar_gae3 = new JMenuItem("DELUXE");
		bar_gae4 = new JMenuItem("EXECUTIVE");
		bar_ev = new JMenuItem("이벤트조회");
		bar_exit = new JMenuItem("종료하기");
		menu.add(bar_yo);
		menu.add(bar_yo2);
		menu.add(bar_yo3);
		menu.add(bar_yo4);
		menu2.add(bar_gae);
		menu2.add(bar_gae2);
		menu2.add(bar_gae3);
		menu2.add(bar_gae4);
		menu3.add(bar_ev);
		menu4.add(bar_exit);
		bar.add(menu);
		bar.add(menu2);
		bar.add(menu3);
		bar.add(menu4);
		//this.setJMenuBar(bar);
	};

	JMenuBar bar;
	JMenuItem bar_yo, bar_yo2, bar_yo3, bar_yo4, bar_gae, bar_gae2, bar_gae3, bar_gae4, bar_ev, bar_exit;
	JMenu menu, menu2, menu3, menu4;
	JPanel pan, pan2, pan3,pan4,pan5,pan6,pan7;
	JLabel lab;
	JButton bts;
	JTable table;
	JScrollPane scroll;

	ImageIcon road_icon, road_press, road_rollover;
	
	void makeImageIcon() {
		road_icon = new ImageIcon("image\\조회1.png");
		road_press = new ImageIcon("image\\조회2.png");
		road_rollover = new ImageIcon("image\\조회3.png");
	}
	
	void makeJtable_bts() {
		table = new JTable();
		table.setPreferredScrollableViewportSize(new Dimension(750,600));
		// this.add("Center",table);
		bts = new JButton(road_icon);
		bts.addActionListener(this);
		bts.setPressedIcon(road_press);//��ư��������
		bts.setRolloverIcon(road_rollover);//��ư���콺�ø���
		bts.setContentAreaFilled(false);//ä������
		bts.setBorderPainted(false);//�ܰ��� ���ش�
		bts.setFocusPainted(false);//�׵θ� ������
		pan = new JPanel();pan2 = new JPanel();pan3 = new JPanel();
		pan4 = new JPanel();pan5 = new JPanel();pan6 = new JPanel();
		pan7 = new JPanel();
		lab = new JLabel("예약 정보 조회");
		pan2.add(lab);
		pan3.add(table);
		pan7.add(bts);
		scroll = new JScrollPane(table);
		pan3.add(scroll);
		//this.add(pan);
		this.add("North",pan2);
		this.add("Center",pan3);
		//this.add(pan4);this.add(pan5);this.add(pan6);
		this.add("South",pan7);
	}

	public Yoya_info() {
		//super(str);
		//this.setLayout(new GridLayout(3,1));
		this.setLayout(new BorderLayout());
		makeImageIcon();
		makeMenubar();
		makeJtable_bts();
		this.setSize(800, 500);
		this.setVisible(true);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		//new Yoyaku("ȣ�ڰ��� �ý���");
	}

}
