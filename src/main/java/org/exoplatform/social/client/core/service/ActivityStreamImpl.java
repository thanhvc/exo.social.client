package org.exoplatform.social.client.core.service;

import java.util.List;

import org.exoplatform.social.client.api.auth.AccessDeniedException;
import org.exoplatform.social.client.api.common.Period;
import org.exoplatform.social.client.api.common.RealtimeListAccess;
import org.exoplatform.social.client.api.event.LifecycleListener;
import org.exoplatform.social.client.api.model.Activity;
import org.exoplatform.social.client.api.model.Comment;
import org.exoplatform.social.client.api.model.Identity;
import org.exoplatform.social.client.api.model.Like;
import org.exoplatform.social.client.api.service.ActivityService;
import org.exoplatform.social.client.api.service.ServiceException;

public class ActivityStreamImpl extends AbstractLifecycle<Activity, ActivityService<Activity>> implements ActivityService<Activity> {

  @Override
  public Activity create(Activity newInstance) throws AccessDeniedException, ServiceException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Activity get(String uuid) throws AccessDeniedException, ServiceException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Activity update(Activity existingInstance) throws AccessDeniedException, ServiceException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void delete(Activity existingInstance) throws AccessDeniedException, ServiceException {
    // TODO Auto-generated method stub
    
  }

  
  @Override
  public RealtimeListAccess<Activity> getActivityStream(Identity identity,
                                                        List<String> appIds,
                                                        Period period) throws AccessDeniedException,
                                                                      ServiceException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public RealtimeListAccess<Activity> getActivityStream(Identity identity) throws AccessDeniedException,
                                                                          ServiceException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public RealtimeListAccess<Activity> getActivityStream(Identity identity, List<String> appIds) throws AccessDeniedException,
                                                                                               ServiceException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public RealtimeListAccess<Activity> getActivityStream(Identity identity, Period period) throws AccessDeniedException,
                                                                                         ServiceException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public RealtimeListAccess<Activity> getActivityStream(List<Identity> identityList,
                                                        List<String> appIds,
                                                        Period period) throws AccessDeniedException,
                                                                      ServiceException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public RealtimeListAccess<Activity> getActivityStream(List<Identity> identityList) throws AccessDeniedException,
                                                                                    ServiceException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public RealtimeListAccess<Activity> getActivityStream(List<Identity> identityList,
                                                        List<String> appIds) throws AccessDeniedException,
                                                                            ServiceException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public RealtimeListAccess<Activity> getActivityStream(List<Identity> identityList, Period period) throws AccessDeniedException,
                                                                                                   ServiceException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Comment createComment(Activity existingActivity, Comment newComment) throws AccessDeniedException,
                                                                             ServiceException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Comment getComment(String commentId) throws AccessDeniedException, ServiceException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Comment updateComment(Comment existingComment) throws AccessDeniedException,
                                                       ServiceException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void deleteComment(Comment existingComment) throws AccessDeniedException, ServiceException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Like like(Activity existingActivity, Identity existingIdentity) throws AccessDeniedException,
                                                                        ServiceException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Like getLike(String likeId) throws AccessDeniedException, ServiceException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void unlike(Like existingLike) throws AccessDeniedException, ServiceException {
    // TODO Auto-generated method stub
    
  }

}
