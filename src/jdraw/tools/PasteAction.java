package jdraw.tools;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

@SuppressWarnings("serial")
public class PasteAction extends AbstractAction {
	
	protected DrawView view;
	protected ClipBoard cb;

	public PasteAction(DrawView view) {
		super("Paste");
		this.view = view;
		cb = ClipBoard.getClipboard();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		java.util.List<Figure> figures = cb.getFigures();
		for (Figure f : figures){
			view.getModel().addFigure(f);
		}
	}

}
