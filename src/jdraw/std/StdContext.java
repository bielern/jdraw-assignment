/*
 * Copyright (c) 2000-2012 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */
package jdraw.std;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;

import jdraw.figures.EllipseTool;
import jdraw.figures.ImageTool;
import jdraw.figures.LineTool;
import jdraw.figures.RectTool;
import jdraw.framework.DrawModel;
import jdraw.framework.DrawToolFactory;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.ricoh.GroupAction;
import jdraw.ricoh.UngroupAction;
import jdraw.tools.CopyAction;
import jdraw.tools.CutAction;
import jdraw.tools.DecorateBorderAction;
import jdraw.tools.PasteAction;
import jdraw.tools.UndecorateBorderAction;

/**
 * Standard implementation of interface DrawContext.
 * 
 * @see DrawView
 * @author Dominik Gruntz & Christoph Denzler
 * @version 2.6, 24.09.09
 */
@SuppressWarnings("serial")
public class StdContext extends AbstractContext {

	/**
	 * Constructs a standard context with a default set of drawing tools.
	 * @param view the view that is displaying the actual drawing.
	 */
  public StdContext(DrawView view) {
		super(view, null);
	}
	
  /**
   * Constructs a standard context. The drawing tools available can be parameterized using <code>toolFactories</code>.
   * @param view the view that is displaying the actual drawing.
   * @param toolFactories a list of DrawToolFactories that are available to the user
   */
	public StdContext(DrawView view, List<DrawToolFactory> toolFactories) {
		super(view, toolFactories);
	}

	/**
	 * Creates and initializes the "Edit" menu.
	 * 
	 * @return the new "Edit" menu.
	 */
	@Override
	protected JMenu createEditMenu() {
		JMenu editMenu = new JMenu("Edit");
		final JMenuItem undo = new JMenuItem("Undo");
		undo.setAccelerator(KeyStroke.getKeyStroke("control Z"));
		editMenu.add(undo);
		undo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getModel().getDrawCommandHandler().undo();
			}
		});

		final JMenuItem redo = new JMenuItem("Redo");
		redo.setAccelerator(KeyStroke.getKeyStroke("control Y"));
		editMenu.add(redo);
		redo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getModel().getDrawCommandHandler().redo();
			}
		});
		editMenu.addSeparator();

		JMenuItem sa = new JMenuItem("SelectAll");
		sa.setAccelerator(KeyStroke.getKeyStroke("control A"));
		editMenu.add(sa);
		sa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Figure f : getModel().getFigures()) {
					getView().addToSelection(f);
				}
				getView().repaint();
			}
		});

		editMenu.addSeparator();
		editMenu.add(new JMenuItem(new CutAction(getView())));
		editMenu.add(new JMenuItem(new CopyAction(getView())));
		editMenu.add(new JMenuItem(new PasteAction(getView())));

		editMenu.addSeparator();
		JMenuItem group = new JMenuItem("Group");
		group.setAccelerator(KeyStroke.getKeyStroke("control G"));
		group.setEnabled(true);
		editMenu.add(group);
		group.addActionListener(new GroupAction(getView()));

		JMenuItem ungroup = new JMenuItem("Ungroup");
		ungroup.setAccelerator(KeyStroke.getKeyStroke("control shift G"));
		ungroup.setEnabled(true);
		editMenu.add(ungroup);
		ungroup.addActionListener(new UngroupAction(getView()));
		
		JMenuItem addBorder = new JMenuItem("Add Border");
		editMenu.add(addBorder);
		addBorder.addActionListener(new DecorateBorderAction(getView()));
		
		JMenuItem rmBorder  = new JMenuItem("Remove Border");
		editMenu.add(rmBorder);
		rmBorder.addActionListener(new UndecorateBorderAction(getView()));

		editMenu.addSeparator();

		JMenu orderMenu = new JMenu("Order...");
		JMenuItem frontItem = new JMenuItem("Bring To Front");
		frontItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bringToFront(getView().getModel(), getView().getSelection());
				getView().repaint();
			}
		});
		orderMenu.add(frontItem);
		JMenuItem backItem = new JMenuItem("Send To Back");
		backItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendToBack(getView().getModel(), getView().getSelection());
				getView().repaint();
			}
		});
		orderMenu.add(backItem);
		editMenu.add(orderMenu);

		JMenu grid = new JMenu("Grid...");
		ButtonGroup gridGroup = new ButtonGroup();
		JRadioButtonMenuItem noGrid = new JRadioButtonMenuItem(new jdraw.tools.NoGrid(getView()));
		grid.add(noGrid);
		JRadioButtonMenuItem grid20 = new JRadioButtonMenuItem(new jdraw.tools.Grid(20, getView()));
		grid.add(grid20);
		JRadioButtonMenuItem grid50 = new JRadioButtonMenuItem(new jdraw.tools.Grid(50, getView()));
		grid.add(grid50);
		JRadioButtonMenuItem snapGrid = new JRadioButtonMenuItem(new jdraw.tools.SnapGrid(getView()));
		grid.add(snapGrid);
		gridGroup.add(noGrid);
		gridGroup.add(grid20);
		gridGroup.add(grid50);
		gridGroup.add(snapGrid);
		editMenu.add(grid);
		
		return editMenu;
	}

	/**
	 * Creates and initializes items in the file menu.
	 * 
	 * @return the new "File" menu.
	 */
	@Override
	protected JMenu createFileMenu() {
	  JMenu fileMenu = new JMenu("File");
		JMenuItem open = new JMenuItem("Open");
		fileMenu.add(open);
		open.setAccelerator(KeyStroke.getKeyStroke("control O"));
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doOpen();
			}
		});

		JMenuItem save = new JMenuItem("Save");
		save.setAccelerator(KeyStroke.getKeyStroke("control S"));
		fileMenu.add(save);
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doSave();
			}
		});

		JMenuItem exit = new JMenuItem("Exit");
		fileMenu.add(exit);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		return fileMenu;
	}

	@Override
	protected void doRegisterDrawTools() {
		// TODO: Add new figure tools here
		addTool(new RectTool(this));
		addTool(new LineTool(this));
		addTool(new EllipseTool(this));
		addTool(new ImageTool(this, "GNU", "gnu.png", "gnu_small.png"));
		addTool(new ImageTool(this, "Tux", "tux.png", "tux_small.png"));
	}

	/**
	 * Changes the order of figures and moves the figures in the selection
	 * to the front, i.e. moves them to the end of the list of figures.
	 * @param model model in which the order has to be changed
	 * @param selection selection which is moved to front
	 */
	public void bringToFront(DrawModel model, List<Figure> selection) {
		// the figures in the selection are ordered according to the order in
		// the model
		List<Figure> orderedSelection = new LinkedList<Figure>();
		int pos = 0;
		for (Figure f : model.getFigures()) {
			pos++;
			if (selection.contains(f)) {
				orderedSelection.add(0, f);
			}
		}
		for (Figure f : orderedSelection) {
			model.setFigureIndex(f, --pos);
		}
	}

	/**
	 * Changes the order of figures and moves the figures in the selection
	 * to the back, i.e. moves them to the front of the list of figures.
	 * @param model model in which the order has to be changed
	 * @param selection selection which is moved to the back
	 */
	public void sendToBack(DrawModel model, List<Figure> selection) {
		// the figures in the selection are ordered according to the order in
		// the model
		List<Figure> orderedSelection = new LinkedList<Figure>();
		for (Figure f : model.getFigures()) {
			if (selection.contains(f)) {
				orderedSelection.add(f);
			}
		}
		int pos = 0;
		for (Figure f : orderedSelection) {
			model.setFigureIndex(f, pos++);
		}
	}

	/**
	 * Handles the opening of a new drawing from a file.
	 */
	private void doOpen() {
		JFileChooser chooser = new JFileChooser(getClass().getResource("")
				.getFile());
		chooser.setDialogTitle("Open Graphic");
		chooser.setDialogType(JFileChooser.OPEN_DIALOG);
		chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
			@Override
			public String getDescription() {
				return "JDraw Graphic (*.draw)";
			}

			@Override
			public boolean accept(File f) {
				return f.isDirectory() || f.getName().endsWith(".draw");
			}
		});
		int res = chooser.showOpenDialog(this);

		if (res == JFileChooser.APPROVE_OPTION) {
			// read jdraw graphic
			System.out.println("read file "
					+ chooser.getSelectedFile().getName());
		}
	}

	/**
	 * Handles the saving of a drawing to a file.
	 */
	private void doSave() {
		JFileChooser chooser = new JFileChooser(getClass().getResource("")
				.getFile());
		chooser.setDialogTitle("Save Graphic");
		chooser.setDialogType(JFileChooser.SAVE_DIALOG);
		FileFilter filter = new FileFilter() {
			@Override
			public String getDescription() {
				return "JDraw Graphic (*.draw)";
			}

			@Override
			public boolean accept(File f) {
				return f.getName().endsWith(".draw");
			}
		};
		chooser.setFileFilter(filter);
		int res = chooser.showOpenDialog(this);

		if (res == JFileChooser.APPROVE_OPTION) {
			// save graphic
			File file = chooser.getSelectedFile();
			if (chooser.getFileFilter() == filter && !filter.accept(file)) {
				file = new File(chooser.getCurrentDirectory(), file.getName() + ".draw");
			}
			System.out.println("save current graphic to file " + file.getName());
		}
	}

}
