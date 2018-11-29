package example.operation.entity;

import java.util.List;

/**
 * Created by Administrator on 2018/9/14.
 */
public class User {

    private int id;
    private String user_id;
    private String name;
    private List<Integer> department;
    private String position;
    private String mobile;
    private int gender;
    private String email;
    private String avatar;
    private int status;
    private int enable;
    private int isleader;
    private String english_name;
    private String telephone;
    private String order;
    private String qr_code;
    private String alias;


    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getDepartment() {
        return department;
    }

    public void setDepartment(List<Integer> department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public int getIsleader() {
        return isleader;
    }

    public void setIsleader(int isleader) {
        this.isleader = isleader;
    }

    public String getEnglish_name() {
        return english_name;
    }

    public void setEnglish_name(String english_name) {
        this.english_name = english_name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getQr_code() {
        return qr_code;
    }

    public void setQr_code(String qr_code) {
        this.qr_code = qr_code;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", user_id='" + user_id + '\'' +
                ", name='" + name + '\'' +
                ", department=" + department +
                ", position='" + position + '\'' +
                ", mobile='" + mobile + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", status=" + status +
                ", enable=" + enable +
                ", isleader=" + isleader +
                ", english_name='" + english_name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", order='" + order + '\'' +
                ", qr_code='" + qr_code + '\'' +
                ", alias='" + alias + '\'' +
                '}';
    }
}
