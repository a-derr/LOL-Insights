package Model.GameData;

public class GameEvent {
    private int EventID;
    private String EventName;
    private float EventTime;

    public int getEventID(){
        return EventID;
    }
    private void setEventID(int eventID){
        EventID = eventID;
    }

    public String getEventName(){
        return EventName;
    }
    private void setEventName(String eventName){
        EventName = eventName;
    }

    public float getEventTime(){
        return EventTime;
    }
    private void setEventTime(float eventTime){
        EventTime = eventTime;
    }
}
