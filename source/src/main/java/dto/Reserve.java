package dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Reserve implements Serializable {
	private int reservenumber;
	private String userid;
	private LocalDateTime sdate;
	private LocalDateTime fdate;
	private int carid;
	private LocalDateTime reservecreated;
	private String purpose;
	private String username;

	public Reserve(int reservenumber, String userid, LocalDateTime sdate, LocalDateTime fdate, int carid,
			LocalDateTime reservecreated, String purpose, String username) {

		this.reservenumber = reservenumber;
		this.userid = userid;
		this.sdate = sdate;
		this.fdate = fdate;
		this.carid = carid;
		this.reservecreated = reservecreated;
		this.purpose = purpose;
		this.username = username;
	}
	public Reserve() {
	}
	public int getReservenumber() {
		return reservenumber;
	}

	public void setReservenumber(int reservenumber) {
		this.reservenumber = reservenumber;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public LocalDateTime getSdate() {
		return sdate;
	}

	public void setSdate(LocalDateTime sdate) {
		this.sdate = sdate;
	}

	public LocalDateTime getFdate() {
		return fdate;
	}

	public void setFdate(LocalDateTime fdate) {
		this.fdate = fdate;
	}

	public int getCarid() {
		return carid;
	}

	public void setCarid(int carid) {
		this.carid = carid;
	}

	public LocalDateTime getReservecreated() {
		return reservecreated;
	}

	public void setReservecreated(LocalDateTime reservecreated) {
		this.reservecreated = reservecreated;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
