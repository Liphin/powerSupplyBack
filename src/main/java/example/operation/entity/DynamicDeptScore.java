package example.operation.entity;

import example.operation.impl.common.CommonService;

/**
 * Created by Administrator on 2018/10/16.
 */
public class DynamicDeptScore {

    private int id;
    private String dynamic_timestamp;
    private String title;
    private int status_cd;
    private String wx_user_id;
    private String wx_user_name;
    private int dept_from;
    private int score;
    private String timestamp;
    private String create_time;
    private String update_time;

    public DynamicDeptScore() {
    }

    /**
     * 插入新闻数据时同时插入dynamic_deptscore表的构造器方法
     * @param dynamicInfo
     */
    public DynamicDeptScore(DynamicInfo dynamicInfo){
        this.dynamic_timestamp = dynamicInfo.getTimestamp();
        this.title = dynamicInfo.getTitle();
        this.status_cd = dynamicInfo.getStatus_cd();
        this.wx_user_id = dynamicInfo.getWx_user_id();
        this.wx_user_name = dynamicInfo.getWx_user_name();
        this.create_time = dynamicInfo.getCreate_time();
        this.timestamp = CommonService.getTimeStamp();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDynamic_timestamp() {
        return dynamic_timestamp;
    }

    public void setDynamic_timestamp(String dynamic_timestamp) {
        this.dynamic_timestamp = dynamic_timestamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWx_user_id() {
        return wx_user_id;
    }

    public void setWx_user_id(String wx_user_id) {
        this.wx_user_id = wx_user_id;
    }

    public int getStatus_cd() {
        return status_cd;
    }

    public void setStatus_cd(int status_cd) {
        this.status_cd = status_cd;
    }

    public int getDept_from() {
        return dept_from;
    }

    public void setDept_from(int dept_from) {
        this.dept_from = dept_from;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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

    public String getWx_user_name() {
        return wx_user_name;
    }

    public void setWx_user_name(String wx_user_name) {
        this.wx_user_name = wx_user_name;
    }

    @Override
    public String toString() {
        return "DynamicDeptScore{" +
                "id=" + id +
                ", dynamic_timestamp='" + dynamic_timestamp + '\'' +
                ", title='" + title + '\'' +
                ", status_cd=" + status_cd +
                ", wx_user_id='" + wx_user_id + '\'' +
                ", wx_user_name='" + wx_user_name + '\'' +
                ", dept_from=" + dept_from +
                ", score=" + score +
                ", timestamp='" + timestamp + '\'' +
                ", create_time='" + create_time + '\'' +
                ", update_time='" + update_time + '\'' +
                '}';
    }
}
