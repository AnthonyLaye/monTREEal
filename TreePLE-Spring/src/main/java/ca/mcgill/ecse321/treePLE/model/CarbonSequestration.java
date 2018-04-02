/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3860.40605acef modeling language!*/

package ca.mcgill.ecse321.treePLE.model;
import java.util.*;

// line 58 "../../../../../../../../ump/180117838895/model.ump"
// line 112 "../../../../../../../../ump/180117838895/model.ump"
public class CarbonSequestration
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //CarbonSequestration Attributes
  private double carbonSequestrationIndex;

  //CarbonSequestration Associations
  private List<Forecast> forecast;
  private List<CarbonSequestrationManager> carbonSequestrationManagers;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public CarbonSequestration(double aCarbonSequestrationIndex)
  {
    carbonSequestrationIndex = aCarbonSequestrationIndex;
    forecast = new ArrayList<Forecast>();
    carbonSequestrationManagers = new ArrayList<CarbonSequestrationManager>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCarbonSequestrationIndex(double aCarbonSequestrationIndex)
  {
    boolean wasSet = false;
    carbonSequestrationIndex = aCarbonSequestrationIndex;
    wasSet = true;
    return wasSet;
  }

  public double getCarbonSequestrationIndex()
  {
    return carbonSequestrationIndex;
  }
  /* Code from template association_GetMany */
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
  /* Code from template association_GetMany */
  public CarbonSequestrationManager getCarbonSequestrationManager(int index)
  {
    CarbonSequestrationManager aCarbonSequestrationManager = carbonSequestrationManagers.get(index);
    return aCarbonSequestrationManager;
  }

  public List<CarbonSequestrationManager> getCarbonSequestrationManagers()
  {
    List<CarbonSequestrationManager> newCarbonSequestrationManagers = Collections.unmodifiableList(carbonSequestrationManagers);
    return newCarbonSequestrationManagers;
  }

  public int numberOfCarbonSequestrationManagers()
  {
    int number = carbonSequestrationManagers.size();
    return number;
  }

  public boolean hasCarbonSequestrationManagers()
  {
    boolean has = carbonSequestrationManagers.size() > 0;
    return has;
  }

  public int indexOfCarbonSequestrationManager(CarbonSequestrationManager aCarbonSequestrationManager)
  {
    int index = carbonSequestrationManagers.indexOf(aCarbonSequestrationManager);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfForecast()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addForecast(Forecast aForecast)
  {
    boolean wasAdded = false;
    if (forecast.contains(aForecast)) { return false; }
    forecast.add(aForecast);
    if (aForecast.indexOfCarbonSequestration(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aForecast.addCarbonSequestration(this);
      if (!wasAdded)
      {
        forecast.remove(aForecast);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeForecast(Forecast aForecast)
  {
    boolean wasRemoved = false;
    if (!forecast.contains(aForecast))
    {
      return wasRemoved;
    }

    int oldIndex = forecast.indexOf(aForecast);
    forecast.remove(oldIndex);
    if (aForecast.indexOfCarbonSequestration(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aForecast.removeCarbonSequestration(this);
      if (!wasRemoved)
      {
        forecast.add(oldIndex,aForecast);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCarbonSequestrationManagers()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addCarbonSequestrationManager(CarbonSequestrationManager aCarbonSequestrationManager)
  {
    boolean wasAdded = false;
    if (carbonSequestrationManagers.contains(aCarbonSequestrationManager)) { return false; }
    carbonSequestrationManagers.add(aCarbonSequestrationManager);
    if (aCarbonSequestrationManager.indexOfCarbonSequestration(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aCarbonSequestrationManager.addCarbonSequestration(this);
      if (!wasAdded)
      {
        carbonSequestrationManagers.remove(aCarbonSequestrationManager);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeCarbonSequestrationManager(CarbonSequestrationManager aCarbonSequestrationManager)
  {
    boolean wasRemoved = false;
    if (!carbonSequestrationManagers.contains(aCarbonSequestrationManager))
    {
      return wasRemoved;
    }

    int oldIndex = carbonSequestrationManagers.indexOf(aCarbonSequestrationManager);
    carbonSequestrationManagers.remove(oldIndex);
    if (aCarbonSequestrationManager.indexOfCarbonSequestration(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aCarbonSequestrationManager.removeCarbonSequestration(this);
      if (!wasRemoved)
      {
        carbonSequestrationManagers.add(oldIndex,aCarbonSequestrationManager);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addCarbonSequestrationManagerAt(CarbonSequestrationManager aCarbonSequestrationManager, int index)
  {  
    boolean wasAdded = false;
    if(addCarbonSequestrationManager(aCarbonSequestrationManager))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCarbonSequestrationManagers()) { index = numberOfCarbonSequestrationManagers() - 1; }
      carbonSequestrationManagers.remove(aCarbonSequestrationManager);
      carbonSequestrationManagers.add(index, aCarbonSequestrationManager);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCarbonSequestrationManagerAt(CarbonSequestrationManager aCarbonSequestrationManager, int index)
  {
    boolean wasAdded = false;
    if(carbonSequestrationManagers.contains(aCarbonSequestrationManager))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCarbonSequestrationManagers()) { index = numberOfCarbonSequestrationManagers() - 1; }
      carbonSequestrationManagers.remove(aCarbonSequestrationManager);
      carbonSequestrationManagers.add(index, aCarbonSequestrationManager);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCarbonSequestrationManagerAt(aCarbonSequestrationManager, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<Forecast> copyOfForecast = new ArrayList<Forecast>(forecast);
    forecast.clear();
    for(Forecast aForecast : copyOfForecast)
    {
      aForecast.removeCarbonSequestration(this);
    }
    ArrayList<CarbonSequestrationManager> copyOfCarbonSequestrationManagers = new ArrayList<CarbonSequestrationManager>(carbonSequestrationManagers);
    carbonSequestrationManagers.clear();
    for(CarbonSequestrationManager aCarbonSequestrationManager : copyOfCarbonSequestrationManagers)
    {
      aCarbonSequestrationManager.removeCarbonSequestration(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "carbonSequestrationIndex" + ":" + getCarbonSequestrationIndex()+ "]";
  }
}