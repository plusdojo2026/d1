package dto;

import java.io.Serializable;

public class IdPw implements Serializable {
	private String userid; // ID
	private String password; // パスワード
	
	public IdPw(String userid, String password) {
		this.userid = userid;
		this.password = password;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public IdPw() {
		this.userid = "";
		this.password = "";
	}

}