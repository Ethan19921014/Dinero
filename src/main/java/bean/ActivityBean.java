package bean;

import java.io.Serializable;
import java.sql.Date;

public class ActivityBean implements Serializable {

	private String actId; 
	private String actName;
	private String actContent; 
	private String endDate; 
	private String memberRemain; 
	private String memberLimit; 
	

	public ActivityBean(String string) {
	}

	//¼g¤£¼gp³£¦æ
	public ActivityBean(String pActId, String pActName, String pEndDate, String pMemberRemain, String pMemberLimit, String pActContent) {
		this.actId = pActId;
		this.actName = pActName;
		this.endDate = pEndDate;
		this.memberRemain =pMemberRemain ;
		this.memberLimit = pMemberLimit;
		this.actContent = pActContent;
	}

	public ActivityBean() {
		// TODO Auto-generated constructor stub
	}

	public String getActId() {
		return actId;
	}

	public void setActId(String actId) {
		this.actId = actId;
	}

	public String getActName() {
		return actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}
	
	public String getActContent() {
		return actContent;
	}

	public void setActContent(String actContent) {
		this.actContent = actContent;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getMemberRemain() {
		return memberRemain;
	}

	public void setMemberRemain(String memberRemain) {
		this.memberRemain = memberRemain;
	}

	public String getMemberLimit() {
		return memberLimit;
	}

	public void setMemberLimit(String memberLimit) {
		this.memberLimit = memberLimit;
	}

}
