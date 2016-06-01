package draw.style;

import java.awt.Color;

import draw.DrawElement;
import draw.Icon;
import geo.LineString;
import geo.Point2D;
import geo.Polygon;
import iText.WebServicePDF;

/**
 * Class to define and manage the possible styles of the {@link DrawElement}s
 * that can be displayed in an {@link WebServicePDF}.
 * 
 * {@link Point2D} border {@link Color} = BLACK {@link Point2D} fill
 * {@link Color} = BLACK {@link Point2D} radius = 1 {@link Point2D} line weight
 * = 1 {@link Point2D} filled = true {@link Point2D} {@link Icon} = null
 * 
 * {@link LineString} border {@link Color} = BLACK {@link LineString} line
 * weight = 1
 * 
 * {@link Polygon} border {@link Color} = BLACK {@link Polygon} fill
 * {@link Color} = BLACK {@link Polygon} line weight = 1
 * 
 * @author DaGri
 * @since 12.05.2016
 */
public class WebServiceStyle extends Style {

	// ATTRIBUTES

	/**
	 * The border {@link Color} of the {@link Point2D}s.
	 */
	private Color point2dBorderColor = Color.BLACK;

	/**
	 * The {@link LineString}s {@link Color}.
	 */
	private Color lineStringColor = Color.BLACK;

	/**
	 * The border {@link Color} of the {@link Polygon}s.
	 */
	private Color polygonBorderColor = Color.BLACK;

	/**
	 * The fill {@link Color} of the {@link Point2D}s.
	 */
	private Color point2dFillColor = Color.BLACK;

	/**
	 * The fill {@link Color} of the {@link Polygon}s.
	 */
	private Color polygonFillColor = Color.BLACK;

	/**
	 * The line weight of the {@link Point2D}s.
	 */
	private float point2dLineweight = 1f;

	/**
	 * The line weight of the {@link LineString}s.
	 */
	private float lineStringLineweight = 1f;

	/**
	 * The line weight of the {@link Polygon}s.
	 */
	private float polygonLineweight = 1f;

	/**
	 * The {@link Boolean} that indicates if the {@link Point2D}s are filled.
	 */
	private boolean point2dFilled = true;

	/**
	 * The {@link Boolean} that indicates if the {@link Polygon}s are filled.
	 */
	private boolean polygonFilled = false;

	/**
	 * The {@link Icon} used for the {@link Point2D}s.
	 */
	private Icon pointIcon = null;

	/**
	 * The radius of the {@link Point2D}s.
	 */
	private int pointRadius = 1;

	// CONSTRUCTORS

	/**
	 * Empty constructor for a {@link WebServiceStyle}. The default values will
	 * be used:
	 * 
	 * {@link Point2D} border {@link Color} = BLACK {@link Point2D} fill
	 * {@link Color} = BLACK {@link Point2D} radius = 1 {@link Point2D} line
	 * weight = 1 {@link Point2D} filled = true {@link Point2D} {@link Icon} =
	 * null
	 * 
	 * {@link LineString} border {@link Color} = BLACK {@link LineString} line
	 * weight = 1
	 * 
	 * {@link Polygon} border {@link Color} = BLACK {@link Polygon} fill
	 * {@link Color} = BLACK {@link Polygon} line weight = 1
	 */
	public WebServiceStyle() {
		super();
	}

	/**
	 * Constructor for a {@link WebServiceStyle} using all input options.
	 * 
	 * @param point2dBorderColor
	 *            the border {@link Color} of the {@link Point2D}s
	 * @param lineStringColor
	 *            the {@link Color} of the {@link LineString}s
	 * @param polygonBorderColor
	 *            the border color of the {@link Polygon}s
	 * @param point2dFillColor
	 *            the fill {@link Color} of the {@link Point2D}s
	 * @param polygonFillColor
	 *            the fill {@link Color} of the {@link Polygon}s
	 * @param point2dLineweight
	 *            the line weight of the {@link Point2D}s
	 * @param lineStringLineweight
	 *            the line weight of the {@link LineString}
	 * @param polygonLineweight
	 *            the line weight of the {@link Polygon}s
	 * @param point2dFilled
	 *            the {@link Boolean} filled value of the {@link Point2D}s
	 * @param polygonFilled
	 *            filled value of the {@link Polygon}s
	 * @param pointIcon
	 *            the {@link Icon} of the {@link Point2D}s
	 * @param pointRadius
	 *            the {@link Integer} radius of the {@link Point2D}s
	 */
	public WebServiceStyle(Color point2dBorderColor, Color lineStringColor, Color polygonBorderColor,
			Color point2dFillColor, Color polygonFillColor, float point2dLineweight, float lineStringLineweight,
			float polygonLineweight, boolean point2dFilled, boolean polygonFilled, Icon pointIcon, int pointRadius) {
		super();
		this.point2dBorderColor = point2dBorderColor;
		this.lineStringColor = lineStringColor;
		this.polygonBorderColor = polygonBorderColor;
		this.point2dFillColor = point2dFillColor;
		this.polygonFillColor = polygonFillColor;
		this.point2dLineweight = point2dLineweight;
		this.lineStringLineweight = lineStringLineweight;
		this.polygonLineweight = polygonLineweight;
		this.point2dFilled = point2dFilled;
		this.polygonFilled = polygonFilled;
		this.pointIcon = pointIcon;
		this.pointRadius = pointRadius;
	}

	// METHODS

	/**
	 * Sets the values for displaying the {@link Point2D}s.
	 *
	 * @param pointRadius
	 *            the {@link Integer} radius of the {@link Point2D}s
	 * @param pointIcon
	 *            the {@link Icon} of the {@link Point2D}s
	 * @param point2dFilled
	 *            the {@link Boolean} filled value of the {@link Point2D}s
	 * @param point2dLineweight
	 *            line weight of the {@link Point2D}s
	 * @param point2dBorderColor
	 *            the border {@link Color} of the {@link Point2D}s
	 * @param point2dFillColor
	 *            the fill {@link Color} of the {@link Point2D}s
	 */
	public void setPointAppearance(int pointRadius, Icon pointIcon, boolean point2dFilled, float point2dLineweight,
			Color point2dBorderColor, Color point2dFillColor) {
		this.point2dBorderColor = point2dBorderColor;
		this.point2dFillColor = point2dFillColor;
		this.point2dLineweight = point2dLineweight;
		this.point2dFilled = point2dFilled;
		this.pointIcon = pointIcon;
		this.pointRadius = pointRadius;
	};

	/**
	 * Sets the values for displaying the {@link LineString}s.
	 *
	 * @param lineStringColor
	 *            the {@link LineString}s {@link Color}
	 * @param lineStringLineweight
	 *            the {@link LineString}s line weight
	 */
	public void setLineStringAppearance(Color lineStringColor, float lineStringLineweight) {
		this.lineStringColor = lineStringColor;
		this.lineStringLineweight = lineStringLineweight;
	};

	/**
	 * Sets the values for displaying the {@link Polygon}s.
	 *
	 * @param polygonBorderColor
	 *            the {@link Polygon}s border {@link Color}
	 * @param polygonFillColor
	 *            the {@link Polygon}s fill {@link Color}
	 * @param polygonLineweight
	 *            the {@link Polygon}s line weight
	 * @param polygonFilled
	 *            the {@link Polygon}s filled value
	 */
	public void setPolygonAppearance(Color polygonBorderColor, Color polygonFillColor, float polygonLineweight,
			boolean polygonFilled) {
		this.setPolygonBorderColor(polygonBorderColor);
		this.setPolygonFillColor(polygonFillColor);
		this.setPolygonLineweight(polygonLineweight);
		this.setPolygonFilled(polygonFilled);
	}

	// GETTERS AND SETTERS

	/**
	 * Returns the border {@link Color} of the {@link Point2D}s as {@link Color}
	 * .
	 *
	 * @return the point2dBorderColor as {@link Color}
	 */
	public Color getPoint2dBorderColor() {
		return point2dBorderColor;
	}

	/**
	 * Sets the border {@link Color} of the {@link Point2D}s.
	 *
	 * @param point2dBorderColor
	 *            the point2dBorderColor to set
	 */
	public void setPoint2dBorderColor(Color point2dBorderColor) {
		if (point2dBorderColor != null)
			this.point2dBorderColor = point2dBorderColor;
	}

	/**
	 * Returns the {@link LineString}s {@link Color} as {@link Color}.
	 *
	 * @return the lineStringColor as {@link Color}
	 */
	public Color getLineStringColor() {
		return lineStringColor;
	}

	/**
	 * Sets the {@link LineString}s {@link Color}.
	 *
	 * @param lineStringColor
	 *            the lineStringColor to set
	 */
	public void setLineStringColor(Color lineStringColor) {
		if (lineStringColor != null)
			this.lineStringColor = lineStringColor;
	}

	/**
	 * Returns the {@link Polygon}s border {@link Color} as {@link Color}.
	 *
	 * @return the polygonBorderColor as {@link Color}
	 */
	public Color getPolygonBorderColor() {
		return polygonBorderColor;
	}

	/**
	 * Sets the {@link Polygon}s border {@link Color}.
	 *
	 * @param polygonBorderColor
	 *            the polygonBorderColor to set
	 */
	public void setPolygonBorderColor(Color polygonBorderColor) {
		if (polygonBorderColor != null)
			this.polygonBorderColor = polygonBorderColor;
	}

	/**
	 * Returns the fill {@link Color} of the {@link Point2D}s as {@link Color}.
	 *
	 * @return the point2dFillColor as {@link Color}
	 */
	public Color getPoint2dFillColor() {
		return point2dFillColor;
	}

	/**
	 * Sets the fill {@link Color} of the {@link Point2D}s.
	 *
	 * @param point2dFillColor
	 *            the point2dFillColor to set
	 */
	public void setPoint2dFillColor(Color point2dFillColor) {
		if (point2dFillColor != null)
			this.point2dFillColor = point2dFillColor;
	}

	/**
	 * Returns the {@link Polygon}s filled {@link Color} as {@link Color}.
	 *
	 * @return the polygonFillColor as {@link Color}
	 */
	public Color getPolygonFillColor() {
		return polygonFillColor;
	}

	/**
	 * Sets the {@link Polygon}s fill {@link Color}.
	 *
	 * @param polygonFillColor
	 *            the polygonFillColor to set
	 */
	public void setPolygonFillColor(Color polygonFillColor) {
		if (polygonFillColor != null)
			this.polygonFillColor = polygonFillColor;
	}

	/**
	 * Returns the line weight of the {@link Point2D}s as {@link Float}.
	 *
	 * @return the point2dLineweight as {@link Float}
	 */
	public float getPoint2dLineweight() {
		return point2dLineweight;
	}

	/**
	 * Sets the line weight of the {@link Point2D}s.
	 *
	 * @param point2dLineweight
	 *            the point2dLineweight to set
	 */
	public void setPoint2dLineweight(float point2dLineweight) {
		this.point2dLineweight = point2dLineweight;
	}

	/**
	 * Returns the line weight of the {@link LineString}s as {@link Float}.
	 *
	 * @return the lineStringLineweight as {@link Float}
	 */
	public float getLineStringLineweight() {
		return lineStringLineweight;
	}

	/**
	 * Sets the line weight of the {@link LineString}s.
	 *
	 * @param lineStringLineweight
	 *            the lineStringLineweight to set
	 */
	public void setLineStringLineweight(float lineStringLineweight) {
		this.lineStringLineweight = lineStringLineweight;
	}

	/**
	 * Returns the line weight of the {@link Polygon}s as {@link Float}.
	 *
	 * @return the polygonLineweight as {@link Float}
	 */
	public float getPolygonLineweight() {
		return polygonLineweight;
	}

	/**
	 * Sets the line weight of the {@link Polygon}s.
	 *
	 * @param polygonLineweight
	 *            the polygonLineweight to set
	 */
	public void setPolygonLineweight(float polygonLineweight) {
		this.polygonLineweight = polygonLineweight;
	}

	/**
	 * Returns the {@link Boolean} filled value of the {@link Point2D}s as
	 * {@link Boolean}.
	 *
	 * @return the point2dFilled as {@link Boolean}
	 */
	public boolean isPoint2dFilled() {
		return point2dFilled;
	}

	/**
	 * Sets the {@link Boolean} filled value of the {@link Point2D}s.
	 *
	 * @param point2dFilled
	 *            the point2dFilled to set
	 */
	public void setPoint2dFilled(boolean point2dFilled) {
		this.point2dFilled = point2dFilled;
	}

	/**
	 * Returns the {@link Boolean} filled value of the {@link Polygon}s as
	 * {@link Boolean}.
	 *
	 * @return the polygonFilled as {@link Boolean}
	 */
	public boolean isPolygonFilled() {
		return polygonFilled;
	}

	/**
	 * Sets the {@link Boolean} filled value of the {@link Polygon}s.
	 *
	 * @param polygonFilled
	 *            the polygonFilled to set
	 */
	public void setPolygonFilled(boolean polygonFilled) {
		this.polygonFilled = polygonFilled;
	}

	/**
	 * Returns the {@link Icon} of the {@link Point2D}s as {@link Icon}.
	 *
	 * @return the pointIcon as {@link Icon}
	 */
	public Icon getPointIcon() {
		return pointIcon;
	}

	/**
	 * Sets the {@link Icon} of the {@link Point2D}s.
	 *
	 * @param pointIcon
	 *            the pointIcon to set
	 */
	public void setPointIcon(Icon pointIcon) {
		this.pointIcon = pointIcon;
	}

	/**
	 * Returns the radius of the {@link Point2D}s as {@link Integer}.
	 *
	 * @return the pointRadius as {@link Integer}
	 */
	public int getPointRadius() {
		return pointRadius;
	}

	/**
	 * Sets the radius of the {@link Point2D}s.
	 *
	 * @param pointRadius
	 *            the pointRadius to set
	 */
	public void setPointRadius(int pointRadius) {
		this.pointRadius = pointRadius;
	};

	// OTHERS
}
