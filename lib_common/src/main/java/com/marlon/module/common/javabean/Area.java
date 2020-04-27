/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.marlon.module.common.javabean;

import com.uwonders.db.AreaDao;
import com.uwonders.db.DaoSession;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinProperty;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Unique;

import java.util.List;

/**
 * 行政区划Entity
 * @author ThinkGem
 * @version 2017-03-22
 */
@Entity
public class Area {

	@Id
	private String id ;
	@NotNull @Unique
	private String areaCode;		// 区域代码
	private String areaName;		// 区域名称
	private String areaType; 		// 区域类型（1：国家；2：省份、直辖市；3：地市；4：区县）

	private Double regionArea;        // 区域面积，单位平方米
	private String treeNames;        // 区域面积，单位平方米
	private Double pointLng;        // 区域中心点经度
	private Double pointLat;        // 区域中心点纬度
	private Double lngMin;        // 网格最小点经度
	private Double latMin;        // 网格最小点纬度
	private Double lngMax;        // 网格最大点经度
	private Double latMax;        // 网格最大点纬度
	@NotNull
	private String parentCode;    //父类code
	private String createDate;
	@ToMany(joinProperties = {
			@JoinProperty(name = "areaCode", referencedName = "parentCode")
	})
	private List<Area> childList;
	/** Used to resolve relations */
	@Generated(hash = 2040040024)
	private transient DaoSession daoSession;
	/** Used for active entity operations. */
	@Generated(hash = 2080275148)
	private transient AreaDao myDao;



	@Generated(hash = 1740137475)
	public Area(String id, @NotNull String areaCode, String areaName, String areaType,
                Double regionArea, String treeNames, Double pointLng, Double pointLat, Double lngMin,
                Double latMin, Double lngMax, Double latMax, @NotNull String parentCode,
                String createDate) {
		this.id = id;
		this.areaCode = areaCode;
		this.areaName = areaName;
		this.areaType = areaType;
		this.regionArea = regionArea;
		this.treeNames = treeNames;
		this.pointLng = pointLng;
		this.pointLat = pointLat;
		this.lngMin = lngMin;
		this.latMin = latMin;
		this.lngMax = lngMax;
		this.latMax = latMax;
		this.parentCode = parentCode;
		this.createDate = createDate;
	}

	@Generated(hash = 179626505)
	public Area() {
	}
	


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getRegionArea() {
		return regionArea;
	}

	public void setRegionArea(Double regionArea) {
		this.regionArea = regionArea;
	}

	public Double getPointLng() {
		return pointLng;
	}

	public void setPointLng(Double pointLng) {
		this.pointLng = pointLng;
	}

	public Double getPointLat() {
		return pointLat;
	}

	public void setPointLat(Double pointLat) {
		this.pointLat = pointLat;
	}

	public Double getLngMin() {
		return lngMin;
	}

	public void setLngMin(Double lngMin) {
		this.lngMin = lngMin;
	}

	public Double getLatMin() {
		return latMin;
	}

	public void setLatMin(Double latMin) {
		this.latMin = latMin;
	}

	public Double getLngMax() {
		return lngMax;
	}

	public void setLngMax(Double lngMax) {
		this.lngMax = lngMax;
	}

	public Double getLatMax() {
		return latMax;
	}

	public void setLatMax(Double latMax) {
		this.latMax = latMax;
	}


	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}


	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	

	public String getAreaType() {
		return areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	/**
	 * To-many relationship, resolved on first access (and after reset).
	 * Changes to to-many relations are not persisted, make changes to the target entity.
	 */
	@Generated(hash = 1932838267)
	public List<Area> getChildList() {
		if (childList == null) {
			final DaoSession daoSession = this.daoSession;
			if (daoSession == null) {
				throw new DaoException("Entity is detached from DAO context");
			}
			AreaDao targetDao = daoSession.getAreaDao();
			List<Area> childListNew = targetDao._queryArea_ChildList(areaCode);
			synchronized (this) {
				if (childList == null) {
					childList = childListNew;
				}
			}
		}
		return childList;
	}

	/** Resets a to-many relationship, making the next get call to query for a fresh result. */
	@Generated(hash = 20549044)
	public synchronized void resetChildList() {
		childList = null;
	}

	/**
	 * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
	 * Entity must attached to an entity context.
	 */
	@Generated(hash = 128553479)
	public void delete() {
		if (myDao == null) {
			throw new DaoException("Entity is detached from DAO context");
		}
		myDao.delete(this);
	}

	/**
	 * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
	 * Entity must attached to an entity context.
	 */
	@Generated(hash = 1942392019)
	public void refresh() {
		if (myDao == null) {
			throw new DaoException("Entity is detached from DAO context");
		}
		myDao.refresh(this);
	}

	/**
	 * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
	 * Entity must attached to an entity context.
	 */
	@Generated(hash = 713229351)
	public void update() {
		if (myDao == null) {
			throw new DaoException("Entity is detached from DAO context");
		}
		myDao.update(this);
	}

	/** called by internal mechanisms, do not call yourself. */
	@Generated(hash = 1631869529)
	public void __setDaoSession(DaoSession daoSession) {
		this.daoSession = daoSession;
		myDao = daoSession != null ? daoSession.getAreaDao() : null;
	}

	public String getParentCode() {
		return this.parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getTreeNames() {
		return this.treeNames;
	}

	public void setTreeNames(String treeNames) {
		this.treeNames = treeNames;
	}


	@Override
	public String toString() {
		return "Area{" +
				"id='" + id + '\'' +
				", areaCode='" + areaCode + '\'' +
				", areaName='" + areaName + '\'' +
				", areaType='" + areaType + '\'' +
				", regionArea=" + regionArea +
				", treeNames='" + treeNames + '\'' +
				", pointLng=" + pointLng +
				", pointLat=" + pointLat +
				", lngMin=" + lngMin +
				", latMin=" + latMin +
				", lngMax=" + lngMax +
				", latMax=" + latMax +
				", parentCode='" + parentCode + '\'' +
				", createDate='" + createDate + '\'' +
				", childList=" + childList +
				", daoSession=" + daoSession +
				", myDao=" + myDao +
				'}';
	}
}