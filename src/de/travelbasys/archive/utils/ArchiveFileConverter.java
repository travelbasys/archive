package de.travelbasys.archive.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.RandomAccessFileOrArray;
import com.itextpdf.text.pdf.codec.TiffImage;

import de.travelbasys.archive.servlet.DownloadFileServlet;

public class ArchiveFileConverter {
	
	private static Log log = LogFactory.getFactory().getInstance(
			DownloadFileServlet.class.getName());

	public static void convertTifToPDF(String tifFile, String pdfFile) {

		try {

			InputStream is = new FileInputStream(new File(tifFile));

			// CONVERT
			com.itextpdf.text.Image image;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, out);
			writer.setStrictImageSequence(true);
			document.open();

			RandomAccessFileOrArray ra = new RandomAccessFileOrArray(is);
			int pages = TiffImage.getNumberOfPages(ra);
			for (int i = 1; i <= pages; i++) {
				image = TiffImage.getTiffImage(ra, i);
				image.setAbsolutePosition(0, 0);
				image.scaleToFit(PageSize.A4.getWidth(),
						PageSize.A4.getHeight());
				document.setPageSize(PageSize.A4);
				document.newPage();
				document.add(image);
			}
			document.close();
			out.flush();

			// write Array of bytes to a FileOutputStream
			OutputStream outputPDF = new FileOutputStream(new File(pdfFile));

			outputPDF.write(out.toByteArray());

			outputPDF.flush();
			outputPDF.close();

			log.debug("The pdf file: " + pdfFile + " created!");

		} catch (FileNotFoundException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		} catch (DocumentException e) {
			log.error(e);
		}

	}
}
