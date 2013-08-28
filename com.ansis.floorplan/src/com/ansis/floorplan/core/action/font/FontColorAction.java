package com.ansis.floorplan.core.action.font;

import java.util.HashMap;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.IWorkbenchPart;

import com.ansis.floorplan.FloorplanActivator;
import com.ansis.floorplan.util.FPConstPresentation;


public class FontColorAction extends SelectionAction {

	// ==================== 1. Static Fields ========================

	public static final String fontColorProperty = "fontColorProperty"; //$NON-NLS-1$


	// ====================== 2. Instance Fields =============================

	private RGB fontColor;


	// ==================== 4. Constructors ====================

	public FontColorAction(final IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(true);
	}


	// ==================== 5. Creators ====================

	public Command createChangeFontColorCommand(final RGB fontColor) {
		final Request changeFontColorReq = new Request("changeFontColor"); //$NON-NLS-1$
		final HashMap<String, RGB> reqData = new HashMap<String, RGB>();
		reqData.put("newFontColor", fontColor); //$NON-NLS-1$
		changeFontColorReq.setExtendedData(reqData);
		final EditPart object = (EditPart)getSelectedObjects().get(0);
		final Command cmd = object.getCommand(changeFontColorReq);
		return cmd; 
	}


	// ==================== 6. Action Methods ====================

	@Override
	protected boolean calculateEnabled() {
		final Command cmd = createChangeFontColorCommand(null); 
		if (cmd == null)
			return false;
		return true;
	}

	@Override
	protected void init() {
		setId(fontColorProperty);
		setImageDescriptor(FloorplanActivator.getDefault().getImageDescriptor("icons/font/fontColor.png")); //$NON-NLS-1$
		setText("Font color"+FPConstPresentation.ELIPSES);  //$NON-NLS-1$
		setEnabled(false);
	}

	@Override
	public void run() {
		execute(createChangeFontColorCommand(getFontColor()));
	}


	// ==================== 7. Getters & Setters ====================

	public RGB getFontColor() {
		return fontColor;
	}

}