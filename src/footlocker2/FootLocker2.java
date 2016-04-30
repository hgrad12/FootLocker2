/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package footlocker2;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hgrad
 */
public class FootLocker2 {

    static final String userName="gkoutr2";//put your MySQL user name
    static final String password="Cosc*cd5w";//put your MySQL password
    private static Connection connection=null;
    /**
     * @param args the command line arguments
     * * @throws java.lang.ClassNotFoundException
     * @throws java.lang.InstantiationException
     * @throws java.lang.IllegalAccessException
     * @throws java.sql.SQLException
     */
    public static Connection getConnection() throws Exception{
        Object newInstance;
         if(connection == null){
            //JDBC
            newInstance = Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/gkoutr2db", userName, password);
        }
        return connection;
    }
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, SQLException{
         /*
                Object newInstance;
            newInstance = Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/gkoutr2db", "gkoutr2", "Cosc*cd5w");// Please use your database name here
            PreparedStatement updateStaff;
            Statement queryStatement = connection.createStatement();
            updateStaff = null;
            String querys="select * from gkoutr2db.P;";
            ResultSet results = queryStatement.executeQuery(querys);
            while(results.next())
            {
                System.out.print(results.getString("p_num"));
                System.out.print("    ");
                System.out.print(results.getString("pname"));
                System.out.println();
            }   //querys="SELECT * FROM J;";
            //updateStaff = connection.prepareStatement(querys);
           // updateStaff.executeUpdate();
            
        
        
        // TODO code application logic here
    


        
       
*/
    }
    
}
