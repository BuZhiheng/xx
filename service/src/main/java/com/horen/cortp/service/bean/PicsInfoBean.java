package com.horen.cortp.service.bean;

import java.util.List;

/**
 * Created by HOREN on 2018/1/3.
 */

public class PicsInfoBean {
    /**
     * damagePics : ["http://172.20.246.125:8080/uploadimg/61352d7f93f8491faa7fe1f7234f9950.png","http://172.20.246.125:8080/uploadimg/2e18d724a34641558979fe6d1363449f.png","http://172.20.246.125:8080/uploadimg/1b060ee1902241acbd38748377b2d396.png","http://172.20.246.125:8080/uploadimg/059ee2251c4d4c40a0a675a4a6710bae.png"]
     * damageRemark : 看看
     * damageType : 上盖损坏
     * filepath : http://172.20.246.125:8080/uploadimg/61352d7f93f8491faa7fe1f7234f9950.png
     * id : TP000000000831
     * order_id : EF130000000062
     * reference_id : http://tp.mmxchina.com/?extinfo=PF0TXT1V
     */

    private String damageRemark;
    private String damageType;
    private String filepath;
    private String id;
    private String order_id;
    private String reference_id;
    private List<String> damagePics;
    private boolean isOpen = true;

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getDamageRemark() {
        return damageRemark;
    }

    public void setDamageRemark(String damageRemark) {
        this.damageRemark = damageRemark;
    }

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getReference_id() {
        return reference_id;
    }

    public void setReference_id(String reference_id) {
        this.reference_id = reference_id;
    }

    public List<String> getDamagePics() {
        return damagePics;
    }

    public void setDamagePics(List<String> damagePics) {
        this.damagePics = damagePics;
    }
}
