import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class colorSelectPanel extends JPanel implements ActionListener{
	public Color paintcolor;
	public JButton selectbutton;
	public mainPanel mp;
    public colorSelectPanel(mainPanel mp)
    {  
    	JLabel info = new JLabel("   调色板   ");
    	paintcolor = Color.black;
    	this.setPreferredSize(new Dimension(100,80));
    	selectbutton = new JButton(); 
    	selectbutton.setPreferredSize(new Dimension(90,25));
    	selectbutton.setBackground(paintcolor);
    	selectbutton.addActionListener(this);
    	this.add(info);
    	this.add(selectbutton);
    	this.mp = mp;
    }
	
	public void actionPerformed(ActionEvent event) {		
		paintcolor = JColorChooser.showDialog(null, "选择一种颜色", paintcolor);
		selectbutton.setBackground(paintcolor);
		mp.paintpanel.paintColor = paintcolor;
	
	}

}
