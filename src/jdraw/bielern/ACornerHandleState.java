package jdraw.bielern;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
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
	
	static public class NWState extends ACornerHandleState {

		public NWState(int id) {
			super(id);
		}
		@Override
		public Point getLocation(Figure figure) {
			Rectangle bounds = figure.getBounds();
			return new Point(bounds.x, bounds.y);
		}
		@Override
		public Cursor getCursor() {
			return new Cursor(Cursor.NW_RESIZE_CURSOR);
		}
	}
	
	static public class NEState extends ACornerHandleState {
		public NEState(int id) {
			super(id);
		}
		@Override
		public Point getLocation(Figure figure) {
			Rectangle bounds = figure.getBounds();
			return new Point((int) bounds.getMaxX(), bounds.y);
		}
		@Override
		public Cursor getCursor() {
			return new Cursor(Cursor.NE_RESIZE_CURSOR);
		}
	}
	
	static public class SEState extends ACornerHandleState {
		public SEState(int id) {
			super(id);
		}
		@Override
		public Point getLocation(Figure figure) {
			Rectangle bounds = figure.getBounds();
			return new Point((int) bounds.getMaxX(), (int) bounds.getMaxY());
		}
		@Override
		public Cursor getCursor() {
			return new Cursor(Cursor.SE_RESIZE_CURSOR);
		}
	}
	
	static public class SWState extends ACornerHandleState {

		public SWState(int id) {
			super(id);
		}
		@Override
		public Point getLocation(Figure figure) {
			Rectangle bounds = figure.getBounds();
			return new Point(bounds.x, (int) bounds.getMaxY());
		}
		@Override
		public Cursor getCursor() {
			return new Cursor(Cursor.SW_RESIZE_CURSOR);
		}
	}

}
