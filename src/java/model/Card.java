/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LENOVO
 */
public class Card {
    private int id;
    private String title;
    private String describe;
    private int studySetId;

    public Card() {
    }

    public Card(int id, String title, String describe, int studySetId) {
        this.id = id;
        this.title = title;
        this.describe = describe;
        this.studySetId = studySetId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getStudySetId() {
        return studySetId;
    }

    public void setStudySetId(int studySetId) {
        this.studySetId = studySetId;
    }
    
    
}
