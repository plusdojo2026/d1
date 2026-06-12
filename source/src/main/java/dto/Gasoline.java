package dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Gasoline implements Serializable {
	private int gasolineid;
	private int userid;
	private String stationname;

	public Gasoline(int gasolineid, int userid, String stationname, int gasolineprice, LocalDateTime createddate) {

		this.gasolineid = gasolineid;
		this.userid = userid;
		this.stationname = stationname;
		this.gasolineprice = gasolineprice;
		this.createddate = createddate;
	}

	private int gasolineprice;
	private LocalDateTime createddate;

	public int getGasolineid() {
		return gasolineid;
	}

	public void setGasolineid(int gasolineid) {
		this.gasolineid = gasolineid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getStationname() {
		return stationname;
	}

	public void setStationname(String stationname) {
		this.stationname = stationname;
	}

	public int getGasolineprice() {
		return gasolineprice;
	}

	public void setGasolineprice(int gasolineprice) {
		this.gasolineprice = gasolineprice;
	}

	public LocalDateTime getCreateddate() {
		return createddate;
	}

	public void setCreateddate(LocalDateTime createddate) {
		this.createddate = createddate;
	}
}