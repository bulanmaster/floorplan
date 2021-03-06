package com.ansis.floorplan.core.figure;

import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.OrderedLayout;
import org.eclipse.draw2d.PolygonShape;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.RGB;

import com.ansis.floorplan.util.FPConstPresentation;


public class PollyFigure extends PolygonShape  {

	// ====================== 2. Instance Fields =============================

	private PointList list = new PointList();

	private Label labelName = new Label();
	
	private Label toolTip = new Label();

	private RGB lineColor;

	private int fontStyle;

	private int fontSize;

	private RGB fontColor;


	// ==================== 4. Constructors ====================

	public PollyFigure(final Rectangle labelPosition) {

		final XYLayout layout = new XYLayout();
		setLayoutManager(layout);

		labelName.setOpaque(true);
		
		toolTip.setOpaque(false);
		
		add(labelName, OrderedLayout.ALIGN_CENTER);
		
		add(toolTip, OrderedLayout.ALIGN_CENTER);
		
		setConstraint(labelName, labelPosition);
		
		setConstraint(toolTip, labelPosition);

		setToolTip(toolTip);

		setPoints(list);
	}


	// ==================== 7. Getters & Setters ====================

	public Label getLabelName() {
		return labelName;
	}

	public void setName(final String name) {
		labelName.setText(name);
		toolTip.setText(name);
	}

	public void setLayout(final Rectangle rect) {
		getParent().setConstraint(this, rect);
	}

	public void setList(final PointList list) {
		setPoints(list);
		this.list = list;
	}

	public RGB getLineColor() {
		return lineColor;
	}

	public void setLineColor(final RGB lineColor) {
		this.lineColor = lineColor;
	}

	public int getFontStyle() {
		return fontStyle;
	}

	public void setFontStyle(final int fontStyle) {
		this.fontStyle = fontStyle;
		this.labelName.setFont(new Font(null, FPConstPresentation.EMPTY_STRING, getFontSize(), fontStyle)); 
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(final int fontSize) {
		this.fontSize = fontSize;
		this.labelName.setFont(new Font(null, FPConstPresentation.EMPTY_STRING, fontSize, getFontStyle())); 
	}

	public RGB getFontColor() {
		return fontColor;
	}

	public void setFontColor(final RGB fontColor) {
		this.fontColor = fontColor;
	}

}