package com.crm.model;

import java.util.Date;

public class Company {
    private Integer id;

    private String name;

    private String pycode;

    private String code;

    private String grade;

    private String source;

    private String cType;

    private String mType;

    private Integer regionId;

    private String regionName;

    private String province;

    private String city;

    private String fax;

    private String postcode;

    private String tel1;

    private String tel2;

    private String mobile;

    private String email;

    private String web;

    private String address;

    private String dealing;

    private String scale;

    private String registerMoney;

    private String kind;

    private String trade;

    private String legalPerson;

    private String bank;

    private String account;

    private String taxCode;

    private String buyFlag;

    private String creater;

    private Date createTime;

    private String updater;

    private Date updateTime;

    private String status;

    private Date nextTouchDate;

    private Date nextServiceDate;

    private Date firstTouchDate;

    private Date lastTouchDate;

    private String dispensePerson;

    private Date dispenseDate;

    private Integer applyUserId;

    private String sPerson;

    private Date sDate;

    private Date planCallbackDate;

    private String curLinkFlag;

    private String curBuyFlag;

    private Integer freezeLeaveDay;

    private String custom1;

    private String custom2;

    private String custom3;

    private String custom4;

    private String choice1;

    private String choice2;

    private String choice3;

    private String choice4;

    private Date time1;

    private Date time2;

    private String shareFlag;

    private String quality;

    private Integer ownerUsr;

    private String remark;

    private String nextTouchAim;

    private String sReason;

    private String planCallbackReason;

    private String shareIds;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getNextTouchAim() {
        return nextTouchAim;
    }

    public void setNextTouchAim(String nextTouchAim) {
        this.nextTouchAim = nextTouchAim;
    }

    public String getsReason() {
        return sReason;
    }

    public void setsReason(String sReason) {
        this.sReason = sReason;
    }

    public String getPlanCallbackReason() {
        return planCallbackReason;
    }

    public void setPlanCallbackReason(String planCallbackReason) {
        this.planCallbackReason = planCallbackReason;
    }

    public String getShareIds() {
        return shareIds;
    }

    public void setShareIds(String shareIds) {
        this.shareIds = shareIds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPycode() {
        return pycode;
    }

    public void setPycode(String pycode) {
        this.pycode = pycode == null ? null : pycode.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getcType() {
        return cType;
    }

    public void setcType(String cType) {
        this.cType = cType == null ? null : cType.trim();
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType == null ? null : mType.trim();
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName == null ? null : regionName.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1 == null ? null : tel1.trim();
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2 == null ? null : tel2.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web == null ? null : web.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getDealing() {
        return dealing;
    }

    public void setDealing(String dealing) {
        this.dealing = dealing == null ? null : dealing.trim();
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale == null ? null : scale.trim();
    }

    public String getRegisterMoney() {
        return registerMoney;
    }

    public void setRegisterMoney(String registerMoney) {
        this.registerMoney = registerMoney == null ? null : registerMoney.trim();
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind == null ? null : kind.trim();
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade == null ? null : trade.trim();
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson == null ? null : legalPerson.trim();
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode == null ? null : taxCode.trim();
    }

    public String getBuyFlag() {
        return buyFlag;
    }

    public void setBuyFlag(String buyFlag) {
        this.buyFlag = buyFlag == null ? null : buyFlag.trim();
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater == null ? null : updater.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getNextTouchDate() {
        return nextTouchDate;
    }

    public void setNextTouchDate(Date nextTouchDate) {
        this.nextTouchDate = nextTouchDate;
    }

    public Date getNextServiceDate() {
        return nextServiceDate;
    }

    public void setNextServiceDate(Date nextServiceDate) {
        this.nextServiceDate = nextServiceDate;
    }

    public Date getFirstTouchDate() {
        return firstTouchDate;
    }

    public void setFirstTouchDate(Date firstTouchDate) {
        this.firstTouchDate = firstTouchDate;
    }

    public Date getLastTouchDate() {
        return lastTouchDate;
    }

    public void setLastTouchDate(Date lastTouchDate) {
        this.lastTouchDate = lastTouchDate;
    }

    public String getDispensePerson() {
        return dispensePerson;
    }

    public void setDispensePerson(String dispensePerson) {
        this.dispensePerson = dispensePerson == null ? null : dispensePerson.trim();
    }

    public Date getDispenseDate() {
        return dispenseDate;
    }

    public void setDispenseDate(Date dispenseDate) {
        this.dispenseDate = dispenseDate;
    }

    public Integer getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(Integer applyUserId) {
        this.applyUserId = applyUserId;
    }

    public String getsPerson() {
        return sPerson;
    }

    public void setsPerson(String sPerson) {
        this.sPerson = sPerson == null ? null : sPerson.trim();
    }

    public Date getsDate() {
        return sDate;
    }

    public void setsDate(Date sDate) {
        this.sDate = sDate;
    }

    public Date getPlanCallbackDate() {
        return planCallbackDate;
    }

    public void setPlanCallbackDate(Date planCallbackDate) {
        this.planCallbackDate = planCallbackDate;
    }

    public String getCurLinkFlag() {
        return curLinkFlag;
    }

    public void setCurLinkFlag(String curLinkFlag) {
        this.curLinkFlag = curLinkFlag == null ? null : curLinkFlag.trim();
    }

    public String getCurBuyFlag() {
        return curBuyFlag;
    }

    public void setCurBuyFlag(String curBuyFlag) {
        this.curBuyFlag = curBuyFlag == null ? null : curBuyFlag.trim();
    }

    public Integer getFreezeLeaveDay() {
        return freezeLeaveDay;
    }

    public void setFreezeLeaveDay(Integer freezeLeaveDay) {
        this.freezeLeaveDay = freezeLeaveDay;
    }

    public String getCustom1() {
        return custom1;
    }

    public void setCustom1(String custom1) {
        this.custom1 = custom1 == null ? null : custom1.trim();
    }

    public String getCustom2() {
        return custom2;
    }

    public void setCustom2(String custom2) {
        this.custom2 = custom2 == null ? null : custom2.trim();
    }

    public String getCustom3() {
        return custom3;
    }

    public void setCustom3(String custom3) {
        this.custom3 = custom3 == null ? null : custom3.trim();
    }

    public String getCustom4() {
        return custom4;
    }

    public void setCustom4(String custom4) {
        this.custom4 = custom4 == null ? null : custom4.trim();
    }

    public String getChoice1() {
        return choice1;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1 == null ? null : choice1.trim();
    }

    public String getChoice2() {
        return choice2;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2 == null ? null : choice2.trim();
    }

    public String getChoice3() {
        return choice3;
    }

    public void setChoice3(String choice3) {
        this.choice3 = choice3 == null ? null : choice3.trim();
    }

    public String getChoice4() {
        return choice4;
    }

    public void setChoice4(String choice4) {
        this.choice4 = choice4 == null ? null : choice4.trim();
    }

    public Date getTime1() {
        return time1;
    }

    public void setTime1(Date time1) {
        this.time1 = time1;
    }

    public Date getTime2() {
        return time2;
    }

    public void setTime2(Date time2) {
        this.time2 = time2;
    }

    public String getShareFlag() {
        return shareFlag;
    }

    public void setShareFlag(String shareFlag) {
        this.shareFlag = shareFlag == null ? null : shareFlag.trim();
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality == null ? null : quality.trim();
    }

    public Integer getOwnerUsr() {
        return ownerUsr;
    }

    public void setOwnerUsr(Integer ownerUsr) {
        this.ownerUsr = ownerUsr;
    }
}