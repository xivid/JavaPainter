import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class JavaPainter extends JFrame{

	public JavaPainter() {
		setTitle("画图");
		setMinimumSize(new Dimension(1000, 650));
		mainPanel mp = new mainPanel();
		add(mp);
		
		setVisible(true);
	}
	public static void main(String[] args) {
		JavaPainter painter = new JavaPainter();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//获取屏幕大小
		painter.setLocation((screenSize.width - painter.getBounds().width) / 2, (screenSize.height - painter.getBounds().height) / 2);//获得适应屏幕大小的最佳位置
		painter.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
