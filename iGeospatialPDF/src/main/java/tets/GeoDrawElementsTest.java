package tets;

import java.awt.Color;
import java.util.ArrayList;

import coordinateSystems.EPSG25832;
import draw.style.WebServiceStyle;
import geo.BoundingBox;
import geo.LineString;
import geo.Point2D;
import geo.Polygon;
import iText.WebServicePDF;
import mapContent.WfsLayer;
import resources.PdfPageSize;

/**
 * Class to test the drawing of geo-objects.
 * 
 * @author DaGri
 * @since 05.06.2016
 *
 */
public class GeoDrawElementsTest {

	public static void main(String[] args) {
		WebServicePDF pdf = new WebServicePDF(PdfPageSize.DinA0);

		EPSG25832 epsg = new EPSG25832();

		BoundingBox bbox = new BoundingBox(new Point2D(0, 0, epsg), new Point2D(1000, 1000, epsg), epsg);

		pdf.setBbox(bbox);
		
		WebServiceStyle style = new WebServiceStyle(Color.BLACK, Color.BLUE, Color.CYAN, Color.BLACK, Color.CYAN, 1, 1,
				1, false, false, null, 50, null);

		WfsLayer layer1 = new WfsLayer(bbox);
		layer1.setStyle(style);

		Point2D p1 = new Point2D(500, 500, new EPSG25832());
		layer1.addGeometry(p1);

		Point2D p2 = new Point2D(550, 550, new EPSG25832());
		Point2D p3 = new Point2D(560, 560, new EPSG25832());
		Point2D p4 = new Point2D(570, 570, new EPSG25832());
		Point2D p5 = new Point2D(580, 580, new EPSG25832());
		ArrayList<Point2D> lineList = new ArrayList<>();
		lineList.add(p2);
		lineList.add(p3);
		lineList.add(p4);
		lineList.add(p5);
		LineString l1 = new LineString(lineList);
		layer1.addGeometry(l1);

		Point2D p6 = new Point2D(650, 650, new EPSG25832());
		Point2D p7 = new Point2D(760, 760, new EPSG25832());
		Point2D p8 = new Point2D(570, 570, new EPSG25832());
		Point2D p9 = new Point2D(580, 780, new EPSG25832());
		ArrayList<Point2D> polyList = new ArrayList<>();
		polyList.add(p1);
		polyList.add(p6);
		polyList.add(p7);
		polyList.add(p8);
		polyList.add(p9);

		Polygon poly = new Polygon(polyList);
		layer1.addGeometry(poly);

		pdf.addLayer(layer1);
		pdf.createPDF();

	}
}
