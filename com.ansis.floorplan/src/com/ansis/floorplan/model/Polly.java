package com.ansis.floorplan.model;

import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;


public class Polly extends ModelTest {

	// ==================== 1. Static Fields ========================

	public static final String PROPERTY_RENAME = "PollyRename"; //$NON-NLS-1$

	public static final String PROPERTY_COLOR = "PollyColor"; //$NON-NLS-1$


	// ====================== 2. Instance Fields =============================

	private PointList list;

	private ModelTest parent;

	private Rectangle bounds;

	private int etage;

	private String name;

	private Rectangle r;

	private Rectangle g;

	private Color color;


	// ==================== 3. Static Methods ====================

	private static Color defaultColor() {
		return new Color(null, 0, 0, 192);
	}


	// ==================== 4. Constructors ====================

	public Polly() {
		this.setColor(defaultColor());
	}


	// ==================== 7. Getters & Setters ====================

	public void setName(final String name) {
		final String oldName = this.name;
		this.name = name;
		getListeners().firePropertyChange(PROPERTY_RENAME, oldName, this.name);
	}

	public String getName() {
		return this.name;
	}

	public void setEtage(final int etage) {
		this.etage = etage;
	}

	public int getEtage() {
		return this.etage;
	}

	public PointList getList() {
		return list;
	}

	public void setList(final PointList list) {
		this.list = list;
	}

	public void setParent(final ModelTest parent) {
		this.parent = parent;
	}

	public ModelTest getParent() {
		return parent;
	}

	public void setBounds(final Rectangle bounds) {
		this.bounds = bounds;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public Rectangle getR() {
		return r;
	}

	public void setR(final Rectangle r) {
		this.r = r;
	}

	public Rectangle getG() {
		return g;
	}

	public void setG(final Rectangle g) {
		this.g = g;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(final Color color) {
		final Color oldColor = this.color;
		this.color = color;
		getListeners().firePropertyChange(PROPERTY_COLOR, oldColor, color);
	}

}