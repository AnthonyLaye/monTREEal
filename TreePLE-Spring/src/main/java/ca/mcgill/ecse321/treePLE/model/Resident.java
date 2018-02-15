/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package ca.mcgill.ecse321.treePLE.model;
import java.util.*;
import java.sql.Date;

// line 31 "../../../../../TreePLEModel.ump"
public class Resident extends Person
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Resident Associations
  private List<Tree> owns;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Resident(String aName, Municipality aMunicipality)
  {
    super(aName, aMunicipality);
    owns = new ArrayList<Tree>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Tree getOwn(int index)
  {
    Tree aOwn = owns.get(index);
    return aOwn;
  }

  public List<Tree> getOwns()
  {
    List<Tree> newOwns = Collections.unmodifiableList(owns);
    return newOwns;
  }

  public int numberOfOwns()
  {
    int number = owns.size();
    return number;
  }

  public boolean hasOwns()
  {
    boolean has = owns.size() > 0;
    return has;
  }

  public int indexOfOwn(Tree aOwn)
  {
    int index = owns.indexOf(aOwn);
    return index;
  }

  public static int minimumNumberOfOwns()
  {
    return 0;
  }

  public Tree addOwn(String aSpecies, float aHeight, int aAge, Date aDate, float aDiameter, float aLongitude, float aLatitude, Municipality aMunicipality)
  {
    return new Tree(aSpecies, aHeight, aAge, aDate, aDiameter, aLongitude, aLatitude, aMunicipality, this);
  }

  public boolean addOwn(Tree aOwn)
  {
    boolean wasAdded = false;
    if (owns.contains(aOwn)) { return false; }
    Resident existingResident = aOwn.getResident();
    boolean isNewResident = existingResident != null && !this.equals(existingResident);
    if (isNewResident)
    {
      aOwn.setResident(this);
    }
    else
    {
      owns.add(aOwn);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeOwn(Tree aOwn)
  {
    boolean wasRemoved = false;
    //Unable to remove aOwn, as it must always have a resident
    if (!this.equals(aOwn.getResident()))
    {
      owns.remove(aOwn);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addOwnAt(Tree aOwn, int index)
  {  
    boolean wasAdded = false;
    if(addOwn(aOwn))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOwns()) { index = numberOfOwns() - 1; }
      owns.remove(aOwn);
      owns.add(index, aOwn);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOwnAt(Tree aOwn, int index)
  {
    boolean wasAdded = false;
    if(owns.contains(aOwn))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOwns()) { index = numberOfOwns() - 1; }
      owns.remove(aOwn);
      owns.add(index, aOwn);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOwnAt(aOwn, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=owns.size(); i > 0; i--)
    {
      Tree aOwn = owns.get(i - 1);
      aOwn.delete();
    }
    super.delete();
  }

}