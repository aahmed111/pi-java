
package objects;


public enum Audience {
    PUBLIQUE(0, "Publique", "/img/ic_public.png"),
    MOI_UNIQUEMENT(1, "Moi uniquement", "/img/moi-uniquement.png");

    private final int id;
    private final String name;
    private final String imgSrc;

    Audience(int id, String name, String imgSrc) {
        this.id = id;
        this.name = name;
        this.imgSrc = imgSrc;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImgSrc() {
        return imgSrc;
    }
    
    
}