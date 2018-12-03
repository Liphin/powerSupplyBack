package example.operation.entity;

import java.util.List;

/**
 * Created by Administrator on 2018/9/15.
 */
public class Checker {

    private int id;
    private String wx_user_name;
    private String wx_user_id;
    private int check_level;
    private int dept_from;
    private int dept_check;
    private String manager_wx_user_id;
    private String timestamp;
    private String create_time;
    private String update_time;

    public Checker() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWx_user_name() {
        return wx_user_name;
    }

    public void setWx_user_name(String wx_user_name) {
        this.wx_user_name = wx_user_name;
    }

    public String getWx_user_id() {
        return wx_user_id;
    }

    public void setWx_user_id(String wx_user_id) {
        this.wx_user_id = wx_user_id;
    }

    public int getDept_from() {
        return dept_from;
    }

    public void setDept_from(int dept_from) {
        this.dept_from = dept_from;
    }

    public int getDept_check() {
        return dept_check;
    }

    public void setDept_check(int dept_from) {
        this.dept_from = dept_check;
    }

    public int getCheck_level() {
        return check_level;
    }

    public void setCheck_level(int check_level) {
        this.check_level = check_level;
    }

    public String getManager_wx_user_id() {
        return manager_wx_user_id;
    }

    public void setManager_wx_user_id(String manager_wx_user_id) {
        this.manager_wx_user_id = manager_wx_user_id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }


    @Override
    public String toString() {
        return "Checker{" +
                "id=" + id +
                ", wx_user_name='" + wx_user_name + '\'' +
                ", wx_user_id='" + wx_user_id + '\'' +
                ", check_level=" + check_level +
                ", dept_from=" + dept_from +
                ", dept_check=" + dept_check +
                ", manager_wx_user_id='" + manager_wx_user_id + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", create_time='" + create_time + '\'' +
                ", update_time='" + update_time + '\'' +
                '}';
    }
}
