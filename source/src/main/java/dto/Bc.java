package dto;

import java.io.Serializable;
import java.time.LocalDate;

public class Bc implements Serializable {
	private int number; 	// 番号
	private int userID;
	private int company_id;
	private String name; 	// 氏名
	private LocalDate day;
	private String department;
	private String phone;
	private String position;
	private String email;
	private String remarks;
	private int stars_id;
	
    // コンストラクタ
    public Bc(int number, int userID, int company_id,String department,String position,String name, String phone,
    		String email, LocalDate day, String remarks, int stars_id) {
        this.number = number;
        this.userID = userID;
        this.name = name;
        this.day = day;
        this.department = department;
        this.phone = phone;
        this.position = position;
        this.email = email;
        this.remarks = remarks;
        this.stars_id = stars_id;
    }
    public Bc() {}

    // Getter & Setter
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    public int getCompany_id() {
        return company_id;
    }
    
    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getStars() {
        return stars_id;
    }

    public void setStars(int stars_id) {
        this.stars_id = stars_id;
    }
}