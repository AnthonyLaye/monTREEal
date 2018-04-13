/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.1.3862.7e232c4b4 modeling language!*/

package ca.mcgill.ecse321.treePLE.model;
import java.util.*;

// line 54 "../../../../../../../../ump/tmp859800/model.ump"
// line 109 "../../../../../../../../ump/tmp859800/model.ump"
public class CarbonSequestrationManager
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //CarbonSequestrationManager Associations
  private List<CarbonSequestration> carbonSequestration;
  private List<SpeciesDensities> speciesDensities;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public CarbonSequestrationManager()
  {
    carbonSequestration = new ArrayList<CarbonSequestration>();
    speciesDensities = new ArrayList<SpeciesDensities>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public CarbonSequestration getCarbonSequestration(int index)
  {
    CarbonSequestration aCarbonSequestration = carbonSequestration.get(index);
    return aCarbonSequestration;
  }

  public List<CarbonSequestration> getCarbonSequestration()
  {
    List<CarbonSequestration> newCarbonSequestration = Collections.unmodifiableList(carbonSequestration);
    return newCarbonSequestration;
  }

  public int numberOfCarbonSequestration()
  {
    int number = carbonSequestration.size();
    return number;
  }

  public boolean hasCarbonSequestration()
  {
    boolean has = carbonSequestration.size() > 0;
    return has;
  }

  public int indexOfCarbonSequestration(CarbonSequestration aCarbonSequestration)
  {
    int index = carbonSequestration.indexOf(aCarbonSequestration);
    return index;
  }
  /* Code from template association_GetMany */
  public SpeciesDensities getSpeciesDensity(int index)
  {
    SpeciesDensities aSpeciesDensity = speciesDensities.get(index);
    return aSpeciesDensity;
  }

  public List<SpeciesDensities> getSpeciesDensities()
  {
    List<SpeciesDensities> newSpeciesDensities = Collections.unmodifiableList(speciesDensities);
    return newSpeciesDensities;
  }

  public int numberOfSpeciesDensities()
  {
    int number = speciesDensities.size();
    return number;
  }

  public boolean hasSpeciesDensities()
  {
    boolean has = speciesDensities.size() > 0;
    return has;
  }

  public int indexOfSpeciesDensity(SpeciesDensities aSpeciesDensity)
  {
    int index = speciesDensities.indexOf(aSpeciesDensity);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCarbonSequestration()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addCarbonSequestration(CarbonSequestration aCarbonSequestration)
  {
    boolean wasAdded = false;
    if (carbonSequestration.contains(aCarbonSequestration)) { return false; }
    carbonSequestration.add(aCarbonSequestration);
    if (aCarbonSequestration.indexOfCarbonSequestrationManager(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aCarbonSequestration.addCarbonSequestrationManager(this);
      if (!wasAdded)
      {
        carbonSequestration.remove(aCarbonSequestration);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeCarbonSequestration(CarbonSequestration aCarbonSequestration)
  {
    boolean wasRemoved = false;
    if (!carbonSequestration.contains(aCarbonSequestration))
    {
      return wasRemoved;
    }

    int oldIndex = carbonSequestration.indexOf(aCarbonSequestration);
    carbonSequestration.remove(oldIndex);
    if (aCarbonSequestration.indexOfCarbonSequestrationManager(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aCarbonSequestration.removeCarbonSequestrationManager(this);
      if (!wasRemoved)
      {
        carbonSequestration.add(oldIndex,aCarbonSequestration);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addCarbonSequestrationAt(CarbonSequestration aCarbonSequestration, int index)
  {  
    boolean wasAdded = false;
    if(addCarbonSequestration(aCarbonSequestration))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCarbonSequestration()) { index = numberOfCarbonSequestration() - 1; }
      carbonSequestration.remove(aCarbonSequestration);
      carbonSequestration.add(index, aCarbonSequestration);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCarbonSequestrationAt(CarbonSequestration aCarbonSequestration, int index)
  {
    boolean wasAdded = false;
    if(carbonSequestration.contains(aCarbonSequestration))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCarbonSequestration()) { index = numberOfCarbonSequestration() - 1; }
      carbonSequestration.remove(aCarbonSequestration);
      carbonSequestration.add(index, aCarbonSequestration);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCarbonSequestrationAt(aCarbonSequestration, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSpeciesDensities()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public SpeciesDensities addSpeciesDensity(String aSpecies, int aDensity, String aUISpecies)
  {
    return new SpeciesDensities(aSpecies, aDensity, aUISpecies, this);
  }

  public boolean addSpeciesDensity(SpeciesDensities aSpeciesDensity)
  {
    boolean wasAdded = false;
    if (speciesDensities.contains(aSpeciesDensity)) { return false; }
    CarbonSequestrationManager existingCarbonSequestrationManager = aSpeciesDensity.getCarbonSequestrationManager();
    boolean isNewCarbonSequestrationManager = existingCarbonSequestrationManager != null && !this.equals(existingCarbonSequestrationManager);
    if (isNewCarbonSequestrationManager)
    {
      aSpeciesDensity.setCarbonSequestrationManager(this);
    }
    else
    {
      speciesDensities.add(aSpeciesDensity);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSpeciesDensity(SpeciesDensities aSpeciesDensity)
  {
    boolean wasRemoved = false;
    //Unable to remove aSpeciesDensity, as it must always have a CarbonSequestrationManager
    if (!this.equals(aSpeciesDensity.getCarbonSequestrationManager()))
    {
      speciesDensities.remove(aSpeciesDensity);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSpeciesDensityAt(SpeciesDensities aSpeciesDensity, int index)
  {  
    boolean wasAdded = false;
    if(addSpeciesDensity(aSpeciesDensity))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSpeciesDensities()) { index = numberOfSpeciesDensities() - 1; }
      speciesDensities.remove(aSpeciesDensity);
      speciesDensities.add(index, aSpeciesDensity);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSpeciesDensityAt(SpeciesDensities aSpeciesDensity, int index)
  {
    boolean wasAdded = false;
    if(speciesDensities.contains(aSpeciesDensity))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSpeciesDensities()) { index = numberOfSpeciesDensities() - 1; }
      speciesDensities.remove(aSpeciesDensity);
      speciesDensities.add(index, aSpeciesDensity);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSpeciesDensityAt(aSpeciesDensity, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<CarbonSequestration> copyOfCarbonSequestration = new ArrayList<CarbonSequestration>(carbonSequestration);
    carbonSequestration.clear();
    for(CarbonSequestration aCarbonSequestration : copyOfCarbonSequestration)
    {
      aCarbonSequestration.removeCarbonSequestrationManager(this);
    }
    while (speciesDensities.size() > 0)
    {
      SpeciesDensities aSpeciesDensity = speciesDensities.get(speciesDensities.size() - 1);
      aSpeciesDensity.delete();
      speciesDensities.remove(aSpeciesDensity);
    } 
  }

}
