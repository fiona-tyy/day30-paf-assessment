package ibf2022.assessment.paf.batch3.repositories;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.assessment.paf.batch3.models.Order;
import ibf2022.assessment.paf.batch3.models.Orders;

@Repository
public class OrderRepository {

	// TODO: Task 5
	@Autowired
	MongoTemplate mongoTemplate;

	public String placeOrder(Integer breweryId, Orders orders){
		String orderId = UUID.randomUUID().toString().substring(0,8);
		orders.setBreweryId(breweryId);
		orders.setOrderId(orderId);
		orders.setDate(LocalDate.now());
		Document doc = new Document("orderId", orders.getOrderId())
								.append("date", orders.getDate().toString())
								.append("breweryId", orders.getBreweryId());
		
		List<Document> ordersDocList = new LinkedList<>();

			for(Order o: orders.getOrders()){
				if(o.getQuantity() > 0){
					Document subDoc = new Document("beerId",o.getBeerId())
											.append("quantity", o.getQuantity());
					ordersDocList.add(subDoc);
				}
			}
		if(ordersDocList.size()>0){
			doc.append("orders", ordersDocList);
		}
		// if(orders.getOrders().size()>0){
		// 	List<Document> ordersDocList = new LinkedList<>();

		// 	for(Order o: orders.getOrders()){
		// 		Document subDoc = new Document();
		// 		subDoc.append("beerId", o.getBeerId());
		// 		subDoc.append("quantity", o.getQuantity());
		// 		ordersDocList.add(subDoc);
		// 	}
		// 	doc.append("orders", ordersDocList);
		// }
		mongoTemplate.insert(doc, "orders");

		return orderId;
	}

}
