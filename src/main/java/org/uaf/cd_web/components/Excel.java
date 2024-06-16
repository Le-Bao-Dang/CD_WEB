package org.uaf.cd_web.components;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsxView;
import org.uaf.cd_web.entity.User;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

public class Excel extends AbstractXlsxView {
    private String [] columName={"name","address","phone","office"};
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws Exception {

        String exelName ="employee.xlsx";  // tên file excel sẽ dk tải xuống.
        Sheet sheet =workbook.createSheet("Employee");
        CreationHelper creationHelper =workbook.getCreationHelper();
        // font áp dụng cho row header (dòng tiêu đề )
        Font fontHeader =workbook.createFont();
        fontHeader.setBold(true);
        fontHeader.setColor(IndexedColors.BLUE.getIndex());
        fontHeader.setFontHeightInPoints((short) 14);
        fontHeader.setCharSet(HSSFFont.ANSI_CHARSET);
        // style áp dụng cho row header (dòng tiêu đề )
        CellStyle cellStyle =workbook.createCellStyle();
        cellStyle.setFont(fontHeader);
        cellStyle.setLocked(true);
        // tạo mới  row header (dòng tiêu đề ) vd:(name,address ,phone
        Row headerRow = sheet.createRow(0);
        // gán giá trị cho các cột đầu tiên (row header)
        for (int i=0;i<columName.length;i++){
            Cell cell =headerRow.createCell(i);
            cell.getStringCellValue().getBytes(Charset.forName("UTF-8"));
            cell.setCellValue(columName[i]);
            cell.setCellStyle(cellStyle);
        }
        // cell style cho các dòng chứ thông tin
        CellStyle cellStyleF=workbook.createCellStyle();
        cellStyleF.setDataFormat(creationHelper.createDataFormat().getFormat("dd-MM-yyyy"));


        List<User> list = (List<User>) model.get("employes");  // employes được get ra từ controller
        int row=1;
        for (User es: list){
            Row epe = sheet.createRow(row++);
            epe.createCell(0).setCellValue(es.getNameUser());
            Cell address = epe.createCell(1);
            address.setCellValue(es.getAddress());
            address.setCellStyle(cellStyleF);
            epe.createCell(2).setCellValue(es.getPhone());
            epe.createCell(3).setCellValue(es.checkDecentralization());
        }
        for(int i = 0; i < columName.length; i++) { // tự động thay đổi cho vừa kích thước cho các ô dữ liệu
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