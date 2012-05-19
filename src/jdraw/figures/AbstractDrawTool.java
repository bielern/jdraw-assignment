package jdraw.figures;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import jdraw.framework.DrawView;
import jdraw.tools.AddFigureCommand;

public abstract class AbstractDrawTool implements DrawTool {

	/** 
	 * the image resource path. 
	 */
	protected static final String IMAGES = "/images/";
	/**
	 * The name of the tool.
	 */
	protected String NAME = null;
	/**
	 * The name of the icon in the images folder.
	 */
	protected String ICON_NAME = null;
	
	/**
	 * The figure, the tool draws
	 */
	protected AbstractFigure figure = null;

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
	
	/**
	 * Activate the draw tool
	 */
	@Override
	public void activate(){
		this.context.showStatusText(NAME + " Mode");
	}

	/**
	 * Deactivate the draw tool.
	 */
	@Override
	public void deactivate() {
		this.context.showStatusText("");
	}

	/**
	 * Anchor the figure and create a new one
	 */
	@Override
	public void mouseDown(int x, int y, MouseEvent e) {
		if (figure != null) {
			throw new IllegalStateException();
		}
		anchor = new Point(x, y);
		mkFigure(anchor, anchor);
		view.getModel().addFigure(figure);
	}

	/**
	 * Recreate the figure, while dragging around the mouse
	 */
	@Override
	public void mouseDrag(int x, int y, MouseEvent e) {
		figure.setBounds(anchor, new Point(x, y));
		java.awt.Rectangle r = figure.getBounds();
		this.context.showStatusText("w: " + r.width + ", h: " + r.height);
	}

	/**
	 * finish drawing the figure
	 */
	@Override
	public void mouseUp(int x, int y, MouseEvent e) {
		figure.setBounds(anchor, new Point(x, y));
		this.view.getModel().getDrawCommandHandler().addCommand(
				new AddFigureCommand(figure, this.view.getModel())
		);
		view.addToSelection(figure);
		figure = null;
		anchor = null;
		this.context.showStatusText(NAME + " Mode");
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
	
	/**
	 * Create the figure with the two points
	 * @param p1 start point
	 * @param p2 end point
	 */
	protected abstract void mkFigure(Point p1, Point p2);
}
