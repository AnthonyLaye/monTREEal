/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package ca.mcgill.ecse321.treePLE.model;
import java.util.*;
import java.sql.Date;

// line 21 "../../../../../TreePLEModel.ump"
public class Municipality
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Municipality Attributes
  private int biodiversityIndex;

  //Municipality Associations
  private List<Person> person;
  private List<Tree> owns;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Municipality(int aBiodiversityIndex)
  {
    biodiversityIndex = aBiodiversityIndex;
    person = new ArrayList<Person>();
    owns = new ArrayList<Tree>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setBiodiversityIndex(int aBiodiversityIndex)
  {
    boolean wasSet = false;
    biodiversityIndex = aBiodiversityIndex;
    wasSet = true;
    return wasSet;
  }

  public int getBiodiversityIndex()
  {
    return biodiversityIndex;
  }

  public Person getPerson(int index)
  {
    Person aPerson = person.get(index);
    return aPerson;
  }

  public List<Person> getPerson()
  {
    List<Person> newPerson = Collections.unmodifiableList(person);
    return newPerson;
  }

  public int numberOfPerson()
  {
    int number = person.size();
    return number;
  }

  public boolean hasPerson()
  {
    boolean has = person.size() > 0;
    return has;
  }

  public int indexOfPerson(Person aPerson)
  {
    int index = person.indexOf(aPerson);
    return index;
  }

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

  public static int minimumNumberOfPerson()
  {
    return 0;
  }

  public Person addPerson(String aName)
  {
    return new Person(aName, this);
  }

  public boolean addPerson(Person aPerson)
  {
    boolean wasAdded = false;
    if (person.contains(aPerson)) { return false; }
    Municipality existingMunicipality = aPerson.getMunicipality();
    boolean isNewMunicipality = existingMunicipality != null && !this.equals(existingMunicipality);
    if (isNewMunicipality)
    {
      aPerson.setMunicipality(this);
    }
    else
    {
      person.add(aPerson);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePerson(Person aPerson)
  {
    boolean wasRemoved = false;
    //Unable to remove aPerson, as it must always have a municipality
    if (!this.equals(aPerson.getMunicipality()))
    {
      person.remove(aPerson);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addPersonAt(Person aPerson, int index)
  {  
    boolean wasAdded = false;
    if(addPerson(aPerson))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPerson()) { index = numberOfPerson() - 1; }
      person.remove(aPerson);
      person.add(index, aPerson);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePersonAt(Person aPerson, int index)
  {
    boolean wasAdded = false;
    if(person.contains(aPerson))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPerson()) { index = numberOfPerson() - 1; }
      person.remove(aPerson);
      person.add(index, aPerson);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPersonAt(aPerson, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfOwns()
  {
    return 0;
  }

  public Tree addOwn(String aSpecies, float aHeight, int aAge, Date aDate, float aDiameter, float aLongitude, float aLatitude, Resident aResident)
  {
    return new Tree(aSpecies, aHeight, aAge, aDate, aDiameter, aLongitude, aLatitude, this, aResident);
  }

  public boolean addOwn(Tree aOwn)
  {
    boolean wasAdded = false;
    if (owns.contains(aOwn)) { return false; }
    Municipality existingMunicipality = aOwn.getMunicipality();
    boolean isNewMunicipality = existingMunicipality != null && !this.equals(existingMunicipality);
    if (isNewMunicipality)
    {
      aOwn.setMunicipality(this);
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
    //Unable to remove aOwn, as it must always have a municipality
    if (!this.equals(aOwn.getMunicipality()))
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
    for(int i=person.size(); i > 0; i--)
    {
      Person aPerson = person.get(i - 1);
      aPerson.delete();
    }
    for(int i=owns.size(); i > 0; i--)
    {
      Tree aOwn = owns.get(i - 1);
      aOwn.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "biodiversityIndex" + ":" + getBiodiversityIndex()+ "]";
  }
}