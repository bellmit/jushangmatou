package com.jsmt.developer.bean;

/**
 * 作者： litao
 * 时间： 2017/12/12
 * 描述：
 */
public class WeiXinMessageBean {
    private String openid;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 性别
     */
    private String sex;
    /**
     * 语言区域
     */
    private String language;
    /**
     *
     */
    private String city;
    private String province;
    private String country;
    /**
     * 头像
     */
    private String headimgurl;
    private String unionid;
    public WeiXinMessageBean() {
        super();
    }
    public WeiXinMessageBean(String openid, String nickname, String sex,
                             String language, String city, String province, String country,
                             String headimgurl, String unionid) {
        super();
        this.openid = openid;
        this.nickname = nickname;
        this.sex = sex;
        this.language = language;
        this.city = city;
        this.province = province;
        this.country = country;
        this.headimgurl = headimgurl;
        this.unionid = unionid;
    }
    public String getOpenid() {
        return openid;
    }
    public void setOpenid(String openid) {
        this.openid = openid;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getHeadimgurl() {
        return headimgurl;
    }
    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }
    public String getUnionid() {
        return unionid;
    }
    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
    @Override
    public String toString() {
        return "WeiXinMessageBean [openid=" + openid + ", nickname=" + nickname
                + ", sex=" + sex + ", language=" + language + ", city=" + city
                + ", province=" + province + ", country=" + country
                + ", headimgurl=" + headimgurl + ", unionid=" + unionid + "]";
    }

}
