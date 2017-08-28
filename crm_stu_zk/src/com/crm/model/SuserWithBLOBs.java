package com.crm.model;

public class SuserWithBLOBs extends Suser {
    private String remark;

    private String menus;

    private String popedom;

    private String avocation;

    private String strongsuit;

    private String communicate;

    private String bringup;

    private String organise;

    private String analyse;

    private String planing;

    private String empolder;

    private String relation;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getMenus() {
        return menus;
    }

    public void setMenus(String menus) {
        this.menus = menus == null ? null : menus.trim();
    }

    public String getPopedom() {
        return popedom;
    }

    public void setPopedom(String popedom) {
        this.popedom = popedom == null ? null : popedom.trim();
    }

    public String getAvocation() {
        return avocation;
    }

    public void setAvocation(String avocation) {
        this.avocation = avocation == null ? null : avocation.trim();
    }

    public String getStrongsuit() {
        return strongsuit;
    }

    public void setStrongsuit(String strongsuit) {
        this.strongsuit = strongsuit == null ? null : strongsuit.trim();
    }

    public String getCommunicate() {
        return communicate;
    }

    public void setCommunicate(String communicate) {
        this.communicate = communicate == null ? null : communicate.trim();
    }

    public String getBringup() {
        return bringup;
    }

    public void setBringup(String bringup) {
        this.bringup = bringup == null ? null : bringup.trim();
    }

    public String getOrganise() {
        return organise;
    }

    public void setOrganise(String organise) {
        this.organise = organise == null ? null : organise.trim();
    }

    public String getAnalyse() {
        return analyse;
    }

    public void setAnalyse(String analyse) {
        this.analyse = analyse == null ? null : analyse.trim();
    }

    public String getPlaning() {
        return planing;
    }

    public void setPlaning(String planing) {
        this.planing = planing == null ? null : planing.trim();
    }

    public String getEmpolder() {
        return empolder;
    }

    public void setEmpolder(String empolder) {
        this.empolder = empolder == null ? null : empolder.trim();
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation == null ? null : relation.trim();
    }
}