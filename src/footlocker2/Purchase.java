/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package footlocker2;
import java.sql.*;
import java.util.*;
/**
 *
 * @author hgrad
 */
public class Purchase {
    
        private String m_itemName;
        private String m_category;
        private String m_itemSize;
        private double m_price;
        private int m_quantity; 
        private double m_total;
        
        Connection conn;
        
        Purchase (){
           
        }
        
        Purchase (String itemName, String category, String itemSize, double price, int quantity)
        {
            m_itemName = itemName;
            m_category = category;
            m_itemSize = itemSize;
            
            m_quantity = quantity;
            m_price = price;
        }
        
        Purchase (Purchase p){
            m_itemName = p.m_itemName;
            m_category = p.m_category;
            m_itemSize = p.m_itemSize;
            m_price = p.m_price;
            m_quantity = p.m_quantity;
            m_total = p.m_total;
        }
        
        public void SetCustomerName(String CustomerName)throws SQLException
        {
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("SELECT customerName FROM Customer");
            String fName = res.getString(1);
            String lName = res.getString(2);
        }
        
        
        public String GetItemName()
        {
            return m_itemName;
        }
        
        public String GetItemSize()
        {
            return m_itemSize;
        }
        
        public String GetCategory()
        {
            return m_category;
        }
        
        public double GetPrice()
        {
            return m_price;
        }
        
        public int GetQuantity()
        {
            return m_quantity;
        }
        
        public double CreateTotal(ArrayList<Purchase> myList)
        {
            double total = 0.0;
            for (int i = 0; i < myList.size(); i++)
            {
                for (int j = 0; j < myList.get(i).GetQuantity(); j++)
                {
                    total += myList.get(i).GetPrice();
                }
            }
            return total;
        }
        
        public double CreateTax(ArrayList<Purchase> myList)
        {
            double total = 0.0;
           for (int i = 0; i < myList.size(); i++)
            {
                for (int j = 0; j < myList.get(i).GetQuantity(); j++)
                {
                    total += myList.get(i).GetPrice();
                }
            }
            return total * (0.06);
        }
        
        public void SetItemName(String itemName)
        {
             m_itemName = itemName;
        }
        
        public void SetItemSize(String itemSize)
        {
            m_itemSize = itemSize;
        }
        
        public void SetCategory(String Category)
        {
            m_category = Category;
        }
        
        public void SetPrice(double price)
        {
           
            m_price = price * m_quantity;
            
        }
        
        public void SetQuantity(int quantity)
        {
            m_quantity = quantity;
        }
        
}
