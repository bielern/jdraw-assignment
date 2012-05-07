package jdraw.ricoh;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import jdraw.figures.GroupFigure;
import jdraw.framework.DrawView;

@SuppressWarnings("serial")
public class GroupAction extends AbstractAction {

	private DrawView view;

	public GroupAction(DrawView view) {
		super();
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		GroupFigure g = new GroupFigure(view);
		view.getModel().addFigure(g);
	}

}
