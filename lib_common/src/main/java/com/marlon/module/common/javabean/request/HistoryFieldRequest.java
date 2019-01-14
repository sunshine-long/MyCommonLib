package com.marlon.module.common.javabean.request;

/**
 * @author Marlon
 * @desc
 * @date 2019/1/9
 */
public class HistoryFieldRequest {
    private Integer gridId;
    private String token;

    public HistoryFieldRequest(Integer gridId, String token) {
        this.gridId = gridId;
        this.token = token;
    }

    public Integer getGridId() {
        return gridId;
    }

    public void setGridId(Integer gridId) {
        this.gridId = gridId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "HistoryFieldRequest{" +
                "gridId=" + gridId +
                ", token='" + token + '\'' +
                '}';
    }
}
