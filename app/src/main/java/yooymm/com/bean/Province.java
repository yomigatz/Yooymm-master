package yooymm.com.bean;

/**
 * Created by
 *
 * @Author: Diminess
 * @Time: 2018/3/6 0006
 * @Biography: Learn not tomorrow.
 */

public class Province {
    private int id;
    private String provinceName;
    private String provinceCode;

    public Province() {
    }
    public Province(int id, String provinceName, String provinceCode) {
        this.id = id;
        this.provinceName = provinceName;
        this.provinceCode = provinceCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
