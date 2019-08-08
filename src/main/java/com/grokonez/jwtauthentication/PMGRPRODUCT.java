package com.grokonez.jwtauthentication;

import java.sql.*;
import java.sql.Date;





public class PMGRPRODUCT {

	
	private Integer PRODUCT_ID;
	private String NAME;
	private String DESCRIPTION;
	private Date CREATION_DATE;
	private Date LAST_MODIFICATION_DATE;
	private String STATUS;
	
	public PMGRPRODUCT() {
		
	}

	public PMGRPRODUCT(Integer pRODUCT_ID, String nAME, String dESCRIPTION, Date cREATION_DATE,
			Date lAST_MODIFICATION_DATE, String sTATUS) {
		super();
		PRODUCT_ID = pRODUCT_ID;
		NAME = nAME;
		DESCRIPTION = dESCRIPTION;
		CREATION_DATE = cREATION_DATE;
		LAST_MODIFICATION_DATE = lAST_MODIFICATION_DATE;
		STATUS = sTATUS;
	}

	public Integer getPRODUCT_ID() {
		return PRODUCT_ID;
	}

	public void setPRODUCT_ID(Integer pRODUCT_ID) {
		PRODUCT_ID = pRODUCT_ID;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	public String getDESCRIPTION() {
		return DESCRIPTION;
	}

	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}

	public Date getCREATION_DATE() {
		return CREATION_DATE;
	}

	public void setCREATION_DATE(Date cREATION_DATE) {
		CREATION_DATE = cREATION_DATE;
	}

	public Date getLAST_MODIFICATION_DATE() {
		return LAST_MODIFICATION_DATE;
	}

	public void setLAST_MODIFICATION_DATE(Date lAST_MODIFICATION_DATE) {
		LAST_MODIFICATION_DATE = lAST_MODIFICATION_DATE;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}




	}
	
	
	
	
	
	
	
	
