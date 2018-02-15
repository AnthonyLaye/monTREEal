/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package ca.mcgill.ecse321.treePLE.model;
import java.util.*;

// line 26 "../../../../../TreePLEModel.ump"
public class Scientist extends Person
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Scientist Associations
  private List<Survey> surveys;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Scientist(String aName, Municipality aMunicipality)
  {
    super(aName, aMunicipality);
    surveys = new ArrayList<Survey>();
  }

  //------------------------
  // INTERFACE
  //------------------------

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

  public static int minimumNumberOfSurveys()
  {
    return 0;
  }

  public boolean addSurvey(Survey aSurvey)
  {
    boolean wasAdded = false;
    if (surveys.contains(aSurvey)) { return false; }
    surveys.add(aSurvey);
    if (aSurvey.indexOfScientist(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aSurvey.addScientist(this);
      if (!wasAdded)
      {
        surveys.remove(aSurvey);
      }
    }
    return wasAdded;
  }

  public boolean removeSurvey(Survey aSurvey)
  {
    boolean wasRemoved = false;
    if (!surveys.contains(aSurvey))
    {
      return wasRemoved;
    }

    int oldIndex = surveys.indexOf(aSurvey);
    surveys.remove(oldIndex);
    if (aSurvey.indexOfScientist(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aSurvey.removeScientist(this);
      if (!wasRemoved)
      {
        surveys.add(oldIndex,aSurvey);
      }
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

  public void delete()
  {
    ArrayList<Survey> copyOfSurveys = new ArrayList<Survey>(surveys);
    surveys.clear();
    for(Survey aSurvey : copyOfSurveys)
    {
      aSurvey.removeScientist(this);
    }
    super.delete();
  }

}