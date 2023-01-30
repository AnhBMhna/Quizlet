/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dal.DAO;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class StudySet {
    private int id;
    private String title;
    private String describe;
    private boolean isShare;
    private int folderId;
    private int userId;

    public StudySet() {
    }

    public StudySet(int id, String title, String describe, boolean isShare, int folderId, int userId) {
        this.id = id;
        this.title = title;
        this.describe = describe;
        this.isShare = isShare;
        this.folderId = folderId;
        this.userId = userId;
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

    public boolean isIsShare() {
        return isShare;
    }

    public void setIsShare(boolean isShare) {
        this.isShare = isShare;
    }

    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public int getNumberCard() {
        DAO d = new DAO();
        ArrayList<Card> listC = d.getAllCardInSet(id);
        return listC.size();
    }
    
    public String getAuthor() {
        DAO d = new DAO();
        User user = d.getUserByUserId(userId);
        String name = user.getName();
        return name;
    }
}
