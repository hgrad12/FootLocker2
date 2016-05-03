/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package footlocker2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author hgrad
 */
public class SQLMethods {
    
    private Connection conn;
    
    public SQLMethods(){
        
        
        try{
            DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
            conn = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/gkoutr2db", "gkoutr2", "Cosc*cd5w");
            
            if(conn != null){
                System.out.println("Connected to the database successfully!");
            }
                }catch (SQLException err){
                    System.out.println( err.getMessage( ) );
                }
    }
    
    public void closeDBConnection(){
        try{
            conn.close();
        }catch(SQLException ex){
            System.out.println("Connection is not closed!!!");
        }
    }
    
    public boolean verifyEmpl(String user) throws SQLException{
        Statement stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery("SELECT emp_id FROM Employee");
        
        while(res.next()){
    
            String empl = res.getString("emp_id");
            
            if(user.equals(empl)){
                return true;
            }
        }
        return false;
    }
    
    public boolean verifyPass(String user, String pass) throws SQLException{
        Statement stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery("SELECT password FROM Employee WHERE emp_id = " + user);
        
        while(res.next()){
            String pw = res.getString("password");
            if(pass.equals(pw)){
                return true;
            }
        }
        return false;
    }
    
    public boolean verifyManager(String user, String pass) throws SQLException{
        Statement stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery("SELECT isManager FROM Employee WHERE emp_id = " + user);
        
        while(res.next()){
            int man = res.getInt("isManager");
            if(man == 1){
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<String> populateCatgDD() throws SQLException{
        Statement stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery("SELECT DISTINCT category FROM inventory ORDER BY category ASC");
        ArrayList<String> list = new ArrayList<>();
        list.add("");
        
        while(res.next()){
            list.add(res.getString("category"));
        }
        return list;
    }
    
    public ArrayList<String> populateBrandDD() throws SQLException{
        Statement stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery("SELECT DISTINCT brand FROM inventory ORDER BY brand ASC");
        ArrayList<String> list = new ArrayList<>();
        list.add("");
        
        while(res.next()){
            list.add(res.getString("brand"));
        }
        return list;
    }
    
    public ResultSet populateResultsTable(String bd, String cg) throws SQLException{
        PreparedStatement stmt = conn.prepareStatement("SELECT i.item_id, i.item_name, i.gender, s.size, s.quantity "
                + "FROM inventory i, size s where i.brand = ? and "
                + "i.category = ? and i.item_id = s.item_id order by i.item_id");
        stmt.setString(1,bd);
        stmt.setString(2,cg);
        
        ResultSet res = stmt.executeQuery();
        
        return res;
    }
    
    public ResultSet populateResultsTableBS(String name) throws SQLException{
        PreparedStatement stmt = conn.prepareStatement("SELECT i.item_id, i.item_name, i.gender, s.size, s.quantity "
                + "FROM inventory i, size s "
                + "where i.item_name like ? "
                + "and i.item_id = s.item_id order by i.item_id ");
        
        stmt.setString(1, "%" + name + "%");
        
        ResultSet res = stmt.executeQuery();
        
        return res;
    }
    
    public Employee getEmpl(String id)throws SQLException{
        Employee empl;
        PreparedStatement stmt = conn.prepareStatement("select * "
                + "from Employee e, commission c where c.emp_id = ? "
                + "and e.emp_id in (select emp_id from commission where emp_id = ?)");
        stmt.setString(1, id);
        stmt.setString(2, id);
        
        
        ResultSet res = stmt.executeQuery();
        res.first();
        String fname = res.getString(2);
        String lname = res.getString(4);
        String emp_id = res.getString(1);
        String address = res.getString(6);
        String position = res.getString(9);
        String minit = res.getString(3);
        String gender;
        gender = res.getString(5);
        double base = res.getDouble(8);
        String phone = res.getString(7);
        double shoes = res.getDouble(14);
        double apperal = res.getDouble(15);
        double access = res.getDouble(16);
        
        empl = new Employee(fname, lname, minit, gender, address, phone, base, position, emp_id, shoes, apperal, access);
        
        return empl;
    }
    
    public void removeEmpl(String id)throws SQLException{
        PreparedStatement gl, cm, em;
        gl = conn.prepareStatement("DELETE FROM goals where emp_id = ?");
        gl.setString(1, id);
        cm = conn.prepareStatement("DELETE FROM commission where emp_id = ?");
        cm.setString(1, id);
        em = conn.prepareStatement("DELETE FROM employee where emp_id = ?");
        em.setString(1, id);
        
        gl.executeUpdate();
        cm.executeUpdate();
        em.executeUpdate();
        
        
    }
    
    class Purchase
    {
        String m_customerName;
        private String m_itemName;
        private String m_category;
        private int m_itemSize;
        private float m_price;
        private int m_quantity; 
        private float m_total;
        
        private ArrayList<Purchase> Cart = new ArrayList<Purchase>();
        
        Purchase (String itemName, String category, int itemSize, float price, int quantity, float total)
        {
            m_itemName = itemName;
            m_category = category;
            m_itemSize = itemSize;
            m_price = price;
            m_quantity = quantity;
            m_total = total;
        }
        
        public void SetCustomerName(String CustomerName)throws SQLException
        {
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("SELECT customerName FROM Customer");
            String fName = res.getString(1);
            String lName = res.getString(2);
            m_customerName = fName + " " +lName;
        }
        
        public String GetCustomerName()
        {
            return m_customerName;
        }
        
        public String GetItemName()
        {
            return m_itemName;
        }
        
        public int GetItemSize()
        {
            return m_itemSize;
        }
        
        public String GetCategory()
        {
            return m_category;
        }
        
        public float GetPrice()
        {
            return m_price;
        }
        
        public int GetQuantity()
        {
            return m_quantity;
        }
        
        public float CreateTotal(ArrayList<Purchase> myList)
        {
            for (int i = 0; i < myList.size(); i++)
            {
                m_total += myList.get(i).GetPrice();
            }
            return m_total;
        }
        
        public void SetItemName(String itemName)
        {
             m_itemName = itemName;
        }
        
        public void SetItemSize(int itemSize)
        {
            m_itemSize = itemSize;
        }
        
        public void SetCategory(String Category)
        {
            m_category = Category;
        }
        
        public void SetPrice(float price)
        {
            int quantity = GetQuantity();
            m_price = price * quantity;
            
        }
        
        public void SetQuantity(int quantity)
        {
            m_quantity = quantity;
        }
        
        public void CreateCart(Purchase sale)
        {
            Cart.add(sale);
        }
        
        public ArrayList<Purchase> GetCart()
        {
            return Cart;
        }
    }
}

