package jdraw.figures;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class ImageFigure extends AbstractRectangularFigure {

	private Image image;
	
	private int x, y, width, height;
	
	public ImageFigure(Point p1, String path){
		image = Toolkit.getDefaultToolkit().getImage(getClass().getResource(path));
		x = p1.x;
		y = p1.y;
		width = height = 1;
	}
	@Override
	public void draw(Graphics g) {
		g.drawImage(image, x, y, width, height, null);
	}

	@Override
	public boolean contains(int x, int y) {
		x -= this.x;
		y -= this.y;
		return x > 0 && y > 0 && x < width && y < height;
	}
	
	@Override
	protected void _move(int dx, int dy) {
		x += dx;
		y += dy;		
	}
	@Override
	protected void _setBounds(Point origin, Point corner) {
		RightRect rr = new RightRect(origin, corner);
		x = rr.x;
		y = rr.y;
		width = rr.width;
		height = rr.height;		
	}

}
