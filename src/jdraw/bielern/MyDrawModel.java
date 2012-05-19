/*
 * Copyright (c) 2000-2012 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.bielern;

import java.util.LinkedList;

import jdraw.framework.DrawCommandHandler;
import jdraw.framework.DrawModel;
import jdraw.framework.DrawModelEvent;
import jdraw.framework.DrawModelListener;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureListener;

/**
 * Provide a standard behavior for the drawing model. This class initially does not implement the methods
 * in a proper way.
 * It is part of the course assignments to do so.
 * @author Noah Bieler (bielern)
 *
 */
public class MyDrawModel implements DrawModel, FigureListener {
	// A list of all the figures
	private LinkedList<Figure> figures = new LinkedList<Figure>();
	
	// A list of all the draw model listener
	private LinkedList<DrawModelListener> drawModelListeners = new LinkedList<DrawModelListener>();
	
	private void notifyListeners(DrawModelEvent e){
		for (DrawModelListener l: drawModelListeners){
			l.modelChanged(e);
		}
	}

	@Override
	public void addFigure(Figure f) {
		if (!figures.contains(f)){
			f.addFigureListener(this);
			figures.add(f);
			notifyListeners(new DrawModelEvent(this, f, DrawModelEvent.Type.FIGURE_ADDED));
		}
	}

	@Override
	public Iterable<Figure> getFigures() {
	// TODO: to be implemented  
		//System.out.println("StdDrawModel.getFigures has to be implemented");
		//return new LinkedList<Figure>(); // Only guarantees, that the application starts -- has to be replaced !!!
		return figures;
	}

	@Override
	public void removeFigure(Figure f) {
		if(figures.remove(f)){
			f.removeFigureListener(this);
			notifyListeners(new DrawModelEvent(this, f, DrawModelEvent.Type.FIGURE_REMOVED));
		}
	}

	@Override
	public void setFigureIndex(Figure f, int index) {
		if(figures.remove(f)){
			figures.add(index, f);
			notifyListeners(new DrawModelEvent(this, f,
					DrawModelEvent.Type.DRAWING_CHANGED));
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void removeAllFigures() {
		for (Figure f : figures){
			notifyListeners(new DrawModelEvent(this, f, DrawModelEvent.Type.DRAWING_CLEARED));
			f.removeFigureListener(this);
		}
		figures.clear();
	}
	
	@Override
	public void addModelChangeListener(DrawModelListener listener) {
		drawModelListeners.add(listener);
	}

	@Override
	public void removeModelChangeListener(DrawModelListener listener) {
		drawModelListeners.remove(listener);
	}

	/** The draw command handler. Initialized here with a dummy implementation. */
	// TODO: initialize with your implementation from the assignments.
	private DrawCommandHandler handler = new MyDrawCommandHandler();

	/**
	 * Retrieve the draw command handler in use.
	 * @return the draw command handler.
	 */
	public DrawCommandHandler getDrawCommandHandler() {
		return handler;
	}

	@Override
	public void figureChanged(FigureEvent e) {
		notifyListeners(new DrawModelEvent(this, e.getFigure(), DrawModelEvent.Type.FIGURE_CHANGED));
	}

}
