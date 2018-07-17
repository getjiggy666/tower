package com.telecom.listener;

import javax.annotation.Resource;

import org.hibernate.event.PostDeleteEvent;
import org.hibernate.event.PostDeleteEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.stereotype.Component;

import com.telecom.entity.Article;
import com.telecom.service.JobService;

@Component("entityPostDeleteEventListener")
public class EntityPostDeleteEventListener implements PostDeleteEventListener {
	
	private static final long serialVersionUID = 616415776098455744L;
	
	private static final String ARTICLE_CATEGORY_ENTITY_NAME = "com.telecom.entity.ArticleCategory";
	private static final String ARTICLE_ENTITY_NAME = "com.telecom.entity.Article";
	
	@Resource(name = "jobServiceImpl")
	private JobService jobService;

	public void onPostDelete(PostDeleteEvent event) {
		EntityPersister entityPersister = event.getPersister();
		String entityName = entityPersister.getEntityName();
		
		if (entityName.equals(ARTICLE_CATEGORY_ENTITY_NAME)) {
			jobService.buildArticleContentHtml();
		} else if (entityName.equals(ARTICLE_ENTITY_NAME)) {
			Article article = (Article) event.getEntity();
			jobService.deleteArticleContentHtml(article.getHtmlPath(), article.getPageCount());
		}
	}

}