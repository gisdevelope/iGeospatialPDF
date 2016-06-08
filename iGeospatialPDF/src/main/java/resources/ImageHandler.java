package resources;

import java.awt.image.BufferedImage;
import java.io.IOException;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Image;

/**
 * Class to handle image operations.
 * 
 * @author DaGri
 * @since 23.05.2016
 *
 */
public class ImageHandler {

	// ATTRIBUTES

	// CONSTRUCTORS

	/**
	 * Empty constructor for an {@link ImageHandler}.
	 */
	public ImageHandler() {

	}

	// METHODS

	public Image convertImage(BufferedImage img) {
		try {
			Image erg = Image.getInstance(img, null);
			return erg;
		} catch (BadElementException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	// GETTERS AND SETTERS

	// OTHERS
}
