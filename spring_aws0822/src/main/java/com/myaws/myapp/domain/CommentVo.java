package com.myaws.myapp.domain;

public class CommentVo {
	// 컬럼을 쓰든 안쓰든 꺼내 놓는 것이 좋다. 혹시 나중에 쓰게 될지도 몰라서..
	private int cidx;
	private String csubject;
	private String ccontents;
	private String cwriter;
	private String writeday;
	private String delyn;
	private String cip;
	private int midx;
	private int bidx;
	
	public int getCidx() {
		return cidx;
	}
	public void setCidx(int cidx) {
		this.cidx = cidx;
	}
	public String getCsubject() {
		return csubject;
	}
	public void setCsubject(String csubject) {
		this.csubject = csubject;
	}
	public String getCcontents() {
		return ccontents;
	}
	public void setCcontents(String ccontents) {
		this.ccontents = ccontents;
	}
	public String getCwriter() {
		return cwriter;
	}
	public void setCwriter(String cwriter) {
		this.cwriter = cwriter;
	}
	public String getWriteday() {
		return writeday;
	}
	public void setWriteday(String writeday) {
		this.writeday = writeday;
	}
	public String getDelyn() {
		return delyn;
	}
	public void setDelyn(String delyn) {
		this.delyn = delyn;
	}
	public String getCip() {
		return cip;
	}
	public void setCip(String cip) {
		this.cip = cip;
	}
	public int getMidx() {
		return midx;
	}
	public void setMidx(int midx) {
		this.midx = midx;
	}
	public int getBidx() {
		return bidx;
	}
	public void setBidx(int bidx) {
		this.bidx = bidx;
	}
	
	

}
