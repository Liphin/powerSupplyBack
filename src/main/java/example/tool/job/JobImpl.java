package example.tool.job;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by Administrator on 2018/4/28.
 */
public class JobImpl {

    private static Logger logger = LoggerFactory.getLogger(JobImpl.class);

    //实例化后即刻run task
    public JobImpl() {

        //A. 每天早上6点更新用户和部门数据库表
        //morningTask();
    }

    /**
     * 每天早上6点更新数据库表
     * @deprecated
     */
    private static void morningTask() {
        try {
            //设置午夜job的task
            JobDetail job = JobBuilder.newJob(MorningJob.class)
                    .withIdentity("morningTask", "version1").build();

            //设置触发器
            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity("sixAMTask", "version1")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0 6 * * ?")) //每日凌晨6点去更新数据库表的job  0 0 6 * * ?
                    .build();

            //运行该task
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(job, trigger); //开始触发run task
            JobImpl.logger.debug("Run morningTask");

        } catch (Exception e) {
            JobImpl.logger.warn("morningTask failure", e);
        }
    }

}












