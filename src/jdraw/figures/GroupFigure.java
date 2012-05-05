package jdraw.figures;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureGroup;

@SuppressWarnings("serial")
public class GroupFigure extends AbstractRectangularFigure implements FigureGroup {

	private LinkedList<Figure> figureParts = new LinkedList<Figure>();
	private LinkedList<InnerBound> innerBounds = new LinkedList<InnerBound>();

	public GroupFigure(DrawView view) {
		List<Figure> figures = view.getSelection();
		Rectangle b = figures.get(0).getBounds();
		this.setBounds(b.getLocation(), new Point(b.x + b.width, b.y + b.height));
		for (Figure f : figures){
			figureParts.add(f);
			bounds.add(f.getBounds());
			view.getModel().removeFigure(f);
		}
		for (Figure f : figureParts){
			innerBounds.add(new InnerBound(f.getBounds()));
		}
	}

	@Override
	public Iterable<Figure> getFigureParts() {
		return figureParts;
	}

	@Override
	public void draw(Graphics g) {
		for (Figure f : figureParts){
			f.draw(g);
		}
	}

	@Override
	protected void _move(int dx, int dy) {
		for (Figure f : figureParts){
			f.move(dx, dy);
		}
	}

	@Override
	protected void _setBounds(Point origin, Point corner) {
		for (int i = 0; i < figureParts.size(); i++){
			Rectangle ib = innerBounds.get(i).getInnerBound();
			figureParts.get(i).setBounds(ib.getLocation(), new Point((int) ib.getMaxX(), (int) ib.getMaxY()));
		}
	}
	
	public class InnerBound {
		double leftX, relWidth, upperY, relHeight;
		InnerBound(Rectangle innerBound){
			leftX = ((double) innerBound.x - bounds.x) / bounds.width; 			
			relWidth = ((double) innerBound.width) / bounds.width; 
			upperY = ((double) innerBound.y - bounds.y) / bounds.height; 			
			relHeight = ((double) innerBound.height) / bounds.height; 

		}
		
		public Rectangle getInnerBound(){
			Rectangle ib = new Rectangle(bounds);
			ib.x = (int) (bounds.x + leftX * bounds.width);
			ib.width = (int) (relWidth * bounds.width);
			ib.y = (int) (bounds.y + upperY * bounds.height);
			ib.height = (int) (relHeight * bounds.height);
			return ib;
		}
	}

}
