package com.marlon.module.common.javabean;

/**
 * @author Marlon
 * @desc
 * @date 2018/12/26
 */
public class UserInfo {

    /**
     * gridName : 280106001
     * iId : 387
     * cName : 吉坡色呷
     * callNum : 15881598743
     * gridId : 513
     * account : 15881598743
     * gridCode : 001001001001
     * token : e0wwjtftbl7fhf5053noubm$3jxm!wfr
     */

    private String gridName;
    private int iId;
    private String cName;
    private String callNum;
    private int gridId;
    private String account;
    private String gridCode;
    private String token;

    public String getGridName() {
        return gridName;
    }

    public void setGridName(String gridName) {
        this.gridName = gridName;
    }

    public int getIId() {
        return iId;
    }

    public void setIId(int iId) {
        this.iId = iId;
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

    public int getGridId() {
        return gridId;
    }

    public void setGridId(int gridId) {
        this.gridId = gridId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getGridCode() {
        return gridCode;
    }

    public void setGridCode(String gridCode) {
        this.gridCode = gridCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "gridName='" + gridName + '\'' +
                ", iId=" + iId +
                ", cName='" + cName + '\'' +
                ", callNum='" + callNum + '\'' +
                ", gridId=" + gridId +
                ", account='" + account + '\'' +
                ", gridCode='" + gridCode + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
