package noor.application.noortaskm;

public class Mytask
{
private String key;
private String title;
private String subject;
private String owner;
private  String important;
private String image;


    public void setKey(String key) {
        this.key = key;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setImportant(String important) {
        this.important = important;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Mytask() {
    }
    public String getKey() {
    return key;
}

    public String getTitle() {
    return title;
}

    public String getSubject() {
    return subject;
}

    public String getOwner() {
    return owner;
}

    public String getImportant() {
    return important;
}

    public String getImage() {
    return image;
}
}

