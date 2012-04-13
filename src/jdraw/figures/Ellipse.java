package jdraw.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

@SuppressWarnings("serial")
public class Ellipse extends AbstractFigure {

	private Ellipse2D.Double ellipse;
	
	public Ellipse (double x, double y, double w, double h){
		ellipse = new Ellipse2D.Double(x, y, w, h);
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.red);
		g.fillOval((int) ellipse.x, (int) ellipse.y, (int) ellipse.width, (int) ellipse.height);
		
		g.setColor(Color.black);
		g.drawOval((int) ellipse.x, (int) ellipse.y, (int) ellipse.width, (int) ellipse.height);
	}

	@Override
	public void move(int dx, int dy) {
		ellipse.x += dx;
		ellipse.y += dy;
		notifyListeners();
	}

	@Override
	public boolean contains(int x, int y) {
		return ellipse.contains(x, y);
	}

	@Override
	public void setBounds(Point origin, Point corner) {
		ellipse.setFrameFromDiagonal(origin, corner);
	}

	@Override
	public Rectangle getBounds() {
		return ellipse.getBounds();
	}

}
