package samples.linhtruong.com.base.task;

import javax.inject.Inject;

import samples.linhtruong.com.base.manager.DbManager;
import samples.linhtruong.com.base.manager.ResManager;

/**
 * Created by Truong on 9/26/16 - 17:44.
 * Description: provide connection to un-expose singleton
 */

public class TaskResource {

    @Inject
    DbManager dbManager;

    @Inject
    ResManager resManager;
}
