package com.w951.zsbus.news.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "zsbus_news_category")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Category implements java.io.Serializable {
	private static final long serialVersionUID = 3873076308109283447L;
	private String categoryId;
	private String categoryPid;
	private String categoryName;
	private Integer categorySort;
	private Boolean categoryShow;
	private String categoryCreatename;
	private String categoryCreatedate;
	private List<Article> articles = new ArrayList<Article>();

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "category_id", unique = true, nullable = false, length = 32)
	public String getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "category_pid", length = 32)
	public String getCategoryPid() {
		return this.categoryPid;
	}

	public void setCategoryPid(String categoryPid) {
		this.categoryPid = categoryPid;
	}

	@Column(name = "category_name", length = 20)
	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Column(name = "category_sort")
	public Integer getCategorySort() {
		return this.categorySort;
	}

	public void setCategorySort(Integer categorySort) {
		this.categorySort = categorySort;
	}

	@Column(name = "category_createname", length = 20)
	public String getCategoryCreatename() {
		return this.categoryCreatename;
	}

	public void setCategoryCreatename(String categoryCreatename) {
		this.categoryCreatename = categoryCreatename;
	}

	@Column(name = "category_createdate", length = 19)
	public String getCategoryCreatedate() {
		return this.categoryCreatedate;
	}

	public void setCategoryCreatedate(String categoryCreatedate) {
		this.categoryCreatedate = categoryCreatedate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "category")
	public List<Article> getArticles() {
		return this.articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	@Column(name = "category_show")
	public Boolean getCategoryShow() {
		return categoryShow;
	}

	public void setCategoryShow(Boolean categoryShow) {
		this.categoryShow = categoryShow;
	}

}