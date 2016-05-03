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
public class Inventory {
    private String id;
    private String name;
    private String brand;
    private String category;
    private String gender;
    private double price;
    private String size;
    private int quantity;
    
    public Inventory(){
        id = "";
        name = "";
        brand = "";
        category = "";
        gender = "";
        price = 0.0;
        size = "";
        quantity = 0;
        }
    
    public Inventory(String id, String name, String brand, 
            String category, String gender, double price, String size, int quantity){
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.gender = gender;
        this.price = price;
        this.size = size;
        this.quantity = quantity;
        }
    
    public Inventory(Inventory i){
        this.id = i.id;
        this.name = i.name;
        this.brand = i.brand;
        this.category = i.category;
        this.gender = i.gender;
        this.price = i.price;
        this.size = i.size;
        this.quantity = i.quantity;
        }
    
    public String getID(){
        return id;
    }
    
    public String getName(){
        return name;
    }
    
    public String getBrand(){
        return brand;
    }
    
    public String getCategory(){
        return category;
    }
    
    public String getGender(){
        return gender;
    }
    
    public double getPrice(){
        return price;
    }
    
    public String getSize(){
        return size;
    }
    
    public int getQuantity(){
        return quantity;
    }
    }