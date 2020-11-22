package com.dsm.xlsxparser;

public class Teacher {

    private String id;
    private String pw;
    private String name;
    private String office;

    public Teacher() {}
    public Teacher(String id, String pw, String name, String office) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.office = office;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }
}
