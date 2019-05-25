package com.fish.user.entity;

import lombok.Data;
import sun.dc.pr.PRError;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by mingkunyu on 2019-05-25
 */
@Data
@Entity
public class UserInfo {

    @Id
    private String id;

    private String username;
    private String password;
    private String openid;
    private Integer role;

}
