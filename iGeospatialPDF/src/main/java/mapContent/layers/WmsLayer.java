package mapContent.layers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import draw.drawer.WebServiceDrawer;
import geo.BoundingBox;
import geo.Point2D;
import iText.GeospatialPDF;
import resources.ServerVersion;
import resources.Tile;
import resources.TileArray;

/**
 * Class to represent a {@link WmsLayer} - a layer requested from a Web Map
 * Service (WMS).
 * 
 * @author DaGri
 * @since 03.05.2016
 *
 */
public class WmsLayer extends MapLayer {

	// ATTRIBUTES

	/**
	 * The {@link BufferedImage} of this {@link WmsLayer}, that stores the map
	 * image to display in the PDF file.
	 */
	private BufferedImage mapImage;

	/**
	 * The dots per inch (DPI) value for the map image of this {@link WmsLayer}.
	 */
	private int dpi;

	/**
	 * The {@link TileArray} that stores the received {@link Tile}s.
	 */
	private TileArray tileArray;

	/**
	 * The link to the web map service (WMS) server including the layer(s) to
	 * request.
	 */
	private String link;

	/**
	 * The version of the server to request as {@link ServerVersion}.
	 */
	private ServerVersion version;

	/**
	 * The desired opacity in percent (between 0 and 100) as {@link Integer}
	 * value.
	 */
	private int opacity;

	/**
	 * The width of the {@link BufferedImage} of this {@link WmsLayer}.
	 */
	private int imageWidth;

	/**
	 * The height of the {@link BufferedImage} of this {@link WmsLayer}.
	 */
	private int imageHeight;

	/**
	 * The layers to request as {@link ArrayList} of {@link String}s.
	 */
	private ArrayList<String> layers = new ArrayList<>();

	// CONSTRUCTORS

	/**
	 * Constructor for a {@link WmsLayer} using various inputs for creating the
	 * {@link WmsLayer}s {@link BufferedImage}.
	 * 
	 * @param bbox
	 *            the {@link BoundingBox}
	 * @param link
	 *            the link to the Web Map Server (WMS)
	 * @param layers
	 *            the {@link ArrayList} of layers as {@link String}s
	 * @param version
	 *            the server version to request as {@link ServerVersion}
	 * @param dpi
	 *            the desired Dots Per Inch of the map image
	 * @param opacity
	 *            the opacity of the map image
	 * @param pageWidth
	 *            the width of the {@link GeospatialPDF} the layer shall be
	 *            displayed in
	 * @param pageHeight
	 *            the height of the {@link GeospatialPDF} the layer shall be
	 *            displayed in
	 */
	public WmsLayer(BoundingBox bbox, String link, ArrayList<String> layers, ServerVersion version, int dpi,
			int opacity, float pageWidth, float pageHeight) {
		super(bbox);
		LOG = Logger.getLogger(this.getClass().getCanonicalName());
		LOG.setLevel(Level.SEVERE);
		// SET THE DRAWER FOR THIS WMSLAYER: A WEBSERVICEDRAWER
		this.setDrawer(new WebServiceDrawer());
		this.setLink(link);
		this.setVersion(version);
		this.setDpi(dpi);
		// CATCH IF OPACITY IS SMALLER THAN 0 OR LARGER THAN 100
		if (opacity < 0)
			opacity = 0;
		if (opacity > 100)
			opacity = 100;
		this.setOpacity(opacity);

		// BOUNDINGBOX WIDTH IS LARGER THAN THE HEIGHT
		if (this.getBbox().getWidthGeo() >= this.getBbox().getHeightGeo()) {
			// SET THE DESIRED WIDTH OF THE IMAGE
			this.setImageWidth((int) (pageWidth * dpi));
			// SET THE DESIRED HEIGHT BY THE RELATION OF THE BOUNDGINGBOX WIDTH
			// AND HEIGHT
			double relation = this.getBbox().getHeightGeo() / this.getBbox().getWidthGeo();
			this.setImageHeight((int) (this.getImageWidth() * relation));
		} else
		// ELSE THE BOUNDINGBOX HEIGHT IS LARGER THAN THE WIDTH
		{
			// SET THE DESIRED HEIGHT OF THE IMAGE
			this.setImageHeight((int) (pageHeight * dpi));
			// SET THE DESIRED HEIGHT BY THE RELATION OF THE BOUNDGINGBOX WIDTH
			// AND HEIGHT
			double relation = this.getBbox().getWidthGeo() / this.getBbox().getHeightGeo();
			this.setImageWidth((int) (this.getImageHeight() * relation));
		}
		// ELSE THE BOUNDINGBOX IS EQUAL IN LENGTH AND HEIGHT : FIRST IF WILL DO

		this.setLayers(layers);
	}

	// METHODS

	/*
	 * (non-Javadoc)
	 * 
	 * @see mapContent.MapLayer#receive()
	 */
	@Override
	public void receive() {
		// FILL THE TILE ARRAY WITH IMAGES
		this.fillTileArray();
		// CALCULATE THE COMPLETE IMAGE
		this.calcImage();

		// WRITE THE COMPLETE IMAGE TO THE FILE SYSTEM FOR DEBUGING
		// this.putOutImage("string");
	}

	/**
	 * Saves an image to the file system.
	 * 
	 * @param buff
	 *            the {@link BufferedImage} to save to the file system
	 */
	@SuppressWarnings("unused")
	private void putOutImage(BufferedImage buff) {
		try {
			File outputfile = new File("output/" + System.currentTimeMillis() + ".png");
			ImageIO.write(buff, "png", outputfile);
		} catch (IOException e) {
			LOG.severe("COULD NOT PUT OUT IMAGE TO THE FILE SYSTEM!");
		}

	}

	/**
	 * Calculates the combined image from the images {@link Tile}s of the
	 * {@link TileArray}.
	 */
	private void calcImage() {

		int overAllImagePixelsX = 0;
		int overAllImagePixelsY = 0;

		// RUNS FROM THE TOP TO THE BOTTOM
		for (int a = 0; a < this.getTileArray().getRows(); a++) {
			overAllImagePixelsY = overAllImagePixelsY + this.getTileArray().getTiles()[0][a].getImageHeight();
		}

		// RUNS FROM THE LEFT TO THE RIGHT
		for (int a = 0; a < this.getTileArray().getColumns(); a++) {
			overAllImagePixelsX = overAllImagePixelsX + this.getTileArray().getTiles()[a][0].getImageWidth();
		}

		// NOW THE COMBINED WIDTH AND HEIGHT OF THE IMAGE TO CREATE IS STORED IN
		// THE VARIABLES
		// CREATE IMAGE TO PUT THE TILE IMAGES INSIDE
		BufferedImage ergImg = new BufferedImage(this.getImageWidth(), this.getImageHeight(),
				BufferedImage.TYPE_INT_ARGB);

		int actWidth = 0;
		int actHeight = 0;

		// IN Y DIRECTION
		for (int y = 0; y < this.getTileArray().getRows(); y++) {
			// IN X DIRECTION
			actWidth = 0;
			for (int x = 0; x < this.getTileArray().getColumns(); x++) {

				// DRAW TILE INTO THE COMPLETE IMAGE
				ergImg.createGraphics().drawImage(this.getTileArray().getTiles()[x][y].getTileImage(), actWidth,
						actHeight, null);

				// COUNT ON THE ACTUAL WIDTH
				actWidth = actWidth + this.getTileArray().getTiles()[x][y].getImageWidth();

				// TRYING TO SAVE HEAP SPACE
				// this.getTileArray().getTiles()[x][y].setTileImage(null);
			}
			actHeight = actHeight + this.getTileArray().getTiles()[0][y].getImageHeight();
		}

		// TRYING TO SAVE HEAP SPACE
		ergImg.flush();
		ergImg.getGraphics().dispose();

		this.setMapImage(ergImg);
	}

	/**
	 * Fills the {@link TileArray} of this {@link WmsLayer} with {@link Tile}s
	 * that already contain their images.
	 */
	private void fillTileArray() {
		// MAXIMUM RECEIVING SIZE PER TILE SHALL BE 1000 X 1000 PIXELS
		int tileArrayWidth = (int) (this.getImageWidth() / 1000.0);
		int tileArrayHeight = (int) (this.getImageHeight() / 1000.0);

		// IF THERE IS ONE MORE TILE NEEDED IN X DIRECTION
		if (this.getImageWidth() % 1000.0 != 0.0) {
			tileArrayWidth++;
		}
		// IF THERE IS ONE MORE TILE NEEDED IN Y DIRECTION
		if (this.getImageHeight() % 1000.0 != 0.0) {
			tileArrayHeight++;
		}

		LOG.info("TILE ARRAY WIDTH=" + tileArrayWidth);
		LOG.info("TILE ARRAY HEIGHT=" + tileArrayHeight);

		this.setTileArray(new TileArray(tileArrayWidth, tileArrayHeight));

		// SPLIT UP BOUNDING BOX TO FIT THE COUNT OF THE TILES IN X- AND Y-
		// DIRECTION
		int standardTileSize = 1000;

		int widthLeft = this.getImageWidth();
		int heightLeft = this.getImageHeight();

		LOG.info("IMAGE WIDTH=" + this.getImageWidth());
		LOG.info("IMAGE HEIGHT=" + this.getImageHeight());

		// THE TIMES A FULL TILE SHALL BE REQUESTED (EG: 2.8 => 2
		// FULL TILES WITH 1000 PIXELS IN THE DIRECTION AND A TILE
		// WITH ONLY 0.8 PERCENT TILE IN THE DIRECTION ARE NEEDED)
		double widthValue = this.getImageWidth() / 1000.0;
		double heightValue = this.getImageHeight() / 1000.0;

		// THE METER VALUES FOR A FULL 1000 X 1000 PIXEL TILE
		double geoValuePerStep = this.getBbox().getWidthGeo() / widthValue;
		// double geoHeightValuePerStep = this.getBbox().getHeightGeo() /
		// heightValue;

		// THE METER VALUES FOR THE ACTUAL STEP
		double actGeoWidthStep = 0;
		double actGeoHeightStep = 0;

		// THE START POINT IS THE UPPER LEFT POINT OF THE BOUNDING BOX
		Point2D actPoint = this.getBbox().getUpLeft();

		double eastingStart = actPoint.getEasting();
		double northingStart = actPoint.getNorthing();

		// THE ACTUAL LONGITUDE AND LATITUDE POSITION (HERE: OF THE START POINT)
		double actNorthing = northingStart;
		double actEastring = eastingStart;

		LOG.info("ACTUAL LAT=" + actNorthing);
		LOG.info("ACTUAL LON=" + actEastring);

		if (heightValue >= 1) {
			LOG.info("NOT YET AT THE RIGHT EDGE");
			// A FULL TILE IN LATITUDE DIRECTION NEEDS TO BE REQUESTED
			// actGeoHeightStep = geoHeightValuePerStep * 1;
			actGeoHeightStep = geoValuePerStep * 1;
			heightValue--;
		} else if (heightValue < 1 && heightValue >= 0) {
			// ONLY A PART OF THE TILE NEEDS TO BE REQUESTED
			LOG.info("REACHED THE RIGHT END");
			// actGeoHeightStep = geoHeightValuePerStep * heightValue;
			actGeoHeightStep = geoValuePerStep * heightValue;
			heightValue = 0;
		}

		actNorthing = actNorthing - actGeoHeightStep;

		// CALCULATE THE GEOGRAPHICAL STEP WIDTH IN TILE-PERCENTS
		if (widthValue >= 1) {
			// A COMPLETE TILE IN LON DIRECTION NEEDS TO BE
			// REQUESTED
			actGeoWidthStep = geoValuePerStep * 1;
			widthValue--;
		} else if (widthValue < 1 && widthValue >= 0) {
			// REACHED THE RIGHT END OF THE IMAGE : ONLY THE METERS
			// STILL LEFT NEED TO BE REQUESTED
			actGeoWidthStep = geoValuePerStep * widthValue;
			widthValue = 0;
		}

		BoundingBox leftStartBox = new BoundingBox(new Point2D(actNorthing, actEastring, this.getBbox().getSystem()),
				new Point2D(actNorthing + actGeoHeightStep, actEastring + actGeoWidthStep, this.getBbox().getSystem()),
				this.getBbox().getSystem());

		for (int h = 0; h < tileArrayHeight; h++) {

			BoundingBox actBbox = leftStartBox;

			for (int w = 0; w < tileArrayWidth; w++) {
				// CREATE NEW TILE
				Tile t = new Tile();

				// IF THE WIDTH LEFT - THE STANDARD TILE SIZE IS MORE THAN 0
				if (widthLeft - standardTileSize > 0) {
					LOG.info("REQUESTING A FULL TILE IN X DIRECTION");
					// UPDATE THE WIDTH LEFT
					widthLeft = widthLeft - standardTileSize;
					// USE THE STANDARD VALUE FOR THE TILE
					t.setImageWidth(standardTileSize);
				} else {
					// ELSE THE WIDTH LEFT IS THE TILE IMAGE SIZE
					t.setImageWidth(widthLeft);
					LOG.info("REQUESTING A TILE WITH A WIDTH OF " + widthLeft);
				}

				// IF THE HEIGHT LEFT - THE STANDARD TILE SIZE IS MORE THAN
				// 0
				if (heightLeft - standardTileSize > 0) {
					LOG.info("REQUESTING A FULL TILE IN Y DIRECTION");
					// USE THE STANDART VALUE FOR THE TILE IMAGE
					t.setImageHeight(standardTileSize);
				} else {
					// ELSE THE HEIGHT LEFT IS THE TILE IMAGE SIZE
					t.setImageHeight(heightLeft);
					LOG.info("REQUESTING A TILE WITH A HEIGHT OF " + widthLeft);
				}

				// SET THE BOUNDINGBOX OF THE ACTUAL TILE
				t.setBbox(actBbox);

				LOG.info("RECEIVING IMAGE...");

				// RECEIVE THE IMAGE FOR THE TILE
				LOG.info("TRYING TO RECEIVE TILE IMAGE...");
				this.receiveImage(t);

				LOG.info("IMAGE RECEIVED!");

				// PUT THE TILE INSIDE THE ARRAY
				this.getTileArray().getTiles()[w][h] = t;

				// CALCULATE THE GEOGRAPHICAL STEP WIDTH IN TILE-PERCENTS
				if (widthValue >= 1) {
					// A COMPLETE TILE IN LON DIRECTION NEEDS TO BE
					// REQUESTED
					actGeoWidthStep = geoValuePerStep * 1;
					widthValue--;
				} else if (widthValue < 1 && widthValue >= 0) {
					// REACHED THE RIGHT END OF THE IMAGE : ONLY THE METERS
					// STILL LEFT NEED TO BE REQUESTED
					actGeoWidthStep = geoValuePerStep * widthValue;
					widthValue = 0;
				}
				actBbox = actBbox.getBBoxRight(actGeoWidthStep);
			}

			widthValue = this.getImageWidth() / 1000.0;

			// CALCULATE THE GEOGRAPHICAL STEP WIDTH IN TILE-PERCENTS
			if (widthValue >= 1) {
				// A COMPLETE TILE IN LON DIRECTION NEEDS TO BE
				// REQUESTED
				actGeoWidthStep = geoValuePerStep * 1;
				widthValue--;
			} else if (widthValue < 1 && widthValue >= 0) {
				// REACHED THE RIGHT END OF THE IMAGE : ONLY THE METERS
				// STILL LEFT NEED TO BE REQUESTED
				actGeoWidthStep = geoValuePerStep * widthValue;
				widthValue = 0;
			}

			// UPDATE THE WIDTH LEFT
			widthLeft = this.getImageWidth();

			LOG.info("UPDATED WIDTH LEFT TO " + widthLeft);

			// UPDATE THE HEIGHT LEFT
			heightLeft = heightLeft - standardTileSize;

			LOG.info("UPDATED HEIGHT LEFT TO " + heightLeft);
			// IF IT DID NOT REACH THE LOWER END OF THE IMAGE
			if (heightValue >= 1) {
				LOG.info("NOT YET AT THE RIGHT EDGE");
				// A FULL TILE IN LATITUDE DIRECTION NEEDS TO BE REQUESTED
				// actGeoHeightStep = geoHeightValuePerStep * 1;
				actGeoHeightStep = geoValuePerStep * 1;
				heightValue--;
			} else if (heightValue < 1 && heightValue > 0) {
				// ONLY A PART OF THE TILE NEEDS TO BE REQUESTED
				LOG.info("REACHED THE RIGHT END");
				// actGeoHeightStep = geoHeightValuePerStep * heightValue;
				actGeoHeightStep = geoValuePerStep * heightValue;
				heightValue = 0;
			}
			leftStartBox = leftStartBox.getBBoxBelow(actGeoHeightStep);
		}

	}

	/**
	 * Receives the {@link BufferedImage} for the {@link Tile} t.
	 *
	 * @param t
	 *            the {@link Tile} to work with
	 */
	private void receiveImage(Tile t) {
		try {
			String urlContent = this.getLink() + "REQUEST=GETMAP" + "&FORMAT=image/png" + "&TRANSPARENT=true"
					+ "&HEIGHT=" + t.getImageHeight() + "&WIDTH=" + t.getImageWidth() + "&";

			String versionString = "VERSION=";
			String systemString = "";
			// TODO : WMS VERSIONEN BRAUCHEN UNTERSCHIEDLICHE CRS UND SRS
			if (this.getVersion().toString().equals(ServerVersion.WMS_V_0_0_3.toString())) {
				versionString = versionString + "0.0.3";
				systemString = "SRS=EPSG:" + this.getBbox().getSystem().getEPSG();
			} else if (this.getVersion().toString().equals(ServerVersion.WMS_V_0_1_0.toString())) {
				versionString = versionString + "0.1.0";
				systemString = "CRS=EPSG:" + this.getBbox().getSystem().getEPSG();
			}else if (this.getVersion().toString().equals(ServerVersion.WMS_V_0_9_0.toString())) {
				versionString = versionString + "0.9.0";
				systemString = "CRS=EPSG:" + this.getBbox().getSystem().getEPSG();
			}else if (this.getVersion().toString().equals(ServerVersion.WMS_V_1_0_0.toString())) {
				versionString = versionString + "1.0.0";
				systemString = "CRS=EPSG:" + this.getBbox().getSystem().getEPSG();
			}else if (this.getVersion().toString().equals(ServerVersion.WMS_V_1_1_0.toString())) {
				versionString = versionString + "1.1.0";
				systemString = "CRS=EPSG:" + this.getBbox().getSystem().getEPSG();
			} else if (this.getVersion().toString().equals(ServerVersion.WMS_V_1_3_0.toString())) {
				versionString = versionString + "1.3.0";
				systemString = "CRS=EPSG:" + this.getBbox().getSystem().getEPSG();
			}
			urlContent = urlContent + versionString + "&" + systemString + "&";

			String layerString = "LAYERS=";
			for (int a = 0; a < this.getLayers().size(); a++) {
				layerString = layerString + this.getLayers().get(a).toString();
				if (a < this.getLayers().size() - 1)
					layerString = layerString + ",";
			}
			layerString = layerString + "&";
			urlContent = urlContent + layerString;

			String stylesString = "STLYES=";
			for (int a = 0; a < this.getLayers().size(); a++) {
				stylesString = stylesString + "default";
				if (a < this.getLayers().size() - 1)
					stylesString = stylesString + ",";
			}
			stylesString = stylesString + "&";
			urlContent = urlContent + stylesString;

			String bboxString = "BBOX=" + t.getBbox().getCornersByVersion(this.version);

			urlContent = urlContent + bboxString;

			if (urlContent.contains("http://") == false) {
				urlContent = "http://" + urlContent;
			}

			// CONVERT TO URL
			URL url = new URL(urlContent);

			// System.out.println(urlContent + "");

			// RECEIVE THE IMAGE FROM THE WMS SERVER
			BufferedImage img = ImageIO.read(url);
			// t.setTileImage(ImageIO.read(url));
			t.setTileImage(img);

		} catch (IOException e) {
			// CREATE OWN IMAGE IF NONE COULD BE RECEIVED
			LOG.severe("TILE IMAGE COULD NOT BE RECEIVED! CREATING REPLACEMENT IMAGE...");
			this.createEmptyImage(t);
		}
	}

	/**
	 * Creates a {@link BufferedImage} for this {@link Tile} if no image could
	 * by received from the Web Map Service (WMS) server.
	 */
	private void createEmptyImage(Tile t) {

		// CREATE AN IMAGE IN THE NEEDED SIZE
		BufferedImage b_img = new BufferedImage(t.getImageWidth(), t.getImageHeight(), BufferedImage.TYPE_INT_ARGB);

		// CREATE THE GRAPHICS FOR THIS IMAGE
		Graphics2D graphics = b_img.createGraphics();

		// SET THE PAINT COLOR
		graphics.setPaint(new Color(254F / 2, 254F, 185F, 185F));
//		graphics.setPaint(new Color(1.0f, 1.0f, 1.0f, 0.5f));

		// DRAW A RECTANGLE OVER THE FULL IMAGE SIZE
		graphics.fillRect(0, 0, b_img.getWidth(), b_img.getHeight());

		// FLUSH DRAWING
		b_img.flush();

		// SET IMAGE
		t.setTileImage(b_img);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mapContent.MapLayer#prepareData(double, double, double, double,
	 * double, double)
	 */
	@Override
	public void prepareData(double northingRed, double eastingRed, double angle, double factor) {
		// TODO HIER DIE VARIABLE BEFUELLEN, ANHAND DERER DAS KARTENBILD GEDREHT
		// WIRD!

	}

	// GETTERS AND SETTERS

	/**
	 * Returns the map image of this {@link WmsLayer} as {@link BufferedImage}.
	 *
	 * @return the mapImage as {@link BufferedImage}
	 */
	public BufferedImage getMapImage() {
		return mapImage;
	}

	/**
	 * Sets the map image of this {@link WmsLayer}.
	 *
	 * @param mapImage
	 *            the mapImage to set
	 */
	public void setMapImage(BufferedImage mapImage) {
		this.mapImage = mapImage;
	}

	/**
	 * Returns the dots per inch (DPI) value of this {@link MapLayer} as
	 * {@link Integer}.
	 *
	 * @return the dpi as {@link Integer}
	 */
	public int getDpi() {
		return dpi;
	}

	/**
	 * Sets the dots per inch (DPI) value of this {@link MapLayer}
	 *
	 * @param dpi
	 *            the dpi to set
	 */
	public void setDpi(int dpi) {
		this.dpi = dpi;
	}

	/**
	 * Returns the array of tiles, of this {@link WmsLayer} as {@link TileArray}
	 * .
	 *
	 * @return the tileArray as {@link TileArray}
	 */
	public TileArray getTileArray() {
		return tileArray;
	}

	/**
	 * Sets the array of tiles of this {@link WmsLayer}.
	 *
	 * @param tileArray
	 *            the tileArray to set
	 */
	public void setTileArray(TileArray tileArray) {
		this.tileArray = tileArray;
	}

	/**
	 * Returns the link to the server as {@link String}.
	 *
	 * @return the link as {@link String}
	 */
	public String getLink() {
		return link;
	}

	/**
	 * Sets the link to the server.
	 *
	 * @param link
	 *            the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * Returns the {@link ServerVersion} of the WMS server to request.
	 *
	 * @return the version as {@link ServerVersion}
	 */
	public ServerVersion getVersion() {
		return version;
	}

	/**
	 * Sets the {@link ServerVersion} of the server to request.
	 *
	 * @param version
	 *            the version to set
	 */
	public void setVersion(ServerVersion version) {
		this.version = version;
	}

	/**
	 * Returns the desired opacity of the map image as {@link Integer} (between
	 * 0 and 100).
	 *
	 * @return the opacity as {@link Integer}
	 */
	public int getOpacity() {
		return opacity;
	}

	/**
	 * Sets the desired opacity of the map image as {@link Integer} (between 0
	 * and 100).
	 *
	 * @param opacity
	 *            the opacity to set
	 */
	public void setOpacity(int opacity) {
		this.opacity = opacity;
	}

	/**
	 * Returns the width of the map image in pixel as {@link Integer}.
	 *
	 * @return the imageWidth as {@link Integer}
	 */
	public int getImageWidth() {
		return imageWidth;
	}

	/**
	 * Sets the width of the map image in pixel.
	 *
	 * @param imageWidth
	 *            the imageWidth to set
	 */
	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	/**
	 * Returns the height of the map image in pixel as {@link Integer}.
	 *
	 * @return the imageHeight as {@link Integer}
	 */
	public int getImageHeight() {
		return imageHeight;
	}

	/**
	 * Sets the height of the map image in pixel.
	 *
	 * @param imageHeight
	 *            the imageHeight to set
	 */
	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}

	/**
	 * Returns the {@link ArrayList} of layer {@link String}s.
	 *
	 * @return the layers as {@link ArrayList} of {@link String}s
	 */
	public ArrayList<String> getLayers() {
		return layers;
	}

	/**
	 * Sets the {@link ArrayList} of layer {@link String}s
	 *
	 * @param layers
	 *            the layers to set
	 */
	public void setLayers(ArrayList<String> layers) {
		this.layers = layers;
	}

	// OTHERS
}
