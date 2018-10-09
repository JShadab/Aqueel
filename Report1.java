package com.housing.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class Report1 {

	public static final String DEST = "report1.pdf";
	public static final String PDF = "resources/pdfs/stationery.pdf";
	public static final String HTML = "resources/xml/installments.html";
	public static final String CSS = "resources/xml/style.css";

	public Document document;
	public float width, height, middle;
	protected float mLeft, mRight, mTop, mBottom;

	public Report1() {

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
		PdfPTable table = new PdfPTable(new float[] { middle });
		table.setWidthPercentage(100f);

		// Heading in Middle
		// table.addCell(ReportUtills.getCell("Sales Contract", PdfPCell.ALIGN_CENTER,
		// ReportUtills.title));

		// Buyer Table
		table.addCell(buildProjectUnitSizeTable());

		table.addCell(buildCustomerGuadianTable());

		table.addCell(buildPhoneTable());

		table.addCell(buildAddressTable());
		table.addCell(buildCityCNICTable());
		table.addCell(buildSaleDateTable());
		
		table.addCell(buildAdditionDiscountTable());
		
		table.addCell(buildDuartionTable());

		// step 5
		document.add(table);
		document.close();

		Desktop.getDesktop().open(new File(result));
	}

	/**
	 * <b>Buyer Detail</b> Table Cell. Cell, has No border. Table has borders. 1st
	 * row is heading with no border. The table contains six rows. TODO pass the
	 * Customer Object and its values
	 * 
	 * @return cell, which contains table of buyer detail.
	 */
	private PdfPCell buildProjectUnitSizeTable() {

		PdfPTable table = new PdfPTable(3);
		try {
			table.setWidths(new int[] { 1, 2, 1 });
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		// table.addCell(ReportUtills.getCell("Buyer Details", PdfPCell.ALIGN_CENTER,
		// ReportUtills.heading));

		{
			String project = "Roze Valley-2";

			PdfPCell cellProject = ReportUtills.getCellWhiteBackgroung("Project", ReportUtills.normalBold,
					PdfPCell.ALIGN_LEFT);
			cellProject.setBorder(PdfPCell.NO_BORDER);

			PdfPCell cellProjectVal = ReportUtills.getCellWhiteBackgroung(project, ReportUtills.normal,
					PdfPCell.ALIGN_CENTER);
			cellProjectVal.setBorder(PdfPCell.BOTTOM);

			table.addCell(cellProject);
			table.addCell(cellProjectVal);

			{
				/** SaleContract cell */

				String dateTime = "11-Sep-18 " + "11:40 AM";
				PdfPCell cellDateTime = ReportUtills.getCellWhiteBackgroung(dateTime, ReportUtills.normal,
						PdfPCell.ALIGN_CENTER);
				cellDateTime.setBorder(PdfPCell.NO_BORDER);

				PdfPCell cellSaleContract = ReportUtills.getCellWhiteBackgroung("Sales Contract", ReportUtills.heading,
						PdfPCell.ALIGN_CENTER);
				cellSaleContract.setBorder(PdfPCell.NO_BORDER);

				PdfPCell cellSaleContractNum = ReportUtills.getCellWhiteBackgroung("476", ReportUtills.heading,
						PdfPCell.ALIGN_CENTER);
				cellSaleContractNum.setBorder(PdfPCell.NO_BORDER);

				PdfPTable tableSaleContract = new PdfPTable(1);
				tableSaleContract.addCell(cellDateTime);
				tableSaleContract.addCell(cellSaleContract);
				tableSaleContract.addCell(cellSaleContractNum);

				PdfPCell cellTableSaleContract = new PdfPCell(tableSaleContract);
				cellTableSaleContract.setBorder(PdfPCell.NO_BORDER);
				cellTableSaleContract.setRowspan(3);
				cellTableSaleContract.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);

				table.addCell(cellTableSaleContract);

			}
		}
		{
			String unitDesc = "Res. plot 402B Block		" + "Hussain Bano		" + "Ground		" + "Normal";

			PdfPCell cellUnitDesc = ReportUtills.getCellWhiteBackgroung("Unit Desc.", ReportUtills.normalBold,
					PdfPCell.ALIGN_LEFT);
			cellUnitDesc.setBorder(PdfPCell.NO_BORDER);

			PdfPCell cellUnitDescVal = ReportUtills.getCellWhiteBackgroung(unitDesc, ReportUtills.normal,
					PdfPCell.ALIGN_CENTER);
			cellUnitDescVal.setBorder(PdfPCell.BOTTOM);

			table.addCell(cellUnitDesc);
			table.addCell(cellUnitDescVal);
		}
		{
			String sizeInfo = "Width: 31.5		" + "Length: 60		" + "Sqft: 3090 	" + "7 Maria";

			PdfPCell cellSizeInfo = ReportUtills.getCellWhiteBackgroung("Size info", ReportUtills.normalBold,
					PdfPCell.ALIGN_LEFT);
			cellSizeInfo.setBorder(PdfPCell.NO_BORDER);

			PdfPCell cellSizeInfoVal = ReportUtills.getCellWhiteBackgroung(sizeInfo, ReportUtills.normal,
					PdfPCell.ALIGN_CENTER);
			cellSizeInfoVal.setBorder(PdfPCell.BOTTOM);

			table.addCell(cellSizeInfo);
			table.addCell(cellSizeInfoVal);
		}

		// ReportUtills.addValues(table, false, , project);
		//
		// ReportUtills.addValues(table, false, "Unit Desc.", unitDesc);
		// ReportUtills.addValues(table, false, "Size", sizeInfo);

		PdfPCell cell = new PdfPCell(table);
		cell.setBorder(PdfPCell.NO_BORDER);
		return cell;
	}

	private PdfPCell buildCustomerGuadianTable() {

		PdfPTable table = new PdfPTable(2);
		try {
			table.setWidths(new int[] { 1, 3 });
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		// table.addCell(ReportUtills.getCell("Buyer Details", PdfPCell.ALIGN_CENTER,
		// ReportUtills.heading));

		{
			String customerName = "Malik Ali";

			PdfPCell cellCustomer = ReportUtills.getCellWhiteBackgroung("Customer", ReportUtills.normalBold,
					PdfPCell.ALIGN_LEFT);
			cellCustomer.setBorder(PdfPCell.NO_BORDER);

			PdfPCell cellcellCustomerVal = ReportUtills.getCellWhiteBackgroung(customerName, ReportUtills.heading,
					PdfPCell.ALIGN_CENTER);
			cellcellCustomerVal.setBorder(PdfPCell.BOTTOM);

			table.addCell(cellCustomer);
			table.addCell(cellcellCustomerVal);

		}
		{
			String guadian = "Rahmat Ali";

			PdfPCell cellGuadian = ReportUtills.getCellWhiteBackgroung("Father/Husband", ReportUtills.normalBold,
					PdfPCell.ALIGN_LEFT);
			cellGuadian.setBorder(PdfPCell.NO_BORDER);

			PdfPCell cellGuadianVal = ReportUtills.getCellWhiteBackgroung(guadian, ReportUtills.normal,
					PdfPCell.ALIGN_CENTER);
			cellGuadianVal.setBorder(PdfPCell.BOTTOM);

			table.addCell(cellGuadian);
			table.addCell(cellGuadianVal);
		}

		PdfPCell cell = new PdfPCell(table);
		cell.setBorder(PdfPCell.NO_BORDER);
		return cell;
	}

	private PdfPCell buildPhoneTable() {

		PdfPTable table = new PdfPTable(4);

		try {
			table.setWidths(new int[] { 1, 1, 1, 1 });
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		{
			String phone = "0301-7305611";
			String cell = "923038926389";

			PdfPCell cellPhone = ReportUtills.getCellWhiteBackgroung("Phone", ReportUtills.normalBold, PdfPCell.ALIGN_LEFT);
			cellPhone.setBorder(PdfPCell.NO_BORDER);

			PdfPCell cellPhoneVal = ReportUtills.getCellWhiteBackgroung(phone, ReportUtills.normal,
					PdfPCell.ALIGN_CENTER);
			cellPhoneVal.setBorder(PdfPCell.BOTTOM);

			PdfPCell cellCellPhone = ReportUtills.getCellWhiteBackgroung("Cell", ReportUtills.normalBold,
					PdfPCell.ALIGN_CENTER);
			cellCellPhone.setBorder(PdfPCell.NO_BORDER);

			PdfPCell cellCellPhoneVal = ReportUtills.getCellWhiteBackgroung(cell, ReportUtills.normal,
					PdfPCell.ALIGN_CENTER);
			cellCellPhoneVal.setBorder(PdfPCell.BOTTOM);

			table.addCell(cellPhone);
			table.addCell(cellPhoneVal);

			table.addCell(cellCellPhone);
			table.addCell(cellCellPhoneVal);

		}

		PdfPCell cell = new PdfPCell(table);
		cell.setBorder(PdfPCell.NO_BORDER);
		return cell;
	}

	private PdfPCell buildAddressTable() {

		PdfPTable table = new PdfPTable(2);
		try {
			table.setWidths(new int[] { 1, 3 });
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		{
			String address = "Chauk no 11/1 L Renala Kurd";

			PdfPCell cellAddress = ReportUtills.getCellWhiteBackgroung("Address", ReportUtills.normalBold,
					PdfPCell.ALIGN_LEFT);
			cellAddress.setBorder(PdfPCell.NO_BORDER);

			PdfPCell cellAddressVal = ReportUtills.getCellWhiteBackgroung(address, ReportUtills.normal,
					PdfPCell.ALIGN_CENTER);
			cellAddressVal.setBorder(PdfPCell.BOTTOM);

			table.addCell(cellAddress);
			table.addCell(cellAddressVal);
		}

		PdfPCell cell = new PdfPCell(table);
		cell.setBorder(PdfPCell.NO_BORDER);
		return cell;
	}

	private PdfPCell buildCityCNICTable() {

		PdfPTable table = new PdfPTable(4);

		try {
			table.setWidths(new int[] { 1, 1, 1, 1 });
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		{
			String city = "Renala Khurd";
			String cnic = "35303-2068298-1";

			PdfPCell cellCity = ReportUtills.getCellWhiteBackgroung("City", ReportUtills.normalBold, PdfPCell.ALIGN_LEFT);
			cellCity.setBorder(PdfPCell.NO_BORDER);

			PdfPCell cellCityVal = ReportUtills.getCellWhiteBackgroung(city, ReportUtills.normal,
					PdfPCell.ALIGN_CENTER);
			cellCityVal.setBorder(PdfPCell.BOTTOM);

			PdfPCell cellCNIC = ReportUtills.getCellWhiteBackgroung("C.N.I.C.", ReportUtills.normalBold,
					PdfPCell.ALIGN_CENTER);
			cellCNIC.setBorder(PdfPCell.NO_BORDER);

			PdfPCell cellCNICVal = ReportUtills.getCellWhiteBackgroung(cnic, ReportUtills.normal,
					PdfPCell.ALIGN_CENTER);
			cellCNICVal.setBorder(PdfPCell.BOTTOM);

			table.addCell(cellCity);
			table.addCell(cellCityVal);

			table.addCell(cellCNIC);
			table.addCell(cellCNICVal);

		}

		PdfPCell cell = new PdfPCell(table);
		cell.setBorder(PdfPCell.NO_BORDER);
		return cell;
	}

	private PdfPCell buildSaleDateTable() {

		PdfPTable table = new PdfPTable(6);

		try {
			table.setWidths(new int[] { 1, 2, 1, 1, 1, 1 });
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		{
			String saleDate = "Monday September 10, 2018";
			String rate = "375,1000";
			String unitPrice = "2,625,000";

			PdfPCell cellSaleDate = ReportUtills.getCellWhiteBackgroung("Sale Date", ReportUtills.normalBold,
					PdfPCell.ALIGN_LEFT);
			cellSaleDate.setBorder(PdfPCell.NO_BORDER);

			PdfPCell cellSaleDateVal = ReportUtills.getCellWhiteBackgroung(saleDate, ReportUtills.normal,
					PdfPCell.ALIGN_CENTER);
			cellSaleDateVal.setBorder(PdfPCell.BOTTOM);

			PdfPCell cellRate = ReportUtills.getCellWhiteBackgroung("Rate/Marla", ReportUtills.normalBold,
					PdfPCell.ALIGN_CENTER);
			cellRate.setBorder(PdfPCell.NO_BORDER);

			PdfPCell cellRateVal = ReportUtills.getCellWhiteBackgroung(rate, ReportUtills.normal,
					PdfPCell.ALIGN_CENTER);
			cellRateVal.setBorder(PdfPCell.BOTTOM);

			PdfPCell cellUnitPrice = ReportUtills.getCellWhiteBackgroung("Price Of Unit", ReportUtills.normalBold,
					PdfPCell.ALIGN_CENTER);
			cellUnitPrice.setBorder(PdfPCell.NO_BORDER);

			PdfPCell cellUnitPriceVal = ReportUtills.getCellWhiteBackgroung(unitPrice, ReportUtills.normal,
					PdfPCell.ALIGN_CENTER);
			cellUnitPriceVal.setBorder(PdfPCell.BOTTOM);

			table.addCell(cellSaleDate);
			table.addCell(cellSaleDateVal);

			table.addCell(cellRate);
			table.addCell(cellRateVal);

			table.addCell(cellUnitPrice);
			table.addCell(cellUnitPriceVal);

		}

		PdfPCell cell = new PdfPCell(table);
		cell.setBorder(PdfPCell.NO_BORDER);
		return cell;
	}

	private PdfPCell buildAdditionDiscountTable() {

		PdfPTable table = new PdfPTable(5);

		try {
			table.setWidths(new int[] { 2, 1, 1, 1, 1 });
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		{
			String additionOrDiscountPer = "0%";
			String additionOrDiscountAmt = "0";
			String totalPrice = "2,625,000";

			PdfPCell cellAdditionOrDiscountPer = ReportUtills.getCellWhiteBackgroung(
					"Addition/Discount value on unit(if any)", ReportUtills.normalBold, PdfPCell.ALIGN_LEFT);
			cellAdditionOrDiscountPer.setBorder(PdfPCell.NO_BORDER);

			PdfPCell cellAdditionOrDiscountPerVal = ReportUtills.getCellWhiteBackgroung(additionOrDiscountPer,
					ReportUtills.normal, PdfPCell.ALIGN_CENTER);
			cellAdditionOrDiscountPerVal.setBorder(PdfPCell.BOTTOM);

			PdfPCell cellRateVal = ReportUtills.getCellWhiteBackgroung(additionOrDiscountAmt, ReportUtills.normal,
					PdfPCell.ALIGN_CENTER);
			cellRateVal.setBorder(PdfPCell.BOTTOM);

			/***********************************************************/

			PdfPCell cellTotalPrice = ReportUtills.getCellWhiteBackgroung("Total Price", ReportUtills.normalBold,
					PdfPCell.ALIGN_LEFT);
			cellTotalPrice.setBorder(PdfPCell.NO_BORDER);

			PdfPCell cellTotalPriceVal = ReportUtills.getCellWhiteBackgroung(totalPrice, ReportUtills.normal,
					PdfPCell.ALIGN_CENTER);
			cellTotalPriceVal.setBorder(PdfPCell.BOTTOM);

			table.addCell(cellAdditionOrDiscountPer);
			table.addCell(cellAdditionOrDiscountPerVal);
			table.addCell(cellRateVal);

			table.addCell(cellTotalPrice);
			table.addCell(cellTotalPriceVal);

		}

		PdfPCell cell = new PdfPCell(table);
		cell.setBorder(PdfPCell.NO_BORDER);
		return cell;
	}
	private PdfPCell buildDuartionTable() {

		PdfPTable table = new PdfPTable(7);

		try {
			table.setWidths(new int[] { 1, 1, 1, 1, 1,1,2 });
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		{
			String duration = "36";
			String type = "Monthly";
			String remark = "                                    ";

			PdfPCell cellDuration = ReportUtills.getCellWhiteBackgroung(
					"Duration", ReportUtills.normalBold, PdfPCell.ALIGN_LEFT);
			cellDuration.setBorder(PdfPCell.NO_BORDER);

			PdfPCell cellDurationVal = ReportUtills.getCellWhiteBackgroung(duration,
					ReportUtills.normal, PdfPCell.ALIGN_CENTER);
			cellDurationVal.setBorder(PdfPCell.BOTTOM);

			PdfPCell cellMonthStr = ReportUtills.getCellWhiteBackgroung("Month", ReportUtills.normalBold,
					PdfPCell.ALIGN_LEFT);
			cellMonthStr.setBorder(PdfPCell.NO_BORDER);

			/***********************************************************/

			PdfPCell cellType = ReportUtills.getCellWhiteBackgroung("Type", ReportUtills.normalBold,
					PdfPCell.ALIGN_RIGHT);
			cellType.setBorder(PdfPCell.NO_BORDER);

			PdfPCell cellTypeVal = ReportUtills.getCellWhiteBackgroung(type, ReportUtills.normal,
					PdfPCell.ALIGN_CENTER);
			cellTypeVal.setBorder(PdfPCell.BOTTOM);
			
			/***********************************************************/
			
			PdfPCell cellRemark = ReportUtills.getCellWhiteBackgroung("Remark", ReportUtills.normalBold,
					PdfPCell.ALIGN_RIGHT);
			cellRemark.setBorder(PdfPCell.NO_BORDER);

			PdfPCell cellRemarkVal = ReportUtills.getCellWhiteBackgroung(remark, ReportUtills.normal,
					PdfPCell.ALIGN_CENTER);
			cellRemarkVal.setBorder(PdfPCell.BOTTOM);

			table.addCell(cellDuration);
			table.addCell(cellDurationVal);
			table.addCell(cellMonthStr);
			

			table.addCell(cellType);
			table.addCell(cellTypeVal);
			
			table.addCell(cellRemark);
			table.addCell(cellRemarkVal);

		}

		PdfPCell cell = new PdfPCell(table);
		cell.setBorder(PdfPCell.NO_BORDER);
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
		Report1 salesContract = new Report1();
		salesContract.createPdf(file.getPath());
	}
}
