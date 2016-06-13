package tets;

import java.util.ArrayList;

import coordinateSystems.EPSG25832;
import geo.BoundingBox;
import geo.Point2D;
import iText.WebServicePDF;
import mapContent.layers.WmsLayer;
import resources.PdfPageSize;
import resources.ServerVersion;

/**
 * Runnable class to test the creation of a {@link WebServicePDF}.
 * 
 * @author DaGri
 * @since 13.05.2016
 *
 */
public class WebServicePDF_WMS_Test {

	/**
	 * Runable main method.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO : BOUNDINGBOX HIER UEBERGEBEN
		WebServicePDF pdf = new WebServicePDF(PdfPageSize.DinA0);

		EPSG25832 epsg = new EPSG25832();

//		BoundingBox bbox = new BoundingBox(new Point2D(5712125, 374676, epsg),
//				new Point2D(5712125 + 500, 374676 + 800, epsg), epsg);
		// BoundingBox bbox = new BoundingBox(new Point2D(1000, 1000, epsg),
		// new Point2D(1000 + 500, 1000 + 500, epsg), epsg);
		BoundingBox bbox = new BoundingBox(new Point2D(5712125, 374676, epsg),
				new Point2D(5712125 + 800, 374676 + 500, epsg), epsg);

		pdf.setMasterBbox(bbox);

		ArrayList<String> layer = new ArrayList<String>();
		layer.add("nw_dtk10_col");

		WmsLayer layer1 = new WmsLayer(bbox, "www.wms.nrw.de/geobasis/wms_nw_dtk10?", layer, ServerVersion.v_1_3_0, 100,
				30, pdf.getPageWidth(), pdf.getPageHeight());

		pdf.addLayer(layer1);

		pdf.createPDF();
	}
}
