package com.marlon.module.common.javabean.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * @author Marlon
 * @desc
 * @date 2019/1/9
 */
@Entity
public class VillageGroup {

    /**
     * cName : 普格
     * cType : 8
     * cCode : 001
     */
    @Id(autoincrement = true)
    private Long id;
    private String cName;
    private String cType;
    private String cCode;

    @Generated(hash = 457706329)
    public VillageGroup(Long id, String cName, String cType, String cCode) {
        this.id = id;
        this.cName = cName;
        this.cType = cType;
        this.cCode = cCode;
    }

    @Generated(hash = 1396459394)
    public VillageGroup() {
    }

    public String getCName() {
        return cName;
    }

    public void setCName(String cName) {
        this.cName = cName;
    }

    public String getCType() {
        return cType;
    }

    public void setCType(String cType) {
        this.cType = cType;
    }

    public String getCCode() {
        return cCode;
    }

    public void setCCode(String cCode) {
        this.cCode = cCode;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
