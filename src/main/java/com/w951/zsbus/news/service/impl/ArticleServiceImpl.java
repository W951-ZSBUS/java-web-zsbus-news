package com.w951.zsbus.news.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.w951.zsbus.news.entity.Article;
import com.w951.zsbus.news.service.ArticleService;
import com.w951.orm.hibernate.HibernateDao;
import com.w951.util.bean.BeanUtil;

/**
 * 
 * 系统版本：v1.0<br>
 * 开发人员：Lusifer<br>
 * 日期：2014-05-14<br>
 * 时间：20:37:00<br>
 * 功能描述：写明作用，调用方式，使用场景，以及特殊情况<br>
 *
 */
@Component
public class ArticleServiceImpl implements ArticleService {
	@Resource
	private HibernateDao hibernateDao;
	
	private static final String QUERY_ARTICLE_BY_CATEGORY = "FROM Article t WHERE t.category.categoryId = :categoryId ORDER BY t.articleCreatedate DESC";
	private static final String QUERY_ARTICLECOUNT_BY_CATEGORY = "SELECT COUNT(*) FROM Article t WHERE t.category.categoryId = :categoryId";

	@Transactional(propagation = Propagation.REQUIRED)
	public String delete(Article entity) {
		entity = get(entity.getArticleId());
		hibernateDao.delete(entity);
		return null;
	}

	public Article get(String id) {
		return hibernateDao.get(new Article(), id);
	}

	public long getCount() {
		return hibernateDao.getCount(new Article());
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public String insert(Article entity) {
		hibernateDao.insert(entity);
		return null;
	}

	public List<Article> queryList(String... order) {
		if (order != null) {
			return hibernateDao.queryList(new Article(), order);
		} else {
			return hibernateDao.queryList(new Article());
		}
	}

	public List<Article> queryPageList(int pageIndex, int pageSize,
			String... order) {
		if (order != null) {
			return hibernateDao.queryPageList(new Article(), pageIndex,
					pageSize, order);
		} else {
			return hibernateDao.queryPageList(new Article(), pageIndex,
					pageSize);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public String update(Article entity) {
		Article obj = get(entity.getArticleId());
		BeanUtil.beanToBean(entity, obj);
		hibernateDao.update(entity);
		return null;
	}
	
	/*----------自定义接口----------*/

	@Override
	public long getCountByCategoryId(String categoryId) {
		return hibernateDao.getCountByHql(QUERY_ARTICLECOUNT_BY_CATEGORY,
				new String[][] { new String[] { "categoryId", categoryId } });
	}

	@Override
	public List<Article> queryPageListByCategoryId(String categoryId, int pageIndex, int pageSize) {
		return hibernateDao.queryPageListByHql(QUERY_ARTICLE_BY_CATEGORY,
				pageIndex, pageSize, new String[][] { new String[] {
						"categoryId", categoryId } });
	}

}