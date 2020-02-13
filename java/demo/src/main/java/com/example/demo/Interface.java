package com.example.demo;

import org.jooq.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.sources.tables.Firms;
import org.jooq.sources.tables.Staff;

import org.springframework.stereotype.Service;

import javax.sql.DataSource;

import static org.jooq.impl.DSL.select;

@Service
public class Interface implements CommonInterface {
    
    DataSource newConnection = DataBaseConfig.CreateDataSource();

    @Override
    public boolean createStaff(InfoStaff staff) {

        boolean successfulCreation = false;
        boolean existBossId = DSL.using(newConnection, SQLDialect.POSTGRES)
                .fetchExists(select(Staff.STAFF.STAFFID)
                        .from(Staff.STAFF)
                        .where(Staff.STAFF.STAFFID.eq(staff.getBossId())));
        boolean bossInTheSameFirm = DSL.using(newConnection, SQLDialect.POSTGRES)
                .fetchExists(select()
                        .from(Staff.STAFF)
                        .where(Staff.STAFF.STAFFID.eq(staff.getBossId()))
                        .and(Staff.STAFF.FIRMID.eq(staff.getFirmId())));

        if (staff.getBossId() == 0) { //если у сотрудника айди босса 0, сам себе начальник

            DSL.using(newConnection, SQLDialect.POSTGRES)
                    .insertInto(Staff.STAFF, Staff.STAFF.STAFFNAME, Staff.STAFF.BOSSID, Staff.STAFF.FIRMID)
                    .values(staff.getStaffName(),
                            staff.getBossId(),
                            staff.getFirmId()).execute();

            successfulCreation = true; // подтверждение об успешном создании

        }

                if (existBossId && (staff.getBossId() != 0)) { //если такой начальник существует

                    if (bossInTheSameFirm) { //и он в этой же фирме

                        DSL.using(newConnection, SQLDialect.POSTGRES)
                                .insertInto(Staff.STAFF, Staff.STAFF.STAFFNAME, Staff.STAFF.BOSSID, Staff.STAFF.FIRMID)
                                .values(staff.getStaffName(),
                                        staff.getBossId(),
                                        staff.getFirmId()).execute();
                        successfulCreation = true; // подтверждение об успешном создании

                    }

                }

        return successfulCreation;
    }

    @Override
    public boolean createFirm(InfoOrganisation firm) { //API creation firm
        DSL.using(newConnection, SQLDialect.POSTGRES)
                .insertInto(Firms.FIRMS, Firms.FIRMS.FIRMNAME, Firms.FIRMS.HEADOFFICEID)
                .values(firm.getOrgName(),
                        firm.getHeadOfficeId())
                .execute();
        return true;
    }

    @Override
    public List<InfoStaff> readAll() { // прочитать всех сотрудников
        ArrayList<InfoStaff> staffArray = new ArrayList<>();
        Result<Record> records = DSL.using(newConnection, SQLDialect.POSTGRES)
                .select()
                .from(Staff.STAFF)
                .fetch();
        for (Record rec : records) {
            InfoStaff newStaff = new InfoStaff();
            newStaff.setBossId(rec.get(Staff.STAFF.BOSSID));
            newStaff.setStaffId(rec.get(Staff.STAFF.STAFFID));
            newStaff.setStaffName(rec.get(Staff.STAFF.STAFFNAME));
            newStaff.setFirmId(rec.get(Staff.STAFF.FIRMID));
            staffArray.add(newStaff);
        }

        return staffArray;

    }

    @Override
    public List<InfoOrganisation> readAllFirms() { // прочитать все организации
        ArrayList<InfoOrganisation> orgArray = new ArrayList<>();
        Result<Record> orgRecords = DSL.using(newConnection, SQLDialect.POSTGRES)
                .select()
                .from(Firms.FIRMS)
                .fetch();
        for (Record record : orgRecords) {
            InfoOrganisation newOrg = new InfoOrganisation();
            newOrg .setOrgId(record.get(Firms.FIRMS.FIRMID));
            newOrg .setOrgName(record.get(Firms.FIRMS.FIRMNAME));
            newOrg .setHeadOfficeId(record.get(Firms.FIRMS.HEADOFFICEID));
            orgArray.add(newOrg);
        }

        return orgArray;

    }

    @Override
    public InfoStaff readStaff(int id) { // прочитать сотрудника по ид
        InfoStaff newStaff = new InfoStaff();
         SelectConditionStep<Record4<Integer, String, Integer, Integer>> records = DSL.using(newConnection, SQLDialect.POSTGRES)
                .select(Staff.STAFF.STAFFID, Staff.STAFF.STAFFNAME, Staff.STAFF.BOSSID, Staff.STAFF.FIRMID)
                .from(Staff.STAFF)
                .where(Staff.STAFF.STAFFID.eq(id));

         for (Record4 rec: records) {
            newStaff.setBossId(rec.get(Staff.STAFF.BOSSID));
            newStaff.setStaffId(rec.get(Staff.STAFF.STAFFID));
            newStaff.setStaffName(rec.get(Staff.STAFF.STAFFNAME));
            newStaff.setFirmId(rec.get(Staff.STAFF.FIRMID));
        }
        return newStaff;
    }

    @Override
    public InfoOrganisation readFirm(int id) { // прочитать организацию по ид
        InfoOrganisation newOrg = new InfoOrganisation();
        SelectConditionStep<Record2<String, Integer>> record2s = DSL.using(newConnection, SQLDialect.POSTGRES)
                .select(Firms.FIRMS.FIRMNAME, Firms.FIRMS.HEADOFFICEID)
                .from(Firms.FIRMS)
                .where(Firms.FIRMS.FIRMID.eq(id));

        for (Record2 rec: record2s) {
            newOrg.setOrgId(rec.get(Firms.FIRMS.FIRMID));
            newOrg.setOrgName(rec.get(Firms.FIRMS.FIRMNAME));
            newOrg.setHeadOfficeId(rec.get(Firms.FIRMS.HEADOFFICEID));
        }
        return newOrg;
    }

    @Override
    public boolean updateStaff(InfoStaff staff, int id) { // изменение сотрудника

        boolean successfullUpdate = false;
        boolean existBossId = DSL.using(newConnection, SQLDialect.POSTGRES)
                .fetchExists(select(Staff.STAFF.STAFFID)
                        .from(Staff.STAFF)
                        .where(Staff.STAFF.STAFFID.eq(staff.getBossId()))); // проверяем есть ли такой начальник
        boolean existsNote = DSL.using(newConnection, SQLDialect.POSTGRES)
                .fetchExists(select()
                        .from(Staff.STAFF).where(Staff.STAFF.STAFFID.eq(id))); //проверка существования сотрудника
        boolean bossInTheSameFirm = DSL.using(newConnection, SQLDialect.POSTGRES)
                .fetchExists(select()
                        .from(Staff.STAFF)
                        .where(Staff.STAFF.STAFFID.eq(staff.getBossId()))
                        .and(Staff.STAFF.FIRMID.eq(staff.getFirmId()))); //проверяем в той ли конторе начальник

        if (existsNote && staff.getBossId() == 0) {
            DSL.using(newConnection, SQLDialect.POSTGRES)
                    .update(Staff.STAFF)
                    .set(Staff.STAFF.STAFFNAME, staff.getStaffName())
                    .set(Staff.STAFF.BOSSID, staff.getBossId())
                    .set(Staff.STAFF.FIRMID, staff.getFirmId())
                    .where(Staff.STAFF.STAFFID.eq(id)).execute();
            successfullUpdate = true;

        }

        if (id != staff.getBossId()) {

            if (existBossId && (staff.getBossId() != 0)) { //если такой начальник существует

                if (bossInTheSameFirm) { //и он в этой же фирме

                    if (existsNote) {
                        DSL.using(newConnection, SQLDialect.POSTGRES)
                                .update(Staff.STAFF)
                                .set(Staff.STAFF.STAFFNAME, staff.getStaffName())
                                .set(Staff.STAFF.BOSSID, staff.getBossId())
                                .where(Staff.STAFF.STAFFID.eq(id)).execute();

                        successfullUpdate = true;
                    }

                }

            }

        }
        return successfullUpdate;
    }

    @Override
    public boolean updateFirm(InfoOrganisation firm, int id) { // изменение фирмы

        boolean existNote = DSL.using(newConnection, SQLDialect.POSTGRES)
                .fetchExists(select()
                        .from(Firms.FIRMS).where(Firms.FIRMS.FIRMID.eq(id))); //проверка существования фирмы

        if (firm.getHeadOfficeId() != id) {

            if (existNote) {
                DSL.using(newConnection, SQLDialect.POSTGRES)
                        .update(Firms.FIRMS)
                        .set(Firms.FIRMS.FIRMNAME, firm.getOrgName())
                        .set(Firms.FIRMS.HEADOFFICEID, firm.getHeadOfficeId())
                        .where(Firms.FIRMS.FIRMID.eq(id)).execute();
            }
            return true;

        }
        return false;

    }

    @Override
    public boolean deleteStaff(int id) { //API of deleting staff

        boolean successfullDeletingStaff = true;
        boolean existsNote = DSL.using(newConnection, SQLDialect.POSTGRES)
                .fetchExists(select()
                        .from(Staff.STAFF).where(Staff.STAFF.BOSSID.eq(id))); //проверка существования дочерних элементов

        if (existsNote) { //условие проверки существования дочерних элементов
            successfullDeletingStaff = false;
        } else {
            DSL.using(newConnection, SQLDialect.POSTGRES)
                    .delete(Staff.STAFF)
                    .where(Staff.STAFF.STAFFID.eq(id))
                    .execute();
        }

        return successfullDeletingStaff;
    }

    @Override
    public boolean deleteFirm(int id) { //API of deleting firm

        boolean successfullDeletingFirm = true;
        boolean existNote = DSL.using(newConnection, SQLDialect.POSTGRES)
                .fetchExists(select()
                        .from(Firms.FIRMS).where(Firms.FIRMS.HEADOFFICEID.eq(id))); //проверка существования дочерних элементов

        if (existNote) { //условие проверки существования дочерних элементов
            successfullDeletingFirm = false;
        } else {
            DSL.using(newConnection, SQLDialect.POSTGRES)
                    .delete(Firms.FIRMS)
                    .where(Firms.FIRMS.FIRMID.eq(id))
                    .execute();
        }

        return successfullDeletingFirm;

    }

} 