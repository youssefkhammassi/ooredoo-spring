package com.grokonez.jwtauthentication;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseconnect  {
	 private  String username;
		private String password;
		private	 Statement stmt = null;
		private	 ResultSet res = null;
		private  Connection con = null; 
		
		
			 
	
		
			  DataBaseconnect(String username, String password) throws SQLException, ClassNotFoundException{
				   this.username=username;
				   this.password=password;
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con = DriverManager.getConnection("jdbc:oracle:thin:@172.23.12.235:1521/PMGRACC",username,password);
					System.out.println("connexion établie"); 
					 stmt = (Statement) con.createStatement();
			       
			    }
			  Connection getcon() {
				  return(con);
			  }
			 
			  Statement  getStmt(){
				   return(stmt);
			   }
			   ResultSet getRes(){
				   return(res);
				   
			   }
			   Connection setcon() {
					  return(con);
				  }
			   void setStmt(Statement stmt){
				   this.stmt=stmt;
			   }
			   void setRes(ResultSet res){
				   this.res=res;
			   }

}
