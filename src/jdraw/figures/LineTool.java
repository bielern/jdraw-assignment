package jdraw.figures;

import java.awt.Point;

import jdraw.framework.DrawContext;

public class LineTool extends AbstractDrawTool {

	public LineTool(DrawContext context) {
		super(context, "Line", "line.png");
	}

	@Override
	protected void mkFigure(Point p1, Point p2) {
		figure = new Line(p1, p2);
	}
}
