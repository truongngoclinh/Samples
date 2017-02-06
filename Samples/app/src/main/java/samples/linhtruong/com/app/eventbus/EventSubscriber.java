package samples.linhtruong.com.app.eventbus;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 2/6/17 - 14:31.
 * @organization VED
 */

public interface EventSubscriber {

    void onEvent(Event event);

    EventBus.RuntimeOption getOption();
}
