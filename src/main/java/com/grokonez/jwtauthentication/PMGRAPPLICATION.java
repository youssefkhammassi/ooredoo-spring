package com.grokonez.jwtauthentication;

import java.sql.Date;

public class PMGRAPPLICATION {
	private Integer APPLICATION_ID;
	private Integer SUBSCRIPTION_ID ;
	private Integer PACKAGE_ID;
	private String MSISDN;
	private Date ENTRY_DATE;
	private Integer AMOUNT ;
	private String STATUS;
	private String NAME ;
	
	public PMGRAPPLICATION () {}
	public PMGRAPPLICATION(Integer aPPLICATION_ID, Integer sUBSCRIPTION_ID, Integer pACKAGE_ID, String mSISDN,
			Date eNTRY_DATE, Integer aMOUNT, String sTATUS, String nAME) {
		super();
		APPLICATION_ID = aPPLICATION_ID;
		SUBSCRIPTION_ID = sUBSCRIPTION_ID;
		PACKAGE_ID = pACKAGE_ID;
		MSISDN = mSISDN;
		ENTRY_DATE = eNTRY_DATE;
		AMOUNT = aMOUNT;
		STATUS = sTATUS;
		NAME = nAME;
	}
	public Integer getAPPLICATION_ID() {
		return APPLICATION_ID;
	}
	public void setAPPLICATION_ID(Integer aPPLICATION_ID) {
		APPLICATION_ID = aPPLICATION_ID;
	}
	public Integer getSUBSCRIPTION_ID() {
		return SUBSCRIPTION_ID;
	}
	public void setSUBSCRIPTION_ID(Integer sUBSCRIPTION_ID) {
		SUBSCRIPTION_ID = sUBSCRIPTION_ID;
	}
	public Integer getPACKAGE_ID() {
		return PACKAGE_ID;
	}
	public void setPACKAGE_ID(Integer pACKAGE_ID) {
		PACKAGE_ID = pACKAGE_ID;
	}
	
	public String getMSISDN() {
		return MSISDN;
	}
	public void setMSISDN(String mSISDN) {
		MSISDN = mSISDN;
	}
	public Date getENTRY_DATE() {
		return ENTRY_DATE;
	}
	public void setENTRY_DATE(Date eNTRY_DATE) {
		ENTRY_DATE = eNTRY_DATE;
	}
	
	public Integer getAMOUNT() {
		return AMOUNT;
	}
	public void setAMOUNT(Integer aMOUNT) {
		AMOUNT = aMOUNT;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	
}
