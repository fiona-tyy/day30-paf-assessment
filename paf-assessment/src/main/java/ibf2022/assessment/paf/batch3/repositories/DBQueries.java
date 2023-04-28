package ibf2022.assessment.paf.batch3.repositories;

public class DBQueries {
    
    public static final String GET_BEER_COUNT_BY_STYLE = """
        SELECT style_id, style_name as style, COUNT(beers.id) as beer_count FROM beers JOIN 
        styles ON beers.style_id = styles.id 
        GROUP BY beers.style_id 
        ORDER BY beer_count DESC, style ASC
            """;


    public static final String GET_BEERS_BREWERY_BY_STYLE = """
        SELECT beers.id as beer_Id, beers.name AS beer_name, beers.descript AS description, breweries.id AS brewery_id,
        breweries.name AS brewery_name 
        FROM beers JOIN breweries ON beers.brewery_id= breweries.id 
        WHERE beers.style_id = ? 
        ORDER BY beer_name
            """;

    public static final String GET_BREWERY_BEERS_BY_BREWERY_ID = """
        SELECT breweries.id AS brewery_id, breweries.name AS brewery_name, address1, address2, city, phone, website, breweries.descript AS description, 
        beers.id AS beer_id, beers.name AS beer_name, beers.descript AS beer_description
        FROM breweries JOIN beers ON breweries.id=beers.brewery_id
        WHERE breweries.id = ?
        ORDER BY beer_name
            """;
}
