package ca.mcgill.ecse321.treePLE.controller;

import ca.mcgill.ecse321.treePLE.dto.MunicipalityDto;
import ca.mcgill.ecse321.treePLE.dto.ResidentDto;
import ca.mcgill.ecse321.treePLE.dto.ScientistDto;
import ca.mcgill.ecse321.treePLE.dto.TreeDto;
import ca.mcgill.ecse321.treePLE.model.Municipality;
import ca.mcgill.ecse321.treePLE.model.Resident;
import ca.mcgill.ecse321.treePLE.model.Scientist;
import ca.mcgill.ecse321.treePLE.model.Tree;
import ca.mcgill.ecse321.treePLE.service.InvalidInputException;
import ca.mcgill.ecse321.treePLE.service.TreePLEService;
import com.google.common.collect.Lists;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // Conversion methods (not part of the API)
    private MunicipalityDto convertToDto(Municipality e) {
        // In simple cases, the mapper service is convenient
        return modelMapper.map(e, MunicipalityDto.class);
    }

    private ResidentDto convertToDto(Resident p) {
        ResidentDto residentDto = modelMapper.map(p, ResidentDto.class);
        //residentDto.setEvents(createEventDtosForParticipant(p));
        return residentDto;
    }

    private ScientistDto convertToDto(Scientist p) {
        ScientistDto scientistDto = modelMapper.map(p, ScientistDto.class);
        //scientistDto.setEvents(createEventDtosForParticipant(p));
        return scientistDto;
    }

    private TreeDto convertToDto(Tree p) {
        TreeDto treeDto = modelMapper.map(p, TreeDto.class);
        //scientistDto.setEvents(createEventDtosForParticipant(p));
        return treeDto;
    }

    //For plant tree?
    @PostMapping(value = { "/trees/{species}", "/trees/{species}/" })
    public TreeDto createTree(
            @PathVariable("species") String species) throws InvalidInputException {
        //Tree tree = service.createTree(species);
        //return convertToDto(tree);
        return null;
    }

    //For list all trees?
    @GetMapping(value = { "/trees", "/trees/" })
    public List<TreeDto> findAllTrees() {
        List<TreeDto> trees = Lists.newArrayList();
        for (Tree tree : service.findAllTrees()) {
            trees.add(convertToDto(tree));
        }
        return trees;
    }





}
