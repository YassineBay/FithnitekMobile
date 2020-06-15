/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.db.Database;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.myapp.entities.Card;
import com.mycompany.myapp.services.PayementService;
import java.io.IOException;

/**
 *
 * @author Yassine
 */
public class GUIMyRegistredCard extends Form {
    Database db;
    boolean created;
    public GUIMyRegistredCard(Form f){
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
        setTitle("My Card");
        setLayout(new FlowLayout(CENTER));
        Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Image visaImg;
        Card myCard = PayementService.getInstance().getCardDetail(1);
        try {
            visaImg = Image.createImage("/Visa.png");
            visaImg.scale(300, 300);
            ImageViewer visaImgv = new ImageViewer(visaImg);
            Label holderName = new Label(myCard.getNom());
            Label holderCardNumber = new Label("************4242");
            Button goBackHome = new Button("Home");
            Button modifyCard = new Button("Modify My Card");
            modifyCard.addActionListener((e)->{
                GUIModifyCard.cardName = myCard.getNom();
                GUIModifyCard ModifyCardForm = new GUIModifyCard(f);
                ModifyCardForm.show();
            });
            Button deleteCard = new Button("Delete My Card");
            deleteCard.addActionListener((e)->{
            PayementService.getInstance().DeleteCard(1);
            //Local DB changing
            try {
                db.execute("UPDATE CardDetail SET isRegistred =0");
                Dialog.show("Success", "Your card has been deleted","OK",null);
                new GUIHomePage(f).show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            } 
            
            });
                  goBackHome.addActionListener((e)->{
            new GUIHomePage(f).show();
            });
            c.addAll(visaImgv,holderName,holderCardNumber,modifyCard,deleteCard,goBackHome);
        } catch (IOException ex) {
        }
       
        addAll(c);
    }
    
}
