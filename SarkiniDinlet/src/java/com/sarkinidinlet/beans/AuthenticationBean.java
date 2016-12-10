package com.sarkinidinlet.beans;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
public class AuthenticationBean {

    /**
     * Loginname
     */
    private String loginName;
    public String getLoginName() {return loginName;}
    public void setLoginName(String loginName) {this.loginName = loginName;}
    
    /**
     * Password
     */
    private String password;
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    /**
     *
     */
    public AuthenticationBean() {}

    /**
     * 
     * @return
     */
    public String CheckValidUser() {
        if (loginName.equals("chandan") && password.equals("chand")) {
            return "home";
        } else {
            return "login";
        }
    }

}
