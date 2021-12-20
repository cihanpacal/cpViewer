package cpViewer.gui.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import cpViewer.context.AppContext;

public class DrawingPanel extends JPanel implements Drawable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DrawingPanel() {
		setLayout(new BorderLayout(0, 0));
		setBorder(new BevelBorder(BevelBorder.LOWERED, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY));
		setBackground(Color.BLACK);
		AppContext.getAppContext().setDrawable(this);
	}

	@Override
	public void draw(BufferedImage bufferedImage) {
		setPreferredSize(new Dimension(bufferedImage.getWidth(),bufferedImage.getHeight()));
		repaint();
		revalidate();
		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if(AppContext.getAppContext().getBufferedImage()!=null) {
			int x=AppContext.getAppContext().getBufferedImage().getWidth();
			int y=AppContext.getAppContext().getBufferedImage().getHeight();
			//ortala
			int dx=-x/2;
			int dy=y/2;
			//donustur
			dx=this.getWidth()/2+dx;
			dy=this.getHeight()/2-dy;
			g.drawImage(AppContext.getAppContext().getBufferedImage(),dx,dy,null);
		}
	}

//	@Override
//	public void showContent(Content content) {
//		repaint();
//		revalidate();
//	}
//
//	public void draw(BufferedImage bufferedImage) {
//		setPreferredSize(new Dimension(bufferedImage.getWidth(),bufferedImage.getHeight()));
//		//setBf(jg.getBf());
//		repaint();
//		revalidate();
//		
//	}
	
	

}
