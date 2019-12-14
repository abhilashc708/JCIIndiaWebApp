package com.jci.india.web.app.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jci.india.web.app.model.Images;
import com.jci.india.web.app.model.Loan;

public class GeneratePdfReport {

	private static final Logger LOGGER = LogManager.getLogger(GeneratePdfReport.class);

	public static ByteArrayInputStream loanReport(List<Loan> loan) throws MalformedURLException, IOException {
		int num=0;
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			PdfPTable table = new PdfPTable(11);
			table.setWidthPercentage(100);
			//table.setWidths(new int[]{1, 3, 3});

			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			Font titleFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 20);
			titleFont.setColor(BaseColor.BLUE);
			Font subtitle = FontFactory.getFont(FontFactory.TIMES_ROMAN, 15);
			subtitle.setColor(BaseColor.BLUE);

			PdfPCell hcell;

			hcell = new PdfPCell(new Phrase("SL No.", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Date", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Project", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Program", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("JC", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("JCT", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("JJ", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Others", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Hours", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Description", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Rate", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			for (Loan loans : loan) {

				PdfPCell cell;

				cell = new PdfPCell(new Phrase(String.valueOf(num=num+1)));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(loans.getDate().toString()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(loans.getProject()));
				cell.setPaddingLeft(5);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(loans.getProgram()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(5);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(loans.getJc()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(5);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(loans.getJct()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(5);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(loans.getJj()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(5);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(loans.getOthers()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(5);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(loans.getHours())));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(5);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(loans.getDescription()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(5);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(loans.getRate())));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(5);
				table.addCell(cell);
			}

			PdfWriter.getInstance(document, out);
			document.open();
			Image img =  Image.getInstance("src/main/resources/images/temp_logo_testing.png");
			Image img2 = null;
			img.setAbsolutePosition(50f, 720f);
			img.scaleAbsolute(100, 100);
			Paragraph para = new Paragraph("ZJCI INDIA ZONE - XIX", titleFont);
			para.setAlignment(Element.ALIGN_CENTER);
			Paragraph para2 = new Paragraph("LOM ACTIVITY PROFILE", subtitle);
			para2.setAlignment(Element.ALIGN_CENTER);
			Paragraph para3 = new Paragraph("AREA C", subtitle);
			para3.setSpacingAfter(30);
			para3.setAlignment(Element.ALIGN_CENTER);

			Paragraph para4 = new Paragraph("JCI KUTHUPARAMBHA", subtitle);
			para4.setSpacingBefore(30);
			para4.setSpacingAfter(30);
			para4.setAlignment(Element.ALIGN_CENTER);
			Paragraph para5 = null;
			String a = null;
			PdfPTable table1 = null;
			for (Loan loanprogram : loan) {

				for (Images imgd : loanprogram.getImages()) {
					a = String.valueOf( imgd.getName() );
				}

				table1 = new PdfPTable(1);
				PdfPTable nested = new PdfPTable(2);

				Image imgs =  Image.getInstance(a);
				//imgs.setAbsolutePosition(50f, 720f);
				imgs.scaleAbsolute(250, 200);


				PdfPCell cell1;

				cell1 = new PdfPCell(new Phrase(loanprogram.getProgram()));
				cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				table1.addCell(cell1);

				PdfPCell nesteds;
				nesteds = new PdfPCell((imgs));
				nested.addCell(nesteds);

				cell1 = new PdfPCell(nested);
				table1.addCell(cell1);


				/*para5 = new Paragraph(loanprogram.getProgram(), subtitle);
				para5.setSpacingBefore(10);
				para5.setSpacingAfter(10);
				para5.setAlignment(Element.ALIGN_CENTER);
				img2 =  Image.getInstance(a);
				img2.scaleAbsolute(250, 200);*/

			}

			Paragraph miniNote = new Paragraph("JCI ...................................  Region  ............................. Month  ....................... Sheet No ................");
			miniNote.setSpacingAfter(10);
			document.add(img);
			document.add(para);
			document.add(para2);
			document.add(para3);
			document.add(miniNote);
			document.add(table);
			document.add(para4);
			//document.add(para5);
			document.add(table1);
			//document.add(img2);
			document.close();

		} catch (DocumentException ex) {

			LOGGER.error("Error occurred: {0}", ex);
		}

		return new ByteArrayInputStream(out.toByteArray());
	}

}
