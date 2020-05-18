/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;
import java.io.IOException;

/**
 *
 * @author Yassine
 */
public class GUIHomePage extends Form {
    Database db;
    boolean created;
    
    public GUIHomePage(Form f){
        created = db.exists("MyDB.db");        
        try {
            db = Database.openOrCreate("MyDB.db");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        setTitle("Register Card");
        setLayout(new FlowLayout());
        Button myCardBtn = new Button("My Card");
        myCardBtn.addActionListener((e)->{
            try {
                Cursor c =  db.executeQuery("Select * from CardDetail");
                while(c.next()){
                    Row row = c.getRow();
                    if (row.getInteger(1)==1){
                        new GUIMyRegistredCard(f).show();
                    }else{
                        new GUIAddMyCard(f).show();
                    }
                }
            } catch (IOException ex) {
            }
        
        });
        Button myNotificationsBtn = new Button("My Notifications");
        myNotificationsBtn.addActionListener((e)->{        
        new GUINotifications(f).show();
        });
        addAll(myCardBtn,myNotificationsBtn);
    }
    
    
}
