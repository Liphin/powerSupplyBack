package example.operation.entity;

import io.netty.handler.codec.http.multipart.FileUpload;

import java.util.List;

/**
 * Created by Administrator on 2018/9/15.
 */
public class DynamicInfo {

    private int id;
    private String title;
    private int type;
    private String wx_user_id;
    private String wx_user_name;
    private int status_cd;
    private int view_count;
    private int pitch_count;
    private int score;
    private int dept_from;
    private int dept_check;
    private int dept_all_target;
    private String dept_target;
    private int stick_cd;
    private String stick_time;
    private String timestamp;
    private String create_time;
    private String update_time;

    private int optType;
    private String html;
    private String search; //搜索的文本
    private FileUpload coverImage; //新闻类型
    private String resourceImgToCoverImg; //记录资源文件中的图片string，待转封面图片
    private String coverimgs;

    public DynamicInfo() {
    }

    public String getCoverimgs() {
        return coverimgs;
    }

    public void setCoverimgs(String coverimgs) {
        this.coverimgs = coverimgs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getWx_user_id() {
        return wx_user_id;
    }

    public void setWx_user_id(String wx_user_id) {
        this.wx_user_id = wx_user_id;
    }

    public String getWx_user_name() {
        return wx_user_name;
    }

    public void setWx_user_name(String wx_user_name) {
        this.wx_user_name = wx_user_name;
    }

    public int getStatus_cd() {
        return status_cd;
    }

    public void setStatus_cd(int status_cd) {
        this.status_cd = status_cd;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public int getPitch_count() {
        return pitch_count;
    }

    public void setPitch_count(int pitch_count) {
        this.pitch_count = pitch_count;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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

    public void setDept_check(int dept_check) {
        this.dept_check = dept_check;
    }

    public String getDept_target() {
        return dept_target;
    }

    public void setDept_target(String dept_target) {
        this.dept_target = dept_target;
    }

    public int getStick_cd() {
        return stick_cd;
    }

    public void setStick_cd(int stick_cd) {
        this.stick_cd = stick_cd;
    }

    public String getStick_time() {
        return stick_time;
    }

    public void setStick_time(String stick_time) {
        this.stick_time = stick_time;
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

    public int getOptType() {
        return optType;
    }

    public void setOptType(int optType) {
        this.optType = optType;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public FileUpload getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(FileUpload coverImage) {
        this.coverImage = coverImage;
    }

    public String getResourceImgToCoverImg() {
        return resourceImgToCoverImg;
    }

    public void setResourceImgToCoverImg(String resourceImgToCoverImg) {
        this.resourceImgToCoverImg = resourceImgToCoverImg;
    }

    public int getDept_all_target() {
        return dept_all_target;
    }

    public void setDept_all_target(int dept_all_target) {
        this.dept_all_target = dept_all_target;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    @Override
    public String toString() {
        return "DynamicInfo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", wx_user_id='" + wx_user_id + '\'' +
                ", wx_user_name='" + wx_user_name + '\'' +
                ", status_cd=" + status_cd +
                ", view_count=" + view_count +
                ", pitch_count=" + pitch_count +
                ", score=" + score +
                ", dept_from=" + dept_from +
                ", dept_check=" + dept_check +
                ", dept_all_target=" + dept_all_target +
                ", dept_target='" + dept_target + '\'' +
                ", stick_cd=" + stick_cd +
                ", stick_time='" + stick_time + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", create_time='" + create_time + '\'' +
                ", update_time='" + update_time + '\'' +
                ", optType=" + optType +
                ", html='" + html + '\'' +
                ", search='" + search + '\'' +
                ", coverImage=" + coverImage +
                ", resourceImgToCoverImg='" + resourceImgToCoverImg + '\'' +
                ", coverimgs='" + coverimgs + '\'' +
                '}';
    }
}
