package draw;

import draw.drawer.Drawer;
import draw.style.Style;

/**
 * Class to collect various {@link DrawElement}s.
 * 
 * @author DaGri
 * @since 03.05.2016
 */
public abstract class DrawCollection {

	// ATTRIBUTES

	/**
	 * The {@link Style} to use to draw the contained elements.
	 */
	private Style style = new Style() {
	};

	/**
	 * The {@link Drawer} used to draw.
	 */
	private Drawer drawer;

	// CONSTRUCTORS

	// METHODS

	// GETTERS AND SETTERS

	/**
	 * Returns the {@link Style}.
	 *
	 * @return the style as {@link Style}
	 */
	public Style getStyle() {
		return style;
	}

	/**
	 * Sets the {@link Style}.
	 *
	 * @param style
	 *            the style to set
	 */
	public void setStyle(Style style) {
		this.style = style;
	}

	/**
	 * Returns the {@link Drawer}.
	 *
	 * @return the drawer as {@link Drawer}
	 */
	public Drawer getDrawer() {
		return drawer;
	}

	/**
	 * Sets the {@link Drawer}.
	 *
	 * @param drawer
	 *            the drawer to set
	 */
	public void setDrawer(Drawer drawer) {
		this.drawer = drawer;
	}

	// OTHERS
}
