/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DbConnection;
import com.controller.House;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
/**
 *
 * @author wissal
 */
public class Queries  implements Serializable{
    private ConnectionDB cnxDB= null ;
    
    public Queries()
    {   try{
        this.cnxDB= new ConnectionDB();
        }catch(Exception e){
            e.printStackTrace();
        }    
    }
    public ResultSet loginControl(String login, String password)
    {   String req ="Select * from  user where login ='"+login+"' and password = '"+password+"';";
         try{ 
           Connection cnx = cnxDB.connect();
           Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            if(rs.next()) {
                return rs;
            }else{
                    return null;
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }    
    }
    public int signUpControl(String login, String password, String email)
    {   String req ="insert into user(login, email, password) values('"+login+"', '"+email+"', '"+password+"'); ";
    
         try{ 
             System.out.println("in sugn p controlllll ");
           Connection cnx = cnxDB.connect();
           Statement st = cnx.createStatement();
           int count = st.executeUpdate(req);
            if(count>0) {
                return count;
            }else{
                    return -1 ;
            }
        }catch(Exception e){
            e.printStackTrace();
            return -1 ;
        }    
    }
    
    
    //version 1 : showOffers()==> search offers by category
    public ResultSet showOffers(String category)
    {      int id = 0;
        String req1="Select id from  category where nameCategory ='"+category+"'";  
        try{ 
            Connection cnx = cnxDB.connect();
            Statement st1 = cnx.createStatement();
            Statement st2 = cnx.createStatement();
            ResultSet rs1 = st1.executeQuery(req1);
            while(rs1.next()){ id = rs1.getInt("id"); }
            String req2 ="Select * from  house where category ='"+id+"'";
            ResultSet rs2 = st2.executeQuery(req2);

        if(rs2!=null){
              return rs2;
        }else{
            return null ;
          }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }  
    }
    //version 1 : showOffers()==> search offers by category and by search option (all, immediat,bid)
    public ResultSet showOffers1(String category, String typeAchat)
    {       Statement st1 = null;
            Statement st2 = null;
            ResultSet rs1 = null;
            ResultSet rs2 = null;
        int id = 0;  
            System.out.println("hani fi show offers1");
            System.out.println("type achat ==>"+typeAchat);
            System.out.println("category ==> "+category);
        String req1="Select id from  category where nameCategory ='"+category+"'";  
        try{  
            Connection cnx = cnxDB.connect();
            st1 = cnx.createStatement();
            st2 = cnx.createStatement();
            rs1 = st1.executeQuery(req1);
            while(rs1.next()){ id = rs1.getInt("id");  }
            String req2 ="Select * from  house where category ="+id+" and typeAchat ='"+typeAchat+"' ";
            rs2 = st2.executeQuery(req2);            
        while(rs2!=null){ 
              return rs2;
        }
        return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }  
    }
     public House findProductById(int id)
     { 
         House house= new House();
         String req ="Select * from  House where id = "+id+";";
        try{ 
           Connection cnx = cnxDB.connect();
           Statement st = cnx.createStatement();
           Statement st1 = cnx.createStatement();
           ResultSet rs = st.executeQuery(req);
            if(rs.next()) {
                house.setId(rs.getInt("id"));
                house.setCategory(rs.getInt("category"));
                house.setDescription(rs.getString("description"));
                house.setLocation(rs.getString("location"));
                house.setPhoto(rs.getString("photo"));
                house.setPrice(rs.getDouble("price"));
                house.setTypeAchat(rs.getString("typeAchat"));
                return house;
            }else{
                    return null;
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public int modifyPriceById(int id, double newBidPrice)
    {   String req="update house set topPrice="+newBidPrice+" where id= "+id+"";  
        try{ 
            Connection cnx = cnxDB.connect();
            Statement st = cnx.createStatement();
            int count = st.executeUpdate(req);
            
        if(count > 0 ){
              return count;
        }else{
            return -1 ;
          }
        }catch(Exception e){
            e.printStackTrace();
            return -1;
        }  
    }
}
