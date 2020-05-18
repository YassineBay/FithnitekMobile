/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
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
    public GUIMyRegistredCard(Form f){
        setTitle("My Card");
        setLayout(new FlowLayout(CENTER));
        Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Image visaImg;
        Card myCard = PayementService.getInstance().getCardDetail(4);
        try {
            visaImg = Image.createImage("/Visa.png");
            visaImg.scale(300, 300);
            ImageViewer visaImgv = new ImageViewer(visaImg);
            Label holderName = new Label(myCard.getNom());
            Label holderCardNumber = new Label("************4242");
            Button goBackHome = new Button("Home");
            goBackHome.addActionListener((e)->{
            new GUIHomePage(f).show();
            });
            c.addAll(visaImgv,holderName,holderCardNumber,goBackHome);
        } catch (IOException ex) {
        }
        add(c);
    }
    
}
