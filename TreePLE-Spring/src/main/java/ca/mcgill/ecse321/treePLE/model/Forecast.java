/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package ca.mcgill.ecse321.treePLE.model;
import java.util.*;

// line 32 "../../../../../TreePLEModel.ump"
public class Forecast
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Forecast Attributes
  private int biodiversityIndex;

  //Forecast Associations
  private TreePLEManager treePLEManager;
  private List<Biodiversity> biodiversities;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Forecast(int aBiodiversityIndex, TreePLEManager aTreePLEManager)
  {
    biodiversityIndex = aBiodiversityIndex;
    boolean didAddTreePLEManager = setTreePLEManager(aTreePLEManager);
    if (!didAddTreePLEManager)
    {
      throw new RuntimeException("Unable to create forecast due to treePLEManager");
    }
    biodiversities = new ArrayList<Biodiversity>();
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

  public TreePLEManager getTreePLEManager()
  {
    return treePLEManager;
  }

  public Biodiversity getBiodiversity(int index)
  {
    Biodiversity aBiodiversity = biodiversities.get(index);
    return aBiodiversity;
  }

  public List<Biodiversity> getBiodiversities()
  {
    List<Biodiversity> newBiodiversities = Collections.unmodifiableList(biodiversities);
    return newBiodiversities;
  }

  public int numberOfBiodiversities()
  {
    int number = biodiversities.size();
    return number;
  }

  public boolean hasBiodiversities()
  {
    boolean has = biodiversities.size() > 0;
    return has;
  }

  public int indexOfBiodiversity(Biodiversity aBiodiversity)
  {
    int index = biodiversities.indexOf(aBiodiversity);
    return index;
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
      existingTreePLEManager.removeForecast(this);
    }
    treePLEManager.addForecast(this);
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfBiodiversities()
  {
    return 0;
  }

  public boolean addBiodiversity(Biodiversity aBiodiversity)
  {
    boolean wasAdded = false;
    if (biodiversities.contains(aBiodiversity)) { return false; }
    biodiversities.add(aBiodiversity);
    if (aBiodiversity.indexOfForecast(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aBiodiversity.addForecast(this);
      if (!wasAdded)
      {
        biodiversities.remove(aBiodiversity);
      }
    }
    return wasAdded;
  }

  public boolean removeBiodiversity(Biodiversity aBiodiversity)
  {
    boolean wasRemoved = false;
    if (!biodiversities.contains(aBiodiversity))
    {
      return wasRemoved;
    }

    int oldIndex = biodiversities.indexOf(aBiodiversity);
    biodiversities.remove(oldIndex);
    if (aBiodiversity.indexOfForecast(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aBiodiversity.removeForecast(this);
      if (!wasRemoved)
      {
        biodiversities.add(oldIndex,aBiodiversity);
      }
    }
    return wasRemoved;
  }

  public boolean addBiodiversityAt(Biodiversity aBiodiversity, int index)
  {  
    boolean wasAdded = false;
    if(addBiodiversity(aBiodiversity))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBiodiversities()) { index = numberOfBiodiversities() - 1; }
      biodiversities.remove(aBiodiversity);
      biodiversities.add(index, aBiodiversity);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBiodiversityAt(Biodiversity aBiodiversity, int index)
  {
    boolean wasAdded = false;
    if(biodiversities.contains(aBiodiversity))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBiodiversities()) { index = numberOfBiodiversities() - 1; }
      biodiversities.remove(aBiodiversity);
      biodiversities.add(index, aBiodiversity);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBiodiversityAt(aBiodiversity, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    TreePLEManager placeholderTreePLEManager = treePLEManager;
    this.treePLEManager = null;
    placeholderTreePLEManager.removeForecast(this);
    ArrayList<Biodiversity> copyOfBiodiversities = new ArrayList<Biodiversity>(biodiversities);
    biodiversities.clear();
    for(Biodiversity aBiodiversity : copyOfBiodiversities)
    {
      aBiodiversity.removeForecast(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "biodiversityIndex" + ":" + getBiodiversityIndex()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "treePLEManager = "+(getTreePLEManager()!=null?Integer.toHexString(System.identityHashCode(getTreePLEManager())):"null");
  }
}