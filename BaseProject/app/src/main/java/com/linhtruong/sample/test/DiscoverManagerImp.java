package com.linhtruong.sample.test;

/**
 * @author linhtruong
 */
public class DiscoverManagerImp {
    protected String applicationId;
    protected String applicationRevision;
    protected String serviceId;

    public DiscoverManagerImp() {
        applicationId = "";
        applicationRevision = "";
        serviceId = "";
    }

    public void setRequestParameters(String applicationId, String applicationRevision, String serviceId) {
        this.applicationId = applicationId;
        this.applicationRevision = applicationRevision;
        this.serviceId = serviceId;
    }
}
