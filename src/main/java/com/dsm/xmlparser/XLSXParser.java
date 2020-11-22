package com.dsm.xmlparser;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class XLSXParser {

    private static final int NAME = 0;
    private static final int ID = 1;
    private static final int PASSWORD = 2;
    private static final int OFFICE = 3;

    private String path;
    private String fileName;

    public XLSXParser() {}
    public XLSXParser(String path, String fileName) {
        this.path = path;
        this.fileName = fileName;
    }

    public List<Teacher> parseXLSX(String path, String fileName) {
        this.path = path;
        this.fileName = fileName;
        return parseXLSX();
    }

    public List<Teacher> parseXLSX() {

        List<Teacher> teachers = new ArrayList<>();

        try {
            FileInputStream file = new FileInputStream(path + fileName);
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            XSSFSheet sheet = workbook.getSheetAt(0);

            int rowMaxIndex = sheet.getPhysicalNumberOfRows();
            for(int rowIndex = 1 ; rowIndex < rowMaxIndex ; rowIndex++) {

                Teacher teacher = new Teacher();
                XSSFRow row = sheet.getRow(rowIndex);

                if(row != null) {
                    int columnMaxIndex = row.getPhysicalNumberOfCells();
                    for(int columnIndex = 0 ; columnIndex < columnMaxIndex ; columnIndex++) {

                        XSSFCell cell = row.getCell(columnIndex);
                        String value = "";

                        if(cell != null) {
                            switch (cell.getCellType()) {
                                case FORMULA:
                                    value = cell.getCellFormula();
                                    break;
                                case NUMERIC:
                                    value = String.valueOf((int)cell.getNumericCellValue());
                                    break;
                                case STRING:
                                    value = cell.getStringCellValue();
                                    break;
                                case BOOLEAN:
                                    value = String.valueOf(cell.getBooleanCellValue());
                                    break;
                                case ERROR:
                                    value = String.valueOf(cell.getErrorCellValue());
                                    break;
                                case BLANK:
                                    value = null;
                                    break;
                            }
                        }

                        switch (columnIndex) {
                            case NAME:
                                teacher.setName(value);
                                break;
                            case ID:
                                teacher.setId(value);
                                break;
                            case PASSWORD:
                                teacher.setPw(value);
                                break;
                            case OFFICE:
                                teacher.setOffice(value);
                                break;
                        }
                    }
                    teachers.add(teacher);
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return teachers;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
