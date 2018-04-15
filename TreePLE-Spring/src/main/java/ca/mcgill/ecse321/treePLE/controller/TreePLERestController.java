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
import ca.mcgill.ecse321.treePLE.model.Tree.Status;
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
    @PostMapping(value = { "/trees/{species}", "/trees/{species}/" })
    public TreeDto createTree(
            @PathVariable("species") String species,
            @RequestParam float height,
            @RequestParam int age,
            @RequestParam Date date,
            @RequestParam float diameter,
            @RequestParam String personName,
            @RequestParam double longitude,
            @RequestParam double latitude,
            @RequestParam String municipality) throws InvalidInputException {

        //Person treeOwner = new Person(personName, service.tm);
        Person treeOwner = new Person(personName, "", "", service.tm);
        int randomNum = ThreadLocalRandom.current().nextInt(10000000, 99999998 + 1);
        Location location = new Location((float) longitude, (float) latitude, municipality);

        Tree tree = service.createTree(species, height, age, date, diameter, randomNum, treeOwner, location);
        return convertToDto(tree);
    }

    @PostMapping(value = { "/base/trees/{species}", "/base/trees/{species}/" })
    public TreeDto createTree(
            @PathVariable("species") String species,
            @RequestParam Date date,
            @RequestParam String personName,
            @RequestParam float longitude,
            @RequestParam float latitude,
            @RequestParam String municipality) throws InvalidInputException {

        //Person treeOwner = new Person(personName, service.tm);
        Person treeOwner = new Person(personName, "", "", service.tm);
        int randomNum = ThreadLocalRandom.current().nextInt(10000000, 99999998 + 1);
        Location location = new Location(longitude, latitude, municipality);

        Tree tree = service.createTree(species, date, randomNum, treeOwner, location);
        return convertToDto(tree);
    }

       
    @PostMapping(value = { "cutDown/tree/{id}", "/tree/{id}/" })
    public boolean cutDownTree(@PathVariable("id") int id) throws InvalidInputException {
    	boolean wasCutDown = false;
    	wasCutDown = service.cutDownTree(id);
    	return wasCutDown;
    }
    
    @PostMapping(value = { "/markCutDown/tree/{id}", "/markCutDown/tree/{id}/" })
    public boolean markForCutDown(@PathVariable("id") int id) throws InvalidInputException {
    	boolean markedForCutDown = false;
    	markedForCutDown = service.markTreeForCutDown(id);
    	return markedForCutDown;
    }
    
    @PostMapping(value = { "/markDiseased/tree/{id}", "/markDiseased/tree/{id}/" })
    public boolean markDiseased(@PathVariable("id") int id) throws InvalidInputException {
    	boolean markedDiseased = false;
    	markedDiseased = service.markTreeDiseased(id);
    	return markedDiseased;
    }
    
    @PostMapping(value = { "/markHealthy/tree/{id}", "/markHealthy/tree/{id}/" })
    public boolean markHealthy(@PathVariable("id") int id) throws InvalidInputException {
    	boolean markedHealthy= false;
    	markedHealthy = service.markTreeHealthy(id);
    	return markedHealthy;
    }

    @PostMapping(value = { "/survey/tree/{treeId}", "/survey/tree/{treeId}/" })
    public TreeDto createSurvey(
            @PathVariable("treeId") int treeId, 
            @RequestParam Date date,
            @RequestParam String personName,
            @RequestParam float height,
            @RequestParam float diameter,
            @RequestParam String status) {//throws InvalidInputException {

        //Person observer = new Person(personName, service.tm);
        Person observer = new Person(personName,"","", service.tm);
        int randomNum = ThreadLocalRandom.current().nextInt(1000000, 9999998 + 1);
        
        //boolean wasAdded = false;
        Tree treeAdded = service.createSurvey(date, randomNum, observer, treeId, height, diameter, status);
        return convertToDto(treeAdded);
    }
    
    @PostMapping(value= { "/register/{name}", "/register/{name}/" })
    public PersonDto register(@PathVariable("name")String name,
    		@RequestParam String email, @RequestParam String password, 
    		@RequestParam String role) throws InvalidInputException {
    	Person p = service.register(name, email, password, role);
    	PersonDto person = convertToDto(p);
    	return person;
    }

    //----------------------------------
    //   GET Methods
    //---------------------------------- 
    
    @GetMapping(value = { "/login", "/login/" })
    public String login(@RequestParam String email, @RequestParam String password) 
    		throws InvalidInputException{
    	String role;
    	role=service.login(email, password);
    	return role;
    }
    
    @GetMapping(value = { "/trees", "/trees/" })
    public List<TreeDto> findAllTrees() throws InvalidInputException{
        List<TreeDto> trees = Lists.newArrayList();
        for (Tree tree : service.findAllTrees()) {
            trees.add(convertToDto(tree));
        }
        return trees;
    }
    
   
    @GetMapping(value = { "/trees/resident/{name}", "/trees/resident/{name}/" })
    public List<TreeDto> findTreesForResident(@PathVariable ("name") String name) {
        List<TreeDto> residentTrees = Lists.newArrayList();
        for (Tree tree : service.findTreesForResident(name)) {
            residentTrees.add(convertToDto(tree));
        }
        return residentTrees;
    }
       
    @GetMapping(value = {"/trees/municipality/{municipality}", "/trees/municipality/{municipality}/" })
    public List<TreeDto> findTreesByMunicipality(@PathVariable("municipality")String municipality)
    		throws InvalidInputException{
    	List<TreeDto> treesOfMuniciplity = Lists.newArrayList();
    	for (Tree tree: service.getTreesByMunicipality(municipality)) {
    		treesOfMuniciplity.add(convertToDto(tree));
    	}
    	return treesOfMuniciplity;
    }
     
    @GetMapping(value = {"/trees/species/{name}", "/trees/species/{name}/"})
    public List<TreeDto> findTreesBySpecies(@PathVariable("name")String species) throws InvalidInputException {
    	List<TreeDto> treesBySpecies = Lists.newArrayList();
    	for (Tree tree: service.getAllTreesBySpecies(species)){
    		treesBySpecies.add(convertToDto(tree));
    	}
    	return treesBySpecies;
    }

    @GetMapping(value = {"/trees/position/", "/trees/position"})
    public List<TreeDto> findTreesByArea(@RequestParam float latitude, 
    		@RequestParam float longitude, 
    		@RequestParam float distance) throws InvalidInputException{
    	List<TreeDto> treesInArea = Lists.newArrayList();
    	//for (Tree tree: service.getTreesByAreaRevised(latitude, longitude, distance)) {
    	for (Tree tree: service.getTreesByArea(latitude, longitude, distance)) {
    		treesInArea.add(convertToDto(tree));
    	}
    	return treesInArea;
    }

    @GetMapping(value = {"/trees/forecast/biodiversityindex", "/trees/forecast/biodiversityindex/"})
    public double getBiodiversityIndexFromTrees(@RequestParam List<String> treesInArea)
            throws InvalidInputException{
        double biodiversityIndex=0;
        biodiversityIndex=service.calculateBiodiversityIndexFromTrees(treesInArea);
        return biodiversityIndex;
    }
    
    @GetMapping(value = {"/trees/forecast/biodiversity", "/trees/forecast/biodiversity/"})
    public double getBiodiversityIndex(@RequestParam float latitude, 
    		@RequestParam float longitude, @RequestParam float distance) 
    				throws InvalidInputException{
    	double biodiversityIndex=0;
    	List<Tree> treesInArea;
    	treesInArea = service.getTreesByArea(latitude, longitude, distance);
    	biodiversityIndex=service.calculateBiodiversityIndex(treesInArea);
    	return biodiversityIndex;
    }
    /*
    @GetMapping(value = {"/trees/forecast/carbonsequestration", "/trees/forecast/carbonsequestration/"})
    public double getCarbonSequestrationIndex(@RequestParam List<Tree> treesInArea)
    				throws InvalidInputException{
    	double carbonSequestration=0;
    	//treesInArea = service.getTreesByArea(latitude, longitude, distance);
    	carbonSequestration=service.calculateCarbonSequestration(treesInArea);
    	return carbonSequestration;
    }
    */

    @GetMapping(value = {"/trees/forecast/carbonsequestration", "/trees/forecast/carbonsequestration/"})
    public double getCarbonSequestrationIndexFromTrees(@RequestParam List<String> treeSpecies,
                                                       @RequestParam List<String> treeHeight,
                                                       @RequestParam List<String> treeDiameter)
            throws InvalidInputException{
        double carbonSequestration=0;
        //treesInArea = service.getTreesByArea(latitude, longitude, distance);
        carbonSequestration=service.calculateCarbonSequestrationFromTrees(treeSpecies, treeHeight, treeDiameter);
        return carbonSequestration;
    }
    /*
        //status: not tested
    @GetMapping(value = {"/trees/forecast/water", "/trees/forecast/water/"})
    public double getWaterNeeded(@RequestParam float latitude, 
    		@RequestParam float longitude, 
    		@RequestParam float distance) throws InvalidInputException{
    	double water;
    	List<Tree> treesInArea;
    	treesInArea = service.getTreesByArea(latitude, longitude, distance);
    	water= service.calculateWaterNeeded(treesInArea);
    	return water;	
    }
    */

    @GetMapping(value = {"/trees/forecast/water", "/trees/forecast/water/"})
    public double getWaterNeededFromTrees(@RequestParam List<String> treeDiameter) throws InvalidInputException{
        double water;
        //treesInArea = service.getTreesByArea(latitude, longitude, distance);
        water= service.calculateWaterNeededFromTrees(treeDiameter);
        return water;
    }
    
    @GetMapping(value = {"/status/tree/{id}", "/status/tree"})
    public Status getTreeStatus(@PathVariable ("id") int id) {
        Status treeStatus = service.getStatus(id);
        return treeStatus;
    }
}
