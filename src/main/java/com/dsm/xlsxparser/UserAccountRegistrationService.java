package com.dsm.xlsxparser;

import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigInteger;
import java.security.MessageDigest;

public class UserAccountRegistrationService {

    private static final String ALGORITHM = "SHA-512";
    private static final String ENCODING = "UTF-8";

    private JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public UserAccountRegistrationService() {
        DatabaseConfiguration configuration = new DatabaseConfiguration();
        jdbcTemplate.setDataSource(configuration.dataSource());
    }

    public void updateTeacher(Teacher teacher) {
        if(teacher.getId() == null || teacher.getPw() == null || teacher.getName() == null || teacher.getOffice() == null) {
            return;
        } else {
            encodingPassword(teacher);

            Teacher findTeacher = null;
            try {
                String sql = "SELECT * FROM teacher WHERE id = ?";
                findTeacher = jdbcTemplate.queryForObject(sql, new Object[]{teacher.getId()}, new TeacherRowMapper());
            } catch(Exception e) {
                e.printStackTrace();
            }

            if (findTeacher == null) {
                jdbcTemplate.update("INSERT INTO teacher (id, pw, name, office) VALUES (?, ? , ? , ?)",
                        new Object[]{teacher.getId(), teacher.getPw(), teacher.getName(), teacher.getOffice()});
            } else {
                jdbcTemplate.update("UPDATE teacher SET pw = ?, name = ?, office = ? WHERE id = ?",
                        new Object[]{teacher.getPw(), teacher.getName(), teacher.getOffice(), teacher.getId()});
            }

            System.out.println("UPDATE::{ " + teacher.getId() + ", " + teacher.getPw() + ", " + teacher.getName() + ", " + teacher.getOffice() + "}");
        }
    }

    public void encodingPassword(Teacher teacher) {
        try {
            MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
            digest.reset();
            digest.update(teacher.getPw().getBytes(ENCODING));
            teacher.setPw(String.format("%0128x", new BigInteger(1, digest.digest())));
        } catch(Exception e) {
            System.out.println("암호화 실패");
        }
    }
}
