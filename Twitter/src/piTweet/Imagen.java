package piTweet;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Imagen extends javax.swing.JPanel {
	private String ruta;
	
	public Imagen(String ruta) {
		this.setSize(300, 400);
		this.ruta=ruta;
	}


	public void paint(Graphics grafico) {
		Dimension height = getSize();
		ImageIcon Img = new ImageIcon(getClass().getResource(ruta));
		grafico.drawImage(Img.getImage(), 0, 0, height.width, height.height, null);
		setOpaque(false);
		super.paintComponent(grafico);
	}
}