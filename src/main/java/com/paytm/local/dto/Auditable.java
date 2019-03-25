package com.paytm.local.dto;

/**
 * interface to be implemented by entities for which auditing is required
 * its implementation class must define getEntityId
 */
public interface Auditable {

    String getEntityId();

}
