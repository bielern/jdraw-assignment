package jdraw.bielern;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

public abstract class ASideHandleState extends ARectHandleState {

	public ASideHandleState(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	protected Point cornerOne, cornerTwo;

	@Override
	public abstract Point getLocation(Figure figure);

	@Override
	public abstract Cursor getCursor();

	@Override
	public void startInteraction(Figure figure, int x, int y, MouseEvent e,
			DrawView v) {
		cornerOne = figure.getHandles().get((stateID + 4 + 1) % 8).getLocation();
		cornerTwo = figure.getHandles().get((stateID + 4 - 1) % 8).getLocation();
	}

	@Override
	public void dragInteraction(Figure figure, int x, int y, MouseEvent e,
			DrawView v) {
		if (stateID == RectangularHandle.N || stateID == RectangularHandle.S){
			figure.setBounds(cornerOne, new Point(cornerTwo.x, y));
		} else {
			figure.setBounds(cornerOne, new Point(x, cornerTwo.y));
		}
	}

	@Override
	public void stopInteraction(Figure figure, int x, int y, MouseEvent e,
			DrawView v) {
		// TODO Auto-generated method stub

	}

}
