package com.ecaresoftech.newproject.poja;

import com.google.gson.annotations.SerializedName;

public class LoginResponse{

	@SerializedName("csrf_token")
	private String csrfToken;

	@SerializedName("logout_token")
	private String logoutToken;

	@SerializedName("current_user")
	private CurrentUser currentUser;

	public void setCsrfToken(String csrfToken){
		this.csrfToken = csrfToken;
	}

	public String getCsrfToken(){
		return csrfToken;
	}

	public void setLogoutToken(String logoutToken){
		this.logoutToken = logoutToken;
	}

	public String getLogoutToken(){
		return logoutToken;
	}

	public void setCurrentUser(CurrentUser currentUser){
		this.currentUser = currentUser;
	}

	public CurrentUser getCurrentUser(){
		return currentUser;
	}
}