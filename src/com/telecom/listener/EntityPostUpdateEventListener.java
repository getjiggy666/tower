package com.telecom.listener;

import javax.annotation.Resource;

import org.hibernate.event.PostUpdateEvent;
import org.hibernate.event.PostUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.stereotype.Component;

import com.telecom.service.JobService;

@Component("entityPostUpdateEventListener")
public class EntityPostUpdateEventListener implements PostUpdateEventListener {
	
	private static final long serialVersionUID = -5981524888794053786L;
	
	private static final String ARTICLE_CATEGORY_ENTITY_NAME = "com.telecom.entity.ArticleCategory";
	private static final String ARTICLE_ENTITY_NAME = "com.telecom.entity.Article";
	
	@Resource(name = "jobServiceImpl")
	private JobService jobService;

	public void onPostUpdate(PostUpdateEvent event) {
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