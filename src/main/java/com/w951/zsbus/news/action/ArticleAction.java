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
import com.w951.zsbus.news.dto.ArticleDTO;
import com.w951.zsbus.news.entity.Article;
import com.w951.zsbus.news.service.ArticleService;

public class ArticleAction extends CommonAction {
	private static final long serialVersionUID = -1L;
	private JSONObject result;
	private JSONArray resultArray;
	private Map<String, Object> request;
	private Map<String, Object> session;

	@Resource
	private ArticleService articleService;
	
	// 参数

	private Article article;
	private int page;
	private int rows;
	private String categoryId;
	
	// Action
	
	public String queryByCategory() throws Exception {
		List<Article> list = articleService.queryPageListByCategoryId(categoryId, page, rows);

		ArticleDTO dto = null;
		List<ArticleDTO> dtos = new ArrayList<ArticleDTO>();
		if (list != null && list.size() > 0) {
			for (Article obj : list) {
				dto = new ArticleDTO();
				BeanUtil.beanToBean(dto, obj);
				dto.setCategoryId(obj.getCategory().getCategoryId());
				dto.setCategoryName(obj.getCategory().getCategoryName());
				dtos.add(dto);
			}
		}

		jsonData.put("total", articleService.getCountByCategoryId(categoryId));
		jsonData.put("rows", dtos);
		result = JSONObject.fromObject(jsonData);

		return SUCCESS;
	}
	
	@Override
	public String insert() throws Exception {
		User admin = (User) session.get("admin");
		
		article.setArticleAuthor(admin.getUserName());
		article.setArticleCreatedate(DateUtil.getDateTime());
		String message = articleService.insert(article);

		if (message != null) {
			jsonData.put("message", message);
		}
		
		result = JSONObject.fromObject(jsonData);

		return SUCCESS;
	}

	@Override
	public String delete() throws Exception {
		String message = articleService.delete(article);

		if (message != null) {
			jsonData.put("message", message);
		}
		
		result = JSONObject.fromObject(jsonData);

		return SUCCESS;
	}

	@Override
	public String update() throws Exception {
		String message = articleService.update(article);

		if (message != null) {
			jsonData.put("message", message);
		}
		
		result = JSONObject.fromObject(jsonData);

		return SUCCESS;
	}

	@Override
	public String query() throws Exception {
		List<Article> list = articleService.queryPageList(page, rows, "articleCreatedate", "DESC");

		ArticleDTO dto = null;
		List<ArticleDTO> dtos = new ArrayList<ArticleDTO>();
		if (list != null && list.size() > 0) {
			for (Article obj : list) {
				dto = new ArticleDTO();
				BeanUtil.beanToBean(dto, obj);
				dto.setCategoryId(obj.getCategory().getCategoryId());
				dto.setCategoryName(obj.getCategory().getCategoryName());
				dtos.add(dto);
			}
		}

		jsonData.put("total", articleService.getCount());
		jsonData.put("rows", dtos);
		result = JSONObject.fromObject(jsonData);

		return SUCCESS;
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

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
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

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

}
