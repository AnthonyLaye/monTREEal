/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package ca.mcgill.ecse321.treePLE.model;
import java.util.*;

// line 43 "../../../../../TreePLEModel.ump"
public class Biodiversity
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Biodiversity Attributes
  private double biodiversityIndex;

  //Biodiversity Associations
  private List<Forecast> forecast;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Biodiversity(double aBiodiversityIndex)
  {
    biodiversityIndex = aBiodiversityIndex;
    forecast = new ArrayList<Forecast>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setBiodiversityIndex(double aBiodiversityIndex)
  {
    boolean wasSet = false;
    biodiversityIndex = aBiodiversityIndex;
    wasSet = true;
    return wasSet;
  }

  public double getBiodiversityIndex()
  {
    return biodiversityIndex;
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

  public static int minimumNumberOfForecast()
  {
    return 0;
  }

  public boolean addForecast(Forecast aForecast)
  {
    boolean wasAdded = false;
    if (forecast.contains(aForecast)) { return false; }
    forecast.add(aForecast);
    if (aForecast.indexOfBiodiversity(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aForecast.addBiodiversity(this);
      if (!wasAdded)
      {
        forecast.remove(aForecast);
      }
    }
    return wasAdded;
  }

  public boolean removeForecast(Forecast aForecast)
  {
    boolean wasRemoved = false;
    if (!forecast.contains(aForecast))
    {
      return wasRemoved;
    }

    int oldIndex = forecast.indexOf(aForecast);
    forecast.remove(oldIndex);
    if (aForecast.indexOfBiodiversity(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aForecast.removeBiodiversity(this);
      if (!wasRemoved)
      {
        forecast.add(oldIndex,aForecast);
      }
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

  public void delete()
  {
    ArrayList<Forecast> copyOfForecast = new ArrayList<Forecast>(forecast);
    forecast.clear();
    for(Forecast aForecast : copyOfForecast)
    {
      aForecast.removeBiodiversity(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "biodiversityIndex" + ":" + getBiodiversityIndex()+ "]";
  }
}