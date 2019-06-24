package net.skhu.skhu_711;

public class ForestLoginRequest {
    String studentNumber;
    String password;

    public ForestLoginRequest(String studentNumber, String password) {
        this.studentNumber = studentNumber;
        this.password = password;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
