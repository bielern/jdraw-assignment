package jdraw.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import jdraw.framework.Figure;

@SuppressWarnings("serial")
public class BorderDecorator extends DecoratorFigure {
	
	static final private  double padding = 5;

	public BorderDecorator(Figure fig) {
		super(fig);
	}

	@Override
	public void draw(Graphics g) {
		Rectangle bounds = getBounds();
		int x = (int) bounds.getX();
		int y = (int) bounds.getY();
		int w = (int) bounds.getWidth();
		int h = (int) bounds.getHeight();
		g.setColor(Color.white);
		g.draw3DRect(x, y, w, h, true);
		super.draw(g);
	}

	/*
	@Override
	public void move(int dx, int dy) {
		super.move(dx, dy);
	}

	@Override
	public boolean contains(int x, int y) {
		return super.contains(x, y);
	}

	@Override
	public void setBounds(Point origin, Point corner) {
		super.setBounds(origin, corner);
	}
	*/

	@Override
	public Rectangle getBounds() {
		Rectangle fb  = super.getBounds();
		int x = (int) (fb.getX() - padding);
		int y = (int) (fb.getY() - padding);
		int w = (int) (fb.getWidth() + 2*padding);
		int h = (int) (fb.getHeight() + 2*padding);
		//fb.setFrame(x, y, w, h);
		return new Rectangle(x, y, w, h);
	}

	/*
	@Override
	public Object clone() {
		return super.clone();
	}
	*/

}
