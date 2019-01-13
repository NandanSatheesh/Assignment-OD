package question2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

/**
 * This class creates an Array of User Class Objects and defines
 * operations which can be performed on the array like
 * addition, serialize and save to file, de-serialize and read it as
 * an object again and display of the object data
 * @author Nandan
 */
public class ArrayOfUsers implements Serializable
{
    private static final String FILENAME= "ArrayOfUsers.ser" ;
    private ArrayList<User> listOfUsers ;

    ArrayOfUsers()
    {
        listOfUsers = new ArrayList<>() ;
    }
    /*
    Gets a valid string
    Validation is done using Scanner methods only
    */
    public String getValidString()
    {
        Scanner input = new Scanner(System.in);
        while(!input.hasNextLine())
        {
            input.nextLine() ;
            System.out.println("please check your input");
        }
        return input.nextLine() ;
    }

    /*
    Gets a valid integer
    Validation is done using Scanner methods only
    */

    public int getValidInt()
    {
        Scanner input = new Scanner(System.in) ;
        while(!input.hasNextInt())
        {
            input.nextLine() ;
            System.out.println("please check your input");
        }
        int in = input.nextInt() ;
        input.nextLine() ;
        return  in ;

    }

    /*
    Adds a user class object to the array of objects after populating it
    */
    public void addUser()
    {
        Scanner input = new Scanner(System.in);
        String name , address   ;
        int age , rollNumber ;
        LinkedHashSet<String> courses = new LinkedHashSet<>();

        System.out.println("enter the name ");
        name = getValidString() ;

        System.out.println("enter the age");
        age = getValidInt() ;

        System.out.println("enter the address");
        address = getValidString() ;

        System.out.println("enter the roll number ");
        rollNumber = getValidInt() ;

        System.out.println("enter the set of four courses from A,B,C,D,E and F only");
        HashSet<String> allCourses = new HashSet<>();
        allCourses.add("A");
        allCourses.add("B");
        allCourses.add("C");
        allCourses.add("D");
        allCourses.add("E");
        allCourses.add("F");
        while(courses.size() < 4 )
        {
            System.out.println("enter the course ");
            String course = getValidString().trim() ;
            if(courses.contains(course))
            {
                System.out.println("course already taken. please take some other course from the lot");
            }
            else if(allCourses.contains(course))
            {
                courses.add(course);
                System.out.println("course "+course+" has been added successfully ") ;
            }
            else
            {
                System.out.println("please check the course you have entered ");
            }
        }

        User user = new User() ;
        user.setFullName(name);
        user.setAge(age);
        user.setAddress(address);
        user.setRollNumber(rollNumber);
        user.setCourses(courses);

        listOfUsers.add(user) ;


    }
    /*
    Serialize and store it to a disk
    */
    public void saveUserDetails()
    {
        // Serialization
        try
        {
            //Saving of object in a file
            FileOutputStream fileOutput = new FileOutputStream(FILENAME);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput);

            // Method for serialization of object
            outputStream.writeObject(listOfUsers);

            outputStream.close();
            fileOutput.close();

            System.out.println("object has been serialized and stored in the disk");

        }
        catch(IOException ex)
        {
            ex.printStackTrace();
            System.out.println("IOException is caught");
        }



    }
    /*
    Deserialize and read it to an object
    */
    public void readUserDetails()
    {
        // Store them in listOfUsers

        try
        {

            // Reading the object from a file
            FileInputStream fileinput = new FileInputStream(FILENAME);

            ObjectInputStream inputObject = new ObjectInputStream(fileinput);

            // Method for deserialization of object
            listOfUsers = (ArrayList<User>)inputObject.readObject();

            inputObject.close();
            fileinput.close();
            System.out.println("Object has been deserialized.");


        }
        catch (IOException ex)
        {
            System.out.println("IOException is caught");
        }

        catch (ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }

    }
    /*
    Displays the contents after sorting
    */
    public void displayAll()
    {


        if(listOfUsers.size() != 0 ) {

            System.out.println("how would you like to sort the data ");
            System.out.println("1. name ");
            System.out.println("2. roll number  ");
            System.out.println("3. age ");
            System.out.println("4. address ");
            System.out.println("enter your choice ");
            int ch = getValidInt();

            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println(String.format("%-15s %-11s %-5s %-20s %-10s", "Name", "Roll Number", "Age", "Address", "Courses"));
            System.out.println("-----------------------------------------------------------------------------------");

            switch (ch) {
                case 1:

                    listOfUsers.sort(Comparator.comparing(User::getFullName));

                    for (int i = 0; i < listOfUsers.size(); i++) {
                        System.out.println(listOfUsers.get(i));
                    }
                    System.out.println("\n\n");
                    break;


                case 2:


                    listOfUsers.sort(Comparator.comparing(User::getRollNumber));

                    for (int i = 0; i < listOfUsers.size(); i++) {
                        System.out.println(listOfUsers.get(i));
                    }
                    System.out.println("\n\n");
                    break;


                case 3:


                    listOfUsers.sort(Comparator.comparing(User::getAge));

                    for (int i = 0; i < listOfUsers.size(); i++) {
                        System.out.println(listOfUsers.get(i));
                    }
                    System.out.println("\n\n");
                    break;


                case 4:


                    listOfUsers.sort(Comparator.comparing(User::getAddress));

                    for (int i = 0; i < listOfUsers.size(); i++) {
                        System.out.println(listOfUsers.get(i));
                    }
                    System.out.println("\n\n");
                    break;


                default:
                    System.out.println("please enter a valid choice ");


            }

        }
        else
        {
            System.out.println("users empty");
        }
    }
    /*
    Deletetion of a User Class Object from the Array
    */
    public void deleteUser()
    {
        LinkedHashSet<Integer> userRollNumbers = new LinkedHashSet<Integer>() ;
        int deleteRollNumber ;
        for(int i = 0 ; i < listOfUsers.size() ; i++)
        {
            userRollNumbers.add(listOfUsers.get(i).getRollNumber()) ;
        }
        System.out.println("enter the user's roll number for deleting that user's details ");
        while(true)
        {

            deleteRollNumber = getValidInt() ;
            if(userRollNumbers.contains(deleteRollNumber))
            {
                List<Integer> rollNumbers = new ArrayList<Integer>(userRollNumbers);
                int removeFromPosition = rollNumbers.indexOf(deleteRollNumber) ;
                listOfUsers.remove(removeFromPosition);
                break ;
            }
            else
            {
                System.out.println("the roll number doesn't exist");
                break ;
            }


        }



    }






}
// End of Class