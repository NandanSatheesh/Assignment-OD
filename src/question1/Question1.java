package question1;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Question1 {


    static ArrayList<Details> details_array = new ArrayList<Details>() ;
    static boolean checkforId(String args[] ) throws ArrayIndexOutOfBoundsException
    {
        /*

        this function checks if the paramter argument -type is present in the list of parameters
        input : array of strings
        output : boolean (true/false)

        */
        try
        {
            for(int i = 0 ; i < args.length ; i=i+2)
            {
                if(args[i].equalsIgnoreCase("-type")) {
                    if (args[i + 1].equalsIgnoreCase("raw") || args[i + 1].equalsIgnoreCase("manufactured") || args[i + 1].equalsIgnoreCase("imported"))
                    {
                        return true;

                    }
                }
            }
            return false ;
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            System.out.println("array index out of bounds");
            return false ;
        }

        }


    public static void main(String[] args)
    {
       /*
       main function for the program

       */
        addDetails(args);

        Scanner userInput = new Scanner(System.in);
        String input = new String() ;
        System.out.print("Do you want to enter details of any other item (y/n):");
        do
        {
           /*
           a simple regex is constructed to check y/n from the user.
           y / Y = Yes
           n / N = No
           a simple validation is implemented using Scanner Class
           */
            while(!userInput.hasNext(Pattern.compile("[yYnN]")))
            {
                userInput.nextLine();
            }

            input = userInput.nextLine() ;


            if(input.equalsIgnoreCase("Y"))
            {
               /*
               add the details to the class when the input is yes
               */
                System.out.println("please enter the details with the following argument tags. \n-name \n-price \n-quantity \n-type \nin a single line ");
                String parameters = userInput.nextLine() ;
                addDetails(parameters.split(" "));

            }
            else if(input.equalsIgnoreCase("N"))
            {
               /*
               the loop should terminate when n/N is entered
               */
                break ;
            }

            System.out.print("Do you want to enter details of any other item (y/n):");



        }while(input.equalsIgnoreCase("Y"));

       /*
       Print the output tax of all the array of objects in the ArrayList
       */
        for(Details object: details_array)
        {
            System.out.println(object);
        }

    }







    public static void addDetails(String args[])
    {
       /*
       populate the data members of the class here in this function
       */
        if(checkforId(args))
        {
            Details temporary_ob = new Details() ;
            for(int i = 0 ; i < args.length ; i++)
            {
                /*
                type checking before insertion is done. The methods are capable of throwing appropriate exception when required
                try-catch blocks are used for exception handling
                */
                if(args[i].trim().equals("-name"))
                {
                    System.out.println("name = "+args[++i]);
                    temporary_ob.setName(args[i]);
                }
                else if(args[i].trim().equals("-price"))
                {
                    System.out.println("price = "+args[++i]);
                    try
                    {
                        temporary_ob.setPrice(args[i]);
                    }
                    catch(NumberFormatException e)
                    {
                        System.out.println("datatype expected for price is double. ");
                        return ;
                    }
                }
                else if(args[i].trim().equals("-quantity"))
                {
                    System.out.println("quantity = "+args[++i]);
                    try
                    {
                        temporary_ob.setQuantity(args[i]);
                    }
                    catch(NumberFormatException e)
                    {
                        System.out.println("datatype expected for quantity is double ");
                        return ;
                    }
                }
                else if(args[i].trim().equals("-type"))
                {
                    System.out.println("type = "+args[++i]);
                    temporary_ob.setType(args[i]);
                }


            }
            // call the calculation function here
            temporary_ob.calculateTax();
            details_array.add(temporary_ob) ;// end of for loop
        } // end of checkforId if block
        else
        {
            System.out.println("-type argument not found");

        }


    }
}
