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
public class Employee {
    private String fname;
    private String lname;
    private String minit;
    private String gender;
    private String address;
    private String phone;
    private double base;
    private String position;
    private String id;
    private double shoes;
    private double apperal;
    private double access;
    
    public Employee(){
        this.fname = "";
        this.lname = "";
        this.minit =  "";
        this.gender = "";
        this.address = "";
        this.position = "";
        this.base = 0.0;
        this.id = "";
        this.phone = "";
        this.shoes = 0.0;
        this.apperal = 0.0;
        this.access = 0.0;
    }
    
    public Employee(String fname, String lname, String minit, String gender, 
            String address, String phone, double base, String position, String id,
            double shoes, double apperal, double access){
        this.fname = fname;
        this.lname = lname;
        this.minit =  minit;
        this.gender = gender;
        this.address = address;
        this.position = position;
        this.base = base;
        this.id = id;
        this.phone = phone;
        this.shoes = shoes;
        this.apperal = apperal;
        this.access = access;
    }
    
    public Employee(Employee e){
        this.fname = e.fname;
        this.lname = e.lname;
        this.minit =  e.minit;
        this.gender = e.gender;
        this.address = e.address;
        this.position = e.position;
        this.base = e.base;
        this.id = e.id;
        this.phone = e.phone;
        this.shoes = e.shoes;
        this.apperal = e.apperal;
        this.access = e.access;
    }
    
    public String getfname(){
        return fname;
    }
    
    public String getlname(){
        return lname;
    }
    
    public String getMinit(){
        return minit;
    }
    
    public String getGender(){
        return gender;
    }
    
    public String getAddress(){
        return address;
    }
    
    public String getPosition(){
        return position;
    }
    
    public String getPhone(){
        return phone;
    }
    
    public String getID(){
        return id;
    }
    
    public double getBase(){
        return base;
    }
    
      public double getShoes(){
        return shoes;
    }
      
      public double getApperal(){
        return apperal;
    }
      public double getAccess(){
        return access;
    }
}
