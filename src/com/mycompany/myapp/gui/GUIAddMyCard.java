/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.db.Database;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridBagLayout;
import com.codename1.ui.layouts.GridLayout;
import com.mycompany.myapp.entities.Card;
import com.mycompany.myapp.services.PayementService;
import java.io.IOException;

/**
 *
 * @author Yassine
 */
public class GUIAddMyCard extends Form {
    
    Database db;
    boolean created;
    
    public GUIAddMyCard(Form f){
        /* try {
            Database.delete("Mydb.db");
        } catch (IOException ex) {
        }>>>>>>>>>>>>>>> IN CASE WHENE DATABASE OR TABLE DOES NOT EXIST WE SHOULD ADD THIS BLOCK EXECUTE IT ONCE THEN COMMENT IT*/
        created = db.exists("MyDB.db");
        
        try {
            db = Database.openOrCreate("MyDB.db");
            if(!created){
                db.execute("create table CardDetail (id INTEGER ,isRegistred )");
                db.execute("INSERT INTO CardDetail (isRegistred) VALUES ('0')");
                System.out.println("Database created !");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        setTitle("Register Card");
        setLayout(new FlowLayout());
        Label firstNameTxt = new Label("First Name : ");
        firstNameTxt.getStyle().setFgColor(0x000000);
        Label lastNameTxt = new Label("Last Name : ");
        lastNameTxt.getStyle().setFgColor(0x000000);
        Label cardNumberTxt = new Label("Card Number : ");
        cardNumberTxt.getStyle().setFgColor(0x000000);
        
        TextField firstNametxtF = new TextField();
        TextField lastNametxtF = new TextField();
        TextField cardNumbertxtF = new TextField();
        
        Button submit = new Button("Add Card");
        submit.addActionListener((e)->{
        
        String nameLbl = firstNametxtF.getText();
        String lNameLbl = lastNametxtF.getText();
        String cardNumberLbl = cardNumbertxtF.getText();
        Card card = new Card(nameLbl, lNameLbl, cardNumberLbl);
        //new USer
        if(PayementService.getInstance().addCard(card,1)){
        Dialog.show("Success", "Your card has been added","OK",null);
           try {
                db.execute("UPDATE CardDetail SET isRegistred =1");
                new GUIHomePage(f).show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            } 
        }
        else{
          Dialog.show("Error", "Your card has not been added","OK",null);

        }
        });
        addAll(firstNameTxt,firstNametxtF,
                lastNameTxt,lastNametxtF,
                cardNumberTxt,cardNumbertxtF ,submit);
        

             

    }
    
}
