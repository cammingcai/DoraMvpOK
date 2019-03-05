package mvp.gz.com.mvp.bean;

import java.io.Serializable;

/**
 * Created by camming on 2018\12\25 0025.
 * code is data  data is code
 *
 * 用户信息类
 */

public class User implements Serializable {

    /**用户唯一ID*/
    private int id;
    /**用户昵称*/
    private String nickName;
    /**用户手机号*/
    private String phone;
    /**年级ID*/
    private int gradeId;
    /**教材版本ID*/
    private int editionId;
    /**科目ID*/
    private int subjectId;
    /**哆啦币*/
    private int studyCoin;
    /**学校名称*/
    private String schoolName;
    /**哆啦ID*/
    private String duolaId;
    /**省份、直辖市ID*/
    private int province;
    /**城市ID*/
    private int city;
    /**区ID*/
    private int area;
    private String address;
    private String sex;
    private String iconUrl;

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjcetId) {
        this.subjectId = subjcetId;
    }

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

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
