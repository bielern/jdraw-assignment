/*
 * Copyright (c) 2000-2012 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.figures;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * Represents rectangles in JDraw.
 * 
 * @author Christoph Denzler
 *
 */
@SuppressWarnings("serial")
public class Rect extends AbstractRectangularFigure {
	/**
	 * Use the java.awt.Rectangle in order to save/reuse code.
	 */
	private java.awt.Rectangle rectangle;
	
	/**
	 * Create a new rectangle of the given dimension.
	 * @param x the x-coordinate of the upper left corner of the rectangle
	 * @param y the y-coordinate of the upper left corner of the rectangle
	 * @param w the rectangle�s width
	 * @param h the rectangle�s height
	 */
	public Rect(int x, int y, int w, int h) {
		rectangle = new java.awt.Rectangle(x, y, w, h);
	}
	
	public Rect(Rectangle r){
		rectangle = new Rectangle(r);
		this.setBounds(r);
	}
	
	public Rect(Rect other){
		this(other.getBounds());
	}
	
	/**
	 * Make a Rectangle with width and height of zero.
	 * @param p1
	 */
	public Rect(Point p1){
		rectangle = new java.awt.Rectangle(p1);
	}

	/**
	 * Draw the rectangle to the given graphics context.
	 * @param g the graphics context to use for drawing.
	 */
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(rectangle.x, rectangle.y, 
							 rectangle.width, rectangle.height);
		g.setColor(Color.BLACK);
		g.drawRect(rectangle.x, rectangle.y, 
							 rectangle.width, rectangle.height);
	}

	@Override
	protected void _move(int dx, int dy) {
		rectangle.setLocation(rectangle.x + dx, rectangle.y + dy);
	}

	@Override
	protected void _setBounds(Point origin, Point corner) {
		rectangle.setFrameFromDiagonal(origin, corner);
	}

	@Override
	public Object clone() {
		return new Rect(this);
	}

}
