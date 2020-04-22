public class Achievement{
    public int id;
    public String title;
    public int quality;
    public boolean complete;
    private String description;

    public Achievement(int id, String title, int quality, String desc){
        if(id<0==true || quality<0==true){
            throw new IllegalArgumentException("The values of your id and quality are invalid");

        }
        else {
            this.id = id;
            this.title = title;
            this.description = desc;
            this.quality = quality;
            this.complete = true;
        }
    }

    public boolean getComplete(){
        return complete;
    }

    public String getTitle() {
        return title;
    }

    public int getQuality() {
        return quality;
    }

    public int getID() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}