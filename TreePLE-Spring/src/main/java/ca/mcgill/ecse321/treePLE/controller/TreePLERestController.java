package ca.mcgill.ecse321.treePLE.controller;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

import ca.mcgill.ecse321.treePLE.dto.PersonDto;
import ca.mcgill.ecse321.treePLE.dto.SurveyDto;
import ca.mcgill.ecse321.treePLE.dto.TreeDto;
import ca.mcgill.ecse321.treePLE.model.Location;
import ca.mcgill.ecse321.treePLE.model.Person;
import ca.mcgill.ecse321.treePLE.model.Survey;
import ca.mcgill.ecse321.treePLE.model.Tree;
import ca.mcgill.ecse321.treePLE.service.InvalidInputException;
import ca.mcgill.ecse321.treePLE.service.TreePLEService;

@RestController
public class TreePLERestController {


    @Autowired
    private TreePLEService service;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping("/")
    public String index() {
        return "TreePLE application root. Web-based frontend is a TODO. Use the REST API to manage trees.\n";
    }

    //----------------------------------
    //   Conversion Methods
    //----------------------------------
    private PersonDto convertToDto(Person p) {
    	PersonDto personDto = modelMapper.map(p, PersonDto.class);
    	return personDto;
    }

//    private MunicipalityDto convertToDto(Municipality m) {
//    	return modelMapper.map(m, MunicipalityDto.class);
//    }
//    
//    private ResidentDto convertToDto(Resident r) {
//        ResidentDto residentDto = modelMapper.map(r, ResidentDto.class);
//        return residentDto;
//    }
//
//    private ScientistDto convertToDto(Scientist s) {
//        ScientistDto scientistDto = modelMapper.map(s, ScientistDto.class);
//        return scientistDto;
//    }
    
    private SurveyDto convertToDto(Survey s) {
    	SurveyDto surveyDto = modelMapper.map(s, SurveyDto.class);
    	return surveyDto;
    }

    private TreeDto convertToDto(Tree t) {
        TreeDto treeDto = modelMapper.map(t, TreeDto.class);
        return treeDto;
    }

    //----------------------------------
    //   POST Methods
    //----------------------------------
//status: successful
    @PostMapping(value = { "/trees/{species}", "/trees/{species}/" })
    public TreeDto createTree(
            @PathVariable("species") String species,
            @RequestParam int height,
            @RequestParam int age,
            @RequestParam Date date,
            @RequestParam float diameter,
            @RequestParam String personName,
            @RequestParam double longitude,
            @RequestParam double latitude,
            @RequestParam String municipality) throws InvalidInputException {

        Person treeOwner = new Person(personName, service.tm);
        int randomNum = ThreadLocalRandom.current().nextInt(10000000, 99999998 + 1);
        Location location = new Location((float) longitude, (float) latitude, municipality);

        Tree tree = service.createTree(species, height, age, date, diameter, randomNum, treeOwner, location);
        return convertToDto(tree);
    }
    
  //status: successful    
    @PostMapping(value = { "/base/trees/{species}", "/base/trees/{species}/" })
    public TreeDto createTree(
            @PathVariable("species") String species,
            @RequestParam Date date,
            @RequestParam String personName,
            @RequestParam float longitude,
            @RequestParam float latitude,
            @RequestParam String municipality) throws InvalidInputException {

        Person treeOwner = new Person(personName, service.tm);
        int randomNum = ThreadLocalRandom.current().nextInt(10000000, 99999998 + 1);
        Location location = new Location(longitude, latitude, municipality);

        Tree tree = service.createTree(species, date, randomNum, treeOwner, location);
        return convertToDto(tree);
    }
    
  //status: successful    
    @PostMapping(value = { "/tree/{id}", "/tree/{id}/" })
    public boolean cutDownTree(@PathVariable("id") int id) throws InvalidInputException {
    	boolean wasCutDown = false;
    	wasCutDown = service.cutDownTree(id);
    	return wasCutDown;
    }
    
  //status: incorrect, gives true the first time, then correct...
    @PostMapping(value = { "/markCutDown/tree/{id}", "/markCutDown/tree/{id}/" })
    public boolean markForCutDown(@PathVariable("id") int id) throws InvalidInputException {
    	boolean markedForCutDown = false;
    	markedForCutDown = service.markTreeForCutDown(id);
    	return markedForCutDown;
    }
    
  //status: incorrect, gives true the first time, then correct...
    @PostMapping(value = { "/markDiseased/tree/{id}", "/markDiseased/tree/{id}/" })
    public boolean markDiseased(@PathVariable("id") int id) throws InvalidInputException {
    	boolean markedDiseased = false;
    	markedDiseased = service.markTreeDiseased(id);
    	return markedDiseased;
    }

  //status: tested, rn gives something empty since none are create... and cant be changed...
    @PostMapping(value = { "/survey/tree/{treeId}", "/survey/tree/{treeId}/" })
    public SurveyDto createSurvey(
            @PathVariable("treeId") int treeId, 
            @RequestParam Date date,
            @RequestParam String personName) throws InvalidInputException {

        Person observer = new Person(personName, service.tm);
        int randomNum = ThreadLocalRandom.current().nextInt(1000000, 9999998 + 1);
       
        Survey survey = service.createSurvey(date, randomNum, observer, treeId);
        return convertToDto(survey);
    }

    //----------------------------------
    //   GET Methods
    //----------------------------------
    
//status: successful    
    @GetMapping(value = { "/trees", "/trees/" })
    public List<TreeDto> findAllTrees() throws InvalidInputException{
        List<TreeDto> trees = Lists.newArrayList();
        for (Tree tree : service.findAllTrees()) {
            trees.add(convertToDto(tree));
        }
        return trees;
    }

    //status: successful    
    @GetMapping(value = { "/trees/resident/{name}", "/trees/resident/{name}" })
    public List<TreeDto> findTreesForResident(@PathVariable ("name") String name) {
        List<TreeDto> residentTrees = Lists.newArrayList();
        for (Tree tree : service.findTreesForResident(name)) {
            residentTrees.add(convertToDto(tree));
        }
        return residentTrees;
    }
    
//status: successful   
    @GetMapping(value = {"/trees/municipality/{municipality}", "/trees/municipality/{municipality}/" })
    public List<TreeDto> findTreesByMunicipality(@PathVariable("municipality")String municipality)
    		throws InvalidInputException{
    	List<TreeDto> treesOfMuniciplity = Lists.newArrayList();
    	for (Tree tree: service.getTreesByMunicipality(municipality)) {
    		treesOfMuniciplity.add(convertToDto(tree));
    	}
    	return treesOfMuniciplity;
    }

  //status: successful     
    @GetMapping(value = {"/trees/species/{name}", "/trees/species/{name}/"})
    public List<TreeDto> findTreesBySpecies(@PathVariable("name")String species) throws InvalidInputException {
    	List<TreeDto> treesBySpecies = Lists.newArrayList();
    	for (Tree tree: service.getAllTreesBySpecies(species)){
    		treesBySpecies.add(convertToDto(tree));
    	}
    	return treesBySpecies;
    }

//status: in process, not tested check for the value if this is correct?
    //seem to not take into consideration the radius
    @GetMapping(value = {"/trees/position/", "/trees/position"})
    public List<TreeDto> findTreesByArea(@RequestParam float latitude, 
    		@RequestParam float longitude, 
    		@RequestParam float radius) throws InvalidInputException{
    	List<TreeDto> treesInArea = Lists.newArrayList();
    	for (Tree tree: service.getTreesByArea(latitude, longitude, radius)) {
    		treesInArea.add(convertToDto(tree));
    	}
    	return treesInArea;
    }
    //status: 2018-04-02 6:15pm : functional, listArea not returning a large list
    //status: in process, does this take in param Trees or TreeDto
    //does this take in param Trees or TreeDto
    //rn this returns a string... makes absolutely no sense
    @GetMapping(value = {"/trees/forecast/biodiversity", "/trees/forecast/biodiversity/"})
    public double getBiodiversityIndex(@RequestParam float latitude, 
    		@RequestParam float longitude, @RequestParam float radius) 
    				throws InvalidInputException{
    	double biodiversityIndex=0;
    	List<Tree> treesInArea;
    	treesInArea = service.getTreesByArea(latitude,longitude, radius);
    	biodiversityIndex=service.calculateBiodiversityIndex(treesInArea);
    	return biodiversityIndex;
    }
    
    //status: needs testing, because affected by getTreesByArea
    @GetMapping(value = {"/trees/forecast/carbonsequestration", "/trees/forecast/carbonsequestration/"})
    public double getCarbonSequestrationIndex(@RequestParam float latitude, 
    		@RequestParam float longitude, @RequestParam float radius) 
    				throws InvalidInputException{
    	double carbonSequestration=0;
    	List<Tree> treesInArea;
    	treesInArea = service.getTreesByArea(latitude,longitude, radius);
    	carbonSequestration=service.calculateCarbonSequestration(treesInArea);
    	return carbonSequestration;
    }
}
