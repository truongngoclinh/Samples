package com.linhtruong.sample.test;

import android.app.Application;
import com.linhtruong.sample.UnitTest;
import com.linhtruong.sample.test_1.DiscoverManagerImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * @author linhtruong
 */
public class DiscoverManagerImpTest {

    // class under test
//    @Mock
    private DiscoverManagerImp mSubject;
    private Application application;

    @Before
    public void setUp() throws IOException {
//        MockitoAnnotations.initMocks(this);
        mSubject = new DiscoverManagerImp();
        // yada yada
    }

    @Test
    public void testSetRequestParameters() throws NoSuchFieldException, IllegalAccessException {
        //Test with all null values.
        mSubject.setRequestParameters(null, null, null);
        DiscoverManagerImp.Test test = new DiscoverManagerImp.Test();
        DiscoverManagerImp.InnerTest innerTest = mSubject.new InnerTest();
        String applicationId = innerTest.getApplicationId();
        String applicationRevision = innerTest.getApplicationRevision();
        String serviceId = innerTest.getServiceId();

//        Field fieldApplicationId = mSubject.getClass().getDeclaredField("mApplicationId");
//        fieldApplicationId.setAccessible(true); // FIXME: don't test private methods
//        String applicationId = (String) fieldApplicationId.get(mSubject);
//        Field fieldApplicationRevision = mSubject.getClass().getDeclaredField("mApplicationRevision");
//        fieldApplicationRevision.setAccessible(true); // FIXME: don't test private methods
//        String applicationRevision = (String) fieldApplicationRevision.get(mSubject);
//        Field fieldServiceId = mSubject.getClass().getDeclaredField("mServiceId");
//        fieldServiceId.setAccessible(true); // FIXME: don't test private methods
//        String serviceId = (String) fieldServiceId.get(mSubject);
        Assert.assertEquals("app", applicationId);
        Assert.assertEquals("1", applicationRevision);
        Assert.assertEquals("service", serviceId);

        //Test applicationIdNotNull
        mSubject.setRequestParameters("app2", null, null);
//        applicationId = (String) fieldApplicationId.get(mSubject);
        applicationId = innerTest.getApplicationId();
        Assert.assertEquals("app2", applicationId);
        Assert.assertEquals("1", applicationRevision);
        Assert.assertEquals("service", serviceId);

        //Test applicatioRevisionNotNull
        mSubject.setRequestParameters(null, "2", null);
//        applicationRevision = (String) fieldApplicationRevision.get(mSubject);
        applicationRevision = innerTest.getApplicationRevision();
        Assert.assertEquals("app2", applicationId);
        Assert.assertEquals("2", applicationRevision);
        Assert.assertEquals("service", serviceId);

        //Test serviceIdNotNull
        mSubject.setRequestParameters(null, null, "service2");
        serviceId = innerTest.getServiceId();
//        serviceId = (String) fieldServiceId.get(mSubject);
        Assert.assertEquals("app2", applicationId);
        Assert.assertEquals("2", applicationRevision);
        Assert.assertEquals("service2", serviceId);

        //Test allNotNull
        mSubject.setRequestParameters("app", "1", "service");
        applicationId = innerTest.getApplicationId();
        applicationRevision = innerTest.getApplicationRevision();
        serviceId = innerTest.getServiceId();
//        applicationId = (String) fieldApplicationId.get(mSubject);
//        applicationRevision = (String) fieldApplicationRevision.get(mSubject);
//        serviceId = (String) fieldServiceId.get(mSubject);
        Assert.assertEquals("app", applicationId);
        Assert.assertEquals("1", applicationRevision);
        Assert.assertEquals("service", serviceId);
    }
}
