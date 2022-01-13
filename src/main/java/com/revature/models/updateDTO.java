package com.revature.models;

public class updateDTO {
	private int rid;
	private int r_status_fk;
	private int resl_id;
	public updateDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public updateDTO(int rid, int r_status_fk, int resl_id) {
		super();
		this.rid = rid;
		this.r_status_fk = r_status_fk;
		this.resl_id = resl_id;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getR_status_fk() {
		return r_status_fk;
	}
	public void setR_status_fk(int r_status_fk) {
		this.r_status_fk = r_status_fk;
	}
	public int getResl_id() {
		return resl_id;
	}
	public void setResl_id(int resl_id) {
		this.resl_id = resl_id;
	}
	@Override
	public String toString() {
		return "updateDTO [rid=" + rid + ", r_status_fk=" + r_status_fk + ", resl_id=" + resl_id + "]";
	}
	
}
