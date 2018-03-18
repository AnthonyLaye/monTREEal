/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package ca.mcgill.ecse321.treePLE.model;
import java.sql.Date;

// line 25 "../../../../../TreePLEModel.ump"
public class Survey
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Survey Attributes
  private Date date;
  private int id;

  //Survey Associations
  private Person observer;
  private Tree tree;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Survey(Date aDate, int aId, Person aObserver, Tree aTree)
  {
    date = aDate;
    id = aId;
    if (!setObserver(aObserver))
    {
      throw new RuntimeException("Unable to create Survey due to aObserver");
    }
    boolean didAddTree = setTree(aTree);
    if (!didAddTree)
    {
      throw new RuntimeException("Unable to create survey due to tree");
    }
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

  public boolean setId(int aId)
  {
    boolean wasSet = false;
    id = aId;
    wasSet = true;
    return wasSet;
  }

  public Date getDate()
  {
    return date;
  }

  public int getId()
  {
    return id;
  }

  public Person getObserver()
  {
    return observer;
  }

  public Tree getTree()
  {
    return tree;
  }

  public boolean setObserver(Person aNewObserver)
  {
    boolean wasSet = false;
    if (aNewObserver != null)
    {
      observer = aNewObserver;
      wasSet = true;
    }
    return wasSet;
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

  public void delete()
  {
    observer = null;
    Tree placeholderTree = tree;
    this.tree = null;
    placeholderTree.removeSurvey(this);
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "observer = "+(getObserver()!=null?Integer.toHexString(System.identityHashCode(getObserver())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "tree = "+(getTree()!=null?Integer.toHexString(System.identityHashCode(getTree())):"null");
  }
}