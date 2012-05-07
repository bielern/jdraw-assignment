package jdraw.figures;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

@SuppressWarnings("serial")
public class DecoratorFigure extends AbstractFigure {
	
	private Figure fig;

	public DecoratorFigure(Figure fig) {
		this.fig = fig;
	}

	@Override
	public void draw(Graphics g) {
		fig.draw(g);
	}

	@Override
	public void move(int dx, int dy) {
		fig.move(dx, dy);
	}

	@Override
	public boolean contains(int x, int y) {
		return fig.contains(x, y);
	}

	@Override
	public void setBounds(Point origin, Point corner) {
		fig.setBounds(origin, corner);
	}

	@Override
	public Rectangle getBounds() {
		return fig.getBounds();
	}

	@Override
	public Object clone() {
		return new DecoratorFigure((Figure)(fig.clone()));
	}
		
	@Override
	public List<FigureHandle> getHandles(){
		return fig.getHandles();
	}

	public Figure getFig() {
		return fig;
	}

}
