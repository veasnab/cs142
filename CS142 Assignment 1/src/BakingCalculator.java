
// Veasna Bun
public class BakingCalculator {
	public static int bagsOfFlour(int cookieCount, int loafCount) {
		// 2 dozen cookies: 2.5 cups of all-purpose flour
		double flourCookieRecipeCount = 24.0;
		double flourRecipeCount = 2.5; 
				
		double flourPerCookie = flourRecipeCount/flourCookieRecipeCount;
		double flourForCookie = flourPerCookie * cookieCount;
		
		//1 loaf bananas bread: 2 cups of all- purpose flour
		double flourPerBananasBread = 2.0;
		double flourForBananasBread = flourPerBananasBread * loafCount;
		
		// total cups of floor for ask recipe 
		double totalCupsOfFlour = flourForBananasBread + flourForCookie;
		
		//covert cups to pounds (3 1/3 cups in a pound)
		double poundsPerCup = 10.0/3.0;
		double flourPoundsPerCup = totalCupsOfFlour/poundsPerCup;
		
		//5lbs sold per bags
		double lbPerBags = 5.0;
		double flourBags = flourPoundsPerCup/lbPerBags;
				
		return (int) Math.ceil(flourBags);
	}

	public static int containersOfSalt(int cookieCount, int loafCount) {
		//2 dozen cookies: 3/4 teaspoon of salt
		double saltCookieRecipeCount = 24.0;
		double saltRecipeCount = 3.0/4.0; 
		
		double saltPerCookie = saltRecipeCount/saltCookieRecipeCount; 
		double saltForCookie = saltPerCookie * cookieCount; 
		
		//1 loaf bananas bread: ⅛ teaspoon of salt
		double saltPerBananasBread = 1.0/8.0;
		double saltForBananasBread = saltPerBananasBread * loafCount;
		
		//total amount of salt for ask recipe
		double totalTeaspoonOfSalt = saltForBananasBread + saltForCookie;
		
		//covert teaspoon to ounces (6 teaspoon of salt in 1 ounce)
		double teaspoonPerOunce = 6.0;
		double saltTeaspoonPerOunces = totalTeaspoonOfSalt/teaspoonPerOunce;
		
		//26 oz of salt per container 
		double ozPerContainer = 26.0;
		double saltContainer = saltTeaspoonPerOunces/ozPerContainer;
		
		return (int) Math.ceil(saltContainer);
	}

	public static int boxesOfBakingSoda(int cookieCount, int loafCount) {
		//2 dozen cookies: 1 teaspoon of baking soda
		double sodaCookieRecipeCount = 24.0;
		double sodaRecipeCount = 1.0;
		
		double sodaPerCookie = sodaRecipeCount/ sodaCookieRecipeCount;
		double sodaForCookie = sodaPerCookie * cookieCount; 
				
		//1 loaf bananas bread: 1 teaspoon of baking soda
		double sodaPerBananasBread = 1.0;
		double sodaForBananasBread = sodaPerBananasBread * loafCount; 
		
		//total amount of soda for ask recipe 
		double totalSodaTeaspoon = sodaForCookie + sodaForBananasBread; 
		
		//convert teaspoon to ounces (6 teaspoon of salt in 1 ounce)
		double teaspoonPerOunce = 6.0;
		double sodaTeaspoonPerOunces = totalSodaTeaspoon/teaspoonPerOunce;
		
		//16oz of baking soda per box
		double ozPerBox = 16.0;
		double sodaBox = sodaTeaspoonPerOunces/ ozPerBox; 
		
		return (int) Math.ceil(sodaBox);
	}

	public static int bottlesOfVanilla(int cookieCount, int loafCount) {
		// 2 dozen cookies: 3/4 teaspoon of vanilla extract
		double vanillaCookieCount = 24.0;
		double vanillaRecipeCount = 3.0/4.0;
		
		double vanillaPerCookie = vanillaRecipeCount/ vanillaCookieCount;
		double vanillaForCookie = vanillaPerCookie * cookieCount;
				
		//1 loaf bananas bread: 3/4 teaspoon vanilla extract
		double vanillaPerBananasBread = 3.0/4.0; 
		double vanillaForBananasBread = vanillaPerBananasBread * loafCount; 
		
		// total amount of vanilla for ask recipe 
		double totalVanillaTeaspoon = vanillaForBananasBread + vanillaForCookie;
		
		//convert teaspoon to ounces (6 teaspoon of salt in 1 ounce)
		double teaspoonPerOunce = 6.0;
		double sodaTeaspoonPerOunces = totalVanillaTeaspoon/teaspoonPerOunce;
		
		//4oz of vanilla per bottle
		double ozPerBottle = 4.0;
		double vanillaBottle = sodaTeaspoonPerOunces/ozPerBottle; 
		
		return (int) Math.ceil(vanillaBottle); 
	}

	public static int cartonsOfEggs(int cookieCount, int loafCount) {
		// 2 dozen cookies: 1 egg
		double eggCookieCount = 24.0;
		double eggRecipeCount = 1.0;
		
		double eggPerCookies = eggRecipeCount / eggCookieCount; 
		double eggForCookies = eggPerCookies * cookieCount;
		
		// 1 loaf bananas bread: 1 egg
		double eggPerBananasBread = 1.0;
		double eggForBananasBread = eggPerBananasBread *loafCount;
		
		//total amount of eggs for ask recipe
		double totalEggs = eggForBananasBread + eggForCookies; 
		
		//12 eggs per carton
		double eggsPerCarton = 12.0;
		double eggsCarton = totalEggs/eggsPerCarton;
			
		return (int) Math.ceil(eggsCarton);
	}

	public static int bagsOfSugar(int cookieCount, int loafCount) {
		// 2 dozen cookies: 1 1/4 cups of sugars
		double sugarCookieCount = 24.0;
		double sugarRecipeCount = 5.0/4.0;
		
		double sugarPerCookie = sugarRecipeCount / sugarCookieCount; 
		double sugarForCookie = sugarPerCookie * cookieCount;
		
		//1 loaf bananas bread: 1/2 cups of sugars
		double sugarPerBananasBread = 1.0/2.0;
		double sugarForBananasBread = sugarPerBananasBread * loafCount;
		
		//total amount of sugar for ask recipe
		double totalSugar = sugarForBananasBread + sugarForCookie; 
		
		//convert cups to pounds: 2 cups of sugar in a pound
		double cupsOfSugarPerPound = 2.0;
		double sugarCupsPerPound = totalSugar/cupsOfSugarPerPound;
		
		//1lb of sugar
		double sugarPerBag = 1.0;
		double sugarBag = sugarCupsPerPound/sugarPerBag; 
		
		return (int) Math.ceil(sugarBag); 
	}

	public static int packagesOfButter(int cookieCount, int loafCount) {
		//2 dozen cookies: 3/4 cup of butter
		double butterCookieCount = 24.0;
		double butterRecipeCount = 3.0/4.0;
		
		double butterPerCookie = butterRecipeCount / butterCookieCount;
		double butterForCookie = butterPerCookie * cookieCount;
		
		//1 loaf bananas bread: ⅓ cups of butter
		double butterPerBananasBread = 1.0/3.0; 
		double butterForBananasBread = butterPerBananasBread * loafCount; 
		
		//total amount of butter for ask recipe 
		double totalButter = butterForBananasBread + butterForCookie; 
		
		//1/2 cups of butter is a stick 
		double butterStickPerButter = 1.0/2.0;
		double butterStick = totalButter*butterStickPerButter;
		
		return (int) Math.ceil(butterStick);
	}

	public static int bananas(int cookieCount, int loafCount) {
		//2 dozen cookies: 0 bananas
		int bananasCookies = 0 * cookieCount;
	
		//1 loaf of bananas bread: 2 bananas
		int bananasPerBananasBread = 2;
		int bananasForBababasBread = bananasPerBananasBread * loafCount + bananasCookies;
		
		return (int) bananasForBababasBread; 
	}

	public static int bagsOfChocolateChips(int cookieCount, int loafCount) {
		//2 dozen cookies: 1 3/4 cups of chocolate chips
		double chipCookieCount = 24.0;
		double chipRecipeCount = 7.0/4.0;
		
		double chipPerCookie = chipRecipeCount/chipCookieCount;
		
		//1 loaf bananas bread: 0 chocolateChips
		double chipsPerBananas = 0 * loafCount;  
		
		double chipForCookie = chipPerCookie * cookieCount + chipsPerBananas; 
		
		//2 cups of chips is a bag
		double cupPerBag = 2.0;
		double chipBag = chipForCookie/cupPerBag; 
		
		return (int) Math.ceil(chipBag);
	}

	public static double totalCost(int cookieCount, int loafCount) {
		//return total cost per ingredients
		
		return 2.10*bagsOfFlour(cookieCount,loafCount) + 0.95*containersOfSalt(cookieCount,loafCount) + 
				1.18*boxesOfBakingSoda(cookieCount,loafCount) + 19.56*bottlesOfVanilla(cookieCount,loafCount) + 
				4.81*cartonsOfEggs(cookieCount,loafCount) + 0.96*bagsOfSugar(cookieCount,loafCount) +
				3.23*packagesOfButter(cookieCount,loafCount) + 0.44*bananas(cookieCount,loafCount) + 
				1.99*bagsOfChocolateChips(cookieCount,loafCount); 
	} 

}

