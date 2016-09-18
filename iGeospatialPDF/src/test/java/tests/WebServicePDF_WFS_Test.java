package tests;

import java.awt.Color;
import java.util.ArrayList;

import coordinateSystems.EPSG25832;
import draw.style.WebServiceStyle;
import geo.BoundingBox;
import geo.Point2D;
import iText.WebServicePDF;
import mapContent.layers.WfsLayer;
import resources.PdfPageSize;
import resources.PointAppearance;
import resources.ServerVersion;

/**
 * Class to test the {@link WebServicePDF}s.
 * 
 * @author DaGri
 * @since 11.06.2016
 *
 */
public class WebServicePDF_WFS_Test {

	public static void main(String[] args) {
		// TODO : BOUNDINGBOX HIER UEBERGEBEN
		EPSG25832 epsg = new EPSG25832();
		BoundingBox bbox = new BoundingBox(new Point2D(5712125, 374676, epsg),
				new Point2D(5712125 + 800, 374676 + 500, epsg), epsg);
		
		WebServicePDF pdf = new WebServicePDF(PdfPageSize.DinA0, bbox);

		String link = "http://www.wms.nrw.de/wms/strassen_nrw_wfs?";

//		pdf.setMasterBbox(bbox);

		ArrayList<String> layers = new ArrayList<>();
		layers.add("strassen_nrw_strassen_nrw_opendata:Bauwerke");
		
		ArrayList<Integer> maxFeatures = new ArrayList<>();
		maxFeatures.add(1000);
		
		WebServiceStyle style = new WebServiceStyle();
		style.setPointAppearance(200, null, true, 20, Color.CYAN, Color.RED, PointAppearance.X);

		WfsLayer layer1 = new WfsLayer(link, bbox, ServerVersion.WFS_V_1_0_0, layers, maxFeatures, style);

		pdf.addLayer(layer1);

		pdf.createPDF();
	}

}
