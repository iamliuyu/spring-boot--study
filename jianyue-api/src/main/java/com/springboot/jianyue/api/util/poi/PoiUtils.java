package com.springboot.jianyue.api.util.poi;

import com.springboot.jianyue.api.entity.Article;
import com.springboot.jianyue.api.entity.User;
import com.springboot.jianyue.api.util.StringUtil;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PoiUtils {
    public static ResponseEntity<byte[]> exportArticleExcel(List<Article> articleList){
        HttpHeaders headers = null;
        ByteArrayOutputStream baos = null;

        try {
            //1.创建Excel文档
            HSSFWorkbook workbook = new HSSFWorkbook();
            //2.创建文档摘要
            workbook.createInformationProperties();
            //3.获取文档信息，并配置
            DocumentSummaryInformation dsi = workbook.getDocumentSummaryInformation();
            //3.1文档类别
            dsi.setCategory("文章信息");
            //3.2设置文档管理员
            dsi.setManager("LIUYU");
            //3.3设置组织机构
            dsi.setCompany("JIANYUE APP");
            //4.获取摘要信息并配置
            SummaryInformation si = workbook.getSummaryInformation();
            //4.1设置文档主题
            si.setSubject("文章信息表");
            //4.2.设置文档标题
            si.setTitle("文章信息");
            //4.3 设置文档作者
            si.setAuthor("LIUYU");
            //4.4设置文档备注
            si.setComments("备注信息暂无");
            //创建Excel表单
            HSSFSheet sheet = workbook.createSheet("JIANYUE APP文章信息表");
            //创建日期显示格式
            HSSFCellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
            //创建标题的显示样式
            HSSFCellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            //定义列的宽度
            sheet.setColumnWidth(0, 5 * 256);
            sheet.setColumnWidth(1, 12 * 256);
            sheet.setColumnWidth(2, 10 * 256);
            sheet.setColumnWidth(3, 5 * 256);
            sheet.setColumnWidth(4, 12 * 256);
            sheet.setColumnWidth(5, 20 * 256);

            //5.设置表头
            HSSFRow headerRow = sheet.createRow(0);
            HSSFCell cell0 = headerRow.createCell(0);
            cell0.setCellValue("文章ID");
            cell0.setCellStyle(headerStyle);
            HSSFCell cell1 = headerRow.createCell(1);
            cell1.setCellValue("作者ID");
            cell1.setCellStyle(headerStyle);
            HSSFCell cell2 = headerRow.createCell(2);
            cell2.setCellValue("文章标题");
            cell2.setCellStyle(headerStyle);
            HSSFCell cell3 = headerRow.createCell(3);
            cell3.setCellValue("文章内容");
            cell3.setCellStyle(headerStyle);
            HSSFCell cell4 = headerRow.createCell(4);
            cell4.setCellValue("创建日期");
            cell4.setCellStyle(headerStyle);

            //6.装数据
            for (int i = 0; i < articleList.size(); i++) {
                HSSFRow row = sheet.createRow(i + 1);
                Article article = articleList.get(i);
                row.createCell(0).setCellValue(article.getId());
                row.createCell(1).setCellValue(article.getUId());
                row.createCell(2).setCellValue(article.getTitle());
                row.createCell(3).setCellValue(article.getContent());
                HSSFCell createTimeCell = row.createCell(4);
                createTimeCell.setCellValue(article.getCreateTime());
                createTimeCell.setCellStyle(dateCellStyle);
            }

            headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment",
                    new String("articleTable.xls".getBytes("UTF-8"), "iso-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            baos = new ByteArrayOutputStream();
            workbook.write(baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("excel导出成功");
        return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);
    }

    public static List<User> importUserExcel(MultipartFile file) {
        List<User> users = new ArrayList<>();
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(new POIFSFileSystem(file.getInputStream()));
            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                HSSFSheet sheet = workbook.getSheetAt(i);
                int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
                User user;
                for (int j = 0; j < physicalNumberOfRows; j++) {
                    if (j == 0) {
                        continue;//标题行
                    }
                    HSSFRow row = sheet.getRow(j);
                    if (row == null) {
                        continue;//没数据
                    }
                    int physicalNumberOfCells = row.getPhysicalNumberOfCells();
                    user = new User();
                    for (int k = 0; k < physicalNumberOfCells; k++) {
//                        System.out.println("CELL:" + physicalNumberOfCells);
//                        System.out.println("ROWS:" + physicalNumberOfRows);
                        HSSFCell cell = row.getCell(k);
                        switch (cell.getCellTypeEnum()) {
                            case STRING: {
                                String cellValue = cell.getStringCellValue();
                                if (cellValue == null) {
                                    cellValue = "";
                                }
                                switch (k) {
                                    case 0:
                                        user.setMobile(cellValue);
                                        break;
                                    case 1:
                                        user.setPassword(StringUtil.getBase64Encoder(cellValue));
                                        break;
                                    case 2:
                                        user.setNickname(cellValue);
                                        break;
                                    case 3:
                                        user.setAvatar(cellValue);
                                        break;
                                }
                            }
                            break;
                            default: {
                                switch (k) {
                                    case 4:
                                        user.setRegtime(cell.getDateCellValue());
                                        break;
                                    case 5:
                                        user.setStatus((short) cell.getNumericCellValue());
                                        break;
                                }
                            }
                            break;
                        }
                    }
                    users.add(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }
}
