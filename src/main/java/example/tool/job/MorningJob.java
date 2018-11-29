package example.tool.job;

import com.alibaba.fastjson.JSON;
import example.operation.entity.Department;
import example.operation.entity.User;
import example.tool.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/28.
 */
public class MorningJob implements Job {

    private static Logger logger = LoggerFactory.getLogger(MorningJob.class);

    /**
     * 早上6点运行的程序
     *
     * @param context
     * @throws JobExecutionException
     */
    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        try {
            //运行程序更新用户和部门信息表
            updateUserDeptInfo();

        } catch (Exception e) {
            MorningJob.logger.error("MidNightJob error", e);
        }
    }


    /**
     * 运行程序更新用户和部门信息表
     */
    public void updateUserDeptInfo() {
        //资源初始化操作
        SqlSession sqlSession = MybatisUtils.getSession();
        String message = "";
        try {
            //1、http请求前端后台获取所有部门department信息数据
            String deptJsonStr = "TODO";
            List<Department> deptJson = JSON.parseArray(deptJsonStr, Department.class);

            //2、http请求前端后台获取所有员工user信息数据
            String userJsonStr = "TODO";
            List<User> userJson = JSON.parseArray(userJsonStr, User.class);

            //3、部门数据表更新操作
            sqlSession.delete(""); //清空原来department表
            for (Department dept : deptJson) {
                sqlSession.insert("", dept); //逐条循环插入部门表最新department数据
            }

            //4、用户数据表更新操作
            sqlSession.delete(""); //清空原来user表
            for (Department dept : deptJson) {
                sqlSession.insert("", dept); //逐条循环插入用户表最新user数据
            }

            //如果上述无异常情况则进行commit数据库
            sqlSession.commit();

        } catch (Exception e) {
            //系统发生错误
            message = "system error updateUserDeptInfo";
            MorningJob.logger.error(message, e);

        } finally {
            sqlSession.close();
        }
    }


}


