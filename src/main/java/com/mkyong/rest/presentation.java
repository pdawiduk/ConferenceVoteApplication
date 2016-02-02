/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mkyong.rest;

/**
 *
 * @author dawiduk
 */
public class presentation {

    int id;
    String presentation;
    String presenter;
    float start;
    String desc;
    String room;
    Double vote;

    public presentation(int id,
            String presentation,
            String presenter,
            float start,
            String desc,
            String room,
            Double vote) {
        
            this.id=id;
            this.presentation=presentation ; 
            this.presenter=presenter ;
            this.start=start ;
            this.desc =desc;
            this.room =room;
            this.vote =vote;
    }
}
