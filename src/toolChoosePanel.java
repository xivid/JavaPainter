
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class toolChoosePanel extends JPanel implements ActionListener{
	JButton pencil, eraser, pline, pcircle, prectangle, filler, clear, undo, redo;
	mainPanel mp;
	String chosentool;
	public toolChoosePanel(mainPanel mp)
	{   
		this.mp = mp;
		JLabel info = new JLabel("    π§æﬂœ‰    ");
		pencil = new JButton(new ImageIcon("src/icon/pencil.png"));
		eraser = new JButton(new ImageIcon("src/icon/eraser.png"));
		pline = new JButton(new ImageIcon("src/icon/line.png"));
		pcircle = new JButton(new ImageIcon("src/icon/ellipse.png"));
		prectangle = new JButton(new ImageIcon("src/icon/rectangle.png"));
		filler = new JButton(new ImageIcon("src/icon/filler.png"));
		clear = new JButton(new ImageIcon("src/icon/clear.png"));
		undo = new JButton(new ImageIcon("src/icon/undo.png"));
		redo = new JButton(new ImageIcon("src/icon/redo.png"));
		
		chosentool = "pencil";
		pencil.setBorder(BorderFactory.createLoweredBevelBorder());
		eraser.setBorder(BorderFactory.createRaisedBevelBorder());
		pline.setBorder(BorderFactory.createRaisedBevelBorder());
		pcircle.setBorder(BorderFactory.createRaisedBevelBorder());
		prectangle.setBorder(BorderFactory.createRaisedBevelBorder());
		filler.setBorder(BorderFactory.createRaisedBevelBorder());
		clear.setBorder(BorderFactory.createRaisedBevelBorder());
		
		pencil.setPreferredSize(new Dimension(40,30));
		eraser.setPreferredSize(new Dimension(45,30));
		pline.setPreferredSize(new Dimension(45,30));
		pcircle.setPreferredSize(new Dimension(45,30));
		prectangle.setPreferredSize(new Dimension(45,30));
		undo.setPreferredSize(new Dimension(45,30));
		redo.setPreferredSize(new Dimension(45,30));
		filler.setPreferredSize(new Dimension(45,30));
		clear.setPreferredSize(new Dimension(90,30));
		
		pencil.addActionListener(this);
		eraser.addActionListener(this);
		pline.addActionListener(this);
		pcircle.addActionListener(this);
		prectangle.addActionListener(this);
		filler.addActionListener(this);
		undo.addActionListener(this);
		redo.addActionListener(this);
		clear.addActionListener(this);
		
		this.setPreferredSize(new Dimension(100,200));
		this.add(info);
		this.add(pencil);
		this.add(eraser);
		this.add(pline);
		this.add(pcircle);
		this.add(prectangle);
		this.add(filler);
		this.add(undo);
		this.add(redo);
		this.add(clear);
	}
	
	public void actionPerformed(ActionEvent e) {		
		if(e.getSource() == pencil)
		{
			mp.paintpanel.paintColor=mp.colorselectpanel.paintcolor;
			chosentool = "pencil";
			pencil.setBorder(BorderFactory.createLoweredBevelBorder());
			eraser.setBorder(BorderFactory.createRaisedBevelBorder());
			pline.setBorder(BorderFactory.createRaisedBevelBorder());
			pcircle.setBorder(BorderFactory.createRaisedBevelBorder());
			prectangle.setBorder(BorderFactory.createRaisedBevelBorder());
			filler.setBorder(BorderFactory.createRaisedBevelBorder());
			clear.setBorder(BorderFactory.createRaisedBevelBorder());
			mp.paintpanel.imgcursor = mp.paintpanel.kit.getImage("src/icon/pencil.png");
			mp.paintpanel.repaint();
			
		}
		else if(e.getSource() == eraser)
		{
			mp.paintpanel.paintColor = Color.white;
			chosentool = "eraser";
			eraser.setBorder(BorderFactory.createLoweredBevelBorder());
			pencil.setBorder(BorderFactory.createRaisedBevelBorder());
			pline.setBorder(BorderFactory.createRaisedBevelBorder());
			pcircle.setBorder(BorderFactory.createRaisedBevelBorder());
			prectangle.setBorder(BorderFactory.createRaisedBevelBorder());
			filler.setBorder(BorderFactory.createRaisedBevelBorder());
			clear.setBorder(BorderFactory.createRaisedBevelBorder());
			mp.paintpanel.imgcursor = mp.paintpanel.kit.getImage("src/icon/eraser.png");
			mp.paintpanel.repaint();
		}
		else if(e.getSource() == pline){
			mp.paintpanel.paintColor=mp.colorselectpanel.paintcolor;
			chosentool = "pline";
			pencil.setBorder(BorderFactory.createRaisedBevelBorder());
			eraser.setBorder(BorderFactory.createRaisedBevelBorder());
			pline.setBorder(BorderFactory.createLoweredBevelBorder());
			pcircle.setBorder(BorderFactory.createRaisedBevelBorder());
			prectangle.setBorder(BorderFactory.createRaisedBevelBorder());
			filler.setBorder(BorderFactory.createRaisedBevelBorder());
			clear.setBorder(BorderFactory.createRaisedBevelBorder());
			mp.paintpanel.imgcursor = mp.paintpanel.kit.getImage("src/icon/pencil.png");
			mp.paintpanel.repaint();
		}
		else if(e.getSource() == pcircle) {
			mp.paintpanel.paintColor=mp.colorselectpanel.paintcolor;
			chosentool = "pcircle";
			pencil.setBorder(BorderFactory.createRaisedBevelBorder());
			eraser.setBorder(BorderFactory.createRaisedBevelBorder());
			pline.setBorder(BorderFactory.createRaisedBevelBorder());
			pcircle.setBorder(BorderFactory.createLoweredBevelBorder());
			prectangle.setBorder(BorderFactory.createRaisedBevelBorder());
			filler.setBorder(BorderFactory.createRaisedBevelBorder());
			clear.setBorder(BorderFactory.createRaisedBevelBorder());
			mp.paintpanel.imgcursor = mp.paintpanel.kit.getImage("src/icon/pencil.png");
			mp.paintpanel.repaint();
		}
		else if(e.getSource() == prectangle) {
			mp.paintpanel.paintColor=mp.colorselectpanel.paintcolor;
			chosentool = "prectangle";
			pencil.setBorder(BorderFactory.createRaisedBevelBorder());
			eraser.setBorder(BorderFactory.createRaisedBevelBorder());
			pline.setBorder(BorderFactory.createRaisedBevelBorder());
			pcircle.setBorder(BorderFactory.createRaisedBevelBorder());
			prectangle.setBorder(BorderFactory.createLoweredBevelBorder());
			filler.setBorder(BorderFactory.createRaisedBevelBorder());
			clear.setBorder(BorderFactory.createRaisedBevelBorder());
			mp.paintpanel.imgcursor = mp.paintpanel.kit.getImage("src/icon/pencil.png");
			mp.paintpanel.repaint();
		}
		else if(e.getSource() == filler) {
			mp.paintpanel.paintColor=mp.colorselectpanel.paintcolor;
			chosentool = "filler";
			pencil.setBorder(BorderFactory.createRaisedBevelBorder());
			eraser.setBorder(BorderFactory.createRaisedBevelBorder());
			pline.setBorder(BorderFactory.createRaisedBevelBorder());
			pcircle.setBorder(BorderFactory.createRaisedBevelBorder());
			prectangle.setBorder(BorderFactory.createRaisedBevelBorder());
			filler.setBorder(BorderFactory.createLoweredBevelBorder());
			clear.setBorder(BorderFactory.createRaisedBevelBorder());
			mp.paintpanel.imgcursor = mp.paintpanel.kit.getImage("src/icon/pencil.png");
			mp.paintpanel.repaint();
		}
		else if(e.getSource() == undo){
			mp.paintpanel.undo();
		}
		else if(e.getSource() == redo) {
			mp.paintpanel.redo();
		}
		else if(e.getSource() == clear)
		{	
			mp.paintpanel.clear();
		}
		
	}

}
