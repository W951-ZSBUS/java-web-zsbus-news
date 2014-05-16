package com.w951.zsbus.news.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 系统版本：v1.0<br>
 * 开发人员：Lusifer<br>
 * 日期：2014-05-16<br>
 * 时间：15:49:37<br>
 * 功能描述：写明作用，调用方式，使用场景，以及特殊情况<br>
 *
 */
public class OutsideDTO implements Serializable {
	private static final long serialVersionUID = -1L;

    private String outsideId;
    private String outsidePid;
    private String outsideName;
    private Integer outsideSort;
    private String outsideUrl;
    private String outsideCreatename;
    private String outsideCreatedate;
    private List<OutsideDTO> children;

    public String getOutsideId() {
        return outsideId;
    }

    public void setOutsideId(String outsideId) {
        this.outsideId = outsideId;
    }
    
    public String getOutsidePid() {
        return outsidePid;
    }

    public void setOutsidePid(String outsidePid) {
        this.outsidePid = outsidePid;
    }
    
    public String getOutsideName() {
        return outsideName;
    }

    public void setOutsideName(String outsideName) {
        this.outsideName = outsideName;
    }
    
    public Integer getOutsideSort() {
        return outsideSort;
    }

    public void setOutsideSort(Integer outsideSort) {
        this.outsideSort = outsideSort;
    }
    
    public String getOutsideUrl() {
        return outsideUrl;
    }

    public void setOutsideUrl(String outsideUrl) {
        this.outsideUrl = outsideUrl;
    }
    
    public String getOutsideCreatename() {
        return outsideCreatename;
    }

    public void setOutsideCreatename(String outsideCreatename) {
        this.outsideCreatename = outsideCreatename;
    }
    
    public String getOutsideCreatedate() {
        return outsideCreatedate;
    }

    public void setOutsideCreatedate(String outsideCreatedate) {
        this.outsideCreatedate = outsideCreatedate;
    }

	public List<OutsideDTO> getChildren() {
		return children;
	}

	public void setChildren(List<OutsideDTO> children) {
		this.children = children;
	}

}