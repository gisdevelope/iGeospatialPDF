package resources;

import java.lang.reflect.Array;

import mapContent.Map;

/**
 * Class to contain an {@link Array} of {@link Tile}s that can be combined to a
 * completed map image.
 * 
 * @author DaGri
 * @since 03.05.2016
 *
 */
public class TileArray {

	// ATTRIBUTES

	/**
	 * The array of {@link Tile}s used to store the {@link Tile}s that cover a
	 * {@link Map}.
	 */
	private Tile[][] tiles;

	/**
	 * The number of columns of the {@link Tile} array as {@link Integer}.
	 */
	private int columns;

	/**
	 * The number of rows of the {@link Tile} array as {@link Integer}.
	 */
	private int rows;

	// CONSTRUCTORS

	/**
	 * Constructor for a {@link TileArray} using two {@link Integer}s. The first
	 * {@link Integer} defines the number of columns, the second one the number
	 * of rows.
	 * 
	 * @param columns
	 *            the number of columns
	 * @param rows
	 *            the number of rows
	 */
	public TileArray(int columns, int rows) {
		this.columns = columns;
		this.rows = rows;
		this.tiles = new Tile[rows][columns];
	}

	// METHODS

	// GETTERS AND SETTERS

	/**
	 * Returns the {@link Tile} - {@link Array}, that contains the {@link Tile}
	 * s, with n rows and m columns. The number of rows can be get by using the
	 * getRows() : {@link Integer} - method. The columns can be get by the
	 * getColumns() : {@link Integer} - method.
	 *
	 * @return the {@link Tile} - {@link Array}
	 */
	public Tile[][] getTiles() {
		return tiles;
	}

	/**
	 * Sets the {@link Tile} - {@link Array}, that contains the {@link Tile} s,
	 * with n rows and m columns.
	 *
	 * @param tiles
	 *            the {@link Tile}s to set
	 */
	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}

	/**
	 * Returns the count of columns of the {@link Tile} - {@link Array} as
	 * {@link Integer}.
	 *
	 * @return the columns
	 */
	public int getColumns() {
		return columns;
	}

	/**
	 * Returns the count of rows of the {@link Tile} - {@link Array} as
	 * {@link Integer}.
	 *
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}

	// OTHERS
}
