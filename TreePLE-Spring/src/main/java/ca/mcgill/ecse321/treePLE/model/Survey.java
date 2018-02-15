/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package ca.mcgill.ecse321.treePLE.model;
import java.sql.Date;
import java.util.*;

// line 36 "../../../../../TreePLEModel.ump"
public class Survey
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Survey Attributes
  private Date date;

  //Survey Associations
  private Tree tree;
  private List<Scientist> scientists;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Survey(Date aDate, Tree aTree)
  {
    date = aDate;
    boolean didAddTree = setTree(aTree);
    if (!didAddTree)
    {
      throw new RuntimeException("Unable to create survey due to tree");
    }
    scientists = new ArrayList<Scientist>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public Date getDate()
  {
    return date;
  }

  public Tree getTree()
  {
    return tree;
  }

  public Scientist getScientist(int index)
  {
    Scientist aScientist = scientists.get(index);
    return aScientist;
  }

  public List<Scientist> getScientists()
  {
    List<Scientist> newScientists = Collections.unmodifiableList(scientists);
    return newScientists;
  }

  public int numberOfScientists()
  {
    int number = scientists.size();
    return number;
  }

  public boolean hasScientists()
  {
    boolean has = scientists.size() > 0;
    return has;
  }

  public int indexOfScientist(Scientist aScientist)
  {
    int index = scientists.indexOf(aScientist);
    return index;
  }

  public boolean setTree(Tree aTree)
  {
    boolean wasSet = false;
    if (aTree == null)
    {
      return wasSet;
    }

    Tree existingTree = tree;
    tree = aTree;
    if (existingTree != null && !existingTree.equals(aTree))
    {
      existingTree.removeSurvey(this);
    }
    tree.addSurvey(this);
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfScientists()
  {
    return 0;
  }

  public boolean addScientist(Scientist aScientist)
  {
    boolean wasAdded = false;
    if (scientists.contains(aScientist)) { return false; }
    scientists.add(aScientist);
    if (aScientist.indexOfSurvey(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aScientist.addSurvey(this);
      if (!wasAdded)
      {
        scientists.remove(aScientist);
      }
    }
    return wasAdded;
  }

  public boolean removeScientist(Scientist aScientist)
  {
    boolean wasRemoved = false;
    if (!scientists.contains(aScientist))
    {
      return wasRemoved;
    }

    int oldIndex = scientists.indexOf(aScientist);
    scientists.remove(oldIndex);
    if (aScientist.indexOfSurvey(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aScientist.removeSurvey(this);
      if (!wasRemoved)
      {
        scientists.add(oldIndex,aScientist);
      }
    }
    return wasRemoved;
  }

  public boolean addScientistAt(Scientist aScientist, int index)
  {  
    boolean wasAdded = false;
    if(addScientist(aScientist))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfScientists()) { index = numberOfScientists() - 1; }
      scientists.remove(aScientist);
      scientists.add(index, aScientist);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveScientistAt(Scientist aScientist, int index)
  {
    boolean wasAdded = false;
    if(scientists.contains(aScientist))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfScientists()) { index = numberOfScientists() - 1; }
      scientists.remove(aScientist);
      scientists.add(index, aScientist);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addScientistAt(aScientist, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Tree placeholderTree = tree;
    this.tree = null;
    placeholderTree.removeSurvey(this);
    ArrayList<Scientist> copyOfScientists = new ArrayList<Scientist>(scientists);
    scientists.clear();
    for(Scientist aScientist : copyOfScientists)
    {
      aScientist.removeSurvey(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "tree = "+(getTree()!=null?Integer.toHexString(System.identityHashCode(getTree())):"null");
  }
}