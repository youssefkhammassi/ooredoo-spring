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
public class greetingcontrollerall {
	
	static  Connection con = null;
	static  Statement st = null;
	static  ResultSet rs = null;
	static  ResultSet RS = null;

   
    @GetMapping("/pmgr/")
    public List<PMGRPRODUCT> test(){ 
    
    	try{
    		
    		 
    		
			  DataBaseconnect Connection =new DataBaseconnect("PMGR","pmgr123");
			  con=Connection.getcon();
		       st = Connection.getStmt();
		       rs = st.executeQuery("select * from PMGR_PRODUCT PROD,PMGR_PACKAGE PACK WHERE PROD.PRODUCT_ID=PACK.PACKAGE_ID ");
		 
		      
		       ResultSetMetaData rsmt = rs.getMetaData();
		       
		       List <PMGRPRODUCT> listePmgr =new ArrayList();
		       
		      
		    
		       while(rs.next())
		    	  
		       {
		    	   PMGRPRODUCT pmgr = new   PMGRPRODUCT();
		    	  
		    	  
		    	  pmgr.setPRODUCT_ID(rs.getInt("PRODUCT_ID"));
		    	  pmgr.setNAME(rs.getString("NAME"));
		    	  pmgr.setDESCRIPTION(rs.getString("DESCRIPTION"));
		   	 
				  
				  pmgr.setCREATION_DATE(rs.getDate("CREATION_DATE"));
				  pmgr.setLAST_MODIFICATION_DATE(rs.getDate("LAST_MODIFICATION_DATE"));
				  if(rs.getString("STATUS").equals("E")) {
						pmgr.setSTATUS("Enable");
					}
			  if(rs.getString("STATUS").equals("D")) {
						pmgr.setSTATUS("Disabled");
					}
				if(rs.getString("STATUS").equals("H")) {
						pmgr.setSTATUS("On Hold");
					}
				  
				  
				  listePmgr.add(pmgr);
				 
				   
		         
		       }
		    	   
		       return listePmgr;
		       
		               
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

	



	
