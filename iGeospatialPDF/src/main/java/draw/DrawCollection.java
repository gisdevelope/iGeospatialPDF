package draw;

import draw.drawer.Drawer;
import draw.style.Style;

/**
 * Class to collect various {@link DrawElement}s.
 * 
 * The {@link DrawCollection} does not know how to (1) reduce (2) turn or (3)
 * scale the internal Data but in the prepareData(...) method it has to call the
 * methods of the {@link DrawElement}s to do so.
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

	/**
	 * Prepares the stored data for displaying: Reduces, turns and scales the
	 * data.
	 * 
	 * The {@link DrawCollection} does not need to know how exactly the
	 * {@link DrawElement}s will be reduced, turned or scaled. This functions
	 * are provided inside the {@link DrawElement}s, so you only need to call
	 * them, when implementing this method.
	 * 
	 * @param angle
	 *            the angle to turn about in radiant
	 * @param factor
	 *            the factor to scale with
	 * @param northing
	 *            the northing to reduce about
	 * @param easting
	 *            the easting to reduce about
	 */
	public abstract void prepareData(double northingRed, double eastingRed, double angle, double factor);

	/**
	 * Abstract method that shall add a {@link DrawElement} de to the internal
	 * stores of {@link DrawElement}s. Returns true if the {@link DrawElement}
	 * could be added, false if not.
	 *
	 * @param e
	 *            the {@link DrawElement} to add
	 * @return true if the {@link DrawElement} could be added, false if not
	 */
	public abstract boolean addDrawElement(DrawElement de);

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
