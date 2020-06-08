/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.DbConnection.Queries;
import com.controller.House;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author wissal
 */
@ManagedBean
@SessionScoped
public class CartController  implements Serializable
{    private boolean addToCart = false;
     private List<House> cartProducts = new ArrayList<>(); 
    
    private String idProduct; /* it's used to save the id of product to delete */
    
    
    public void addProduct()
    {   
        Queries query=new Queries();
        House house= new House();
        setAddToCart(true);
       
        FacesContext fc = FacesContext.getCurrentInstance();
        setIdProduct(getIdProdParam(fc));        
        System.out.println("im in addProduct method ");
        System.out.println("prooduct id --> "+getIdProduct());
        house = query.findProductById(Integer.parseInt(getIdProduct()));
        System.out.println("prooduct desciption --> "+house.getDescription());
        
        if(house.listContainHouse(cartProducts, Integer.parseInt(getIdProduct() ))){
   
         System.out.println("prooduct already exist in the cart ");
        }else{
            cartProducts.add(house);
            System.out.println("new product added to the cart ");
        }
        
    }
        public void cancelProduct()
    {   
        Queries query=new Queries();
        House house= new House();
        setAddToCart(true);
       
        FacesContext fc = FacesContext.getCurrentInstance();
        setIdProduct(getIdProdParam(fc));        
        System.out.println("im in cancelProduct method ");
        System.out.println("prooduct  id --> "+getIdProduct());
        house = query.findProductById(Integer.parseInt(getIdProduct()));
        System.out.println("prooduct desciption --> "+house.getDescription());

        for (int i=0; i<cartProducts.size(); i++)
        {
            if(cartProducts.get(i).getId()== Integer.parseInt(getIdProduct())){
                
               System.out.println("cancelling....");
               cartProducts.remove(i);
               RequestContext.getCurrentInstance().execute("document.getElementById("+getIdProduct()+").remove();");
               
            }
        }
    }
    
    //get value from "f:param"
    public String getIdProdParam(FacesContext fc){

            Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
            return params.get("idProduct");

    }     
    
    public List<House> getCartProducts() {
        return cartProducts;
    }

    public boolean isAddToCart() {
        return addToCart;
    }

    public void setAddToCart(boolean addToCart) {
        this.addToCart = addToCart;
    }

    public void setCartProducts(List<House> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public void deleteProduct(House house)
    {
        
    } 
     public String getIdProduct() {
        
        return idProduct;
    }
 


    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

}
