package example.operation.mapper;

import example.operation.entity.DynamicInfoHq;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface DynamicInfoHqMapper {
    /***********************新闻模块数据操作*********************************************************************/

    /******************** select ***********************/
    //获取指定范围后勤服务数据
    //获取指定起始create_time以及数目的新闻数据-->返回后台前端
    @Select("select * from dynamicinfohq where type=9 and create_time<#{create_time} order by create_time desc limit 120")
    public List<DynamicInfoHq> getRangeNewsInfoToBgHq(@Param("create_time") String create_time);

    //获取所有新闻数据的记录数
    @Select("select count(*) from dynamicinfohq where type=9")
    public int getNewsNumHq();

    //获取指定title搜索的新闻发布数据
    @Select("select * from dynamicinfoHq where type=9 and (title like concat('%',#{search},'%')) order by create_time desc")
    public List<DynamicInfoHq> searchNewsListHq(@Param("search") String search);

    //获取指定timestamp的新闻数据
    @Select("select * from dynamicinfoHq where timestamp=#{dynamic_timestamp}")
    public DynamicInfoHq getSingleNewsDetailInfoHq(@Param("dynamic_timestamp") String dynamic_timestamp);

    /******************** insert ***********************/
    //注册操作， 默认用户名为手机号，因为注册时需要验证码，验证过该手机用户存在，绑定微信后再进行update个人信息处理
    @Insert("insert into dynamicinfohq(type, title, wx_user_id, wx_user_name, status_cd, dept_from, dept_check, dept_all_target, dept_target, timestamp, create_time,update_time) " +
            "values(#{type}, #{title}, #{wx_user_id}, #{wx_user_name}, #{status_cd}, #{dept_from}, #{dept_check}, #{dept_all_target}, #{dept_target}, #{timestamp}, #{create_time},#{update_time})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public int insertNewNewsHq(DynamicInfoHq dynamicInfoHq);


    /******************** delete ***********************/
    //测试Junit时删除新添加的user数据
    @Delete("delete from dynamicinfohq where id=#{id}")
    public int deleteNewsHq(@Param("id") Integer id);


    /******************** update ***********************/
    //管理员更新news信息操作
    @Update("update dynamicinfohq set type=#{type}, title=#{title}, status_cd=3 ,dept_all_target=#{dept_all_target}, dept_target=#{dept_target}, update_time=#{update_time} where id=#{id}")
    public int updateNewsHq(DynamicInfoHq dynamicInfoHq);

    //更新用户对该新闻信息的点赞
    @UpdateProvider(type = SqlProvider.class, method = "updateDynamicPitchCountHq")
    public int updateDynamicPitchCountHq(Map<String, Object> map);

    //更新动态信息表的总浏览次数
    @Update("update dynamicinfohq set view_count=view_count+1 where timestamp=#{dynamic_timestamp}")
    public int updateDynamicViewCountHq(@Param("dynamic_timestamp") String dynamic_timestamp);

    //更新用户新后勤服务数据置顶功能
    @Update("update dynamicinfohq set stick_cd=#{stick_cd}, stick_time=#{stick_time} where timestamp=#{timestamp}")
    public int setDynamicStickInfoHq(@Param("stick_cd") String stick_cd, @Param("stick_time") String stick_time, @Param("timestamp") String timestamp);
}
