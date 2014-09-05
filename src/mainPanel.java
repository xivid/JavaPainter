import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class mainPanel extends JPanel {
	public JPanel controlpanel;
	public JToolBar statusbar;
	public filePanel filepanel;
	public paintPanel paintpanel;
	public thickPanel thickpanel;
	public toolChoosePanel toolchoosepanel;
	public colorSelectPanel colorselectpanel;
	public JLabel status;
	public JFrame mainwindow;
	public mainPanel(JFrame mw) {
		mainwindow = mw;
		this.setLayout(new BorderLayout());

		controlpanel = new JPanel();
		paintpanel = new paintPanel(this);
		statusbar = new JToolBar();
		statusbar.setFloatable(false);
		
		filepanel = new filePanel(this);
		colorselectpanel = new colorSelectPanel(this);
		thickpanel = new thickPanel(this);
		toolchoosepanel = new toolChoosePanel(this);
		status = new JLabel("Java画图板    作者：HIT-CS 1130310217 杨志飞");
		status.setFont(new Font("Microsoft Yahei", Font.PLAIN, 14));
		
		controlpanel.setPreferredSize(new Dimension(120, 350));
		controlpanel.add(filepanel);
		controlpanel.add(thickpanel);
		controlpanel.add(toolchoosepanel);
		controlpanel.add(colorselectpanel);
		statusbar.add(status);
		
		this.add(controlpanel, BorderLayout.WEST);
		this.add(paintpanel, BorderLayout.CENTER); //添加一个 showpanel
		this.add(statusbar, BorderLayout.SOUTH);
	}
}
