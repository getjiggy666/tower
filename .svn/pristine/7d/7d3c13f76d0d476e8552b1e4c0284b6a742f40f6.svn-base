package com.telecom.action.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.BeanUtils;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.telecom.entity.Article;
import com.telecom.entity.Article.ArticleType;
import com.telecom.entity.ArticleCategory;
import com.telecom.service.ArticleCategoryService;
import com.telecom.service.ArticleService;
import com.telecom.util.ImageUtil;

/**
 * 后台Action类 - 文章
 */

@ParentPackage("admin")
public class ArticleAction extends BaseAdminAction {

	private static final long serialVersionUID = -7731544442958843271L;
	
	private Article article;
	private File imageFile;
	private String imageFileContentType; // file关联变量
	private String imageFileFileName; // file关联变量

	@Resource(name = "articleServiceImpl")
	private ArticleService articleService;
	@Resource(name = "articleCategoryServiceImpl")
	private ArticleCategoryService articleCategoryService;
	
	// 列表
	public String list(){
		pager = articleService.findPager(pager);
		return LIST;
	}
	
	// 查看
	public String view(){
		article = articleService.load(id);
		return VIEW;
	}
	
	// 添加
	public String add(){
		return INPUT;
	}
	
	// 编辑
	public String edit(){
		article = articleService.load(id);
		return INPUT;
	}
	
	// 删除
	public String delete(){
		StringBuffer logInfoStringBuffer = new StringBuffer("删除文章: ");
		// 循环ID删除
		for (String id : ids) {
			Article article = articleService.load(id);
			articleService.delete(article);
			logInfoStringBuffer.append(article.getTitle() + " ");
		}
		
		// 保存日志
		logInfo = logInfoStringBuffer.toString();

		return ajax(Status.success, "删除成功!");
	}
	
	// 保存
	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "article.title", message = "标题不允许为空!")})
	@InputConfig(resultName = "error")
	public String save(){
		if(article.getIsRecommend() != null){
			article.setIsRecommend(true);
		}else{
			article.setIsRecommend(false);
		}
		if(article.getIsTop() != null){
			article.setIsTop(true);
		}else{
			article.setIsTop(false);
		}
		if(imageFile != null){
			String imageUrl = ImageUtil.copyImageFile(getServletContext(), imageFile);
			article.setImageUrl(imageUrl);
		}
		
		// 若分类没选择，则设置为空
		if(!StringUtils.isNotEmpty(article.getArticleCategory().getId())){
			article.setArticleCategory(null);
		}
		
		articleService.save(article);
		logInfo = "添加文章: " + article.getTitle();

		redirectUrl = "article!list.action";
		return SUCCESS;
	}
	
	// 修改
	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "article.title", message = "标题不允许为空!")})
	@InputConfig(resultName = "error")
	public String update(){
		Article persistent = articleService.load(id);
		if(article.getIsRecommend() != null){
			article.setIsRecommend(true);
		}else{
			article.setIsRecommend(false);
		}
		if(article.getIsTop() != null){
			article.setIsTop(true);
		}else{
			article.setIsTop(false);
		}
		if(imageFile != null){
			String imageUrl = ImageUtil.copyImageFile(getServletContext(), imageFile);
			article.setImageUrl(imageUrl);
		}else{
			article.setImageUrl(persistent.getImageUrl());
		}
		// 若分类没选择，则设置为空
		if(!StringUtils.isNotEmpty(article.getArticleCategory().getId())){
			article.setArticleCategory(null);
		}
		BeanUtils.copyProperties(article, persistent,new String[] { "id", "createDate", "modifyDate", "hits", "htmlPath", "wapHtmlPath", "pageCount", "isPublication" });
		articleService.update(persistent);
		logInfo = "修改文章: " + persistent.getTitle();

		redirectUrl = "article!list.action";
		return SUCCESS;
	}
	
	// 获取文章分类树集合
	public List<ArticleCategory> getArticleCategoryTreeList(){
		return articleCategoryService.getArticleCategoryTreeList();
	}
	
	// 获取文章类别集合
	public List<String> getArticleTypeAllList(){
		List<String> articleTypeList = new ArrayList<String>();
		ArticleType[] articleTypes = ArticleType.values();
		for (ArticleType articleType : articleTypes) {
			articleTypeList.add(articleType.toString());
		}
		return articleTypeList;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public File getImageFile() {
		return imageFile;
	}

	public void setImageFile(File imageFile) {
		this.imageFile = imageFile;
	}

	public String getImageFileContentType() {
		return imageFileContentType;
	}

	public void setImageFileContentType(String imageFileContentType) {
		this.imageFileContentType = imageFileContentType;
	}

	public String getImageFileFileName() {
		return imageFileFileName;
	}

	public void setImageFileFileName(String imageFileFileName) {
		this.imageFileFileName = imageFileFileName;
	}
	
}
