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
	
	Figure  	owner;
	Point   	pos;
	static int	size = 5;

	public AbstractFigureHandler(Figure owner) {
		super();
		this.owner = owner;
	}

	@Override
	public Figure getOwner() {
		return owner;
	}

	@Override
	public Point getLocation() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
		// TODO Auto-generated method stub

	}

}
