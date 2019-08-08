package com.grokonez.jwtauthentication;

import java.sql.Connection;
import java.util.Date;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.text.SimpleDateFormat;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="*",maxAge=3600)

@RestController
public class GreetingController2 {
	
	
	static  Connection con = null;
	static  Statement st = null;
	static  ResultSet rs = null;
	static  ResultSet RS = null;
	Date Date_Debut=null;
	Date Date_Fin= null;
	
public String dateformat(String datetest) throws ParseException {
		String date_s = datetest;

        // *** note that it's "yyyy-MM-dd hh:mm:ss" not "yyyy-mm-dd hh:mm:ss"  
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = (Date) dt.parse(date_s);

        // *** same for the format String below
        SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy");

		return dt1.format(date);
		
	}
public static java.util.Date convertFromSQLDateToJAVADate(
        java.sql.Date ENTRY_DATE) {
    java.util.Date javaDate = null;
    Date entryDate = null;
	if (ENTRY_DATE != null) {
        entryDate = new Date(ENTRY_DATE.getTime());
    }
    return entryDate;
}

   
    @GetMapping("/pmgrA/{id}/{datedebut}/{datefin}")
    public List<PMGRAPPLICATION> test(@PathVariable long id,@PathVariable String datedebut,@PathVariable String datefin) throws ParseException { 
    
    	try{
    		
    		 
    		
			  DataBaseconnect Connection =new DataBaseconnect("PMGR","pmgr123");
			  con=Connection.getcon();
		       st = Connection.getStmt();
		       rs = st.executeQuery("select * from  PMGR_CUSTOMER CUST,PMGR_APPLICATION APP ,PMGR_PACKAGE PACK ,PMGR_SUBSCRIPTION SUB WHERE   PACK.PACKAGE_ID=SUB.PACKAGE_ID AND SUB.SUBSCRIPTION_ID=APP.SUBSCRIPTION_ID " );
		 
		      
		       ResultSetMetaData rsmt = rs.getMetaData();
		       
		       List <PMGRAPPLICATION> listePmgrA =new ArrayList();
		       
		      List   DateFiltrage =new ArrayList();
		      
		       while(rs.next())
		    	  
		       {
		    	   PMGRAPPLICATION pmgrA = new   PMGRAPPLICATION();
		    	  
		    	pmgrA.setMSISDN(rs.getString("MSISDN"));
		    	pmgrA.setAPPLICATION_ID(rs.getInt("APPLICATION_ID"));
		    	 // pmgrA.setSUBSCRIPTION_ID (rs.getInt("SUBSCRIPTION_ID "));
		    	  pmgrA.setPACKAGE_ID(rs.getInt("PACKAGE_ID"));
		   	 
		    	 
		   
		    	  pmgrA.setENTRY_DATE(rs.getDate("ENTRY_DATE"));
				  pmgrA.setAMOUNT(rs.getInt("AMOUNT"));
				  pmgrA.setNAME(rs.getString("NAME"));
				  if(rs.getString("STATUS").equals("S")) {
						pmgrA.setSTATUS("SUCCESS");
					}
			  if(rs.getString("STATUS").equals("C")) {
						pmgrA.setSTATUS("CANCELED");
					}
				if(rs.getString("STATUS").equals("F")) {
						pmgrA.setSTATUS("FAILED ");
					}
				if(rs.getString("STATUS").equals("A")) {
					pmgrA.setSTATUS(" ABORT  ");
				}
				listePmgrA.add(pmgrA);
				   
		         
		       }
		    	   


		        Date a = new Date(dateformat(datedebut));  
		        Date b = new Date(dateformat(datefin));
		      for (int i=0;i<listePmgrA.size();i++) {
		    	
		    	  java.util.Date d =(rs.getDate("entry_DATE"));
		    	  if((a.compareTo(d) * d.compareTo(b) > 0)==true) {
		    		  DateFiltrage.add(d);
		    	  }
		    	
		      }
		      return DateFiltrage;
		               
		   }catch(Exception e){
			   System.out.println(e);
			   return null;
		   }finally{
		       try{
		       st.close();
		       rs.close();
		       con.close();
		       }catch(Exception e){
		           System.out.println(e);
		           return null;
		       }
		   }
		
		
    	 
		
    	
  
    }


	private Object lenght(List<PMGRAPPLICATION> listePmgrA) {
		// TODO Auto-generated method stub
		return null;
	}


	private Object getDate() {
		// TODO Auto-generated method stub
		return null;
	}
}

	



	