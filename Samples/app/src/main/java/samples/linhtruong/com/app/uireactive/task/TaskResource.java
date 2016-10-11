package samples.linhtruong.com.app.uireactive.task;

import javax.inject.Inject;

import samples.linhtruong.com.app.uireactive.manager.ResManager;
import samples.linhtruong.com.app.uireactive.manager.RxDbManager;

/**
 * Created by Truong on 9/26/16 - 17:44.
 * Description: provide connection to un-expose singleton
 */

public class TaskResource {

    @Inject
    RxDbManager dbManager;

    @Inject
    ResManager resManager;
}
