package gui.koordinatsistemi;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Point2D;

import javax.swing.JComponent;

public class Merkez implements KoordinatSistemi {

	@Override
	public Point donustur(int x, int y, JComponent comp) {
		int dx=comp.getWidth()/2+x;
		int dy=comp.getHeight()/2-y;
		return new Point(dx,dy);
	}

	@Override
	public Point donustur(Point p, JComponent comp) {
		int dx=comp.getWidth()/2+p.x;
		int dy=comp.getHeight()/2-p.y;
		return new Point(dx,dy);
	}

	@Override
	public Point ortala(int width, int height) {
		int dx=-width/2;
		int dy=height/2;
		return new Point(dx,dy);
	}

	@Override
	public Point ortala(Dimension d) {
		// TODO Auto-generated method stub
		int dx=-d.width/2;
		int dy=d.height/2;
		return new Point(dx,dy);
	}

	

	
	
	
}
