/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package ca.mcgill.ecse321.treePLE.model;
import java.util.*;

// line 31 "../../../../../TreePLEModel.ump"
public class Forecast
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Forecast Associations
  private TreePLEManager treePLEManager;
  private List<Biodiversity> biodiversities;
  private List<Canopy> canopies;
  private List<CarbonSequestration> carbonSequestrations;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Forecast(TreePLEManager aTreePLEManager)
  {
    boolean didAddTreePLEManager = setTreePLEManager(aTreePLEManager);
    if (!didAddTreePLEManager)
    {
      throw new RuntimeException("Unable to create forecast due to treePLEManager");
    }
    biodiversities = new ArrayList<Biodiversity>();
    canopies = new ArrayList<Canopy>();
    carbonSequestrations = new ArrayList<CarbonSequestration>();
  }

  //------------------------
  // INTERFACE
  //------------------------

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

  public Canopy getCanopy(int index)
  {
    Canopy aCanopy = canopies.get(index);
    return aCanopy;
  }

  public List<Canopy> getCanopies()
  {
    List<Canopy> newCanopies = Collections.unmodifiableList(canopies);
    return newCanopies;
  }

  public int numberOfCanopies()
  {
    int number = canopies.size();
    return number;
  }

  public boolean hasCanopies()
  {
    boolean has = canopies.size() > 0;
    return has;
  }

  public int indexOfCanopy(Canopy aCanopy)
  {
    int index = canopies.indexOf(aCanopy);
    return index;
  }

  public CarbonSequestration getCarbonSequestration(int index)
  {
    CarbonSequestration aCarbonSequestration = carbonSequestrations.get(index);
    return aCarbonSequestration;
  }

  public List<CarbonSequestration> getCarbonSequestrations()
  {
    List<CarbonSequestration> newCarbonSequestrations = Collections.unmodifiableList(carbonSequestrations);
    return newCarbonSequestrations;
  }

  public int numberOfCarbonSequestrations()
  {
    int number = carbonSequestrations.size();
    return number;
  }

  public boolean hasCarbonSequestrations()
  {
    boolean has = carbonSequestrations.size() > 0;
    return has;
  }

  public int indexOfCarbonSequestration(CarbonSequestration aCarbonSequestration)
  {
    int index = carbonSequestrations.indexOf(aCarbonSequestration);
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

  public static int minimumNumberOfCanopies()
  {
    return 0;
  }

  public boolean addCanopy(Canopy aCanopy)
  {
    boolean wasAdded = false;
    if (canopies.contains(aCanopy)) { return false; }
    canopies.add(aCanopy);
    if (aCanopy.indexOfForecast(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aCanopy.addForecast(this);
      if (!wasAdded)
      {
        canopies.remove(aCanopy);
      }
    }
    return wasAdded;
  }

  public boolean removeCanopy(Canopy aCanopy)
  {
    boolean wasRemoved = false;
    if (!canopies.contains(aCanopy))
    {
      return wasRemoved;
    }

    int oldIndex = canopies.indexOf(aCanopy);
    canopies.remove(oldIndex);
    if (aCanopy.indexOfForecast(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aCanopy.removeForecast(this);
      if (!wasRemoved)
      {
        canopies.add(oldIndex,aCanopy);
      }
    }
    return wasRemoved;
  }

  public boolean addCanopyAt(Canopy aCanopy, int index)
  {  
    boolean wasAdded = false;
    if(addCanopy(aCanopy))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCanopies()) { index = numberOfCanopies() - 1; }
      canopies.remove(aCanopy);
      canopies.add(index, aCanopy);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCanopyAt(Canopy aCanopy, int index)
  {
    boolean wasAdded = false;
    if(canopies.contains(aCanopy))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCanopies()) { index = numberOfCanopies() - 1; }
      canopies.remove(aCanopy);
      canopies.add(index, aCanopy);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCanopyAt(aCanopy, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfCarbonSequestrations()
  {
    return 0;
  }

  public boolean addCarbonSequestration(CarbonSequestration aCarbonSequestration)
  {
    boolean wasAdded = false;
    if (carbonSequestrations.contains(aCarbonSequestration)) { return false; }
    carbonSequestrations.add(aCarbonSequestration);
    if (aCarbonSequestration.indexOfForecast(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aCarbonSequestration.addForecast(this);
      if (!wasAdded)
      {
        carbonSequestrations.remove(aCarbonSequestration);
      }
    }
    return wasAdded;
  }

  public boolean removeCarbonSequestration(CarbonSequestration aCarbonSequestration)
  {
    boolean wasRemoved = false;
    if (!carbonSequestrations.contains(aCarbonSequestration))
    {
      return wasRemoved;
    }

    int oldIndex = carbonSequestrations.indexOf(aCarbonSequestration);
    carbonSequestrations.remove(oldIndex);
    if (aCarbonSequestration.indexOfForecast(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aCarbonSequestration.removeForecast(this);
      if (!wasRemoved)
      {
        carbonSequestrations.add(oldIndex,aCarbonSequestration);
      }
    }
    return wasRemoved;
  }

  public boolean addCarbonSequestrationAt(CarbonSequestration aCarbonSequestration, int index)
  {  
    boolean wasAdded = false;
    if(addCarbonSequestration(aCarbonSequestration))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCarbonSequestrations()) { index = numberOfCarbonSequestrations() - 1; }
      carbonSequestrations.remove(aCarbonSequestration);
      carbonSequestrations.add(index, aCarbonSequestration);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCarbonSequestrationAt(CarbonSequestration aCarbonSequestration, int index)
  {
    boolean wasAdded = false;
    if(carbonSequestrations.contains(aCarbonSequestration))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCarbonSequestrations()) { index = numberOfCarbonSequestrations() - 1; }
      carbonSequestrations.remove(aCarbonSequestration);
      carbonSequestrations.add(index, aCarbonSequestration);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCarbonSequestrationAt(aCarbonSequestration, index);
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
    ArrayList<Canopy> copyOfCanopies = new ArrayList<Canopy>(canopies);
    canopies.clear();
    for(Canopy aCanopy : copyOfCanopies)
    {
      aCanopy.removeForecast(this);
    }
    ArrayList<CarbonSequestration> copyOfCarbonSequestrations = new ArrayList<CarbonSequestration>(carbonSequestrations);
    carbonSequestrations.clear();
    for(CarbonSequestration aCarbonSequestration : copyOfCarbonSequestrations)
    {
      aCarbonSequestration.removeForecast(this);
    }
  }

}