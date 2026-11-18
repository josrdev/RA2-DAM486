package dam.m6.uf2;

public class Atleta {
    private int id;
    private String name;
    private int sportId;

    public Atleta(String name, int sportId) {
        this(0, name, sportId);
    }

    public Atleta(int id, String name, int sportId) {
        this.id = id;
        this.name = name;
        this.sportId = sportId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSportId() {
        return sportId;
    }
}
