import java.util.Date;

public class Task {

    public int id;
    public String title, desc;
    public int quality, timeLimit, type;
    public boolean complete;
    private Date startTime;
    private int progress;

    public Task(){ //default constructor = blank object created
        complete = false;
    }


    public Task(int id, String title, String desc, int quality, int timeLimit, int type, boolean complete){
        this.id=id;
        this.title=title;
        this.desc=desc;
        this.quality=quality;
        this.timeLimit=timeLimit;
        this.type=type;
        this.complete=complete;
        if (type == 1) {
            this.progress = 0;
        }

    }

    public int calcExp(){
        //TODO - not in sprint 1, "Determining EXP gain" zenhub card
        return 0;
    }
    /** SETTERS **/
    public void setID(int id) {
        if (id < 0) throw new IllegalArgumentException("Invalid id");

        this.id = id;
    }
    public void setTitle(String title) { this.title = title; }
    public void setDesc(String desc) { this.desc = desc; }
    public void setQuality(int quality) {
        if (quality < 0) throw new IllegalArgumentException("Invalid quality"); //TODO max quality?
        this.quality = quality;
    }
    public void setTimeLimit(int timeLimit) {
        if (timeLimit < 0) throw new IllegalArgumentException("Invalid time"); //TODO max timeLimit?
        this.timeLimit = timeLimit;
    }
    public void setType(int type) throws IllegalArgumentException  {
        if (type < 0 || type > 3) throw new IllegalArgumentException("Invalid type");
        this.type = type;
    }
    public void complete() { this.complete = true; }

    public void startTime() { this.startTime = new Date(); }

    //TODO PROGRESS ADD

    /** GETTERS **/
    public boolean isTimed(){ return timeLimit > 0; }
    public int getID() { return id; }
    public String getTitle() { return title; }
    public String getDesc() { return desc; }
    public int getQuality() { return quality; }
    public int getTimeLimit() { return timeLimit; }
    public Date getStartTime() { return startTime; }
    public int getTypeInt() { return type; }
    public String getTypeStr() {
        switch(type){
            case 0: return "default";
            case 1: return "main";
            case 2: return "daily";
            case 3: return "weekly";
            default: return null;
        }
    }
    public int //TODO PROGRESS GET

    public boolean isComplete() { return complete; }
}
