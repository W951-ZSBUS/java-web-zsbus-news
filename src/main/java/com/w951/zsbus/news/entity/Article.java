package com.w951.zsbus.news.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "zsbus_news_article")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Article implements java.io.Serializable {
	private static final long serialVersionUID = 3880565269405018559L;
	private String articleId;
	private Category category;
	private String articleTitle;
	private String articleSubtitle;
	private String articleAuthor;
	private String articleCreatedate;
	private String articleContent;

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "article_id", unique = true, nullable = false, length = 32)
	public String getArticleId() {
		return this.articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Column(name = "article_title", length = 50)
	public String getArticleTitle() {
		return this.articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	@Column(name = "article_subtitle", length = 150)
	public String getArticleSubtitle() {
		return this.articleSubtitle;
	}

	public void setArticleSubtitle(String articleSubtitle) {
		this.articleSubtitle = articleSubtitle;
	}

	@Column(name = "article_author", length = 20)
	public String getArticleAuthor() {
		return this.articleAuthor;
	}

	public void setArticleAuthor(String articleAuthor) {
		this.articleAuthor = articleAuthor;
	}

	@Column(name = "article_createdate", length = 19)
	public String getArticleCreatedate() {
		return this.articleCreatedate;
	}

	public void setArticleCreatedate(String articleCreatedate) {
		this.articleCreatedate = articleCreatedate;
	}

	@Column(name = "article_content", length = 65535)
	public String getArticleContent() {
		return this.articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

}