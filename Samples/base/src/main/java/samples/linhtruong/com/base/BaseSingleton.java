package samples.linhtruong.com.base;

/**
 * All singleton classes that have to be cleared/destroyed upon logout or app process killed.
 *
 * @author linhtruong
 * @date 2/6/17 - 14:11.
 * @organization VED
 */

public interface BaseSingleton {

    void onDestroy();
}
