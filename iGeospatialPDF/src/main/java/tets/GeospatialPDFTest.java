package tets;

import java.util.ArrayList;

import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;

import coordinateSystems.EPSG25832;
import geo.BoundingBox;
import geo.Point2D;
import iText.WebServicePDF;
import mapContent.WmsLayer;
import resources.ServerVersion;

/**
 * Runnable class to test the creation of a {@link WebServicePDF}.
 * 
 * @author DaGri
 * @since 13.05.2016
 *
 */
public class GeospatialPDFTest {

	/**
	 * Runable main method.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		WebServicePDF pdf = new WebServicePDF(new Rectangle(1000, 1000));
//		 WebServicePDF pdf = new WebServicePDF(PageSize.A0);

		EPSG25832 epsg = new EPSG25832();

		BoundingBox bbox = new BoundingBox(new Point2D(5712125, 374676, epsg),
				new Point2D(5712125 + 500, 374676 + 500, epsg), epsg);
//		BoundingBox bbox = new BoundingBox(new Point2D(1000, 1000, epsg),
//				new Point2D(1000 + 500, 1000 + 500, epsg), epsg);

		ArrayList<String> layer = new ArrayList<String>();
		layer.add("nw_dtk10_col");

		WmsLayer layer1 = new WmsLayer(bbox, "www.wms.nrw.de/geobasis/wms_nw_dtk10?", layer, ServerVersion.v_1_3_0, 100,
				100, pdf.getPageSize());

		pdf.addLayer(layer1);

		pdf.createPDF();

	}
}
