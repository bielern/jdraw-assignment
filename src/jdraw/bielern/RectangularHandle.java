package jdraw.bielern;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

public class RectangularHandle extends AbstractHandle {

	public final int N = 0;
	public final int E = 1;
	public final int S = 2;
	public final int W = 3;
	public final int NE = 4;
	public final int SE = 5;
	public final int SW = 6;
	public final int NW = 7;
	
	public RectangularHandle(Figure figure, int stateID) {
		super(figure);
		switch (stateID){
			case N : {
				this.state = new HandleState(){
					@Override
					public Point getLocation(Figure figure) {
						Rectangle bounds = figure.getBounds();
						return new Point(bounds.x + bounds.width / 2, bounds.y);
					}
					@Override
					public Cursor getCursor() {
						return new Cursor(Cursor.N_RESIZE_CURSOR);
					}
				};
			}
			case E : {
				this.state = new HandleState(){
					@Override
					public Point getLocation(Figure figure) {
						Rectangle bounds = figure.getBounds();
						return new Point((int) bounds.getMaxX(), bounds.y + bounds.height / 2);
					}
					@Override
					public Cursor getCursor() {
						return new Cursor(Cursor.E_RESIZE_CURSOR);
					}
				};
			}
			case S : {
				this.state = new HandleState(){
					@Override
					public Point getLocation(Figure figure) {
						Rectangle bounds = figure.getBounds();
						return new Point(bounds.x + bounds.width / 2, (int) bounds.getMaxY());
					}
					@Override
					public Cursor getCursor() {
						return new Cursor(Cursor.S_RESIZE_CURSOR);
					}
				};
			}
			case W : {
				this.state = new HandleState(){
					@Override
					public Point getLocation(Figure figure) {
						Rectangle bounds = figure.getBounds();
						return new Point(bounds.x, bounds.y + bounds.height / 2);
					}
					@Override
					public Cursor getCursor() {
						return new Cursor(Cursor.W_RESIZE_CURSOR);
					}
				};
			}
			case NE : {
				this.state = new HandleState(){
					@Override
					public Point getLocation(Figure figure) {
						Rectangle bounds = figure.getBounds();
						return new Point((int) bounds.getMaxX(), bounds.y);
					}
					@Override
					public Cursor getCursor() {
						return new Cursor(Cursor.NE_RESIZE_CURSOR);
					}
				};
			}
			case SE : {
				this.state = new HandleState(){
					@Override
					public Point getLocation(Figure figure) {
						Rectangle bounds = figure.getBounds();
						return new Point((int) bounds.getMaxX(), (int) bounds.getMaxY());
					}
					@Override
					public Cursor getCursor() {
						return new Cursor(Cursor.SE_RESIZE_CURSOR);
					}
				};
			}
			case SW : {
				this.state = new HandleState(){
					@Override
					public Point getLocation(Figure figure) {
						Rectangle bounds = figure.getBounds();
						return new Point(bounds.x, (int) bounds.getMaxY());
					}
					@Override
					public Cursor getCursor() {
						return new Cursor(Cursor.SW_RESIZE_CURSOR);
					}
				};
			}
			case NW : {
				this.state = new HandleState(){
					@Override
					public Point getLocation(Figure figure) {
						Rectangle bounds = figure.getBounds();
						return new Point(bounds.x, (int) bounds.getMaxY());
					}
					@Override
					public Cursor getCursor() {
						return new Cursor(Cursor.NW_RESIZE_CURSOR);
					}
				};
			}
		}
	}

	@Override
	public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
		// TODO Auto-generated method stub
		
	}
	
}
