/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Colis;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Yassine
 */
public class ColisService {

    public ArrayList<Colis> colies;
    public static ColisService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ColisService() {
        req = new ConnectionRequest();
    }

    public static ColisService getInstance() {
        if (instance == null) {
            instance = new ColisService();
        }
        return instance;
    }

    public ArrayList<Colis> parseColis(String jsonText) {
        colies = new ArrayList<>();
        JSONParser j = new JSONParser();
        try {
            JSONArray getArray = new JSONArray(jsonText);
            for (int i = 0; i < getArray.length(); i++) {
                Colis colis = new Colis();
                JSONObject colisObject = getArray.getJSONObject(i);
                colis.setId(colisObject.getInt("id"));
                colis.setDepart(colisObject.getString("depart"));
                colis.setDescription(colisObject.getString("description"));
                colis.setDestination(colisObject.getString("destination"));
                colis.setReward(colisObject.getInt("reward"));
                colis.setLabel(colisObject.getString("label"));
                colies.add(colis);
            }
        } catch (JSONException ex) {
        }

        return colies;
    }

    public ArrayList<Colis> getAllColis() {
        String url = "http://127.0.0.1:8000/colis/colis/apishowallcoli";
        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                colies = parseColis(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return colies;
    }

}
