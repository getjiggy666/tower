package com.telecom.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.impl.CriteriaImpl.OrderEntry;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.transform.ResultTransformer;
import org.springframework.util.Assert;

import com.telecom.bean.Pager;
import com.telecom.dao.BaseDao;
import com.telecom.entity.BaseEntity;
import com.telecom.util.DateOperateUtil;
import com.telecom.util.ReflectionUtil;

/**
 * Dao实现类 - 基类
 */

public class BaseDaoImpl<T, PK extends Serializable> implements BaseDao<T, PK> {

	private static final String ORDER_LIST_PROPERTY_NAME = "orderList";// "排序"属性名称
	private static final String CREATE_DATE_PROPERTY_NAME = "createDate";// "创建日期"属性名称
	
	private Class<T> entityClass;
	protected SessionFactory sessionFactory;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
        Class c = getClass();
        Type type = c.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
            this.entityClass = (Class<T>) parameterizedType[0];
        }
	}

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public T get(PK id) {
		Assert.notNull(id, "id is required");
		return (T) getSession().get(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public T load(PK id) {
		Assert.notNull(id, "id is required");
		return (T) getSession().load(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> getAllList() {
		ClassMetadata classMetadata = sessionFactory.getClassMetadata(entityClass);
		String hql;
		if (ArrayUtils.contains(classMetadata.getPropertyNames(), ORDER_LIST_PROPERTY_NAME)) {
			hql = "from " + entityClass.getName() + " as entity order by entity." + ORDER_LIST_PROPERTY_NAME + " desc";
		} else {
			hql = "from " + entityClass.getName();
		}
		return getSession().createQuery(hql).list();
	}
	
	public Long getTotalCount() {
		String hql = "select count(*) from " + entityClass.getName();
		return (Long) getSession().createQuery(hql).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public PK save(T entity) {
		Assert.notNull(entity, "entity is required");
		if (entity instanceof BaseEntity) {
			try {
				Method method = entity.getClass().getMethod(BaseEntity.ON_SAVE_METHOD_NAME);
				method.invoke(entity);
				return (PK) getSession().save(entity);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return (PK) getSession().save(entity);
		}
	}

	public void update(T entity) {
		Assert.notNull(entity, "entity is required");
		if (entity instanceof BaseEntity) {
			try {
				Method method = entity.getClass().getMethod(BaseEntity.ON_UPDATE_METHOD_NAME);
				method.invoke(entity);
				getSession().update(entity);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			getSession().update(entity);
		}
	}

	public void delete(T entity) {
		Assert.notNull(entity, "entity is required");
		getSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public void delete(PK id) {
		Assert.notNull(id, "id is required");
		T entity = (T) getSession().load(entityClass, id);
		getSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public void delete(PK[] ids) {
		Assert.notEmpty(ids, "ids must not be empty");
		for (PK id : ids) {
			T entity = (T) getSession().load(entityClass, id);
			getSession().delete(entity);
		}
	}

	public void flush() {
		getSession().flush();
	}

	public void evict(Object object) {
		Assert.notNull(object, "object is required");
		getSession().evict(object);
	}

	public void clear() {
		getSession().clear();
	}
	
	public Pager findPager(Pager pager) {
		Criteria criteria = getSession().createCriteria(entityClass);
		return findPager(pager, criteria);
	}
	
	public Pager findPager(Pager pager, Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion criterion : criterions) {
			criteria.add(criterion);
		}
		return findPager(pager, criteria);
	}
	
	public Pager findPager(Pager pager, Order... orders) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Order order : orders) {
			criteria.addOrder(order);
		}
		return findPager(pager, criteria);
	}
	
	public Pager findPager(Pager pager, Criteria criteria) {
		Assert.notNull(pager, "pager is required");
		Assert.notNull(criteria, "criteria is required");
		
		Integer pageNumber = pager.getPageNumber();
		Integer pageSize = pager.getPageSize();
		String searchBy = pager.getSearchBy();
		String keyword = pager.getKeyword();
		Date beginDate = pager.getBeginDate();
		Date endDate = pager.getEndDate();
		String timeBy = pager.getTimeBy();
		String orderBy = pager.getOrderBy();
		Pager.Order order = pager.getOrder();
		
		// 获取备用条件与条件字段值
		String searchByExtra_1 = pager.getSearchByExtra_1();
		String searchByExtra_2 = pager.getSearchByExtra_2();
		String searchByExtra_3 = pager.getSearchByExtra_3();
		String searchByExtra_4 = pager.getSearchByExtra_4();
		String searchByExtra_5 = pager.getSearchByExtra_5();
		String valueExtra_1 = pager.getValueExtra_1();
		String valueExtra_2 = pager.getValueExtra_2();
		String valueExtra_3 = pager.getValueExtra_3();
		String valueExtra_4 = pager.getValueExtra_4();
		String valueExtra_5 = pager.getValueExtra_5();
		
		// 获取classMetadata对象
		ClassMetadata classMetadata = sessionFactory.getClassMetadata(entityClass);
		if (StringUtils.isNotEmpty(searchBy) && StringUtils.isNotEmpty(keyword)) {
			if (searchBy.contains(".")) {
				String alias = StringUtils.substringBefore(searchBy, ".");
				criteria.createAlias(alias, alias);
			}  
			criteria.add(Restrictions.like(searchBy, "%" + keyword + "%"));
		}
		
		if(beginDate != null && StringUtils.isNotEmpty(timeBy)){
			if (timeBy.contains(".")) {
				String alias = StringUtils.substringBefore(timeBy, ".");
				criteria.createAlias(alias, alias);
			}
			criteria.add(Restrictions.gt(timeBy, beginDate));
		}
		if(endDate != null && StringUtils.isNotEmpty(timeBy)){
			if (timeBy.contains(".")) {
				String alias = StringUtils.substringBefore(timeBy, ".");
				criteria.createAlias(alias, alias);
			}
			criteria.add(Restrictions.lt(timeBy, endDate));
		}
		
		// 加入备用查询条件
		criteria = addCriteriaByColumnAndValue(searchByExtra_1, valueExtra_1, criteria, classMetadata);
		criteria = addCriteriaByColumnAndValue(searchByExtra_2, valueExtra_2, criteria, classMetadata);
		criteria = addCriteriaByColumnAndValue(searchByExtra_3, valueExtra_3, criteria, classMetadata);
		criteria = addCriteriaByColumnAndValue(searchByExtra_4, valueExtra_4, criteria, classMetadata);
		criteria = addCriteriaByColumnAndValue(searchByExtra_5, valueExtra_5, criteria, classMetadata);
		
		pager.setTotalCount(criteriaResultTotalCount(criteria));
		
		if (StringUtils.isNotEmpty(orderBy) && order != null) {
			if (order == Pager.Order.asc) {
				criteria.addOrder(Order.asc(orderBy));
			} else {
				criteria.addOrder(Order.desc(orderBy));
			}
		}
		
		if (!StringUtils.equals(orderBy, ORDER_LIST_PROPERTY_NAME) && ArrayUtils.contains(classMetadata.getPropertyNames(), ORDER_LIST_PROPERTY_NAME)) {
			criteria.addOrder(Order.asc(ORDER_LIST_PROPERTY_NAME));
			criteria.addOrder(Order.desc(CREATE_DATE_PROPERTY_NAME));
			if (StringUtils.isEmpty(orderBy) || order == null) {
				pager.setOrderBy(ORDER_LIST_PROPERTY_NAME);
				pager.setOrder(Pager.Order.asc);
			}
		} else if (!StringUtils.equals(orderBy, CREATE_DATE_PROPERTY_NAME) && ArrayUtils.contains(classMetadata.getPropertyNames(), CREATE_DATE_PROPERTY_NAME)) {
			criteria.addOrder(Order.desc(CREATE_DATE_PROPERTY_NAME));
			if (StringUtils.isEmpty(orderBy) || order == null) {
				pager.setOrderBy(CREATE_DATE_PROPERTY_NAME);
				pager.setOrder(Pager.Order.desc);
			}
		}
		
		criteria.setFirstResult((pageNumber - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		pager.setResult(criteria.list());
		return pager;
	}
	
	// 获取Criteria查询数量
	@SuppressWarnings("unchecked")
	public int criteriaResultTotalCount(Criteria criteria) {
		Assert.notNull(criteria, "criteria is required");
		
		int criteriaResultTotalCount = 0;
		try {
			CriteriaImpl criteriaImpl = (CriteriaImpl) criteria;
			
			Projection projection = criteriaImpl.getProjection();
			ResultTransformer resultTransformer = criteriaImpl.getResultTransformer();
			List<OrderEntry> orderEntries = (List) ReflectionUtil.getFieldValue(criteriaImpl, "orderEntries");
			ReflectionUtil.setFieldValue(criteriaImpl, "orderEntries", new ArrayList());
			
			Integer totalCount = ((Long) criteriaImpl.setProjection(Projections.rowCount()).uniqueResult()).intValue();
			if (totalCount != null) {
				criteriaResultTotalCount = totalCount;
			}
			
			criteriaImpl.setProjection(projection);
			if (projection == null) {
				criteriaImpl.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
			}
			if (resultTransformer != null) {
				criteriaImpl.setResultTransformer(resultTransformer);
			}
			ReflectionUtil.setFieldValue(criteriaImpl, "orderEntries", orderEntries);
		} catch (Exception e) {
			
		}
		return criteriaResultTotalCount;
	}
	
	// 根据字段名与参数值增加查询条件
		private Criteria addCriteriaByColumnAndValue(String column, String value,
				Criteria criteria, ClassMetadata classMetadata){
			if (StringUtils.isNotEmpty(column) && StringUtils.isNotEmpty(value)) {
				Class columnClass = null;
				if (column.contains(".")) {
					String alias = StringUtils.substringBefore(column, ".");
					String aliasColumn = StringUtils.substringAfter(column, ".");
					criteria.createAlias(alias, alias);
					Class aliasClass = classMetadata.getPropertyType(alias).getReturnedClass();
					ClassMetadata aliasClassMetadata = sessionFactory.getClassMetadata(aliasClass);
					columnClass = aliasClassMetadata.getPropertyType(aliasColumn).getReturnedClass();
				}
				else{
					// 根据column判断字段对应的属性类型
					columnClass = classMetadata.getPropertyType(column).getReturnedClass();
				}
				// 根据属性的类型不同，加入不同条件
				if(columnClass.equals(String.class)){
					criteria.add(Restrictions.like(column, "%" + value + "%"));
				}
				else if(columnClass.equals(Date.class)){
					criteria.add(Restrictions.eq(column, DateOperateUtil.stringToDateWithFormat(value, "yyyy-MM-dd HH:mm:ss")));
				}
				else if(columnClass.equals(Integer.class)){
					criteria.add(Restrictions.eq(column, new Integer(value)));
				}
				else if(columnClass.equals(Float.class)){
					criteria.add(Restrictions.eq(column, new Float(value)));			
				}
				else if(columnClass.equals(Double.class)){
					criteria.add(Restrictions.eq(column, new Double(value)));
				}
				else if(columnClass.equals(BigDecimal.class)){
					criteria.add(Restrictions.eq(column, new BigDecimal(value)));
				}
				else if(columnClass.equals(Boolean.class)){
					criteria.add(Restrictions.eq(column, new Boolean(value)));
				}
				else if(columnClass.isEnum()){
					criteria.add(Restrictions.eq(column, Enum.valueOf(columnClass, value)));
				}
			}
			return criteria;
		}
	
	public List<Object[]> getDataBySql(String sql) {
        List<Object[]> list = getSession().createSQLQuery(sql).list();
        return list;
	}
	
	public List<Object[]> getDataByHql(String hql){
		List<Object[]> list = getSession().createQuery(hql).list();
        return list;
	}

}