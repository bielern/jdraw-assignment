package jdraw.figures;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import jdraw.framework.DrawView;

public abstract class AbstractDrawTool implements DrawTool {

	/** 
	 * the image resource path. 
	 */
	protected static final String IMAGES = "/images/";
	
	protected String NAME = null;
	protected String ICON_NAME = null;
	
	protected AbstractFigure figure = null;
	
	protected abstract void mkFigure(Point p1, Point p2);

	/**
	 * The context we use for drawing.
	 */
	protected DrawContext context;

	/**
	 * The context's view. This variable can be used as a shortcut, i.e.
	 * instead of calling context.getView().
	 */
	protected DrawView view;

	/**
	 * Temporary variable.
	 * During rectangle creation this variable refers to the point the
	 * mouse was first pressed.
	 */
	protected Point anchor = null;
	
	protected AbstractDrawTool(DrawContext context, String name, String iconName){
		this.context = context;
		this.view = context.getView();
		this.NAME = name;
		this.ICON_NAME = iconName;
	}
	
	@Override
	public void activate(){
		this.context.showStatusText(NAME + " Mode");
	}
	

	@Override
	public void deactivate() {
		this.context.showStatusText("");
	}

	@Override
	public void mouseDown(int x, int y, MouseEvent e) {
		if (figure != null) {
			throw new IllegalStateException();
		}
		anchor = new Point(x, y);
		mkFigure(anchor, anchor);
		view.getModel().addFigure(figure);
	}

	@Override
	public void mouseDrag(int x, int y, MouseEvent e) {
		figure.setBounds(anchor, new Point(x, y));
		java.awt.Rectangle r = figure.getBounds();
		this.context.showStatusText("w: " + r.width + ", h: " + r.height);
	}

	@Override
	public void mouseUp(int x, int y, MouseEvent e) {
		figure.setBounds(anchor, new Point(x, y));
		figure = null;
		anchor = null;
		this.context.showStatusText("Rectangle Mode");
		
	}

	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
	}

	@Override
	public Icon getIcon() {
		return new ImageIcon(getClass().getResource(IMAGES + ICON_NAME));
	}

	@Override
	public String getName() {
		return NAME;
	}

}
