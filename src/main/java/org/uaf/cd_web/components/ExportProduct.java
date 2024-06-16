package org.uaf.cd_web.components;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsxView;
import org.uaf.cd_web.entity.Product;
import org.uaf.cd_web.entity.User;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

public class ExportProduct extends AbstractXlsxView {
    private String[] columName = {
            "ID_PR", "IMG", "NAME_PR", "MENU", "DISCOUNT", "PRICE",
            "NSX", "HSD", "BRAND", "DESCRIBE", "WEIGHT", "ORIGIN", "INVENTORY",
    };

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String exelName = "product.xlsx";  // tên file excel sẽ dk tải xuống.
        Sheet sheet = workbook.createSheet("Product");
        CreationHelper creationHelper = workbook.getCreationHelper();
        // font áp dụng cho row header (dòng tiêu đề )
        Font fontHeader = workbook.createFont();
        fontHeader.setBold(true);
        fontHeader.setColor(IndexedColors.BLUE.getIndex());
        fontHeader.setFontHeightInPoints((short) 14);
        fontHeader.setCharSet(HSSFFont.ANSI_CHARSET);
        // style áp dụng cho row header (dòng tiêu đề )
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(fontHeader);
        cellStyle.setLocked(true);
        // tạo mới  row header (dòng tiêu đề )
        Row headerRow = sheet.createRow(0);
        // gán giá trị cho các cột đầu tiên (row header)
        for (int i = 0; i < columName.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.getStringCellValue().getBytes(Charset.forName("UTF-8"));
            cell.setCellValue(columName[i]);
            cell.setCellStyle(cellStyle);
        }
        // cell style cho các dòng chứ thông tin
        CellStyle cellStyleF = workbook.createCellStyle();
        cellStyleF.setDataFormat(creationHelper.createDataFormat().getFormat("dd-MM-yyyy"));


        List<Product> list = (List<Product>) model.get("product");  // employes được get ra từ controller
        int row = 1;
        for (Product product : list) {
            Row epe = sheet.createRow(row++);
            epe.createCell(0).setCellValue(product.getIdPr());
            epe.createCell(1).setCellValue(product.getAvt().getUrl());
            epe.createCell(2).setCellValue(product.getNamePr());
            epe.createCell(3).setCellValue(product.getMenu().getNameMenu());
            epe.createCell(4).setCellValue(product.getDiscount());
            epe.createCell(5).setCellValue(product.getPrice());
            Cell nsx = epe.createCell(6);
            nsx.setCellValue(product.getDetailPr().getNsx());
            nsx.setCellStyle(cellStyleF);
            Cell hsd = epe.createCell(7);
            hsd.setCellValue(product.getDetailPr().getHsd());
            hsd.setCellStyle(cellStyleF);
            epe.createCell(8).setCellValue(product.getDetailPr().getBrand());
            epe.createCell(9).setCellValue(product.getDetailPr().getDescribe());
            epe.createCell(10).setCellValue(product.getDetailPr().getWeight());
            epe.createCell(11).setCellValue(product.getDetailPr().getOrigin());
            epe.createCell(12).setCellValue(product.getDetailPr().getInventory());
        }
        for (int i = 0; i < columName.length; i++) { // tự động thay đổi cho vừa kích thước cho các ô dữ liệu
            sheet.autoSizeColumn(i);
        }
        try {  // gửi kèm file thông qua response
            response.setHeader("Content-Disposition", "attachement; filename=\""
                    + java.net.URLEncoder.encode(exelName, "UTF-8") + "\";charset=\"UTF-8\"");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}

