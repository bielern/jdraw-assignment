package jdraw.tools;

import java.util.LinkedList;
import java.util.List;

import jdraw.framework.Figure;

public class ClipBoard {
	private static ClipBoard cb = null;
	private LinkedList<Figure> figures;
	
	private ClipBoard(){
		figures = new LinkedList<Figure> ();
	}
	
	static public ClipBoard getClipboard(){
		if (cb == null){
			cb = new ClipBoard();
		}
		return cb;
	}
	
	public  List<Figure> getFigures(){
		return figures;
	}
	
	public void setFigures(List<Figure> list) {
		if (list.size() > 0) {
			figures.clear();
			for (Figure f : list) {
				this.figures.add((Figure) f.clone());
			}
		}
	}

}
