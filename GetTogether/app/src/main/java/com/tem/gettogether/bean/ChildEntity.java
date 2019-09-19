package com.tem.gettogether.bean;

/**
 * @ProjectName: GetTogether
 * @Package: com.tem.gettogether.bean
 * @ClassName: ChildEntity
 * @Author: csc
 * @CreateDate: 2019/9/15 13:54
 * @Description:
 */

import java.util.List;

/**
 * 子项数据的实体类
 */
public class ChildEntity {

    private String nickname;
    private String head_pic;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHead_pic() {
        return head_pic;
    }

    public void setHead_pic(String head_pic) {
        this.head_pic = head_pic;
    }

    public ChildEntity(String nickname, String head_pic) {
        this.nickname = nickname;
        this.head_pic = head_pic;
    }
}
