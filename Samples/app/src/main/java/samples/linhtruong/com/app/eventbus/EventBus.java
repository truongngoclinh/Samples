package samples.linhtruong.com.app.eventbus;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import samples.linhtruong.com.base.BaseSingleton;
import samples.linhtruong.com.base.loop.UILooper;
import samples.linhtruong.com.base.loop.TaskThreadPool;
import samples.linhtruong.com.base.manager.SingletonManager;
import samples.linhtruong.com.utils.LogUtils;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 2/6/17 - 14:18.
 * @organization VED
 */

public class EventBus implements BaseSingleton {

    private static volatile EventBus mInstance;
    private Map<String, LinkedList<WeakReference<EventSubscriber>>> mMap;

    public enum RuntimeOption {
        MAIN_THREAD,
        NEW_THREAD
    }

    public static EventBus getInstance() {
        if (mInstance == null) {
            synchronized (EventBus.class) {
                if (mInstance == null) {
                    mInstance = new EventBus();
                }
            }
        }

        return mInstance;
    }

    public synchronized void subscribe(String type, EventSubscriber eventSubscriber) {
        if (eventSubscriber == null) {
            return;
        }

        LogUtils.d("event: " + type);
        if (mMap.containsKey(type)) {
            LinkedList<WeakReference<EventSubscriber>> events = mMap.get(type);
            Iterator<WeakReference<EventSubscriber>> iterator = events.iterator();

            while (iterator.hasNext()) {
                EventSubscriber currentSubscriber = iterator.next().get();
                if (currentSubscriber == null) {
                    iterator.remove(); // the event is garbage collected, should remove it from the list
                    continue;
                }

                if (currentSubscriber == eventSubscriber) {
                    iterator.remove(); // already registered
                    return;
                }
            }
        } else {
            LinkedList<WeakReference<EventSubscriber>> events = new LinkedList<>();
            events.add(new WeakReference<>(eventSubscriber));
            mMap.put(type, events);
        }
    }

    public synchronized void unsubscribe(String type, EventSubscriber eventSubscriber) {
        LogUtils.d("event: " + type);
        if (mMap.containsKey(type)) {
            LinkedList<WeakReference<EventSubscriber>> events = mMap.get(type);
            Iterator<WeakReference<EventSubscriber>> iterator = events.iterator();

            while (iterator.hasNext()) {
                EventSubscriber currentSubscriber = iterator.next().get();
                if (currentSubscriber == null) {
                    iterator.remove(); // the vent is garbage collected, should remove it from the list
                    continue;
                }

                if (currentSubscriber == eventSubscriber) {
                    iterator.remove();
                    break;
                }
            }

            if (events.size() == 0) {
                mMap.remove(type); /// remove whole list
            }
        }
    }

    public synchronized void fire(String type, Event event) {
        if (mMap.containsKey(type)) {
            LinkedList<WeakReference<EventSubscriber>> events = mMap.get(type);
            for (WeakReference<EventSubscriber> ev : events) {
                EventSubscriber eventSubscriber = ev.get();
                switch (eventSubscriber.getOption()) {
                    case MAIN_THREAD:
                        fireOnMainLoop(eventSubscriber, event);
                        break;

                    case NEW_THREAD:
                        fireOnNewThread(eventSubscriber, event);
                        break;
                }
            }
        }
    }

    private void fireOnMainLoop(final EventSubscriber eventSubscriber, final Event event) {
        LogUtils.d("eventId: " + event.getRequestId());
        UILooper.getInstance().post(new Runnable() {
            @Override
            public void run() {
                eventSubscriber.onEvent(event);
            }
        });
    }

    private void fireOnNewThread(EventSubscriber eventSubscriber, Event event) {
        LogUtils.d("eventId: " + event.getRequestId());
        TaskThreadPool.getPool().addTask(new TaskWrapper(eventSubscriber, event));
    }

    private EventBus() {
        mMap = new HashMap<>();
        SingletonManager.register(this);
    }

    @Override
    public void onDestroy() {
        mMap.clear();
        mInstance = null;
    }

    private class TaskWrapper implements Runnable {

        private EventSubscriber mEventSubscriber;
        private Event mEvent;

        public TaskWrapper(EventSubscriber eventSubscriber, Event event) {
            mEventSubscriber = eventSubscriber;
            mEvent = event;
        }

        @Override
        public void run() {
            mEventSubscriber.onEvent(mEvent);
        }
    }
}
