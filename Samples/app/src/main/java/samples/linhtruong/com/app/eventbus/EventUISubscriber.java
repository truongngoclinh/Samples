package samples.linhtruong.com.app.eventbus;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 2/6/17 - 14:33.
 * @organization VED
 */

public abstract class EventUISubscriber  implements EventSubscriber {

    @Override
    public void onEvent(Event event) {

    }

    @Override
    public EventBus.RuntimeOption getOption() {
        return EventBus.RuntimeOption.MAIN_THREAD;
    }
}
