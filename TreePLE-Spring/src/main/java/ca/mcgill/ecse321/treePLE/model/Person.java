/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.1.3862.7e232c4b4 modeling language!*/

package ca.mcgill.ecse321.treePLE.model;
import java.util.*;

import java.sql.Date;

// line 16 "../../../../../../../../ump/tmp273475/model.ump"
// line 75 "../../../../../../../../ump/tmp273475/model.ump"
public class Person
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Person Attributes
  private String name;
  private String email;
  private String password;
  private Role role;
  
  //------------------------
  // ENUMERATION
  //------------------------
  
  public enum Role { Unspecified, Resident, Scientist }

  //Person Associations
  private List<Tree> tree;
  private TreePLEManager treePLEManager;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Person(String aName, String aEmail, String aPassword, TreePLEManager aTreePLEManager)
  {
    name = aName;
    email = aEmail;
    password = aPassword;
    role = Role.Unspecified;
    tree = new ArrayList<Tree>();
    boolean didAddTreePLEManager = setTreePLEManager(aTreePLEManager);
    if (!didAddTreePLEManager)
    {
      throw new RuntimeException("Unable to create person due to treePLEManager");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  
  
  public void setRole(Role aRole) {
	  role = aRole;
  }
  
  public Role getRole() {
	  return role;
  }
  
  public String getRoleName() {
	  String roleName = role.toString();
	  return roleName;
}

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    email = aEmail;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getEmail()
  {
    return email;
  }

  public String getPassword()
  {
    return password;
  }
  /* Code from template association_GetMany */
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
  /* Code from template association_GetOne */
  public TreePLEManager getTreePLEManager()
  {
    return treePLEManager;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTree()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Tree addTree(String aSpecies, float aHeight, int aAge, Date aDate, float aDiameter, int aId, TreePLEManager aTreePLEManager, Location aLocation)
  {
    return new Tree(aSpecies, aHeight, aAge, aDate, aDiameter, aId, this, aTreePLEManager, aLocation);
  }

  public boolean addTree(Tree aTree)
  {
    boolean wasAdded = false;
    if (tree.contains(aTree)) { return false; }
    Person existingPerson = aTree.getPerson();
    boolean isNewPerson = existingPerson != null && !this.equals(existingPerson);
    if (isNewPerson)
    {
      aTree.setPerson(this);
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
    //Unable to remove aTree, as it must always have a person
    if (!this.equals(aTree.getPerson()))
    {
      tree.remove(aTree);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
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
  /* Code from template association_SetOneToMany */
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
      existingTreePLEManager.removePerson(this);
    }
    treePLEManager.addPerson(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=tree.size(); i > 0; i--)
    {
      Tree aTree = tree.get(i - 1);
      aTree.delete();
    }
    TreePLEManager placeholderTreePLEManager = treePLEManager;
    this.treePLEManager = null;
    if(placeholderTreePLEManager != null)
    {
      placeholderTreePLEManager.removePerson(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "email" + ":" + getEmail()+ "," +
            "password" + ":" + getPassword()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "treePLEManager = "+(getTreePLEManager()!=null?Integer.toHexString(System.identityHashCode(getTreePLEManager())):"null");
  }
}