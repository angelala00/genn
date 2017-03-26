package ${targetPackage}.manager.controller;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Jiangchi
 * 
 */
public abstract class BaseController {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	protected ServletContext servletContext;
}
