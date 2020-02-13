/*
 * This file is generated by jOOQ.
 */
package org.jooq.sources.tables;


import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row4;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;
import org.jooq.sources.Keys;
import org.jooq.sources.Public;
import org.jooq.sources.tables.records.StaffRecord;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Staff extends TableImpl<StaffRecord> {

    private static final long serialVersionUID = -460171372;

    /**
     * The reference instance of <code>public.staff</code>
     */
    public static final Staff STAFF = new Staff();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<StaffRecord> getRecordType() {
        return StaffRecord.class;
    }

    /**
     * The column <code>public.staff.StaffId</code>.
     */
    public final TableField<StaffRecord, Integer> STAFFID = createField(DSL.name("StaffId"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('\"Staff_StaffId_seq\"'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>public.staff.StaffName</code>.
     */
    public final TableField<StaffRecord, String> STAFFNAME = createField(DSL.name("StaffName"), org.jooq.impl.SQLDataType.VARCHAR(500).nullable(false), this, "");

    /**
     * The column <code>public.staff.FirmId</code>.
     */
    public final TableField<StaffRecord, Integer> FIRMID = createField(DSL.name("FirmId"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.staff.BossId</code>.
     */
    public final TableField<StaffRecord, Integer> BOSSID = createField(DSL.name("BossId"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * Create a <code>public.staff</code> table reference
     */
    public Staff() {
        this(DSL.name("staff"), null);
    }

    /**
     * Create an aliased <code>public.staff</code> table reference
     */
    public Staff(String alias) {
        this(DSL.name(alias), STAFF);
    }

    /**
     * Create an aliased <code>public.staff</code> table reference
     */
    public Staff(Name alias) {
        this(alias, STAFF);
    }

    private Staff(Name alias, Table<StaffRecord> aliased) {
        this(alias, aliased, null);
    }

    private Staff(Name alias, Table<StaffRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Staff(Table<O> child, ForeignKey<O, StaffRecord> key) {
        super(child, key, STAFF);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<StaffRecord, Integer> getIdentity() {
        return Keys.IDENTITY_STAFF;
    }

    @Override
    public UniqueKey<StaffRecord> getPrimaryKey() {
        return Keys.STAFF_PKEY;
    }

    @Override
    public List<UniqueKey<StaffRecord>> getKeys() {
        return Arrays.<UniqueKey<StaffRecord>>asList(Keys.STAFF_PKEY);
    }

    @Override
    public Staff as(String alias) {
        return new Staff(DSL.name(alias), this);
    }

    @Override
    public Staff as(Name alias) {
        return new Staff(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Staff rename(String name) {
        return new Staff(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Staff rename(Name name) {
        return new Staff(name, null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<Integer, String, Integer, Integer> fieldsRow() {
        return (Row4) super.fieldsRow();
    }
}
