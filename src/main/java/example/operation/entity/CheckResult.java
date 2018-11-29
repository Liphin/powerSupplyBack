package example.operation.entity;

/**
 * Created by Administrator on 2018/9/15.
 */
public class CheckResult {

    private int id;
    private int check_level;
    private String dynamic_timestamp;
    private int check_status;
    private String checker_wx_user_id;
    private String checker_wx_user_name;
    private String checker_comment;
    private String checker_modify_comment;
    private int score;
    private String timestamp;
    private String create_time;
    private String update_time;

    public CheckResult() {
    }

    public CheckResult(int check_level, String dynamic_timestamp, int check_status, String checker_wx_user_id,
                       String checker_wx_user_name, String checker_comment, String checker_modify_comment, int score,
                       String timestamp, String create_time) {
        this.check_level = check_level;
        this.dynamic_timestamp = dynamic_timestamp;
        this.check_status = check_status;
        this.checker_wx_user_id = checker_wx_user_id;
        this.checker_wx_user_name = checker_wx_user_name;
        this.checker_comment = checker_comment;
        this.checker_modify_comment = checker_modify_comment;
        this.score = score;
        this.timestamp = timestamp;
        this.create_time = create_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCheck_level() {
        return check_level;
    }

    public void setCheck_level(int check_level) {
        this.check_level = check_level;
    }

    public String getDynamic_timestamp() {
        return dynamic_timestamp;
    }

    public void setDynamic_timestamp(String dynamic_timestamp) {
        this.dynamic_timestamp = dynamic_timestamp;
    }

    public int getCheck_status() {
        return check_status;
    }

    public void setCheck_status(int check_status) {
        this.check_status = check_status;
    }

    public String getChecker_wx_user_id() {
        return checker_wx_user_id;
    }

    public void setChecker_wx_user_id(String checker_wx_user_id) {
        this.checker_wx_user_id = checker_wx_user_id;
    }

    public String getChecker_wx_user_name() {
        return checker_wx_user_name;
    }

    public void setChecker_wx_user_name(String checker_wx_user_name) {
        this.checker_wx_user_name = checker_wx_user_name;
    }

    public String getChecker_comment() {
        return checker_comment;
    }

    public void setChecker_comment(String checker_comment) {
        this.checker_comment = checker_comment;
    }

    public String getChecker_modify_comment() {
        return checker_modify_comment;
    }

    public void setChecker_modify_comment(String checker_modify_comment) {
        this.checker_modify_comment = checker_modify_comment;
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

    @Override
    public String toString() {
        return "CheckResult{" +
                "id=" + id +
                ", check_level=" + check_level +
                ", dynamic_timestamp='" + dynamic_timestamp + '\'' +
                ", check_status='" + check_status + '\'' +
                ", checker_wx_user_id='" + checker_wx_user_id + '\'' +
                ", checker_wx_user_name='" + checker_wx_user_name + '\'' +
                ", checker_comment='" + checker_comment + '\'' +
                ", checker_modify_comment='" + checker_modify_comment + '\'' +
                ", score=" + score +
                ", timestamp='" + timestamp + '\'' +
                ", create_time='" + create_time + '\'' +
                ", update_time='" + update_time + '\'' +
                '}';
    }
}
