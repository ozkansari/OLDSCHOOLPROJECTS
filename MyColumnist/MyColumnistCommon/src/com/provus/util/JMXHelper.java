package com.provus.util;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.remote.JMXServiceURL;
import javax.management.remote.rmi.RMIConnectorServer;

import org.apache.log4j.Logger;

public class JMXHelper {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(JMXHelper.class);

	private static RMIConnectorServer rmiConnectorServer;

	public static void startJMX(int port) {
		if (port <= 0) {
			logger.info("JMX port < 0. So will not start");
			return;
		}
		if (rmiConnectorServer != null) {
			logger.error("RMIConnectorServer already started for JMX");
			return;
		}
		try {
			MBeanServer mbs;
			mbs = ManagementFactory.getPlatformMBeanServer();
			String jndiPath = "/jmxconnector";
			LocateRegistry.createRegistry(port);
			JMXServiceURL serviceURL = new JMXServiceURL("rmi", null, 0,
					"/jndi/rmi://localhost:" + port + jndiPath);
			rmiConnectorServer = new RMIConnectorServer(serviceURL, null);
			ObjectName onServer = new ObjectName(
					"Provus:type=JMXConnector");
			mbs.registerMBean(rmiConnectorServer, onServer);
			rmiConnectorServer.start();
			logger.debug("JMX RMI Server started:" + serviceURL);

		} catch (Exception e) {
			logger.error("Error occured when initializing JMX module", e);
		}

	}

	public static void stopJMX() {
		if (rmiConnectorServer == null) {
			logger.warn("RMIConnectorServer not started");
			return;
		}
		try {
			rmiConnectorServer.stop();
		} catch (IOException e) {
			logger.error("Error occured when stopping JMX", e);
		}
		rmiConnectorServer = null;
	}
}
