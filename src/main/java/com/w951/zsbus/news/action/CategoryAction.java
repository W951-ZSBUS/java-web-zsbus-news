package com.w951.zsbus.news.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.w951.util.action.CommonAction;
import com.w951.util.bean.BeanUtil;
import com.w951.util.date.DateUtil;
import com.w951.util.dto.User;
import com.w951.zsbus.news.dto.CategoryDTO;
import com.w951.zsbus.news.entity.Category;
import com.w951.zsbus.news.service.CategoryService;

public class CategoryAction extends CommonAction {
	private static final long serialVersionUID = -1L;
	private JSONObject result;
	private JSONArray resultArray;
	private Map<String, Object> request;
	private Map<String, Object> session;

	@Resource
	private CategoryService categoryService;

	// 参数

	private Category category;
	private int page;
	private int rows;

	// Action

	@Override
	public String insert() throws Exception {
		User admin = (User) session.get("admin");

		category.setCategoryCreatename(admin.getUserName());
		category.setCategoryCreatedate(DateUtil.getDateTime());
		String message = categoryService.insert(category);

		if (message != null) {
			jsonData.put("message", message);
		}

		result = JSONObject.fromObject(jsonData);

		return SUCCESS;
	}

	@Override
	public String delete() throws Exception {
		String message = categoryService.delete(category);

		if (message != null) {
			jsonData.put("message", message);
		}

		result = JSONObject.fromObject(jsonData);

		return SUCCESS;
	}

	@Override
	public String update() throws Exception {
		String message = categoryService.update(category);

		if (message != null) {
			jsonData.put("message", message);
		}

		result = JSONObject.fromObject(jsonData);

		return SUCCESS;
	}

	@Override
	public String query() throws Exception {
		List<Category> list = categoryService.queryPageList(page, rows);

		CategoryDTO dto = null;
		List<CategoryDTO> dtos = new ArrayList<CategoryDTO>();
		if (list != null && list.size() > 0) {
			for (Category obj : list) {
				dto = new CategoryDTO();
				BeanUtil.beanToBean(dto, obj);
				dtos.add(dto);
			}
		}

		jsonData.put("total", categoryService.getCount());
		jsonData.put("rows", dtos);
		result = JSONObject.fromObject(jsonData);

		return SUCCESS;
	}
	
	// Array
	
	public String queryTreeArray() throws Exception {
		List<CategoryDTO> dtos = categoryService.queryTreeByPid("0");
		resultArray = JSONArray.fromObject(dtos);
		return "array";
	}

	// getter setter

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

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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

	public JSONArray getResultArray() {
		return resultArray;
	}

	public void setResultArray(JSONArray resultArray) {
		this.resultArray = resultArray;
	}

}
