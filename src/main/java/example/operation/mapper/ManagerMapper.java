package example.operation.mapper;

/**
 * Created by Administrator on 2017/2/8.
 */

import example.operation.entity.Manager;
import org.apache.ibatis.annotations.*;
//@CacheNamespace(flushInterval = 3000)

public interface ManagerMapper {

    /********************以下是选择user操作***********************/
    //管理者登录管理平台，验证账号密码是否正确
    @Select("select * from manager where manager_account=#{account} and password=#{password} ")
    public Manager managerLogin(@Param("account") String account, @Param("password") String password);

    //根据管理者id获取管理者信息数据
    @Select("select * from manager where wx_user_id=#{wx_user_id}")
    public Manager getManagerData(String wx_user_id);

//    /********************以下是插入user操作***********************/
//    //注册操作， 默认用户名为手机号，因为注册时需要验证码，验证过该手机用户存在，绑定微信后再进行update个人信息处理
//    @Insert("insert into user(timestamp,portrait,user_name,email,password,phone,resource_total) " +
//            "values(#{timestamp},#{portrait},#{user_name},#{email},#{password},#{phone},#{resource_total})")
//    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
//    public int registerNewAccount(@Param("user_id") Integer user_id, @Param("file_type") String file_type);
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
