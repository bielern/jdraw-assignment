package jdraw.tools;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import jdraw.figures.BorderDecorator;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

@SuppressWarnings("serial")
public class DecorateBorderAction extends AbstractAction {
	
	DrawView view;

	public DecorateBorderAction(DrawView view) {
		super();
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		for(Figure f : view.getSelection()) {
			BorderDecorator d = new BorderDecorator(f);
			view.getModel().addFigure(d);
			//view.getModel().removeFigure(f);
			view.addToSelection(d);
		}
	}

}
