package com.crm.model;

import java.util.Date;

public class Suser {
    private Integer id;

    private String creator;

    private Date createtime;

    private String updater;

    private Date updatetime;

    private String name;

    private String cnname;

    private String password;

    private String address;

    private String telephone;

    private String email;

    private Date begindate;

    private Date enddate;

    private String accessFilelevel;

    private String status;

    private String commendman;

    private String movetelephone;

    private String nowaddress;

    private String nowtelephone;

    private String identitycode;

    private String insurancecode;

    private String instancylinkman;

    private String instancytelephone;

    private String sex;

    private Date birthday;

    private String personneltype;

    private String duty;

    private Date workdate;

    private String highschool;

    private String finishschool;

    private Date finishschooldate;

    private String consortname;

    private String youngonename;

    private String officetelephone;

    private String consorttelephone;

    private String consortcompany;

    private Integer roleId;

    private Integer groupId;

    private Integer accessGroupId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater == null ? null : updater.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCnname() {
        return cnname;
    }

    public void setCnname(String cnname) {
        this.cnname = cnname == null ? null : cnname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getBegindate() {
        return begindate;
    }

    public void setBegindate(Date begindate) {
        this.begindate = begindate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getAccessFilelevel() {
        return accessFilelevel;
    }

    public void setAccessFilelevel(String accessFilelevel) {
        this.accessFilelevel = accessFilelevel == null ? null : accessFilelevel.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCommendman() {
        return commendman;
    }

    public void setCommendman(String commendman) {
        this.commendman = commendman == null ? null : commendman.trim();
    }

    public String getMovetelephone() {
        return movetelephone;
    }

    public void setMovetelephone(String movetelephone) {
        this.movetelephone = movetelephone == null ? null : movetelephone.trim();
    }

    public String getNowaddress() {
        return nowaddress;
    }

    public void setNowaddress(String nowaddress) {
        this.nowaddress = nowaddress == null ? null : nowaddress.trim();
    }

    public String getNowtelephone() {
        return nowtelephone;
    }

    public void setNowtelephone(String nowtelephone) {
        this.nowtelephone = nowtelephone == null ? null : nowtelephone.trim();
    }

    public String getIdentitycode() {
        return identitycode;
    }

    public void setIdentitycode(String identitycode) {
        this.identitycode = identitycode == null ? null : identitycode.trim();
    }

    public String getInsurancecode() {
        return insurancecode;
    }

    public void setInsurancecode(String insurancecode) {
        this.insurancecode = insurancecode == null ? null : insurancecode.trim();
    }

    public String getInstancylinkman() {
        return instancylinkman;
    }

    public void setInstancylinkman(String instancylinkman) {
        this.instancylinkman = instancylinkman == null ? null : instancylinkman.trim();
    }

    public String getInstancytelephone() {
        return instancytelephone;
    }

    public void setInstancytelephone(String instancytelephone) {
        this.instancytelephone = instancytelephone == null ? null : instancytelephone.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPersonneltype() {
        return personneltype;
    }

    public void setPersonneltype(String personneltype) {
        this.personneltype = personneltype == null ? null : personneltype.trim();
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty == null ? null : duty.trim();
    }

    public Date getWorkdate() {
        return workdate;
    }

    public void setWorkdate(Date workdate) {
        this.workdate = workdate;
    }

    public String getHighschool() {
        return highschool;
    }

    public void setHighschool(String highschool) {
        this.highschool = highschool == null ? null : highschool.trim();
    }

    public String getFinishschool() {
        return finishschool;
    }

    public void setFinishschool(String finishschool) {
        this.finishschool = finishschool == null ? null : finishschool.trim();
    }

    public Date getFinishschooldate() {
        return finishschooldate;
    }

    public void setFinishschooldate(Date finishschooldate) {
        this.finishschooldate = finishschooldate;
    }

    public String getConsortname() {
        return consortname;
    }

    public void setConsortname(String consortname) {
        this.consortname = consortname == null ? null : consortname.trim();
    }

    public String getYoungonename() {
        return youngonename;
    }

    public void setYoungonename(String youngonename) {
        this.youngonename = youngonename == null ? null : youngonename.trim();
    }

    public String getOfficetelephone() {
        return officetelephone;
    }

    public void setOfficetelephone(String officetelephone) {
        this.officetelephone = officetelephone == null ? null : officetelephone.trim();
    }

    public String getConsorttelephone() {
        return consorttelephone;
    }

    public void setConsorttelephone(String consorttelephone) {
        this.consorttelephone = consorttelephone == null ? null : consorttelephone.trim();
    }

    public String getConsortcompany() {
        return consortcompany;
    }

    public void setConsortcompany(String consortcompany) {
        this.consortcompany = consortcompany == null ? null : consortcompany.trim();
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getAccessGroupId() {
        return accessGroupId;
    }

    public void setAccessGroupId(Integer accessGroupId) {
        this.accessGroupId = accessGroupId;
    }
}