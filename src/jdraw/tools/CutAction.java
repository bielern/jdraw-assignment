package jdraw.tools;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

@SuppressWarnings("serial")
public class CutAction extends AbstractAction {

	protected DrawView view;
	protected ClipBoard cb;
	
	public CutAction(DrawView view) {
		super("Cut");
		this.view = view;
		cb = ClipBoard.getClipboard();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		cb.setFigures(view.getSelection());
		for (Figure f : view.getSelection()){
			view.getModel().removeFigure(f);
		}
	}
	

}
