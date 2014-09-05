
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class thickPanel extends JPanel implements ActionListener{
	Thickbutton[] tb;
	mainPanel mp;
	public thickPanel(mainPanel mp)
	{    
		 JLabel info = new JLabel(" ª≠± ¥÷œ∏ ");
		 this.mp = mp;
		 tb = new Thickbutton[6];
		 for(int i = 0; i < 6; i++) tb[i] = new Thickbutton(i*2+1);
		 tb[0].setBorder(BorderFactory.createLoweredBevelBorder());
		 this.add(info);
		 for(int i = 0; i < 6; i++){
			tb[i].addActionListener(this);
		 	tb[i].setPreferredSize(new Dimension(45,30));
		 	this.add(tb[i]);	 
		 }
		 this.setPreferredSize(new Dimension(100, 130));
//		 thickbutton1 = new Thickbutton(2);
//		 thickbutton2 = new Thickbutton(4);
//		 thickbutton3 = new Thickbutton(8);
//		 thickbutton4 = new Thickbutton(12);
//		 thickbutton1.setBorder(BorderFactory.createLoweredBevelBorder());
//		 thickbutton1.addActionListener(this);
//		 thickbutton2.addActionListener(this);
//		 thickbutton3.addActionListener(this);
//		 thickbutton4.addActionListener(this);
//		 thickbutton1.setPreferredSize(new Dimension(45,30));
//		 thickbutton2.setPreferredSize(new Dimension(45,30));
//		 thickbutton3.setPreferredSize(new Dimension(45,30));
//		 thickbutton4.setPreferredSize(new Dimension(45,30));
//		 this.setPreferredSize(new Dimension(100,70));
//		 this.add(thickbutton1);
//		 this.add(thickbutton2);
//		 this.add(thickbutton3);
//		 this.add(thickbutton4);
	}

	public void actionPerformed(ActionEvent e) {
		Thickbutton bt = (Thickbutton) e.getSource();
		mp.paintpanel.thick = bt.getwidth();       
		for(int i = 0; i < 6; i++) tb[i].setBorder(BorderFactory.createRaisedBevelBorder());
	    bt.setBorder(BorderFactory.createLoweredBevelBorder());
	}
	
	public class Thickbutton extends JButton{   
		private int width;
		public Thickbutton(int width) {   
			this.setBorder(BorderFactory.createRaisedBevelBorder());
			this.width = width;
		}
		
		public float getwidth() { return (float)width; }
		
		@Override
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.setColor(Color.black);
			g.fillOval(23-width/2,15-width/2, width+1, width+1);
		}
	}

}
