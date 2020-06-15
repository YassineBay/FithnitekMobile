/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Colis;
import java.util.ArrayList;
import com.mycompany.myapp.entities.Notification;
import com.mycompany.myapp.entities.Utilisateur;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;


/**
 *
 * @author Yassine
 */
public class NotificationService {
    public ArrayList<Notification> notifications;
    public static NotificationService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public NotificationService() {
        req = new ConnectionRequest();
    }
    
     public static NotificationService getInstance() {
        if (instance == null) {
            instance = new NotificationService();
        }
        return instance;
    }
     
     public ArrayList<Notification> parseNotifications(String jsonText){
        try {
            notifications=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("result");
            for(Map<String,Object> obj : list){
                Notification n = new Notification();
                n.setContent(obj.get("content").toString());
                n.setId(obj.get("id").toString());
                JSONObject jo = new JSONObject(obj);
                try {
                    JSONObject livreur = jo.getJSONObject("idlivreur");
                    
                    n.setSender(livreur.getString("username"));
                    JSONObject colis = jo.getJSONObject("idcolis");
                    Colis c = new Colis(colis.getInt("id"), colis.getString("depart"), colis.getString("destination"),
                            colis.getString("date_limit"), colis.getString("label"), colis.getString("description"), colis.getDouble("reward") ,colis.getString("image"));
                    c.setReward(colis.getDouble("reward"));
                    n.setColis(c);
                    System.out.println(n.getSender());
                    System.out.println(colis.getDouble("reward"));
                } catch (JSONException ex) {
                }

                
                notifications.add(n);
            }
        } catch (IOException ex) {
            
        }
        return notifications;
    }
     
     public ArrayList<Notification> getAllNotifications(int id){
        String url = Statics.BASE_URL+"notifications?idpropc="+id;
        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println(req.getResponseData().toString());
                notifications = parseNotifications(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return notifications;
    }
     
     public Colis getNotificationColis(){
         return null;
     }
     
     public boolean deleteNotif(Notification n){
        String url ="http://127.0.0.1:8000/api/deleteNotif?IDNotif="+n.getId();
        System.out.println(url);
        req.setPost(true);
        req.setUrl(url);
        //req.addArgument("first_name", c.getNom());
        //req.addArgument("last_name", c.getPrenom());
        //req.addArgument("cardnumber", c.getCardNumber());
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
           
       }
    
    

}
