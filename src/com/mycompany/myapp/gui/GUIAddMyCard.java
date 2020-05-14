/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

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

/**
 *
 * @author Yassine
 */
public class GUIAddMyCard extends Form {
    
    public GUIAddMyCard(Form f){
        
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
        Card card = new Card(nameLbl, nameLbl, cardNumberLbl);
        //new USer
        if(PayementService.getInstance().addCard(card,1)){
        Dialog.show("Success", "Your card has been added","OK",null);
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
