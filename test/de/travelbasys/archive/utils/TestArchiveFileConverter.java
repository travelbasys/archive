package de.travelbasys.archive.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.RandomAccessFileOrArray;
import com.itextpdf.text.pdf.codec.TiffImage;

public class TestArchiveFileConverter {

	public static void main(String[] args) {

		ArchiveFileConverter archiveFileConverter = new ArchiveFileConverter();
		archiveFileConverter.convertTifToPDF(
				"C:/Users/Hagge/workspace/Archive/PDFDaten/6055/test.tif",
				"C:/Users/Hagge/workspace/Archive/PDFDaten/6055/test.pdf");
	}
}