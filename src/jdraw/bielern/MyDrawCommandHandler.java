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
	protected CommandScript script;

	public MyDrawCommandHandler(){
		//System.out.println("Instantiated the draw command handler");
		stack = new Stack<DrawCommand>();
		index = -1;
		scripting = false;
	}
	@Override
	public void addCommand(DrawCommand cmd) { 
		System.out.println("Add Command");
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
			System.out.println("undo");
			stack.elementAt(index).undo();
			index--;
		}
		
	}

	@Override
	public void redo() { 
		if (redoPossible()) {
			System.out.println("redo");
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
		System.out.println("begin script"); 
		scripting = true;
		script = new CommandScript();
	}

	@Override
	public void endScript() { 
		System.out.println("end script"); 
		scripting = false;
		this.addCommand(script);
	}

	@Override
	public void clearHistory() { 
		System.out.println("clear history");
		stack.clear();
		index = -1;
	}
}
