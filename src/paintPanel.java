
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.*;
import java.util.*;

import javax.swing.*;


@SuppressWarnings("serial")
public class paintPanel extends JPanel {
	public ArrayList<myPolygon> List;
	public Stack<myElement> undoStack;
	public ArrayList<Color> colorList;
	public ArrayList<Float> thickList;
	public Color paintColor = Color.black;
	public myPolygon poly, x;
	public int beginX, beginY;
	public float thick = 1f;
	public Toolkit kit;
	public Image imgcursor;
	public BufferedImage img;
	public mainPanel mp;
	public Point p;
	public boolean hasimage;
	public class myPolygon extends Polygon {
		public boolean isfill;
	}
	
	public class myElement {
		myPolygon polygon;
		Color color;
		Float thick;
		
		public myElement(myPolygon p, Color c, Float f){
			polygon = p;
			color = c;
			thick = f;
		}
	}
	public paintPanel(mainPanel mp) {
		this.mp = mp;
		undoStack = new Stack<myElement>();
		List = new ArrayList<myPolygon>();
		colorList = new ArrayList<Color>();
		thickList = new ArrayList<Float>();
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(600, 410));
		this.addMouseListener(new paintListener());
		this.addMouseMotionListener(new paintListener());
		kit = Toolkit.getDefaultToolkit();
		imgcursor = kit.getImage("src/icon/pencil.png");
	}

	boolean justcleared;
	public void clear() {
		hasimage = false;
		undoStack.clear();
		for(int i = 0; i < List.size(); i++)
			undoStack.add(new myElement(List.get(i), colorList.get(i), thickList.get(i)));
		List = new ArrayList<myPolygon>();
		colorList = new ArrayList<Color>();
		thickList = new ArrayList<Float>();
		justcleared = true;
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		if(hasimage) g2d.drawImage(img, 0, 0, null);
		for (int i = 0; i < List.size(); i++) {
			g.setColor(colorList.get(i));
			g2d.setStroke(new BasicStroke(thickList.get(i), BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND));
			x = List.get(i);
			if(x.isfill)
				g2d.fillPolygon(x);
			else
				g2d.drawPolyline(x.xpoints, x.ypoints, x.npoints);
		}
		
		Cursor cursor = kit.createCustomCursor(imgcursor, new Point(8, 23),
				"custom cursor"); //TODO: 根据tool调整热点
		setCursor(cursor);
	}

	public void undo(){
		if(justcleared) {
			justcleared = false;
			if(img != null) {
				hasimage = true;
			}
			while(!undoStack.empty()) {
				myElement x = undoStack.pop();
				List.add(x.polygon);
				colorList.add(x.color);
				thickList.add(x.thick);
			}
			repaint();
		}
		else if(List.size() > 0){
			int id = List.size() - 1;
			myElement x = new myElement(List.get(id), colorList.get(id), thickList.get(id));
			undoStack.push(x);
			List.remove(id);
			colorList.remove(id);
			thickList.remove(id);
			repaint();
		}
		
	}
	
	public void redo() {
		if(!justcleared && !undoStack.empty()){
			myElement x = undoStack.pop();
			List.add(x.polygon);
			colorList.add(x.color);
			thickList.add(x.thick);
			repaint();
		}
	}
	public class paintListener implements MouseListener, MouseMotionListener {

		public void mouseDragged(MouseEvent e) {
			int currentX = e.getX();
			int currentY = e.getY();
			if(mp.toolchoosepanel.chosentool.equals("eraser") 
					|| mp.toolchoosepanel.chosentool.equals("pencil")
					|| mp.toolchoosepanel.chosentool.equals("filler")){
				poly.addPoint(currentX, currentY);
				repaint();
			} else if (mp.toolchoosepanel.chosentool.equals("pline")) {
				poly.npoints = 2;
				poly.xpoints[1] = currentX;
				poly.ypoints[1] = currentY;
				repaint();
			} else if (mp.toolchoosepanel.chosentool.equals("pcircle")) {
				poly.npoints = 2;
				poly.xpoints[1] = currentX;
				poly.ypoints[1] = currentY;
				repaint();
			} else if (mp.toolchoosepanel.chosentool.equals("prectangle")) {
				if(poly.npoints < 5) {
					poly.addPoint(currentX, poly.ypoints[0]);
					poly.addPoint(currentX, currentY);
					poly.addPoint(poly.xpoints[0], currentY);
					poly.addPoint(poly.xpoints[0], poly.ypoints[0]);
				} else {
					poly.xpoints[1] = currentX; poly.ypoints[1] = poly.ypoints[0];
					poly.xpoints[2] = currentX; poly.ypoints[2] = currentY;
					poly.xpoints[3] = poly.xpoints[0]; poly.ypoints[3] = currentY;
					poly.xpoints[4] = poly.xpoints[0]; poly.ypoints[4] = poly.ypoints[0];
				}
				repaint();
			}
		}

		public void mousePressed(MouseEvent e) {
			justcleared = false;
			beginX = e.getX();
			beginY = e.getY();
			poly = new myPolygon();
			poly.addPoint(beginX, beginY);
			List.add(poly);
			if (mp.toolchoosepanel.chosentool.equals("filler"))
				poly.isfill = true;
			if (mp.toolchoosepanel.chosentool.equals("eraser"))
				colorList.add(Color.white);
			else
				colorList.add(paintColor);
			thickList.add(thick);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if(mp.toolchoosepanel.chosentool.equals("eraser") || mp.toolchoosepanel.chosentool.equals("pencil")){
				int currentX = e.getX();
				int currentY = e.getY();
				poly.addPoint(currentX, currentY);
				repaint();
			}
		}
//未经测试的填充算法，基于深度优先搜索和屏幕像素捕捉
//		public Color getColor(int x, int y){
//			Robot robot = null;
//			try {robot = new Robot();}
//			catch(Exception ex) { System.out.println(ex.getMessage());}
//			return robot.getPixelColor(x, y);
//		}
//		
//		boolean[][] vis = new boolean[4000][3000];
//		public void makeBoundPoly(myPolygon poly, Color color, int x0, int y0, int xbound, int ybound, int width, int height){
//			vis[x0][y0] = true;
//			if(!getColor(x0, y0).equals(color))
//				poly.addPoint(x0, y0);
//			else {
//				if(!vis[x0+1][y0] && x0+1 <= xbound+width) makeBoundPoly(poly, color, x0+1, y0, xbound, ybound, width, height);
//				if(!vis[x0-1][y0] && x0-1 >= xbound) makeBoundPoly(poly, color, x0-1, y0, xbound, ybound, width, height);
//				if(!vis[x0][y0+1] && y0+1 <= ybound+height) makeBoundPoly(poly, color, x0, y0+1, xbound, ybound, width, height);
//				if(!vis[x0][y0-1] && y0-1 <= ybound+height) makeBoundPoly(poly, color, x0, y0-1, xbound, ybound, width, height);
//			}
//			
//		}
		
		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		public void mouseReleased(MouseEvent e) {
			if(mp.toolchoosepanel.chosentool.equals("pcircle")) {
				myPolygon info = List.get(List.size() - 1), circle = new myPolygon();
				int x0 = info.xpoints[0], y0 = info.ypoints[0], //圆心坐标
						x1 = info.xpoints[1], y1 = info.ypoints[1]; //圆上一个点的坐标
				int radius = (int)Math.sqrt((x1-x0)*(x1-x0) + (y1-y0)*(y1-y0));
				List.remove(List.size() - 1);
				for(int x = x0-radius; x <= x0+radius; x++)
					circle.addPoint(x, y0 + (int)Math.sqrt(radius*radius - (x-x0)*(x-x0)));
				for(int x = x0+radius; x >= x0-radius; x--)
					circle.addPoint(x, y0 - (int)Math.sqrt(radius*radius - (x-x0)*(x-x0)));
				circle.addPoint(circle.xpoints[0], circle.ypoints[0]);
				List.add(circle);
				repaint();
			}
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

}
