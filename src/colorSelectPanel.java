import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;
@SuppressWarnings("serial")
public class colorSelectPanel extends JPanel implements ActionListener{
	public Color paintcolor, paintcolor2;
	public JButton selectbutton, selectbutton2;
	public mainPanel mp;
    public colorSelectPanel(mainPanel mp)
    {  
    	setBorder(new TitledBorder("调色板"));
    	JLabel info2 = new JLabel("前景色/背景色");
    	paintcolor = Color.black;
    	paintcolor2 = Color.white;
    	this.setPreferredSize(new Dimension(100, 80));
    	selectbutton = new JButton(); 
    	selectbutton.setPreferredSize(new Dimension(35, 25));
    	selectbutton.setBackground(paintcolor);
    	selectbutton.addActionListener(this);
    	
    	selectbutton2 = new JButton(); 
    	selectbutton2.setPreferredSize(new Dimension(35, 25));
    	selectbutton2.setBackground(paintcolor2);
    	selectbutton2.addActionListener(this);
    	this.add(info2);
    	this.add(selectbutton);
    	this.add(selectbutton2);
    	this.mp = mp;
    }
	
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == selectbutton) {
			paintcolor = JColorChooser.showDialog(null, "选择一种颜色", paintcolor);
			selectbutton.setBackground(paintcolor);
			mp.paintpanel.paintColor = paintcolor;
		} else if(event.getSource() == selectbutton2){
			paintcolor2 = JColorChooser.showDialog(null, "选择一种颜色", paintcolor2);
			selectbutton2.setBackground(paintcolor2);
			mp.paintpanel.paintColor2 = paintcolor2;
		}
	
	}

}
