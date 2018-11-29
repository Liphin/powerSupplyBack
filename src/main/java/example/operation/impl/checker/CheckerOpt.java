package example.operation.impl.checker;

import com.alibaba.fastjson.JSON;
import example.operation.entity.CheckResult;
import example.operation.entity.Checker;
import example.operation.entity.DynamicInfo;
import example.operation.entity.Dynamicimg;
import example.operation.entity.response.ResponseData;
import example.operation.entity.response.StatusCode;
import example.operation.impl.common.CommonImpl;
import example.operation.impl.common.CommonService;
import example.operation.impl.friendcircle.FriendCircleOpt;
import example.tool.common.Assemble;
import example.tool.common.Common;
import example.tool.common.Mapper;
import example.tool.config.GlobalConfig;
import example.tool.parser.form.FormData;
import example.tool.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Administrator on 2018/9/29.
 */
public class CheckerOpt {

    private static Logger logger = LoggerFactory.getLogger(CheckerOpt.class);

    /**
     * 管理员在手机客户端审核时查看所有待审的朋友圈新闻数据
     *
     * @param msg
     * @throws Exception
     */
    public static ResponseData getPendVerifyFriendCircle(Object msg) {
        ResponseData responseData = new ResponseData(StatusCode.ERROR.getValue());
        SqlSession sqlSession = MybatisUtils.getSession();
        String message = "";
        try {
            Map<String, Object> map = FormData.getParam(msg);
            List<DynamicInfo> friendList = sqlSession.selectList(Mapper.GET_PEND_VERIFY_FRIEND_CIRCLE, map);
            Assemble.responseSuccessSetting(responseData, friendList);

        } catch (Exception e) {
            message = "sys error";
            CheckerOpt.logger.debug(message, e);
            Assemble.responseErrorSetting(responseData, 500, message);

        } finally {
            CommonService.databaseCommitClose(sqlSession, responseData, false);
        }
        return responseData;
    }


    /**
     * 管理员在手机客户端审核时搜索相关标题的数据
     *
     * @param msg
     * @throws Exception
     */
    public static ResponseData searchPendVerifyFriendCircle(Object msg) {
        ResponseData responseData = new ResponseData(StatusCode.ERROR.getValue());
        SqlSession sqlSession = MybatisUtils.getSession();
        String message = "";
        try {
            Map<String, Object> map = FormData.getParam(msg);
            List<DynamicInfo> friendList = sqlSession.selectList(Mapper.SEARCH_PEND_VERIFY_FRIEND_CIRCLE, map);
            Assemble.responseSuccessSetting(responseData, friendList);

        } catch (Exception e) {
            message = "sys error";
            CheckerOpt.logger.debug(message, e);
            Assemble.responseErrorSetting(responseData, 500, message);

        } finally {
            CommonService.databaseCommitClose(sqlSession, responseData, false);
        }
        return responseData;
    }


    /**
     * 验证是否为审核人员
     *
     * @param msg
     * @throws Exception
     */
    public static ResponseData verifyCheckerInfo(Object msg) {
        ResponseData responseData = new ResponseData(StatusCode.ERROR.getValue());
        SqlSession sqlSession = MybatisUtils.getSession();
        String message = "";
        try {
            //Map<String, Object> map = FormData.getParam(msg);
            //Checker checker = sqlSession.selectOne(Mapper.GET_CHECKER, map);
            Checker checker_id = (Checker) FormData.getParam(msg, Checker.class);
            Checker checker = sqlSession.selectOne(Mapper.GET_CHECKER, checker_id);

            //验证是否为指定审核人员
            if (CommonService.checkNotNull(checker)) {
                Assemble.responseSuccessSetting(responseData, checker);

            } else {
                message = "checker info not found";
                CheckerOpt.logger.warn(message);
                Assemble.responseErrorSetting(responseData, 401, message);
            }

        } catch (Exception e) {
            message = "sys error";
            CheckerOpt.logger.debug(message, e);
            Assemble.responseErrorSetting(responseData, 500, message);

        } finally {
            CommonService.databaseCommitClose(sqlSession, responseData, false);
        }
        return responseData;
    }

    /**
     * 获取初审通过的信息
     *
     * @param msg
     * @throws Exception
     */
    public static ResponseData selectFirstCheckInfo(Object msg) {
        ResponseData responseData = new ResponseData(StatusCode.ERROR.getValue());
        SqlSession sqlSession = MybatisUtils.getSession();
        String message = "";
        try {
            Map<String, Object> map = FormData.getParam(msg);
            CheckResult checkResult = sqlSession.selectOne(Mapper.GET_FIRST_CHECKER_INFO, map);

            //验证是否为指定审核人员
            if (CommonService.checkNotNull(checkResult)) {
                Assemble.responseSuccessSetting(responseData, checkResult);

            } else {
                message = "checker info not found";
                CheckerOpt.logger.warn(message);
                Assemble.responseErrorSetting(responseData, 401, message);
            }

        } catch (Exception e) {
            message = "sys error";
            CheckerOpt.logger.debug(message, e);
            Assemble.responseErrorSetting(responseData, 500, message);

        } finally {
            CommonService.databaseCommitClose(sqlSession, responseData, false);
        }
        return responseData;
    }

    /**
     * 获取初审checker的信息
     *
     * @param msg
     * @throws Exception
     */
    public static ResponseData searchCheckerInfoFirstList(Object msg) {
        ResponseData responseData = new ResponseData(StatusCode.ERROR.getValue());
        SqlSession sqlSession = MybatisUtils.getSession();
        String message = "";
        try {
            Map<String, Object> map = FormData.getParam(msg);
            List<Checker> list = sqlSession.selectList(Mapper.GET_FIRST_CHECKER, map);
            Assemble.responseSuccessSetting(responseData, list);

        } catch (Exception e) {
            message = "sys error";
            CheckerOpt.logger.debug(message, e);
            Assemble.responseErrorSetting(responseData, 500, message);

        } finally {
            CommonService.databaseCommitClose(sqlSession, responseData, false);
        }
        return responseData;
    }

    /**
     * 获取终审checker的信息
     *
     * @param msg
     * @throws Exception
     */
    public static ResponseData searchCheckerInfoFinalList(Object msg) {
        ResponseData responseData = new ResponseData(StatusCode.ERROR.getValue());
        SqlSession sqlSession = MybatisUtils.getSession();
        String message = "";
        try {
            Map<String, Object> map = FormData.getParam(msg);
            List<Checker> list = sqlSession.selectList(Mapper.GET_FINAL_CHECKER, map);
            Assemble.responseSuccessSetting(responseData, list);

        } catch (Exception e) {
            message = "sys error";
            CheckerOpt.logger.debug(message, e);
            Assemble.responseErrorSetting(responseData, 500, message);

        } finally {
            CommonService.databaseCommitClose(sqlSession, responseData, false);
        }
        return responseData;
    }

    /**
     * 获取历史评审数据
     *
     * @param msg
     * @throws Exception
     */
    public static ResponseData getHistoryCheckResult(Object msg) {
        ResponseData responseData = new ResponseData(StatusCode.ERROR.getValue());
        SqlSession sqlSession = MybatisUtils.getSession();
        String message = "";
        try {
            Map<String, Object> map = FormData.getParam(msg);
            List<CheckResult> list = sqlSession.selectList(Mapper.GET_HISTORY_CHECK_RESULT, map);
            Assemble.responseSuccessSetting(responseData, list);

        } catch (Exception e) {
            message = "sys error";
            CheckerOpt.logger.debug(message, e);
            Assemble.responseErrorSetting(responseData, 500, message);

        } finally {
            CommonService.databaseCommitClose(sqlSession, responseData, false);
        }
        return responseData;
    }

    /**
     * 提交审核信息数据
     *
     * @param msg
     * @throws Exception
     */
    public static ResponseData updateCheckResult(Object msg) {
        ResponseData responseData = new ResponseData(StatusCode.ERROR.getValue());
        SqlSession sqlSession = MybatisUtils.getSession();
        String message = "";
        try {
            //数据获取与初始化
            CheckResult checkResult = (CheckResult) FormData.getParam(msg, CheckResult.class);
            checkResult.setTimestamp(CommonService.getTimeStamp());
            checkResult.setCreate_time(CommonService.getDateTime());
            //插入check result数据
            int num = sqlSession.update(Mapper.INSERT_NEW_CHECK_RESULT, checkResult);
            //int checkLevel=checkResult.getCheck_level();

            //更新dynamicinfo表状态
            Map<String, Object> map = new HashMap();
            //String UpdateTime=CommonService.getDateTime();
            map.put(Common.TIMESTAMP, checkResult.getDynamic_timestamp());
            map.put(Common.SCORE, checkResult.getScore());
            map.put(Common.UPDATE_TIME,CommonService.getDateTime() );
            sqlSession.update(Mapper.UPDATE_DYNAMIC_INFO_CHECK, map);
            //设置返回结果
            if (num > 0) {
//                Map<String, Object> data = new HashMap<String, Object>(2);
//                data.put(Common.UPDATE_TIME, UpdateTime);
                Assemble.responseSuccessSetting(responseData, null);

            } else {
                message = "checkresult insert db error";
                CheckerOpt.logger.warn(message);
                Assemble.responseErrorSetting(responseData, 401, message);
            }

        } catch (Exception e) {
            message = "sys error";
            CheckerOpt.logger.debug(message, e);
            Assemble.responseErrorSetting(responseData, 500, message);

        } finally {
            CommonService.databaseCommitClose(sqlSession, responseData, true);
        }
        return responseData;
    }

    /**
     * 提交审核信息数据
     *
     * @param msg
     * @throws Exception
     */
    public static ResponseData submitCheckResult(Object msg) {
        ResponseData responseData = new ResponseData(StatusCode.ERROR.getValue());
        SqlSession sqlSession = MybatisUtils.getSession();
        String message = "";
        try {
            //数据获取与初始化
            CheckResult checkResult = (CheckResult) FormData.getParam(msg, CheckResult.class);
            checkResult.setTimestamp(CommonService.getTimeStamp());
            checkResult.setCreate_time(CommonService.getDateTime());
            //插入check result数据
            int num = sqlSession.update(Mapper.INSERT_NEW_CHECK_RESULT, checkResult);
            int checkLevel=checkResult.getCheck_level();

            //更新dynamicinfo表状态
            Map<String, Object> map = new HashMap();
            map.put(Common.TIMESTAMP, checkResult.getDynamic_timestamp());
            if (checkResult.getCheck_status() == 1) {
                //审核通过，设置一级、二级审核状态
                map.put(Common.STATUS_CD, checkResult.getCheck_level() + 1);
                map.put(Common.SCORE, checkResult.getScore());

            } else {
                //审核不通过，设置status_cd为不通过的初审4  不通过终审5
                if(checkLevel==1) {
                    map.put(Common.STATUS_CD, Common.FRIENDCHECK_NOT_PASS_STATUS);
                }
                else {
                    map.put(Common.STATUS_CD, Common.FRIENDCHECK_FINAL_NOT_PASS_STATUS);
                }

                map.put(Common.SCORE, 0);
            }
            map.put(Common.UPDATE_TIME, CommonService.getDateTime());
            sqlSession.update(Mapper.UPDATE_DYNAMIC_INFO_STATUS, map);

            //设置返回结果
            if (num > 0) {
                Assemble.responseSuccessSetting(responseData, null);

            } else {
                message = "checkresult insert db error";
                CheckerOpt.logger.warn(message);
                Assemble.responseErrorSetting(responseData, 401, message);
            }

        } catch (Exception e) {
            message = "sys error";
            CheckerOpt.logger.debug(message, e);
            Assemble.responseErrorSetting(responseData, 500, message);

        } finally {
            CommonService.databaseCommitClose(sqlSession, responseData, true);
        }
        return responseData;
    }


    /**
     * 重新更新HTML文件动态信息
     *
     * @param msg
     * @return
     */
    public static ResponseData updateNewHtmlFile(Object msg) {
        return CommonService.simpleImplOpt(true, (responseData, sqlSession) -> {
            Map<String, Object> map = FormData.getParam(msg);
            String html = (String) map.get(Common.HTML);
            String timestamp = (String) map.get(Common.TIMESTAMP);
            String coverImgName = (String) map.get(Common.COVER_IMG);
            String coverimgs = (String) map.get(Common.COVERIMGS);
            map.put(Common.UPDATE_TIME, CommonService.getDateTime());

            //更新title数据
            int num = sqlSession.update(Mapper.UPDATE_DYNAMIC_INFO_TITLE, map);
            if (num > 0) {
                Dynamicimg dynamicimg = new Dynamicimg();
                dynamicimg.setDynamic_timestamp(timestamp);
                dynamicimg.setImgname_1(coverImgName);
                FriendCircleOpt.resourceImgToCoverImg(dynamicimg);
            }

            //更新dynamicimg表
            Dynamicimg dynamicimg = JSON.parseObject(coverimgs, Dynamicimg.class);
            int num_img = sqlSession.update(Mapper.UPDATE_FRIENDIMG, dynamicimg);

            //重新保存该HTML文件
            FileOutputStream out = null;
            try {
                //获取保存到系统的路径
                String dynamicInfoHtmlPath = GlobalConfig.getProperties(Common.DYNAMICINFOS_SYS_PATH_HTML);
                //拼接最终保存的文件名
                String systemFileName = dynamicInfoHtmlPath + timestamp + Common.SUFFIX_INDEX_HTML;
                //文件保存操作
                File file = new File(systemFileName);
                //如果文件不存在则创建文件
                if (!file.exists()) file.createNewFile();
                //输出内容到文件
                out = new FileOutputStream(file, false); //不append，直接覆写
                out.write(html.getBytes("utf-8"));
                out.flush();

            } catch (Exception e) {
                String message = "saveHtmlFile system error";
                CheckerOpt.logger.error(message, e);
                throw new Exception(e);

            } finally {
                out.close();
            }

            //正确返回数据
            Assemble.responseSuccessSetting(responseData, null);
        });
    }


    /**
     * 获取当前所有审核员checker数据
     *
     * @param msg
     * @throws Exception
     */
    public static ResponseData getAllCheckerInfo(Object msg) {
        ResponseData responseData = new ResponseData(StatusCode.ERROR.getValue());
        SqlSession sqlSession = MybatisUtils.getSession();
        String message = "";
        try {
            //获取所有checker数据
            List<Checker> list = sqlSession.selectList(Mapper.GET_ALL_CHECKER_INFO);
            Assemble.responseSuccessSetting(responseData, list);

        } catch (Exception e) {
            message = "sys error";
            CheckerOpt.logger.debug(message, e);
            Assemble.responseErrorSetting(responseData, 500, message);

        } finally {
            CommonService.databaseCommitClose(sqlSession, responseData, false);
        }
        return responseData;
    }


    /**
     * 更新审核员的相关数据
     *
     * @param msg
     * @throws Exception
     */
    public static ResponseData updateCheckerInfo(Object msg) {
        ResponseData responseData = new ResponseData(StatusCode.ERROR.getValue());
        SqlSession sqlSession = MybatisUtils.getSession();
        String message = "";
        try {
            //获取所有checker数据
            Map<String, Object> map = FormData.getParam(msg);
            List<Checker> list = JSON.parseArray(String.valueOf(map.get(Common.MEMBERS)), Checker.class);

            //逐条查阅list中每条数据
            for (Checker checker : list) {
                //解绑审核员权限，操作类型为1，如果为一级或二级审核员则删除后插入新的
                sqlSession.delete(Mapper.REVOKE_CHECKER_RIGHT, checker.getWx_user_id());
                //设置一级或二级审核员操作
                if (checker.getCheck_level() > 0) {
                    //循环该人员所在的每个部门插入数据库
//                    for (Integer dept_from : checker.getDepartment()) {
//                        checker.setDept_from(dept_from);
//                        int num = sqlSession.insert(Mapper.GRANT_CHECKER_RIGHT, checker);
//                        if (num <= 0) throw new Exception("config role insert item db error");
//                    }
                    int num = sqlSession.insert(Mapper.GRANT_CHECKER_RIGHT, checker);
                    if (num <= 0) throw new Exception("config role insert item db error");
                }
            }
            //正常运行无exception抛出则重新获取更新后的数据表数据
            List<Checker> listNew = sqlSession.selectList(Mapper.GET_ALL_CHECKER_INFO);
            Assemble.responseSuccessSetting(responseData, listNew);

        } catch (Exception e) {
            message = "sys error";
            CheckerOpt.logger.debug(message, e);
            Assemble.responseErrorSetting(responseData, 500, message);

        } finally {
            CommonService.databaseCommitClose(sqlSession, responseData, true);
        }
        return responseData;
    }


    /**
     * 二级审核员获取一级审核员审核的消息以及被反驳的消息
     *
     * @param msg
     * @return
     */
    public static ResponseData getFirstCheckAndRejectNews(Object msg) {

        return CommonService.simpleImplOpt(false, new CommonImpl() {
            @Override
            public void run(ResponseData responseData, SqlSession sqlSession) throws Exception {
                //获取创建时间
                String createTime = (String) FormData.getParam(msg, Common.CREATE_TIME);
                //查询数据库返回时间小于该createTime的20条一级审核及反驳的新闻数据

                List<DynamicInfo> list = sqlSession.selectList(Mapper.GET_FIRST_CHECK_AND_REJECT_NEWS, createTime);
                //返回该数据
                Assemble.responseSuccessSetting(responseData, list);
            }
        });
    }

    /**
     * 二级审核员获取终审通过的记录
     *
     * @param msg
     * @return
     */
    public static ResponseData getSuccessNews(Object msg) {

        return CommonService.simpleImplOpt(false, new CommonImpl() {
            @Override
            public void run(ResponseData responseData, SqlSession sqlSession) throws Exception {
                //获取创建时间
                String createTime = (String) FormData.getParam(msg, Common.CREATE_TIME);
                //查询数据库返回时间小于该createTime的20条一级审核及反驳的新闻数据

                List<DynamicInfo> list = sqlSession.selectList(Mapper.GET_SUCCESS_NEWS, createTime);
                //返回该数据
                Assemble.responseSuccessSetting(responseData, list);
            }
        });
    }

}
