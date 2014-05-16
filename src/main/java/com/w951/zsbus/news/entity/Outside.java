package com.w951.zsbus.news.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "zsbus_news_outside")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Outside implements java.io.Serializable {
	private static final long serialVersionUID = 4491815483804111921L;
	private String outsideId;
	private String outsidePid;
	private String outsideName;
	private Integer outsideSort;
	private String outsideUrl;
	private String outsideCreatename;
	private String outsideCreatedate;

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "outside_id", unique = true, nullable = false, length = 32)
	public String getOutsideId() {
		return this.outsideId;
	}

	public void setOutsideId(String outsideId) {
		this.outsideId = outsideId;
	}

	@Column(name = "outside_pid", length = 32)
	public String getOutsidePid() {
		return this.outsidePid;
	}

	public void setOutsidePid(String outsidePid) {
		this.outsidePid = outsidePid;
	}

	@Column(name = "outside_name", length = 20)
	public String getOutsideName() {
		return this.outsideName;
	}

	public void setOutsideName(String outsideName) {
		this.outsideName = outsideName;
	}

	@Column(name = "outside_sort")
	public Integer getOutsideSort() {
		return this.outsideSort;
	}

	public void setOutsideSort(Integer outsideSort) {
		this.outsideSort = outsideSort;
	}

	@Column(name = "outside_url", length = 200)
	public String getOutsideUrl() {
		return this.outsideUrl;
	}

	public void setOutsideUrl(String outsideUrl) {
		this.outsideUrl = outsideUrl;
	}

	@Column(name = "outside_createname", length = 20)
	public String getOutsideCreatename() {
		return this.outsideCreatename;
	}

	public void setOutsideCreatename(String outsideCreatename) {
		this.outsideCreatename = outsideCreatename;
	}

	@Column(name = "outside_createdate", length = 19)
	public String getOutsideCreatedate() {
		return this.outsideCreatedate;
	}

	public void setOutsideCreatedate(String outsideCreatedate) {
		this.outsideCreatedate = outsideCreatedate;
	}

}