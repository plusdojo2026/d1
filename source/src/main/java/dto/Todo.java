package dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Todo implements Serializable {
	private int todoid;
	private int carid;
	private String outsidephoto;
	private String outsidememo;
	private boolean smell;
	private String insideitemmemo;
	private String gasolineamount;
	private boolean lostitem;
	private LocalDateTime createddate;
	private String lostitemmemo;
	private int userid;

	public Todo(int todoid, int carid, String outsidephoto, String outsidememo, boolean smell, String insideitemmemo,
			String gasolineamount, boolean lostitem, LocalDateTime createddate, String lostitemmemo, int userid) {

		this.todoid = todoid;
		this.carid = carid;
		this.outsidephoto = outsidephoto;
		this.outsidememo = outsidememo;
		this.smell = smell;
		this.insideitemmemo = insideitemmemo;
		this.gasolineamount = gasolineamount;
		this.lostitem = lostitem;
		this.createddate = createddate;
		this.lostitemmemo = lostitemmemo;
		this.userid = userid;
	}

	public int getTodoid() {
		return todoid;
	}

	public void setTodoid(int todoid) {
		this.todoid = todoid;
	}

	public int getCarid() {
		return carid;
	}

	public void setCarid(int carid) {
		this.carid = carid;
	}

	public String getOutsidephoto() {
		return outsidephoto;
	}

	public void setOutsidephoto(String outsidephoto) {
		this.outsidephoto = outsidephoto;
	}

	public String getOutsidememo() {
		return outsidememo;
	}

	public void setOutsidememo(String outsidememo) {
		this.outsidememo = outsidememo;
	}

	public boolean isSmell() {
		return smell;
	}

	public void setSmell(boolean smell) {
		this.smell = smell;
	}

	public String getInsideitemmemo() {
		return insideitemmemo;
	}

	public void setInsideitemmemo(String insideitemmemo) {
		this.insideitemmemo = insideitemmemo;
	}

	public String getGasolineamount() {
		return gasolineamount;
	}

	public void setGasolineamount(String gasolineamount) {
		this.gasolineamount = gasolineamount;
	}

	public boolean isLostitem() {
		return lostitem;
	}

	public void setLostitem(boolean lostitem) {
		this.lostitem = lostitem;
	}

	public LocalDateTime getCreateddate() {
		return createddate;
	}

	public void setCreateddate(LocalDateTime createddate) {
		this.createddate = createddate;
	}

	public String getLostitemmemo() {
		return lostitemmemo;
	}

	public void setLostitemmemo(String lostitemmemo) {
		this.lostitemmemo = lostitemmemo;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
}