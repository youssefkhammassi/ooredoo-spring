package com.grokonez.jwtauthentication;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
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
public class GreetingController1 {
	
	static  Connection con = null;
	static  Statement st = null;
	static  ResultSet rs = null;
	

   
    @GetMapping("/pmgrC/{id}")
    public List<PMGRCUSTOMER> test(@PathVariable Long id){ 
    
    	try{
    		
    		 
    		
			  DataBaseconnect Connection =new DataBaseconnect("PMGR","pmgr123");
			  con=Connection.getcon();
		       st = Connection.getStmt();
		       rs = st.executeQuery("select * from PMGR_CUSTOMER CUST , PMGR_SUBSCRIPTION SUB , PMGR_PACKAGE PACK WHERE CUST.CUSTOMER_ID= SUB.CUSTOMER_ID AND SUB.PACKAGE_ID=PACK.PACKAGE_ID AND CUST.MSISDN='"+id+"'");
		 
		      
		       ResultSetMetaData rsmtd = rs.getMetaData();
		       
		       List <PMGRCUSTOMER> listePmgrC =new ArrayList();
		       
		      
		    
		       while(rs.next())
		    	  
		       {
		    	   PMGRCUSTOMER pmgrC = new PMGRCUSTOMER();
		    	  pmgrC.setSUBSCRIPTION_ID(rs.getInt("SUBSCRIPTION_ID"));
		    	   pmgrC.setPACKAGE_ID(rs.getInt("PACKAGE_ID"));
		    	   pmgrC.setSUBSCRIPTION_DATE(rs.getDate("SUBSCRIPTION_DATE"));
		    	   pmgrC.setSTATUS(rs.getString("STATUS"));
		    	   pmgrC.setNAME(rs.getNString("NAME"));
		    	  pmgrC.setMSISDN(rs.getString("MSISDN"));
		    	   pmgrC.setCUSTOMER_ID(rs.getString("CUSTOMER_ID"));
		    	   pmgrC.setTM_CODE(rs.getString("TM_CODE"));
		    	   if(rs.getString("STATUS").equals("E")) {
						pmgrC.setSTATUS("Enable");
					}
			  if(rs.getString("STATUS").equals("D")) {
						pmgrC.setSTATUS("Disabled");
					}
				if(rs.getString("STATUS").equals("H")) {
						pmgrC.setSTATUS("On Hold");
					}
		    	  
				  listePmgrC.add(pmgrC);
				 
		       }
		    	   
		       return listePmgrC ;
		       
		               
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


	private Object getDate() {
		// TODO Auto-generated method stub
		return null;
	}
}

