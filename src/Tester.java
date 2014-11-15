import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;


public class Tester {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		searchEngine search;
		Scanner input = new Scanner(System.in);

		ArrayList<String> urls = new ArrayList<String>();
		urls.add("http://en.wikipedia.org/wiki/Academy_Award_for_Best_Picture");
		urls.add("http://en.wikipedia.org/wiki/Academy_Award_for_Best_Writing_(Original_Screenplay)");
		urls.add("http://en.wikipedia.org/wiki/Academy_Award_for_Best_Actor");
		urls.add("https://en.wikipedia.org/wiki/Academy_Award_for_Best_Actress");
		urls.add("https://en.wikipedia.org/wiki/Academy_Award_for_Best_Director");
		urls.add("http://en.wikipedia.org/wiki/List_of_countries_by_number_of_Academy_Awards_for_Best_Foreign_Language_Film");
		urls.add("http://en.wikipedia.org/wiki/Academy_Award_for_Best_Animated_Feature");
		urls.add("http://en.wikipedia.org/wiki/Academy_Award_for_Best_Animated_Short_Film");
		urls.add("http://en.wikipedia.org/wiki/List_of_Big_Five_Academy_Award_winners_and_nominees");
		urls.add("http://en.wikipedia.org/wiki/List_of_films_receiving_six_or_more_Academy_Awards");

		Boolean again=true;
		while (again){
			System.out.println("what would you like the search for? " +
					"\n" + "1) Best Picture:" +
					  "\n" +"2) Best Screenplay?"
					  + "\n" + "3) Best Actor nominated role"
					  + "\n" 	+ "4) Best Actress nominees in a specific year"
					  + "\n" 	+ "5) Best Director "
					  + "\n" 	+ "6) Most nominated country"
					  +	"\n" 	+ " 7) Number of nominations in certain category with certain actor"
					  + "\n" 	+ "8) Big four movies"
					  + "\n" 	+ "9) Big five movies nominatees based on the year the movie was nominated on"
					  + "\n" 	+ "10) List of the movies with greater than 6 nominations");
			int answer;
			try{
				answer=input.nextInt();
if (answer > 0 && answer <= urls.size()){
					
					if (answer==1){
						
						String prod = input.next();
						search= new searchEngine(urls.get(answer-1), prod);
						search.parseBestMovie();
						again=false;
					}
					else if(answer==2){
						System.out.println("What is the name of the movie you want to search for?");
						 input.nextLine();
						 String movie = input.nextLine();
						search= new searchEngine(urls.get(answer-1), movie);
						search.parseScreenplay();
						again=false;
					}
					else if (answer==3){
						System.out.println("What is the name of the role you want to search for?");
						 String role = input.next();
						search= new searchEngine(urls.get(answer-1), role);
						search.parseActor();
						again=false;
					}
					else if (answer==4){
						System.out.println("What is the YEAR you want to search for?");
						 String year = input.next();
						search= new searchEngine(urls.get(answer-1), year);
						search.findActressInfo();
						again=false;
					}
					else if (answer==5){
						System.out.println("What is the number of nominations you want to search for?");
						 String year = input.next();
						search= new searchEngine(urls.get(answer-1), year);
						search.parseBestDirector();
						again=false;
					}
					else if (answer==6){
					
						 String year = null;
						 System.out.println("Just press enter");
						search= new searchEngine(urls.get(answer-1), year);
						search.findCountry();
						again=false;
					}
					else if (answer==7){
						System.out.println("What is the name of the Animated you want to search for?"+
								"\n" +
								"1) Best Animated Feature " + "\n" +
								"2) Best Animated Short " + "\n");
						int choice=input.nextInt();
						//Scanner in = new Scanner(System.in);						
						if (choice==1){
							input.nextLine();
							System.out.println("what is the name of the actor?");
							String actor = input.nextLine();
							search= new searchEngine(urls.get(answer-1), actor);
							search.parseNoms();

						}
						else{
							input.nextLine();
							System.out.println("what is the name of the actor?");
							String actor = input.nextLine();
							search= new searchEngine(urls.get(answer), actor);
							search.parseNoms();
							}
						
						
						again=false;
					}
					else if(answer==8){
						String choice=null;
						search= new searchEngine(urls.get(answer), choice);
						search.bigFour();
						again=false;

					}
					else if(answer==9){
						System.out.println("what is the number of nominations you want shown?");

						String choice=input.next();
						search= new searchEngine(urls.get(answer-1), choice);
						search.mosts();
						again=false;

					}
					else if(answer==10){
						//System.out.println("what is the number of nominations you want shown?");
						String choice=null;
						search= new searchEngine(urls.get(answer-1), choice);
						search.mostAwarded();
						again=false;

					}
					
				else{
					System.out.println("try again");
				}
			}
			}
			catch (InputMismatchException e) {
				System.out.println("try again");
			}	

		}

	}
}
