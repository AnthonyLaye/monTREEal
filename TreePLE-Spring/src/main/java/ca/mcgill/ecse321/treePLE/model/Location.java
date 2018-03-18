/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package ca.mcgill.ecse321.treePLE.model;
import java.util.*;
import java.sql.Date;

// line 35 "../../../../../TreePLEModel.ump"
public class Location
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Location Attributes
  private float longitude;
  private float latitude;
  private String municipality;

  //Location Associations
  private List<Tree> tree;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Location(float aLongitude, float aLatitude, String aMunicipality)
  {
    longitude = aLongitude;
    latitude = aLatitude;
    municipality = aMunicipality;
    tree = new ArrayList<Tree>();
  }

  //------------------------
  // INTERFACE
  //------------------------

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

  public boolean setMunicipality(String aMunicipality)
  {
    boolean wasSet = false;
    municipality = aMunicipality;
    wasSet = true;
    return wasSet;
  }

  public float getLongitude()
  {
    return longitude;
  }

  public float getLatitude()
  {
    return latitude;
  }

  public String getMunicipality()
  {
    return municipality;
  }

  public Tree getTree(int index)
  {
    Tree aTree = tree.get(index);
    return aTree;
  }

  public List<Tree> getTree()
  {
    List<Tree> newTree = Collections.unmodifiableList(tree);
    return newTree;
  }

  public int numberOfTree()
  {
    int number = tree.size();
    return number;
  }

  public boolean hasTree()
  {
    boolean has = tree.size() > 0;
    return has;
  }

  public int indexOfTree(Tree aTree)
  {
    int index = tree.indexOf(aTree);
    return index;
  }

  public static int minimumNumberOfTree()
  {
    return 0;
  }

  public Tree addTree(String aSpecies, float aHeight, int aAge, Date aDate, float aDiameter, int aId, Person aPerson, TreePLEManager aTreePLEManager)
  {
    return new Tree(aSpecies, aHeight, aAge, aDate, aDiameter, aId, aPerson, aTreePLEManager, this);
  }

  public boolean addTree(Tree aTree)
  {
    boolean wasAdded = false;
    if (tree.contains(aTree)) { return false; }
    Location existingLocation = aTree.getLocation();
    boolean isNewLocation = existingLocation != null && !this.equals(existingLocation);
    if (isNewLocation)
    {
      aTree.setLocation(this);
    }
    else
    {
      tree.add(aTree);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTree(Tree aTree)
  {
    boolean wasRemoved = false;
    //Unable to remove aTree, as it must always have a location
    if (!this.equals(aTree.getLocation()))
    {
      tree.remove(aTree);
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
      if(index > numberOfTree()) { index = numberOfTree() - 1; }
      tree.remove(aTree);
      tree.add(index, aTree);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTreeAt(Tree aTree, int index)
  {
    boolean wasAdded = false;
    if(tree.contains(aTree))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTree()) { index = numberOfTree() - 1; }
      tree.remove(aTree);
      tree.add(index, aTree);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTreeAt(aTree, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=tree.size(); i > 0; i--)
    {
      Tree aTree = tree.get(i - 1);
      aTree.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "longitude" + ":" + getLongitude()+ "," +
            "latitude" + ":" + getLatitude()+ "," +
            "municipality" + ":" + getMunicipality()+ "]";
  }
}