package com.marlon.module.common.javabean.request;

/**
 * @author Marlon
 * @desc
 * @date 2019/1/8
 */
public class GridRequest {
    private Integer gridId;

    public GridRequest() {
    }

    public GridRequest(Integer gridId) {
        this.gridId = gridId;
    }

    public Integer getGridId() {
        return gridId;
    }

    public void setGridId(Integer gridid) {
        this.gridId = gridid;
    }

    @Override
    public String toString() {
        return "GridRequest{" +
                "gridid=" + gridId +
                '}';
    }
}
