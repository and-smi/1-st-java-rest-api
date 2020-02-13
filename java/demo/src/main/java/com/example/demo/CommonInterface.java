package com.example.demo;

import java.util.List;

public interface CommonInterface {

    boolean createStaff (InfoStaff staff);

    boolean createFirm (InfoOrganisation firm);

    List<InfoStaff> readAll();

    List<InfoOrganisation> readAllFirms();

    InfoStaff readStaff(int id);

    InfoOrganisation readFirm(int id);

    boolean updateStaff(InfoStaff staff, int id);

    boolean updateFirm(InfoOrganisation firm, int id);

    boolean deleteStaff(int id);

    boolean deleteFirm(int id);

}