package jdraw.bielern;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.tools.SetBoundsCommand;

public abstract class ASideHandleState extends ARectHandleState {

	public ASideHandleState(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	protected Point cornerOne, cornerTwo;
	protected Point oldSide;

	@Override
	public abstract Point getLocation(Figure figure);

	@Override
	public abstract Cursor getCursor();

	@Override
	public void startInteraction(Figure figure, int x, int y, MouseEvent e,
			DrawView v) {
		cornerOne = figure.getHandles().get((stateID + 4 + 1) % 8).getLocation();
		cornerTwo = figure.getHandles().get((stateID + 4 - 1) % 8).getLocation();
		oldSide = new Point(x, y);
	}

	@Override
	public void dragInteraction(Figure figure, int x, int y, MouseEvent e,
			DrawView v) {
		Point newCorner;
		Point oldCorner;
		if (stateID == RectangularHandle.N || stateID == RectangularHandle.S){
			newCorner = new Point(cornerTwo.x, y);
			oldCorner = new Point(cornerTwo.x, oldSide.y);
		} else {
			newCorner = new Point(x, cornerTwo.y);
			oldCorner = new Point(oldSide.x, cornerTwo.y);
		}
		figure.setBounds(cornerOne, newCorner);
		v.getModel().getDrawCommandHandler().addCommand(new SetBoundsCommand(cornerOne, oldCorner, cornerOne, newCorner, figure));
	}

	@Override
	public void stopInteraction(Figure figure, int x, int y, MouseEvent e,
			DrawView v) {
		// TODO Auto-generated method stub

	}
	
	public static class NState extends ASideHandleState {
		public NState(int id) {
			super(id);
		}
		@Override
		public Point getLocation(Figure figure) {
			Rectangle bounds = figure.getBounds();
			return new Point(bounds.x + bounds.width / 2, bounds.y);
		}
		@Override
		public Cursor getCursor() {
			return new Cursor(Cursor.N_RESIZE_CURSOR);
		}
	}
	
	public static class WState extends ASideHandleState {
		public WState(int id) {
			super(id);
		}
		@Override
		public Point getLocation(Figure figure) {
			Rectangle bounds = figure.getBounds();
			return new Point(bounds.x, bounds.y + bounds.height / 2);
		}
		@Override
		public Cursor getCursor() {
			return new Cursor(Cursor.W_RESIZE_CURSOR);
		}
	}
	
	public static class SState extends ASideHandleState {

		public SState(int id) {
			super(id);
		}
		@Override
		public Point getLocation(Figure figure) {
			Rectangle bounds = figure.getBounds();
			return new Point(bounds.x + bounds.width / 2, (int) bounds.getMaxY());
		}
		@Override
		public Cursor getCursor() {
			return new Cursor(Cursor.S_RESIZE_CURSOR);
		}
	}
	
	public static class EState extends ASideHandleState {

		public EState(int id) {
			super(id);
		}
		@Override
		public Point getLocation(Figure figure) {
			Rectangle bounds = figure.getBounds();
			return new Point((int) bounds.getMaxX(), bounds.y + bounds.height / 2);
		}
		@Override
		public Cursor getCursor() {
			return new Cursor(Cursor.E_RESIZE_CURSOR);
		}
	}

}
