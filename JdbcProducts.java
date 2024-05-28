package com.wipro.io;

import java.sql.*;

public class JdbcProducts {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // Step 1: Load the Oracle JDBC driver
        Class.forName("oracle.jdbc.OracleDriver");

        // Step 2: Establish a connection to the database
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:9501/XE", "system", "rps@123");

        System.out.println("Connection is successful");

        // Step 3: Create a statement
        Statement st = con.createStatement();

        // Step 4: Create table if not exists
        st.executeUpdate("CREATE TABLE IF NOT EXISTS PRODUCTS (PROD_ID NUMBER(20) PRIMARY KEY, NAME VARCHAR(50) NOT NULL, PRICE NUMBER)");

        // Inserting new products
        st.executeUpdate("INSERT INTO PRODUCTS (PROD_ID, NAME, PRICE) VALUES (1, 'Laptop', 40000)");
        st.executeUpdate("INSERT INTO PRODUCTS (PROD_ID, NAME, PRICE) VALUES (2, 'Cooler', 30000)");
        st.executeUpdate("INSERT INTO PRODUCTS (PROD_ID, NAME, PRICE) VALUES (3, 'Heater', 2000)");
        st.executeUpdate("INSERT INTO PRODUCTS (PROD_ID, NAME, PRICE) VALUES (4, 'Fridge', 25000)");

        System.out.println("Inserted Successfully");

        // Updating a product
        st.executeUpdate("UPDATE PRODUCTS SET PRICE = 60000 WHERE PROD_ID = 4");

        // Deleting a product
        st.executeUpdate("DELETE FROM PRODUCTS WHERE PROD_ID = 2");

        // Executing a query to retrieve data
        ResultSet rs = st.executeQuery("SELECT * FROM PRODUCTS");

        // Printing the result set metadata
        ResultSetMetaData rms = rs.getMetaData();
        for (int i = 1; i <= rms.getColumnCount(); i++) {
            System.out.print(rms.getColumnName(i) + " ");
        }
        System.out.println();

        // Printing the result set data
        while (rs.next()) {
            System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getDouble(3));
        }

        // Closing the resources
        rs.close();
        st.close();
        con.close();
    }
}

