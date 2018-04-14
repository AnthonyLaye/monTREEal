/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package ca.mcgill.ecse321.treePLE.model;
import java.util.*;
import java.sql.Date;

// line 19 "../../../../../TreePLEModel.ump"
public class TreePLEManager
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TreePLEManager Associations
  private List<Tree> trees;
  private List<Person> person;
  private List<Forecast> forecast;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TreePLEManager()
  {
    trees = new ArrayList<Tree>();
    person = new ArrayList<Person>();
    forecast = new ArrayList<Forecast>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Tree getTree(int index)
  {
    Tree aTree = trees.get(index);
    return aTree;
  }

  public List<Tree> getTrees()
  {
    List<Tree> newTrees = Collections.unmodifiableList(trees);
    return newTrees;
  }

  public int numberOfTrees()
  {
    int number = trees.size();
    return number;
  }

  public boolean hasTrees()
  {
    boolean has = trees.size() > 0;
    return has;
  }

  public int indexOfTree(Tree aTree)
  {
    int index = trees.indexOf(aTree);
    return index;
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

  public Forecast getForecast(int index)
  {
    Forecast aForecast = forecast.get(index);
    return aForecast;
  }

  public List<Forecast> getForecast()
  {
    List<Forecast> newForecast = Collections.unmodifiableList(forecast);
    return newForecast;
  }

  public int numberOfForecast()
  {
    int number = forecast.size();
    return number;
  }

  public boolean hasForecast()
  {
    boolean has = forecast.size() > 0;
    return has;
  }

  public int indexOfForecast(Forecast aForecast)
  {
    int index = forecast.indexOf(aForecast);
    return index;
  }

  public static int minimumNumberOfTrees()
  {
    return 0;
  }

  public Tree addTree(String aSpecies, float aHeight, int aAge, Date aDate, float aDiameter, int aId, Person aPerson, Location aLocation)
  {
    return new Tree(aSpecies, aHeight, aAge, aDate, aDiameter, aId, aPerson, this, aLocation);
  }

  public boolean addTree(Tree aTree)
  {
    boolean wasAdded = false;
    if (trees.contains(aTree)) { return false; }
    TreePLEManager existingTreePLEManager = aTree.getTreePLEManager();
    boolean isNewTreePLEManager = existingTreePLEManager != null && !this.equals(existingTreePLEManager);
    if (isNewTreePLEManager)
    {
      aTree.setTreePLEManager(this);
    }
    else
    {
      trees.add(aTree);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTree(Tree aTree)
  {
    boolean wasRemoved = false;
    //Unable to remove aTree, as it must always have a treePLEManager
    if (!this.equals(aTree.getTreePLEManager()))
    {
      trees.remove(aTree);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addTreeAt(Tree aTree, int index)
  {  
    boolean wasAdded = false;
    if(addTree(aTree))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTrees()) { index = numberOfTrees() - 1; }
      trees.remove(aTree);
      trees.add(index, aTree);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTreeAt(Tree aTree, int index)
  {
    boolean wasAdded = false;
    if(trees.contains(aTree))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTrees()) { index = numberOfTrees() - 1; }
      trees.remove(aTree);
      trees.add(index, aTree);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTreeAt(aTree, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfPerson()
  {
    return 0;
  }

  public boolean addPerson(Person aPerson)
  {
    boolean wasAdded = false;
    if (person.contains(aPerson)) { return false; }
    TreePLEManager existingTreePLEManager = aPerson.getTreePLEManager();
    boolean isNewTreePLEManager = existingTreePLEManager != null && !this.equals(existingTreePLEManager);
    if (isNewTreePLEManager)
    {
      aPerson.setTreePLEManager(this);
    }
    else
    {
      person.add(aPerson);
    }
    wasAdded = true;
    return wasAdded;
  }
  
  /* Code from template association_AddManyToOne */
  public Person addPerson(String aName, String aEmail, String aPassword)
  {
    return new Person(aName, aEmail, aPassword, this);
  }

  public boolean removePerson(Person aPerson)
  {
    boolean wasRemoved = false;
    //Unable to remove aPerson, as it must always have a treePLEManager
    if (!this.equals(aPerson.getTreePLEManager()))
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

  public static int minimumNumberOfForecast()
  {
    return 0;
  }

  public Forecast addForecast()
  {
    return new Forecast(this);
  }

  public boolean addForecast(Forecast aForecast)
  {
    boolean wasAdded = false;
    if (forecast.contains(aForecast)) { return false; }
    TreePLEManager existingTreePLEManager = aForecast.getTreePLEManager();
    boolean isNewTreePLEManager = existingTreePLEManager != null && !this.equals(existingTreePLEManager);
    if (isNewTreePLEManager)
    {
      aForecast.setTreePLEManager(this);
    }
    else
    {
      forecast.add(aForecast);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeForecast(Forecast aForecast)
  {
    boolean wasRemoved = false;
    //Unable to remove aForecast, as it must always have a treePLEManager
    if (!this.equals(aForecast.getTreePLEManager()))
    {
      forecast.remove(aForecast);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addForecastAt(Forecast aForecast, int index)
  {  
    boolean wasAdded = false;
    if(addForecast(aForecast))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfForecast()) { index = numberOfForecast() - 1; }
      forecast.remove(aForecast);
      forecast.add(index, aForecast);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveForecastAt(Forecast aForecast, int index)
  {
    boolean wasAdded = false;
    if(forecast.contains(aForecast))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfForecast()) { index = numberOfForecast() - 1; }
      forecast.remove(aForecast);
      forecast.add(index, aForecast);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addForecastAt(aForecast, index);
    }
    return wasAdded;
  }
  
  /* Code from template association_AddManyToOne */
  public Person person(String aName, String aEmail, String aPassword)
  {
    return new Person(aName, aEmail, aPassword, this);
  }
  
  public void delete()
  {
    while (trees.size() > 0)
    {
      Tree aTree = trees.get(trees.size() - 1);
      aTree.delete();
      trees.remove(aTree);
    }
    
    for(int i=person.size(); i > 0; i--)
    {
      Person aPerson = person.get(i - 1);
      aPerson.delete();
    }
    for(int i=forecast.size(); i > 0; i--)
    {
      Forecast aForecast = forecast.get(i - 1);
      aForecast.delete();
    }
  }

}