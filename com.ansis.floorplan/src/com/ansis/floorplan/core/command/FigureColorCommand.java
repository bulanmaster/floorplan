package com.ansis.floorplan.core.command;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Display;

import com.ansis.floorplan.core.model.ChildModel;
import com.ansis.floorplan.util.FPConstPresentation;


public class FigureColorCommand extends Command {

	// ====================== 2. Instance Fields =============================

	private ChildModel model;

	private RGB oldFigureColor;

	@SuppressWarnings("unused")
	private RGB newFigureColor;


	// ==================== 6. Action Methods ====================

	@Override
	public void execute() {
		this.oldFigureColor = model.getColor();

		// Create the color-change dialog
		final ColorDialog dlg = new ColorDialog(Display.getCurrent().getActiveShell());

		// Change the title bar text
		dlg.setText("Choose a figure color" + FPConstPresentation.ELIPSES);

		// Open the dialog and retrieve the selected color
		final RGB rgb = dlg.open();

		if (rgb != null) 
			model.setColor(rgb);

	}

	@Override
	public void undo() {
		this.model.setColor(oldFigureColor);
	}


	// ==================== 7. Getters & Setters ====================

	public void setModel(final Object model) {
		this.model = (ChildModel)model;
	}

	public void setNewFigureColor(final RGB newFigureColor) {
		this.newFigureColor = newFigureColor;
	}

}