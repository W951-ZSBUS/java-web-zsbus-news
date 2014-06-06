package com.w951.zsbus.news.api;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.w951.orm.hibernate.HibernateDao;
import com.w951.util.action.CommonBaseAction;
import com.w951.util.bean.BeanUtil;
import com.w951.zsbus.news.dto.ArticleDTO;
import com.w951.zsbus.news.entity.Article;
import com.w951.zsbus.news.service.ArticleService;

public class ArticleAPI extends CommonBaseAction {
	private static final long serialVersionUID = 540268166829121002L;
	private JSONObject result;
	private JSONArray resultArray;
	private Map<String, Object> request;
	private Map<String, Object> session;
	
	@Resource
	private HibernateDao hibernateDao;
	@Resource
	private ArticleService articleService;
	
	private int page;
	private int rows;
	private String articleId;
	private String categoryId;
	private String jsoncallback;
	
	// Action
	
	public String queryByCategory() throws Exception {
		List<Article> list = articleService.queryPageListByCategoryId(categoryId, page, rows);

		ArticleDTO dto = null;
		List<ArticleDTO> dtos = new ArrayList<ArticleDTO>();
		if (list != null && list.size() > 0) {
			for (Article obj : list) {
				dto = new ArticleDTO();
				BeanUtil.beanToBean(dto, obj);
				dto.setArticleContent("");
				dto.setCategoryId(obj.getCategory().getCategoryId());
				dto.setCategoryName(obj.getCategory().getCategoryName());
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
	
	public String queryCountByCategory() throws Exception {
		long count = articleService.getCountByCategoryId(categoryId);
		
		resultArray = JSONArray.fromObject(count);
		
		// 返回跨域请求的数据
		if (jsoncallback != null) {
			jsoncallback(resultArray);
			return null;
		}
		
		return "array";
	}
	
	public String queryById() throws Exception {
		Article obj = articleService.get(articleId);
		ArticleDTO dto = new ArticleDTO();
		BeanUtil.beanToBean(dto, obj);
		dto.setArticleContent(URLEncoder.encode(obj.getArticleContent(), "UTF-8"));
		dto.setCategoryId(obj.getCategory().getCategoryId());
		dto.setCategoryName(obj.getCategory().getCategoryName());
		
		resultArray = JSONArray.fromObject(dto);
		
		// 返回跨域请求的数据
		if (jsoncallback != null) {
			jsoncallback(resultArray);
			return null;
		}
		
		return "array";
	}
	
	// getter setter

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

	public String getJsoncallback() {
		return jsoncallback;
	}

	public void setJsoncallback(String jsoncallback) {
		this.jsoncallback = jsoncallback;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
}
