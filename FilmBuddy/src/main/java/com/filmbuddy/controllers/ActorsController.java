package com.filmbuddy.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Statement;
import java.sql.ResultSet;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.filmbuddy.model.Actor;

@Controller
@RequestMapping({ "/", "/actors" })
public class ActorsController {
    
    
    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;
    
    @GetMapping()
    public String getAllActors(Model model) {
        List<Actor> actors = new ArrayList<Actor>();
        // Code to query the database and
        // add actors to the List will go here
        
        Connection con;
        try {
            con = DriverManager.getConnection(url, username, password);
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM actor");
            
            //access each row in the table
            while (rs.next()) {
                // create a new Actor object
                Actor newActor = new Actor();
                // get the values from each column of the current row and add them to the new Album
                newActor.setActor_id(rs.getInt("actor_id"));
                newActor.setFirst_name(rs.getString("first_name"));
                newActor.setLast_name(rs.getString("last_name"));
                newActor.setLast_update(rs.getTimestamp("last_update"));

                // add the new actor to the actors list
                actors.add(newActor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        

        model.addAttribute("actors", actors);
        return "actors";
    }
}