
import java.io.IOException;
import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
import java.util.Scanner;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
//import org.jsoup.nodes.Node;
//import org.jsoup.nodes.Node;
//import org.jsoup.select.Elements;
import org.jsoup.select.Elements;


public class searchEngine {
	String input;
	String URL;
	Scanner in;
	ArrayList<String> actresses;
	//	private HashMap<String, Integer> wordHash;
	//static Scanner input;
	searchEngine(String URL, String input){
		this.input=input;
		this.URL=URL;
		in = new Scanner(URL);
		//	wordHash = new HashMap<String, Integer>();
	}
	public void parseBestMovie(){
		try{
			Document doc  = Jsoup.connect(URL).get();


			for (Element table : doc.select("table.wikitable")) {

				for (Element row : table.select("tr")) {
					String movieRow = row.text();

					//for(Element link : row.select("href")){
					if (movieRow.contains(input)){
						int cutDisney = movieRow.indexOf("Dis");
						int cutWalt = movieRow.indexOf("Walt");

						if (movieRow.indexOf("Walt")<movieRow.indexOf(cutDisney)){
							String movie = movieRow.substring(0, cutWalt);
							System.out.println("Movie:   "+movie);

						}
						else if (movieRow.indexOf("Pixar")>movieRow.indexOf(cutDisney)){
							String movie=movieRow.substring(0, movieRow.indexOf("Pixar"));
							System.out.println("Movie:   "+movie);
						}
						else {
							String movie=movieRow.substring(0, movieRow.indexOf(input));
							System.out.println("Movie:   "+movie);

						}
						//	else if (movieRow.contains)
						//    System.out.println("this is TDS:       " + movieRow.first());
						//   }
						//    System.out.println(row.text());
						//   }

					}
				}
				//  }
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void linkList(String URL)
	{
		//ArrayList<String> ref = new ArrayList<String>();
		ArrayList<Elements> refs = new ArrayList<Elements>();

		Document doc=null;

		try {
			doc = Jsoup.connect(URL).get();
			String html = doc.html();
			Jsoup.parse(html);
			Elements e = doc.select("[href]"); 


			//Elements ef = doc.getElementsByAttributeStarting([table]) + doc.getElementById([/table]);
			refs.add(e);
			//String absHref = e.attr("title"); 
			//	if((absHref.contains("d")))
			//if (e.contains("a")){
			//		System.out.println(e);
			//}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		//return doc;

	}
	public void parseScreenplay() {
		try{
			Document doc  = Jsoup.connect(URL).get();
			for (Element table : doc.select("table.wikitable")) {

				for (Element row : table.select("tr")) {
					String movieRow = row.text();
					if (movieRow.contains(input)){
						int index =input.length();
						String movie=movieRow.substring(index, movieRow.length());
						System.out.println("Winning Writers:   "+movie);

					}
				}
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Yo something is not right here...");
			e.printStackTrace();
		}
	}
	public void parseActor(){
		try{
			Document doc  = Jsoup.connect(URL).get();
			System.out.println("input "+ input);
			for (Element table : doc.select("table.wikitable")) {
				for (Element row : table.select("tr")) {
					String movieRow = row.text();	
					if (movieRow.contains(input)){
						//Element tds=row.select("td").first();
						String Actor = row.select("td").first().text(); 

						System.out.println("Actors Who Played Role:   "+Actor);

					}
				}
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	private ArrayList<String> parseActressInfo(){
		//need all nominated actresses and movie and age at nomination
		actresses= new ArrayList<String>();;

		try{
			Document doc  = Jsoup.connect(URL).get();
			ArrayList<String> URL = new ArrayList<String>();
			Elements urls = doc.select("[href]"); 
			for(Element e: urls){
				String absHref = e.attr("abs:href"); 
				URL.add(absHref);

			}
			//	String url;
			for (Element table : doc.select("table.wikitable")) {
				if (table.text().contains(input)){
					Elements rows = table.select("tr");
					for (int i=0; i < rows.size(); i++){
						Element row = rows.get(i);

						if (row.text().contains(input)){

							for  (int j=1; j <6; j++){
								row=rows.get(i+j);

								String actressName =row.select("td").first().text();
								String movieName = row.select("td").get(2).text();
								String firstName =actressName.substring(0,actressName.indexOf(" "));
								String lastName =actressName.substring(actressName.indexOf(" ")+1);
								for (String e: URL){
									if((e.contains(firstName))&& (e.contains(lastName))){
										actresses.add(actressName + "  " + e+ "   "+movieName);  

									}
								}
								//		String actressLine =row.select("td").first().html();

								//	System.out.println("THE HTML  " +actressLine);
							}
						}
					}
				}
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actresses;
	}
	public void findActressInfo(){
		String name;
		String movie;
		String link;
		String actress;
		ArrayList<String> URL = new ArrayList<String>();
		int size = parseActressInfo().size();
		int i =0;
		while (i < size){
			if (i==0){
				actress=parseActressInfo().get(i);
				link=actress.substring(actress.indexOf("  ")+2, actress.indexOf("   "));
				movie=actress.substring(actress.indexOf("   "));
				name=actress.substring(0,actress.indexOf("  "));
				URL.add(link);
				try {
					Document doc  = Jsoup.connect(link).get();
					String Document = doc.html();
					in=new Scanner(Document);

					String line;
					while (in.hasNext()){
						line = in.nextLine();
						if (line.contains("bday")){
							//String cut = "bday";
							// int cutz = cut.length()+2;
							String edityear=line.substring((line.indexOf("bday")), (line.indexOf("bday")+10));
							String bday=edityear.substring(6,10);
							int year = Integer.parseInt(bday);
							int winYear = Integer.parseInt(input);
							int age=winYear-year;
							System.out.println("\n" + "Actress" +	name +"\n" +" Movie Nomination: " +movie);

							System.out.println("Age of nomination:  " +age);
						}

					}


				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				i++;

			}
			else{
				actress=parseActressInfo().get(i);
				String prev=parseActressInfo().get(i-1);
				if (actress.equals(prev)){
					i++;
				}
				else{
					name=actress.substring(0,actress.indexOf("  "));
					movie=actress.substring(actress.indexOf("   "));
					link=actress.substring(actress.indexOf("  ")+2, actress.indexOf("   "));
					URL.add(link);
					try {
						Document doc  = Jsoup.connect(link).get();
						String Document = doc.html();
						in=new Scanner(Document);

						String line;
						while (in.hasNext()){
							line = in.nextLine();
							if (line.contains("bday")){
								String edityear=line.substring((line.indexOf("bday")), (line.indexOf("bday")+10));
								String bday=edityear.substring(6,10);
								int year = Integer.parseInt(bday);
								int winYear = Integer.parseInt(input);
								int age=winYear-year;
								System.out.println("\n" + "Actress" +	name +"\n" +" Movie Nomination: " +movie);

								System.out.println("Age of nomination:  " +age);
							}

						}
						//Elements line = doc.getElementsByAttribute("span");
						// System.out.println(" THE LINE READS: " +line);
						//int TARGET=6;


					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					i++;	
				}
			}
		}
	}
	public void parseBestDirector(){
		ArrayList<String> directors = new ArrayList<String>();

		try{
			Document doc  = Jsoup.connect(URL).get();

			//	String url;
			for (Element table : doc.select("table.multicol")) {
				if (table.text().contains(input)){
					Elements rows = table.select("li");
					for (int i=0; i < rows.size(); i++){
						String row = rows.get(i).html();
						in=new Scanner(row);
						String line;
						while (in.hasNext()){
							line = in.nextLine();
							if (line.contains("(")&& line.contains(")")){

								String nomNumber =line.substring((line.indexOf("</a> ("))+6, (line.indexOf("</a> ("))+7);
								//System.out.println(nomNumber);
								//String z=nomNumber.substring(6);
								int nom = Integer.parseInt(nomNumber);
								int param = Integer.parseInt(input);
								if (nom > param){

									String directorName =line.substring((line.indexOf("title=")+7), (line.indexOf(">")-1));
									directors.add(directorName);
									System.out.println(directorName);
								}
							}
						}
					}
				}
			}
			for (String d: directors){
				System.out.println("Director:   "+ d);
				for (Element table : doc.select("table.wikitable")) {
					for (Element row : table.select("tr")) {
						//String movieRow = row.text();	
						for (Element tds: row.select("td")){
							if (tds.text().contains(d)){
								//int h = tds.text().indexOf("title");
								String Movie =tds.html();
								String movie = Movie.substring(Movie.indexOf(" Ð <i><a href=")+13);
								String ans = movie.substring(movie.indexOf("title")+6, movie.indexOf(">"));
								//String Actor = tds.text(); 

								System.out.println("Movie Nominations:   "+ ans);

							}
						}
						//Element tds=row.select("td").first();

					}
				}
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void findCountry(){
		ArrayList<String> countries = new ArrayList<String>();
		ArrayList<String> cCount = new ArrayList<String>();

		//class="mw-redirect">
		try {
			String countLine="";
			//System.out.println(URL);
			Document doc  = Jsoup.connect(URL).get();
			for (Element table : doc.select("table")) {	
				for (Element rows : table.select("tr")){

					for (Element lines : rows.select("td")){
						if (lines.html().contains("flagicon")){
							int z=	(lines.html().indexOf("img alt=")+1);
							String country=lines.html().substring(z, lines.html().indexOf("src="));
							countLine = country.substring(7);
							cCount.add(countLine);
						}
						if (lines.html().contains("amp") && cCount.size()<20){
							String count = lines.html().substring(lines.html().indexOf(">+</span>"));
							String zCount = count.substring(9);
							int number = Integer.parseInt(zCount);
							countries.add(number+" "+countLine);
							//	System.out.println(counter);
						}

					}
				}
			}
			int winner=0;
			int largest=Integer.parseInt(countries.get(0).substring(0,countries.get(0).indexOf(" ")));
			int i=0;
			while ( i < countries.size()){
				int compare=Integer.parseInt(countries.get(i).substring(0,countries.get(i).indexOf(" ")));
				if (largest > compare){
					i++;
				}
				else{
					largest=compare;
					winner=i;
					i++;
				}
			}
			System.out.println("The Winner is  "+ countries.get(winner));
		}

		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private ArrayList<String> findBestAwardandActor(){
		ArrayList<String> links = new ArrayList<String>();
		Document doc;
		System.out.println(URL);
		try {
			doc = Jsoup.connect(URL).get();
			if (URL.equals("http://en.wikipedia.org/wiki/Academy_Award_for_Best_Animated_Short_Film")){
				for (Element table : doc.select("table.wikitable")) {	
					for (Element rows : table.select("tr")){
						for (Element ul: rows.select("ul")){
							for (Element block : ul.select("li")){
								Element moveLine = block.select("li").first();
								Elements e = moveLine.select("[href]"); 
								String absHref = e.attr("abs:href"); 
								links.add(absHref);
								//}
							}
						}
					}
				}

				//	}


			}
			else{
				for (Element table : doc.select("table.wikitable")) {	
					for (Element rows : table.select("tr")){

						for (Element block : rows.select("td")){

							if (block.html().contains("<b>")){
								Elements e = block.select("[href]"); 
								String absHref = e.attr("abs:href"); 
								//	System.out.println("LINK: "+absHref);
								links.add(absHref);

							}

						}
					}

				}

			}
		}


		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return links;

	}
	public void parseNoms() {
		// TODO Auto-generated method stub
		//System.out.println(" input" +input);

		Document document;
		try {
			int size=(findBestAwardandActor().size());
			for (int i=0; i<size; i++){
				document = Jsoup.connect(findBestAwardandActor().get(i)).get();
				for (Element tables : document.select("table")) {	
					for (Element rows : tables.select("tr")){
						if (rows.html().contains("Starring")){
							for (Element block : rows.select("td")){
								Element moveLine = block.select("li").first();
								System.out.println(moveLine);
							}
						}
					}
				}
			}
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


	}
	public void bigFour(){
		Document doc;
		ArrayList<String> answers = new ArrayList<String>();

		try {
			doc = Jsoup.connect(URL).get();
			for (Element tables : doc.select("table.wikitable")) {	
				for (Element rows : tables.select("tr")){
					Elements block =rows.getAllElements();
					String movie = block.get(7).text();
					if (movie.contains("[note")){
						movie.substring(0, movie.indexOf("[note"));
					}
					answers.add(movie+ "\n"+"  Number of wins: " + block.get(6).text());
				}
			}
			for (int i=1; i <answers.size(); i++){
				System.out.println("The movie:  " + answers.get(i));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void mosts(){
		try {
			//System.out.println(" hi");
			Document doc = Jsoup.connect(URL).get();
			for (Element tables : doc.select("table.wikitable")) {	
				//System.out.println(" hi 1");
				for (Element rows : tables.select("tr")){
					//	System.out.println(" hi 2");

					Elements block =rows.getAllElements();
					//Element tds=block.get(index)
					String movie=null;
					int index=0;
					for (Element e : block){
						index++;
						//Elements move = ;
						if (e.html().contains("bgcolor")){

							movie=block.get(7).text();
							int thresh=(Integer.parseInt(input));
							int wins= Integer.parseInt(block.get(6).text());
							if (thresh <= wins ){
								String category =block.get(index+2).text();
								System.out.println("The Year of Win: "+category);
								if (movie.contains("[note")){
									movie.substring(0, movie.indexOf("[note"));
								}
								System.out.println(movie+ "\n"+"  Number of wins: " + block.get(6).text());
							}
						}
					}

				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void mostAwarded(){

		try{
			Document doc = Jsoup.connect(URL).get();
			for (Element tables : doc.select("table.wikitable")) {	
				for (Element rows : tables.select("tr")){
					Elements block =rows.getAllElements();
					//System.out.println(block);
					String movieInfo=null;
					if ( block.html().contains("td")){
						movieInfo = block.first().text();
						//String movie =movieInfo.substring(0,movieInfo.indexOf(" "));
						
					//	int thresh=(Integer.parseInt(input));
					//	String wins=movieInfo.substring(movieInfo.indexOf(movie),movieInfo.indexOf("  "));
					//	int win =Integer.parseInt(wins);
					//	if (thresh <= win ){
					
						System.out.println("The movies nominated: "+ movieInfo);
					}
				}
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


