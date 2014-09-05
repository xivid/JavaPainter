import javax.swing.*;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileFilter;

import java.awt.image.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;

@SuppressWarnings("serial")
public class filePanel extends JPanel implements ActionListener{
	mainPanel mp;
	JButton fileopen, filesave, filesaveas, filenew;
	public filePanel(mainPanel mp){
		JLabel title = new JLabel("     文件     ");
		this.mp = mp;
		filenew = new JButton(new ImageIcon("src/icon/new.png"));
		fileopen = new JButton(new ImageIcon("src/icon/open.png"));
		filesave = new JButton(new ImageIcon("src/icon/save.png"));
		filesaveas = new JButton(new ImageIcon("src/icon/saveas.png"));
		
		fileopen.setPreferredSize(new Dimension(45,30));
		filesave.setPreferredSize(new Dimension(45,30));
		filesaveas.setPreferredSize(new Dimension(45,30));
		filenew.setPreferredSize(new Dimension(45,30));
		
		fileopen.addActionListener(this);
		filesave.addActionListener(this);
		filesaveas.addActionListener(this);
		filenew.addActionListener(this);
		
		this.setPreferredSize(new Dimension(100, 100));
		this.add(title);
		this.add(filenew);
		this.add(fileopen);
		this.add(filesave);
		this.add(filesaveas);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == filenew){
			mp.paintpanel.hasimage = false;
			mp.paintpanel.img = null;
			mp.paintpanel.undoStack.clear();
			mp.paintpanel.clear();
		}
		else if(e.getSource() == fileopen){
			JFileChooser jc = new JFileChooser();
            jc.setFileFilter(new FileFilter() {
                public boolean accept(File f) { // 设定可用的文件的后缀名
                    if (f.getName().endsWith(".jpg") || f.getName().endsWith(".png") || f.isDirectory()|| f.getName().endsWith(".gif")) {
                        return true;
                    }
                    return false;
                }

                public String getDescription() {
                    return "图片(*.jpg,*.png,*.gif)";
                }
            });
            int returnValue = jc.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jc.getSelectedFile();
                if (selectedFile != null) {
                    String fileString = selectedFile.getAbsolutePath();
                    try {
                        BufferedImage newImage = ImageIO.read(new File(fileString));
                        mp.paintpanel.hasimage = true;
                        mp.paintpanel.img = newImage;
                        mp.paintpanel.repaint();

                    } catch (IOException ex) {
                        System.out.println(ex);
                    }

                }
            }
		}
		else if(e.getSource() == filesaveas){
			BufferedImage image = new BufferedImage(mp.paintpanel.getWidth(),mp.paintpanel.getHeight(), BufferedImage.TYPE_INT_RGB);   
			Graphics2D g2 = image.createGraphics();   
			mp.paintpanel.paint(g2);
			JFileChooser FileCh = new JFileChooser();
			
			FileCh.setFileFilter(new FileFilter() {
                public boolean accept(File f) {
                    if (f.isDirectory())
                        return true;
                    return false;
                }

                public String getDescription() {
                    return "PNG图片(*.png)";
                }
                
                public String getSuffix() {
                	return ".png";
                }
            });
			
			FileCh.setFileFilter(new FileFilter() {
                public boolean accept(File f) {
                    if (f.isDirectory())
                        return true;
                    return false;
                }

                public String getDescription() {
                    return "GIF图片(*.gif)";
                }
                
                public String getSuffix() {
                	return ".gif";
                }
            });
			
			FileCh.setFileFilter(new FileFilter() {
                public boolean accept(File f) { 
                    if (f.isDirectory())
                        return true;
                    return false;
                }

                public String getDescription() {
                    return "JPEG图片（*.jpg）";
                }
                
                public String getSuffix() {
                	return ".jpg";
                }
            });
			int returnValue = FileCh.showSaveDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = FileCh.getSelectedFile();
				if(selectedFile != null){
					String fileString = selectedFile.getAbsolutePath();
					String fileType = "jpg";
					if(FileCh.getFileFilter() == FileCh.getChoosableFileFilters()[1])
						fileType = "png";
					else if(FileCh.getFileFilter() == FileCh.getChoosableFileFilters()[2])
						fileType = "gif";
					
					System.out.println(fileString);
					System.out.println(fileType);
					try {
						ImageIO.write(image, fileType, new java.io.File(fileString+"."+fileType));
					} catch (IOException e1) {
						
					}
				}
            }
		}
	}
}