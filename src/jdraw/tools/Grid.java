package jdraw.tools;

import java.awt.Point;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import jdraw.framework.DrawView;
import jdraw.framework.PointConstrainer;

@SuppressWarnings("serial")
public class Grid extends AbstractAction implements PointConstrainer {

	private int gridSize;
	private DrawView view;
	
	public Grid(int gridSize, DrawView view) {
		super(gridSize + " x " + gridSize + " Grid");
		this.gridSize = gridSize;
		this.view = view;
	}

	@Override
	public Point constrainPoint(Point p) {
		int x = (p.x + gridSize / 2) / gridSize * gridSize;
		int y = (p.y + gridSize / 2) / gridSize * gridSize;
		Point result = new Point(x, y);
		return result;
	}

	@Override
	public int getStepX(boolean right) {
		if (right)
			return gridSize;
		else 
			return -gridSize;
	}

	@Override
	public int getStepY(boolean down) {
		if (down)
			return gridSize;
		else
			return -gridSize;
	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deactivate() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		view.setConstrainer(this);
	}

}
