/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author wissal
 */
@ManagedBean
@SessionScoped
public class ShowMyListingsController implements Serializable{

    @ManagedProperty(value="#{loginController}")
    private LoginController login;
//    private int id;
    private List<House> MyListings = new ArrayList<>(); 
    
    public ShowMyListingsController() {
    }
    
    public void myListings(){
            System.out.println("user id how posted this post --> "+login.getId());
            
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public LoginController getLogin() {
        return login;
    }

    public void setLogin(LoginController login) {
        this.login = login;
    }

    
    
//    public LoginController getLogin() {
//        return login;
//    }
//
//    public void setLogin(LoginController login) {
//        this.login = login;
//    }

    public List<House> getMyListings() {
        return MyListings;
    }

    public void setMyListings(List<House> MyListings) {
        this.MyListings = MyListings;
    }

    
}
