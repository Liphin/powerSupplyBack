package example.operation.mapper;

/**
 * Created by Administrator on 2017/2/8.
 */

import example.operation.entity.CheckResult;
import example.operation.entity.Checker;
import example.operation.entity.Manager;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
//@CacheNamespace(flushInterval = 3000)

public interface CheckResultMapper {

    /********************以下是select操作***********************/
    //管理者登录管理平台，验证账号密码是否正确
    @Select("select * from checkresult where dynamic_timestamp=#{dynamic_timestamp}")
    public List<CheckResult> getHistoryCheckResult(@Param("dynamic_timestamp") String dynamic_timestamp);

    //获取初审通过的信息
    @Select("select * from checkresult where dynamic_timestamp=#{dynamic_timestamp} and check_level=1 and check_status=1 order by update_time desc limit 1")
    public CheckResult getFirstCheckerInfo(CheckResult checkResult);


    /********************以下是insert操作***********************/
    //插入check result数据
    @Insert("insert into checkresult(check_level, dynamic_timestamp, check_status, checker_wx_user_id, " +
            "checker_wx_user_name, checker_comment, checker_modify_comment, score, timestamp, create_time) " +
            "values(#{check_level}, #{dynamic_timestamp}, #{check_status}, #{checker_wx_user_id}," +
            "#{checker_wx_user_name}, #{checker_comment}, #{checker_modify_comment}, #{score}, #{timestamp}, #{create_time})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public int insertNewCheckResult(CheckResult checkResult);
//
//
//    /********************以下是删除user操作***********************/
//    //测试Junit时删除新添加的user数据
//    @Delete("delete from user where email=#{email}")
//    public int deleteUserInDb(int user_id);
//
//
//    /********************以下是更新user操作***********************/
//    //更新用户个人信息
//    @Update("update user set user_name=#{user_name},role=#{role},phone=#{phone},nation=#{nation},portrait=#{portrait} where id=#{id}")
//    public int updateUserInfo(int user_id);

}
