/*
 * Copyright (c) 2000-2012 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package jdraw.std;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JComponent;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawModel;
import jdraw.framework.DrawModelEvent;
import jdraw.framework.DrawModelListener;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;
import jdraw.framework.PointConstrainer;

/**
 * Standard implementation of interface DrawView.
 * 
 * @see DrawView
 * @author Dominik Gruntz
 * @version 2.0, 26.04.01
 */
@SuppressWarnings("serial")
public class StdDrawView extends JComponent implements DrawView {
	/** Space in pixels around the minimal bounding box of all figures. */
	private static final int BOUNDING_BOX_PADDING = 10;

	/** The view's model. */
	private DrawModel model;
	/** The context surrounding this view. */
	private DrawContext context;
	/** The constrainer used in this view. */
	private PointConstrainer constrainer;
	/** The view's selection. */
	private List<Figure> selection = new LinkedList<Figure>();
	/** The handles occuring in this view. */
	private List<FigureHandle> handles = new LinkedList<FigureHandle>();
	/** Send changes to this listener. */
	private DrawModelListener ml;

	/**
	 * Indicates whether a mouse interaction is active. If dragging > 0 then
	 * moving/deleting figures over the keyboard is disabled. Type
	 * <code>int</code> instead of a boolean allows to handle inter-clicks
	 * correctly.
	 */
	private int dragging = 0;

	/**
	 * Creates a new StdDrawView.
	 * 
	 * @param aModel
	 *            the model that this view will visualize.
	 */
	public StdDrawView(DrawModel aModel) {

		this.model = aModel;

		ml = new DrawModelListener() {
			public void modelChanged(DrawModelEvent e) {
				Dimension size = getPreferredSize();
				setPreferredSize(size);
				revalidate();

				if (e.getType() == DrawModelEvent.Type.FIGURE_REMOVED) {
					removeFromSelection(e.getFigure());
				}
				if (e.getType() == DrawModelEvent.Type.DRAWING_CLEARED) {
					clearSelection();
				}
				repaint();
			}
		};

		this.model.addModelChangeListener(ml);

		InputEventHandler ieh = new InputEventHandler();
		addMouseListener(ieh);
		addMouseMotionListener(ieh);

		addKeyListener(ieh);
	}

	@Override
	public void finalize() throws Throwable {
		System.out.println("StdDrawView.finalize");
		super.finalize();
	}

	@Override
	public void close() {
		model.removeModelChangeListener(ml);
	}

	@Override
	public DrawModel getModel() {
		return model;
	}

	// Constrainer
	// ////////////
	@Override
	public void setConstrainer(PointConstrainer constrainer) {
		if (this.constrainer != null) {
			this.constrainer.deactivate();
		}
		this.constrainer = constrainer;
		if (this.constrainer != null) {
			this.constrainer.activate();
		}
	}

	@Override
	public PointConstrainer getConstrainer() {
		return constrainer;
	}

	/**
	 * Constraints the location of point p according to the rules of the
	 * underlying constrainer.
	 * 
	 * @param p
	 *            the point to constrain
	 * @return a point that is the result of constraining p, ie. the new
	 *         location of p according to the constrainer.
	 */
	protected Point constrainPoint(Point p) {
		if (constrainer != null) {
			return constrainer.constrainPoint(p);
		}
		return p;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}

	@Override
	public void paintComponent(Graphics g) {
		// g.setColor(getBackground());
		// g.fillRect(0, 0, getWidth(), getHeight());
		for (Figure f : model.getFigures()) {
			f.draw(g);
		}
		for (FigureHandle fh : handles) {
			fh.draw(g);
		}

		if (selectionRectangle != null) {
			g.setColor(Color.BLACK);
			g.drawRect(selectionRectangle.x, selectionRectangle.y,
					selectionRectangle.width, selectionRectangle.height);
		}
	}

	// Selection
	// //////////
	@Override
	public List<Figure> getSelection() {
		return new LinkedList<Figure>(selection);
	}

	// TODO neue Methode getHandlesSelection welche alle Handles der
	// selektierten Figuren zurueckgibt. Danach Iteration und Suche nach
	// Handle unter Maus ausserhalb.

	@Override
	public void clearSelection() {
		selection.clear();
		handles.clear();
	}

	@Override
	public void addToSelection(Figure f) {
		context.setDefaultTool();
		if (!selection.contains(f)) {
			selection.add(f);
			List<FigureHandle> hList = f.getHandles();
			if (hList != null) {
				handles.addAll(hList);
			}
		}
	}

	@Override
	public void removeFromSelection(Figure f) {
		if (selection.remove(f)) {
			Iterator<FigureHandle> it = handles.iterator();
			while (it.hasNext()) {
				FigureHandle h = it.next();
				if (h.getOwner() == f) {
					it.remove();
				}
			}
		}
	}

	/** Selection rectangle. */
	private Rectangle selectionRectangle;

	/**
	 * Set the selection rectangle.
	 * 
	 * @param selRectangle
	 *            new selection rectangle.
	 */
	public void setSelectionRubberBand(Rectangle selRectangle) {
		this.selectionRectangle = selRectangle;
	}

	// Size
	// /////
	@Override
	public Dimension getMinimumSize() {
		return getPreferredSize();
	}

	@Override
	public Dimension getPreferredSize() {
		java.awt.Rectangle r = new java.awt.Rectangle();
		for (Figure f : model.getFigures()) {
			r.add(f.getBounds());
		}

		Dimension size = new Dimension();
		size.height = r.height + r.y + BOUNDING_BOX_PADDING;
		size.width = r.width + r.x + BOUNDING_BOX_PADDING;
		return size;
	}

	@Override
	public FigureHandle getHandle(int x, int y, MouseEvent e) {
		for (FigureHandle fh : handles) {
			if (fh.contains(x, y)) {
				return fh;
			}
		}
		return null;
	}

	@Override
	public void setDrawContext(DrawContext context) {
		this.context = context;
	}

	@Override
	public DrawContext getDrawContext() {
		return context;
	}

	/**
	 * Handles all mouse and keyboard events for the StdDrawView.
	 * 
	 * @author Christoph Denzler
	 */
	private class InputEventHandler implements MouseListener,
			MouseMotionListener, KeyListener {
		// KeyListener
		// ////////////

		// Checkstyle will complain about a too high cyclomatic complexity.
		// Switch statements inherently boost this
		// metric but still present quite readable code. Unfortunately the
		// cyclometric complexity check cannot be
		// suppressed from code.
		@Override
		public void keyPressed(KeyEvent e) {
			// disable figure deletion and figure moving while mouse operations
			if (dragging > 0) {
				return;
			}

			int code = e.getKeyCode();
			if (code == KeyEvent.VK_DELETE) {
				for (Figure f : getSelection()) {
					System.out.println("Key pressed");
					model.getDrawCommandHandler().addCommand(
							new RemoveFigureCommand(model, f));
					model.removeFigure(f);
					// as a consequence, the figure is also removed from the
					// selection
				}
				repaint();
			}

			int dx = 0;
			int dy = 0;
			switch (code) {
			case KeyEvent.VK_LEFT:
				dx = (constrainer != null) ? -constrainer.getStepX(false) : -1;
				break;
			case KeyEvent.VK_RIGHT:
				dx = (constrainer != null) ? constrainer.getStepX(true) : +1;
				break;
			case KeyEvent.VK_UP:
				dy = (constrainer != null) ? -constrainer.getStepY(false) : -1;
				break;
			case KeyEvent.VK_DOWN:
				dy = (constrainer != null) ? constrainer.getStepY(true) : +1;
				break;
			default:
			}
			// move selection
			if (dx != 0 || dy != 0) {
				for (Figure figure : selection) {
					figure.move(dx, dy);
					model.getDrawCommandHandler().addCommand(
							new MoveCommand(figure, dx, dy));
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent keyevent) {
			// ignore event.
		}

		@Override
		public void keyTyped(KeyEvent keyevent) {
			// ignore event.
		}

		// MouseListener
		// /////////////////////////
		@Override
		public void mousePressed(MouseEvent e) {
			requestFocus();
			Point p = constrainPoint(new Point(e.getX(), e.getY()));
			if (dragging > 0) {
				// mouse was pressed during dragging, e.g. another mouse button.
				context.getTool().mouseDrag(p.x, p.y, e);
			} else if ((e.getModifiers() & InputEvent.BUTTON1_MASK) != 0) {
				model.getDrawCommandHandler().beginScript();
				context.getTool().mouseDown(p.x, p.y, e);
			}
			dragging++;
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			dragging--;
			Point p = constrainPoint(new Point(e.getX(), e.getY()));
			if (dragging > 0) {
				context.getTool().mouseDrag(p.x, p.y, e);
			} else if ((e.getModifiers() & InputEvent.BUTTON1_MASK) != 0) {
				model.getDrawCommandHandler().endScript();
				context.getTool().mouseUp(p.x, p.y, e);
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// do nothing on mouse click.
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// do nothing
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// do nothing
		}

		// MouseMotionListener
		// //////////////////////
		@Override
		public void mouseDragged(MouseEvent e) {
			Point p = constrainPoint(new Point(e.getX(), e.getY()));
			context.getTool().mouseDrag(p.x, p.y, e);
			setCursor(context.getTool().getCursor());
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();

			for (FigureHandle h : handles) {
				if (h.contains(x, y)) {
					StdDrawView.super.setCursor(h.getCursor());
					return;
				}
			}
			setCursor(context.getTool().getCursor());
		}
	}

}
