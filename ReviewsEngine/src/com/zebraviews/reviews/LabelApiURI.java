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
		return "http://api.foodessentials.com/setprofile?json=%7B+++++%22session_id%22%3A+%22"+sid+"%22%2C+++++%22nutrients%22%3A+%5B%7B+++++++++%22name%22%3A+%22Calcium%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Calories%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Calories+from+Fat%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Cholesterol%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Dietary+Fiber%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Insoluble+Fiber%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Iron%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Monounsaturated+Fat%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Other+Carbohydrate%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Polyunsaturated+Fat%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Potassium%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Protein%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Saturated+Fat%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Saturated+Fat+Calories%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Sodium%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Soluble+Fiber%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Sugar+Alcohol%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Sugars%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Total+Carbohydrate%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Total+Fat%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Vitamin+A%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Vitamin+C%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%5D%2C+++++%22allergens%22%3A+%5B%7B+++++++++%22name%22%3A+%22Cereals%22%2C+++++++++%22value%22%3A+%22"+cerealAllergy+"%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Coconut%22%2C+++++++++%22value%22%3A+%22"+coconutAllergy+"%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Corn%22%2C+++++++++%22value%22%3A+%22"+cornAllergy+"%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Egg%22%2C+++++++++%22value%22%3A+%22"+eggAllergy+"%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Fish%22%2C+++++++++%22value%22%3A+%22"+fishAllergy+"%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Gluten%22%2C+++++++++%22value%22%3A+%22"+glutenAllergy+"%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Lactose%22%2C+++++++++%22value%22%3A+%22"+lactoseAllergy+"%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Milk%22%2C+++++++++%22value%22%3A+%22"+milkAllergy+"%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Peanuts%22%2C+++++++++%22value%22%3A+%22"+peanutAllergy+"%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Sesame+Seeds%22%2C+++++++++%22value%22%3A+%22"+sesameAllergy+"%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Shellfish%22%2C+++++++++%22value%22%3A+%22"+shellfishAllergy+"%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Soybean%22%2C+++++++++%22value%22%3A+%22"+soybeanAllergy+"%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Sulfites%22%2C+++++++++%22value%22%3A+%22"+sulfiteAllergy+"%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Tree+Nuts%22%2C+++++++++%22value%22%3A+%22"+treenutAllergy+"%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Wheat%22%2C+++++++++%22value%22%3A+%22"+wheatAllergy+"%22+++++%7D%5D%2C+++++%22additives%22%3A+%5B%7B+++++++++%22name%22%3A+%22Acidity+Regulator%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Added+Sugar%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22AntiCaking+Agents%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22AntiFoaming+Agent%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Antioxidants%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Artificial+Color%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Artificial+Flavoring+Agent%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Artificial+Preservative%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Bulking+Agents%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Colors%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Emulsifiers%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Enzyme%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Firming+Agent%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Flavor+Enhancer%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Flour+Treating+Agent%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Food+Acids%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Gelling+Agents%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Glazing+Agent%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Humectants%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Leavening+Agent%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Mineral+Salt%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Natural+Color%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Natural+Flavoring+Agent%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Natural+Preservative%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Preservatives%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Propellant%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Raising+Agents%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Saturated+Fat%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Sequestrant%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Stabilizers%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Sweeteners%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Thickeners%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Trans+Fat%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Unsaturated+Fat%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Vegetable+Gum%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%5D%2C+++++%22myingredients%22%3A+%5B%5D+%7D&api_key="+apiKey;
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

	public static String getLabelURI(String upc, String sid, String appid, String longitude, String lattitude, String apiKey){
		return "http://api.foodessentials.com/label?u=" + upc + "&sessid=" + sid + "&appid=" + appid + "&f=json&long="
		+ longitude + "&lat=" + lattitude + "&api_key=" + apiKey;
	}
	
	public static String getLabelArrayURI(String upc, String sid, String numberOfItems, String startOffset, String apiKey){
		return "http://api.foodessentials.com/labelarray?u=" + upc + "&sid=" + sid + "&n=" + numberOfItems + "&s=" +
		startOffset + "&f=json&api_key=" + apiKey;
	}
	
	public static String getLabelSummaryURI(String upc, String sid, String appid, String longitude, String lattitude, String apiKey){
		return "http://api.foodessentials.com/label_summary?u=" + upc + "&sid=" + sid + "&appid=" + appid + "&f=json&long="
		+ longitude + "&lat=" + lattitude + "&api_key=" + apiKey;
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