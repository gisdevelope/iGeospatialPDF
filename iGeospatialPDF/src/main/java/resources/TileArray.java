package resources;

/**
 * Class to TODO
 * 
 * @author DaGri
 * @since 03.05.2016
 *
 */
public class TileArray {

	// ATTRIBUTES

	/**
	 * The array of {@link Tile}s used to store the {@link Tile}s that cover a
	 * TODO (MAP).
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
		// TODO : STIMMEN ROWS UND TILES SO?
		this.tiles = new Tile[rows][columns];
	}

	// METHODS

	// GETTERS AND SETTERS

	/**
	 * Returns the TODO
	 *
	 * @return the tiles
	 */
	public Tile[][] getTiles() {
		return tiles;
	}

	/**
	 * Sets the TODO
	 *
	 * @param tiles
	 *            the tiles to set
	 */
	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}

	/**
	 * Returns the TODO
	 *
	 * @return the columns
	 */
	public int getColumns() {
		return columns;
	}

	/**
	 * Sets the TODO
	 *
	 * @param columns
	 *            the columns to set
	 */
	public void setColumns(int columns) {
		this.columns = columns;
	}

	/**
	 * Returns the TODO
	 *
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * Sets the TODO
	 *
	 * @param rows
	 *            the rows to set
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	// OTHERS
}
