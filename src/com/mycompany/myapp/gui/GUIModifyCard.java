/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.facebook.User;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.myapp.entities.Card;
import com.mycompany.myapp.entities.Utilisateur;
import com.mycompany.myapp.services.PayementService;

/**
 *
 * @author Yassine
 */
public class GUIModifyCard extends Form{
    public static String cardName;
    
    public GUIModifyCard(Form f){
        
        setTitle("Modify Card");
        setLayout(BoxLayout.y());
        
        Label CardNameLbl = new Label("Card Holder Name : ");
        CardNameLbl.getStyle().setFgColor(0x000000);
        Label cardNumberLbl = new Label("Card Number : ");
        cardNumberLbl.getStyle().setFgColor(0x000000);
        Label dateLbl = new Label("Card Expiration Date : ");
        dateLbl.getStyle().setFgColor(0x000000);
        Label cvcLbl = new Label("Card Expiration Date : ");
        cvcLbl.getStyle().setFgColor(0x000000);
        
        TextField CardNameTxtF = new TextField(getCardName());
        TextField cardNumberTxtF = new TextField();
        TextField dateTxtF = new TextField();
        TextField cvcTxtF = new TextField();
        Container btnContainer = new Container(new FlowLayout());
        Button saveBtn = new Button("Save Card");
        saveBtn.addActionListener((e)->{
            Utilisateur user = new Utilisateur();
            Card card = new Card();
            card.setNom(CardNameTxtF.getText());
            user.setId(1);
            PayementService.getInstance().ModifyCard(card, user.getId());
           Dialog.show("Success", "Your card has been deleted","OK",null);
            new GUIMyRegistredCard(f).show();
        });
        Button goBackBtn = new Button("Go Back");
        goBackBtn.addActionListener((e)->{
        new GUIMyRegistredCard(f).show();
        });
        btnContainer.addAll(saveBtn,goBackBtn);
        addAll(CardNameLbl,CardNameTxtF,cardNumberLbl,cardNumberTxtF,dateLbl,dateTxtF,cvcLbl,cvcTxtF,btnContainer);

    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

   
    
}
