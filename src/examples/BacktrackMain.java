package examples;

/**
 * Cette classe représente un lanceur pour le backtraking
 */
public class BacktrackMain{
	
	/**
     * Méthode qui permet de lancer notre backtraking
     * @param args les arguments au lancement
     */
    public static void main(String[] args){
		ExampleFactory backTracking = null;
		if(args.length == 2){
		  if(args[0].equals("2")){
			backTracking = new ExampleFactory("alea");
		  }else if(args[0].equals("1") && args[1].equals("1")){
			backTracking = new ExampleFactory("tri"+"MoinsVariable"+"contrainte");
		  }else if(args[0].equals("1") && args[1].equals("2")){
			backTracking = new ExampleFactory("tri"+"PlusVariable"+"contrainte");
		  }else if(args[0].equals("1") && args[1].equals("3")){
			backTracking = new ExampleFactory("tri"+"MoinsVariable"+"domaine");
		  }else if(args[0].equals("1") && args[1].equals("4")){
			backTracking = new ExampleFactory("tri"+"PlusVariable"+"domaine");
		  }else if(args[0].equals("3") && args[1].equals("1")){
			backTracking = new ExampleFactory("valeurs"+"MoinsValeur");
		  }else if(args[0].equals("3") && args[1].equals("2")){
			backTracking = new ExampleFactory("valeurs"+"PlusValeur");
		  }else{
			backTracking = new ExampleFactory("");
		  }
		}else{
		  backTracking = new ExampleFactory("");
		}
		  backTracking.backTracking();
    }
}
