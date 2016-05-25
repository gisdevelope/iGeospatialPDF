package tets;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfName;
import com.lowagie.text.pdf.PdfStructureElement;
import com.lowagie.text.pdf.PdfStructureTreeRoot;
import com.lowagie.text.pdf.PdfWriter;

import resources.PdfContentHandler;

/**
 * Class to TODO
 * 
 * @author DaGri
 * @since 24.05.2016
 *
 */
public class ItextTest {

	/**
	 * TODO
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		Document doc = new Document(PageSize.A0);
		try {

			PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("output/pdfTest.pdf"));
			writer.setTagged();

			doc.open();

			// CREATE THE STRUCTURE TREE ROOT
			PdfStructureTreeRoot treeRoot = writer.getStructureTreeRoot();
			// CREATE THE TOP ELEMENT OF THE TREE
			PdfStructureElement topElem = new PdfStructureElement(treeRoot, new PdfName("Data"));

			PdfContentByte contByte = writer.getDirectContent();
			PdfContentHandler handler = new PdfContentHandler(writer, doc, treeRoot, topElem);

			BufferedImage buff = new BufferedImage(300, 300, BufferedImage.TYPE_INT_ARGB);

			Graphics2D gra = buff.createGraphics();
			gra.setColor(Color.BLUE);
			gra.fillRect(0, 0, 300, 300);

			Image img = Image.getInstance(buff, null);

			img.setAbsolutePosition(0f, doc.getPageSize().getHeight() - 300);
			contByte.addImage(img);
			doc.close();
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}

	}
}
