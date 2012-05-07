package jdraw.ricoh;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import jdraw.framework.DrawContext;

@SuppressWarnings("serial")
public class GroupAction extends AbstractAction {
	
	DrawContext context;
	
	public GroupAction(DrawContext context) {
		this.context = context;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// get selected figures with getView().getSelection()
		// call drawModel.removeFigure() on them
		// make a new GroupFigure from them.
		context.getView().getSelection();
	}

}
