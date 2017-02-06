package paul.gayde.channelmessaging;

/**
 * Created by gaydep on 23/01/2017.
 */
public class Channel {

    int channelID;
    String name;
    String connectedusers;

    public Channel(String connectedusers, String name, int channelID) {
        this.connectedusers = connectedusers;
        this.name = name;
        this.channelID = channelID;
    }

    public int getChannelID() {
        return channelID;
    }

    public void setChannelID(int channelID) {
        this.channelID = channelID;
    }

    public String getName() { return name;  }

    public void setName(String name) {
        this.name = name;
    }

    public String getConnectedusers() {
        return connectedusers;
    }

    public void setConnectedusers(String connectedusers) {
        this.connectedusers = connectedusers;
    }
}
