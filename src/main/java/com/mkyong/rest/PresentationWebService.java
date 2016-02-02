package com.mkyong.rest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
    List<presentation> presentList= new ArrayList<presentation> ();

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";

    JsonObject albums = new JsonObject();
    JsonArray datasets = new JsonArray();
    JsonObject dataset = new JsonObject();

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
             presentList.add(new presentation(rs.getInt("id"),
            rs.getString("presentation"),
            rs.getString("presenter"),
            rs.getFloat("start"),
            rs.getString("description"),
            rs.getString("room"),
            rs.getDouble("vote")));
       
        }

        rs.close();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
        return Response.status(200).entity(gson.toJson(presentList)).build();

    }
}
