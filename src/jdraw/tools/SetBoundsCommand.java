package jdraw.tools;

import java.awt.Point;

import jdraw.framework.DrawCommand;
import jdraw.framework.Figure;

public class SetBoundsCommand implements DrawCommand {
	private Point oldOrigin, oldCorner;
	private Point newOrigin, newCorner;
	private Figure f;
	

	public SetBoundsCommand(Point oldOrigin, Point oldCorner, Point newOrigin,
			Point newCorner, Figure f) {
		super();
		this.oldOrigin = oldOrigin;
		this.oldCorner = oldCorner;
		this.newOrigin = newOrigin;
		this.newCorner = newCorner;
		this.f = f;
	}

	@Override
	public void redo() {
		f.setBounds(newOrigin, newCorner);

	}

	@Override
	public void undo() {
		f.setBounds(oldOrigin, oldCorner);

	}

}
