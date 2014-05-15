package com.w951.zsbus.news.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.w951.orm.hibernate.HibernateDao;
import com.w951.util.bean.BeanUtil;
import com.w951.zsbus.news.dto.CategoryDTO;
import com.w951.zsbus.news.entity.Category;
import com.w951.zsbus.news.service.CategoryService;

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
public class CategoryServiceImpl implements CategoryService {
	@Resource
	private HibernateDao hibernateDao;

	private static final String QUERY_CATEGORY_BY_PID = "FROM Category t WHERE t.categoryPid = :categoryPid ORDER BY t.categorySort";

	@Transactional(propagation = Propagation.REQUIRED)
	public String delete(Category entity) {
		entity = get(entity.getCategoryId());
		if (entity.getCategoryPid().equals("0")) {
			return "禁止操作系统目录";
		} else {
			// 递归删除所属目录
			List<Category> list = hibernateDao.queryListByHql(QUERY_CATEGORY_BY_PID, new String[][] { new String[] { "categoryPid", entity.getCategoryId() } });
			for (Category category : list) {
				deleteTree(category);
				hibernateDao.delete(category);
			}
			hibernateDao.delete(entity);

			return null;
		}
	}

	public Category get(String id) {
		return hibernateDao.get(new Category(), id);
	}

	public long getCount() {
		return hibernateDao.getCount(new Category());
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public String insert(Category entity) {
		hibernateDao.insert(entity);
		return null;
	}

	public List<Category> queryList(String... order) {
		if (order != null) {
			return hibernateDao.queryList(new Category(), order);
		} else {
			return hibernateDao.queryList(new Category());
		}
	}

	public List<Category> queryPageList(int pageIndex, int pageSize, String... order) {
		if (order != null) {
			return hibernateDao.queryPageList(new Category(), pageIndex, pageSize, order);
		} else {
			return hibernateDao.queryPageList(new Category(), pageIndex, pageSize);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public String update(Category entity) {
		if (entity.getCategoryPid().equals("0")) {
			return "禁止操作系统目录";
		} else {
			Category category = get(entity.getCategoryId());
			BeanUtil.beanToBean(entity, category);
			hibernateDao.update(entity);
			return null;
		}
	}

	/*----------自定义接口----------*/

	@Override
	public List<CategoryDTO> queryTreeByPid(String pid) {
		List<CategoryDTO> dtos = new ArrayList<CategoryDTO>();
		List<Category> list = hibernateDao.queryListByHql(QUERY_CATEGORY_BY_PID, new String[][] { new String[] { "categoryPid", pid } });

		for (Category category : list) {
			CategoryDTO dto = new CategoryDTO();
			BeanUtil.beanToBean(dto, category);
			dtos.add(dto);

			// 使用递归创建整棵树

			createTree(dto);
		}

		return dtos;
	}

	/**
	 * 递归查询
	 * 
	 * @param dto
	 */
	private void createTree(CategoryDTO dto) {
		List<Category> subList = hibernateDao.queryListByHql(QUERY_CATEGORY_BY_PID, new String[][] { new String[] { "categoryPid", dto.getCategoryId() } });
		if (subList != null && subList.size() > 0) {
			List<CategoryDTO> subDtos = new ArrayList<CategoryDTO>();
			// 递归中的循环是不会因为重新调用自身而不再循环，会一层一层往上继续循环
			for (Category subCategory : subList) {
				CategoryDTO subDto = new CategoryDTO();
				BeanUtil.beanToBean(subDto, subCategory);
				subDtos.add(subDto);

				createTree(subDto);
			}
			dto.setChildren(subDtos);
		}
	}

	/**
	 * 递归删除
	 * 
	 * @param category
	 */
	private void deleteTree(Category category) {
		List<Category> subList = hibernateDao.queryListByHql(QUERY_CATEGORY_BY_PID, new String[][] { new String[] { "categoryPid", category.getCategoryId() } });
		if (subList != null && subList.size() > 0) {
			for (Category subCategory : subList) {
				deleteTree(subCategory);
				hibernateDao.delete(subCategory);
			}
		}
	}

}