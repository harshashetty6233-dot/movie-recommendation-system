import java.util.*;

class Movie {
    String name;
    String genre;
    double rating;
    int year;

    Movie(String name, String genre, double rating, int year) {
        this.name = name;
        this.genre = genre.toLowerCase();
        this.rating = rating;
        this.year = year;
    }
}

public class MovieRecommendation {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Movie> movies = new ArrayList<>();

        // Movie dataset with years
        movies.add(new Movie("Inception", "sci-fi", 4.8, 2010));
        movies.add(new Movie("Interstellar", "sci-fi", 4.7, 2014));
        movies.add(new Movie("The Matrix", "sci-fi", 4.6, 1999));
        movies.add(new Movie("Avatar", "sci-fi", 4.5, 2009));
        movies.add(new Movie("Gravity", "sci-fi", 4.2, 2013));

        movies.add(new Movie("Titanic", "romance", 4.5, 1997));
        movies.add(new Movie("The Notebook", "romance", 4.3, 2004));
        movies.add(new Movie("La La Land", "romance", 4.4, 2016));
        movies.add(new Movie("Before Sunrise", "romance", 4.2, 1995));
        movies.add(new Movie("Pride & Prejudice", "romance", 4.6, 2005));

        movies.add(new Movie("Avengers", "action", 4.6, 2012));
        movies.add(new Movie("John Wick", "action", 4.4, 2014));
        movies.add(new Movie("Mad Max", "action", 4.5, 2015));
        movies.add(new Movie("Gladiator", "action", 4.7, 2000));
        movies.add(new Movie("Die Hard", "action", 4.3, 1988));

        movies.add(new Movie("Joker", "drama", 4.7, 2019));
        movies.add(new Movie("Forrest Gump", "drama", 4.8, 1994));
        movies.add(new Movie("The Godfather", "drama", 4.9, 1972));
        movies.add(new Movie("Fight Club", "drama", 4.6, 1999));
        movies.add(new Movie("Whiplash", "drama", 4.7, 2014));

        System.out.println("=== MOVIE RECOMMENDATION SYSTEM ===");
        System.out.println("Available genres: action, drama, sci-fi, romance\n");

        // Ask for preferences
        System.out.print("Enter preferred genres (comma-separated, e.g., action,drama): ");
        String input = sc.nextLine().toLowerCase();

        System.out.print("Enter minimum rating (0-5, e.g., 4.0): ");
        double minRating = sc.nextDouble();

        System.out.print("Filter by minimum year? (enter 0 to skip): ");
        int minYear = sc.nextInt();
        sc.nextLine(); // consume newline

        String[] genres = input.split(",");

        List<Movie> recommended = new ArrayList<>();

        // Filter movies
        for (Movie m : movies) {
            for (String g : genres) {
                if (m.genre.equals(g.trim()) && m.rating >= minRating) {
                    if (minYear == 0 || m.year >= minYear) {
                        recommended.add(m);
                    }
                }
            }
        }

        // Sort by rating (highest first)
        Collections.sort(recommended, new Comparator<Movie>() {
            public int compare(Movie a, Movie b) {
                return Double.compare(b.rating, a.rating);
            }
        });

        // Show results
        System.out.println("\n📽️ TOP RECOMMENDATIONS 📽️");
        System.out.println("=========================");
        
        if (recommended.isEmpty()) {
            System.out.println("❌ No movies match your criteria. Try lower rating or different genres!");
        } else {
            System.out.println("Found " + recommended.size() + " movie(s) matching your preferences\n");
            
            System.out.print("How many recommendations do you want? (1-" + recommended.size() + "): ");
            int numToShow = sc.nextInt();
            numToShow = Math.min(numToShow, recommended.size());
            
            System.out.println("\n🎬 Your Personalized Recommendations:");
            System.out.println("---------------------------------");
            for (int i = 0; i < numToShow; i++) {
                Movie m = recommended.get(i);
                System.out.println((i + 1) + ". " + m.name);
                System.out.println("   Genre: " + m.genre.toUpperCase());
                System.out.println("   Rating: " + m.rating + " ⭐");
                System.out.println("   Year: " + m.year);
                System.out.println();
            }
            
            // Show average rating
            double avgRating = 0;
            for (Movie m : recommended) {
                avgRating += m.rating;
            }
            avgRating /= recommended.size();
            System.out.println("📊 Average rating: " + String.format("%.2f", avgRating) + " ⭐");
        }

        // Surprise movie option
        System.out.print("\n🎲 Want a random movie suggestion? (yes/no): ");
        String surprise = sc.next().toLowerCase();
        
        if (surprise.equals("yes")) {
            Random rand = new Random();
            Movie randomMovie = movies.get(rand.nextInt(movies.size()));
            System.out.println("\n✨ SURPRISE MOVIE ✨");
            System.out.println("Watch: " + randomMovie.name);
            System.out.println("Genre: " + randomMovie.genre);
            System.out.println("Rating: " + randomMovie.rating + " ⭐");
            System.out.println("Year: " + randomMovie.year);
        }

        sc.close();
        System.out.println("\n👋 Happy watching!");
    }
}