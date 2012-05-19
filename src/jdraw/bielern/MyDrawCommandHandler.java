/*
 * Copyright (c) 2000-2012 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.bielern;

import java.util.Stack;

import jdraw.framework.DrawCommand;
import jdraw.framework.DrawCommandHandler;

/**
 * Provides an empty command handler. This class provides an empty dummy implementation of the draw command
 * handler. It enables the application to start up correctly and to behave meaningful, but with the limitation
 * that it does not provide any undo/redo behavior. 
 * @author Christoph. Denzler
 *
 */
public class MyDrawCommandHandler implements DrawCommandHandler {
	
	protected Stack<DrawCommand> stack;
	protected int index;
	protected Boolean scripting;
	protected CompositeDrawCommand script;

	public MyDrawCommandHandler(){
		stack = new Stack<DrawCommand>();
		index = -1;
		scripting = false;
	}
	@Override
	public void addCommand(DrawCommand cmd) { 
		while (redoPossible()){
			stack.pop();
		}
		if (scripting){
			script.addCommand(cmd);
		} else {
			stack.add(cmd);
			index++;
		}
	}
	
	@Override
	public void undo() { 
		if (undoPossible()){
			stack.elementAt(index).undo();
			index--;
		}
		
	}

	@Override
	public void redo() { 
		if (redoPossible()) {
			index++;
			stack.elementAt(index).redo();
		}
	}

	@Override
	public boolean undoPossible() { 
		return (index > -1); 
	}

	@Override
	public boolean redoPossible() { 
		return (1 < (stack.size() - index)); 
	}

	@Override
	public void beginScript() { 
		scripting = true;
		script = new CompositeDrawCommand();
	}

	@Override
	public void endScript() { 
		scripting = false;
		if (! script.empty()){
			this.addCommand(script);
		}
	}

	@Override
	public void clearHistory() { 
		stack.clear();
		index = -1;
	}
}
