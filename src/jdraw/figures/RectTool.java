/*
 * Copyright (c) 2000-2012 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.figures;

import java.awt.Point;

import jdraw.framework.DrawContext;

/**
 * This tool defines a mode for drawing rectangles.
 *
 * @see jdraw.framework.Figure
 *
 * @author  Christoph Denzler
 * @version 2.1, 27.09.07
 */
public class RectTool extends AbstractDrawTool {
	public RectTool(DrawContext context){
		super(context, "Rectangle", "rectangle.png");
	}

	@Override
	protected void mkFigure(Point p1, Point p2) {
		figure = new Rect(p1);
	}

}
