package example.operation.mapper;

/**
 * Created by Administrator on 2017/2/8.
 */

import example.operation.entity.CheckResult;
import example.operation.entity.Checker;
import example.operation.entity.Manager;
import org.apache.ibatis.annotations.*;

import java.util.List;
//@CacheNamespace(flushInterval = 3000)

public interface CheckerMapper {

    /************************** select ***************************************/
    //管理者登录管理平台，验证账号密码是否正确
    @Select("select * from checker where wx_user_id=#{wx_user_id}")
    public Checker getChecker(Checker checker);
    //public Checker getChecker(@Param("wx_user_id") String wx_user_id);

    //获取初审审核员的数据
    @Select("select * from checker where dept_check=#{dept_check} and check_level=1")
    public List<Checker> getFirstChecker(@Param("dept_check") int dept_check);

    //获取终审审核员的数据
    @Select("select * from checker where check_level=2")
    public List<Checker> getFinalChecker(@Param("check_level") String check_level);

    //获取所有管理员相关数据
    @Select("select * from checker")
    public List<Checker> getAllCheckerInfo();


    /************************** insert **************************************/
    //插入审核员操作
    @Insert("insert into checker(wx_user_name, wx_user_id, dept_from, dept_check, check_level, manager_wx_user_id, timestamp, create_time) " +
                "values(#{wx_user_name}, #{wx_user_id},#{dept_from}, #{dept_check}, #{check_level}, #{manager_wx_user_id}, #{timestamp}, #{create_time})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public int grantCheckerRight(Checker checker);


    /************************** delete *************************************/
    //测试Junit时删除新添加的user数据
    @Delete("delete from checker where wx_user_id=#{wx_user_id}")
    public int revokeCheckerRight(String wx_user_id);

//
//    /********************以下是更新user操作***********************/
//    //更新用户个人信息
//    @Update("update user set user_name=#{user_name},role=#{role},phone=#{phone},nation=#{nation},portrait=#{portrait} where id=#{id}")
//    public int updateUserInfo(int user_id);

}
