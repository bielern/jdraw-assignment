package jdraw.bielern;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.tools.SetBoundsCommand;

public abstract class ALineHandleState implements HandleState {

	protected int state;
	Point anchor;
	Point oldCorner;
	
	ALineHandleState(int id){
		state = id;
	}
	@Override
	public abstract Point getLocation(Figure figure);

	@Override
	public Cursor getCursor() {
		return new Cursor(Cursor.MOVE_CURSOR);
	}

	@Override
	public void startInteraction(Figure figure, int x, int y, MouseEvent e,
			DrawView v) {
		anchor = figure.getHandles().get((state + 1) % 2).getLocation();
		oldCorner = new Point(x, y);
	}

	@Override
	public void dragInteraction(Figure figure, int x, int y, MouseEvent e,
			DrawView v) {
		Point newCorner = new Point(x, y);
		figure.setBounds(anchor, newCorner);
		v.getModel().getDrawCommandHandler().addCommand(new SetBoundsCommand(anchor, oldCorner, anchor, newCorner, figure));
	}

	@Override
	public void stopInteraction(Figure figure, int x, int y, MouseEvent e,
			DrawView v) {
		// TODO Auto-generated method stub
		
	}
}
