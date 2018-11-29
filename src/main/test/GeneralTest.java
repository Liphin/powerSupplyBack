import example.operation.impl.common.CommonService;
import example.tool.common.Mapper;
import example.tool.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * Created by Administrator on 2018/9/22.
 */
public class GeneralTest {

    @Test
    public void getUnionCode(){
        System.out.println(CommonService.getUnionId());
        System.out.println(CommonService.getUnionId());
        System.out.println(CommonService.getUnionId());
    }



    /**
     * 插入新闻测试数据
     */
    @Test
    public void insertRecordNewsToDynamicInfoDb() {
        int num = 30;
        SqlSession sqlSession = MybatisUtils.getSession();
        try {
            int influenceNum = sqlSession.insert(Mapper.INSERT_TEST_NEWS_RECORD_TO_DYNAMICINFO, num);
            System.out.println("influence num : " + influenceNum);
            sqlSession.commit();

        }catch (Exception e){
            e.printStackTrace();

        }finally {
            sqlSession.close();
        }
    }


    /**
     * 插入朋友圈测试数据
     */
    @Test
    public void insertFriendRecordToDynamicInfoDb() {
        int num = 30;
        SqlSession sqlSession = MybatisUtils.getSession();
        try {
            int influenceNum = sqlSession.insert(Mapper.INSERT_TEST_FRIEND_RECORD_TO_DYNAMICINFO, num);
            System.out.println("influence num : " + influenceNum);
            sqlSession.commit();

        }catch (Exception e){
            e.printStackTrace();

        }finally {
            sqlSession.close();
        }
    }
}
