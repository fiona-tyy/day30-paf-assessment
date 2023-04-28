package ibf2022.assessment.paf.batch3.services;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import ibf2022.assessment.paf.batch3.models.Beer;
import ibf2022.assessment.paf.batch3.models.Brewery;
import ibf2022.assessment.paf.batch3.models.Style;

public class UtilService {
    

    public static Style createStyleFromRowset (SqlRowSet rs){
        Style style = new Style();
        style.setStyleId(rs.getInt("style_id"));
        style.setName(rs.getString("style"));
        style.setBeerCount(rs.getInt("beer_count"));
        return style;
    }

    public static Beer createBeerFromRowset(SqlRowSet rs){

        // SELECT beers.id as beer_Id, beers.name AS beer_name, beers.descript AS description, breweries.id AS brewery_id,
        // breweries.name AS brewery_name 

        Beer beer = new Beer();
        beer.setBeerId(rs.getInt("beer_id"));
        beer.setBeerName(rs.getString("beer_name"));
        beer.setBeerDescription(rs.getString("beer_description"));
        beer.setBreweryId(rs.getInt("brewery_id"));
        beer.setBreweryName(rs.getString("brewery_name"));

        return beer;
    }


    public static Brewery createBreweryFromRowset(SqlRowSet rs) {

        // SELECT breweries.id AS brewery_id, breweries.name AS brewery_name, address1, address2, city, phone, website, breweries.descript AS description, 
        // beers.id AS beer_id, beers.name AS beer_name, beers.descript AS beer_description
        Brewery brewery = new Brewery();
        brewery.setBreweryId(rs.getInt("brewery_id"));
        brewery.setName(rs.getString("brewery_name"));
        brewery.setAddress1(rs.getString("address1"));
        brewery.setAddress2(rs.getString("address2"));
        brewery.setCity(rs.getString("city"));
        brewery.setPhone(rs.getString("phone"));
        brewery.setWebsite(rs.getString("website"));
        brewery.setDescription(rs.getString("description"));
        return brewery;
    }
}
