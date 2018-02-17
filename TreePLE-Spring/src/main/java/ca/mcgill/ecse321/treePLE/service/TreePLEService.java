package ca.mcgill.ecse321.treePLE.service;

import ca.mcgill.ecse321.treePLE.model.Tree;
import ca.mcgill.ecse321.treePLE.persistence.PersistenceXStream;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class TreePLEService {

    //private TreePLEManager tm;

    public Tree createTree(String species, float height, int age, Date date, float diameter, float longitude, float latitude)
        throws InvalidInputException{

        if(species == null || height == 0 || age == 0 || date == null || diameter == 0 || longitude == 0 || latitude == 0){
            throw new InvalidInputException("Something is empty!");
        }

        Tree tree= new Tree(species, height, age, date, diameter, longitude, latitude);
        //tm.addTree(tree);
        //PersistenceXStream.saveToXMLwithXStream(tm);
        return tree;
    }

    public Tree[] findAllTrees() {
        return null;
    }
}
