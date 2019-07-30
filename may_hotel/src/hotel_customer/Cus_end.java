package hotel_customer;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Cus_end extends JPanel{

	JPanel image_pen;
	JLabel label,label2;
	
	Cus_end() {
		this.setLayout(new BorderLayout());
		
		JPanel main1 = new JPanel();
		main1.setLayout(new BorderLayout());
		image_pen = new Image_main("image\\���.png");
		label2 = new JLabel("         이용해 주셔서 감사합니다.");
		JPanel grid_pen = new JPanel(new GridLayout(3,3));
		grid_pen.add(new JPanel()); grid_pen.add(new JPanel()); grid_pen.add(new JPanel());
		grid_pen.add(new JPanel()); grid_pen.add(image_pen); grid_pen.add(new JPanel());
		grid_pen.add(new JPanel()); grid_pen.add(label2); grid_pen.add(new JPanel());
		
		label = new JLabel("HMS SYSTEM ver 1.0");
	
		label2.setFont(new Font("Courier",Font.PLAIN,20));
		label.setFont(new Font("Courier",Font.PLAIN,30));
		JPanel label_pen = new JPanel();
		label_pen.add(label);
		main1.add("Center",grid_pen); main1.add("South", label_pen);
		this.add("Center", main1);
		this.setSize(800, 500);
		//this.setVisible(true);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		//new Cus_end();
	}


}
class Image5 extends JPanel{
	Image i;
	
	Image5(String path){
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