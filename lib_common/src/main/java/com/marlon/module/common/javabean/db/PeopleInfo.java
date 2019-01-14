package com.marlon.module.common.javabean.db;


import com.marlon.module.common.javabean.PointBean;
import com.marlon.module.common.javabean.convernet.ConvernetPoint;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * @author Marlon
 * @desc
 * @date 2019/1/8
 */
@Entity
public class PeopleInfo {

    /**人
     * address :
     * cCode : 自动生成
     * lngLat : null
     * iId : 7578
     * identityCardNumber : 51342819740109171X
     * cName : 吉尔此哈
     * callNum : 18728954561
     * groupCode : 001001001001
     */
    //不能用int （ID 表示标识主键 且主键不能用int autoincrement = true 表示主键会自增）
    @Id(autoincrement = true)
    private Long id;
    private String address;
    private String cCode;
    @Convert(converter = ConvernetPoint.class,columnType =String.class)
    private PointBean lngLat;
    private String iId;
    private String identityCardNumber;
    private String cName;
    private String callNum;
    private String groupCode;

    @Generated(hash = 1381525577)
    public PeopleInfo(Long id, String address, String cCode, PointBean lngLat,
            String iId, String identityCardNumber, String cName, String callNum,
            String groupCode) {
        this.id = id;
        this.address = address;
        this.cCode = cCode;
        this.lngLat = lngLat;
        this.iId = iId;
        this.identityCardNumber = identityCardNumber;
        this.cName = cName;
        this.callNum = callNum;
        this.groupCode = groupCode;
    }

    @Generated(hash = 260782605)
    public PeopleInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getcCode() {
        return cCode;
    }

    public void setcCode(String cCode) {
        this.cCode = cCode;
    }

    public String getiId() {
        return iId;
    }

    public void setiId(String iId) {
        this.iId = iId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCCode() {
        return cCode;
    }

    public void setCCode(String cCode) {
        this.cCode = cCode;
    }

    public PointBean getLngLat() {
        return lngLat;
    }

    public void setLngLat(PointBean lngLat) {
        this.lngLat = lngLat;
    }

    public String getIId() {
        return iId;
    }

    public void setIId(String iId) {
        this.iId = iId;
    }

    public String getIdentityCardNumber() {
        return identityCardNumber;
    }

    public void setIdentityCardNumber(String identityCardNumber) {
        this.identityCardNumber = identityCardNumber;
    }

    public String getCName() {
        return cName;
    }

    public void setCName(String cName) {
        this.cName = cName;
    }

    public String getCallNum() {
        return callNum;
    }

    public void setCallNum(String callNum) {
        this.callNum = callNum;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }
}
