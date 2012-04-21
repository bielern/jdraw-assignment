package jdraw.bielern;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

public abstract class AbstractHandle implements FigureHandle {

	protected Figure figure;
	protected HandleState state;
	
	static final private int SIZE = 6;
	static final private int HALF_SIZE = SIZE / 2;
	
	public AbstractHandle(Figure figure){
		this.figure = figure;
	}
	
	@Override
	public Figure getOwner() {
		return figure;
	}

	@Override
	public Point getLocation() {
		//return state.getLocation(figure);
		Point p = state.getLocation(figure);
		return p;
	}

	@Override
	public void draw(Graphics g) {
		Point center = getLocation();
		int x = center.x - HALF_SIZE;
		int y = center.y - HALF_SIZE;
		g.setColor(Color.WHITE);
		g.fillRect(x, y, SIZE, SIZE);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, SIZE, SIZE);
	}

	@Override
	public Cursor getCursor() {
		return state.getCursor();
	}

	@Override
	public boolean contains(int x, int y) {
		Point loc = this.getLocation();
		return Math.abs(loc.x - x) <= HALF_SIZE 
			&& Math.abs(loc.y - y) <= HALF_SIZE;
	}

	@Override
	public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
		//anchor = figure.getHandles().get((stateID + 4) % 8).getLocation();
		state.startInteraction(figure, x, y, e, v);
	}

	@Override
	public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {		
		//figure.setBounds(anchor, new Point(x, y));
		state.dragInteraction(figure, x, y, e, v);
	}

	@Override
	public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
		state.stopInteraction(figure, x, y, e, v);
	}
}
