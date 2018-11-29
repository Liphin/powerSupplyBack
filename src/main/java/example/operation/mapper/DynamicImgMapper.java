package example.operation.mapper;

import example.operation.entity.DynamicInfo;
import example.operation.entity.Dynamicimg;
import org.apache.ibatis.annotations.*;

import javax.ws.rs.DELETE;

/**
 * Created by Administrator on 2018/10/3.
 */
public interface DynamicImgMapper {

    /************************** select ***************************************/
    //管理者登录管理平台，验证账号密码是否正确
    @Select("select * from dynamicimg where dynamic_timestamp=#{timestamp}")
    public Dynamicimg getFriendCircleResourceImg(String timestamp);



    /******************** insert ***********************/
    //新增朋友圈，同时新增动态信息图片表，记录图片个数和图片名字
    @Insert("insert into dynamicimg(dynamic_timestamp, img_num, imgname_1, imgname_2, imgname_3, imgname_4, imgname_5, imgname_6, imgname_7, imgname_8, imgname_9, imgname_10, create_time) " +
            "values(#{dynamic_timestamp}, #{img_num}, #{imgname_1}, #{imgname_2}, #{imgname_3}, #{imgname_4}, #{imgname_5}, #{imgname_6}, #{imgname_7}, #{imgname_8}, #{imgname_9}, #{imgname_10}, #{create_time})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public int insertNewFriendImg(Dynamicimg dynamicimg);



    /******************** insert ***********************/
    //删除资源文件表
    @Delete("delete from dynamicimg where dynamic_timestamp=#{timestamp}")
    public int deleteResourceImg(String timestamp);


    /******************** update ***********************/
    //更新朋友圈时同时更新动态图片表
    @Update("update dynamicimg set img_num=#{img_num}, imgname_1=#{imgname_1}, imgname_2=#{imgname_2},"+
            "imgname_3=#{imgname_3}, imgname_4=#{imgname_4}, imgname_5=#{imgname_5},"+
            "imgname_6=#{imgname_6}, imgname_7=#{imgname_7}, imgname_8=#{imgname_8},"+
            "imgname_9=#{imgname_9}, imgname_10=#{imgname_10} where dynamic_timestamp=#{dynamic_timestamp}")
    public int updateFriendImg(Dynamicimg dynamicimg);

}
