package jdraw.figures;

import java.awt.Point;

import jdraw.framework.DrawContext;

public class EllipseTool extends AbstractDrawTool {

	public EllipseTool(DrawContext context) {
		super(context, "Ellipse", "oval.png");
	}

	@Override
	protected void mkFigure(Point p1, Point p2) {
		figure = new Ellipse(p1);
	}

}
