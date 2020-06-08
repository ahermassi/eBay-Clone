/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.DbConnection.Queries;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
/**
 *
 * @author wissal
 */
@ManagedBean
@SessionScoped
public class showOffersController  implements Serializable {
    private String category;
//    private String Description ;
    private List<House> items;
    private Queries query= new Queries();
    private String selectedOption = null;
    private boolean optionAll  = false;
    @ManagedProperty(value = "#{house}")
    private House house;
    private Date currentDate;
 

    @PostConstruct
    public void init() {
         currentDate = new Date();
    }
    public  void checkSearch() throws SQLException
    {   System.out.println("hani fi check method, le catttt : "+this.category);
        System.out.println("selectedOption==>"+getSelectedOption());
        if("2".equals(getSelectedOption()) || "3".equals(getSelectedOption()) )
        {   System.out.println("selectedOption==>"+selectedOption);
            //setCategory("waterside");  System.out.println("x=category"+getCategory());
            try{
            outcome1();
            }catch(Exception e){
 
            e.printStackTrace();
            }
            
        }else if("1".equals(getSelectedOption())){
             try{
            /* in outcome() the value category is gave from the link param of showOffers page, so if we click in search buttom
               category value will be null --> the property optionAll is to tell the outcome() method that the category is the 
               same that is gave from the link --> not gave it second time 
            */
            setOptionAll(true);
            outcome();
            setOptionAll(false);
            }catch(Exception e){
 
            e.printStackTrace();
            }
        }else{
            try{
            outcome();
            }catch(Exception e){
 
            e.printStackTrace();
            }
        }
    }
    

    public void outcome() throws SQLException
    {  // outcome() is used to set the value of param category 
        
        System.out.println(" hani fi outcome....");
        
        
        items = new ArrayList<>();
        try{
            if(!optionAll){
        FacesContext fc = FacesContext.getCurrentInstance();
        this.category = getCountryParam(fc);
            }
        System.out.println(" just by category ==> "+getCategory());
        ResultSet rs= query.showOffers(this.category);
        while (rs.next()) {
            House item = new House();
            item.setId(rs.getInt("id"));
            item.setDescription(rs.getString("description"));
            item.setLocation(rs.getString("location"));
            item.setCategory(rs.getInt("category"));
            item.setPrice(rs.getDouble("price"));
            item.setPhoto(rs.getString("photo"));
            item.setTypeAchat(rs.getString("typeAchat"));
            item.setFinalDate(rs.getDate("finalDate"));
            item.setLeftDays(dateDiff(rs.getDate("finalDate")));
            item.setUserPostId(rs.getInt("userPost"));
            item.setTopPrice(rs.getDouble("topPrice"));
            
            items.add(item);
            System.out.println("location   >>> "+item.getLocation());
            System.out.println("url   >>> "+item.getPhoto());
            System.out.println(" date final  >>> "+rs.getDate("finalDate"));
            System.out.println(" dateDiff  >>> "+item.getLeftDays());
            
        }
        setItems(items);
        }catch(Exception e){
 
            e.printStackTrace();
            }
       
    }
     //get value from "f:param"
    public String getCountryParam(FacesContext fc){

            Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
            return params.get("category");

    }     
    public void outcome1() throws SQLException
    { String typeAchat="";
        items = new ArrayList<>();
        System.out.println("hani fil outcome1");
          if("2".equals(getSelectedOption())){
                typeAchat = "immediat"; System.out.println("type achat : "+typeAchat); 
                System.out.println("cat : "+getCategory());
            }else if("3".equals(getSelectedOption())){
                 typeAchat = "enchere"; System.out.println("type achat : "+typeAchat);
            }
        try{                  
        ResultSet rs= query.showOffers1(getCategory(),typeAchat);
        
        while (rs.next()) { 
            System.out.println("rs-->> "+rs.getString("description"));
            House item = new House();
            item.setId(rs.getInt("id"));
            item.setDescription(rs.getString("description"));
            item.setLocation(rs.getString("location"));
            item.setCategory(rs.getInt("category"));
            item.setPrice(rs.getDouble("price"));
            item.setPhoto(rs.getString("photo"));
            item.setTypeAchat(rs.getString("typeAchat"));
            item.setFinalDate((rs.getDate("finalDate")));
            item.setLeftDays(dateDiff(rs.getDate("finalDate")));
            item.setUserPostId(rs.getInt("userPost"));
            item.setTopPrice(rs.getDouble("topPrice"));
            
            items.add(item);
            System.out.println("location   >>> "+item.getLocation());
            System.out.println("url   >>> "+item.getPhoto());
        }
        setItems(items);
        }catch(Exception e){
 
            e.printStackTrace();
        }       
    }
    public static String dateDiff(Date finalDate)
    {      String output = null;
        Date date = new Date();
        if( finalDate != null){

            long diff = finalDate.getTime() - date.getTime()  ;
            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours1 = diff / (60 * 60 * 1000);
            long diffDays = diffHours1 /24 ;
            long diffHours = diffHours1 -(24 * diffDays);

            try {
                Date calculatedTime =  new SimpleDateFormat("HH:mm:ss").parse(diffHours+":"+diffMinutes+":"+diffSeconds);
                output= String.valueOf(diffDays)+" days "+ String.valueOf(diffHours)+ " h "+ String.valueOf(diffMinutes) + "min";
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return output;
        }
        return null;
    }
    public String logOut()
    { System.out.println("in logouttttttt");
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }
    
    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }
    //get value from "f:param"
    public String getSearchParam(FacesContext fc){

            Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
            System.out.println("optionnnn   :::: "+params.get("searchOption"));
            return params.get("searchOption");
            

    }

    public List<House> getItems() {
        return items;
    }

    public void setItems(List<House> items) {
        this.items = items;
    }
   
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isOptionAll() {
        return optionAll;
    }

    public void setOptionAll(boolean optionAll) {
        this.optionAll = optionAll;
    }
}