package question2;

import java.io.Serializable;
import java.util.Comparator;
import java.util.LinkedHashSet;

/**
 * This class defined the object User and the set of methods for accessing the
 * data members
 * @author Nandan
 */
public class User implements Serializable, Comparable<User>
{
    private String mfullName ;
    private int mage ;
    private String maddress ;
    private int mrollNumber ;
    private LinkedHashSet<String> setOfCourses  ;

    User()
    {
        mfullName = "" ;
        mage = 0 ;
        maddress = "" ;
        mrollNumber = 0 ;
        setOfCourses = new LinkedHashSet<String>() ;

    }

    public void setFullName(String fullName)
    {
        this.mfullName = fullName ;
    }

    public void setAge(int age)
    {
        this.mage = age ;
    }

    public void setAddress(String address)
    {
        this.maddress = address ;
    }

    public void setRollNumber(int rollNumber)
    {
        this.mrollNumber = rollNumber ;
    }

    public void setCourses(LinkedHashSet<String> courses )
    {
        this.setOfCourses = courses ;
    }

    public int getAge()
    {
        return this.mage ;
    }
    public String getAddress()
    {
        return this.maddress ;
    }


    public String toString()
    {

        return String.format("%-15s %-11d %-5d %-20s %-10s",this.mfullName,this.mrollNumber,this.mage,this.maddress,this.setOfCourses) ;

    }

    public int getRollNumber()
    {
        return this.mrollNumber ;
    }
    public String getFullName()
    {
        return this.mfullName ;
    }

    /*
    compareTo method is overriden to provide a custom functionality while sorting
    roll numbers and names
    */
    public int compareTo(User ob)
    {
        int compareStep1 =  this.getFullName().compareTo(ob.getFullName()) ;
        if( compareStep1 != 0 )
        {
            return compareStep1 ;
        }
        else
        {
            return this.mrollNumber - ob.mrollNumber ;
        }
    }

    public static Comparator<User> UserComparator =  new Comparator<User>()  {

        public int compare(User ob1 , User ob2) {

            int compareStep1 = ob1.getFullName().compareTo(ob2.getFullName()) ;

            if( compareStep1 != 0 )
            {
                return compareStep1 ;
            }
            else
            {
                return ob1.getRollNumber() - ob2.getRollNumber() ;
            }

        }

    };

}