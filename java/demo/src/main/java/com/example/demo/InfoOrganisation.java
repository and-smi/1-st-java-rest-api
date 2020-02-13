package com.example.demo;

public class InfoOrganisation { //класс для запроса данных организации
    
    private int orgId;
    private String orgName;
    private int headOfficeId;
    

    public void setOrgId(int orgId) {

        this.orgId = orgId;
    }

    public int getOrgId() {

        return orgId;
    }

    public void setHeadOfficeId(int headOfficeId) {

        this.headOfficeId = headOfficeId;
    }

    public int getHeadOfficeId() {

        return headOfficeId;
    }

    public void setOrgName(String orgName) { 

        this.orgName = orgName;
    }

    public String getOrgName() {

        return orgName;
    }

}
