package com.w951.zsbus.news.dto;

import java.io.Serializable;

/**
 * 
 * 系统版本：v1.0<br>
 * 开发人员：Lusifer<br>
 * 日期：2014-05-14<br>
 * 时间：20:37:00<br>
 * 功能描述：写明作用，调用方式，使用场景，以及特殊情况<br>
 *
 */
public class ArticleDTO implements Serializable {
	private static final long serialVersionUID = -1L;

    private String articleId;
    private String articleTitle;
    private String articleSubtitle;
    private String articleAuthor;
    private String articleCreatedate;
    private String articleContent;
    private String categoryId;
	private String categoryName;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }
    
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    
    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }
    
    public String getArticleSubtitle() {
        return articleSubtitle;
    }

    public void setArticleSubtitle(String articleSubtitle) {
        this.articleSubtitle = articleSubtitle;
    }
    
    public String getArticleAuthor() {
        return articleAuthor;
    }

    public void setArticleAuthor(String articleAuthor) {
        this.articleAuthor = articleAuthor;
    }
    
    public String getArticleCreatedate() {
        return articleCreatedate;
    }

    public void setArticleCreatedate(String articleCreatedate) {
        this.articleCreatedate = articleCreatedate;
    }
    
    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}