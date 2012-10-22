package org.exoplatform.social.client.core.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.exoplatform.social.client.api.SocialClientLibException;
import org.exoplatform.social.client.api.common.RealtimeListAccess;
import org.exoplatform.social.client.api.model.RestActivity;
import org.exoplatform.social.client.api.model.RestIdentity;
import org.exoplatform.social.client.core.AbstractClientTestV1Alpha3;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class ActivitiesRealtimeListAccessV1Alpha3IT extends AbstractClientTestV1Alpha3{

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
        //OK
      }
    }
  }

  @AfterMethod
  @Override
  public void tearDown() {
    super.tearDown();
  }

  /**
   * @param numberOfActivity number of activity to create
   * @return list of activity created
   * @throws SocialClientLibException
   */
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
  
  /**
   * test getNewer activities of stream and feed
   * @throws SocialClientLibException
   */
  @Test
  public void shouldGetNewer() throws SocialClientLibException {
    if (!canRunTest()) {
      return;
    }
    startSessionAs("demo", "gtn");
    RestIdentity restIdentity = identityService.getIdentity("organization", "demo");
    
    //demo creates 5 activities
    createActivities(5);
    
    //get all activities stream of demo
    RealtimeListAccess<RestActivity> listStream = activityService.getActivityStream(restIdentity);
    
    Long sinceTime = listStream.load(0, 5)[2].getPostedTime();
    RestActivity[] listNewerActivity = listStream.loadNewer(sinceTime, 10);
    assertThat(listNewerActivity.length, equalTo(2));
    
    //get all activities feed of demo
    RealtimeListAccess<RestActivity> listFeed = activityService.getFeedActivityStream(restIdentity);
    
    sinceTime = listFeed.load(0, 5)[3].getPostedTime();
    listNewerActivity = listFeed.loadNewer(sinceTime, 10);
    assertThat(listNewerActivity.length, equalTo(3));
  }
  
  /**
   * test getOlder activities of stream and feed
   * @throws SocialClientLibException
   */
  @Test
  public void shouldGetOlder() throws SocialClientLibException {
    if (!canRunTest()) {
      return;
    }
    startSessionAs("demo", "gtn");
    RestIdentity restIdentity = identityService.getIdentity("organization", "demo");
    
    //demo creates 5 activities
    createActivities(5);
    
    //get all activities stream of demo
    RealtimeListAccess<RestActivity> listStream = activityService.getActivityStream(restIdentity);
    
    Long maxTime = listStream.load(0, 5)[1].getPostedTime();
    RestActivity[] listOlderActivity = listStream.loadOlder(maxTime, 5);
    assertThat(listOlderActivity.length, equalTo(3));
    
    //get all activities feed of demo
    RealtimeListAccess<RestActivity> listFeed = activityService.getFeedActivityStream(restIdentity);
    
    maxTime = listFeed.load(0, 5)[3].getPostedTime();
    listOlderActivity = listFeed.loadOlder(maxTime, 5);
    assertThat(listOlderActivity.length, equalTo(1));
  }
  
}
