package com.w951.zsbus.news.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.w951.orm.hibernate.HibernateDao;
import com.w951.util.action.CommonBaseAction;
import com.w951.util.bean.BeanUtil;
import com.w951.zsbus.news.dto.CategoryDTO;
import com.w951.zsbus.news.entity.Category;
import com.w951.zsbus.news.service.CategoryService;

public class CategoryAPI extends CommonBaseAction {
	private static final long serialVersionUID = 8147956794620539430L;
	private JSONObject result;
	private JSONArray resultArray;
	private Map<String, Object> request;
	private Map<String, Object> session;
	
	private static final String QUERY_CATEGORY_BY_SHOW = "FROM Category t WHERE t.categoryShow = true ORDER BY t.categorySort";

	@Resource
	private HibernateDao hibernateDao;
	@Resource
	private CategoryService categoryService;

	private int page;
	private int rows;
	private String id;
	private String jsoncallback;

	// Action

	public String query() throws Exception {
		List<CategoryDTO> dtos = categoryService.queryTreeByPid("0");
		resultArray = JSONArray.fromObject(dtos.get(0).getChildren());

		// 返回跨域请求的数据

		if (jsoncallback != null) {
			jsoncallback(resultArray);
			return null;
		}

		return "array";
	}

	public String queryById() throws Exception {
		Category obj = hibernateDao.get(new Category(), id);
		CategoryDTO dto = new CategoryDTO();
		BeanUtil.beanToBean(dto, obj);

		resultArray = JSONArray.fromObject(dto);

		// 返回跨域请求的数据

		if (jsoncallback != null) {
			jsoncallback(resultArray);
			return null;
		}

		return "array";
	}
	
	public String queryByShow() throws Exception {
		List<Category> list = hibernateDao.queryListByHql(QUERY_CATEGORY_BY_SHOW, null);
		
		CategoryDTO dto = null;
		List<CategoryDTO> dtos = new ArrayList<CategoryDTO>();
		if (list != null && list.size() > 0) {
			for (Category obj : list) {
				dto = new CategoryDTO();
				BeanUtil.beanToBean(dto, obj);
				dtos.add(dto);
			}
		}
		
		resultArray = JSONArray.fromObject(dtos);
		
		// 返回跨域请求的数据

		if (jsoncallback != null) {
			jsoncallback(resultArray);
			return null;
		}
		
		return "array";
	}

	public String queryByProperty() throws Exception {
		return SUCCESS;
	}

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

	public JSONArray getResultArray() {
		return resultArray;
	}

	public void setResultArray(JSONArray resultArray) {
		this.resultArray = resultArray;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJsoncallback() {
		return jsoncallback;
	}

	public void setJsoncallback(String jsoncallback) {
		this.jsoncallback = jsoncallback;
	}
}
