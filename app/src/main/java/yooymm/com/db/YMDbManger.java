package yooymm.com.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import java.util.ArrayList;
import java.util.List;

import yooymm.com.bean.City;
import yooymm.com.bean.County;
import yooymm.com.util.Constant;
import yooymm.com.bean.Province;

/**
 * Created by
 *
 * @Author: Diminess
 * @Time: 2018/3/6 0006
 * @Biography: Learn not tomorrow.
 */

public class YMDbManger {
    private static YooymmSqliteHelper helper;
    private SQLiteDatabase db;
    private static YMDbManger mYMDbManger;

    /**
     * 将构造方法  私有化
     * @param context
     */
    private YMDbManger(Context context){
        helper = new YooymmSqliteHelper(context);
        db = helper.getWritableDatabase();
    }

    /**
     * 单例  获取 YMDbManger 实例
     * @param context
     * @return
     */
    public synchronized static YMDbManger getInstancee(Context context){
        if (mYMDbManger == null){
            mYMDbManger = new YMDbManger(context);
        }
        return mYMDbManger;
    }


    public synchronized static YooymmSqliteHelper getInstance(Context context){
        if (helper==null){
            helper = new YooymmSqliteHelper(context);
        }
        return helper;
    }

    /**
     * 根据sql语句查询获得cursor对象
     * @param db 数据库对象
     * @param sql 查询的sql语句
     * @param selectionArgs 查询条件的占位符
     * @return 查询结果
     */
    public static Cursor selectDataBySql(SQLiteDatabase db ,String sql,String[] selectionArgs){
        Cursor cursor = null;
        if (db!=null){
            //通过sql语句
            db.rawQuery(sql,selectionArgs);
        }
        return cursor;
    }

    /**
     * 将Province实例存储到数据库
     * @param province
     */
    public void saveProvince(Province province){
        if (province!=null){
            ContentValues values = new ContentValues();
            values.put("province_name",province.getProvinceName());
            values.put("province_code",province.getProvinceCode());
            db.insert("Province",null,values);
        }
    }

    /**
     * 从数据库中读取全国所有的省份信息
     * @return
     */
    public List<Province> loadProvinces(){
        List<Province> list = new ArrayList<>();
        Cursor cursor = db.query(Constant.CREATE_PROVINCE,null,
                null,null,
                null,null,null);
        if (cursor.moveToFirst()){
            do {
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                list.add(province);
            }while (cursor.moveToNext());
        }
        if (cursor!=null){
            cursor.close();
        }
        return list;
    }

    /**
     * 将City实例存储到数据库
     * @param city
     */
    public void saveCity(City city){
        if (city !=null){
            ContentValues values = new ContentValues();
            values.put("city_name",city.getCityNme());
            values.put("city_code",city.getCityCode());
            values.put("province_id",city.getProvinceId());
            db.insert(Constant.TABLE_CITY,null,values);
        }
    }

    /**
     * 从数据库读取某省下所有的城市信息
     * @param provinceId
     * @return
     */
    public List<City> loadCities(int provinceId){
        List<City> list = new ArrayList<>();
        Cursor cursor = db.query(Constant.CREATE_CITY,null,
                "province_id",new String[]{String.valueOf(provinceId)},
                null,null,null);
        if (cursor.moveToFirst()){
            City city = new City();
            city.setId(cursor.getInt(cursor.getColumnIndex("id")));
            city.setCityNme(cursor.getString(cursor.getColumnIndex("city_name")));
            city.setCityNme(cursor.getString(cursor.getColumnIndex("city_code")));
            city.setProvinceId(provinceId);
            list.add(city);
        }while (cursor.moveToNext());
        if (cursor!=null){
            cursor.close();
        }
        return list;
    }

    /**
     * 将County实例存到数据库
     * @param county
     */
    public void saveCounty(County county){
        if (county!=null){
            ContentValues values = new ContentValues();
            values.put("county_name",county.getCountyName());
            values.put("county_code",county.getCountyCode());
            values.put("city_id",county.getCityId());
            db.insert(Constant.TABLE_County,null,values);
        }
    }

    /**
     * 从数据库读取某城市下所有的县信息
     * @param cityId
     * @return
     */
    public List<County> loadCounties(int cityId){
        List<County> list = new ArrayList<>();
        Cursor cursor = db.query(Constant.TABLE_County,null,
                "city_id = ?",new String[]{String.valueOf(cityId)},
                null,null,null);
        if (cursor.moveToFirst()){
            do {
                County county = new County();
                county.setId(cursor.getInt(cursor.getColumnIndex("id")));
                county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
                county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
                county.setCityId(cityId);
                list.add(county);
            }while (cursor.moveToNext());
        }
        if (cursor!=null){
            cursor.close();
        }
        return list;
    }



}
