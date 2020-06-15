/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import java.io.IOException;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.URLImage;
import com.mycompany.myapp.entities.Notification;
import com.mycompany.myapp.entities.Utilisateur;
import com.mycompany.myapp.services.NotificationService;
import com.mycompany.myapp.services.PayementService;
import com.mycompany.myapp.utils.Statics;



/**
 *
 * @author Yassine
 */
public class GUINotifications extends Form {
        private ConnectionRequest req;
        EncodedImage ec ;
        Image imgFromServer = null ;
        ImageViewer  ivFromServer = null ;

 
    public GUINotifications(Form f) {
            setTitle("Notifications");
            setLayout(BoxLayout.y());
            Utilisateur testUser = new Utilisateur();
            testUser.setId(1);
            for(Notification n :NotificationService.getInstance().getAllNotifications(testUser.getId())){           
            Container con = new Container(new FlowLayout());
            Image imageNotif = null;
        try {
            imageNotif = Image.createImage("/notification.png");
            imageNotif.scale(150, 150);
        } catch (IOException ex) {
        }
            ImageViewer ivNotif = new ImageViewer(imageNotif);
            Label txtNotifContent = new Label("You have a new notification from "+n.getSender());
            Button bSendMessage = new Button("Send Message");
            Button bShowDetails = new Button("Show Details");
            bShowDetails.addActionListener((e)->{
                Form detailColisForm = new Form(BoxLayout.y());
                                System.out.println(n.getColis().getImage());
                String url ="http://localhost/MobileImages/"+n.getColis().getImage()+".jpg";
                System.out.println(url);
                try {
                    ec = EncodedImage.create("/download.png");
                    imgFromServer = URLImage.createToStorage(ec, url, url,URLImage.RESIZE_SCALE);
                    ivFromServer = new ImageViewer(imgFromServer);
                    detailColisForm.add(ivFromServer);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                Label colisNameTxt = new Label("Package Name : ");
                colisNameTxt.getStyle().setFgColor(0x000000);
                Label colisDepartTxt = new Label("From : ");
                colisDepartTxt.getStyle().setFgColor(0x000000);
                Label colisDestinationTxt = new Label("To : ");
                colisDestinationTxt.getStyle().setFgColor(0x000000);
                Label colisDateLimitTxt = new Label("Delevery Date Limit: ");
                colisDateLimitTxt.getStyle().setFgColor(0x000000);
                Label colisRewardTxt = new Label("Reward : ");
                colisRewardTxt.getStyle().setFgColor(0x000000);

                Label colisName = new Label(n.getColis().getLabel());
                Label colisDepart = new Label(n.getColis().getDepart());
                Label colisDestination = new Label(n.getColis().getDestination());
                Label colisDateLimit = new Label(n.getColis().getDate_limit());
                Label colisReward = new Label(""+n.getColis().getReward());
                Button btnGoBack = new Button("Go back");
                Button payNow = new Button("Accept The Payement");
                payNow.addActionListener((evt)->{
                if(PayementService.getInstance().createPayement(n.getColis(), testUser)){
                    Dialog.show("Success", "Your payement have been delivered", "Ok", null);
                    NotificationService.getInstance().deleteNotif(n);
                    new GUIHomePage(f).show();
                }else {
                    Dialog.show("Denied", "Failed To Pay", "Ok", null);
                }
                });
                btnGoBack.addActionListener((event)->{
                    show();
                });
                detailColisForm.addAll(
                        colisNameTxt,colisName,
                        colisDepartTxt,colisDepart,
                        colisDestinationTxt,colisDestination,
                        colisDateLimitTxt,colisDateLimit,
                        colisRewardTxt,colisReward,
                        payNow,
                        btnGoBack);
                detailColisForm.show();
            });
             
           
            con.addAll(ivNotif,txtNotifContent,bShowDetails);
             add(con);
            }
      Button goBackHome = new Button("Home");
            goBackHome.addActionListener((e)->{
            new GUIHomePage(f).show();
            });
            add(goBackHome);

            
    }

    public GUINotifications() {
    }
    }
        


