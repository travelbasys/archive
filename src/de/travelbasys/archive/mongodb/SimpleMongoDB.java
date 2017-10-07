package de.travelbasys.archive.mongodb;
import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;

public class SimpleMongoDB {

	public static void main(String[] args) {
		try {
			// If MongoDB in secure mode, authentication is required.
			MongoClient mongoClient = new MongoClient("localhost", 27017);
			DB db = mongoClient.getDB("ElasticSearch");

			System.out.println("Database" + db.getName());

			DBCollection tableFileText = db.getCollection("FileText");
			System.out.println("table name" + tableFileText.getName());

//			 //insert
//			 BasicDBObject document = new BasicDBObject();
//			 document.put("FileName", "19896075207607.pdf");
//			 document.put("Barcode", "19896075207607");
//			 document.put("Firma", "2501");
//			 document.put("FullText",
//			 "Faksimile aus dem RBSweb-System, gedruckt am 23.11.2016 um 11:15, Exp 0055"+
//			 " DDDDDDDDD"+
//			 " AAAAAAAAAAAAAAAAAAA"+
//			 " HERR BBBBBBBBBBBBBBB"+
//			 " ARENDT CCCCCCCCCCCCCCCCC"+
//			 " . 20A Ar._38"+
//			 " 45130 ESSEN D 45472 Mülheim"+
//			 " SEITE"+
//			 " KASSENZL NR. 000222002 111031 37904189 1"+
//			 " 31.10.11"+
//			 " ARENDT 20.11.11 0845 0"+
//			 " Preis in MWST in"+
//			 " EUR EUR"+
//			 " 010 0845 9900 INL*01 29975857 01 78,00 + 1 78,00"+
//			 " 0845 Auftr.-ID 782979641 1 -1"+
//			 " 0845 2. KL VON ESSEN"+
//			 " 0845 NACH HAMBURG"+
//			 " 010 0845 9902 EPA RESERVIERUN*01 29976135 01 4,50 + 1 4,50"+
//			 " 0845 Auftr.-ID 593513522 1 -1"+
//			 " 010 0845 9902 EPA RESERVIERUN*01 29976136 01 0,00 + 0"+
//			 " 0845 Auftr.-ID 593513522 1 -2"+
//			 " 010 0845*7803 SERVICEPAUSCH BAHN 7803 01 3,50 + 1 3,50"+
//			 " 010 0845 9641 SC 1890 65056613020780 01 86,00 - 0"+
//			 " 0845 *01 DB Vertrieb GmbH 60326 Frankfurt a.M"+
//			 " 0845 UST-ID-Nr. DE 814 160 246 Stephensonstr. 1"+
//			 " 0845 Nettosumme zu 19,00% 72,27+"+
//			 " 19% MWST 7% MWST MWST Gesamt EUR"+
//			 " 13,73+ 0,00+ 13,73+ 0,00+"+
//			 "Sitz: Essen, HRB 123456 GF: Ingo Brandes, Eyk Pfeiffer"+
//			 "Bank, BLZ 36020030, KTO 123456789 UST-ID: 111/2222/3333"
//			 );
//			
//			 tableFileText.insert(document);
//
//			 document = new BasicDBObject();
//			 document.put("name", "mkyong1");
//			 document.put("age", 301);
//			 document.put("createdDate", new Date());
//			 tableFileText.insert(document);
//
//			// Select
//			 BasicDBObject searchQuery = new BasicDBObject();
//			 searchQuery.put("FullText", "WildFly");
//			 DBCursor cursor = tableFileText.find(searchQuery);
//			
//			 while (cursor.hasNext()) {
//			System.out.println(cursor.next());
//			 }

//			 Search FullText
//			 db.FileText.createIndex({"FullText":"text","content":"text"})
//			 tableFileText.createIndex(new BasicDBObject("FullText", "text"));
//
//			 db.FileText.find({$text: {$search: "Wurstbrot"}})
//			 BasicDBObject query = new BasicDBObject("text", "Wildfly");

			DBObject query = QueryBuilder.start().text("BBBBBBBBBBBBBBB").get();
			query.put("Firma", "2501");

			DBCursor indexCursor = tableFileText.find(query);
			try {
				while (indexCursor.hasNext()) {
					System.out.println("Barcode: " + indexCursor.next().get("Barcode"));
				}
			} finally {
				indexCursor.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String GetFileNameUsingTextAndCompany(String company , String text) {

		String barcode = "";
		try {

			MongoClient mongoClient = new MongoClient("localhost", 27017);
			DB db = mongoClient.getDB("ElasticSearch");

			DBCollection tableFileText = db.getCollection("FileText");

			DBObject query = QueryBuilder.start().text(text).get();
			query.put("Firma", company);

			DBCursor indexCursor = tableFileText.find(query);
			try {
				while (indexCursor.hasNext()) {
					barcode = (String) indexCursor.next().get("Barcode");
					System.out.println("Barcode: " + barcode);
				}
			} finally {
				indexCursor.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return barcode;

	}
}
