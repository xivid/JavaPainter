
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;

@SuppressWarnings("serial")
public class thickPanel extends JPanel implements ActionListener{
	Thickbutton[] tb;
	mainPanel mp;
	public thickPanel(mainPanel mp)
	{    
		 setBorder(new TitledBorder("ª≠± ¥÷œ∏"));
		 this.mp = mp;
		 tb = new Thickbutton[6];
		 for(int i = 0; i < 6; i++) tb[i] = new Thickbutton(i*2+1);
		 tb[0].setBorder(BorderFactory.createLoweredBevelBorder());
		 for(int i = 0; i < 6; i++){
			tb[i].addActionListener(this);
			tb[i].setToolTipText((i*2+1) + "œÒÀÿ");
		 	tb[i].setPreferredSize(new Dimension(40,30));
		 	add(tb[i]);	 
		 }
		 setPreferredSize(new Dimension(100, 130));
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
			g.fillOval(17-width/2,14-width/2, width+1, width+1);
		}
	}

}
