package com.housing.reports;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class ReportUtills {

	public static Font title = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLDITALIC);
	public static Font heading = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD);
	public static Font normal = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
	public static Font normalBold = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);

	public static void addValues(PdfPTable table, String... values) {
		addValues(table, ReportUtills.normal, true, 1, values);
	}

	public static void addValues(PdfPTable table, int colspan, String... values) {
		addValues(table, ReportUtills.normal, true, colspan, values);
	}

	public static void addValues(PdfPTable table, boolean border, String... values) {
		addValues(table, ReportUtills.normal, border, 1, values);
	}

	/**
	 * Add Value to the Table cell. This method has the capability to merge columns.
	 * 
	 * @param table
	 *            in which we will add cell.
	 * @param font
	 *            of the value.
	 * @param border
	 *            if true then no border.
	 * @param colspan
	 *            merge columns, 1 mean single column.
	 * @param values
	 *            to show columns, list of values, automatically adjust in table
	 */
	public static void addValues(PdfPTable table, Font font, boolean border, int colspan, String... values) {
		PdfPCell cell;
		for (String value : values) {
			cell = new PdfPCell(new Phrase(value, font));
			cell.setColspan(colspan);
			if (!border)
				cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
		}
	}

	public static PdfPCell getCell(String text, int alignment, Font font) {
		PdfPCell cell = new PdfPCell(new Phrase(text, font));
		cell.setPadding(0);
		cell.setColspan(4);
		cell.setPaddingBottom(5f);
		cell.setHorizontalAlignment(alignment);
		cell.setBorder(PdfPCell.NO_BORDER);
		return cell;
	}

	public static PdfPCell getCell(String text, Font font, int alignment) {
		PdfPCell cell = new PdfPCell(new Phrase(text, font));
		cell.setPadding(2);
		cell.setPaddingBottom(5f);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cell.setHorizontalAlignment(alignment);
		return cell;
	}

	public static PdfPCell getCellWhiteBackgroung(String text, Font font, int alignment) {
		PdfPCell cell = new PdfPCell(new Phrase(text, font));
		cell.setPadding(2);
		cell.setPaddingBottom(5f);
		cell.setBackgroundColor(BaseColor.WHITE);
		cell.setHorizontalAlignment(alignment);
		return cell;
	}

}
