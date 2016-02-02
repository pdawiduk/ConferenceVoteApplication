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
    Connection conn = null;
    Statement stmt = null;

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";
    StringBuffer strJson = new StringBuffer();
    

    @GET
    @Path("/show")
    @Produces(MediaType.APPLICATION_JSON)

    public Response getMsg() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");

        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt = conn.createStatement();
        stmt.execute("USE presentations");
        String query = "SELECT * FROM PRESENTATIONS;";
        
        ResultSet rs = stmt.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();

        while (rs.next()) {

            int id = rs.getInt("id");
            String presentation = rs.getString("presentation");
            String presenter = rs.getString("presenter");
            Float start = rs.getFloat("start");
            String desc = rs.getString("description");
            String room = rs.getString("room");

            strJson.append('"' + '{' + '"' + "presentations:[" + '"');
            strJson.append("{");
            strJson.append('"' + "id" + '"' + '"' + id + '"');
            strJson.append('"' + "presentation" + '"' + '"' + presentation + '"');
            strJson.append('"' + "presenter" + '"' + '"' + presenter + '"');
            strJson.append('"' + "start" + '"' + '"' + start + '"');
            strJson.append('"' + "description" + '"' + '"' + desc + '"');
            strJson.append('"' + "room" + '"' + '"' + room + '"');

            if (!rs.next()) {
                strJson.append(',');
            }

        }

        strJson.append("]}");
        rs.close();
        return Response.status(200).entity(strJson.toString()).build();

    }
}
