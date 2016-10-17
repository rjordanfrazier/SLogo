package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import Model.animal.Animal;
import View.helper.CoordinatePair;

public class AnimalPane extends Observable implements Observer {

	private Map<Integer, Animal> myAnimalMap = new HashMap<>();
	private List<String> myCommandHistory = new ArrayList<>();

	// <string = name of variable, string/other? = value / expression>
	private Map<String, String> myVariables = new HashMap<>();
	
	// Map of string ID to List of CoordinatePair<double x, double y>
	private Map<String, List<CoordinatePair>> coordinateMap;

	private int animalID;

	public AnimalPane(Animal animal) {
		// this increments when adding new animals
		animalID = 0;
		// map of AnimalPane ID to Animal (for multiple animals on same pane)
		myAnimalMap = new HashMap<Integer, Animal>();
		
		//Map of variable names and expressions
		myVariables = new HashMap<String, String>();
		
		//list of all executed commands
		myCommandHistory = new ArrayList<String>();
		//list of Animal ID and Coordinate Lists for translation rendering
		coordinateMap = new HashMap<String, List<CoordinatePair>>();
		addAnimal(animal);
	}
	
	/**
	 * Adds animal and notifies view that a new animal needs to be displayed
	 * @param animal
	 */
	public void addAnimal(Animal animal) {
		animalID++;
		myAnimalMap.put(animalID, animal);
		animal.setId(animalID);
		animal.addObserver(this);
		
		// notify SLogoView that a new turtle was added, and needs to update the view to include new turtle
		setChanged();
		notifyObservers();
	}

	@Override
	public void update(Observable o, Object arg) {
		// updates values of animals, object arg is the value that is changing
		// or just update everything why not with a complete re renderingR	
		
		if ( o instanceof Animal) {
			Animal cur = (Animal) o;
			for( int animalId : myAnimalMap.keySet() ) {
				if (animalId == cur.getId()) { // I think this will hit them all no matter what
					// TODO: update animalPane.
					
					if(cur.getSelected()) {
						// now update this guy
					}
					
				}
			}
		}
	}
	
	public void setCoordinates(Map<String, List<CoordinatePair>> coordinateMap) {
		this.coordinateMap = coordinateMap;	
	}
	
	public Map<String, List<CoordinatePair>> getCoordinates() {
		return coordinateMap;
	}


}
