package dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Gasoline implements Serializable {
	private int gasolineid;
	private String userid;
	private String stationname;
	private int gasolineprice;
	private LocalDateTime createddate;
	private String resultMessage;

	public Gasoline(int gasolineid, String userid, String stationname, int gasolineprice, LocalDateTime createddate) {

		this.gasolineid = gasolineid;
		this.userid = userid;
		this.stationname = stationname;
		this.gasolineprice = gasolineprice;
		this.createddate = createddate;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public int getGasolineid() {
		return gasolineid;
	}

	public void setGasolineid(int gasolineid) {
		this.gasolineid = gasolineid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
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