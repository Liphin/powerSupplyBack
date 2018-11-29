package example.operation.mapper;

/**
 * Created by Administrator on 2017/2/8.
 */
import org.apache.ibatis.annotations.*;
//@CacheNamespace(flushInterval = 3000)

public interface UserMapper {

    /********************以下是选择user操作***********************/
    //根据user_id获得该user的全部信息
    @Select("select * from user where wx_user_id=#{wx_user_id}")
    public Object selectUserByWxId(String wx_user_id);


    /********************以下是插入user操作***********************/



    /********************以下是删除user操作***********************/



    /********************以下是更新user操作***********************/


}
