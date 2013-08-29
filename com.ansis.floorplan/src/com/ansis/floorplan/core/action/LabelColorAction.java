package com.ansis.floorplan.core.action;

import java.util.HashMap;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.IWorkbenchPart;

import com.ansis.floorplan.FloorplanActivator;
import com.ansis.floorplan.IFloorplanImageKeys;
import com.ansis.floorplan.util.FPConstPresentation;


public class LabelColorAction extends SelectionAction {

	// ==================== 1. Static Fields ========================

	public static final String labelColorProperty = "labelColorProperty"; //$NON-NLS-1$


	// ====================== 2. Instance Fields =============================

	private RGB labelColor;


	// ==================== 4. Constructors ====================

	public LabelColorAction(final IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(true);
	}


	// ==================== 5. Creators ====================

	public Command createLabelColorCommand(final RGB labelColor) {
		final Request labelColorReq = new Request("labelColor"); //$NON-NLS-1$
		final HashMap<String, RGB> reqData = new HashMap<String, RGB>();
		reqData.put("newLabelColor", labelColor); //$NON-NLS-1$
		labelColorReq.setExtendedData(reqData);
		final EditPart object = (EditPart)getSelectedObjects().get(0);
		final Command cmd = object.getCommand(labelColorReq);
		return cmd; 
	}


	// ==================== 6. Action Methods ====================

	@Override
	protected boolean calculateEnabled() {
		final Command cmd = createLabelColorCommand(null); 
		if (cmd == null)
			return false;
		return true;
	}

	@Override
	protected void init() {
		setId(labelColorProperty);
		setImageDescriptor(FloorplanActivator.getDefault().getImageDescriptor(IFloorplanImageKeys.ICON_FONT_BACKGROUND)); 
		setText("Label color" + FPConstPresentation.ELIPSES);  //$NON-NLS-1$
		setEnabled(false);
	}

	@Override
	public void run() {
		execute(createLabelColorCommand(getLabelColor()));
	}


	// ==================== 7. Getters & Setters ====================

	public RGB getLabelColor() {
		return labelColor;
	}

}