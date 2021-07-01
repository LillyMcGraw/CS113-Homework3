package edu.miracosta.cs113;

//trying to get eclipse to push my project..SOS


import java.util.Objects;

//E means generic element
//filter Elements that implement the comparable
public class Term implements Cloneable, Comparable<Term> { 
	
	
	// class variables
	private int coefficient;
	private int exponent;
	
	// full constructor
	public Term(int coefficient, int exponent) {
		
		this.coefficient = coefficient;
		this.exponent = exponent;
	}
	
	// default constructor
	public Term()
	{
		coefficient = 1;
		exponent = 1;
	}
	
	// copy constructor
	public Term(Term O)
	{
		coefficient = O.coefficient;
		exponent = O.exponent;
	}

	//stringConstructor
	public Term(String t) {
		// TODO Auto-generated constructor stub
		int indexOfX = t.indexOf("x");
		int indexOfCarrot = t.indexOf("^");
		
	
		// if there is a ""
	    if (t.isEmpty())
		{
			coefficient = 0;
			exponent = 0;
		}
		
	    // if it has an x, doesn't have a ^ , and it has a coefficient 
		else if (t.contains("x") && !t.contains("^") && indexOfX != 1)
			{
			   if (t.startsWith("-"))
					   {
				   			coefficient = Integer.parseInt(t.substring(0, indexOfX));
				   			exponent = 1;
					   }
			   else
			   {
				   coefficient = Integer.parseInt(t.substring(1, indexOfX));
			       exponent = 1;
			   }
			}
	    
	   // if it has an x, doesn't have a ^ and it doesn't have a coefficient
		else if (t.contains("x") && !t.contains("^") && indexOfX == 1)
		{
		   if (t.startsWith("-"))
				   {
			   			coefficient = -1;
			   			exponent = 1;
				   }
		   else
		   {
			   coefficient = 1;
		       exponent = 1;
		   }
		}
	    
	    // if it has a ^ and a coefficient 
	    // takes what is after the ^ and assigns the exponent value 
		else if (indexOfX == 1 && t.contains("^"))
		{
			  if (t.startsWith("-"))
			  {
				  coefficient = -1;
				  exponent = Integer.parseInt(t.substring(indexOfCarrot + 1));
			  }
			  else 
			  {
				  coefficient = 1;
			      exponent = Integer.parseInt(t.substring(indexOfCarrot + 1));
			  }
		}
	    
	    // if it doesn't contain an x or ^
		else if (!t.contains("x") && !t.contains("^"))
		{
			//System.out.println("here");
			// looking through whole string and if there is not an exponent then == 0
			coefficient = Integer.parseInt(t.substring(0));
			exponent = 0;
		}
	    

			else 
			{
			// looking at everything on the left side
			coefficient = Integer.parseInt(t.substring(0, indexOfX));
	       // looking at everything on the right side
	        exponent = Integer.parseInt(t.substring(indexOfCarrot + 1));
			}
	    
	    
	}

	//Getters + setters
	public int getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(int coefficient) {
		this.coefficient = coefficient;
	}

	public int getExponent() {
		return exponent;
	}

	public void setExponent(int exponent) {
		this.exponent = exponent;
	}

	
	//toString
	@Override
	public String toString() {
		
		// special cases
	if (coefficient == 0)
	{
		return "";
	}
	else if (coefficient == -1)
	{
		if (exponent != 1)
		{
			return "-x^" + exponent;
		}
		else 
			return "-x";
	}
	else if (coefficient == 1)
	{
		if (exponent != 1)
		{
		    return "+" + "x^" + exponent;
		}
		else 
			return "+" + "x";
	}
	else if (exponent == 1)
	{
		if (coefficient < 0)
		{
		return coefficient + "x";
		}
		else 
			return "+" + coefficient + "x";
	}
	
	else if (exponent == 0)
	{
	   if (coefficient > 0)
	   {
		   return "+" + coefficient;
	   }
		return "" + coefficient;
	}
	else if (coefficient > 0)
	{
		return "+" + coefficient + "x" + "^" + exponent;
	}
	else
		return coefficient + "x^" + exponent;
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(coefficient, exponent);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Term other = (Term) obj;
		return coefficient == other.coefficient && exponent == other.exponent;
	}

	//setAll
	// a constructor used on an object that has been instantiated
	public void setAll(int coefficient, int exponent) {
		// TODO Auto-generated method stub
		this.coefficient = coefficient;
        this.exponent = exponent;
		
	}

	//compareTo
	//compares the current exponent value with others 
	public int compareTo(Term test) {
		// TODO Auto-generated method stub
		Term otherTerm = (Term) test;
        if (this.exponent > otherTerm.getExponent())
        {
            return 1;
        }
        else if (this.exponent < otherTerm.getExponent())
        {
            return -1;
        }
        else
        {
		return 0;
        }
	}

	// clone method
	public Object clone()
    {
        try
        {
            return super.clone();
        } catch (CloneNotSupportedException e)
        {
            return null;
        }
    }

	
}

