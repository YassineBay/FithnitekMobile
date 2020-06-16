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
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Colis;
import com.mycompany.myapp.entities.Notification;
import com.mycompany.myapp.services.ColisService;
import com.mycompany.myapp.services.NotificationService;

/**
 *
 * @author Yassine
 */
public class GUIListColis extends Form {

    public GUIListColis(Form f) {
        setTitle("Packages");
        setLayout(BoxLayout.y());

        for (Colis colis : ColisService.getInstance().getAllColis()) {
            Label departLbl = new Label(colis.getDepart());
            Button requestPackage = new Button("Request Package Delevery");
            requestPackage.addActionListener((e) -> {
                Notification notification = new Notification();
                notification.setColis(colis);
                if (NotificationService.getInstance().createNotification(notification, 1)) {
                    Dialog.show("Sucess", "Please wait for the payement", "OK", null);
                }
            });
            addAll(departLbl, requestPackage);

        }
    }

}
