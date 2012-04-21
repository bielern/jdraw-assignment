package jdraw.bielern;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

public abstract class ACornerHandleState extends ARectHandleState {

	public ACornerHandleState(int id) {
		super(id);
	}
	
	protected Point anchor;

	@Override
	public abstract Point getLocation(Figure figure);

	@Override
	public abstract Cursor getCursor();

	@Override
	public void startInteraction(Figure figure, int x, int y, MouseEvent e,
			DrawView v) {
		anchor = figure.getHandles().get((stateID + 4) % 8).getLocation();
	}

	@Override
	public void dragInteraction(Figure figure, int x, int y, MouseEvent e,
			DrawView v) {
		figure.setBounds(anchor, new Point(x, y));
	}

	@Override
	public void stopInteraction(Figure figure, int x, int y, MouseEvent e,
			DrawView v) {
		// TODO Auto-generated method stub
	}

}
