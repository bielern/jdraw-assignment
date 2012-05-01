package jdraw.tools;


import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import jdraw.framework.DrawModelEvent;
import jdraw.framework.DrawModelListener;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;
import jdraw.framework.PointConstrainer;
import jdraw.framework.DrawView;

@SuppressWarnings("serial")
public class SnapGrid extends AbstractAction implements PointConstrainer, DrawModelListener {

	private DrawView view;
	private final int DIST = 20;
	private Figure generatedFigure;
	
	public SnapGrid(DrawView view) {
		super("Snap Grid");
		this.view = view;
		this.generatedFigure = null;
	}

	public SnapGrid(String name, Icon icon) {
		super(name, icon);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		view.setConstrainer(this);
		view.getModel().addModelChangeListener(this);
	}

	@Override
	public Point constrainPoint(Point p) {
		int minDist = 10000; // very large
		Point minPoint = new Point(p);
		List<Figure> selected = view.getSelection();
		//System.out.println("selected: " + selected.size());
		if (selected.size() == 0) { // Generating a figure
			for (Figure f : view.getModel().getFigures()) {
				if (!f.equals(this.generatedFigure)) {
					//System.out.println("Figure checks handles");
					List<FigureHandle> handles = f.getHandles();
					for (FigureHandle h : handles) {
						Point hp = h.getLocation();
						int dist = (int) Math.sqrt((p.x - hp.x) * (p.x - hp.x)
								+ (p.y - hp.y) * (p.y - hp.y));
						if (dist < this.DIST && dist < minDist) {
							minDist = dist;
							minPoint = hp;
						}
					}
				}
			}
			this.generatedFigure = null;
			return minPoint;
		} else { // moving around figures
			Point myNearestHandle = null;
			for (Figure f : view.getModel().getFigures()) {
				if (!selected.contains(f) && !f.equals(this.generatedFigure)) {
					//System.out.println("Figure checks handles");
					List<FigureHandle> handles = f.getHandles();
					for (FigureHandle h : handles) {
						for (Figure s : selected) {
							for (FigureHandle myH : s.getHandles()) {
								Point hp = h.getLocation();
								Point myHp = myH.getLocation();
								int dist = (int) Math.sqrt(
										(myHp.x - hp.x) * (myHp.x - hp.x) + 
										(myHp.y - hp.y) * (myHp.y - hp.y));
								if (dist < this.DIST && dist < minDist) {
									minDist = dist;
									minPoint = hp;
									myNearestHandle = myHp;
								}
							}
						}
					}
				}
			}
			if (myNearestHandle != null){
				System.out.println("Other: " + minPoint.x + " and " + minPoint.y);
				//System.out.println("Mine: " + myNearestHandle.x + " and " + myNearestHandle.y);
				//return new Point(p.x + minPoint.x - myNearestHandle.x, p.y + minPoint.y - myNearestHandle.y);
				int dx = minPoint.x - myNearestHandle.x;
				int dy = minPoint.y - myNearestHandle.y;
				Point res = new Point(p.x + dx, p.y + dy);
				//System.out.println("New: " + res.x + " and " + res.y);
				//System.out.println("Old: " + p.x + " and " + p.y);
				return res;
				
			} else {
				System.out.println("No constraints!");
				return p;
			}
		}
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
		// TODO Auto-generated method stub

	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void modelChanged(DrawModelEvent e) {
		//System.out.println("I am now informed");
		this.generatedFigure = e.getFigure();
		
	}

}
