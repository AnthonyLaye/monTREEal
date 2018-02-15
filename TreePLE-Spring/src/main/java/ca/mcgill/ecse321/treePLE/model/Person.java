/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package ca.mcgill.ecse321.treePLE.model;

// line 17 "../../../../../TreePLEModel.ump"
public class Person
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Person Attributes
  private String name;

  //Person Associations
  private Municipality municipality;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Person(String aName, Municipality aMunicipality)
  {
    name = aName;
    boolean didAddMunicipality = setMunicipality(aMunicipality);
    if (!didAddMunicipality)
    {
      throw new RuntimeException("Unable to create person due to municipality");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public Municipality getMunicipality()
  {
    return municipality;
  }

  public boolean setMunicipality(Municipality aMunicipality)
  {
    boolean wasSet = false;
    if (aMunicipality == null)
    {
      return wasSet;
    }

    Municipality existingMunicipality = municipality;
    municipality = aMunicipality;
    if (existingMunicipality != null && !existingMunicipality.equals(aMunicipality))
    {
      existingMunicipality.removePerson(this);
    }
    municipality.addPerson(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Municipality placeholderMunicipality = municipality;
    this.municipality = null;
    placeholderMunicipality.removePerson(this);
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "municipality = "+(getMunicipality()!=null?Integer.toHexString(System.identityHashCode(getMunicipality())):"null");
  }
}