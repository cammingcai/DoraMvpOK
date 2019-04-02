package com.gz.camming.mvp.bean;

/**
 * Created by camming on 2019/4/2.
 */

public class LoginData {

    private int id;
    private String nickName;
    private String phone;
    private int gradeId;
    private int city;
    private int area;
    private String address;
    private int editionId;
    private int studyCoin;
    private String schoolName;
    private String duolaId;
    private int province;
    private String sex;
    private int expire_date;
    private String permission;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getEditionId() {
        return editionId;
    }

    public void setEditionId(int editionId) {
        this.editionId = editionId;
    }

    public int getStudyCoin() {
        return studyCoin;
    }

    public void setStudyCoin(int studyCoin) {
        this.studyCoin = studyCoin;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getDuolaId() {
        return duolaId;
    }

    public void setDuolaId(String duolaId) {
        this.duolaId = duolaId;
    }

    public int getProvince() {
        return province;
    }

    public void setProvince(int province) {
        this.province = province;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getExpire_date() {
        return expire_date;
    }

    public void setExpire_date(int expire_date) {
        this.expire_date = expire_date;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", phone='" + phone + '\'' +
                ", gradeId=" + gradeId +
                ", city=" + city +
                ", area=" + area +
                ", address='" + address + '\'' +
                ", editionId=" + editionId +
                ", studyCoin=" + studyCoin +
                ", schoolName='" + schoolName + '\'' +
                ", duolaId='" + duolaId + '\'' +
                ", province=" + province +
                ", sex='" + sex + '\'' +
                ", expire_date=" + expire_date +
                ", permission='" + permission + '\'' +
                '}';
    }
}
