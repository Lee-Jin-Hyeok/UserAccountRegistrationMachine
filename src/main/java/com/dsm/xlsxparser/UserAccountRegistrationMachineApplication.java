package com.dsm.xlsxparser;

import java.util.List;

public class UserAccountRegistrationMachineApplication {
    private static final String PATH = "./";                            // 경로
    private static final String FILE_NAME = "example.xlsx";     // 파일명

    public static void main(String[] args) {
        XLSXParser parser = new XLSXParser(PATH, FILE_NAME);
        List<Teacher> teachers = parser.parseXLSX();

        UserAccountRegistrationService service = new UserAccountRegistrationService();
        for(Teacher teacher : teachers) {
            service.updateTeacher(teacher);
        }
    }
}
