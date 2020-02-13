package com.example.demo;

public class InfoStaff { //класс для запроса данных о сотрудниках
    
    private int staffId;
    private String staffName;
    private int firmId;
    private int bossId;

    public void setStaffId(int staffId) {

        this.staffId = staffId;

    }

    public int getStaffId() {

        return staffId;
    }

    public void setFirmId(int firmId) {

        this.firmId = firmId;
    }

    public int getFirmId() {

        return firmId;
    }

    public void setStaffName(String staffName) { 

        this.staffName = staffName;
    }

    public String getStaffName() {

        return staffName;
    }

    public void setBossId(int bossId) {

        this.bossId = bossId;
    }

    public int getBossId() {

        return bossId;
    }
}
