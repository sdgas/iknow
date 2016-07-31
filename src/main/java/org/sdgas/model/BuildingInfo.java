package org.sdgas.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wilson.he on 2016/7/31.
 */
@Entity
public class BuildingInfo {

    /**
     * 序号
     */
    private int infoId;

    /**
     * 镇街
     */
    private String street;

    /**
     * 楼宇名称
     */
    private String buildingName;

    /**
     * 地址
     */
    private String address;

    /**
     * 新建项目编号
     */
    private String projectId;

    /**
     * 项目速记码
     */
    private String shorthandCode;

    /**
     * 合同签订户数
     */
    private int userNum;

    /**
     * 实际用气户数
     */
    private int actualUserNum;

    /**
     * 施工单位
     */
    private String constructionOrganization;

    /**
     * 气表安装
     */
    private String location;

    /**
     * 燃气表品牌及类别
     */
    private String meterType;

    /**
     * 供气情况/气源类型
     */
    private String gasType;

    /**
     * 供气合同编号
     */
    private String contractId;

    /**
     * 合同签订时间
     */
    private Date signatureDate;

    /**
     * 庭院地管
     */
    private String courtyard;

    /**
     * 立管
     */
    private String standPipe;

    /**
     * 户内用气点(个)
     */
    private String gasPoints;

    /**
     * 户内已装管道设施情况（地管、户内镀锌管、阀门等）
     */
    private String installedInfo;

    /**
     * 15A总阀
     */
    private String mainValve;

    /**
     * 15C嘴阀(个)
     */
    private String valve;

    /**
     * DN15镀锌管(米)
     */
    private String dn15;

    /**
     * 其它/我司需安装内容
     */
    private String others;

    /**
     * 容量气价
     */
    private String capacityGasPrice;

    /**
     * 合同约定的其它收费项目
     */
    private String otherPrice;

    /**
     * 报装时间
     */
    private  Date installationTime;

    /**
     * 小区通气情况
     */
    private String useGas;

    /**
     * 项目跟进业务
     */
    private String followed;

    /**
     * 备注
     */
    private String remark;

    /**
     * 户内安装明细
     */
    private String file;

    @Id
    @GeneratedValue
    public int getInfoId() {
        return infoId;
    }

    public void setInfoId(int infoId) {
        this.infoId = infoId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getShorthandCode() {
        return shorthandCode;
    }

    public void setShorthandCode(String shorthandCode) {
        this.shorthandCode = shorthandCode;
    }

    public int getUserNum() {
        return userNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    public int getActualUserNum() {
        return actualUserNum;
    }

    public void setActualUserNum(int actualUserNum) {
        this.actualUserNum = actualUserNum;
    }

    public String getConstructionOrganization() {
        return constructionOrganization;
    }

    public void setConstructionOrganization(String constructionOrganization) {
        this.constructionOrganization = constructionOrganization;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMeterType() {
        return meterType;
    }

    public void setMeterType(String meterType) {
        this.meterType = meterType;
    }

    public String getGasType() {
        return gasType;
    }

    public void setGasType(String gasType) {
        this.gasType = gasType;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    @Temporal(TemporalType.DATE)
    public Date getSignatureDate() {
        return signatureDate;
    }

    public void setSignatureDate(Date signatureDate) {
        this.signatureDate = signatureDate;
    }

    public String getCourtyard() {
        return courtyard;
    }

    public void setCourtyard(String courtyard) {
        this.courtyard = courtyard;
    }

    public String getStandPipe() {
        return standPipe;
    }

    public void setStandPipe(String standPipe) {
        this.standPipe = standPipe;
    }

    public String getGasPoints() {
        return gasPoints;
    }

    public void setGasPoints(String gasPoints) {
        this.gasPoints = gasPoints;
    }

    public String getInstalledInfo() {
        return installedInfo;
    }

    public void setInstalledInfo(String installedInfo) {
        this.installedInfo = installedInfo;
    }

    public String getMainValve() {
        return mainValve;
    }

    public void setMainValve(String mainValve) {
        this.mainValve = mainValve;
    }

    public String getValve() {
        return valve;
    }

    public void setValve(String valve) {
        this.valve = valve;
    }

    public String getDn15() {
        return dn15;
    }

    public void setDn15(String dn15) {
        this.dn15 = dn15;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public String getCapacityGasPrice() {
        return capacityGasPrice;
    }

    public void setCapacityGasPrice(String capacityGasPrice) {
        this.capacityGasPrice = capacityGasPrice;
    }

    public String getOtherPrice() {
        return otherPrice;
    }

    public void setOtherPrice(String otherPrice) {
        this.otherPrice = otherPrice;
    }

    @Temporal(TemporalType.DATE)
    public Date getInstallationTime() {
        return installationTime;
    }

    public void setInstallationTime(Date installationTime) {
        this.installationTime = installationTime;
    }

    public String getUseGas() {
        return useGas;
    }

    public void setUseGas(String useGas) {
        this.useGas = useGas;
    }

    public String getFollowed() {
        return followed;
    }

    public void setFollowed(String followed) {
        this.followed = followed;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
