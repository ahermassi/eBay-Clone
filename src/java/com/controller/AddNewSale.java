/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import com.DbConnection.ConnectionDB;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author wissal
 */
@ManagedBean
@SessionScoped
public class AddNewSale {
ConnectionDB cnxDB ;
private String description;
private String location ; 
private Double price;
private String address;
private String photo;
private Date finalDate;
private int category;
private String purchaseType;



    public AddNewSale() {
        this.cnxDB = new ConnectionDB();
    }
    
    public void addNew() throws SQLException, ParseException
    {    String req="";
       
        System.out.println("*******************************");
        System.out.println("addd new Description-->"+getDescription());
        System.out.println("addd new location-->"+getLocation());
        System.out.println("addd new address-->"+getAddress());
        System.out.println("addd new photo-->"+getPhoto());
        System.out.println("addd new price-->"+getPrice());
        System.out.println("addd new date-->"+getFinalDate());
        System.out.println("addd new category -->"+getCategory());
        System.out.println("addd new purchase Type-->"+getPurchaseType());
        
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String Time = sdf.format(getFinalDate());
        if("bid".equals(getPurchaseType())){
                req ="insert into house(description, location, price, category, typeAchat, address, photo, finalDate) values "
                        + "('"+getDescription()+"', '"+getLocation()+"', "+getPrice()+", "+getCategory()+", '"+getPurchaseType()+"', '"+getAddress()+"', '"+getPhoto()+"', null) ;";
        }else{
                req ="insert into house(description, location, price, category, typeAchat, address, photo, finalDate) values "
                        + "('"+getDescription()+"', '"+getLocation()+"', "+getPrice()+", "+getCategory()+", '"+getPurchaseType()+"', '"+getAddress()+"', '"+getPhoto()+"', '"+Time+"') ;";
        }
         try{ 
           Connection cnx = cnxDB.connect();
           Statement st = cnx.createStatement();
           int count = st.executeUpdate(req);
           
        }catch(Exception e){
            e.printStackTrace();
           
        }    
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) throws ParseException {
//        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//        Date date2 = formatter.parse(formatter.format(finalDate));
                this.finalDate= finalDate;
    }

   
    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

   
    
    
}
