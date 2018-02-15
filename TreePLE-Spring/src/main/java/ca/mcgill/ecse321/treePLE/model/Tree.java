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
  private float longitude;
  private float latitude;

  //Tree Associations
  private Municipality municipality;
  private Resident resident;
  private List<Survey> surveies;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Tree(String aSpecies, float aHeight, int aAge, Date aDate, float aDiameter, float aLongitude, float aLatitude, Municipality aMunicipality, Resident aResident)
  {
    species = aSpecies;
    height = aHeight;
    age = aAge;
    date = aDate;
    diameter = aDiameter;
    longitude = aLongitude;
    latitude = aLatitude;
    boolean didAddMunicipality = setMunicipality(aMunicipality);
    if (!didAddMunicipality)
    {
      throw new RuntimeException("Unable to create own due to municipality");
    }
    boolean didAddResident = setResident(aResident);
    if (!didAddResident)
    {
      throw new RuntimeException("Unable to create own due to resident");
    }
    surveies = new ArrayList<Survey>();
  }

  //------------------------
  // INTERFACE
  //------------------------

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

  public boolean setLongitude(float aLongitude)
  {
    boolean wasSet = false;
    longitude = aLongitude;
    wasSet = true;
    return wasSet;
  }

  public boolean setLatitude(float aLatitude)
  {
    boolean wasSet = false;
    latitude = aLatitude;
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

  public float getLongitude()
  {
    return longitude;
  }

  public float getLatitude()
  {
    return latitude;
  }

  public Municipality getMunicipality()
  {
    return municipality;
  }

  public Resident getResident()
  {
    return resident;
  }

  public Survey getSurvey(int index)
  {
    Survey aSurvey = surveies.get(index);
    return aSurvey;
  }

  public List<Survey> getSurveies()
  {
    List<Survey> newSurveies = Collections.unmodifiableList(surveies);
    return newSurveies;
  }

  public int numberOfSurveies()
  {
    int number = surveies.size();
    return number;
  }

  public boolean hasSurveies()
  {
    boolean has = surveies.size() > 0;
    return has;
  }

  public int indexOfSurvey(Survey aSurvey)
  {
    int index = surveies.indexOf(aSurvey);
    return index;
  }

  public boolean setMunicipality(Municipality aMunicipality)
  {
    boolean wasSet = false;
    if (aMunicipality == null)
    {
      return wasSet;
    }

    Municipality existingMunicipality = municipality;
    municipality = aMunicipality;
    if (existingMunicipality != null && !existingMunicipality.equals(aMunicipality))
    {
      existingMunicipality.removeOwn(this);
    }
    municipality.addOwn(this);
    wasSet = true;
    return wasSet;
  }

  public boolean setResident(Resident aResident)
  {
    boolean wasSet = false;
    if (aResident == null)
    {
      return wasSet;
    }

    Resident existingResident = resident;
    resident = aResident;
    if (existingResident != null && !existingResident.equals(aResident))
    {
      existingResident.removeOwn(this);
    }
    resident.addOwn(this);
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfSurveies()
  {
    return 0;
  }

  public Survey addSurvey(Date aDate)
  {
    return new Survey(aDate, this);
  }

  public boolean addSurvey(Survey aSurvey)
  {
    boolean wasAdded = false;
    if (surveies.contains(aSurvey)) { return false; }
    Tree existingTree = aSurvey.getTree();
    boolean isNewTree = existingTree != null && !this.equals(existingTree);
    if (isNewTree)
    {
      aSurvey.setTree(this);
    }
    else
    {
      surveies.add(aSurvey);
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
      surveies.remove(aSurvey);
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
      if(index > numberOfSurveies()) { index = numberOfSurveies() - 1; }
      surveies.remove(aSurvey);
      surveies.add(index, aSurvey);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSurveyAt(Survey aSurvey, int index)
  {
    boolean wasAdded = false;
    if(surveies.contains(aSurvey))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSurveies()) { index = numberOfSurveies() - 1; }
      surveies.remove(aSurvey);
      surveies.add(index, aSurvey);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSurveyAt(aSurvey, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Municipality placeholderMunicipality = municipality;
    this.municipality = null;
    placeholderMunicipality.removeOwn(this);
    Resident placeholderResident = resident;
    this.resident = null;
    placeholderResident.removeOwn(this);
    for(int i=surveies.size(); i > 0; i--)
    {
      Survey aSurvey = surveies.get(i - 1);
      aSurvey.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "species" + ":" + getSpecies()+ "," +
            "height" + ":" + getHeight()+ "," +
            "age" + ":" + getAge()+ "," +
            "diameter" + ":" + getDiameter()+ "," +
            "longitude" + ":" + getLongitude()+ "," +
            "latitude" + ":" + getLatitude()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "municipality = "+(getMunicipality()!=null?Integer.toHexString(System.identityHashCode(getMunicipality())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "resident = "+(getResident()!=null?Integer.toHexString(System.identityHashCode(getResident())):"null");
  }
}