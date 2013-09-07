package com.ansis.floorplan.core.action.defaults;

import java.util.HashMap;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.IWorkbenchPart;

import com.ansis.floorplan.FloorplanActivator;
import com.ansis.floorplan.IFloorplanImageKeys;
import com.ansis.floorplan.core.model.Canvas;
import com.ansis.floorplan.core.model.ChildModel;
import com.ansis.floorplan.util.color.FPStandardColor;


public class DefaultFigureColorAction extends SelectionAction {

	// ==================== 1. Static Fields ========================

	public static final String defaultFigureColorProperty = "defaultFigureColorProperty"; //$NON-NLS-1$


	// ====================== 2. Instance Fields =============================

	private Canvas model;

	private int selection = 0;


	// ==================== 4. Constructors ====================

	public DefaultFigureColorAction(final IWorkbenchPart part, final Canvas model) {
		super(part);
		this.model = model;
		setLazyEnablementCalculation(true);
	}


	// ==================== 5. Creators ====================

	public Command createDefaultFigureColorCommand(final RGB defaultFigureColor) {
		final Request defaultFigureColorReq = new Request("defaultFigureColor"); //$NON-NLS-1$
		final HashMap<String, RGB> reqData = new HashMap<String, RGB>();
		reqData.put("newDefaultFigureColor", defaultFigureColor); //$NON-NLS-1$
		defaultFigureColorReq.setExtendedData(reqData);
		final EditPart object = (EditPart)getSelectedObjects().get(0);
		final Command cmd = object.getCommand(defaultFigureColorReq);
		return cmd; 
	}


	// ==================== 6. Action Methods ====================

	@Override
	protected boolean calculateEnabled() {
		final Command cmd = createDefaultFigureColorCommand(null); 
		if (cmd == null)
			return false;
		return true;
	}

	@Override
	protected void init() {
		setId(defaultFigureColorProperty);
		setImageDescriptor(FloorplanActivator.getDefault().getImageDescriptor(IFloorplanImageKeys.ICON_COLOR)); 
		setText("Default figure color (Navy Blue)");
		setEnabled(false);
	}

	@Override
	public void run() 
	{
		final Request defaultFigureColorReq = new Request("defaultFigureColor"); //$NON-NLS-1$
		final HashMap<String, RGB> reqData = new HashMap<String, RGB>();
		reqData.put("newDefaultFigureColor", getDefaultFigureColor()); //$NON-NLS-1$
		defaultFigureColorReq.setExtendedData(reqData);
		for (final Object ob : getSelectedObjects()) {

			final EditPart object = (EditPart)ob;
			final Command cmd = object.getCommand(defaultFigureColorReq);
			selection = 1;
			execute(cmd);

		}		

		if (selection == 0) {
			for (final ChildModel childModel : model.getChildren())
				childModel.setColor(getDefaultFigureColor());
		}
	}


	// ==================== 7. Getters & Setters ====================

	public RGB getDefaultFigureColor() {
		return FloorplanActivator.getDefault().getColor(FPStandardColor.BLUE_NAVY).getRGB();
	}

}