package samples.linhtruong.com.app.eventbus;

import java.util.UUID;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 2/6/17 - 16:27.
 * @organization VED
 */

public class EventId {

    private int mRawId;

    public EventId() {
        mRawId = _generateUUID();
    }

    public EventId(int id) {
        mRawId = id;
    }

    public EventId(String id) {
        mRawId = Integer.parseInt(id);
    }

    public int getId() {
        return mRawId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof EventId)) {
            return false;
        }

        EventId id = (EventId) o;
        return (mRawId == id.getId());
    }

    @Override
    public int hashCode() {
        return mRawId;
    }

    private static int _generateUUID() {
        UUID uuid = UUID.randomUUID();
        long msb = uuid.getMostSignificantBits();
        long lsb = uuid.getLeastSignificantBits();
        int v = (int) (msb ^ lsb & 0xffff);
        if (v < 0) {
            return -v;
        }
        return v;
    }
}
