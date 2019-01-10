package example;

import com.alibaba.fastjson.JSON;
import example.operation.entity.response.ResponseData;
import example.operation.impl.checker.CheckerOpt;
import example.operation.impl.friendcircle.FriendCircleOpt;
import example.operation.impl.login.LoginOpt;
import example.operation.impl.news.NewsOpt;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import example.tool.common.Common;
import example.tool.parser.form.FormData;

import java.util.Map;

import static com.aliyun.oss.internal.OSSHeaders.ACCESS_CONTROL_ALLOW_ORIGIN;
import static io.netty.handler.codec.http.HttpHeaderNames.*;
import static io.netty.handler.codec.http.HttpResponseStatus.CONTINUE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * Created by Administrator on 2017/2/8.
 */

public class BackAccess {

    private static Logger logger = LoggerFactory.getLogger(BackAccess.class);

    /**
     * 暴露第三方回调的url链接无需cross域验证
     *
     * @param request request请求对象
     * @param msg     request请求护具
     * @param ctx     返回通道
     * @return
     */
    public static boolean nonCrossVerify(HttpRequest request, Object msg, ChannelHandlerContext ctx) {
        //获取uri数据
        String uri = request.uri();

        //记录并返回消息体是否被消费了
        boolean messagePurchase = true;

        /* **************************************************************/
        if (uri.equals("/netty1")) {
            Map<String, Object> map = FormData.getParam(msg);
            httpResponse(ctx, msg, JSON.toJSONString("hello world 1"));
        }
        //管理员登录操作
        else if (uri.equals("/managerLogin")) {
            ResponseData responseData = LoginOpt.managerLogin(msg);
            httpResponse(ctx, msg, responseData);
        }
        //根据用户id和session获取用户信息数据
        else if (uri.equals("/getUserInfoByIdAndSessionId")) {
            ResponseData responseData = LoginOpt.getUserInfoByIdAndSessionId(msg);
            httpResponse(ctx, msg, responseData);
        }
        //保存新闻数据
        else if (uri.equals("/saveNewsData")) {
            ResponseData responseData = NewsOpt.saveNewsData(msg);
            httpResponse(ctx, msg, responseData);
        }
        //保存后勤服务数据
        else if (uri.equals("/saveHqNewsData")) {
            ResponseData responseData = NewsOpt.saveHqNewsData(msg);
            httpResponse(ctx, msg, responseData);
        }
        //拷贝新闻数据
        else if (uri.equals("/copyNews")) {
            ResponseData responseData = NewsOpt.copyNews(msg);
            httpResponse(ctx, msg, responseData);
        }
        //拷贝后勤服务数据
        else if (uri.equals("/copyNewsHq")) {
            ResponseData responseData = NewsOpt.copyNewsHq(msg);
            httpResponse(ctx, msg, responseData);
        }
        //删除新闻数据
        else if (uri.equals("/deleteNews")) {
            ResponseData responseData = NewsOpt.deleteNews(msg);
            httpResponse(ctx, msg, responseData);
        }
        //删除后勤服务数据
        else if (uri.equals("/deleteNewsHq")) {
            ResponseData responseData = NewsOpt.deleteNewsHq(msg);
            httpResponse(ctx, msg, responseData);
        }
        //批量删除新闻数据
        else if (uri.equals("/deleteBatchNews")) {
            ResponseData responseData = NewsOpt.deleteBatchNews(msg);
            httpResponse(ctx, msg, responseData);
        }
        //批量删除后勤服务数据
        else if (uri.equals("/deleteBatchNewsHq")) {
            ResponseData responseData = NewsOpt.deleteBatchNewsHq(msg);
            httpResponse(ctx, msg, responseData);
        }
        //获取指定范围内的新闻数据
        else if (uri.equals("/getRangeNewsListToBg")) {
            ResponseData responseData = NewsOpt.getRangeNewsListToBg(msg);
            httpResponse(ctx, msg, responseData);
        }
        //获取指定范围内的后勤服务数据
        else if (uri.equals("/getRangeNewsListToBgHq")) {
            ResponseData responseData = NewsOpt.getRangeNewsListToBgHq(msg);
            httpResponse(ctx, msg, responseData);
        }
        //获取某条新闻详情信息
        else if (uri.equals("/getRangeNewsListToPhone")) {
            ResponseData responseData = NewsOpt.getRangeNewsListToPhone(msg);
            httpResponse(ctx, msg, responseData);
        }
        //获取该用户对动态新闻的用户操作数据情况
        else if (uri.equals("/getUserDynamicInfo")) {
            ResponseData responseData = NewsOpt.getUserDynamicInfo(msg);
            httpResponse(ctx, msg, responseData);
        }
        //获取该用户对动态后勤服务的用户操作数据情况
        else if (uri.equals("/getUserDynamicInfoHq")) {
            ResponseData responseData = NewsOpt.getUserDynamicInfoHq(msg);
            httpResponse(ctx, msg, responseData);
        }
        //更新用户对动态新闻的点赞情况
        else if (uri.equals("/updatePitchCount")) {
            ResponseData responseData = NewsOpt.updatePitchCount(msg);
            httpResponse(ctx, msg, responseData);
        }
        //更新用户对动态新闻的点赞情况
        else if (uri.equals("/updatePitchCountHq")) {
            ResponseData responseData = NewsOpt.updatePitchCountHq(msg);
            httpResponse(ctx, msg, responseData);
        }
        //上传资源信息
        else if (uri.equals("/uploadResource")) {
            ResponseData responseData = NewsOpt.uploadResource(msg);
            httpResponse(ctx, msg, responseData);
        }
        //置顶功能设置
        else if (uri.equals("/setDynamicStickInfo")) {
            ResponseData responseData = NewsOpt.setDynamicStickInfo(msg);
            httpResponse(ctx, msg, responseData);
        }
        //置顶功能设置
        else if (uri.equals("/setDynamicStickInfoHq")) {
            ResponseData responseData = NewsOpt.setDynamicStickInfoHq(msg);
            httpResponse(ctx, msg, responseData);
        }
        //管理员在PC客户端审核时搜索相关标题的数据
        else if (uri.equals("/searchNewsList")) {
            ResponseData responseData = NewsOpt.searchNewsList(msg);
            httpResponse(ctx, msg, responseData);
        }
        //管理员在PC客户端审核时搜索相关标题的数据
        else if (uri.equals("/searchNewsListHq")) {
            ResponseData responseData = NewsOpt.searchNewsListHq(msg);
            httpResponse(ctx, msg, responseData);
        }


        /*add by lxc 新增朋友圈后台处理功能 *********************************************/
        //获取指定范围内的朋友圈数据
        else if (uri.equals("/getRangeFriendCircleListToBg")) {
            ResponseData responseData = FriendCircleOpt.getRangeFriendCircleListToBg(msg);
            httpResponse(ctx, msg, responseData);
        }
        //保存朋友圈数据，手机客户端提交的
        else if (uri.equals("/saveFriendCircle")) {
            ResponseData responseData = FriendCircleOpt.saveFriendCircle(msg);
            httpResponse(ctx, msg, responseData);
        }
        //获取用户发表的所有朋友圈数据信息
        else if (uri.equals("/getUserFriendInfoToPhone")) {
            ResponseData responseData = FriendCircleOpt.getUserFriendInfoToPhone(msg);
            httpResponse(ctx, msg, responseData);
        }
        //拷贝朋友圈数据
        else if (uri.equals("/copyFriend")) {
            ResponseData responseData = FriendCircleOpt.copyFriend(msg);
            httpResponse(ctx, msg, responseData);
        }
        //删除朋友圈数据
        else if (uri.equals("/deleteFriend")) {
            ResponseData responseData = FriendCircleOpt.deleteFriend(msg);
            httpResponse(ctx, msg, responseData);
        }
        //批量删除朋友圈数据
        else if (uri.equals("/deleteBatchFriend")) {
            ResponseData responseData = FriendCircleOpt.deleteBatchFriend(msg);
            httpResponse(ctx, msg, responseData);
        }
        //获取某条动态信息的图片信息
        else if (uri.equals("/selectFriendCircleImg")) {
            ResponseData responseData = FriendCircleOpt.selectFriendCircleImg(msg);
            httpResponse(ctx, msg, responseData);
        }
        //获取某条动态信息
        else if (uri.equals("/selectFriendCircleInfo")) {
            ResponseData responseData = FriendCircleOpt.selectFriendCircleInfo(msg);
            httpResponse(ctx, msg, responseData);
        }
        //获取朋友圈资源图片数据
        else if (uri.equals("/getFriendCircleResourceImg")) {
            ResponseData responseData = FriendCircleOpt.getFriendCircleResourceImg(msg);
            httpResponse(ctx, msg, responseData);
        }
        //获取指定范围内的发布的朋友圈数据
        else if (uri.equals("/getPublishedFriendToPhone")) {
            ResponseData responseData = FriendCircleOpt.getPublishedFriendToPhone(msg);
            httpResponse(ctx, msg, responseData);
        }
        //模糊搜索指定title和时间字段的数据
        else if (uri.equals("/searchNews")) {
            ResponseData responseData = FriendCircleOpt.searchNews(msg);
            httpResponse(ctx, msg, responseData);
        }
        //模糊搜索指定title和时间字段的数据
        else if (uri.equals("/searchUserNews")) {
            ResponseData responseData = FriendCircleOpt.searchUserNews(msg);
            httpResponse(ctx, msg, responseData);
        }
        //管理员在PC客户端审核时搜索相关标题的数据
        else if (uri.equals("/searchFriendCircle")) {
            ResponseData responseData = FriendCircleOpt.searchFriendCircle(msg);
            httpResponse(ctx, msg, responseData);
        }
        //管理员在PC客户端审核时搜索相关标题的数据
        else if (uri.equals("/searchFriendCirclePc")) {
            ResponseData responseData = FriendCircleOpt.searchFriendCirclePc(msg);
            httpResponse(ctx, msg, responseData);
        }
        //审核操作时获取该条朋友圈消息所有图片数据和消息体信息
        else if (uri.equals("/getDynamicImgAndInfo")) {
            ResponseData responseData = FriendCircleOpt.getDynamicImgAndInfo(msg);
            httpResponse(ctx, msg, responseData);
        }


        /* 积分获取等相关设置  *********************************************/
        //获取各部门积分数据
        else if (uri.equals("/getDeptScore")) {
            ResponseData responseData = FriendCircleOpt.getDeptScore(msg);
            httpResponse(ctx, msg, responseData);
        }
        //获取某部门内部成员的积分数据
        else if (uri.equals("/getDeptUserScore")) {
            ResponseData responseData = FriendCircleOpt.getDeptUserScore(msg);
            httpResponse(ctx, msg, responseData);
        }
        //获取某部门内部成员的积分数据明细
        else if (uri.equals("/getDeptScoreDetail")) {
            ResponseData responseData = FriendCircleOpt.getDeptScoreDetail(msg);
            httpResponse(ctx, msg, responseData);
        }
        //获取用户积分数据
        else if (uri.equals("/getUserScore")) {
            ResponseData responseData = FriendCircleOpt.getUserScore(msg);
            httpResponse(ctx, msg, responseData);
        }
        //获取朋友圈数据的状态
        else if (uri.equals("/selectFriendCircleStatus")) {
            ResponseData responseData = FriendCircleOpt.selectFriendCircleStatus(msg);
            httpResponse(ctx, msg, responseData);
        }
        //获取朋友圈数据的更新时间
        else if (uri.equals("/selectFriendCircleUpdateTime")) {
            ResponseData responseData = FriendCircleOpt.selectFriendCircleUpdateTime(msg);
            httpResponse(ctx, msg, responseData);
        }
        //监控后台程序
        else if (uri.equals("/watchAppFun")) {
            ResponseData responseData = FriendCircleOpt.watchAppFun(msg);
            httpResponse(ctx, msg, responseData);
        }


        //获取所有朋友圈数据
//        else if(uri.equals("/getAllFriendList")){
//            ResponseData responseData = NewsOpt.getAllNewsList(msg);
//            httpResponse(ctx, msg, responseData);
//        }
//        //获取某条朋友圈详情信息
//        else if(uri.equals("/getRangeFriendList")){
//            ResponseData responseData = NewsOpt.getRangeNewsList(msg);
//            httpResponse(ctx, msg, responseData);
//        }


        /* 审核员相关设置  *********************************************/
        //管理员在手机客户端审核时查看所有待审的朋友圈新闻数据
        else if (uri.equals("/getPendVerifyFriendCircle")) {
            ResponseData responseData = CheckerOpt.getPendVerifyFriendCircle(msg);
            httpResponse(ctx, msg, responseData);
        }
        //管理员在手机客户端审核时搜索相关标题的数据
        else if (uri.equals("/searchPendVerifyFriendCircle")) {
            ResponseData responseData = CheckerOpt.searchPendVerifyFriendCircle(msg);
            httpResponse(ctx, msg, responseData);
        }
        //获取checker信息数据
        else if (uri.equals("/verifyCheckerInfo")) {
            ResponseData responseData = CheckerOpt.verifyCheckerInfo(msg);
            httpResponse(ctx, msg, responseData);
        }
        //获取初审通过信息数据
        else if (uri.equals("/selectFirstCheckInfo")) {
            ResponseData responseData = CheckerOpt.selectFirstCheckInfo(msg);
            httpResponse(ctx, msg, responseData);
        }
        //获取初审checker信息数据
        else if (uri.equals("/searchCheckerInfoFirstList")) {
            ResponseData responseData = CheckerOpt.searchCheckerInfoFirstList(msg);
            httpResponse(ctx, msg, responseData);
        }
        //获取终审checker信息数据
        else if (uri.equals("/searchCheckerInfoFinalList")) {
            ResponseData responseData = CheckerOpt.searchCheckerInfoFinalList(msg);
            httpResponse(ctx, msg, responseData);
        }
        //获取历史评审信息数据
        else if (uri.equals("/getHistoryCheckResult")) {
            ResponseData responseData = CheckerOpt.getHistoryCheckResult(msg);
            httpResponse(ctx, msg, responseData);
        }
        //提交审核信息数据
        else if (uri.equals("/submitCheckResult")) {
            ResponseData responseData = CheckerOpt.submitCheckResult(msg);
            httpResponse(ctx, msg, responseData);
        }
        //提交修改审核信息数据
        else if (uri.equals("/updateCheckResult")) {
            ResponseData responseData = CheckerOpt.updateCheckResult(msg);
            httpResponse(ctx, msg, responseData);
        }
        //重新保存HTML数据
        else if (uri.equals("/updateNewHtmlFile")) {
            ResponseData responseData = CheckerOpt.updateNewHtmlFile(msg);
            httpResponse(ctx, msg, responseData);
        }
        //提交审核信息数
        else if (uri.equals("/getAllCheckerInfo")) {
            ResponseData responseData = CheckerOpt.getAllCheckerInfo(msg);
            httpResponse(ctx, msg, responseData);
        }
        //提交审核信息数
        else if (uri.equals("/updateCheckerInfo")) {
            ResponseData responseData = CheckerOpt.updateCheckerInfo(msg);
            httpResponse(ctx, msg, responseData);
        }
        //二级审核员查看一级审核员审核的消息以及被反驳的消息
        else if (uri.equals("/getFirstCheckAndRejectNews")) {
            ResponseData responseData = CheckerOpt.getFirstCheckAndRejectNews(msg);
            httpResponse(ctx, msg, responseData);
        }
        //二级审核员查看已终审通过的记录
        else if (uri.equals("/getSuccessNews")) {
            ResponseData responseData = CheckerOpt.getSuccessNews(msg);
            httpResponse(ctx, msg, responseData);
        }


        //若尚未消费该事件，则返回false
        else {
            messagePurchase = false;
        }
        return messagePurchase;
    }


    /**
     * 无需获取登录状态才能访问的链接请求
     *
     * @param request request请求对象
     * @param msg     request请求护具
     * @param ctx     返回通道
     */
    public static boolean nonLoginAccess(HttpRequest request, Object msg, ChannelHandlerContext ctx) {
        //获取uri数据
        String uri = request.uri();

        //记录并返回消息体是否被消费了
        boolean messagePurchase = true;

        /* **************************************************************/
        if (uri.equals("/netty2")) {
            Map<String, Object> map = FormData.getParam(msg);
            httpResponse(ctx, msg, JSON.toJSONString("hello world 2"));

        }
        //若尚未消费该事件，则返回false
        else {
            messagePurchase = false;
        }
        return messagePurchase;
    }


    /**
     * 需要用户已登录状态才可访问的请求
     *
     * @param request uri请求地址
     * @param msg     request请求护具
     * @param ctx     返回通道
     */
    public static void loginAccess(HttpRequest request, Object msg, ChannelHandlerContext ctx) {
        //获取uri数据
        String uri = request.uri();

        /* **************************************************************/
        /*测试专区*/
        if (uri.equals("/netty3")) {
            Map<String, Object> map = FormData.getParam(msg);
            httpResponse(ctx, msg, JSON.toJSONString("hello world 3"));

        } else {
            String message = "server do not serve such request: " + uri;
            httpResponse(ctx, msg, message);
            BackAccess.logger.debug(message);
        }
    }


    /**
     * 返回http请求相关消息
     *
     * @param ctx 通信通道
     * @param msg 请求的引用
     */
    public static void httpResponse(ChannelHandlerContext ctx, Object msg, Object dataBack) {

        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(JSON.toJSONString(dataBack).getBytes()));
        response.headers().set(CONTENT_TYPE, Common.RETURN_JSON);
        commonResponse(ctx, msg, response);
        BackAccess.logger.debug("Return Response Data: \n" + dataBack.toString());
    }


    /**
     * 返回http请求相关消息
     *
     * @param ctx 通信通道
     * @param msg 请求的引用
     */
    public static void httpResponsePureHtml(ChannelHandlerContext ctx, Object msg, String htmlData) {
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(htmlData.getBytes()));
        response.headers().set(CONTENT_TYPE, Common.RETURN_TEXT_HTML);
        commonResponse(ctx, msg, response);
        BackAccess.logger.debug("Return html pure data response");
    }


    /**
     * 返回json数据和HTML数据相同的消息体
     *
     * @param ctx      通信通道
     * @param msg      请求数据
     * @param response 请求返回消息封装体
     */
    private static void commonResponse(ChannelHandlerContext ctx, Object msg, FullHttpResponse response) {
        if (HttpUtil.is100ContinueExpected((HttpMessage) msg)) {
            ctx.write(new DefaultFullHttpResponse(HTTP_1_1, CONTINUE));
        }
        boolean keepAlive = HttpUtil.isKeepAlive((HttpMessage) msg);
        response.headers().set(CONTENT_LENGTH, response.content().readableBytes());
        response.headers().set(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        response.headers().set(ACCESS_CONTROL_ALLOW_METHODS, "POST");
        response.headers().set(ACCESS_CONTROL_ALLOW_HEADERS, "user_id, session_id, *");
        response.headers().set(ACCEPT, "*");
        if (!keepAlive) {
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        } else {
            ctx.writeAndFlush(response);
        }
    }

}