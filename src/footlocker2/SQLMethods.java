/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package footlocker2;

import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author hgrad
 */
public class SQLMethods {
    
    private Connection conn;
    
    public SQLMethods(){
        
        
        try{
            DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "password");
            
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
        ResultSet res = stmt.executeQuery("SELECT emp_id FROM employee");
        
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
        ResultSet res = stmt.executeQuery("SELECT password FROM employee WHERE emp_id = " + user);
        
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
        ResultSet res = stmt.executeQuery("SELECT isManager FROM employee WHERE emp_id = " + user);
        
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
                + "from employee e, commission c where c.emp_id = ? "
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
    
    public void addEmpl(String fname, String mi, String lname, String gender, String address, String phone, double base, String position,
            String empid, double shoes, double apperal, double access) throws SQLException{
        
        Statement stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery("SELECT password FROM employee");
        String pass;
        int max = 0;
        int min;
        while(res.next()){
            String pw = res.getString("password");
            pass = pw.substring(pw.length() - 1);
            
            min = Integer.parseInt(pass);
            if(min > max){
                max = min;
            }
        }
        
        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
        //java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        //SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        //String formatted = df.format(new Date());
        String DATE_FORMAT_NOW = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        String stringDate = sdf.format(date );
        
        PreparedStatement stmt1 = conn.prepareStatement("INSERT INTO employee "
                + "(emp_id, first_name, Minit, last_name, gender, address, phone_num, base_pay, "
                + "position, start_date, password, isManager) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
                stmt1.setString(1, empid);
                stmt1.setString(2, fname);
                stmt1.setString(3, mi);
                stmt1.setString(4, lname);
                stmt1.setString(5, gender);
                stmt1.setString(6, address);
                stmt1.setString(7, phone);
                stmt1.setDouble(8, base);
                stmt1.setString(9, position);
                stmt1.setString(10, stringDate);
                stmt1.setString(11, ("password" + max));
                stmt1.setInt(12, 0);
                
                stmt1.executeUpdate();
                
        PreparedStatement stmt2 = conn.prepareStatement("insert into commission "
                + "(emp_id, shoes, apperal, accessories, total_commission) values(?,?,?,?,?)");
                stmt2.setString(1, empid);
                stmt2.setDouble(2, shoes);
                stmt2.setDouble(3, apperal);
                stmt2.setDouble(4, access);
                stmt2.setDouble(5, 0.0);
                
                stmt2.executeUpdate();
        
        PreparedStatement stmt3 = conn.prepareStatement("insert into goals"
                + "(emp_id, total_sales, curr_year, last_year) values(?,?,?,?)");
                stmt3.setString(1, empid);
                stmt3.setDouble(2, 0.0);
                stmt3.setDouble(3, 0.0);
                stmt3.setDouble(4, 0.0);
                
                stmt3.executeUpdate();
        
    }
    
    public void updateEmpl(String id, String fname, String mi, String lname, String gender, String address, String phone, double pay, String position, double access, double shoes, double apperal)throws SQLException{
        PreparedStatement gl, cm, em;
        em = conn.prepareStatement("UPDATE employee SET first_name=?,Minit=?,last_name=?,gender=?,address=?,phone_num=?,base_pay=?,position=? WHERE emp_id=?;");
        em.setString(1, fname);
        em.setString(2, mi);
        em.setString(3, lname);
        em.setString(4, gender);
        em.setString(5, address);
        em.setString(6, phone);
        em.setDouble(7, pay);
        em.setString(8, position);
        em.setString(9, id);
        cm = conn.prepareStatement("UPDATE commission SET shoes=?, apperal=?,accessories=? where emp_id = ?");
        cm.setDouble(1, shoes);
        cm.setDouble(2, apperal);
        cm.setDouble(3, access);
        cm.setString(4, id);
        
        em.executeUpdate();
        cm.executeUpdate();
    }
}
