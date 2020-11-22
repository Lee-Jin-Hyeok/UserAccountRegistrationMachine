package com.dsm.xmlparser;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherRowMapper implements RowMapper<Teacher> {
    @Override
    public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
        Teacher findTeacher = new Teacher();

        findTeacher.setId(rs.getString("id"));
        findTeacher.setPw(rs.getString("pw"));
        findTeacher.setName(rs.getString("name"));
        findTeacher.setOffice(rs.getString("office"));

        return findTeacher;
    }
}
