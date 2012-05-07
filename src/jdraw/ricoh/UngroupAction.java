package jdraw.ricoh;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import jdraw.figures.GroupFigure;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

@SuppressWarnings("serial")
public class UngroupAction extends AbstractAction {

	private DrawView view;
	
	public UngroupAction(DrawView view) {
		super();
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (Figure s  : view.getSelection()){
			if (s instanceof GroupFigure){
				for (Figure f : ((GroupFigure) s).getFigureParts()){
					view.getModel().addFigure(f);
				}
				view.getModel().removeFigure(s);
			}
		}
		
	}

}
