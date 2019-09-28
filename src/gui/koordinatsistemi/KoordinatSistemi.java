package gui.koordinatsistemi;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Point2D;

import javax.swing.JComponent;

public interface KoordinatSistemi {
	Point donustur(int x,int y,JComponent comp);
	Point donustur(Point p,JComponent comp);
	Point ortala(int width,int height);
	Point ortala(Dimension d);
	
}
