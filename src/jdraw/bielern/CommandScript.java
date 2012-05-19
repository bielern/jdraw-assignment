package jdraw.bielern;

import jdraw.framework.DrawCommand;


class CompositeDrawCommand extends MyDrawCommandHandler implements DrawCommand {
	@Override
	public void undo() {
		while (undoPossible()) {
			super.undo();
		}

	}

	@Override
	public void redo() {
		while (redoPossible()) {
			super.redo();
		}
	}
	
	public Boolean empty(){
		return stack.empty();
	}
}