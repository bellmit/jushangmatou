package com.tem.gettogether.rongyun;

import java.io.Serializable;

/**
 * @author 作者 lt:
 * @date 2019/04/03
 * @Description
 */
public class RongFriends implements Serializable {


    private String userId;

    private String userName;

    private String portraitUri;


    public RongFriends(String userId, String userName, String portraitUri) {
        this.userId = userId;
        this.userName = userName;
        this.portraitUri = portraitUri;
    }

    @Override
    public String toString() {
        return "RongFriends{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", portraitUri='" + portraitUri + '\'' +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPortraitUri() {
        return portraitUri;
    }

    public void setPortraitUri(String portraitUri) {
        this.portraitUri = portraitUri;
    }
}
