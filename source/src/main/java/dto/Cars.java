package dto;

import java.io.Serializable;

public class Cars implements Serializable {
	private int carid;
	private String carname;
	private String carstatus;
	private String carimage;
	
	public Cars(int carid, String carname, String carstatus, String carimage) {
		this.carid = carid;
		this.carname = carname;
		this.carstatus = carstatus;
		this.carimage = carimage;
	}
	public int getCarid() {
		return carid;
	}
	public void setCarid(int carid) {
		this.carid = carid;
	}
	public String getCarname() {
		return carname;
	}
	public void setCarname(String carname) {
		this.carname = carname;
	}
	public String getCarstatus() {
		return carstatus;
	}
	public void setCarstatus(String carstatus) {
		this.carstatus = carstatus;
	}
	public String getCarimage() {
		return carimage;
	}
	public void setCarimage(String carimage) {
		this.carimage = carimage;
	}
}