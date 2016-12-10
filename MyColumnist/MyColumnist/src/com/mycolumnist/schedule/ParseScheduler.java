package com.mycolumnist.schedule;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import com.mycolumnist.schedule.job.ParserJob;

/**
 * 
 * @author ozkansari
 *
 */
public class ParseScheduler implements javax.servlet.Servlet{
	
	private static Logger logger = Logger.getLogger(ParseScheduler.class);
	
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public void init(ServletConfig arg0) throws ServletException {
		try {
			// First we must get a reference to a scheduler
			SchedulerFactory sf = new StdSchedulerFactory();
			Scheduler sched = sf.getScheduler();
	
			// job 1 will run every day at 5 am
			JobDetail dailyParserJob = new JobDetail("dailyParserJob", "parseGroup", ParserJob.class);
			CronTrigger dailyParserTrigger = new CronTrigger("dailyParserTrigger", "parseGroup", "dailyParserJob", "parseGroup", "* 00 0/6 * * ? *");
			sched.addJob(dailyParserJob, true);
			Date ft = sched.scheduleJob(dailyParserTrigger);
			sched.start();
			
			logger.info(dailyParserJob.getFullName() + " has been scheduled to run at: " + ft
	                + " and repeat based on expression: "
	                + dailyParserTrigger.getCronExpression());
		
		} catch (SchedulerException e) {
			logger.error(e);
		} catch (ParseException e) {
			logger.error(e);
		}
	}

	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
}
