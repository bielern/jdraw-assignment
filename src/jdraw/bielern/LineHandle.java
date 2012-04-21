package jdraw.bielern;

import java.awt.Point;

import jdraw.figures.Line;
import jdraw.framework.Figure;

public class LineHandle extends AbstractHandle {

	final public static int START = 0;
	final public static int END   = 1;
	
	public LineHandle(Figure figure, int id) {
		super(figure);
		if (id == START){
			state = new ALineHandleState(id){
				@Override
				public Point getLocation(Figure figure) {
					Line line = (Line) figure;
					return line.start();
				}
			};
		} else if (id == END) {
			state = new ALineHandleState(id){
				@Override
				public Point getLocation(Figure figure) {
					Line line = (Line) figure;
					return line.end();
				}
			};
		}
	}

}
