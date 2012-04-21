package jdraw.bielern;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import jdraw.framework.Figure;

public class RectangularHandle extends AbstractHandle {

	public final static int N  = 0;
	public final static int NE = 1;
	public final static int E  = 2;
	public final static int SE = 3;
	public final static int S  = 4;
	public final static int SW = 5;
	public final static int W  = 6;
	public final static int NW = 7;
		
	public RectangularHandle(Figure figure, int stateID) {
		super(figure);
		switch (stateID){
			case N : {
				this.state = new ASideHandleState(stateID){
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
				break;
			}
			case E : {
				this.state = new ASideHandleState(stateID){
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
				break;
			}
			case S : {
				this.state = new ASideHandleState(stateID){
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
				break;
			}
			case W : {
				this.state = new ASideHandleState(stateID){
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
				break;
			}
			case NE : {
				this.state = new ACornerHandleState(stateID){
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
				break;
			}
			case SE : {
				this.state = new ACornerHandleState(stateID){
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
				break;
			}
			case SW : {
				this.state = new ACornerHandleState(stateID){
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
				break;
			}
			case NW : {
				this.state = new ACornerHandleState(stateID){
					@Override
					public Point getLocation(Figure figure) {
						Rectangle bounds = figure.getBounds();
						return new Point(bounds.x, bounds.y);
					}
					@Override
					public Cursor getCursor() {
						return new Cursor(Cursor.NW_RESIZE_CURSOR);
					}
				};
				break;
			}
		}
	}
}
