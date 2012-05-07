package jdraw.tools;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import jdraw.framework.DrawView;

@SuppressWarnings("serial")
public class CopyAction extends AbstractAction {
	
	protected DrawView view;
	protected ClipBoard cb;
	

	public CopyAction(DrawView view) {
		super("Copy");
		this.view = view;
		cb = ClipBoard.getClipboard();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		cb.setFigures(view.getSelection());
	}

}
