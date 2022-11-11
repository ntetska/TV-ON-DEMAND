public class Film extends FilmAndSerie {
    private int length;

    public Film(int id,
                String title,
                String description,
                int release_year,
                String language,
                String original_language,
                int length,
                String rating,
                String special_features)
    {
        super(
                id,
                title,
                description,
                release_year,
                language,
                original_language,
                rating,
                special_features
            );
        this.length = length;
    }

    public int getLength() {
        return length;
    }
}
