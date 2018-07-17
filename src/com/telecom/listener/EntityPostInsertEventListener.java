package com.telecom.listener;

import javax.annotation.Resource;

import org.hibernate.event.PostInsertEvent;
import org.hibernate.event.PostInsertEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.stereotype.Component;

import com.telecom.service.JobService;

@Component("entityPostInsertEventListener")
public class EntityPostInsertEventListener implements PostInsertEventListener {
	
	private static final long serialVersionUID = -6010867340759752487L;
	private static final String ARTICLE_CATEGORY_ENTITY_NAME = "com.telecom.entity.ArticleCategory";
	private static final String ARTICLE_ENTITY_NAME = "com.telecom.entity.Article";
	
	@Resource(name = "jobServiceImpl")
	private JobService jobService;

	public void onPostInsert(PostInsertEvent event) {
		EntityPersister entityPersister = event.getPersister();
		String entityName = entityPersister.getEntityName();
		String id = String.valueOf(event.getId());
		
		if (entityName.equals(ARTICLE_CATEGORY_ENTITY_NAME)) {
			jobService.buildArticleContentHtml();
		} else if (entityName.equals(ARTICLE_ENTITY_NAME)) {
			jobService.buildArticleContentHtml(id);
		}
	}

}