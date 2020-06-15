/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Card;
import com.mycompany.myapp.entities.Colis;
import com.mycompany.myapp.entities.Utilisateur;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Yassine
 */
public class PayementService {
        
    public ArrayList<Card> Cards;
    public static PayementService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
      public PayementService() {
        req = new ConnectionRequest();
    }
    
      public static PayementService getInstance() {
        if (instance == null) {
            instance = new PayementService();
        }
        return instance;
    }
        
        public boolean addCard(Card c ,int id) {
            
        String url = "http://127.0.0.1:8000/payement/api/Mycard?first_name="+c.getNom()+"&last_name="+c.getPrenom()+"&id="+id;
        //String url = "http://127.0.0.1:8000/payement/api/Mycard";
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
        
        public boolean ModifyCard(Card c ,int id) {    
        String url = "http://127.0.0.1:8000/payement/api/UpdateCard?HolderName="+c.getNom()+"&id="+id;
        //String url = "http://127.0.0.1:8000/payement/api/Mycard";
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
        
        
        public Card getCardDetail(int id){
            Card card = new Card();
            String url = "http://127.0.0.1:8000/payement/api/MyCardDetail?id="+id;
            req.setUrl(url);
            req.setPost(false);
            req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                try {
                    JSONObject obj = new JSONObject(new String(req.getResponseData()));
                    System.out.println(new String(req.getResponseData()));
                    String holderName = obj.getString("name");
                    card.setNom(holderName);
                    req.removeResponseListener(this);
                } catch (JSONException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return card;
                    }
        
            public boolean DeleteCard(int id) {    
        String url = "http://127.0.0.1:8000/payement/api/DeleteCard?id="+id;
        //String url = "http://127.0.0.1:8000/payement/api/Mycard";
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
            
            
       public boolean createPayement(Colis c , Utilisateur u){
           String url = "http://127.0.0.1:8000/payement/api/createCharge?idColis="+c.getId()+"&IdUSer="+u.getId();
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
