package example.operation.impl.friendcircle;

import com.alibaba.fastjson.JSON;
import example.operation.entity.*;
import example.operation.entity.response.ResponseData;
import example.operation.entity.response.StatusCode;
import example.operation.impl.common.CommonImpl;
import example.operation.impl.common.CommonService;
import example.operation.impl.news.NewsOpt;
import example.tool.common.Assemble;
import example.tool.common.Common;
import example.tool.common.Mapper;
import example.tool.config.GlobalConfig;
import example.tool.parser.form.FormData;
import example.tool.util.MybatisUtils;
import io.netty.handler.codec.http.multipart.FileUpload;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.print.DocFlavor;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Administrator on 2018/9/14.
 */
public class FriendCircleOpt {

    private static Logger logger = LoggerFactory.getLogger(FriendCircleOpt.class);


    /**
     * 获取所有新闻数据
     *
     * @param msg http传递的数据
     */
    public static ResponseData getRangeFriendCircleListToBg(Object msg) {
        ResponseData responseData = new ResponseData(StatusCode.ERROR.getValue());
        SqlSession sqlSession = MybatisUtils.getSession();
        String message = "";
        try {
            Map<String, Object> map = FormData.getParam(msg);
            List<DynamicInfo> newsList = sqlSession.selectList(Mapper.GET_RANGE_FRIEND_CIRCLE_INFO_TO_BG, map);
            int newsNum = sqlSession.selectOne(Mapper.GET_FRIEND_NUM);
            //检查是否查找到指定起始位置及数目的新闻并返回相应结果
            if (CommonService.checkNotNull(newsList)) {
                //设置回传的返回数据
                Map<String, Object> data = new HashMap<String, Object>(2);
                data.put(Common.TOTAL_NUM, newsNum);
                data.put(Common.FRIEND_CIRCLE_LIST_DATA, newsList);
                Assemble.responseSuccessSetting(responseData, data);

            } else {
                message = "friend circle info not found";
                FriendCircleOpt.logger.warn(message);
                Assemble.responseErrorSetting(responseData, 401, message);
            }

        } catch (Exception e) {
            message = "sys error";
            FriendCircleOpt.logger.debug(message, e);
            Assemble.responseErrorSetting(responseData, 500, message);

        } finally {
            CommonService.databaseCommitClose(sqlSession, responseData, false);
        }
        return responseData;
    }

    /**
     * 获取该用户发表的朋友圈所有数据
     *
     * @param msg http传递的数据
     */
    public static ResponseData getUserFriendInfoToPhone(Object msg) {
        ResponseData responseData = new ResponseData(StatusCode.ERROR.getValue());
        SqlSession sqlSession = MybatisUtils.getSession();
        String message = "";
        try {
            Map<String, Object> map = FormData.getParam(msg);
            List<DynamicInfo> newsList = sqlSession.selectList(Mapper.GET_FRIEND_USER_INFO, map);
            //检查是否查找到指定起始位置及数目的新闻并返回相应结果
            if (CommonService.checkNotNull(newsList)) {
                //设置回传的返回数据
                Assemble.responseSuccessSetting(responseData, newsList);

            } else {
                message = "friend circle info not found";
                FriendCircleOpt.logger.warn(message);
                Assemble.responseErrorSetting(responseData, 401, message);
            }

        } catch (Exception e) {
            message = "sys error";
            FriendCircleOpt.logger.debug(message, e);
            Assemble.responseErrorSetting(responseData, 500, message);

        } finally {
            CommonService.databaseCommitClose(sqlSession, responseData, false);
        }
        return responseData;
    }

    /**
     * 获取某条动态对应的图片信息
     *
     * @param msg http传递的数据
     */
    public static ResponseData selectFriendCircleImg(Object msg) {
        ResponseData responseData = new ResponseData(StatusCode.ERROR.getValue());
        SqlSession sqlSession = MybatisUtils.getSession();
        String message = "";
        try {
            Map<String, Object> map = FormData.getParam(msg);
            Dynamicimg dynamicimg = sqlSession.selectOne(Mapper.GET_FRIEND_IMAGE, map);
            //检查是否查找到指定起始位置及数目的新闻并返回相应结果
            if (CommonService.checkNotNull(dynamicimg)) {
                Map<String, Object> data = new HashMap<>();
                data.put(Common.DYNAMICIMG, dynamicimg); //添加该用户对该新闻的行为数据
                Assemble.responseSuccessSetting(responseData, data);
            } else {
                message = "news info not found";
                FriendCircleOpt.logger.warn(message);
                Assemble.responseErrorSetting(responseData, 401, message);
            }

        } catch (Exception e) {
            message = "get dynamic info error";
            FriendCircleOpt.logger.debug(message, e);
            Assemble.responseErrorSetting(responseData, 500, message);

        } finally {
            CommonService.databaseCommitClose(sqlSession, responseData, false);
        }
        return responseData;
    }

    /**
     * 获取某条动态对应的图片信息
     *
     * @param msg http传递的数据
     */
    public static ResponseData selectFriendCircleInfo(Object msg) {
        ResponseData responseData = new ResponseData(StatusCode.ERROR.getValue());
        SqlSession sqlSession = MybatisUtils.getSession();
        String message = "";
        try {
            Map<String, Object> map = FormData.getParam(msg);
            DynamicInfo dynamicInfo = sqlSession.selectOne(Mapper.GET_FRIEND_CIRCLE_INFO, map);
            //检查是否查找到指定起始位置及数目的新闻并返回相应结果
            if (CommonService.checkNotNull(dynamicInfo)) {
                Map<String, Object> data = new HashMap<>();
                data.put(Common.DYNAMICINFO, dynamicInfo); //添加该用户对该新闻的行为数据
                Assemble.responseSuccessSetting(responseData, data);
            } else {
                Map<String, Object> data = new HashMap<>();
                message = "news info not found";
                FriendCircleOpt.logger.warn(message);
                Assemble.responseSuccessSetting(responseData, data);
            }

        } catch (Exception e) {
            message = "get dynamic info error";
            FriendCircleOpt.logger.debug(message, e);
            Assemble.responseErrorSetting(responseData, 500, message);

        } finally {
            CommonService.databaseCommitClose(sqlSession, responseData, false);
        }
        return responseData;
    }


    /**
     * 保存朋友圈的HTML数据
     *
     * @param msg
     * @return
     */
    public static ResponseData saveFriendCircle(Object msg) {
        ResponseData responseData = new ResponseData(StatusCode.ERROR.getValue());
        SqlSession sqlSession = MybatisUtils.getSession();
        String message = "";
        int num = 0;
        int num_img = 0;
        try {
            //前端新闻数据获取
            DynamicInfo dynamicInfo = (DynamicInfo) FormData.getParam(msg, DynamicInfo.class);
            dynamicInfo.setType(Common.FRIEND_CIRCLE_TYPE);

            String s = dynamicInfo.getCoverimgs();
            Dynamicimg dynamicimg = JSON.parseObject(s, Dynamicimg.class);
            //String UpdateTime="";
            //根据操作类型动态进行新闻插入或更新数据库等操作
            if (dynamicInfo.getOptType() == Common.DYNAMIC_OPT_INSERT) {
                //设置时间戳、创建时间和更新时间等
                dynamicInfo.setCreate_time(CommonService.getDateTime());
                dynamicInfo.setUpdate_time(CommonService.getDateTime());
                //UpdateTime = dynamicInfo.getUpdate_time();

                num = sqlSession.insert(Mapper.INSERT_NEW_NEWS, dynamicInfo);
                num_img = sqlSession.insert(Mapper.INSERT_NEW_FRIENDIMG, dynamicimg);

                //循环该用户所在所有部门，插入消息数据到dynamic_deptscore表中
                /*DynamicDeptScore dynamicDeptScore = new DynamicDeptScore(dynamicInfo);
                List<Integer> list = JSON.parseArray(dynamicInfo.getDept_from(), Integer.class);
                for (Integer deptFrom : list) {
                    dynamicDeptScore.setDept_from(deptFrom);
                    sqlSession.insert(Mapper.INSERT_NEW_DYNAMIC_DEPT_INFO, dynamicDeptScore);
                }*/

            } else if (dynamicInfo.getOptType() == Common.DYNAMIC_OPT_UPDATE) {
                //更新数据
                dynamicInfo.setUpdate_time(CommonService.getDateTime());
                //UpdateTime = dynamicInfo.getUpdate_time();
                num = sqlSession.update(Mapper.UPDATE_FRIEND, dynamicInfo);
                num_img = sqlSession.update(Mapper.UPDATE_FRIENDIMG, dynamicimg);

                //循环该用户所在所有部门，更新消息数据到dynamic_deptscore表中
                /*DynamicDeptScore dynamicDeptScore = new DynamicDeptScore(dynamicInfo);
                List<Integer> list = JSON.parseArray(dynamicInfo.getDept_from(), Integer.class);
                for (Integer deptFrom : list) {
                    dynamicDeptScore.setDept_from(deptFrom);
                    //TODO sqlSession.insert(Mapper.INSERT_NEW_DYNAMIC_DEPT_INFO, dynamicDeptScore);
                    num_dept = sqlSession.update(Mapper.UPDATE_DYNAMIC_DEPT_STATUS_TITLE, dynamicDeptScore);
                    if (num_dept <= 0){
                        throw new Exception("部门"+deptFrom+":插入错误");
                    }
                }*/

                //如果是管理员手动发布该朋友圈则需插入到checkResult列表
                insertManagerVerifyCheckResult(dynamicInfo, sqlSession);
            }

            //根据更新的数据number动态进行不同返回
            if (num > 0 && num_img > 0) {
//                //把资源文件第一张图片拷贝作为朋友圈新闻的封面图，
//                //场景： 1、用户创建项目场景， 2、用户更新项目场景
//                //不适用场景： 3、管理员直接保存发布该朋友圈
//                if (dynamicInfo.getStatus_cd() != Common.FRIENDCHECK_PASS_STATUS) {
//                    resourceImgToCoverImg(dynamicimg);
//                }
                //应用场景： 1、用户创建项目场景， 2、用户更新项目场景， 3、管理员直接保存发布该朋友圈
                resourceImgToCoverImg(dynamicimg);

                //保存新闻得到文件操作
                saveHtmlFile(dynamicInfo);
//                Map<String, Object> data = new HashMap<String, Object>(2);
//                data.put(Common.UPDATE_TIME, UpdateTime);
                Assemble.responseSuccessSetting(responseData, null);

            } else {
                message = "database influence record error";
                FriendCircleOpt.logger.warn(message);
                Assemble.responseErrorSetting(responseData, 401, message);
            }

        } catch (Exception e) {
            message = "saveNewsData system error";
            FriendCircleOpt.logger.error(message, e);
            Assemble.responseErrorSetting(responseData, 500, message);

        } finally {
            CommonService.databaseCommitClose(sqlSession, responseData, true);
        }
        return responseData;
    }


    /**
     * 如果是管理员手动发布该朋友圈则需插入到checkResult列表
     */
    private static void insertManagerVerifyCheckResult(DynamicInfo dynamicInfo, SqlSession sqlSession) {

        if (CommonService.checkNotNull(dynamicInfo.getWx_user_id())) {

            //查询该wx_user_id是否为管理员，是则插入check_result表
            Manager manager = sqlSession.selectOne(Mapper.GET_MANAGER_DATA, dynamicInfo.getWx_user_id());

            if (CommonService.checkNotNull(manager)) {
                //设置管理员审核数据
                CheckResult checkResult = new CheckResult();
                checkResult.setCheck_level(Common.MANAGER_CHECK_LEVEL);
                checkResult.setDynamic_timestamp(dynamicInfo.getTimestamp());
                checkResult.setCheck_status(Common.CHECK_PASS_STATUS);
                checkResult.setChecker_wx_user_id(manager.getWx_user_id());
                checkResult.setChecker_wx_user_name(manager.getWx_user_name());
                checkResult.setChecker_comment(Common.EMPTY);
                checkResult.setChecker_modify_comment(Common.EMPTY);
                checkResult.setScore(Common.DEFAULT_SCORE);
                checkResult.setTimestamp(CommonService.getTimeStamp());
                checkResult.setCreate_time(CommonService.getDateTime());
                int checkReusltNum = sqlSession.insert(Mapper.INSERT_NEW_CHECK_RESULT, checkResult);
            }
        }
    }


    /**
     * 保存HTML文件操作
     *
     * @param dynamicInfo 动态朋友圈数据
     * @throws Exception 保存出错抛出异常信息
     */
    private static void saveHtmlFile(DynamicInfo dynamicInfo) throws Exception {
        FileOutputStream out = null;
        try {
            //获取保存到系统的路径
            String dynamicInfoHtmlPath = GlobalConfig.getProperties(Common.DYNAMICINFOS_SYS_PATH_HTML);
            //拼接最终保存的文件名
            String systemFileName = dynamicInfoHtmlPath + dynamicInfo.getTimestamp() + Common.SUFFIX_INDEX_HTML;
            File file = new File(systemFileName);
            //如果文件不存在则创建文件
            if (!file.exists()) file.createNewFile();
            //输出内容到文件
            out = new FileOutputStream(file, false); //不append，直接覆写
            out.write(dynamicInfo.getHtml().getBytes("utf-8"));
            out.flush();

        } catch (Exception e) {
            String message = "saveHtmlFile system error";
            FriendCircleOpt.logger.error(message, e);
            throw new Exception(e);

        } finally {
            out.close();
        }
    }


    /**
     * 把resource资源库中首张图片作为封面图
     *
     * @param dynamicimg
     * @throws Exception
     */
    public static void resourceImgToCoverImg(Dynamicimg dynamicimg) throws Exception {
        try {
            //分别获取resource和coverimg路径
            String dynamicSysPathResource = GlobalConfig.getProperties(Common.DYNAMICINFOS_SYS_PATH_RESOURCE);
            String dynamicSysPathCoverImg = GlobalConfig.getProperties(Common.DYNAMICINFOS_SYS_PATH_COVERIMG);
            //分别设置resource和coverimg文件名
            String sourceResourceImg = dynamicSysPathResource + dynamicimg.getImgname_1();
            String destCoverImg = dynamicSysPathCoverImg + dynamicimg.getDynamic_timestamp() + Common.SUFFIX_PNG;
            //把resource中文件拷贝到coverimg中
            CommonService.copyFiles(sourceResourceImg, destCoverImg);

        } catch (Exception e) {
            String message = "resourceImgToCoverImg system error";
            FriendCircleOpt.logger.error(message, e);
            throw new Exception(e);
        }
    }


    /**
     * 获取所有部门积分排名数据
     *
     * @param msg
     * @return
     */
    public static ResponseData getDeptScore(Object msg) {
        ResponseData responseData = new ResponseData(StatusCode.ERROR.getValue());
        SqlSession sqlSession = MybatisUtils.getSession();
        String message = "";
        try {
            List<Map> deptScoreList = sqlSession.selectList(Mapper.GET_DEPT_SCORE);
            Assemble.responseSuccessSetting(responseData, deptScoreList);

        } catch (Exception e) {
            message = "sys error";
            FriendCircleOpt.logger.debug(message, e);
            Assemble.responseErrorSetting(responseData, 500, message);

        } finally {
            CommonService.databaseCommitClose(sqlSession, responseData, false);
        }
        return responseData;
    }


    /**
     * 获取部门所有用户积分排名数据
     *
     * @param msg
     * @return
     */
    public static ResponseData getDeptUserScore(Object msg) {
        ResponseData responseData = new ResponseData(StatusCode.ERROR.getValue());
        SqlSession sqlSession = MybatisUtils.getSession();
        String message = "";
        try {
            Integer deptFrom = Integer.parseInt(String.valueOf(FormData.getParam(msg, Common.DEPT_FROM)));
            List<Map> deptUserScoreList = sqlSession.selectList(Mapper.GET_DEPT_USER_SCORE, deptFrom);
            Assemble.responseSuccessSetting(responseData, deptUserScoreList);

        } catch (Exception e) {
            message = "sys error";
            FriendCircleOpt.logger.debug(message, e);
            Assemble.responseErrorSetting(responseData, 500, message);

        } finally {
            CommonService.databaseCommitClose(sqlSession, responseData, false);
        }
        return responseData;
    }


    /**
     * 获取所有部门积分数据明细
     *
     * @param msg
     * @return
     */
    public static ResponseData getDeptScoreDetail(Object msg) {
        ResponseData responseData = new ResponseData(StatusCode.ERROR.getValue());
        SqlSession sqlSession = MybatisUtils.getSession();
        String message = "";
        try {
            List<Map> deptScoreDetailList = sqlSession.selectList(Mapper.GET_DEPT_SCORE_DETAIL);
            Assemble.responseSuccessSetting(responseData, deptScoreDetailList);

        } catch (Exception e) {
            message = "sys error";
            FriendCircleOpt.logger.debug(message, e);
            Assemble.responseErrorSetting(responseData, 500, message);

        } finally {
            CommonService.databaseCommitClose(sqlSession, responseData, false);
        }
        return responseData;
    }




    /**
     * 获取用户积分数据
     *
     * @param msg
     * @return
     */
    public static ResponseData getUserScore(Object msg) {
        ResponseData responseData = new ResponseData(StatusCode.ERROR.getValue());
        SqlSession sqlSession = MybatisUtils.getSession();
        String message = "";
        try {
            //Map<String, Object> map = FormData.getParam(msg);
            //List<DynamicInfo> userScoreList = sqlSession.selectList(Mapper.GET_USER_SCORE, map);
            DynamicInfo dynamicInfo_id = (DynamicInfo) FormData.getParam(msg, DynamicInfo.class);
            List<DynamicInfo> userScoreList = sqlSession.selectList(Mapper.GET_USER_SCORE, dynamicInfo_id);
            Assemble.responseSuccessSetting(responseData, userScoreList);

        } catch (Exception e) {
            message = "sys error";
            FriendCircleOpt.logger.debug(message, e);
            Assemble.responseErrorSetting(responseData, 500, message);

        } finally {
            CommonService.databaseCommitClose(sqlSession, responseData, false);
        }
        return responseData;
    }

    /**
     * 获取朋友圈的状态信息
     *
     * @param msg
     * @return
     */
    public static ResponseData selectFriendCircleStatus(Object msg) {
        ResponseData responseData = new ResponseData(StatusCode.ERROR.getValue());
        SqlSession sqlSession = MybatisUtils.getSession();
        String message = "";
        try {

            Map<String, Object> map = FormData.getParam(msg);
            DynamicInfo dynamicInfo = sqlSession.selectOne(Mapper.GET_DYNAMIC_STATUS, map);
            //检查是否查找到指定起始位置及数目的新闻并返回相应结果
            if (CommonService.checkNotNull(dynamicInfo)) {
                Map<String, Object> data = new HashMap<>();
                data.put(Common.DYNAMICINFO, dynamicInfo); //返回动态信息
                Assemble.responseSuccessSetting(responseData, data);
            } else {
                message = "news info not found";
                FriendCircleOpt.logger.warn(message);
                Assemble.responseErrorSetting(responseData, 401, message);
            }

        } catch (Exception e) {
            message = "sys error";
            FriendCircleOpt.logger.debug(message, e);
            Assemble.responseErrorSetting(responseData, 500, message);

        } finally {
            CommonService.databaseCommitClose(sqlSession, responseData, false);
        }
        return responseData;
    }

    /**
     * 获取朋友圈的状态信息
     *
     * @param msg
     * @return
     */
    public static ResponseData selectFriendCircleUpdateTime(Object msg) {
        ResponseData responseData = new ResponseData(StatusCode.ERROR.getValue());
        SqlSession sqlSession = MybatisUtils.getSession();
        String message = "";
        try {

            Map<String, Object> map = FormData.getParam(msg);
            DynamicInfo dynamicInfo = sqlSession.selectOne(Mapper.GET_DYNAMIC_UPDATETIME, map);
            //检查是否查找到指定起始位置及数目的新闻并返回相应结果
            if (CommonService.checkNotNull(dynamicInfo)) {
                Map<String, Object> data = new HashMap<>();
                data.put(Common.DYNAMICINFO, dynamicInfo); //返回动态信息
                Assemble.responseSuccessSetting(responseData, data);
            } else {
                message = "news info not found";
                FriendCircleOpt.logger.warn(message);
                Assemble.responseErrorSetting(responseData, 401, message);
            }

        } catch (Exception e) {
            message = "sys error";
            FriendCircleOpt.logger.debug(message, e);
            Assemble.responseErrorSetting(responseData, 500, message);

        } finally {
            CommonService.databaseCommitClose(sqlSession, responseData, false);
        }
        return responseData;
    }


    /**
     * 获取朋友圈所有资源文件数据
     *
     * @param msg
     * @return
     */
    public static ResponseData getFriendCircleResourceImg(Object msg) {
        return CommonService.simpleImplOpt(false, (responseData, sqlSession) -> {
            Map<String, Object> map = FormData.getParam(msg);
            String timestamp = (String) map.get(Common.TIMESTAMP);
            Dynamicimg dynamicimg = sqlSession.selectOne(Mapper.GET_FRIEND_CIRCLE_RESOURCE, timestamp);
            Assemble.responseSuccessSetting(responseData, dynamicimg);
        });
    }


    /**
     * 获取指定范围内的发布的朋友圈数据
     *
     * @param msg
     * @return
     */
    public static ResponseData getPublishedFriendToPhone(Object msg) {
        return CommonService.simpleImplOpt(false, (responseData, sqlSession) -> {
            Map<String, Object> map = FormData.getParam(msg);
            List<DynamicInfo> list = sqlSession.selectList(Mapper.GET_PUBLISHED_FRIEND_TO_PHONE, map);
            Assemble.responseSuccessSetting(responseData, list);
        });
    }


    /**
     * 手机端前端搜索指定匹配字段的新闻数据
     *
     * @param msg
     * @return
     */
    public static ResponseData searchNews(Object msg) {
        return CommonService.simpleImplOpt(false, (responseData, sqlSession) -> {
            DynamicInfo dynamicInfo = (DynamicInfo) FormData.getParam(msg, DynamicInfo.class);
            List<DynamicInfo> list = sqlSession.selectList(Mapper.SEARCH_NEWS, dynamicInfo);
            Assemble.responseSuccessSetting(responseData, list);
        });
    }

    /**
     * 手机端前端搜索指定用户的指定匹配字段的新闻数据
     *
     * @param msg
     * @return
     */
    public static ResponseData searchUserNews(Object msg) {
        return CommonService.simpleImplOpt(false, (responseData, sqlSession) -> {
            DynamicInfo dynamicInfo = (DynamicInfo) FormData.getParam(msg, DynamicInfo.class);
            List<DynamicInfo> list = sqlSession.selectList(Mapper.SEARCH_USER_NEWS, dynamicInfo);
            Assemble.responseSuccessSetting(responseData, list);
        });
    }


    /**
     * 后台前端搜索指定匹配字段的新闻数据
     *
     * @param msg
     * @return
     */
    public static ResponseData searchFriendCircle(Object msg) {
        return CommonService.simpleImplOpt(false, (responseData, sqlSession) -> {
            DynamicInfo dynamicInfo = (DynamicInfo) FormData.getParam(msg, DynamicInfo.class);
            List<DynamicInfo> list = sqlSession.selectList(Mapper.SEARCH_FRIEND_CIRCLE_NEWS, dynamicInfo);
            Assemble.responseSuccessSetting(responseData, list);
        });
    }

    /**
     * 后台前端搜索指定匹配字段的新闻数据
     *
     * @param msg
     * @return
     */
    public static ResponseData searchFriendCirclePc(Object msg) {
        return CommonService.simpleImplOpt(false, (responseData, sqlSession) -> {
            DynamicInfo dynamicInfo = (DynamicInfo) FormData.getParam(msg, DynamicInfo.class);
            List<DynamicInfo> list = sqlSession.selectList(Mapper.SEARCH_FRIEND_CIRCLE_NEWS_PC, dynamicInfo);
            Assemble.responseSuccessSetting(responseData, list);
        });
    }

//    /**
//     * 保存动态消息上传图片信息
//     *
//     * @param dynamicInfo 动态数据
//     * @throws Exception 保存出错抛出异常信息
//     */
//    public static void saveCoverImgFile(DynamicInfo dynamicInfo) throws Exception {
//        FileOutputStream out = null;
//        try {
//            //获取保存到系统的路径
//            String dynamicInfoCoverImg = GlobalConfig.getProperties(Common.DYNAMICINFOS_SYS_PATH_COVERIMG);
//            //拼接最终保存的文件名
//            String coverImageName = dynamicInfoCoverImg + dynamicInfo.getTimestamp() + Common.SUFFIX_PNG;
//            File file = new File(coverImageName);
//            //如果文件不存在则创建文件
//            if (!file.exists()) file.createNewFile();
//            //输出内容到文件
//            out = new FileOutputStream(file, false); //不append，直接覆写
//            out.write(dynamicInfo.getCoverImage().get());
//            out.flush();
//
//        } catch (Exception e) {
//            String message = "saveCoverImgFile system error";
//            FriendCircleOpt.logger.error(message, e);
//            throw new Exception(e);
//
//        } finally {
//            out.close();
//        }
//    }

//    /**
//     * 保存朋友圈上存图片方法
//     *
//     * @param friendimage
//     * @param Timestamp
//     * @param image_sq
//     * @throws Exception
//     */
//    public static void saveFriendImgFile(FileUpload friendimage, String Timestamp, int image_sq) throws Exception {
//        FileOutputStream out = null;
//        try {
//            //获取保存到系统的路径
//            String dynamicInfoFriendImg = GlobalConfig.getProperties(Common.DYNAMICINFOS_SYS_PATH_FRIENDIMG);
//            //拼接最终保存的文件名
//            String firendImageName = dynamicInfoFriendImg + Timestamp + "_" + image_sq + Common.SUFFIX_PNG;
//            File file = new File(firendImageName);
//            //如果文件不存在则创建文件
//            if (!file.exists()) file.createNewFile();
//            //输出内容到文件
//            out = new FileOutputStream(file, false); //不append，直接覆写
//            out.write(friendimage.get());
//            out.flush();
//
//        } catch (Exception e) {
//            String message = "saveFriendImgFile system error";
//            FriendCircleOpt.logger.error(message, e);
//            throw new Exception(e);
//
//        } finally {
//            out.close();
//        }
//    }
//
//

    /**
     * 拷贝朋友圈功能
     *
     * @param msg
     */
    public static ResponseData copyFriend(Object msg) {
        ResponseData responseData = new ResponseData(StatusCode.ERROR.getValue());
        SqlSession sqlSession = MybatisUtils.getSession();
        String message = "";
        try {
            //获取传递过来的数据
//            Map<String,Object>map = FormData.getParam(msg);
//            String timestamp = (String) map.get(Common.TIMESTAMP);
//            String deptF = (String) map.get(Common.DEPT_FROM);

            //解析数据
//            List<Integer> Deptlist = JSON.parseArray(deptF,Integer.class);
            String timestamp = (String) FormData.getParam(msg, Common.TIMESTAMP);


            //获取数据库中dynamicinfo该条目数据
            DynamicInfo dynamicInfo = sqlSession.selectOne(Mapper.GET_SINGLE_FRIEND_DETAIL_INFO, timestamp);
            //获取数据库中dynamicimg该条目数据
            Dynamicimg dynamicImg = sqlSession.selectOne(Mapper.GET_FRIEND_CIRCLE_RESOURCE, timestamp);

            //设置新的timestamp等时间数据
            String newTimestamp = CommonService.getTimeStamp() + "_" + dynamicInfo.getWx_user_id();
            dynamicInfo.setTimestamp(newTimestamp);
            dynamicInfo.setCreate_time(CommonService.getDateTime());
            dynamicInfo.setStatus_cd(Common.FRIEND_DRAFT_STATUS);
            dynamicImg.setDynamic_timestamp(newTimestamp);
            dynamicImg.setCreate_time(CommonService.getDateTime());

            //插入新数据到数据库
            int num = sqlSession.insert(Mapper.INSERT_NEW_NEWS, dynamicInfo);
            int numImg = sqlSession.insert(Mapper.INSERT_NEW_FRIENDIMG, dynamicImg);
            //循环该用户所在所有部门，更新消息数据到dynamic_deptscore表中
//            DynamicDeptScore dynamicDeptScore = new DynamicDeptScore(dynamicInfo);
//            for (Integer deptFrom : Deptlist) {
//                dynamicDeptScore.setDept_from(deptFrom);
//                int num_dept = sqlSession.insert(Mapper.INSERT_NEW_DYNAMIC_DEPT_INFO, dynamicDeptScore);
//                if (num_dept <= 0){
//                    throw new Exception("部门"+deptFrom+":插入错误");
//                }
//            }

            if (num > 0 && numImg > 0) {
                //拷贝新闻封面和内容数据
                String dynamicInfoCoverImg = GlobalConfig.getProperties(Common.DYNAMICINFOS_SYS_PATH_COVERIMG);
                String dynamicInfoHtmlPath = GlobalConfig.getProperties(Common.DYNAMICINFOS_SYS_PATH_HTML);

                //拷贝新闻封面数据
                String sourceCoverImg = dynamicInfoCoverImg + timestamp + Common.SUFFIX_PNG;
                String destCoverImg = dynamicInfoCoverImg + newTimestamp + Common.SUFFIX_PNG;
                CommonService.copyFiles(sourceCoverImg, destCoverImg);

                //拷贝新闻内容数据
                String sourceNewsFile = dynamicInfoHtmlPath + timestamp + Common.SUFFIX_INDEX_HTML;
                String destNewsFile = dynamicInfoHtmlPath + newTimestamp + Common.SUFFIX_INDEX_HTML;
                CommonService.copyFiles(sourceNewsFile, destNewsFile);

                //返回正确数据
                Assemble.responseSuccessSetting(responseData, null);
            }

        } catch (Exception e) {
            message = "copyFriend error";
            FriendCircleOpt.logger.error(message, e);
            Assemble.responseErrorSetting(responseData, 500, message);

        } finally {
            CommonService.databaseCommitClose(sqlSession, responseData, true);
        }
        return responseData;
    }


    /**
     * 审核操作时获取该条朋友圈消息所有图片数据
     * @param msg
     * @return
     */
    public static ResponseData getDynamicImgAndInfo(Object msg){
        return CommonService.simpleImplOpt(false, new CommonImpl() {
            @Override
            public void run(ResponseData responseData, SqlSession sqlSession) throws Exception {
                //获取前端传进来的timestamp参数
                String timestamp = (String) FormData.getParam(msg, Common.TIMESTAMP);

                //获取数据库中dynamicimg该条目数据
                Dynamicimg dynamicImg = sqlSession.selectOne(Mapper.GET_FRIEND_CIRCLE_RESOURCE, timestamp);
                DynamicInfo dynamicInfo = sqlSession.selectOne(Mapper.GET_DYNAMIC_INFO, timestamp);

                //返回数据
                Map<String, Object> map = new HashMap<>(2);
                map.put(Common.DYNAMICINFO, dynamicInfo);
                map.put(Common.DYNAMICIMG, dynamicImg);
                Assemble.responseSuccessSetting(responseData, map);
            }
        });
    };



    /**
     * 同时删除多条朋友圈
     *
     * @param msg
     */
    public static ResponseData deleteFriend(Object msg) {
        return CommonService.simpleImplOpt(true, new CommonImpl() {
            @Override
            public void run(ResponseData responseData, SqlSession sqlSession) throws Exception {

                //获取传递过来的数据
//                Map<String,Object>map = FormData.getParam(msg);
//                String timestamp = (String) map.get(Common.TIMESTAMP);
//                String deptF = (String) map.get(Common.DEPT_FROM);

                //解析数据
//                List<Integer> Deptlist = JSON.parseArray(deptF,Integer.class);

                Map<String, Object> map = FormData.getParam(msg);
                String timestamp = (String) map.get(Common.TIMESTAMP);

                //删除数据库及系统文件操作
//                deleteFriendOpt(sqlSession, timestamp, Deptlist);
                deleteFriendOpt(sqlSession, timestamp);
                //返回正确数据
                Assemble.responseSuccessSetting(responseData, null);
            }
        });
    }

    /**
     * 删除朋友圈数据操作
     */
    public static void deleteFriendOpt(SqlSession sqlSession, String timestamp) throws Exception{
        //删除数据库数据
        int deleteInfoNum = sqlSession.delete(Mapper.DELETE_FRIEND, timestamp);
        int deleteImgNum = sqlSession.delete(Mapper.DELETE_RESOURCE_IMG, timestamp);

//        for (Integer deptFrom : Deptlist) {
//            DynamicDeptScore dynamicDeptScore = new DynamicDeptScore();
//            dynamicDeptScore.setDept_from(deptFrom);
//            dynamicDeptScore.setDynamic_timestamp(timestamp);
//            int deleteDeptScoreNum = sqlSession.delete(Mapper.DELETE_DYNAMIC_DEPTSCORE, dynamicDeptScore);
//            if (deleteDeptScoreNum <= 0){
//                throw new Exception("部门"+deptFrom+":插入错误");
//            }
//        }

        if (deleteInfoNum > 0 && deleteImgNum > 0) {
            //删除朋友圈封面、图片、内容数据
            String dynamicInfoCoverImg = GlobalConfig.getProperties(Common.DYNAMICINFOS_SYS_PATH_COVERIMG);
            String dynamicInfoHtmlPath = GlobalConfig.getProperties(Common.DYNAMICINFOS_SYS_PATH_HTML);

            //删除朋友圈封面文件数据
            String coverImg = dynamicInfoCoverImg + timestamp + Common.SUFFIX_PNG;
            deleteFile(coverImg);

            //删除朋友圈内容文件数据
            String htmlFile = dynamicInfoHtmlPath + timestamp + Common.SUFFIX_INDEX_HTML;
            deleteFile(htmlFile);

        } else {
            String message = "delete file from database error, timestamp: " + timestamp;
            FriendCircleOpt.logger.warn(message);
        }
    }


    /**
     * 批量删除朋友圈数据
     *
     * @param msg
     */
    public static ResponseData deleteBatchFriend(Object msg) {
        return CommonService.simpleImplOpt(true, new CommonImpl() {
            @Override
            public void run(ResponseData responseData, SqlSession sqlSession) throws Exception {
                //获取传递过来的数据
//                String listStr = (String) FormData.getParam(msg, Common.DELETE_LIST);
                //解析Json数组数据
//                List<HashMap> list = JSON.parseArray(listStr, HashMap.class);

                //获取传递过来的数据
                String listStr = (String) FormData.getParam(msg, Common.DELETE_LIST);
                //解析Json数组数据
                List<String> list = JSON.parseArray(listStr, String.class);

                //循环传递过来的list中每个数据，并删除每条朋友圈数据
//                for (HashMap map : list) {
//                    //解析数据
//                    String timestamp = (String) map.get("timestamp");
//                    String deptArrayStr = (String) map.get("dept_from");
//                    List<Integer> Deptlist = JSON.parseArray(deptArrayStr, Integer.class);
//
//                    deleteFriendOpt(sqlSession, timestamp, Deptlist);
//                }
                //循环传递过来的list中每个数据，并删除每条朋友圈数据
                for (String timestamp : list) {
                    deleteFriendOpt(sqlSession, timestamp);
                }
                //返回正确数据
                Assemble.responseSuccessSetting(responseData, null);
            }
        });
    }


    /**
     * 删除单文件操作
     *
     * @param path
     */
    public static void deleteFile(String path) {
        File file = new File(path);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }

}
