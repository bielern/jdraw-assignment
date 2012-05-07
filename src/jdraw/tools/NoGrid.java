package jdraw.tools;

import java.awt.Point;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import jdraw.framework.DrawView;
import jdraw.framework.PointConstrainer;

@SuppressWarnings("serial")
public class NoGrid extends AbstractAction implements PointConstrainer {

	private DrawView view;
	public NoGrid(DrawView view){
		super("No Grid");
		this.view = view;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		view.setConstrainer(null);
	}

	@Override
	public Point constrainPoint(Point p) {
		return null;
	}

	@Override
	public int getStepX(boolean right) {
		if (right)
			return 1;
		else
			return -1;
	}

	@Override
	public int getStepY(boolean down) {
		if (down)
			return 1;
		else
			return -1;
	}

	@Override
	public void activate() {
	}

	@Override
	public void deactivate() {
	}

}
