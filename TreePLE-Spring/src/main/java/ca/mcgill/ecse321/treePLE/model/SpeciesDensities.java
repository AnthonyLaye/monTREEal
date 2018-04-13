/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.1.3862.7e232c4b4 modeling language!*/

package ca.mcgill.ecse321.treePLE.model;

// line 63 "../../../../../../../../ump/tmp859800/model.ump"
// line 119 "../../../../../../../../ump/tmp859800/model.ump"
public class SpeciesDensities
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SpeciesDensities Attributes
  private String species;
  private int density;
  private String UISpecies;

  //SpeciesDensities Associations
  private CarbonSequestrationManager CarbonSequestrationManager;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SpeciesDensities(String aSpecies, int aDensity, String aUISpecies, CarbonSequestrationManager aCarbonSequestrationManager)
  {
    species = aSpecies;
    density = aDensity;
    UISpecies = aUISpecies;
    boolean didAddCarbonSequestrationManager = setCarbonSequestrationManager(aCarbonSequestrationManager);
    if (!didAddCarbonSequestrationManager)
    {
      throw new RuntimeException("Unable to create speciesDensity due to CarbonSequestrationManager");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setSpecies(String aSpecies)
  {
    boolean wasSet = false;
    species = aSpecies;
    wasSet = true;
    return wasSet;
  }

  public boolean setDensity(int aDensity)
  {
    boolean wasSet = false;
    density = aDensity;
    wasSet = true;
    return wasSet;
  }

  public boolean setUISpecies(String aUISpecies)
  {
    boolean wasSet = false;
    UISpecies = aUISpecies;
    wasSet = true;
    return wasSet;
  }

  public String getSpecies()
  {
    return species;
  }

  public int getDensity()
  {
    return density;
  }

  public String getUISpecies()
  {
    return UISpecies;
  }
  /* Code from template association_GetOne */
  public CarbonSequestrationManager getCarbonSequestrationManager()
  {
    return CarbonSequestrationManager;
  }
  /* Code from template association_SetOneToMany */
  public boolean setCarbonSequestrationManager(CarbonSequestrationManager aCarbonSequestrationManager)
  {
    boolean wasSet = false;
    if (aCarbonSequestrationManager == null)
    {
      return wasSet;
    }

    CarbonSequestrationManager existingCarbonSequestrationManager = CarbonSequestrationManager;
    CarbonSequestrationManager = aCarbonSequestrationManager;
    if (existingCarbonSequestrationManager != null && !existingCarbonSequestrationManager.equals(aCarbonSequestrationManager))
    {
      existingCarbonSequestrationManager.removeSpeciesDensity(this);
    }
    CarbonSequestrationManager.addSpeciesDensity(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    CarbonSequestrationManager placeholderCarbonSequestrationManager = CarbonSequestrationManager;
    this.CarbonSequestrationManager = null;
    if(placeholderCarbonSequestrationManager != null)
    {
      placeholderCarbonSequestrationManager.removeSpeciesDensity(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "species" + ":" + getSpecies()+ "," +
            "density" + ":" + getDensity()+ "," +
            "UISpecies" + ":" + getUISpecies()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "CarbonSequestrationManager = "+(getCarbonSequestrationManager()!=null?Integer.toHexString(System.identityHashCode(getCarbonSequestrationManager())):"null");
  }
}
