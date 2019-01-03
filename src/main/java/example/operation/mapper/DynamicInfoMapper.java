package example.operation.mapper;

/**
 * Created by Administrator on 2017/2/8.
 */

import example.operation.entity.DynamicInfo;
import example.operation.entity.Dynamicimg;
import example.operation.entity.Manager;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

//@CacheNamespace(flushInterval = 3000)
public interface DynamicInfoMapper {

    /***********************新闻模块数据操作*********************************************************************/

    /******************** select ***********************/
    //获取全部新闻数据
    @Select("select * from dynamicinfo where type<>7 order by create_time desc")
    public List<DynamicInfo> getAllNewsInfo();

    //获取指定起始create_time以及数目的新闻数据-->返回后台前端
    @Select("select * from dynamicinfo where type<>7 and create_time<#{create_time} order by create_time desc limit 120")
    public List<DynamicInfo> getRangeNewsInfoToBg(@Param("create_time") String create_time);

    //获取指定起始create_time以及数目的新闻数据-->返回手机前端
    @Select("select * from dynamicinfo where type=#{type} and create_time<#{create_time} order by create_time desc limit 20")
    public List<DynamicInfo> getRangeNewsInfoToPhone(@Param("create_time") String create_time, @Param("type") String type);

    //获取所有新闻数据的记录数
    @Select("select count(*) from dynamicinfo where type<>7")
    public int getNewsNum();

    //获取指定timestamp的新闻数据
    @Select("select * from dynamicinfo where timestamp=#{dynamic_timestamp}")
    public DynamicInfo getSingleNewsDetailInfo(@Param("dynamic_timestamp") String dynamic_timestamp);

    //获取指定timestamp的新闻数据
    @Select("select * from dynamicinfo where timestamp=#{timestamp}")
    public DynamicInfo getDynamicInfo(String timestamp);

    //对信息数据的部门进行group by操作，计算score的值，按照score大小进行排序
    @Select("select dept_from, sum(score) from dynamicinfo where dept_from>1 group by dept_from order by sum(score) desc")
    public List<Map> getDeptScore();

    //获取所有积分信息
    @Select("select wx_user_id,wx_user_name,dept_from,score from dynamicinfo where dept_from>1 and score>0")
    public List<Map> getDeptScoreDetail();

    //对指定部门的用户进行group by操作，计算score的值，按照score大小进行排序
    @Select("select wx_user_id, wx_user_name, sum(score) from dynamicinfo where dept_from=#{dept_from} group by wx_user_id order by sum(score) desc")
    public List<Map> getDeptUserScore(int dept_from);

    //获取用户积分数据
    @Select("select * from dynamicinfo where wx_user_id=#{wx_user_id} and score>0 order by create_time desc")
    public List<DynamicInfo> getUserScore(DynamicInfo dynamicInfo);
    //public List<Map> getUserScore(@Param("wx_user_id") String wx_user_id);


    //获取动态信息的状态
    @Select("select * from dynamicinfo where timestamp=#{timestamp} ")
    public DynamicInfo getDynamicStatus(@Param("timestamp") String timestamp);

    //获取动态信息的状态
    @Select("select * from dynamicinfo where timestamp=#{timestamp} ")
    public DynamicInfo selectFriendCircleInfo(@Param("timestamp") String timestamp);

    //获取动态信息的更新时间
    @Select("select * from dynamicinfo where timestamp=#{timestamp} ")
    public DynamicInfo getDynamicUpdateTime(@Param("timestamp") String timestamp);

    //搜索指定匹配字段的数据
    @Select("select * from dynamicinfo where type=#{type} and status_cd=#{status_cd} and " +
            "((title like concat('%',#{search},'%')) or (wx_user_name like concat('%',#{search},'%')))  order by create_time desc")
    public List<DynamicInfo> searchNews(DynamicInfo dynamicInfo);

    //搜索指定用户指定匹配字段的数据
    @Select("select * from dynamicinfo where type=#{type} and wx_user_id=#{wx_user_id} and " +
            "(title like concat('%',#{search},'%'))  order by create_time desc")
    public List<DynamicInfo> searchUserNews(DynamicInfo dynamicInfo);

    //获取指定title搜索的新闻发布数据
    @Select("select * from dynamicinfo where type<>7 and (title like concat('%',#{search},'%')) order by create_time desc")
    public List<DynamicInfo> searchNewsList(@Param("search") String search);



    /******************** insert ***********************/
    //注册操作， 默认用户名为手机号，因为注册时需要验证码，验证过该手机用户存在，绑定微信后再进行update个人信息处理
    @Insert("insert into dynamicinfo(type, title, wx_user_id, wx_user_name, status_cd, dept_from, dept_check, dept_all_target, dept_target, timestamp, create_time,update_time) " +
            "values(#{type}, #{title}, #{wx_user_id}, #{wx_user_name}, #{status_cd}, #{dept_from}, #{dept_check}, #{dept_all_target}, #{dept_target}, #{timestamp}, #{create_time},#{update_time})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public int insertNewNews(DynamicInfo dynamicInfo);

    //插入测试数据操作
    @InsertProvider(type = SqlProvider.class, method = "insertTestNewsRecordToDynamicInfo")
    public int insertTestNewsRecordToDynamicInfo(Integer num);



    /******************** delete ***********************/
    //测试Junit时删除新添加的user数据
    @Delete("delete from dynamicinfo where id=#{id}")
    public int deleteNews(@Param("id") Integer id);


    /******************** update ***********************/
    //管理员更新news信息操作
    @Update("update dynamicinfo set type=#{type}, title=#{title}, status_cd=3 ,dept_all_target=#{dept_all_target}, dept_target=#{dept_target}, update_time=#{update_time} where id=#{id}")
    public int updateNews(DynamicInfo dynamicInfo);

    //更新动态信息表的总浏览次数
    @Update("update dynamicinfo set view_count=view_count+1 where timestamp=#{dynamic_timestamp}")
    public int updateDynamicViewCount(@Param("dynamic_timestamp") String dynamic_timestamp);

    //更新用户对该新闻信息的点赞
    @UpdateProvider(type = SqlProvider.class, method = "updateDynamicPitchCount")
    public int updateDynamicPitchCount(Map<String, Object> map);

    //更新用户新闻数据置顶功能
    @Update("update dynamicinfo set stick_cd=#{stick_cd}, stick_time=#{stick_time} where timestamp=#{timestamp}")
    public int setDynamicStickInfo(@Param("stick_cd") String stick_cd, @Param("stick_time") String stick_time, @Param("timestamp") String timestamp);


    /***********************朋友圈数据操作***************************************************************************/

    /******************** select ***********************/
    //获取朋友圈中所有待审核的新闻数据， 暂时不做限制20条，直接获取全部信息
    @SelectProvider(type = SqlProvider.class, method = "getPendVerifyFriendCircle")
    public List<DynamicInfo> getPendVerifyFriendCircle(Map<String, Object> map);

    //管理员在手机客户端审核时搜索相关标题的数据
    @SelectProvider(type = SqlProvider.class, method = "searchPendVerifyFriendCircle")
    public List<DynamicInfo> searchPendVerifyFriendCircle(Map<String, Object> map);

    //获取指定起始create_time以及数目的朋友圈数据-->返回后台前端
    @Select("select * from dynamicinfo where type=7 and create_time<#{create_time} order by create_time desc limit 120")
    public List<DynamicInfo> getRangeFriendCircleInfoToBg(@Param("create_time") String create_time);

    //获取所有朋友圈数据的记录数
    @Select("select count(*) from dynamicinfo where type=7")
    public int getFriendNum();

    //获取指定timestamp的新闻数据
    @Select("select * from dynamicimg where dynamic_timestamp=#{dynamic_timestamp}")
    public Dynamicimg getFriendImage(@Param("dynamic_timestamp") String dynamic_timestamp);

    //获取全部朋友圈数据
    @Select("select * from dynamicinfo where type=7 order by create_time desc")
    public List<DynamicInfo> getAllFriendInfo();

    //获取指定起始位置以及数目的朋友圈数据
    @Select("select * from dynamicinfo where type=#{type} and create_time<#{create_time} order by create_time desc limit 5")
    public List<DynamicInfo> getRangeFriendInfo(@Param("create_time") String create_time, @Param("type") String type);


    //获取朋友圈某条朋友圈图片数
    @Select("select image_num from dynamicinfo where timestamp=#{timestamp}")
    public int getFriendImageNum();

    //获取指定timestamp的朋友圈数据
    @Select("select * from dynamicinfo where timestamp=#{timestamp}")
    public DynamicInfo getSingleFriendDetailInfo(String timestamp);

    //获取指定用户的朋友圈数据
    @Select("select * from dynamicinfo where type=7 and wx_user_id=#{wx_user_id} and create_time<#{create_time} order by create_time desc limit 20 ")
    public DynamicInfo getUserFriendInfoToPhone(@Param("wx_user_id") String wx_user_id, @Param("create_time") String create_time);

    //获取指定范围内的发布的朋友圈数据
    @Select("select * from dynamicinfo where type=7 and status_cd=3 and create_time<#{create_time} order by create_time desc limit 20 ")
    public List<DynamicInfo> getPublishedFriendToPhone(@Param("create_time") String create_time);

    //获取指定范围内的发布的朋友圈数据
    @Select("select * from dynamicinfo where type=7 and ((title like concat('%',#{search},'%')) or (wx_user_name like concat('%',#{search},'%'))) order by create_time desc")
    public List<DynamicInfo> searchFriendCircleNews(@Param("search") String search);

    //获取指定范围内的发布的朋友圈数据
    @Select("select * from dynamicinfo where type=7 and status_cd in #{status_cd} and((title like concat('%',#{search},'%')) or (wx_user_name like concat('%',#{search},'%'))) order by create_time desc")
    public List<DynamicInfo> searchFriendCircleNewsPc(@Param("search") String search,@Param("status_cd") String status_cd);

    //获取指定范围内的发布的朋友圈数据
    @Select("select * from dynamicinfo where type=7 and (status_cd=1 or status_cd=4) and create_time<#{create_time} order by create_time desc limit 20")
    public List<DynamicInfo> getFirstCheckAndRejectNews(String createTime);

    //获取指定范围内的发布的朋友圈数据
    @Select("select * from dynamicinfo where type=7 and status_cd=3 and create_time<#{create_time} order by create_time desc limit 20")
    public List<DynamicInfo> getSuccessNews(String createTime);



    /******************** insert ***********************/
    //注册操作， 默认用户名为手机号，因为注册时需要验证码，验证过该手机用户存在，绑定微信后再进行update个人信息处理
//    @Insert("insert into dynamicinfo(type, title, wx_user_id, status_cd, dept_from, dept_target, timestamp, create_time) " +
//            "values(#{type}, #{title}, #{wx_user_id}, #{status_cd}, #{dept_from}, #{dept_target}, #{timestamp}, #{create_time})")
//    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
//    public int insertNewFriend(DynamicInfo dynamicInfo);

    //插入朋友圈测试数据操作
    @InsertProvider(type = SqlProvider.class, method = "insertTestFriendRecordToDynamicInfo")
    public int insertTestFriendRecordToDynamicInfo(Integer num);


    /******************** delete ***********************/
    //测试Junit时删除新添加的user数据
    @Delete("delete from dynamicinfo where timestamp=#{timestamp}")
    public int deleteFriend(String timestamp);


    /******************** update ***********************/
    //管理员更新朋友圈信息操作
    @Update("update dynamicinfo set title=#{title}, status_cd=#{status_cd}, update_time=#{update_time}," +
            "dept_all_target=#{dept_all_target}, dept_target=#{dept_target} where timestamp=#{timestamp}")
    public int updateFriend(DynamicInfo dynamicInfo);

    //设置信息的状态
    @Update("update dynamicinfo set status_cd=#{status_cd}, score=#{score}, update_time=#{update_time} where timestamp=#{timestamp}")
    public int updateDynamicInfoStatus(@Param("status_cd") String status_cd, @Param("score") String score,
                                       @Param("timestamp") String timestamp, @Param("update_time") String update_time);

    //设置信息的修改数据
    @Update("update dynamicinfo set score=#{score}, update_time=#{update_time} where timestamp=#{timestamp}")
    public int updateDynamicInfoCheck(@Param("score") String score, @Param("timestamp") String timestamp, @Param("update_time") String update_time);


    //更新审核员审核保存新的HTML文件
    @Update("update dynamicinfo set title=#{title}, update_time=#{update_time} where timestamp=#{timestamp}")
    public int updateDynamicInfoTitle(@Param("title") String title, @Param("timestamp") String timestamp, @Param("update_time") String update_time);
}














