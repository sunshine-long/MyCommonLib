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
public class GridInfo {

    /**
     * areaContract : 0
     * farmerCount : 2
     * gridName : 280106001
     * groupCodes : 沙里村 一组@001014001001,新农村 四组@001016001001
     * stagePer : 0
     * stageEnd : null
     * point : {"lng":"102.554342150688","lat":"27.311252829749"}
     * sStage : null
     * stageBegin : null
     * areaPlan : 0
     * stationPoint : 普格烟草公司@普乐烟站@附城烟点
     * gridId : 513
     * areaDelineation : 695098.18
     * examineStandard : null
     * gridCode : 001001001001
     */
    //不能用int （ID 表示标识主键 且主键不能用int autoincrement = true 表示主键会自增）
    @Id(autoincrement = true)
    private Long id;
    private String areaContract;
    private Integer farmerCount;
    private String gridName;
    private String groupCodes;
    private String stagePer;
    private String stageEnd;
    @Convert(converter = ConvernetPoint.class,columnType = String.class)
    private PointBean point;
    private String sStage;
    private String stageBegin;
    private String areaPlan;
    private String stationPoint;
    private String gridId;
    private String areaDelineation;
    private String examineStandard;
    private String gridCode;

    @Generated(hash = 428951101)
    public GridInfo(Long id, String areaContract, Integer farmerCount,
            String gridName, String groupCodes, String stagePer, String stageEnd,
            PointBean point, String sStage, String stageBegin, String areaPlan,
            String stationPoint, String gridId, String areaDelineation,
            String examineStandard, String gridCode) {
        this.id = id;
        this.areaContract = areaContract;
        this.farmerCount = farmerCount;
        this.gridName = gridName;
        this.groupCodes = groupCodes;
        this.stagePer = stagePer;
        this.stageEnd = stageEnd;
        this.point = point;
        this.sStage = sStage;
        this.stageBegin = stageBegin;
        this.areaPlan = areaPlan;
        this.stationPoint = stationPoint;
        this.gridId = gridId;
        this.areaDelineation = areaDelineation;
        this.examineStandard = examineStandard;
        this.gridCode = gridCode;
    }

    @Generated(hash = 341398696)
    public GridInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFarmerCount(Integer farmerCount) {
        this.farmerCount = farmerCount;
    }

    public String getsStage() {
        return sStage;
    }

    public void setsStage(String sStage) {
        this.sStage = sStage;
    }

    public String getAreaContract() {
        return areaContract;
    }

    public void setAreaContract(String areaContract) {
        this.areaContract = areaContract;
    }

    public int getFarmerCount() {
        return farmerCount;
    }

    public void setFarmerCount(int farmerCount) {
        this.farmerCount = farmerCount;
    }

    public String getGridName() {
        return gridName;
    }

    public void setGridName(String gridName) {
        this.gridName = gridName;
    }

    public String getGroupCodes() {
        return groupCodes;
    }

    public void setGroupCodes(String groupCodes) {
        this.groupCodes = groupCodes;
    }

    public String getStagePer() {
        return stagePer;
    }

    public void setStagePer(String stagePer) {
        this.stagePer = stagePer;
    }

    public String getStageEnd() {
        return stageEnd;
    }

    public void setStageEnd(String stageEnd) {
        this.stageEnd = stageEnd;
    }

    public PointBean getPoint() {
        return point;
    }

    public void setPoint(PointBean point) {
        this.point = point;
    }

    public String getSStage() {
        return sStage;
    }

    public void setSStage(String sStage) {
        this.sStage = sStage;
    }

    public String getStageBegin() {
        return stageBegin;
    }

    public void setStageBegin(String stageBegin) {
        this.stageBegin = stageBegin;
    }

    public String getAreaPlan() {
        return areaPlan;
    }

    public void setAreaPlan(String areaPlan) {
        this.areaPlan = areaPlan;
    }

    public String getStationPoint() {
        return stationPoint;
    }

    public void setStationPoint(String stationPoint) {
        this.stationPoint = stationPoint;
    }

    public String getGridId() {
        return gridId;
    }

    public void setGridId(String gridId) {
        this.gridId = gridId;
    }

    public String getAreaDelineation() {
        return areaDelineation;
    }

    public void setAreaDelineation(String areaDelineation) {
        this.areaDelineation = areaDelineation;
    }

    public String getExamineStandard() {
        return examineStandard;
    }

    public void setExamineStandard(String examineStandard) {
        this.examineStandard = examineStandard;
    }

    public String getGridCode() {
        return gridCode;
    }

    public void setGridCode(String gridCode) {
        this.gridCode = gridCode;
    }
}
