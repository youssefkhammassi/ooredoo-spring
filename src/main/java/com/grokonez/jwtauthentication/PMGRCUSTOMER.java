package com.grokonez.jwtauthentication;

import java.sql.Date;

public class PMGRCUSTOMER {
	private String MSISDN;
	private String CUSTOMER_ID;
	private String TM_CODE;
	private String OFFER_TYPE;
	private String C_STATUS;
	private Integer SUBSCRIPTION_ID;
	private Integer PACKAGE_ID;
	private Date SUBSCRIPTION_DATE;
	private String STATUS;
	private String NAME;
	public PMGRCUSTOMER() {}
	public PMGRCUSTOMER(String mSISDN, String cUSTOMER_ID, String tM_CODE, String oFFER_TYPE, String c_STATUS,
			Integer sUBSCRIPTION_ID, Integer pACKAGE_ID, Date sUBSCRIPTION_DATE, String sTATUS, String nAME) {
		super();
		MSISDN = mSISDN;
		CUSTOMER_ID = cUSTOMER_ID;
		TM_CODE = tM_CODE;
		OFFER_TYPE = oFFER_TYPE;
		C_STATUS = c_STATUS;
		SUBSCRIPTION_ID = sUBSCRIPTION_ID;
		PACKAGE_ID = pACKAGE_ID;
		SUBSCRIPTION_DATE = sUBSCRIPTION_DATE;
		STATUS = sTATUS;
		NAME = nAME;
	}
	public String getMSISDN() {
		return MSISDN;
	}
	public void setMSISDN(String mSISDN) {
		MSISDN = mSISDN;
	}
	public String getCUSTOMER_ID() {
		return CUSTOMER_ID;
	}
	public void setCUSTOMER_ID(String cUSTOMER_ID) {
		CUSTOMER_ID = cUSTOMER_ID;
	}
	public String getTM_CODE() {
		return TM_CODE;
	}
	public void setTM_CODE(String tM_CODE) {
		TM_CODE = tM_CODE;
	}
	public String getOFFER_TYPE() {
		return OFFER_TYPE;
	}
	public void setOFFER_TYPE(String oFFER_TYPE) {
		OFFER_TYPE = oFFER_TYPE;
	}
	public String getC_STATUS() {
		return C_STATUS;
	}
	public void setC_STATUS(String c_STATUS) {
		C_STATUS = c_STATUS;
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
	public Date getSUBSCRIPTION_DATE() {
		return SUBSCRIPTION_DATE;
	}
	public void setSUBSCRIPTION_DATE(Date sUBSCRIPTION_DATE) {
		SUBSCRIPTION_DATE = sUBSCRIPTION_DATE;
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
