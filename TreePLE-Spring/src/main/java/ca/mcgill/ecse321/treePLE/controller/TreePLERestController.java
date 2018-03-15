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
import ca.mcgill.ecse321.treePLE.dto.TreeDto;
import ca.mcgill.ecse321.treePLE.model.Location;
import ca.mcgill.ecse321.treePLE.model.Person;
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
            @RequestParam int height,
            @RequestParam int age,
            @RequestParam Date date,
            @RequestParam float diameter,
            @RequestParam String personName,
            @RequestParam float longitude,
            @RequestParam float latitude,
            @RequestParam String municipality) throws InvalidInputException {

        Person treeOwner = new Person(personName, service.tm);
        int randomNum = ThreadLocalRandom.current().nextInt(10000000, 99999998 + 1);
        Location location = new Location(longitude, latitude, municipality);

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

        Person treeOwner = new Person(personName, service.tm);
        int randomNum = ThreadLocalRandom.current().nextInt(10000000, 99999998 + 1);
        Location location = new Location(longitude, latitude, municipality);

        Tree tree = service.createTree(species, date, randomNum, treeOwner, location);
        return convertToDto(tree);
    }
    
    @PostMapping(value = { "/tree/{id}", "/tree/{id}/" })
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

    //----------------------------------
    //   GET Methods
    //----------------------------------
    @GetMapping(value = { "/trees", "/trees/" })
    public List<TreeDto> findAllTrees() {
        List<TreeDto> trees = Lists.newArrayList();
        for (Tree tree : service.findAllTrees()) {
            trees.add(convertToDto(tree));
        }
        return trees;
    }
}
