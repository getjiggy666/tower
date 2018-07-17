package com.telecom.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.search.annotations.Indexed;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import com.telecom.bean.PageTemplateConfig;
import com.telecom.util.TemplateConfigUtil;

/**
 * 实体类 - 文章
 */

@Indexed
@Entity
public class Article extends BaseEntity {

	private static final long serialVersionUID = -6556041338276141734L;
	
	// 文章类别(活动)
	public enum ArticleType{
		article, activity
	}
	
	public static final String ARTICLE_HITS_CACHE_KEY_PREFIX = "ARTICLE_HITS";// 文章点击数缓存Key前缀
	public static final int ARTICLE_HITS_CACHE_TIME = 600;// 文章点击数缓存有效期
	public static final int DEFAULT_ARTICLE_LIST_PAGE_SIZE = 20;// 文章列表默认每页显示数
	private static final int PAGE_CONTENT_LENGTH = 1000;// 内容分页长度
	public static final String NEXT_PAGE_SIGN = "{nextPage}";// 内容分页标记
	
	private String author; // 作者
	private String content; // 内容
	private String wapContent;// wap内容
	private Integer hits; // 点击量
	private String htmlPath; // HTML静态文件路径（首页）
	private String wapHtmlPath;// wapHTML静态文件路径(首页)
	private String articleUrl;// 外链
	private String articleWapUrl;// wap外链
	private Boolean isPublication; // 是否发布
	private Boolean isRecommend; // 是否为推荐文章
	private Boolean isTop; // 是否置顶
	private String metaDescription; // 页面描述
	private String metaKeywords; // 页面关键字
	private Integer pageCount; // 文章页数
	private String imageUrl;// 图片
	private String title; // 标题
	private ArticleType articleType;// 文章类别
	private ArticleCategory articleCategory; // 外键关联对象---文章分类对象

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(nullable = true)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWapContent() {
		return wapContent;
	}

	public void setWapContent(String wapContent) {
		this.wapContent = wapContent;
	}

	@Column(nullable = false)
	public Integer getHits() {
		return hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}

	@Column(nullable = true)
	public String getHtmlPath() {
		return htmlPath;
	}
	
	public void setHtmlPath(String htmlPath) {
		this.htmlPath = htmlPath;
	}

	@Column(nullable = true)
	public String getWapHtmlPath() {
		return wapHtmlPath;
	}

	public void setWapHtmlPath(String wapHtmlPath) {
		this.wapHtmlPath = wapHtmlPath;
	}

	@Column(nullable = true)
	public String getArticleUrl() {
		return articleUrl;
	}

	public void setArticleUrl(String articleUrl) {
		this.articleUrl = articleUrl;
	}

	@Column(nullable = true)
	public String getArticleWapUrl() {
		return articleWapUrl;
	}

	public void setArticleWapUrl(String articleWapUrl) {
		this.articleWapUrl = articleWapUrl;
	}

	@Column(nullable = false)
	public Boolean getIsPublication() {
		return isPublication;
	}

	public void setIsPublication(Boolean isPublication) {
		this.isPublication = isPublication;
	}

	@Column(nullable = false)
	public Boolean getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Boolean isRecommend) {
		this.isRecommend = isRecommend;
	}

	@Column(nullable = false)
	public Boolean getIsTop() {
		return isTop;
	}

	public void setIsTop(Boolean isTop) {
		this.isTop = isTop;
	}

	public String getMetaDescription() {
		return metaDescription;
	}

	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}

	public String getMetaKeywords() {
		return metaKeywords;
	}

	public void setMetaKeywords(String metaKeywords) {
		this.metaKeywords = metaKeywords;
	}

	@Column(nullable = false)
	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Column(nullable = false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(nullable = false)
	public ArticleType getArticleType() {
		return articleType;
	}

	public void setArticleType(ArticleType articleType) {
		this.articleType = articleType;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = true)
	@ForeignKey(name = "fk_article_article_category")
	public ArticleCategory getArticleCategory() {
		return articleCategory;
	}

	public void setArticleCategory(ArticleCategory articleCategory) {
		this.articleCategory = articleCategory;
	}

	// 保存处理
	@Override
	@Transient
	public void onSave() {
		if(isPublication == null){
			isPublication = true;
		}
		if(isRecommend == null){
			isRecommend = false;
		}
		if(isTop == null){
			isTop = false;
		}
		if(pageCount == null){
			pageCount = 1;
		}
		if(hits == null){
			hits = 0;
		}
		if(StringUtils.isEmpty(articleUrl)){
			articleUrl = null;
		}
		if(StringUtils.isEmpty(articleWapUrl)){
			articleWapUrl = null;
		}
		PageTemplateConfig pageTemplateConfig = TemplateConfigUtil.getPageTemplateConfig(PageTemplateConfig.ARTICLE_CONTENT);
		PageTemplateConfig pageTemplateWapConfig = TemplateConfigUtil.getWapPageTemplateConfig(PageTemplateConfig.ARTICLE_WAP_CONTENT);
		htmlPath = pageTemplateConfig.getHtmlRealPath();
		wapHtmlPath = pageTemplateWapConfig.getWapHtmlRealPath();
		pageCount = getPageContentList().size();
	}
	
	// 更新处理
	@Override
	@Transient
	public void onUpdate() {
		if(isPublication == null){
			isPublication = true;
		}
		if(isRecommend == null){
			isRecommend = false;
		}
		if(isTop == null){
			isTop = false;
		}
		if(pageCount == null){
			pageCount = 1;
		}
		if(hits == null){
			hits = 0;
		}
		if(StringUtils.isEmpty(articleUrl)){
			articleUrl = null;
		}
		if(StringUtils.isEmpty(articleWapUrl)){
			articleWapUrl = null;
		}
	}
	
	/**
	 * 获取文本内容
	 */
	@Transient
	public String getContentText() {
		Document document = Jsoup.parse(content);
		return document.text();
	}

	/**
	 * 获取分页内容
	 */
	@Transient
	public List<String> getPageContentList() {
		if (content.contains(NEXT_PAGE_SIGN)) {
			return Arrays.asList(content.split(NEXT_PAGE_SIGN));
		} else {
			List<String> pageContentList = new ArrayList<String>();
			Document document = Jsoup.parse(content);
			List<Node> children = document.body().childNodes();
			if (children != null) {
				int textLength = 0;
				StringBuffer html = new StringBuffer();
				for (Node node : children) {
					if (node instanceof Element) {
						Element element = (Element) node;
						html.append(element.outerHtml());
						textLength += element.text().length();
						if (textLength >= PAGE_CONTENT_LENGTH) {
							pageContentList.add(html.toString());
							textLength = 0;
							html.setLength(0);
						}
					} else if (node instanceof TextNode) {
						TextNode textNode = (TextNode) node;
						String text = textNode.text();
						Pattern pattern = Pattern.compile("[,;\\.!?，；。！？]");
						String[] contents = pattern.split(text);
						Matcher matcher = pattern.matcher(text);
						for (String content : contents) {
							if (matcher.find()) {
								content += matcher.group();
							}
							html.append(content);
							textLength += content.length();
							if (textLength >= PAGE_CONTENT_LENGTH) {
								pageContentList.add(html.toString());
								textLength = 0;
								html.setLength(0);
							}
						}
					}
				}
				String pageContent = html.toString();
				if (StringUtils.isNotEmpty(pageContent)) {
					pageContentList.add(pageContent);
				}
			}
			return pageContentList;
		}
	}
	
	/**
	 * 获取分页内容
	 */
	@Transient
	public List<String> getWapPageContentList() {
		if (wapContent.contains(NEXT_PAGE_SIGN)) {
			return Arrays.asList(wapContent.split(NEXT_PAGE_SIGN));
		} else {
			List<String> pageContentList = new ArrayList<String>();
			Document document = Jsoup.parse(wapContent);
			List<Node> children = document.body().childNodes();
			if (children != null) {
				int textLength = 0;
				StringBuffer html = new StringBuffer();
				for (Node node : children) {
					if (node instanceof Element) {
						Element element = (Element) node;
						html.append(element.outerHtml());
						textLength += element.text().length();
						if (textLength >= PAGE_CONTENT_LENGTH) {
							pageContentList.add(html.toString());
							textLength = 0;
							html.setLength(0);
						}
					} else if (node instanceof TextNode) {
						TextNode textNode = (TextNode) node;
						String text = textNode.text();
						Pattern pattern = Pattern.compile("[,;\\.!?，；。！？]");
						String[] contents = pattern.split(text);
						Matcher matcher = pattern.matcher(text);
						for (String content : contents) {
							if (matcher.find()) {
								content += matcher.group();
							}
							html.append(content);
							textLength += content.length();
							if (textLength >= PAGE_CONTENT_LENGTH) {
								pageContentList.add(html.toString());
								textLength = 0;
								html.setLength(0);
							}
						}
					}
				}
				String pageContent = html.toString();
				if (StringUtils.isNotEmpty(pageContent)) {
					pageContentList.add(pageContent);
				}
			}
			return pageContentList;
		}
	}
}
