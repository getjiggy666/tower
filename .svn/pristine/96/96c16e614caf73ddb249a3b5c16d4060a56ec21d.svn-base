package com.telecom.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.ForeignKey;

/**
 * 实体类 - 文章分类
 */

@Entity
public class ArticleCategory extends BaseEntity {

	private static final long serialVersionUID = -7519486124522222526L;
	
	public static final String PATH_SEPARATOR = ",";// 树路径分隔符
	public static final String ARTICLE_LIST_URL_SUFFIX = "htm";// 文章列表URL后缀
	
	private Integer grade;// 级别
	private String metaDescription;// 描述
	private String metaKeywords;// 关键词
	private String name;// 名称
	private Integer orderList;// 排序
	private String path;// 路径
	private String sign;// 标识
	
	private ArticleCategory parent;// 父级分类
	
	private Set<ArticleCategory> children = new HashSet<ArticleCategory>();// 子分类
	private Set<Article> articleSet = new HashSet<Article>();// 文章

	
	@Column(nullable = false)
	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	@Column(nullable = true)
	public String getMetaDescription() {
		return metaDescription;
	}

	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}

	@Column(nullable = true)
	public String getMetaKeywords() {
		return metaKeywords;
	}

	public void setMetaKeywords(String metaKeywords) {
		this.metaKeywords = metaKeywords;
	}

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = true)
	public Integer getOrderList() {
		return orderList;
	}

	public void setOrderList(Integer orderList) {
		this.orderList = orderList;
	}

	@Column(nullable = false)
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(nullable = false)
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name = "fk_article_category_parent")
	public ArticleCategory getParent() {
		return parent;
	}

	public void setParent(ArticleCategory parent) {
		this.parent = parent;
	}

	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	public Set<ArticleCategory> getChildren() {
		return children;
	}

	public void setChildren(Set<ArticleCategory> children) {
		this.children = children;
	}
	
	@OneToMany(mappedBy = "articleCategory", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
	public Set<Article> getArticleSet() {
		return articleSet;
	}

	public void setArticleSet(Set<Article> articleSet) {
		this.articleSet = articleSet;
	}

	// 保存处理
	@Override
	@Transient
	public void onSave() {
	}
	
	// 更新处理
	@Override
	@Transient
	public void onUpdate() {
	}
}
