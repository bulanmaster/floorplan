package com.ansis.floorplan.core.action.font.style;

import java.util.HashMap;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import com.ansis.floorplan.FloorplanActivator;
import com.ansis.floorplan.IFloorplanImageKeys;
import com.ansis.floorplan.core.model.CanvasModel;
import com.ansis.floorplan.core.model.ChildModel;
import com.ansis.floorplan.util.FPConstPresentation;
import com.ansis.floorplan.util.font.FPFontStyle;


public class FontStyleBoldAction extends SelectionAction {

	// ==================== 1. Static Fields ========================

	public static final String fontStylePropertyBold = "fontStylePropertyBold"; //$NON-NLS-1$

	
	// ====================== 2. Instance Fields =============================

	private CanvasModel model;
	
	private int selection = 0;
	

	// ==================== 4. Constructors ====================

	public FontStyleBoldAction(final IWorkbenchPart part, final CanvasModel model) {
		super(part);
		this.model = model;
		setLazyEnablementCalculation(true);
	}


	// ==================== 5. Creators ====================

	public Command createFontStyleCommand(final String fontStyle) {
		final Request fontStyleReq = new Request("fontStyle"); //$NON-NLS-1$
		final HashMap<String, String> reqData = new HashMap<String, String>();
		reqData.put("newFontStyle", fontStyle); //$NON-NLS-1$
		fontStyleReq.setExtendedData(reqData);
		final EditPart object = (EditPart)getSelectedObjects().get(0);
		final Command cmd = object.getCommand(fontStyleReq);
		return cmd; 
	}


	// ==================== 6. Action Methods ====================

	@Override
	protected boolean calculateEnabled() {
		final Command cmd = createFontStyleCommand(FPConstPresentation.EMPTY_STRING); 
		if (cmd == null)
			return false;
		return true;
	}

	@Override
	protected void init() {
		setId(fontStylePropertyBold);
		setImageDescriptor(FloorplanActivator.getDefault().getImageDescriptor(IFloorplanImageKeys.ICON_FONT_BOLD)); 
		setText(FPFontStyle.BOLD.getName());
		setEnabled(false);
	}

	@Override
	public void run() {
		
		final Request fontStyleReq = new Request("fontStyle"); //$NON-NLS-1$
		final HashMap<String, String> reqData = new HashMap<String, String>();
		reqData.put("newFontStyle", getBold()); //$NON-NLS-1$
		fontStyleReq.setExtendedData(reqData);
		for (final Object ob : getSelectedObjects()) {

			final EditPart object = (EditPart)ob;
			final Command cmd = object.getCommand(fontStyleReq);
			selection = 1;
			execute(cmd);

		}
		if (selection == 0) {
			for (final ChildModel childModel : model.getChildren())
				childModel.setFontStyle( FPFontStyle.BOLD.getStyle() );
		}
	}


	// ==================== 7. Getters & Setters ====================

	public String getBold() {
		return String.valueOf( FPFontStyle.BOLD.getStyle() );
	}

}