package com.example.dawiduk.conferencevoteapplication;

/**
 * Created by dawiduk on 3-2-16.
 */
public class Presentation {
    private int id;
    private String presentation;
    private String presenter;
    private float start;
    private String desc;
    private String room;
    private Double vote;


    public Presentation(int id,
                        String presentation,
                        String presenter,
                        float start,
                        String desc,
                        String room,
                        Double vote) {

        this.id = id;
        this.presentation = presentation;

        this.presenter = presenter;
        this.start = start;
        this.desc = desc;
        this.room = room;
        this.vote = vote;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public Double getVote() {
        return vote;
    }

    public void setVote(Double vote) {
        this.vote = vote;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getStart() {
        return start;
    }

    public void setStart(float start) {
        this.start = start;
    }

    public String getPresenter() {
        return presenter;
    }

    public void setPresenter(String presenter) {
        this.presenter = presenter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
