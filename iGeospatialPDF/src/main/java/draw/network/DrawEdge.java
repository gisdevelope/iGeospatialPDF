package draw.network;

import draw.DrawElement;
import network.Edge;

/**
 * Class to represent a PDF-printable {@link Edge}.
 * 
 * @author DaGri
 * @since 12.05.2016
 *
 */
public class DrawEdge extends DrawElement {

	// ATTRIBUTES

	// CONSTRUCTORS

	// METHODS

	/*
	 * (non-Javadoc)
	 * 
	 * @see draw.DrawElement#reduce(double, double)
	 */
	@Override
	public void reduce(double northing, double easting) {
		// TODO : REDUCE FUNKTION DER DRAWEDGE IMPLEMENTIEREN

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see draw.DrawElement#scale(double)
	 */
	@Override
	public void scale(double factor) {
		// TODO : SCALE FUNKTION DER DRAW EDGE IMPLEMENTIEREN

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see draw.DrawElement#turn(double, double, double)
	 */
	@Override
	protected void turn(double angle) {
		// TODO : TURN FUNKTION DER DRAW EDGE IMPLEMENTIEREN

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see draw.DrawElement#convertToPdfSystem()
	 */
	@Override
	public void convertToPdfSystem() {
		// TODO : CONVERT TO PDF SYSTEM DER DRAW EDGE IMPLEMENTIEREN
	}

	// GETTERS AND SETTERS

	// OTHERS
}
