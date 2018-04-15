/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package ca.mcgill.ecse321.treePLE.model;
import java.sql.Date;
import java.util.*;

// line 3 "../../../../../TreePLEModel.ump"
public class Tree
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Tree Attributes
  private String species;
  private float height;
  private int age;
  private Date date;
  private float diameter;
  private int id;

  //Tree Associations
  private Person person;
  private List<Survey> surveys;
  private TreePLEManager treePLEManager;
  private Location location;
  private Status status;
  
  //------------------------
  // ENUMERATION
  //------------------------
  
  public enum Status { Healthy, Diseased, MarkedForCutdown, Cutdown }
  
  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Tree(String aSpecies, float aHeight, int aAge, Date aDate, float aDiameter, int aId, Person aPerson, TreePLEManager aTreePLEManager, Location aLocation)
  {
    species = aSpecies;
    height = aHeight;
    age = aAge;
    date = aDate;
    diameter = aDiameter;
    id = aId;
    status = Status.Healthy;
    boolean didAddPerson = setPerson(aPerson);
    if (!didAddPerson)
    {
      throw new RuntimeException("Unable to create tree due to person");
    }
    surveys = new ArrayList<Survey>();
    boolean didAddTreePLEManager = setTreePLEManager(aTreePLEManager);
    if (!didAddTreePLEManager)
    {
      throw new RuntimeException("Unable to create tree due to treePLEManager");
    }
    boolean didAddLocation = setLocation(aLocation);
    if (!didAddLocation)
    {
      throw new RuntimeException("Unable to create tree due to location");
    }
  }

  public Tree(String aSpecies, Date aDate, int aId, Person aPerson, TreePLEManager aTreePLEManager, Location aLocation)
  {
    species = aSpecies;
    date = aDate;
    id = aId;
    status = Status.Healthy;
    height = (float) 100.0;
    diameter = (float) 10.0;
    age = 1;    
    boolean didAddPerson = setPerson(aPerson);
    if (!didAddPerson)
    {
      throw new RuntimeException("Unable to create tree due to person");
    }
    surveys = new ArrayList<Survey>();
    boolean didAddTreePLEManager = setTreePLEManager(aTreePLEManager);
    if (!didAddTreePLEManager)
    {
      throw new RuntimeException("Unable to create tree due to treePLEManager");
    }
    boolean didAddLocation = setLocation(aLocation);
    if (!didAddLocation)
    {
      throw new RuntimeException("Unable to create tree due to location");
    }
  }

  
  //------------------------
  // INTERFACE
  //------------------------
  
  public void setStatus(Status aStatus) {
	  status = aStatus;
  }
  
  public Status getStatus() {
	  return status;
  }
  
  public String getStatusName() {
	  String statusName = status.toString();
	  return statusName;
}

  public boolean setSpecies(String aSpecies)
  {
    boolean wasSet = false;
    species = aSpecies;
    wasSet = true;
    return wasSet;
  }

  public boolean setHeight(float aHeight)
  {
    boolean wasSet = false;
    height = aHeight;
    wasSet = true;
    return wasSet;
  }

  public boolean setAge(int aAge)
  {
    boolean wasSet = false;
    age = aAge;
    wasSet = true;
    return wasSet;
  }

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setDiameter(float aDiameter)
  {
    boolean wasSet = false;
    diameter = aDiameter;
    wasSet = true;
    return wasSet;
  }

  public boolean setId(int aId)
  {
    boolean wasSet = false;
    id = aId;
    wasSet = true;
    return wasSet;
  }

  public String getSpecies()
  {
    return species;
  }

  public float getHeight()
  {
    return height;
  }

  public int getAge()
  {
    return age;
  }

  public Date getDate()
  {
    return date;
  }

  public float getDiameter()
  {
    return diameter;
  }

  public int getId()
  {
    return id;
  }

  public Person getPerson()
  {
    return person;
  }

  public Survey getSurvey(int index)
  {
    Survey aSurvey = surveys.get(index);
    return aSurvey;
  }

  public List<Survey> getSurveys()
  {
    List<Survey> newSurveys = Collections.unmodifiableList(surveys);
    return newSurveys;
  }

  public int numberOfSurveys()
  {
    int number = surveys.size();
    return number;
  }

  public boolean hasSurveys()
  {
    boolean has = surveys.size() > 0;
    return has;
  }

  public int indexOfSurvey(Survey aSurvey)
  {
    int index = surveys.indexOf(aSurvey);
    return index;
  }

  public TreePLEManager getTreePLEManager()
  {
    return treePLEManager;
  }

  public Location getLocation()
  {
    return location;
  }

  public boolean setPerson(Person aPerson)
  {
    boolean wasSet = false;
    if (aPerson == null)
    {
      return wasSet;
    }

    Person existingPerson = person;
    person = aPerson;
    if (existingPerson != null && !existingPerson.equals(aPerson))
    {
      existingPerson.removeTree(this);
    }
    person.addTree(this);
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfSurveys()
  {
    return 0;
  }

  public Survey addSurvey(Date aDate, int aId, Person aObserver)
  {
    return new Survey(aDate, aId, aObserver, this);
  }

  public boolean addSurvey(Survey aSurvey)
  {
    boolean wasAdded = false;
    //if (surveys.contains(aSurvey)) { return false; }
    Tree existingTree = aSurvey.getTree();
    boolean isNewTree = existingTree != null && !this.equals(existingTree);
    if (isNewTree)
    {
      aSurvey.setTree(this);
    }
    else
    {
    	surveys.add(aSurvey);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSurvey(Survey aSurvey)
  {
    boolean wasRemoved = false;
    //Unable to remove aSurvey, as it must always have a tree
    if (!this.equals(aSurvey.getTree()))
    {
    	surveys.remove(aSurvey);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addSurveyAt(Survey aSurvey, int index)
  {  
    boolean wasAdded = false;
    if(addSurvey(aSurvey))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSurveys()) { index = numberOfSurveys() - 1; }
      surveys.remove(aSurvey);
      surveys.add(index, aSurvey);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSurveyAt(Survey aSurvey, int index)
  {
    boolean wasAdded = false;
    if(surveys.contains(aSurvey))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSurveys()) { index = numberOfSurveys() - 1; }
      surveys.remove(aSurvey);
      surveys.add(index, aSurvey);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSurveyAt(aSurvey, index);
    }
    return wasAdded;
  }

  public boolean setTreePLEManager(TreePLEManager aTreePLEManager)
  {
    boolean wasSet = false;
    if (aTreePLEManager == null)
    {
      return wasSet;
    }

    TreePLEManager existingTreePLEManager = treePLEManager;
    treePLEManager = aTreePLEManager;
    if (existingTreePLEManager != null && !existingTreePLEManager.equals(aTreePLEManager))
    {
      existingTreePLEManager.removeTree(this);
    }
    treePLEManager.addTree(this);
    wasSet = true;
    return wasSet;
  }

  public boolean setLocation(Location aLocation)
  {
    boolean wasSet = false;
    if (aLocation == null)
    {
      return wasSet;
    }

    Location existingLocation = location;
    location = aLocation;
    if (existingLocation != null && !existingLocation.equals(aLocation))
    {
      existingLocation.removeTree(this);
    }
    location.addTree(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Person placeholderPerson = person;
    this.person = null;
    placeholderPerson.removeTree(this);
    while (surveys.size() > 0)
    {
      Survey aSurvey = surveys.get(surveys.size() - 1);
      aSurvey.delete();
      surveys.remove(aSurvey);
    }
    
    TreePLEManager placeholderTreePLEManager = treePLEManager;
    this.treePLEManager = null;
    placeholderTreePLEManager.removeTree(this);
    Location placeholderLocation = location;
    this.location = null;
    placeholderLocation.removeTree(this);
  }


  public String toString()
  {
    return super.toString() + "["+
            "species" + ":" + getSpecies()+ "," +
            "height" + ":" + getHeight()+ "," +
            "age" + ":" + getAge()+ "," +
            "diameter" + ":" + getDiameter()+ "," +
            "id" + ":" + getId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "person = "+(getPerson()!=null?Integer.toHexString(System.identityHashCode(getPerson())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "treePLEManager = "+(getTreePLEManager()!=null?Integer.toHexString(System.identityHashCode(getTreePLEManager())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "location = "+(getLocation()!=null?Integer.toHexString(System.identityHashCode(getLocation())):"null");
  }
}
