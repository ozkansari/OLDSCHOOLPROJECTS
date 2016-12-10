package com.mycolumnist.schedule.job;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.mycolumnist.model.parser.base.ParserVariables;


/**
 * <p>
 * Job to parse columns and columnists
 * </p>
 * 
 * @author ozkansari
 */
public class ParserJob implements Job {

    private static Logger logger = Logger.getLogger(ParserJob.class);

    /**
     * Empty constructor for job initilization
     */
    public ParserJob() {
    }

    /**
     * <p>
     * Called by the <code>{@link org.quartz.Scheduler}</code> when a
     * <code>{@link org.quartz.Trigger}</code> fires that is associated with
     * the <code>Job</code>.
     * </p>
     * 
     * @throws JobExecutionException
     *             if there is an exception while executing the job.
     */
    public void execute(JobExecutionContext context)
        throws JobExecutionException {

        // imply print out its job name and the
        // date and time that it is running
        String jobName = context.getJobDetail().getFullName();
        logger.info("SimpleJob says: " + jobName + " executing at " + new Date());

        ParserVariables.parseAll();
        
    }

}
