package jdraw.figures;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class ImageFigure extends AbstractRectangularFigure {

	private Image image;
	private String path;
	
	private int x, y, width, height;
	
	public ImageFigure(Point p1, String path){
		image = Toolkit.getDefaultToolkit().getImage(getClass().getResource(path));
		this.path = path;
		x = p1.x;
		y = p1.y;
		width = height = 1;
	}
	
	public ImageFigure(Rectangle r, String path){
		image = Toolkit.getDefaultToolkit().getImage(getClass().getResource(path));
		this.path = path;
		this.setBounds(r);
		x = r.x;
		y = r.y;
		width = r.width;
		height = r.height;
	}
	
	public ImageFigure(ImageFigure other){
		this(other.getBounds(), other.getPath());
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(image, x, y, width, height, null);
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
	
	public String getPath(){
		return path;
	}

	@Override
	public Object clone() {
		return new ImageFigure(this);
	}

}
