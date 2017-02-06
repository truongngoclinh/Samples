package samples.linhtruong.com.app.eventbus;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 2/6/17 - 15:15.
 * @organization VED
 */

public class Event {

    private Object data;
    private EventId requestId;

    public Event() {
        this.requestId = new EventId();
    }

    public Event(int id) {
        this.requestId = new EventId(id);
    }

    public Event(int id, Object data) {
        this.requestId = new EventId(id);
        this.data = data;
    }

    public Event(String id) {
        this.requestId = new EventId(id);
    }

    public Event(String id, Object data) {
        this.requestId = new EventId(id);
        this.data = data;
    }

    public EventId getRequestId() {
        return requestId;
    }

    public Object getData() {
        return data;
    }

}
