package org.uaf.cd_web.components;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.uaf.cd_web.entity.*;
import org.uaf.cd_web.reponsitory.MenuReponesitory;

@Component
public class ImportFormExcel {

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = {"ID_USER", "ADDRESS", "PASSW", "NAME_USER", "PHONE", "EMAIL", "BIRTHDAY", "DATE_SIGNUP", "SEX", "Decentralization"};
    static String SHEET1 = "User";
    static String SHEET2 = "Product";
    private static MenuReponesitory menuRep;
    private static List<Image> images;

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    @SneakyThrows
    public static List<User> excelToUser(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(SHEET1);
            Iterator<Row> rows = sheet.iterator();

            List<User> users = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                User user = new User();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            user.setIdUser(currentCell.getStringCellValue());
                            break;

                        case 1:
                            user.setAddress(currentCell.getStringCellValue());
                            break;

                        case 2:
                            user.setPassw(currentCell.getStringCellValue());
                            break;

                        case 3:
                            user.setNameUser(currentCell.getStringCellValue());
                            break;
                        case 4:
                            user.setPhone(currentCell.getStringCellValue());
                            break;
                        case 5:
                            user.setEmail(currentCell.getStringCellValue());
                            break;
                        case 6:

                            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                            java.util.Date utilDate = dateFormat.parse(currentCell.getStringCellValue());
                            Date sqlDate = new Date(utilDate.getTime());
                            user.setBirthday(sqlDate);
                            break;
                        case 7:
                            SimpleDateFormat dateFormat2 = new SimpleDateFormat("MM/dd/yyyy");
                            java.util.Date utilDate2 = dateFormat2.parse(currentCell.getStringCellValue());
                            Date sqlDate2 = new Date(utilDate2.getTime());
                            user.setDateSignup(sqlDate2);
                            break;
                        case 8:
                            user.setSex(currentCell.getBooleanCellValue());
                            break;
                        case 9:
                            user.setDecentralization((int) currentCell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }

                    cellIdx++;
                }
                users.add(user);
            }
            workbook.close();
            return users;
        } catch (IOException e) {
            System.out.println("fail to parse Excel file: " + e.getMessage());
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    @SneakyThrows
    public static List<Product> excelToProduct(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(SHEET2);
            Iterator<Row> rows = sheet.iterator();

            List<Product> products = new ArrayList<>();
            images = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Product product = new Product();
                Detail_Pr detail_pr = new Detail_Pr();
                Image image = new Image(null, "", "", 0);

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            String idPr = currentCell.getStringCellValue();
                            product.setIdPr(idPr);
                            detail_pr.setIdPr(idPr);
                            image.setProduct(product);
                            image.setIdImg(idPr + RandomOTP.generateRandomString() + cellIdx);
                            break;
                        case 1:
                            image.setUrl(currentCell.getStringCellValue());
                            break;
                        case 2:
                            product.setNamePr(currentCell.getStringCellValue());
                            break;
                        case 3:
                            product.setMenu(new Menu("m6","Gạo lứt","m1"));
                            break;
                        case 4:
                            product.setDiscount((int) currentCell.getNumericCellValue());
                            break;
                        case 5:
                            product.setPrice((int) currentCell.getNumericCellValue());
                            break;
                        case 6:
//                            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
//                            java.util.Date utilDate = dateFormat.parse(currentCell.getStringCellValue());
                            java.util.Date utilDate = currentCell.getDateCellValue();
                            Date sqlDate = new Date(utilDate.getTime());
                            detail_pr.setNsx(sqlDate);
                            break;
                        case 7:
//                            SimpleDateFormat dateFormat2 = new SimpleDateFormat("MM-dd-yyyy");
//                            java.util.Date utilDate2 = dateFormat2.parse(currentCell.getStringCellValue());
                            java.util.Date utilDate2 = currentCell.getDateCellValue();
                            Date sqlDate2 = new Date(utilDate2.getTime());
                            detail_pr.setHsd(sqlDate2);
                            break;
                        case 8:
                            detail_pr.setBrand(currentCell.getStringCellValue());
                            break;
                        case 9:
                            detail_pr.setDescribe(currentCell.getStringCellValue());
                            break;
                        case 10:
                            detail_pr.setWeight(currentCell.getNumericCellValue());
                            break;
                        case 11:
                            detail_pr.setOrigin(currentCell.getStringCellValue());
                            break;
                        case 12:
                            detail_pr.setInventory((int) currentCell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                detail_pr.setDate_import_pr(Date.valueOf(LocalDate.now()));
                detail_pr.setConditionPR(0);
                product.setDetailPr(detail_pr);
                products.add(product);
                images.add(image);
            }
            workbook.close();
            return products;
        } catch (IOException e) {
            System.out.println("fail to parse Excel file: " + e.getMessage());
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<Image> getImages() {
        return images;
    }
}