import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

public class Task {

    public int id;
    public String title, desc;
    public int baseQuality, timeLimit, type;
    public double completionQuality;
    public boolean complete;
    private LocalDateTime startTime;
    private int progress;

    public Task(){ //default constructor = blank object created
        title = "";
        complete = false;
    }


    public Task(int id, String title, String desc, int baseQuality, int timeLimit, int type, boolean complete){
        this.id=id;
        this.title=title;
        this.desc=desc;
        this.baseQuality = baseQuality;
        this.timeLimit=timeLimit;
        this.type=type;
        this.complete=complete;
        if (type == 1) {
            this.progress = 0;
        }

    }

    public double calcExp(){
        double xp = baseQuality * completionQuality;
        //TODO - "Determining EXP gain" zenhub card
        return xp;
    }
    /** SETTERS **/
    public void setID(int id) {
        if (id < 0) throw new IllegalArgumentException("Invalid id");

        this.id = id;
    }
    public void setTitle(String title) { this.title = title; }
    public void setDesc(String desc) { this.desc = desc; }
    public void setBaseQuality(int baseQuality) {
        if (baseQuality < 0) throw new IllegalArgumentException("Invalid baseQuality"); //TODO max baseQuality?
        this.baseQuality = baseQuality;
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
    public void setCompletionQuality(double completionQuality) {
        this.completionQuality = completionQuality;
    }
    public void startTime() { this.startTime =  LocalDateTime.now(); }

    public void addProgress(int progress) {

        this.progress+=progress;
        if (this.progress > 100) this.progress = 100;
    }

    /** GETTERS **/

    public boolean checkIfTimed(){ return timeLimit > 0; }
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDesc() { return desc; }
    public int getBaseQuality() { return baseQuality; }
    public int getTimeLimit() { return timeLimit; }
    public LocalDateTime getStartTime() { return startTime; }
    public int getType() { return type; }
    @JsonIgnore
    public String getTypeStr() {
        switch(type){
            case 0: return "default";
            case 1: return "main";
            case 2: return "daily";
            case 3: return "weekly";
            default: return null;
        }
    }
    public int getProgress() { return progress; }
    public double getCompletionQuality() { return completionQuality; }
    public boolean isComplete() { return complete; }

    public String toString(){ //TODO could make this a little more complicated if needed
        String result = "";
        if (!title.isEmpty()) {
            result += "TASK\nid: " + id + "  title: " + title + "\ndesc: " + desc + "\nbaseQuality: " + baseQuality + "  timelimit: " + timeLimit +
                    "  type: " + type + "  complete: " + complete + "  progress: " + progress;
            return result;
        }
        else{ return "Empty task object."; }
    }
}
