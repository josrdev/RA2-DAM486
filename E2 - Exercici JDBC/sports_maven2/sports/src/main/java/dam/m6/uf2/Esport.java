package dam.m6.uf2;

public class Esport {
    private int id;
    private String name;

    public Esport(String name) {
        this(0, name);
    }

    public Esport(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
