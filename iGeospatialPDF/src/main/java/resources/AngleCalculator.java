package resources;

/**
 * Class to provide support with the angle values for turning the PDFs.
 * 
 * @author DaGri
 * @since 14.06.2016
 */
public class AngleCalculator {

	// ATTRIBUTES

	/**
	 * The instance of this {@link AngleCalculator}.
	 */
	private static AngleCalculator instance;

	// A full circle in radiant is Math.PI() * 2. In degrees a full circle
	// consists of 360 parts. In gon the full circle is splitted in 400 parts.

	// CONSTRUCTORS

	/**
	 * Private constructor for an {@link AngleCalculator}.
	 */
	private AngleCalculator() {
		// NOTHING
	};

	// METHODS

	/**
	 * Converts the DEGREE value to a radiant value that can be used for the
	 * turning of the PDF content.
	 *
	 * @param gon
	 *            the DEGREE value as {@link Double}
	 * @return rad the DEGREE value converted to radiant
	 */
	public double degreeToRad(double degree) {
		return degree * (Math.PI / 180);
	}

	/**
	 * Converts the GON value to a radiant value that can be used for the
	 * turning of the PDF content.
	 *
	 * @param gon
	 *            the GON value as {@link Double}
	 * @return rad the GON value converted to radiant
	 */
	public double gonToRad(double gon) {
		return gon * (Math.PI / 200);
	}

	// GETTERS AND SETTERS

	/**
	 * Returns the instance of a {@link AngleCalculator}.
	 *
	 * @return the instance as {@link AngleCalculator}
	 */
	public static AngleCalculator getInstance() {
		if (instance == null) {
			instance = new AngleCalculator();
		}
		return instance;
	}

	/**
	 * Sets the the instance of this {@link AngleCalculator}.
	 *
	 * @param instance
	 *            the instance to set
	 */
	public static void setInstance(AngleCalculator instance) {
		AngleCalculator.instance = instance;
	}

	// OTHERS
}
