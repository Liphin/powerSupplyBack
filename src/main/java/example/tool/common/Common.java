package example.tool.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2018/2/12.
 */
public class Common {

    private static Logger logger = LoggerFactory.getLogger(Common.class.getName());

    //业务需求限制
    public static int COVERIMAGE_NUM = 1; //要求封面至少上传一张图片
    public static int FRIENDIMAGE_MAX_NUM = 10;//要求朋友圈最多上传十张图片
    public static int DYNAMIC_OPT_INSERT = 1;//新增记录
    public static int DYNAMIC_OPT_UPDATE = 2;//更新记录
    public static int FRIEND_CIRCLE_TYPE = 7;//供电朋友圈
    public static int FRIEND_DRAFT_STATUS = 0;//供电朋友圈草稿状态
    public static int FRIEND_FIRSTCHECK_STATUS = 1;//供电朋友圈一级审核状态
    public static int FRIENDCHECK_PASS_STATUS = 3;//供电朋友圈审核通过审核状态
    public static int FRIENDCHECK_NOT_PASS_STATUS = 4;//供电朋友圈初审不通过状态
    public static int FRIENDCHECK_FINAL_NOT_PASS_STATUS = 5;//供电朋友圈终审不通过状态
    public static int MANAGER_CHECK_LEVEL = 3;//管理员审核级别
    public static int CHECK_PASS_STATUS = 1;//审核通过状态

    //global环境设置配置
    public static String DEV_ENVIRONMENT = "dev"; //测试环境
    public static String PROD_ENVIRONMENT = "prod"; //生产环境
    public static String UTF8 = "UTF-8";
    public static String TRUE = "true";
    public static String FALSE = "false";
    public static String EMPTY = "";
    public static int DEFAULT_SCORE = 3;
    public static String SETTING_FILES = "com.viewcoder.setting.files"; //设定目标环境下的文件

    //数据返回设置
    public static String RETURN_TEXT_HTML = "text/html;charset=UTF-8";
    public static String RETURN_JSON = "application/json";

    //上传参数解析
    public static String PASSWORD = "password";
    public static String ID = "id";
    public static String USER_ID = "user_id";
    public static String WX_USER_ID = "wx_user_id";
    public static String CREATE_TIME = "create_time";
    public static String UPDATE_TIME = "update_time";
    public static String TOTAL_NUM = "totalNum";
    public static String NEWS_LIST_DATA = "newsListData";
    public static String FRIEND_CIRCLE_LIST_DATA = "friendCircleListData";
    public static String FRIEND_LIST_DATA = "friendListData";
    public static String TIMESTAMP = "timestamp";
    public static String DYNAMIC_TIMESTAMP = "dynamic_timestamp";
    public static String DYNAMIC_PITCH = "dynamic_pitch";
    public static String IMAGENUM = "image_num";
    public static String DELETE_LIST = "deleteList";
    public static String DYNAMICINFO = "dynamicinfo";
    public static String USER_DYNAMIC = "user_dynamic";
    public static String DYNAMICIMG = "dynamicimg";
    public static String COVER_IMG = "cover_img";
    public static String COVERIMGS = "coverimgs";
    public static String HTML = "html";
    public static String NEW_RESOURCE = "new_resource";
    public static String RESOURCE_TIMESTAMP = "resource_timestamp";
    public static String CHECK_LEVEL = "check_level";
    public static String MEMBERS = "members";
    public static String DEPT_FROM = "dept_from";
    public static String STICK_CD = "stick_cd";
    public static String STATUS_CD = "status_cd";
    public static String DEPARTMENT = "department";
    public static String SCORE = "score";
    public static String SEARCH = "search";


    //config文件的配置参数
    public static String DYNAMICINFOS_SYS_PATH_HTML = "dynamicinfos.system.path.html";
    public static String DYNAMICINFOS_SYS_PATH_COVERIMG = "dynamicinfos.system.path.coverimg";
    public static String DYNAMICINFOS_SYS_PATH_RESOURCE = "dynamicinfos.system.path.resource";

    //后缀名参数
    public static String SUFFIX_INDEX_HTML = "-index.html";
    public static String SUFFIX_PNG = ".png";

}
