package jdraw.tools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import jdraw.figures.DecoratorFigure;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

@SuppressWarnings("serial")
public class UndecorateBorderAction extends AbstractAction implements
		ActionListener {

	private DrawView view;

	public UndecorateBorderAction(DrawView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(Figure f : view.getSelection()) {
			if (f instanceof DecoratorFigure) {
				DecoratorFigure d = (DecoratorFigure) f;
				Figure inner = d.getFig();
				view.getModel().addFigure(inner);
				view.getModel().removeFigure(f);
			}
		}
	}

}
