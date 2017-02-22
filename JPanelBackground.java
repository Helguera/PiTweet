package piTweet;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class JPanelBackground extends JPanel {
	private Image background;
	private double width;
	private double height;
	private int panel_height;
	private int panel_width;

	public void paintComponent(Graphics g) {
		if (this.background != null) {
			g.drawImage(this.background, 0, 0,(int) width, (int)height, null);
		}
		super.paintComponent(g);
	}


	public void setBackground(String imagePath, double ancho, double alto) {
		panel_height=this.getSize().height;
		panel_width=this.getSize().width;
		double proporcion = ancho/alto;
		if(proporcion>1){
			width=panel_width;
			height=panel_width*alto/ancho;
		}if(proporcion<1){
			width=panel_height*ancho/alto;
			height=panel_height;
		}if(proporcion==1){
			width=panel_height;
			height=panel_height;
		}
		this.setOpaque(false);
		this.background = new ImageIcon(imagePath).getImage();
		repaint();
	}

}