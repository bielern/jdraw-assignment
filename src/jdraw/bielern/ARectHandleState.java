package jdraw.bielern;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

public abstract class ARectHandleState implements HandleState {

	protected int stateID;
	public ARectHandleState(int id){
		stateID = id;
	}
	@Override
	public abstract Point getLocation(Figure figure);

	@Override
	public abstract Cursor getCursor();

	@Override
	public abstract void startInteraction(Figure figure, int x, int y, MouseEvent e,
			DrawView v);

	@Override
	public abstract void dragInteraction(Figure figure, int x, int y, MouseEvent e,
			DrawView v);

	@Override
	public abstract void stopInteraction(Figure figure, int x, int y, MouseEvent e,
			DrawView v);

}
