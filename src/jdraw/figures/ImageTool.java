package jdraw.figures;

import java.awt.Point;

import jdraw.framework.DrawContext;

public class ImageTool extends AbstractDrawTool {

	private String FIGURE_NAME;
	public ImageTool(DrawContext context, String name, String figureName, String iconName) {
		super(context, name, iconName);
		FIGURE_NAME = figureName;
	}

	@Override
	protected void mkFigure(Point p1, Point p2) {
		figure = new ImageFigure(p1, IMAGES + FIGURE_NAME);
	}

}
