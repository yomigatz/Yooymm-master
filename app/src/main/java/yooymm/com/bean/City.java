package yooymm.com.bean;

/**
 * Created by
 *
 * @Author: Diminess
 * @Time: 2018/3/6 0006
 * @Biography: Learn not tomorrow.
 */

public class City {
    private int id;
    private String cityNme;
    private String cityCode;
    private int provinceId;

    public City() {
    }

    public City(int id, String cityNme, String cityCode, int provinceId) {
        this.id = id;
        this.cityNme = cityNme;
        this.cityCode = cityCode;
        this.provinceId = provinceId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityNme() {
        return cityNme;
    }

    public void setCityNme(String cityNme) {
        this.cityNme = cityNme;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
