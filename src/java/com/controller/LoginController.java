/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

//import com.DbConnection.Queries;
//import com.model.User;
import com.DbConnection.ConnectionDB;
import com.DbConnection.Queries;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author wissal
 */
@ManagedBean(name="login")
@SessionScoped 
public class LoginController implements Serializable{

    private int id ;
    private String login;
    private String password;
    private String email;
    private int registerForm ;
    private String idProduct; /* it's used to save the user id of product to delete */
    Queries query= new Queries();
    private List<House> myList = new ArrayList<>();
    
    @PostConstruct
    public void init(){
        this.registerForm = 0;
    }
    public String loginControl() throws SQLException
    {  
        ResultSet rs = query.loginControl(login, password);
        
        if(rs != null){
            setId(rs.getInt("id"));/* to persist the user id value in the current session */
             
        return "home.xhtml?faces-redirect-true";
       }else{
        return "login.xhtml?faces-redirect-true";
            }
    }
   
     public String signUp()
    {  
        int count = query.signUpControl(login, password, email);
        if(count > 0){
             
        return "home.xhtml?faces-redirect-true";
       }else{
            setRegisterForm(1);
        return "login.xhtml?faces-redirect-true";
            }
    }

      public void myListings()
      {     myList.removeAll(myList);
            ConnectionDB cnxDB= new ConnectionDB();
            int userPost = getId();
            System.out.println("user id how posted this post --> "+getId());
            String req ="Select * from  house where userPost ="+userPost+" ;";
            try{ 
           Connection cnx = cnxDB.connect();
           Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                House item = new House();
            item.setId(rs.getInt("id"));
            item.setDescription(rs.getString("description"));
            item.setLocation(rs.getString("location"));
            item.setCategory(rs.getInt("category"));
            item.setPrice(rs.getDouble("price"));
            item.setPhoto(rs.getString("photo"));
            item.setTypeAchat(rs.getString("typeAchat"));
            item.setFinalDate((rs.getDate("finalDate")));
            item.setLeftDays(showOffersController.dateDiff(rs.getDate("finalDate")));
            item.setUserPostId(rs.getInt("userPost"));
            myList.add(item);
            
            System.out.println("location   >>> "+item.getLocation());
            System.out.println("url   >>> "+item.getPhoto());
            }
            setMyList(myList);
        }catch(Exception e){
            e.printStackTrace();
        }    
        
    }
      
       public void cancelPost() throws SQLException
    {  try{
        ConnectionDB cnxDB= new ConnectionDB();
        Queries query=new Queries();
        House house= new House();
//        setAddToCart(true);
        Connection cnx = cnxDB.connect();
        Statement st = cnx.createStatement();
        
        FacesContext fc = FacesContext.getCurrentInstance();
        setIdProduct(getIdProdParam(fc));        
        System.out.println("im in cancelProduct method ");
        System.out.println("prooduct  id --> "+getIdProduct());
        house = query.findProductById(Integer.parseInt(getIdProduct()));
        System.out.println("prooduct desciption --> "+house.getDescription());

        for (int i=0; i<myList.size(); i++)
        {
            if(myList.get(i).getId()== Integer.parseInt(getIdProduct())){
                
               System.out.println("cancelling....");
               myList.remove(i);
               RequestContext.getCurrentInstance().execute("document.getElementById("+getIdProduct()+").remove();");
               String req ="delete from  house where id ="+getIdProduct()+" ;";
               int count = st.executeUpdate(req);
               if(count>0)
                   System.out.println("house deleted of user "+getId());
               
            }
        }
             
        
        int userPost = getId();
        System.out.println("user id how posted this post --> "+getId());
        
         
           
           
        }catch(Exception e){
            e.printStackTrace();
        }    
    }
       
        
    //get value from "f:param"
    public String getIdProdParam(FacesContext fc){

            Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
            return params.get("idProduct");

    }     

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

       
    public List<House> getMyList() {
        return myList;
    }

    public void setMyList(List<House> myList) {
        this.myList = myList;
    }

    
    public int getRegisterForm() {
        return registerForm;
    }

    public void setRegisterForm(int registerForm) {
        this.registerForm = registerForm;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
