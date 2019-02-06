package np.com.ajitkbaral.erashan.entity;

import java.io.Serializable;

public class Category implements Serializable {

    private int id;
    private String name;
    private String imageUrl;
    private int drawableResource;

    public Category() {
    }

    public Category(int id, String name, String imageUrl, int drawableResource) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.drawableResource = drawableResource;
    }

    public Category(int id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public Category(int id, String name, int drawableResource) {
        this.id = id;
        this.name = name;
        this.drawableResource = drawableResource;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getDrawableResource() {
        return drawableResource;
    }

    public void setDrawableResource(int drawableResource) {
        this.drawableResource = drawableResource;
    }
}
