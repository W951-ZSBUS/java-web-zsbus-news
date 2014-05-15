package com.w951.zsbus.news.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.w951.util.service.CommonService;
import com.w951.zsbus.news.dto.CategoryDTO;
import com.w951.zsbus.news.entity.Category;

/**
 * 
 * 系统版本：v1.0<br>
 * 开发人员：Lusifer<br>
 * 日期：2014-05-14<br>
 * 时间：20:37:00<br>
 * 功能描述：写明作用，调用方式，使用场景，以及特殊情况<br>
 *
 */
@Transactional
public interface CategoryService extends CommonService<Category> {
	/**
	 * 查询分类树
	 * @param categoryPid 父类ID
	 * @return
	 */
	public List<CategoryDTO> queryTreeByPid(String pid);
}