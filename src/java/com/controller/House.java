/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.DbConnection.Queries;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author wissal
 */
@ManagedBean(name="house")
@SessionScoped
public class House implements Serializable {
    // private List<House> dataList;
    private int id ;
    private String description; 
    private String location;
    private int category;
    private double price;
    private String photo;
    private String typeAchat;
    private Date finalDate;
    private String leftDays;
    private int userPostId;
    private double topPrice;

    
    
    
    public String modifyTopBidPrice()
    {   Queries query=new Queries();
        FacesContext fc = FacesContext.getCurrentInstance();
        
        int id =Integer.parseInt(getIdParam(fc));   
        System.out.println("idddddd"+id);
        
        double realPrice =Double.parseDouble(getPriceParam(fc));
        System.out.println(" price "+realPrice);
        
        double bidPrice =Double.parseDouble(getTopPriceParam(fc));
        System.out.println("bid price "+bidPrice);
        
        
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        double newBidPrice= Double.parseDouble(request.getParameter("txtAnotherProperty"));
        System.out.println("toooppp "+newBidPrice );
        
        
        if(newBidPrice > bidPrice && newBidPrice < realPrice)
        query.modifyPriceById(id,newBidPrice);
        
        return "showOffers.xhtml?faces-redirect-true";
    }
    //get value from "f:param" the id of the house 
    public String getIdParam(FacesContext fc){

            Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
            return params.get("id");
    }     
     //get value from "f:param"  the bidPrice of the house 
    public String getTopPriceParam(FacesContext fc){

            Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
            return params.get("bidPrice");
    } 
    //get value from "f:param"  the price of the house 
    public String getPriceParam(FacesContext fc){

            Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
            return params.get("price");
    } 
    public double getTopPrice() {
        return topPrice;
    }

    public void setTopPrice(double topPrice) {
        this.topPrice = topPrice;
    }

    
    public String getLeftDays() {
        return leftDays;
    }

    public void setLeftDays(String leftDays) {
        this.leftDays = leftDays;
    }
    
    public String getTypeAchat() {
        return typeAchat;
    }

    public void setTypeAchat(String typeAchat) {
        this.typeAchat = typeAchat;
    }

    
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    

    public int getId() {
        return id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserPostId() {
        return userPostId;
    }

    public void setUserPostId(int userPostId) {
        this.userPostId = userPostId;
    }

    public boolean listContainHouse(List<House> L, int id)
    {
        for (int i=0; i<L.size(); i++)
        {
            if(L.get(i).getId()== id)
                return true;
        }
        return false;
    }
   
}
