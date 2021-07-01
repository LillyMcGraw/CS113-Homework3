package edu.miracosta.cs113;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Objects;

//contains a LinkedList of Terms which are ordered from highest to lower terms. 
//Polynomials can be added to local polynomial in which case any matching terms will be combine and the sum will take it's original place.
//Individual terms can be added or removed as needed.


//Let's use the generic "E" so we can store anything
public class Polynomial
{
	//class variables and linkedList
	private LinkedList<Term> termList;
	
	//default constructor
	//termList becomes new empty LL
	public Polynomial()
    {
        this.termList = new LinkedList<Term>();
    }
    
	
	//copy constructor
	// take in another polynomial and adds to original LL
	public Polynomial(Polynomial o)
	{
		 this.termList = new LinkedList<Term>();
		// run through entire LL of other Polynomial
		for (int i = 0; i < o.getTermList().size(); i++)
		{
			//create temp term
			Term termTemp = new Term(o.getTerm(i));
			termList.add(new Term(termTemp));
			
		}
			
	}
	
	// getNumterms
			// returns the number of elements inside the LL
			public int getNumTerms()
			{
				return termList.size();
			}
			
	//clear 
	// remove all nodes/ elements in linkedList
			public void clear()
			{
				termList.clear();
			}
	
	// goes to the index and looks at term and returns what is there
	public Term getTerm(int i) {
		// TODO Auto-generated method stub
		return this.termList.get(i);
		
	}
	
	//getters + setters
			public LinkedList<Term> getTermList() {
				return termList;
			}


			public void setTermList(LinkedList<Term> termList) {
				this.termList = termList;
			}
	
	//sort
		public void sort()
		{
			//uses compare to and looks at the exponents 
			// comparator organizes exponents from largest to smallest
			Comparator<Term> c = Collections.reverseOrder();
			// comparator is organizing the exponents and looking at the termList to know what to organize
			Collections.sort(termList, c);	
		}
		
		//addterm
		
		public void addTerm(Term o)
		{
			System.out.println(termList.toString());
			System.out.println("this is " + o);
			//create special cases (1,0,-1)
			
			// if empty; add the term
			if (getNumTerms() == 0)
			{
				termList.add(o);
			}
			
			else 
			{
				// flag is checking if the term was added to the LL
				boolean flag = true;
				//looping through entire LL
				for (int i = 0; i < getNumTerms(); i++)
				{
					if (o.getExponent() == this.getTerm(i).getExponent())
					{
						// checking if the exponents match and if so then adding them together
						int newCoefficient = o.getCoefficient() + this.getTerm(i).getCoefficient();
						this.getTerm(i).setCoefficient(newCoefficient);
						flag = false;
					}
					
				}
				// if there are no matching exponents then it sends it back into the list
				if (flag)
				{
					termList.add(o);
				}
				
				sort();
			}
		}

		//removeTerm
		// when giving an index; it removes the term in the index
		private void remove(int i) {
			// TODO Auto-generated method stub
			termList.remove(i);
		}	
	
	//addPolynomial
	public void add(Polynomial o)
	{
		sort();
		o.sort();
		
		for (int j = 0; j < o.getNumTerms(); j++)
		{
			boolean flag = true;
				for (int i = 0; i < getNumTerms(); i++)
				{
					//checks to see if term matches with any other exponents in LL
					if (o.getTerm(j).getExponent() == this.getTerm(i).getExponent())
					{
						// checking if the exponents match and if so then adding them together
						int newCoefficient = o.getTerm(j).getCoefficient() + this.getTerm(i).getCoefficient();
						this.getTerm(i).setCoefficient(newCoefficient);
						
						// if added terms are 0; prints 0
						if (this.getTerm(i).getCoefficient() == 0)
						{
							this.remove(i);
						}
						
						flag = false;
						break;
					}
					
				}
				
				if (flag)
				{
					// looks at other polynomials to add if nothing is specified
					this.termList.add(o.getTerm(j));
				}

				
		}
		   sort();
	}
	
	
	//toString
	@Override
	public String toString() {
		String output = "";
		sort();
		// if list is empty or null; display 0
		if (this.termList == null || this.termList.size() == 0)
		{
			output += "0";
		}
		
		else 
		{  
			//adding all polynomials
			for (int i = 0; i < termList.size(); i++)
			{
				output += termList.get(i).toString();
				if (output.charAt(0) == '+')
				{
					output = output.substring(1);
				}
			}
		}
		return output;
	}

	//equals method
	@Override
	public int hashCode() {
		return Objects.hash(termList);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Polynomial other = (Polynomial) obj;
		return Objects.equals(termList, other.termList);
	}
	
    
	
	
	
	
}

