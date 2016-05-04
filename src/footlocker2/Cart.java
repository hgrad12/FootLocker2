/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package footlocker2;

/**
 *
 * @author hgrad
 */
public class Cart {
    
    private String id;
    private int quantity;
    private String size;
    
    public Cart(){
        id ="";
        size = "";
        quantity = 0;
    }
    
    public Cart(String id, String size, int quantity){
        this.id = id;
        this.size = size;
        this.quantity = quantity;
    }
    
    public Cart(Cart c){
        this.id = c.id;
        this.size = c.size;
        this.quantity = c.quantity;
    }
    
    public String getID(){
        return id;
    }
    
    public String getSize(){
        return size;
    }
    
    public int getQuantity(){
        return quantity;
    }
   
    
}
