package com.zebraviews.reviews;

import java.util.Random;

public class LabelApiURI {
	
	public static String getCreateSessionURI(String apiKey){
		Random generator = new Random();
		int uid = generator.nextInt(2147483646);
		int devid = generator.nextInt(2147483646);
		return "http://api.foodessentials.com/createsession?uid=" + uid + "&devid=" + devid + "&appid=ZebraViews&f=js" +
		"on&api_key=" + apiKey;
	}
	
	public static String getGetProfileURI(String sid, String apiKey){
	return "http://api.foodessentials.com/getprofile?sid=" + sid + "&f=json&api_key=" + apiKey;
	}
	
	public static String getSetProfileURI(String sid, boolean cerealAllergy, boolean coconutAllergy, boolean cornAllergy, boolean eggAllergy, boolean fishAllergy, boolean glutenAllergy, boolean lactoseAllergy, boolean milkAllergy, boolean peanutAllergy, boolean sesameAllergy, boolean shellfishAllergy, boolean soybeanAllergy, boolean sulfiteAllergy, boolean treenutAllergy, boolean wheatAllergy, String apiKey){
		return "http://api.foodessentials.com/setprofile?json=%7B+++++%22session_id%22%3A+%22"+sid+"%22%2C+++++%22nutrients%22%3A+%5B%5D%2C+++++%22allergens%22%3A+%5B%7B+++++++++%22name%22%3A+%22Cereals%22%2C+++++++++%22value%22%3A+%22"+cerealAllergy+"%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Coconut%22%2C+++++++++%22value%22%3A+%22"+coconutAllergy+"%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Corn%22%2C+++++++++%22value%22%3A+%22"+cornAllergy+"%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Egg%22%2C+++++++++%22value%22%3A+%22"+eggAllergy+"%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Fish%22%2C+++++++++%22value%22%3A+%22"+fishAllergy+"%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Gluten%22%2C+++++++++%22value%22%3A+%22"+glutenAllergy+"%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Lactose%22%2C+++++++++%22value%22%3A+%22"+lactoseAllergy+"%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Milk%22%2C+++++++++%22value%22%3A+%22"+milkAllergy+"%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Peanuts%22%2C+++++++++%22value%22%3A+%22"+peanutAllergy+"%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Sesame+Seeds%22%2C+++++++++%22value%22%3A+%22"+sesameAllergy+"%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Shellfish%22%2C+++++++++%22value%22%3A+%22"+shellfishAllergy+"%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Soybean%22%2C+++++++++%22value%22%3A+%22"+soybeanAllergy+"%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Sulfites%22%2C+++++++++%22value%22%3A+%22"+sulfiteAllergy+"%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Tree+Nuts%22%2C+++++++++%22value%22%3A+%22"+treenutAllergy+"%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Wheat%22%2C+++++++++%22value%22%3A+%22"+wheatAllergy+"%22+++++%7D%5D%2C+++++%22additives%22%3A+%5B%5D%2C+++++%22myingredients%22%3A+%5B%5D+%7D&api_key="+apiKey;
	}
	
	public static String getIngredientSearchURI(String query, String sid, String numberOfItems, String startOffset, String apiKey){	
	return "http://api.foodessentials.com/ingredientsearch?q=" + query + "&sid=" + sid + "&n=" + numberOfItems + "&s=321&" +
	"f=json&api_key=" + apiKey;	
	}
	
	/* id refers to the ingredient id */
	public static String getAddMyIngredientURI(String sid, String id, String apiKey){
		return "http://api.foodessentials.com/addmyingredient?sid=" + sid + "&id=" + id + "&f=json&api_key=" + apiKey;
	}
	
	/* id refers to the ingredient id */
	public static String getRemoveMyIngredientURI(String sid, String id, String apiKey){
		return "http://api.foodessentials.com/removemyingredient?sid=" + sid + "&id=" + id + "&f=json&api_key=" + apiKey;
	}
	
	public static String getSearchProductsURI(String query, String sid, String numberOfItems, String startOffset, String apiKey){
		return "http://api.foodessentials.com/searchprods?q=" + query + "&sid=" + sid + "&n="+ numberOfItems + "&s=" + startOffset + 
		"&f=json&api_key=" + apiKey;
	}
	
	public static String getProductScoreURI(String upc, String sid, String apiKey){
		return "http://api.foodessentials.com/productscore?u=" + upc + "&sid=" + sid + "&f=json&api_key=" + apiKey;
	}

	public static String getLabelURI(String upc, String sid, String apiKey){
		return "http://api.foodessentials.com/label?u=" + upc + "&sessid=" + sid + "&appid=" +
		"ZebraViews&f=json&api_key=" + apiKey;
	}
	
	public static String getLabelArrayURI(String upc, String sid, String numberOfItems, String startOffset, String apiKey){
		return "http://api.foodessentials.com/labelarray?u=" + upc + "&sid=" + sid + "&n=" + numberOfItems + "&s=" +
		startOffset + "&f=json&api_key=" + apiKey;
	}
	
	public static String getLabelSummaryURI(String upc, String sid, String apiKey){
		return "http://api.foodessentials.com/label_summary?u=" + upc + "&sid=" + sid + "&appid=" +
		"ZebraViews&f=json&api_key=" + apiKey;
	}
	/* Property must be equal to the allergen or additive exactly as it is spelt; propertyType must be "allergen" or "additive" */
	public static String getGetAllergenAdditiveURI(String upc, String sid, String property, String propertyType, String apiKey){
		return "http://api.foodessentials.com/getallergenadditive?u=" + upc + "&sid=" + sid + "&property=" + property + 
		"&propType=" + propertyType + "&f=json&api_key=" + apiKey;
	}
	
	/* Type must be "nutrient", "allergen", or "additive"; Name refers to the name of the nutrient/allergen/additive */
	public static String getGetPropertyDescriptionURI(String sid, String type, String name, String apiKey){
		return "http://api.foodessentials.com/getpropdescription?sid=" + sid + "&type=" + type + "&name=" + name + "&f=json" +
		"&api_key=" + apiKey;
	}
	
	public static String getGetMyListURI(String sid, String numberOfItems, String startOffset, String apiKey){
		return "http://api.foodessentials.com/getmylist?sid=" + sid + "&n=" + numberOfItems + "s=" + startOffset +
		"&f=json&api_key=" + apiKey;
	}
	
	public static String getAddMyListURI(String sid, String upc, String apiKey){
		return "http://api.foodessentials.com/addmylist?u=" + upc + "&sid=" + sid + "&f=json&api_key=" + apiKey;
	}
	
	public static String getRemoveMyListURI(String sid, String upc, String apiKey){
		return "http://api.foodessentials.com/removemylist?sid=" + sid + "&u=" + upc + "&f=json&api_key=" + apiKey;
	}
	
	public static String getGetSearchLog(String sid, String numberOfItems, String startOffset, String apiKey){
		return "http://api.foodessentials.com/getsearchlog?sid=" + sid + "&n=" + numberOfItems + "s=" + startOffset +
		"&f=json&api_key=" + apiKey;
	}
}