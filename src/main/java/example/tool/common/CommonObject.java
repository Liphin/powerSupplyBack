package example.tool.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/16.
 * 一些对象数据的初始化
 */
public class CommonObject {

    //定义各种对象信息数据
    private static final Map<Integer, String> loginVerify = new HashMap<>();//用户登录时验证的userId:sessionId等信息；

    static {
        //各种对象信息的初始化操作
    }

    //一些getter方法返回对象数据信息
    public static Map<Integer, String> getLoginVerify() {
        return loginVerify;
    }

}
