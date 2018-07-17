package com.telecom.action.admin;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.BeanUtils;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.telecom.entity.Article;
import com.telecom.entity.ArticleCategory;
import com.telecom.service.ArticleCategoryService;

/**
 * 后台Action类 - 文章分类
 */

@ParentPackage("admin")
public class ArticleCategoryAction extends BaseAdminAction {

	private static final long serialVersionUID = -7731545555958843271L;

	private ArticleCategory articleCategory;
	private List<ArticleCategory> articleCategoryList;
	private String parentId;

	@Resource(name = "articleCategoryServiceImpl")
	private ArticleCategoryService articleCategoryService;

	// 是否已存在标识 ajax验证
	public String checkSign() {
		String oldSign = getParameter("oldValue");
		String newSign = articleCategory.getSign();
		if (articleCategoryService.isUniqueBySign(oldSign, newSign)) {
			return ajax("true");
		} else {
			return ajax("false");
		}
	}

	// 添加
	public String add() {
		return INPUT;
	}

	// 编辑
	public String edit() {
		articleCategory = articleCategoryService.load(id);
		return INPUT;
	}

	// 列表
	public String list() {
		articleCategoryList = articleCategoryService.getAllList();
		return LIST;
	}

	// 删除
	public String delete() {
		articleCategory = articleCategoryService.load(id);
		Set<ArticleCategory> children = articleCategory.getChildren();
		if(children != null && children.size() > 0){
			addActionError("此文章分类存在下级分类,删除失败!");
			return ERROR;
		}
		Set<Article> articleSet = articleCategory.getArticleSet();
		if(articleSet != null && articleSet.size() > 0){
			addActionError("此文章分类存在文章,删除失败!");
			return ERROR;
		}
		articleCategoryService.delete(id);
		logInfo = "删除文章分类: " + articleCategory.getName();

		redirectUrl = "article_category!list.action";
		return SUCCESS;
	}

	// 保存
	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "articleCategory.name", message = "分类名称不允许为空!"),
			@RequiredStringValidator(fieldName = "articleCategory.sign", message = "标识不允许为空!") }, intRangeFields = { @IntRangeFieldValidator(fieldName = "articleCategory.orderList", min = "0", message = "排序必须为零或正整数!") }, regexFields = { @RegexFieldValidator(fieldName = "articleCategory.sign", regexExpression = "^\\w+$", message = "标识只允许包含英文、数字和下划线!") })
	@InputConfig(resultName = "error")
	public String save() {
		// 判断标识是否存在
		if (articleCategoryService.isExistBySign(articleCategory.getSign())) {
			addActionError("标识已存在!");
			return ERROR;
		}
		// 上级目录不为顶级时
		if (parentId != null && !parentId.equals("")) {
			ArticleCategory parent = articleCategoryService.load(parentId);
			articleCategory.setParent(parent);
		}
		// 排序为空时自动填入0
		if (articleCategory.getOrderList() == null) {
			articleCategory.setOrderList(0);
		}
		// 级别设置
		if (articleCategory.getGrade() == null) {
			if (parentId != null && !parentId.equals("")) {
				articleCategory
						.setGrade(articleCategory.getParent().getGrade() + 1);
			} else {
				articleCategory.setGrade(0);
			}
		}
		// 路径设置
		if (articleCategory.getPath() == null) {
			if (parentId != null && !parentId.equals("")) {
				if (articleCategory.getParent().getPath().equals("0")) {
					articleCategory
							.setPath(articleCategory.getParent().getId());
				} else {
					String path = articleCategory.getParent().getPath() + ","
							+ articleCategory.getParent().getId();
					articleCategory.setPath(path);
				}
			} else {
				articleCategory.setPath("0");
			}
		}

		articleCategoryService.save(articleCategory);
		logInfo = "添加文章分类: " + articleCategory.getName();

		redirectUrl = "article_category!list.action";
		return SUCCESS;
	}

	// 修改
	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "articleCategory.name", message = "分类名称不允许为空!"),
			@RequiredStringValidator(fieldName = "articleCategory.sign", message = "标识不允许为空!") }, intRangeFields = { @IntRangeFieldValidator(fieldName = "articleCategory.orderList", min = "0", message = "排序必须为零或正整数!") }, regexFields = { @RegexFieldValidator(fieldName = "articleCategory.sign", regexExpression = "^\\w+$", message = "标识只允许包含英文、数字和下划线!") })
	@InputConfig(resultName = "error")
	public String update() {
		ArticleCategory persistent = articleCategoryService.load(id);
		// 判断标识是否存在
		if(!persistent.getSign().equals(articleCategory.getSign())){
			if (articleCategoryService.isExistBySign(articleCategory.getSign())) {
				addActionError("标识已存在!");
				return ERROR;
			}
		}
		// 排序为空时自动填入0
		if (articleCategory.getOrderList() == null) {
			articleCategory.setOrderList(0);
		}

		BeanUtils.copyProperties(articleCategory, persistent,new String[] { "id", "createDate", "modifyDate", "grade", "path", "parent", "children" });
		articleCategoryService.update(persistent);
		logInfo = "修改文章分类: " + articleCategory.getName();

		redirectUrl = "article_category!list.action";
		return SUCCESS;
	}

	// 文章分类树节点
	public List<ArticleCategory> getArticleCategoryTreeList() {
		return articleCategoryService.getArticleCategoryTreeList();
	}

	public ArticleCategory getArticleCategory() {
		return articleCategory;
	}

	public void setArticleCategory(ArticleCategory articleCategory) {
		this.articleCategory = articleCategory;
	}

	public List<ArticleCategory> getArticleCategoryList() {
		return articleCategoryList;
	}

	public void setArticleCategoryList(List<ArticleCategory> articleCategoryList) {
		this.articleCategoryList = articleCategoryList;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

}
