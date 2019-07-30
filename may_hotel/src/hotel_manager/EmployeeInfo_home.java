package hotel_manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class EmployeeInfo_home extends JPanel{

	JPanel image_pen;
	JLabel label;
	
	public EmployeeInfo_home() {
		this.setLayout(new BorderLayout());
		
		JPanel main1 = new JPanel();
		main1.setLayout(new BorderLayout());
		image_pen = new Image_main("image\\배경.png");
		
		JPanel grid_pen = new JPanel(new GridLayout(3,3));
		grid_pen.add(new JPanel()); grid_pen.add(new JPanel()); grid_pen.add(new JPanel());
		grid_pen.add(new JPanel()); grid_pen.add(image_pen); grid_pen.add(new JPanel());
		grid_pen.add(new JPanel()); grid_pen.add(new JPanel()); grid_pen.add(new JPanel());
		
		label = new JLabel("HMS SYSTEM ver 1.0");
		label.setFont(new Font("Courier",Font.PLAIN,30));
		JPanel label_pen = new JPanel();
		label_pen.add(label);
		main1.add("Center",grid_pen); main1.add("South", label_pen);
		this.add("Center", main1);
		this.setSize(800, 500);
		this.setVisible(true);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
	//new Cus_Main2();
	}


}
class Image_main extends JPanel{
	Image i;
	
	Image_main(String path){
		i = getToolkit().getDefaultToolkit().getImage(path);
	}

//	@Override
//	protected void paintComponent(Graphics g) {
//		g.drawImage(i, 0, 0, this.getWidth(), this.getHeight(), this);
////		super.paintComponent(g);
//	}

	@Override
	public void paint(Graphics arg0) {
		arg0.clearRect(0, 0, this.getWidth(), this.getHeight());
		arg0.drawImage(i, 0, 0, this.getWidth(), this.getHeight(), this);
//		this.repaint();
	}
	
}