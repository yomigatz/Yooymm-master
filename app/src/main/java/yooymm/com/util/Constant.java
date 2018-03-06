package yooymm.com.util;

/**
 * Created by
 *
 * @Author: Diminess
 * @Time: 2018/3/6 0006
 * @Biography: Learn not tomorrow.
 */

public class Constant {

    public static final String DATABASE_NAME =  "yooymmDB.db";
    public static final int DATABASE_VERSION= 1;
    public static final String TABLE_NAME = "person";
    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String AGE = "age";


    /**
     * Province表 建表语句
     */
    public static final String TABLE_PROVINCE = "Province";
    public static final String CREATE_PROVINCE =
            "create table "+TABLE_PROVINCE+" (id integer primary key autoincrement,province_name text,province_code text)";

    /**
     * City表 建表语句
     */
    public static final String TABLE_CITY = "City";
    public static final String CREATE_CITY =
            "create table "+TABLE_CITY+" (id integer primary key autoincrement,city_name text,city_code text,province_id integer)";


    /**
     * County表 建表语句
     */
    public static final String TABLE_County = "County";
    public static final String CREATE_COUNTY =
            "create table "+TABLE_County+" (id integer primary key autoincrement,county_name text,county_code text ,city_id integer)";


}
