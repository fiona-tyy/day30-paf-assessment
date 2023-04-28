package ibf2022.assessment.paf.batch3.repositories;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import ibf2022.assessment.paf.batch3.models.Beer;
import ibf2022.assessment.paf.batch3.models.Brewery;
import ibf2022.assessment.paf.batch3.models.Style;
import ibf2022.assessment.paf.batch3.services.UtilService;

import static ibf2022.assessment.paf.batch3.repositories.DBQueries.*;

@Repository
public class BeerRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	// DO NOT CHANGE THE SIGNATURE OF THIS METHOD
	public List<Style> getStyles() {
		List<Style> styles = new LinkedList<>();

		SqlRowSet rs = jdbcTemplate.queryForRowSet(GET_BEER_COUNT_BY_STYLE);

		while(rs.next()){
			styles.add(UtilService.createStyleFromRowset(rs));
		}

		return styles;
	}
		
	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public List<Beer> getBreweriesByBeer(Integer styleId ) {
	
		List<Beer> beers = new LinkedList<>();

		SqlRowSet rs = jdbcTemplate.queryForRowSet(GET_BEERS_BREWERY_BY_STYLE, new Object[]{styleId});

		while(rs.next()){
			beers.add(UtilService.createBeerFromRowset(rs));
		}

		return beers;
	}

	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public Optional<Brewery> getBeersFromBrewery(Integer breweryId) {

		// TODO: Task 4 

		List<Brewery> breweries = new LinkedList<>();
		List<Beer> beers = new LinkedList<>();

		SqlRowSet rs = jdbcTemplate.queryForRowSet(GET_BREWERY_BEERS_BY_BREWERY_ID, new Object[]{breweryId});
		while(rs.next()){
			breweries.add(UtilService.createBreweryFromRowset(rs));
			beers.add(UtilService.createBeerFromRowset(rs));
		}
		if(breweries.isEmpty()){
			return Optional.empty();
		} else{
			Brewery brewery = breweries.get(0);
			brewery.setBeers(beers);
			// System.out.println(">>>checkpoint inside repo getBeersFromBrewery: "+ brewery);
			return Optional.of(brewery);

		}

	}

}
