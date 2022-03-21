package com.udacity.vehicles.service;

import com.udacity.vehicles.client.maps.MapsClient;
import com.udacity.vehicles.client.prices.PriceClient;
import com.udacity.vehicles.domain.car.Car;
import com.udacity.vehicles.domain.car.CarRepository;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

/**
 * Implements the car service create, read, update or delete
 * information about vehicles, as well as gather related
 * location and price data when desired.
 */
@Service
public class CarService {

	@Autowired
	@Qualifier("pricing")
	private WebClient.Builder webClientPricing;
	
	@Autowired
	@Qualifier("maps")
	private WebClient webClientMaps;
	
	@Autowired
	ModelMapper modelMapper;
    private final CarRepository repository;

////    public CarService(CarRepository repository) {
////        /**
////         * TODO: Add the Maps and Pricing Web Clients you create
////         *   in `VehiclesApiApplication` as arguments and set them here.
//    		done11111111111111111111111111111
////         */
////
////    	this.repository = repository;
////    }
    

   

	/**
     * Gathers a list of all vehicles
     * @return a list of all vehicles in the CarRepository
     */
    public List<Car> list() {
        return repository.findAll();
    }

    public CarService(Builder webClientPricing, WebClient webClientMaps, CarRepository repository) {
		super();
		this.webClientPricing = webClientPricing;
		this.webClientMaps = webClientMaps;
		this.repository = repository;
	}

	/**
     * Gets car information by ID (or throws exception if non-existent)
     * @param id the ID number of the car to gather information on
     * @return the requested car's information, including location and price
     */
    public Car findById(Long id) {
        /**
         * TODO: Find the car by ID from the `repository` if it exists.
         *   If it does not exist, throw a CarNotFoundException
         *   Remove the below code as part of your implementation.
         *   done111111111111111111
         */
    	
    	try {
    		Optional<Car> optional= repository.findById(id);
    		Car car=optional.orElseThrow(RuntimeException::new);
    		//car=repository.findById(id);
    		PriceClient priceClient=new PriceClient(webClientPricing);
    		MapsClient mapsClient=new MapsClient(webClientMaps, modelMapper);
    		car.setPrice(priceClient.getPrice(id));
    		car.setLocation(mapsClient.getAddress(car.getLocation()));
    		
    		return car;
    		
    	}
    		catch(Exception e) {
    		throw new CarNotFoundException("Car was not found");
    		}
    	
        /**
         * TODO: Use the Pricing Web client you create in `VehiclesApiApplication`
         *   to get the price based on the `id` input'
         * TODO: Set the price of the car
         * Note: The car class file uses @transient, meaning you will need to call
         *   the pricing service each time to get the price.
         *   done 111111111111111111111111111111111111
         */


        /**
         * TODO: Use the Maps Web client you create in `VehiclesApiApplication`
         *   to get the address for the vehicle. You should access the location
         *   from the car object and feed it to the Maps service.
         * TODO: Set the location of the vehicle, including the address information
         * Note: The Location class file also uses @transient for the address,
         * meaning the Maps service needs to be called each time for the address.
         * done 11111111111111111111111111111111111111
         */


        
    }

    /**
     * Either creates or updates a vehicle, based on prior existence of car
     * @param car A car object, which can be either new or existing
     * @return the new/updated car is stored in the repository
     */
    public Car save(Car car) {
        if (car.getId() != null) {
            return repository.findById(car.getId()).map(carToBeUpdated -> {
                        carToBeUpdated.setDetails(car.getDetails());
                        carToBeUpdated.setLocation(car.getLocation());
                        carToBeUpdated.setCondition(car.getCondition());
                        return repository.save(carToBeUpdated);
                    }).orElseThrow(CarNotFoundException::new);
        }

        return repository.save(car);
    }

    /**
     * Deletes a given car by ID
     * @param id the ID number of the car to delete
     */
    public void delete(Long id) {
        /**
         * TODO: Find the car by ID from the `repository` if it exists.
         *   If it does not exist, throw a CarNotFoundException
         *   done11111111111111111111111
         */
    	

        /**
         * TODO: Delete the car from the repository.
         * done11111111111111111111
         */
    	try {
    		repository.deleteById(id);
    	
    	}
    		catch(Exception e) {
    		throw new CarNotFoundException("Car was not found");
    		}
    
    }
}
