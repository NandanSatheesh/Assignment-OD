package question4;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator ;


public class ProducerConsumer {

    static Connection conn ;
    static Statement st ;
    static ResultSet res ;

    public static void getConnectionDB(){



        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/od_q4", "root", "password");
            st = (Statement) conn.createStatement();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public static void main(String[] args) {

        getConnectionDB();

        ArrayList<Details> arrayOfObjects = new ArrayList<>() ;


        Thread readerThread = new Thread() {

            public void run() {



                try {
                    String query = "SELECT NAME, PRICE, QUANTITY, TYPE FROM DETAILS ";
                    res = st.executeQuery(query);
                    System.out.println("records from the database ");
                    while (res.next()) {
                        Details object = new Details();
                        object.setName(res.getString("name"));
                        object.setPrice(String.valueOf(res.getInt("price")));
                        object.setQuantity(String.valueOf(res.getInt("quantity")));
                        object.setType(res.getString("type"));
                        System.out.println(object);
                        arrayOfObjects.add(object);

                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }


            }



        };



        Thread updateThread = new Thread() {


            public void run() {


                System.out.println("calculating tax..");

                try {

                    String updateQuery = "UPDATE DETAILS SET tax = ? WHERE NAME = ? AND PRICE = ? AND QUANTITY = ? AND TYPE = ?" ;
                    PreparedStatement preparedStatement ;







                    Iterator<Details> iterator = arrayOfObjects.iterator();
                    while (iterator.hasNext()) {

                        preparedStatement = conn.prepareStatement(updateQuery);

                        Details tempObject = iterator.next();


                        tempObject.calculateTax();

                        preparedStatement.setDouble(1 , tempObject.getTax());
                        preparedStatement.setString(2 , tempObject.getName());
                        preparedStatement.setDouble(3,tempObject.getPrice());
                        preparedStatement.setInt(4, tempObject.getQuantity());
                        preparedStatement.setString(5,tempObject.getType());

                        preparedStatement.executeUpdate() ;





                        System.out.println(tempObject);

                    }
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
        };





        try{
            readerThread.start() ;
            readerThread.join();
            updateThread.start();
        }
        catch(Exception e ){
            e.printStackTrace();
        }

        }



    }

