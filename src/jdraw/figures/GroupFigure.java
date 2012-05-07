package jdraw.figures;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import jdraw.framework.Figure;
import jdraw.framework.FigureGroup;

@SuppressWarnings("serial")
public class GroupFigure extends AbstractFigure implements FigureGroup {

	private java.util.LinkedList<Figure> figureParts;

	public GroupFigure() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Iterable<Figure> getFigureParts() {
		return figureParts;
	}

	@Override
	public void draw(Graphics g) {
		for(Figure f : figureParts) {
			f.draw(g);
		}

	}

	@Override
	public void move(int dx, int dy) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean contains(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setBounds(Point origin, Point corner) {
		// TODO Auto-generated method stub

	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}
