package coordinateSystems;

/**
 * Class that represents the coordinate system with the EPSG-code 25832.
 * 
 * @author DaGri
 * @since 03.05.2016
 *
 */
public class EPSG25832 extends CoordinateSystem {

	// ATTRIBUTES

	// CONSTRUCTORS

	/**
	 * Constructor for a EPSG:25832 {@link CoordinateSystem}.
	 *
	 */
	public EPSG25832() {
		super();
		this.setEPSG(25832);
		this.setWKT(
				//@formatter:off
				"PROJCS["
				+ "\"ETRS89 / UTM zone 32N\","
				+ "GEOGCS[\"ETRS89\","
				+ "DATUM[\"European_Terrestrial_Reference_System_1989\","
				+ "SPHEROID[\"GRS 1980\",6378137,298.257222101,"
				+ "AUTHORITY[\"EPSG\",\"7019\"]],"
				+ "AUTHORITY[\"EPSG\",\"6258\"]],"
				+ "PRIMEM[\"Greenwich\",0,"
				+ "AUTHORITY[\"EPSG\",\"8901\"]],"
				+ "UNIT[\"degree\",0.01745329251994328,"
				+ "AUTHORITY[\"EPSG\",\"9122\"]],"
				+ "AUTHORITY[\"EPSG\",\"4258\"]],"
				+ "UNIT[\"metre\",1,"
				+ "AUTHORITY[\"EPSG\",\"9001\"]],"
				+ "PROJECTION[\"Transverse_Mercator\"],"
				+ "PARAMETER[\"latitude_of_origin\",0],"
				+ "PARAMETER[\"central_meridian\",9],"
				+ "PARAMETER[\"scale_factor\",0.9996],"
				+ "PARAMETER[\"false_easting\",500000],"
				+ "PARAMETER[\"false_northing\",0],"
				+ "AUTHORITY[\"EPSG\",\"25832\"],"
				+ "AXIS[\"Easting\",EAST],AXIS[\"Northing\",NORTH]]");
		//@formatter:on
	}

	// METHODS

	// GETTERS AND SETTERS

	// OTHERS
}