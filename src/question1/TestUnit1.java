package question1;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class TestUnit1
{


    @Before
    public void createObjects()
    {
        Question1 ob = new Question1() ;
    }

    @Test
    public void testcheckforId()
    {
        // replace the string with the test case of your choice
        String[] args = "-name nandan -price 100 -type raw".trim().split(" ");


        assertEquals("true" , String.valueOf(Question1.checkforId(args)).trim() ) ;

        args = "-name nandan -price 100 -quantity 500".trim().split(" ");


        assertEquals("false" , String.valueOf(Question1.checkforId(args)).trim() ) ;
    }

    @Test
    public void testcalculatetax()
    {
        Details ob = new Details() ;
        Question1 qob = new Question1();
        ob.setName("Book");
        ob.setPrice("100");
        ob.setType("raw");
        ob.calculateTax();
        assertEquals(12.5,ob.getTax(),1.0E-06);

        ob = new Details() ;
        ob.setName("Laptop");
        ob.setPrice("2000");
        ob.setType("manufactured");
        ob.calculateTax();
        assertEquals(295.0,ob.getTax(),1.0E-06);

        ob = new Details() ;
        ob.setName("Mouse");
        ob.setPrice("200");
        ob.setType("imported");
        ob.calculateTax();
        assertEquals(130.0,ob.getTax(),1.0E-06);



    }




}