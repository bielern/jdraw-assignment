package jdraw.ricoh;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

public abstract class AbstractFigureHandler implements FigureHandle {
	
	private Figure  	owner;
	private Point   	pos;
	private Point		anchor;
	private static int	size = 5;
	private java.awt.Rectangle rectangle;

	public AbstractFigureHandler(Figure owner) {
		super();
		this.owner = owner;
		rectangle = new java.awt.Rectangle(pos.x, pos.y, size, size);
	}

	@Override
	public Figure getOwner() {
		return owner;
	}

	@Override
	public Point getLocation() {
		return pos;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(pos.x, pos.y, size, size);
		g.setColor(Color.BLACK);
		g.drawRect(pos.x, pos.y, size, size);
	}

	@Override
	public Cursor getCursor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(int x, int y) {
		return rectangle.contains(x, y);
	}

	@Override
	public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
		anchor = new Point(x, y);
	}

	@Override
	public abstract void dragInteraction(int x, int y, MouseEvent e, DrawView v);
	
	@Override
	public abstract void stopInteraction(int x, int y, MouseEvent e, DrawView v);

	public void setPos(int x, int y) {
		pos.setLocation(x, y);
		rectangle.setLocation(x, y);
	}
	public Point getPos() {
		return pos;
	}
	public void setAnchor(int x, int y) {
		anchor.setLocation(x, y);
	}
	public Point getAnchor() {
		return anchor;
	}
}