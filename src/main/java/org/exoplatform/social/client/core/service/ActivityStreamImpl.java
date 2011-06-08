package org.exoplatform.social.client.core.service;

import org.exoplatform.social.client.api.auth.AccessDeniedException;
import org.exoplatform.social.client.api.model.Activity;
import org.exoplatform.social.client.api.service.ActivityService;
import org.exoplatform.social.client.api.service.Service;
import org.exoplatform.social.client.api.service.ServiceException;

public class ActivityStreamImpl extends AbstractLifecycle<Activity, ActivityService<Activity>> implements Service<Activity> {

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


 

  

  

}
