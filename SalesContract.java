package com.housing.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class SalesContract {

	public static final String DEST = "report1.pdf";
	public static final String PDF = "resources/pdfs/stationery.pdf";
	public static final String HTML = "resources/xml/installments.html";
	public static final String CSS = "resources/xml/style.css";

	public Document document;
	public float width, height, middle;
	protected float mLeft, mRight, mTop, mBottom;

	public SalesContract() {

		document = new Document(PageSize.A4);
		Rectangle rec = document.getPageSize();
		document.setMargins(20, 20, 20, 20);

		width = rec.getWidth();
		height = rec.getHeight();
		mLeft = document.leftMargin();
		mRight = document.rightMargin();
		mTop = document.topMargin();
		mBottom = document.bottomMargin();

		middle = width / 2 + mLeft + mRight;

		debug();
	}

	public void createPdf(String result) throws IOException, DocumentException {

		// step 2
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(result));
		document.open();

		// step 3
		PdfPTable table = new PdfPTable(new float[] { middle, middle });
		table.setWidthPercentage(100f);

		//Heading in Middle
		table.addCell(ReportUtills.getCell("Sales Contract", PdfPCell.ALIGN_CENTER, ReportUtills.title));

		//Buyer Table
		table.addCell(buildBuyerTable());

		//Unit description
		table.addCell(buildUnitDescriptionTable());
		
		//Nominee Table
		table.addCell(buildNomineeTable());
		
		// Unit Table
		table.addCell(builUnitTable());
		
		//Installment Table
		table.addCell(buildInstallementTable());
		
		//Signature Box. 
		table.addCell(buildSignatureTable());
		
		//Witness 1
		table.addCell(buildWitnessTable(1));
		
		//Witness 2
		table.addCell(buildWitnessTable(1));

		// step 5
		document.add(table);
		document.close();

		Desktop.getDesktop().open(new File(result));
	}

	/**
	 * <b>Buyer Detail</b> Table Cell. Cell, has No border. Table has borders. 
	 * 1st row is heading with no border. 
	 * The table contains six rows.
	 * TODO pass the Customer Object and its values 
	 * 
	 * @return cell, which contains table of buyer detail. 
	 */
	private PdfPCell buildBuyerTable() {

		PdfPTable table = new PdfPTable(2);

		table.addCell(ReportUtills.getCell("Buyer Details", PdfPCell.ALIGN_CENTER, ReportUtills.heading));

		ReportUtills.addValues(table, "Name", "Ali Malik");
		ReportUtills.addValues(table, "Father / Husband", "Rehmat Ali");
		ReportUtills.addValues(table, "CNIC", "3740670185357");
		ReportUtills.addValues(table, "Phone / Cell", "03354982015");
		ReportUtills.addValues(table, "Address", "Address Line 1");
		ReportUtills.addValues(table, "City", "Sialkot");

		PdfPCell cell = new PdfPCell(table);
		cell.setBorder(PdfPCell.NO_BORDER);
		return cell;
	}

	/**
	 * <b>Nominee Table</b> Cell. Cell, has No Border. Table has borders. 
	 * 1st row is heading with no border. 
	 * Table has 3 rows, and a heading row.  
	 * 
	 * TODO pass the Customer Object and its values. 
	 * 
	 * @return cell, which contains table of Nominee Detail. 
	 */
	private PdfPCell buildNomineeTable() {

		PdfPTable table = new PdfPTable(2);

		table.addCell(ReportUtills.getCell("Nominee Details", PdfPCell.ALIGN_CENTER, ReportUtills.heading));

		ReportUtills.addValues(table, "Name", "Robina Kosar");
		ReportUtills.addValues(table, "Father / Husband", "Malik Ali");
		ReportUtills.addValues(table, "CNIC", "3740670185357");

		PdfPCell cell = new PdfPCell(table);
		cell.setBorder(PdfPCell.NO_BORDER);
		return cell;
	}

	/**
	 * <b>Unit Table</b> Cell. Cell, has No Border. Table has borders. 
	 * 1st row is heading with no border. 
	 * Table had 2 rows and a Heading row. 
	 * 
	 * TODO pass the Customer Object and its values. 
	 * 
	 * @return cell, which contains table of Nominee Detail. 
	 */
	private PdfPCell builUnitTable() {

		PdfPTable table = new PdfPTable(2);
		table.addCell(ReportUtills.getCell("Unit Details", PdfPCell.ALIGN_CENTER, ReportUtills.heading));

		ReportUtills.addValues(table, "Kh #", "");
		ReportUtills.addValues(table, "Sq #", "");

		PdfPCell cell = new PdfPCell(table);
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setPaddingLeft(10);
		return cell;
	}
	
	/**
	 * <b>Unit Table</b> Cell. Cell, has No Border. Table has borders. 
	 * 1st row is heading with no border. 
	 * Table had 6 rows and a Heading row. 
	 * 
	 * TODO pass the Unit Object and its values , Currently Default Value is set. 
	 * 
	 * @return cell, which contains table of Nominee Detail. 
	 */
	private PdfPCell buildUnitDescriptionTable() {

		PdfPTable table = new PdfPTable(4);

		table.addCell(ReportUtills.getCell("Unit Description", PdfPCell.ALIGN_CENTER, ReportUtills.heading));

		ReportUtills.addValues(table, 2, "Sales Date", "Ali Malik");
		ReportUtills.addValues(table, 2, "Unit Description", "Resident Plot #: Hussain Bio, Ground, Normal");
		ReportUtills.addValues(table, 2, "Unit Size", "W: 31.5, L: 7, Sqft: 1890, Marla 7");
		ReportUtills.addValues(table, 2, "Ins Type ", "Monthly");
		ReportUtills.addValues(table, "Disc", "0", "Duration", "36");
		ReportUtills.addValues(table, "Per Month", "375000", "Unit Price" , "26,25,000");

		PdfPCell cell = new PdfPCell(table);
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setPaddingLeft(10);
		return cell;
	}
	
	/**
	 * <b>Installment Table</b> Cell. Cell, has No Border. Table has borders. 
	 * 1st row is heading with no border. 
	 * Table had 38 rows, if we increase the number of rows, Table will move to next page. 
	 * 
	 * TODO pass the Installment Object List and set object values. 
	 * User List.get(i) because table is in chunks of 38 rows. 
	 * Each page will have 38 rows.  
	 * 
	 * @return cell, which contains table of Installment Detail. 
	 */
	private PdfPCell buildInstallementTable() {
		PdfPTable table = new PdfPTable(3);
		
		table.addCell(ReportUtills.getCell("Description", ReportUtills.normal, PdfPCell.ALIGN_CENTER));
		table.addCell(ReportUtills.getCell("Amount",ReportUtills.normal, PdfPCell.ALIGN_RIGHT));;
		table.addCell(ReportUtills.getCell("Date",ReportUtills.normal, PdfPCell.ALIGN_CENTER));
		
		for(int i = 0; i < 38; i++)
			ReportUtills.addValues(table, " ", " ", " ");
		
		PdfPCell cell = new PdfPCell(table);
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setPaddingTop(10);
		cell.setColspan(2);
		return cell;
	}
	
	/**
	 * <b>Signature Box</b> Cell. Cell, has No Border. Table has borders.  
	 * 
	 * @return cell, which contains table of Signature Box. 
	 */
	private PdfPCell buildSignatureTable() {
		PdfPTable table = new PdfPTable(2);
		table.addCell(ReportUtills.getCell("Buyer Signature: __________________ ", PdfPCell.ALIGN_LEFT, ReportUtills.heading));
		
		PdfPCell cell = new PdfPCell(table);
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setColspan(2);
		cell.setPaddingTop(10);
		return cell;
	}
	
	/**
	 * <b>Witness Box</b> Cell. Cell, has No Border. Table has no borders.  
	 * 
	 * @return cell, which contains table of Witness Box. 
	 */
	private PdfPCell buildWitnessTable(int no) {
		PdfPTable table = new PdfPTable(2);
		table.addCell(ReportUtills.getCell("Witness " + no, PdfPCell.ALIGN_LEFT, ReportUtills.heading));
		
		ReportUtills.addValues(table, false, "Name:", "___________________");
		ReportUtills.addValues(table, false,  "CNIC:", "___________________");
		ReportUtills.addValues(table, false,  "Date:", "___________________");
		
		PdfPCell cell = new PdfPCell(table);
		cell.setBorder(PdfPCell.NO_BORDER);
		cell.setPaddingTop(10);
		return cell;
	}


	public void debug() {
		System.out.println("Page Width: " + width);
		System.out.println("Page Height: " + height);
		System.out.println("Page Left: " + mLeft);
		System.out.println("Page Right: " + mRight);
		System.out.println("Page Top: " + mTop);
		System.out.println("Page Bottom: " + mBottom);
		System.out.println("Page Middle: " + middle);
	}

	public static void main(String[] args) throws IOException, DocumentException {
		File file = new File(DEST);
		// file.getParentFile().mkdirs();
		SalesContract salesContract = new SalesContract();
		salesContract.createPdf(file.getPath());
	}
}
