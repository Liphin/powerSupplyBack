package example.operation.impl.vote;

import example.operation.entity.DynamicInfoHq;
import example.operation.entity.response.ResponseData;
import example.operation.entity.response.StatusCode;
import example.operation.impl.common.CommonService;
import example.tool.common.Assemble;
import example.tool.common.Common;
import example.tool.common.Mapper;
import example.tool.parser.form.FormData;
import example.tool.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoteOpt {
    private static Logger logger = LoggerFactory.getLogger(VoteOpt.class);

    /**
     * 获取所有后勤服务数据
     *
     * @param msg http传递的数据
     */
    public static ResponseData getRangeVoteListToBgHq(Object msg) {
        ResponseData responseData = new ResponseData(StatusCode.ERROR.getValue());
        SqlSession sqlSession = MybatisUtils.getSession();
        String message = "";
        try {
            Map<String, Object> map = FormData.getParam(msg);
            List<DynamicInfoHq> newsList = sqlSession.selectList(Mapper.GET_RANGE_VOTE_INFO_TO_BG_HQ, map);
            int newsNum = sqlSession.selectOne(Mapper.GET_VOTE_NUM_HQ);
            //检查是否查找到指定起始位置及数目的新闻并返回相应结果
            if (CommonService.checkNotNull(newsList)) {
                //设置回传的返回数据
                Map<String, Object> data = new HashMap<String, Object>(2);
                data.put(Common.TOTAL_NUM, newsNum);
                data.put(Common.NEWS_LIST_DATA, newsList);
                Assemble.responseSuccessSetting(responseData, data);

            } else {
                message = "voteHq info not found";
                VoteOpt.logger.warn(message);
                Assemble.responseErrorSetting(responseData, 401, message);
            }

        } catch (Exception e) {
            message = "sys error";
            VoteOpt.logger.debug(message, e);
            Assemble.responseErrorSetting(responseData, 500, message);

        } finally {
            CommonService.databaseCommitClose(sqlSession, responseData, false);
        }
        return responseData;
    }

    /**
     * 后台搜索指定匹配字段的新闻数据
     *
     * @param msg
     * @return
     */
    public static ResponseData searchVoteListHq(Object msg) {
        return CommonService.simpleImplOpt(false, (responseData, sqlSession) -> {
            DynamicInfoHq dynamicInfoHq = (DynamicInfoHq) FormData.getParam(msg, DynamicInfoHq.class);
            List<DynamicInfoHq> list = sqlSession.selectList(Mapper.SEARCH_VOTE_LIST_HQ, dynamicInfoHq);
            Assemble.responseSuccessSetting(responseData, list);
        });
    }
}
