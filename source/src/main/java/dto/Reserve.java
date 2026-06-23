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
	private String carname;
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
//	public Reserve(int carid) {
//		super();
//		this.carid = carid;
//	}

	public String getCarname() {
		return carname;
	}

	public void setCarname(String carname) {
		this.carname = carname;
	}

	public Reserve(int reservenumber) {
		super();
		this.reservenumber = reservenumber;
	}

	public Reserve(int carid, String carname) {
		super();
		this.carname = carname;
		this.carid = carid;
	}

	public Reserve(int reservenumber, LocalDateTime sdate, LocalDateTime fdate, String purpose) {
		super();
		this.reservenumber = reservenumber;
		this.sdate = sdate;
		this.fdate = fdate;
		this.purpose = purpose;
	}

	public Reserve(String userid, LocalDateTime sdate, LocalDateTime fdate, int carid, String purpose) {
		super();
		this.userid = userid;
		this.sdate = sdate;
		this.fdate = fdate;
		this.carid = carid;
		this.purpose = purpose;
	}

	public Reserve(int reservenumber, LocalDateTime sdate, LocalDateTime fdate, String purpose, String username,
			String userid) {
		super();
		this.reservenumber = reservenumber;
		this.sdate = sdate;
		this.fdate = fdate;
		this.purpose = purpose;
		this.username = username;
		this.userid = userid;
	}

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