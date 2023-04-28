package ibf2022.assessment.paf.batch3.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibf2022.assessment.paf.batch3.models.Beer;
import ibf2022.assessment.paf.batch3.models.Brewery;
import ibf2022.assessment.paf.batch3.models.Orders;
import ibf2022.assessment.paf.batch3.models.Style;
import ibf2022.assessment.paf.batch3.repositories.BeerRepository;
import ibf2022.assessment.paf.batch3.repositories.OrderRepository;

@Service
public class BeerService {

	@Autowired
	BeerRepository beerRepository;

	@Autowired
	OrderRepository orderRepository;

	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public String placeOrder(Integer breweryId, Orders orders) {
		// TODO: Task 5 
		return this.orderRepository.placeOrder(breweryId, orders);
	}

	// public List<Style> getStyles(){
	// 	return this.beerRepository.getStyles();
	// }

	// public List<Beer> getBreweriesByBeer(Integer styleId ){
	// 	return this.beerRepository.getBreweriesByBeer(styleId);
	// }

	// public Optional<Brewery> getBeersFromBrewery(Integer breweryId){
	// 	return this.beerRepository.getBeersFromBrewery(breweryId);
	// }
}
