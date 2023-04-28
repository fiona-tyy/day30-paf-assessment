package ibf2022.assessment.paf.batch3.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ibf2022.assessment.paf.batch3.models.Beer;
import ibf2022.assessment.paf.batch3.models.Brewery;
import ibf2022.assessment.paf.batch3.models.Style;
import ibf2022.assessment.paf.batch3.services.BeerService;

@Controller
public class BeerController {

	@Autowired
	BeerService beerSvc;

	//TODO Task 2 - view 0

	@GetMapping(path = "/")
	public String showLanding(Model model){
		List<Style> styles = beerSvc.getStyles();
		model.addAttribute("styles", styles);

		return "view0";
	}
	
	
	//TODO Task 3 - view 1
	@GetMapping(path = "/beer/style/{styleId}")
	public String showBeerDetails(Model model, @PathVariable Integer styleId, @RequestParam String styleName){
		List<Beer> beers = beerSvc.getBreweriesByBeer(styleId);

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
	public String showBreweryDetails(Model model, @PathVariable Integer breweryId, @RequestParam String breweryName){

		Optional<Brewery> brewery = beerSvc.getBeersFromBrewery(breweryId);

		boolean breweryExists;
		if(brewery.isEmpty()){
			breweryExists = false;
		}

		breweryExists = true;
		model.addAttribute("breweryExists", breweryExists);
		model.addAttribute("breweryName", breweryName);
		model.addAttribute("brewery", brewery);

		return "view2";
	}

	
	//TODO Task 5 - view 2, place order
	@PostMapping(path = "/beer/order")
	public String postOrder(){

		return "view3";
	}
}
