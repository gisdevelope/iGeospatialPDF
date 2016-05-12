package draw;

import draw.style.Style;

/**
 * Abstract parental class for all draw-able elements that provides functions
 * and attributes for all child-classes.
 * 
 * @author DaGri
 * @since 03.05.2016
 */
public abstract class DrawElement {

	// ATTRIBUTES

	/**
	 * The {@link Style} object of this {@link DrawElement}.
	 */
	private Style style;

	// CONSTRUCTORS

	// METHODS

	// GETTERS AND SETTERS

	/**
	 * Returns the {@link Style} element of this {@link DrawElement}.
	 *
	 * @return the style
	 */
	public Style getStyle() {
		return style;
	}

	/**
	 * Sets the {@link Style} element of this {@link DrawElement}.
	 *
	 * @param style
	 *            the style to set
	 */
	public void setStyle(Style style) {
		this.style = style;
	}

	// OTHERS
}
