package sg.edu.np.mad.madpractical5;

public class User {
    private String name;
    public String getName() { return name; }
    public void setName(String n) { name = n; }
    private String description;
    public String getDescription() { return description; }
    public void setDescription(String d) { description = d; }
    private int id;
    public int getId() { return id; }
    public void setId(int i) { id = i; }
    private boolean followed;
    public boolean getFollowed() { return followed; }
    public void setFollowed(Boolean f) { followed = f; }
    public User() {}
    public User(String name, String description, int id, boolean followed) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.followed = followed;
    }


}

