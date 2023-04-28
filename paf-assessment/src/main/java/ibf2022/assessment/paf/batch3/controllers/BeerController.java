package ibf2022.assessment.paf.batch3.controllers;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ibf2022.assessment.paf.batch3.models.Beer;
import ibf2022.assessment.paf.batch3.models.Brewery;
import ibf2022.assessment.paf.batch3.models.Order;
import ibf2022.assessment.paf.batch3.models.Orders;
import ibf2022.assessment.paf.batch3.models.Style;
import ibf2022.assessment.paf.batch3.repositories.BeerRepository;
import ibf2022.assessment.paf.batch3.services.BeerService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class BeerController {

	@Autowired
	BeerService beerSvc;

	@Autowired
	BeerRepository beerRepo;

	//TODO Task 2 - view 0

	@GetMapping(path = "/")
	public String showLanding(Model model){
		List<Style> styles = beerRepo.getStyles();
		model.addAttribute("styles", styles);

		return "view0";
	}
	
	
	//TODO Task 3 - view 1
	@GetMapping(path = "/beer/style/{styleId}")
	public String showBeerDetails(Model model, @PathVariable Integer styleId, @RequestParam String styleName){
		List<Beer> beers = beerRepo.getBreweriesByBeer(styleId);

		boolean beersFound;

		if(beers.isEmpty()){
			beersFound = false;
		}

		beersFound = true;
		model.addAttribute("beersFound", beersFound);
		model.addAttribute("styleName", styleName);
		model.addAttribute("beers", beers);

		return "view1";
	}

	//TODO Task 4 - view 2
	@GetMapping(path = "/beer/brewery/{breweryId}")
	public String showBreweryDetails(HttpSession session, Model model, @PathVariable Integer breweryId, @RequestParam String breweryName){
		// System.out.println(">> inside getmapping showBreweryDetails >> breweryId: " + breweryId);

		Optional<Brewery> brewery = beerRepo.getBeersFromBrewery(breweryId);

		boolean breweryExists;
		if(brewery.isEmpty()){
			breweryExists = false;
		}

		breweryExists = true;
		model.addAttribute("breweryExists", breweryExists);
		model.addAttribute("breweryName", breweryName);
		model.addAttribute("brewery", brewery.get());
		List<Beer> beers = brewery.get().getBeers();
		session.setAttribute(String.valueOf(breweryId), beers);
		Orders submitOrder = new Orders();
		for(int i= 0; i < beers.size(); i ++){
			submitOrder.addOrder(new Order());
		}
		
		model.addAttribute("submitOrder", submitOrder);
		return "view2";
	}

	
	//TODO Task 5 - view 2, place order
	@PostMapping(path = "/brewery/{breweryId}/order")
	public String postOrder(HttpSession session, Model model, @ModelAttribute Orders orders, @PathVariable Integer breweryId){
		
		List<Beer> beers = (List<Beer>) session.getAttribute(String.valueOf(breweryId));

		for(int i = 0; i < orders.getOrders().size(); i ++){
			orders.getOrders().get(i).setBeerId(beers.get(i).getBeerId());
		}

		System.out.println(orders);
		String orderId = beerSvc.placeOrder(breweryId, orders);
		
		model.addAttribute("orderId", orderId);
		return "view3";
	}
}
