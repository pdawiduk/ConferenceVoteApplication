package com.mkyong.rest;
 import java.sql.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
 
@Path("/presentation")
public class PresentationWebService {
    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/presentations";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";
 
	@GET
        @Path("/show")
        @Produces(MediaType.APPLICATION_JSON)
	
	public Response getMsg() throws SQLException {
            
                String query = "SELECT * FROM PRESENTATIONS ORDER BY room DESC";
                        Statement stmt = null;
                        ResultSet rs = stmt.executeQuery(query);
                        ResultSetMetaData rsmd = rs.getMetaData();
                        StringBuffer strJson = null;
                        
                        while(rs.next()){
                            //Retrieve by column name
                            int id  = rs.getInt("id");
                            String presentation =rs.getString("presentation");
                            String presenter = rs.getString("presenter");
                            Float start = rs.getFloat("start");
                            String desc = rs.getString("desription");
                            int room =rs.getInt("room");

                            
                            //Display values
                            strJson.append('"'+'{'+'"'+"presentations:["+'"');
                            strJson.append("{");
                            strJson.append('"'+ "id"+'"'+ '"'+id+'"');
                            strJson.append('"'+ "presentation"+'"'+ '"'+presentation+'"');
                            strJson.append('"'+ "presenter"+'"'+ '"'+presenter+'"');
                            strJson.append('"'+ "start"+'"'+ '"'+start+'"');
                            strJson.append('"'+ "description"+'"'+ '"'+desc+'"');
                            strJson.append('"'+ "room"+'"'+ '"'+room+'"');
                            strJson.append('"'+ "start"+'"'+ '"'+start+'"');
                            if(!rs.next())
                            strJson.append(',');
                               
                        }
                        

                        strJson.append("]}");
                        rs.close();
 
		
 
		return Response.status(200).entity(strJson.toString()).build();
 
	}
 
}