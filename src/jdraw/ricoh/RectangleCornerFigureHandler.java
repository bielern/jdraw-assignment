package jdraw.ricoh;

import jdraw.framework.Figure;
import jdraw.framework.DrawView;

import java.awt.event.MouseEvent;
import java.awt.Point;

public class RectangleCornerFigureHandler extends AbstractFigureHandler {

	public RectangleCornerFigureHandler(Figure owner) {
		super(owner);
	}
	
	@Override
	public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
		Point pos = getPos();
		Point anchor = getAnchor();
		int dx = x - anchor.x;
		int dy = y - anchor.y;
		setPos(pos.x+dx, pos.y+dy);
	}
	
	@Override
	public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
	}
}
