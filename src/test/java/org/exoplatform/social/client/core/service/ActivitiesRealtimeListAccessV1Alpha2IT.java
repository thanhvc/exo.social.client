package org.exoplatform.social.client.core.service;

import java.util.ArrayList;
import java.util.List;

import org.exoplatform.social.client.api.SocialClientLibException;
import org.exoplatform.social.client.api.model.RestActivity;
import org.exoplatform.social.client.core.AbstractClientTestV1Alpha2;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ActivitiesRealtimeListAccessV1Alpha2IT extends AbstractClientTestV1Alpha2 {
  private List<RestActivity> tearDownActivityList;
  
  @BeforeMethod
  @Override
  public void setUp() {
    super.setUp();
  }

  @Override
  public void afterSetup() {
    tearDownActivityList = new ArrayList<RestActivity>();
  }

  @Override
  public void beforeTearDown() {
    startSessionAs("demo", "gtn");
    for (RestActivity activity : tearDownActivityList) {
      try {
        activityService.delete(activity);
      } catch (Exception e) {
        // OK
      }
    }
  }

  @AfterMethod
  @Override
  public void tearDown() {
    super.tearDown();
  }
  
  @Test
  public void testWithGetRealtimeActivityWithLimit100() {
//    not stable ???
//    startSessionAs("demo", "gtn");
//    RestIdentity restIdentity = identityService.getIdentity("organization", "demo");
//    createActivities(200);
//    
//    RealtimeListAccess<RestActivity> activities = new ActivitiesRealtimeListAccessV1Alpha2(restIdentity, ActivityType.ACTIVITY_STREAM, null);
//    assertThat("RealtimeListAccess<RestActivity> getOlder().size should have size = 100", 100, equalTo(activities.loadAsList(0, 100).size()));
    
  }
  
  private List<RestActivity> createActivities(int numberOfActivity) throws SocialClientLibException {
    List<RestActivity> createdActivityList = new ArrayList<RestActivity>();
    for (int i = 0; i < numberOfActivity; i++) {
      RestActivity restActivityToCreate = new RestActivity();
      restActivityToCreate.setTitle("test " + i);
      RestActivity createdActivity = activityService.create(restActivityToCreate);
      createdActivityList.add(createdActivity);
      tearDownActivityList.add(createdActivity);
    }
    return createdActivityList;
  }
}
