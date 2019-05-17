package com.linhtruong.sample.test_1;

/**
 * @author linhtruong
 */
public class DiscoverManagerImp {
    private String mApplicationId = "app";
    private String mApplicationRevision = "1";
    private String mServiceId = "service";

    public DiscoverManagerImp() {
    }

    public void setRequestParameters(String applicationId, String applicationRevision, String serviceId) {
        if (applicationId != null) {
            this.mApplicationId = applicationId;
        }

        if (applicationRevision != null) {
            this.mApplicationRevision = applicationRevision;
        }

        if (serviceId != null) {
            this.mServiceId = serviceId;
        }
    }


    public static class Test {
        public Test() {
        }

    }

    public class InnerTest {
        public InnerTest() {
        }

        public String getApplicationId() {
            return mApplicationId;
        }

        public String getApplicationRevision() {
            return mApplicationRevision;
        }
        public String getServiceId() {
            return mServiceId;
        }
    }
}

