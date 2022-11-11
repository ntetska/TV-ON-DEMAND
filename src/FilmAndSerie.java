public abstract class FilmAndSerie {
    private int id;
    private String title;
    private String description;
    private int release_year;
    private String language;
    private String original_language;
    private String rating;
    private String special_features;



    public FilmAndSerie(int id, String title, String description, int release_year, String language, String original_language, String rating, String special_features) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.release_year = release_year;
        this.language = language;
        this.original_language = original_language;
        this.rating = rating;
        this.special_features = special_features;
    }



    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getRelease_year() {
        return release_year;
    }

    public String getLanguage() {
        return language;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getRating() {
        return rating;
    }

    public String getSpecial_features() {
        return special_features;
    }
}
