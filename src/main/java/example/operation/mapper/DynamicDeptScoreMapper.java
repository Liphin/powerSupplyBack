package example.operation.mapper;

import example.operation.entity.DynamicDeptScore;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/10/17.
 */
public interface DynamicDeptScoreMapper {

    /******************** select ***********************/
    //对信息数据的部门进行group by操作，计算score的值，按照score大小进行排序
    //@Select("select dept_from, sum(score) from dynamicinfo where dept_from<>1 group by dept_from order by sum(score) desc")
    //public List<Map> getDeptScore();
//    @Select("select dept_from, sum(score) from dynamic_deptscore where dept_from<>1 and status_cd=3 group by dept_from order by sum(score) desc")
//    public List<Map> getDeptScore();
//
//    //获取用户积分数据
//    @Select("select * from dynamic_deptscore where wx_user_id=#{wx_user_id} and status_cd=3 and score>0 order by create_time desc")
//    public List<Map> getUserScore(@Param("wx_user_id") String wx_user_id);
//
//
//    /******************** insert ***********************/
//    //注册操作， 默认用户名为手机号，因为注册时需要验证码，验证过该手机用户存在，绑定微信后再进行update个人信息处理
//    @Insert("insert into dynamic_deptscore(dynamic_timestamp, title, status_cd, wx_user_id, wx_user_name, dept_from, timestamp, create_time) " +
//            "values(#{dynamic_timestamp}, #{title}, #{status_cd}, #{wx_user_id}, #{wx_user_name}, #{dept_from}, #{timestamp}, #{create_time})")
//    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
//    public int insertNewDynamicDeptInfo(DynamicDeptScore dynamicDeptScore);
//
//
//    /******************** delete ***********************/
//    //删除朋友圈时删除动态部门积分表
//    @Delete("delete from dynamic_deptscore where dept_from=#(dept_from) and dynamic_timestamp=#{dynamic_timestamp}")
//    public int deleteDynamicDeptScore(DynamicDeptScore dynamicDeptScore);
//
//
//    /******************** update ***********************/
//    //更新积分
//    @Update("update dynamic_deptscore set status_cd=#{status_cd},score=#{score} where dynamic_timestamp=#{timestamp}")
//    public int updateDynamicDeptStatusScore(@Param("status_cd") String status_cd,@Param("score") String score, @Param("timestamp") String timestamp);
//
//    @Update("update dynamic_deptscore set status_cd=#{status_cd},title=#{title} where dept_from=#{dept_from} and dynamic_timestamp=#{dynamic_timestamp}")
//    public int updateDynamicDeptStatusTitle(DynamicDeptScore dynamicDeptScore);


}
