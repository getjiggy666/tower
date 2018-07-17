package com.telecom.common;

import javax.servlet.ServletContext;

import com.telecom.directive.ArticleListDirective;
import com.telecom.directive.CheckboxDirective;
import com.telecom.directive.PaginationDirective;
import com.telecom.directive.SubstringMethod;
import com.telecom.util.SpringUtil;

import freemarker.template.TemplateException;

public class FreemarkerManager extends org.apache.struts2.views.freemarker.FreemarkerManager {

	public synchronized freemarker.template.Configuration getConfiguration(ServletContext servletContext) {
		config = (freemarker.template.Configuration) servletContext.getAttribute(CONFIG_SERVLET_CONTEXT_KEY);
        if (config == null) {
            try {
                init(servletContext);
            } catch (TemplateException e) {
            	e.printStackTrace();
            }
            SubstringMethod substringMethod = (SubstringMethod) SpringUtil.getBean("substringMethod");
			CheckboxDirective checkboxDirective = (CheckboxDirective) SpringUtil.getBean("checkboxDirective");
			PaginationDirective paginationDirective = (PaginationDirective) SpringUtil.getBean("paginationDirective");
			ArticleListDirective articleListDirective = (ArticleListDirective) SpringUtil.getBean("articleListDirective");
			
			config.setSharedVariable(SubstringMethod.TAG_NAME, substringMethod);
			config.setSharedVariable(CheckboxDirective.TAG_NAME, checkboxDirective);
			config.setSharedVariable(PaginationDirective.TAG_NAME, paginationDirective);
			config.setSharedVariable(ArticleListDirective.TAG_NAME, articleListDirective);
			
            // store this configuration in the servlet context
            servletContext.setAttribute(CONFIG_SERVLET_CONTEXT_KEY, config);
        }
        return config;
	}

}