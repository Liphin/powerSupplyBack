package example.operation.mapper;


import com.alibaba.fastjson.JSON;
import example.operation.entity.DynamicInfo;
import example.operation.impl.common.CommonService;
import example.tool.common.Common;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/12.
 */
public class SqlProvider {


    /**
     * 根据项目传递过来参数进行更新项目公开程度状态
     *
     * @param map
     * @return
     */
//    public String updateProjectOpenness(Map<String, Object> map) {
//        StringBuilder stringBuilder = new StringBuilder();
//        int is_public = Integer.parseInt((map.get(Common.IS_PUBLIC)).toString());
//
//        //需检查user_id和project_id同时不为空才继续进行update操作
//        if (CommonService.checkNotNull(map.get(Common.USER_ID)) && CommonService.checkNotNull(map.get(Common.PROJECT_ID))) {
//            stringBuilder.append("update project set is_public=" + is_public);
//
//            //如果提交公开待审核状态则进行添加对应的industry类型
//            if (is_public == 1) {
//                stringBuilder.append(" , industry_code='" + map.get(Common.INDUSTRY_CODE) + "'");
//                stringBuilder.append(" , industry_sub_code='" + map.get(Common.INDUSTRY_SUB_CODE) + "'");
//
//            } else if (is_public == 0) {
//                //若设置不公开则设置industry类型等为空
//                stringBuilder.append(" , industry_code=''");
//                stringBuilder.append(" , industry_sub_code=''");
//            }
//            stringBuilder.append(" where id=" + map.get(Common.PROJECT_ID) + " and user_id=" + map.get(Common.USER_ID));
//        }
//        return stringBuilder.toString();
//    }


    /**
     * 根据dynamicPitch数值动态更新dynamic的pitch_count数据
     *
     * @param map
     * @return
     */
    public String updateDynamicPitchCount(Map<String, Object> map) {
        StringBuilder stringBuilder = new StringBuilder();
        String timestamp = String.valueOf(map.get(Common.DYNAMIC_TIMESTAMP));
        Integer dynamicPitch = Integer.parseInt(String.valueOf(map.get(Common.DYNAMIC_PITCH)));
        if (dynamicPitch == 1) {
            stringBuilder.append("update dynamicinfo set pitch_count=pitch_count+1 where timestamp='" + timestamp + "'");
        } else {
            stringBuilder.append("update dynamicinfo set pitch_count=pitch_count-1 where timestamp='" + timestamp + "'");
        }
        return stringBuilder.toString();
    }

    /**
     * 根据dynamicPitch数值动态更新dynamic的pitch_count数据
     *
     * @param map
     * @return
     */
    public String updateDynamicPitchCountHq(Map<String, Object> map) {
        StringBuilder stringBuilder = new StringBuilder();
        String timestamp = String.valueOf(map.get(Common.DYNAMIC_TIMESTAMP));
        Integer dynamicPitch = Integer.parseInt(String.valueOf(map.get(Common.DYNAMIC_PITCH)));
        if (dynamicPitch == 1) {
            stringBuilder.append("update dynamicinfohq set pitch_count=pitch_count+1 where timestamp='" + timestamp + "'");
        } else {
            stringBuilder.append("update dynamicinfohq set pitch_count=pitch_count-1 where timestamp='" + timestamp + "'");
        }
        return stringBuilder.toString();
    }


    /**
     * 获取待审核的朋友圈数据
     *
     * @param map
     * @return
     */
    public String getPendVerifyFriendCircle(Map<String, Object> map) {
        StringBuilder stringBuilder = new StringBuilder();
        Integer checkLevel = Integer.parseInt(String.valueOf(map.get(Common.CHECK_LEVEL)));
        Integer department = Integer.parseInt(String.valueOf(map.get(Common.DEPARTMENT)));
        //String wxUserId = String.valueOf(map.get(Common.WX_USER_ID));
        //List<Integer> list = JSON.parseArray(String.valueOf(map.get(Common.DEPT_FROM)), Integer.class);
        //根据审核级别动态获取相应数据
//        if (checkLevel == 2) {
//            stringBuilder.append("select * from dynamicinfo where type=7 and status_cd=2 order by create_time;");
//            //stringBuilder.append("select * from dynamicinfo where type=7 and status_cd=2 order by create_time");
//        } else {
//            stringBuilder.append("select * from dynamic_deptscore where status_cd=1 and (");
//            for (int i = 0; i < list.size(); i++) {
//                stringBuilder.append("dept_from=" + list.get(i));
//                if (i + 1 < list.size()) {
//                    stringBuilder.append(" or ");
//                }
//            }
//            stringBuilder.append(") order by create_time;");
//            //stringBuilder.append("select * from dynamicinfo where type=7 and status_cd=1 and id in (select distinct b.id from checker a , dynamicinfo b where concat('%,',b.dept_from,',%') like concat('%,',a.dept_from,',%') and a.wx_user_id= '" + wxUserId + "' and a.check_level = " + checkLevel + ") order by create_time;");
//            //stringBuilder.append("select * from dynamicinfo where type=7 and status_cd=1 and dept_from=" + department + " order by create_time");
//        }
        //根据审核级别动态获取相应数据
        if (checkLevel == 2) {
            stringBuilder.append("select * from dynamicinfo where type=7 and status_cd=2 order by create_time desc");
        } else {
            stringBuilder.append("select * from dynamicinfo where type=7 and (status_cd=1 or status_cd=2 or status_cd=5) and (dept_check=" + department + ") order by create_time desc");
        }
        return stringBuilder.toString();
    }


    /**
     * 管理员在手机客户端审核时搜索相关标题的数据
     *
     * @param map
     * @return
     */
    public String searchPendVerifyFriendCircle(Map<String, Object> map) {
        StringBuilder stringBuilder = new StringBuilder();
        Integer statusCd = Integer.parseInt(String.valueOf(map.get(Common.STATUS_CD)));
        Integer deptFrom = Integer.parseInt(String.valueOf(map.get(Common.DEPT_FROM)));
        String search = String.valueOf(map.get(Common.SEARCH));
        //根据审核级别动态获取相应数据
        if (statusCd == 2) {
            stringBuilder.append("select * from dynamicinfo where type=7 and status_cd=2 and ((title like concat('%','" + search + "','%')) or (wx_user_name like concat('%','" + search + "','%'))) order by create_time desc");
        } else {
            stringBuilder.append("select * from dynamicinfo where type=7 and status_cd=1 and dept_from=" + deptFrom + " and ((title like concat('%','" + search + "','%')) or (wx_user_name like concat('%','" + search + "','%'))) order by create_time desc");
        }
        return stringBuilder.toString();
    }

    /**
     * 管理员在手机客户端审核时搜索相关标题的数据
     *
     * @param map
     * @return
     */
    public String searchFriendCircleNewsPc(Map<String, Object> map) {
        StringBuilder stringBuilder = new StringBuilder();
        String statusCd = String.valueOf(map.get(Common.STATUS_CD));
        String search = String.valueOf(map.get(Common.SEARCH));
        //根据审核级别动态获取相应数据
        stringBuilder.append("select * from dynamicinfo where type=7 and status_cd in ("+statusCd+") and ((title like concat('%','" + search + "','%')) or (wx_user_name like concat('%','" + search + "','%'))) order by create_time desc");
        return stringBuilder.toString();
    }


    /**
     * 动态信息表中插入新闻测试数据
     *
     * @return
     */
    public String insertTestNewsRecordToDynamicInfo(Integer num) {
        String title = "测试title数据";
        StringBuilder stringBuilder = new StringBuilder();
        //日期数据初始化
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long timestamp = Long.parseLong(CommonService.getTimeStamp());
        stringBuilder.append("insert into dynamicinfo (title, type, wx_user_id, wx_user_name, status_cd, view_count, pitch_count, timestamp, create_time) values");
        for (int i = 0; i < num; i++) {
            day.setTime(day.getTime() - i * 1000);
            stringBuilder.append(" (");
            stringBuilder.append("'" + (title + i) + "' , " + (i % 6 + 1) + " , 'ZhangBin', '张斌' , " + (i % 5) + " , 0, 0, '" + (timestamp + i) + "' , '" + df.format(day) + "'");
            stringBuilder.append(")");
            if (i < (num - 1)) {
                stringBuilder.append(",");
            }
        }
        return stringBuilder.toString();
    }


    /**
     * 动态信息表中插入朋友圈测试数据
     *
     * @return
     */
    public String insertTestFriendRecordToDynamicInfo(Integer num) {
        String title = "测试title数据";
        StringBuilder stringBuilder = new StringBuilder();
        //日期数据初始化
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long timestamp = Long.parseLong(CommonService.getTimeStamp());
        stringBuilder.append("insert into dynamicinfo (title, type, wx_user_id, wx_user_name, status_cd, view_count, pitch_count, timestamp, create_time) values");
        for (int i = 0; i < num; i++) {
            day.setTime(day.getTime() - i * 1000);
            stringBuilder.append(" (");
            stringBuilder.append("'" + (title + i) + "' , 7 , 'ZhangBin', '张斌' , " + (i % 5) + " , 0, 0, '" + (timestamp + i) + "' , '" + df.format(day) + "'");
            stringBuilder.append(")");
            if (i < (num - 1)) {
                stringBuilder.append(",");
            }
        }
        return stringBuilder.toString();
    }


}





