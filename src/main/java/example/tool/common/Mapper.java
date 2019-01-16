package example.tool.common;

/**
 * Created by Administrator on 2018/2/24.
 */
public class Mapper {

    //manager表的数据库操作mapper************************************************************
    //select
    public static final String MANAGER_LOGIN = "managerLogin";
    public static final String GET_MANAGER_DATA = "getManagerData";


    //user表的数据库操作mapper************************************************************
    //select
    public static final String SELECT_USER_BY_WX_ID = "selectUserByWxId";




    //checker表的数据库操作mapper************************************************************
    //select
    public static final String GET_CHECKER = "getChecker";

    public static final String GET_FIRST_CHECKER_INFO = "getFirstCheckerInfo";

    public static final String GET_FIRST_CHECKER = "getFirstChecker";

    public static final String GET_FINAL_CHECKER = "getFinalChecker";

    public static final String GET_ALL_CHECKER_INFO = "getAllCheckerInfo";

    //insert
    public static final String GRANT_CHECKER_RIGHT = "grantCheckerRight";

    //delete
    public static final String REVOKE_CHECKER_RIGHT = "revokeCheckerRight";




    //checkresult表的数据库操作mapper************************************************************
    //select
    public static final String GET_HISTORY_CHECK_RESULT = "getHistoryCheckResult";
    public static final String GET_FIRST_CHECK_AND_REJECT_NEWS = "getFirstCheckAndRejectNews";
    public static final String GET_SUCCESS_NEWS = "getSuccessNews";

    //insert
    public static final String INSERT_NEW_CHECK_RESULT = "insertNewCheckResult";



    //user_dynamic表的数据库操作mapper************************************************************
    //select
    public static final String GET_USER_DYNAMIC_INFO = "getUserDynamicInfo";

    //insert
    public static final String INSERT_NEW_USER_DYNAMIC_INFO = "insertNewUserDynamicInfo";

    //update
    public static final String UPDATE_USER_DYNAMIC_VIEW_COUNT = "updateUserDynamicViewCount";
    public static final String UPDATE_USER_DYNAMIC_PITCH_COUNT = "updateUserDynamicPitchCount";




    //dynamicInfo表的数据库操作mapper************************************************************
    //dynamicInfo表  新闻类数据操作
    //select
    public static final String GET_SINGLE_NEWS_DETAIL_INFO = "getSingleNewsDetailInfo";
    public static final String GET_SINGLE_NEWS_DETAIL_INFO_HQ = "getSingleNewsDetailInfoHq";
    public static final String GET_ALL_NEWS_INFO = "getAllNewsInfo";
    public static final String GET_RANGE_NEWS_INFO_TO_BG = "getRangeNewsInfoToBg";
    public static final String GET_RANGE_NEWS_INFO_TO_BG_HQ = "getRangeNewsInfoToBgHq";
    public static final String GET_RANGE_NEWS_INFO_TO_PHONE = "getRangeNewsInfoToPhone";
    public static final String GET_NEWS_NUM = "getNewsNum";
    public static final String GET_NEWS_NUM_HQ = "getNewsNumHq";
    public static final String SEARCH_NEWS = "searchNews";
    public static final String SEARCH_USER_NEWS = "searchUserNews";
    public static final String SEARCH_NEWS_LIST = "searchNewsList";
    public static final String SEARCH_NEWS_LIST_HQ = "searchNewsListHq";
    public static final String GET_DYNAMIC_INFO = "getDynamicInfo";

    //insert
    public static final String INSERT_NEW_NEWS = "insertNewNews";
    public static final String INSERT_NEW_NEWS_HQ = "insertNewNewsHq";
    public static final String DELETE_NEWS = "deleteNews";
    public static final String DELETE_NEWS_HQ = "deleteNewsHq";
    public static final String INSERT_TEST_NEWS_RECORD_TO_DYNAMICINFO = "insertTestNewsRecordToDynamicInfo";

    //update
    public static final String UPDATE_NEWS = "updateNews";
    public static final String UPDATE_NEWS_HQ = "updateNewsHq";


    //dynamicInfo表  朋友圈类数据操作**********************
    //select
    public static final String GET_RANGE_FRIEND_CIRCLE_INFO_TO_BG = "getRangeFriendCircleInfoToBg";
    public static final String GET_SINGLE_FRIEND_DETAIL_INFO = "getSingleFriendDetailInfo";
    public static final String GET_ALL_FRIEND_INFO = "getAllFriendInfo";
    public static final String GET_RANGE_FRIEND_INFO = "getRangeFriendInfo";
    public static final String GET_PEND_VERIFY_FRIEND_CIRCLE = "getPendVerifyFriendCircle";
    public static final String SEARCH_PEND_VERIFY_FRIEND_CIRCLE = "searchPendVerifyFriendCircle";
    public static final String GET_FRIEND_NUM = "getFriendNum";
    public static final String GET_FRIEND_IMAGE = "getFriendImage";
    public static final String GET_FRIEND_USER_INFO = "getUserFriendInfoToPhone";
    public static final String GET_DEPT_SCORE = "getDeptScore";
    public static final String GET_DEPT_SCORE_DETAIL = "getDeptScoreDetail";
    public static final String GET_DEPT_USER_SCORE = "getDeptUserScore";
    public static final String GET_PUBLISHED_FRIEND_TO_PHONE = "getPublishedFriendToPhone";
    public static final String GET_USER_SCORE = "getUserScore";
    public static final String GET_DYNAMIC_STATUS = "getDynamicStatus";
    public static final String GET_DYNAMIC_UPDATETIME = "getDynamicUpdateTime";
    public static final String SEARCH_FRIEND_CIRCLE_NEWS = "searchFriendCircleNews";
    public static final String SEARCH_FRIEND_CIRCLE_NEWS_PC = "searchFriendCircleNewsPc";
    public static final String SEARCH_FRIEND_CIRCLE_NEWS_PC_NUM = "searchFriendCircleNewsPcNum";
    public static final String GET_FRIEND_CIRCLE_INFO = "selectFriendCircleInfo";

    //insert
    public static final String INSERT_NEW_FRIEND = "insertNewFriend";

    //delete
    public static final String DELETE_FRIEND = "deleteFriend";
    //public static final String DELETE_DYNAMIC_DEPTSCORE = "deleteDynamicDeptScore";

    //update
    public static final String UPDATE_FRIEND = "updateFriend";
    public static final String SET_DYNAMIC_STICK_INFO = "setDynamicStickInfo";
    public static final String SET_DYNAMIC_STICK_INFO_HQ = "setDynamicStickInfoHq";
    public static final String INSERT_TEST_FRIEND_RECORD_TO_DYNAMICINFO = "insertTestFriendRecordToDynamicInfo";
    public static final String UPDATE_DYNAMIC_VIEW_COUNT = "updateDynamicViewCount";
    public static final String UPDATE_DYNAMIC_VIEW_COUNT_HQ = "updateDynamicViewCountHq";
    public static final String UPDATE_DYNAMIC_PITCH_COUNT = "updateDynamicPitchCount";
    public static final String UPDATE_DYNAMIC_PITCH_COUNT_HQ = "updateDynamicPitchCountHq";
//    public static final String UPDATE_DYNAMIC_DEPT_STATUS_SCORE = "updateDynamicDeptStatusScore";
//    public static final String UPDATE_DYNAMIC_DEPT_STATUS_TITLE = "updateDynamicDeptStatusTitle";
    public static final String UPDATE_DYNAMIC_INFO_STATUS = "updateDynamicInfoStatus";
    public static final String UPDATE_DYNAMIC_INFO_TITLE = "updateDynamicInfoTitle";
    public static final String UPDATE_DYNAMIC_INFO_CHECK = "updateDynamicInfoCheck";






    //dynamicimg表
    //select
    public static final String GET_FRIEND_CIRCLE_RESOURCE = "getFriendCircleResourceImg";

    //insert
    public static final String INSERT_NEW_FRIENDIMG = "insertNewFriendImg";

    //delete
    public static final String DELETE_RESOURCE_IMG = "deleteResourceImg";

    //update
    public static final String UPDATE_FRIENDIMG = "updateFriendImg";



    //dynamic_deptscore表
    //select
//    public static final String INSERT_NEW_DYNAMIC_DEPT_INFO= "insertNewDynamicDeptInfo";
}













