package fr.epsi.myEpsi.mbeans;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.Configurator;

public class ChangeLog implements ChangeLogMBean {
	
	@Override
	public void setValue(String level) {
		
		if(level.equals("INFO")) {
			Configurator.setRootLevel(Level.INFO);
		}
		if(level.equals("DEBUG")) {
			Configurator.setRootLevel(Level.DEBUG);
		}
		if(level.equals("ERROR")) {
			Configurator.setRootLevel(Level.ERROR);
		}
		if(level.equals("WARN")) {
			Configurator.setRootLevel(Level.WARN);
		}
		if(level.equals("TRACE")) {
			Configurator.setRootLevel(Level.TRACE);
		}
		if(level.equals("FATAL")) {
			Configurator.setRootLevel(Level.FATAL);
		}

	}

	@Override
	public String getValue() {
		return LogManager.getRootLogger().getLevel().name();

	}

}
