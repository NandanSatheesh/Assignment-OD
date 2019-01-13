package question1;

public class Details {



    private String name ;
    private double price ;
    private int quantity ;
    private String type ;
    private double tax ;
    private double finalPrice ;

    public Details()
    {
        this.name = "" ;
        this.price = 0 ;
        this.quantity = 0 ;
        this.type = "" ;
        this.tax = 0 ;
        this.finalPrice = 0 ;
    }

    public void setName(String name )
    {
        this.name = name ;
    }

    public void setPrice(String price )throws NumberFormatException
    {
        this.price = Double.parseDouble(price) ;
    }

    public void setQuantity(String quantity )throws NumberFormatException
    {
        this.quantity = Integer.parseInt(quantity) ;
    }

    public void setType(String type )
    {
        this.type = type;
    }

    public void calculateTax()
    {
        if(this.type.equalsIgnoreCase("raw"))
        {
            this.tax = 0.125 * price ;
        }
        else if(this.type.equalsIgnoreCase("manufactured"))
        {
            // 12.5% of the item cost + 2% of (item cost + 12.5% of item cost) = 14.75% of item cost
            this.tax = 0.1475 * price ;
        }
        else if(this.type.equalsIgnoreCase("imported"))
        {

            this.tax = 0.1 * this.price ;

            if(this.price + this.tax <= 100)
            {
                this.tax += 5 ;
            }
            else if(this.price + this.tax <= 200)
            {
                this.tax += 10 ;
            }
            else
            {
                this.tax += 0.5 * (this.tax + this.price) ;
            }



        }

        finalPrice = this.price + this.tax ;
    }
    public double getTax(){ return this.tax ;}
    public String toString()
    {
        return("the details of this object are as follows--\nname:"+this.name+"\nprice:"+this.price+"\nquantity:"+this.quantity+"\ntype:"+this.type+"\ntax:"+this.tax+"\n");
    }

}


