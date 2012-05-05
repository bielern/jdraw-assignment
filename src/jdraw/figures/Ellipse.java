package jdraw.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

@SuppressWarnings("serial")
public class Ellipse extends AbstractRectangularFigure {

	private Ellipse2D.Double ellipse;
	
	public Ellipse (double x, double y, double w, double h){
		ellipse = new Ellipse2D.Double(x, y, w, h);
	}
	
	public Ellipse (Rectangle r){
		ellipse = new Ellipse2D.Double(r.x, r.y, r.width, r.height);
		this.setBounds(r);
	}
	
	public Ellipse (Ellipse e){
		this(e.getBounds());
	}
	
	public Ellipse (Point p1){
		ellipse = new Ellipse2D.Double(p1.x, p1.y, 0, 0);
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.red);
		g.fillOval((int) ellipse.x, (int) ellipse.y, (int) ellipse.width, (int) ellipse.height);
		
		g.setColor(Color.black);
		g.drawOval((int) ellipse.x, (int) ellipse.y, (int) ellipse.width, (int) ellipse.height);
	}

	@Override
	public boolean contains(int x, int y) {
		return ellipse.contains(x, y);
	}

	@Override
	protected void _move(int dx, int dy) {
		ellipse.x += dx;
		ellipse.y += dy;
	}

	@Override
	protected void _setBounds(Point origin, Point corner) {
		ellipse.setFrameFromDiagonal(origin, corner);
	}

	@Override
	public Object clone() {
		return new Ellipse(this);
	}

}
