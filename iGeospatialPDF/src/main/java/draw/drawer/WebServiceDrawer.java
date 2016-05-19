package draw.drawer;

import java.util.ArrayList;

import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfStructureElement;

import draw.DrawCollection;
import draw.DrawElement;
import draw.geo.DrawLineString;
import draw.geo.DrawPoint;
import draw.geo.DrawPolygon;
import iText.GeospatialPDF;

/**
 * Class to draw the {@link DrawElement}s that are used in an
 * {@link GeospatialPDF}.
 * 
 * @author DaGri
 * @since 09.05.2016
 */
public class WebServiceDrawer extends Drawer {

	// ATTRIBUTES

	// CONSTRUCTORS
	
	/**
	 * Constructor for TODO
	 */
	public WebServiceDrawer(){
		super();
	}

	/**
	 * Constructor for a {@link WebServiceDrawer} using a {@link PdfContentByte}
	 * and a {@link PdfStructureElement} to draw tagged {@link DrawElement}s
	 * directly into the structure of a PDF file.
	 * 
	 * @param contByte
	 *            the {@link PdfContentByte} to use
	 * @param top
	 *            the {@link PdfStructureElement} to use
	 */
	public WebServiceDrawer(PdfContentByte contByte, PdfStructureElement top) {
		super(contByte, top);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see draw.drawer.Drawer#drawAll(draw.DrawCollection)
	 */
	@Override
	public void drawAll(DrawCollection dc) {
		this.drawPolygons(dc.getPolygons());
		this.drawLineStrings(dc.getLinestrings());
		this.drawPoints(dc.getPoints());

	}

	// METHODS

	/**
	 * Draws a {@link DrawPoint} into the {@link GeospatialPDF}.
	 *
	 * @param p
	 *            the {@link DrawPoint} to draw
	 */
	private void drawPoint2D(DrawPoint p) {
	}

	/**
	 * Draws an {@link ArrayList} of {@link DrawPoint} into the
	 * {@link GeospatialPDF}.
	 *
	 * @param points
	 *            the {@link ArrayList} of {@link DrawPoint}s to draw
	 */
	private void drawPoints(ArrayList<DrawPoint> points) {
		for (int a = 0; a < points.size(); a++) {
			this.drawPoint2D(points.get(a));
		}
	}

	/**
	 * Draws a {@link DrawLineString} into the {@link GeospatialPDF}.
	 *
	 * @param ls
	 *            the {@link DrawLineString} to draw
	 */
	private void drawLineString(DrawLineString ls) {
	}

	/**
	 * Draws a {@link DrawLineString} into the {@link GeospatialPDF}.
	 *
	 * @param lineStrings
	 *            the {@link ArrayList} of {@link DrawLineString}s to draw
	 */
	private void drawLineStrings(ArrayList<DrawLineString> lineStrings) {
		for (int a = 0; a < lineStrings.size(); a++) {
			this.drawLineString(lineStrings.get(a));
		}
	}

	/**
	 * Draws a {@link DrawPolygon} into the {@link GeospatialPDF}.
	 *
	 * @param p
	 *            the {@link DrawPoint} to draw
	 */
	private void drawPolygon(DrawPolygon p) {
	}

	/**
	 * Draws an {@link ArrayList} of {@link DrawPolygon}s into a
	 * {@link GeospatialPDF}.
	 *
	 * @param polygons
	 *            the {@link ArrayList} of {@link DrawPolygon}s to draw
	 */
	private void drawPolygons(ArrayList<DrawPolygon> polygons) {
		for (int a = 0; a < polygons.size(); a++) {
			this.drawPolygon(polygons.get(a));
		}
	}

	// GETTERS AND SETTERS

	// OTHERS
}
