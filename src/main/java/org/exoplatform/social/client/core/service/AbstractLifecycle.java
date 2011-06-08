/*
 * Copyright (C) 2003-2011 eXo Platform SAS.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see<http://www.gnu.org/licenses/>.
 */
package org.exoplatform.social.client.core.service;

import org.exoplatform.social.client.api.auth.AccessDeniedException;
import org.exoplatform.social.client.api.event.CRUDLifecycle;
import org.exoplatform.social.client.api.event.CRUDLifecycleListener;
import org.exoplatform.social.client.api.event.Lifecycle;
import org.exoplatform.social.client.api.event.LifecycleException;
import org.exoplatform.social.client.api.event.LifecycleListener;
import org.exoplatform.social.client.api.service.Service;
import org.exoplatform.social.client.api.service.ServiceException;
import org.exoplatform.social.client.core.util.CRUDLifecycleSupport;
import org.exoplatform.social.client.core.util.LifecycleSupport;

/**
 * /**
 * Abstract implementation of the <b>Lifecycle</b> interface, providing common 
 * functionality required when Component execution
 * 
 * @author thanh_vucong
 *
 * @param <M> The object which implements Model interface
 * @param <S> The object which implements Service interface
 */
public abstract class AbstractLifecycle<M, S> implements Service<M>, CRUDLifecycle<M>, Lifecycle<M,S> {

  /**
   * Has this component been started?
   */
  protected boolean started = false;
  
  /**
   * The lifecycle event support for this component.
   */
  protected LifecycleSupport<M, S> lifecycle = new LifecycleSupport<M, S>(this);
  
  /**
   * The crud lifecycle event support for this component.
   */
  protected CRUDLifecycleSupport<M> crudLifecycle = new CRUDLifecycleSupport<M>(this);
  
  
  /**
   * Prepare for active use of the public methods of this Component.
   * @exception LifecycleException if this component detects a fatal error
   *  that prevents it from being started
   */
  public void start() throws LifecycleException {
   
    if (started) {
      return;
    }
    // Notify our interested LifecycleListeners
    lifecycle.broadcastEvent(BEFORE_START_EVENT, null);

    started = true;
    
    //// Notify our interested LifecycleListeners
    lifecycle.broadcastEvent(START_EVENT, null);
    
    //Notify our interested LifecycleListeners
    lifecycle.broadcastEvent(AFTER_START_EVENT, null);
  }
  
  /**
   * Gracefully shut down active use of the public methods of this Component.
   * @exception LifecycleException if this component detects a fatal error
   *  that needs to be reported
   */
  public void stop() throws LifecycleException {
    // Validate and update our current component state
    if (!started) {
      return;
    }
    
    // Notify our interested LifecycleListeners
    lifecycle.broadcastEvent(BEFORE_STOP_EVENT, null);
    
    //Notify our interested LifecycleListeners
    lifecycle.broadcastEvent(STOP_EVENT, null);
    started = false;
    
    // Notify our interested LifecycleListeners
    lifecycle.broadcastEvent(AFTER_STOP_EVENT, null);

  }
  
  /**
   * Add a lifecycle event listener to this component
   * 
   * @param listener The listener is added
   */
  public final void addLifecycleListener(LifecycleListener<M,S> listener) {
    lifecycle.addLifecycleListener(listener);
  }
  
  /**
   * Remove a lifecycle event listener which was added to this component.
   * @param listener The listener will be removed.
   */
  public void removeLifecycleListener(LifecycleListener<M,S> listener) {
    lifecycle.removeLifecycleListener(listener);
  }
  
  /**
   * Add a crud lifecycle event listener to this component
   * 
   * @param listener The listener is added
   */
  public void addCRUDLifecycleListener(CRUDLifecycleListener<M> listener) {
    crudLifecycle.addCRUDLifecycleListener(listener);
  }
  
  /**
   * Remove a crud lifecycle event listener which was added to this component.
   * @param listener The listener will be removed.
   */
  public void removeCRUDLifecycleListener(CRUDLifecycleListener<M> listener) {
    crudLifecycle.removeCRUDLifecycleListener(listener);
  }
  
  /**
   * This implementation performs the corresponding action for CRUD operation.
   * @param newInstance A new instance
   * @throws AccessDeniedException
   * @throws ServiceException
   */
  public final void doCreate(M newInstance) throws AccessDeniedException, ServiceException {
    preCreate(newInstance);
    create(newInstance);
    postCreate(newInstance);
  }
  
 /**
  * Override this method to perform pre-processing on new model being saved.
  * @param newInstance A new instance
  * @throws AccessDeniedException
  * @throws ServiceException
  */
  public void preCreate(M newInstance) throws AccessDeniedException, ServiceException {
    crudLifecycle.broadcastEvent(BEFORE_CREATE_EVENT, newInstance);
  }
  
  
  /**
   * Override this method to perform post-processing on new model being saved.
   * @param newInstance A new instance
   * @throws AccessDeniedException
   * @throws ServiceException
   */
  public void postCreate(M newInstance) throws AccessDeniedException, ServiceException {
    crudLifecycle.broadcastEvent(AFTER_CREATE_EVENT, newInstance);
  }
  
  /**
   * This implementation performs the corresponding action for CRUD operation.
   * 
   * @param existingInstance An existing instance
   * @throws AccessDeniedException
   * @throws ServiceException
   */
  public final void doDelete(M existingInstance) throws AccessDeniedException, ServiceException {
    preDelete(existingInstance);
    delete(existingInstance);
    postDelete(existingInstance);
  }
  
  /**
   * Override this method to perform pre-processing on existing model being deleted.
   * @param existingInstance An existing instance
   * @throws AccessDeniedException
   * @throws ServiceException
   */
  public void preDelete(M existingInstance) throws AccessDeniedException, ServiceException {
    crudLifecycle.broadcastEvent(BEFORE_DELETE_EVENT, existingInstance);
  }
  
  /**
   * Override this method to perform post-processing on existing model being deleted.
   * @param existingInstance An existing instance
   * @throws AccessDeniedException
   * @throws ServiceException
   */
  public void postDelete(M existingInstance) throws AccessDeniedException, ServiceException {
    crudLifecycle.broadcastEvent(AFTER_DELETE_EVENT, existingInstance);
  }
  
  /**
   * This implementation performs the corresponding action for CRUD operation.
   * 
   * @param existingInstance An existing instance
   * @throws AccessDeniedException
   * @throws ServiceException
   */
  public final void doUpdate(M existingInstance) throws AccessDeniedException, ServiceException {
    preUpdate(existingInstance);
    update(existingInstance);
    postUpdate(existingInstance);
  }
  
  /**
   * Override this method to perform pre-processing on existing model being updated.
   * @param existingInstance An existing instance
   * @throws AccessDeniedException
   * @throws ServiceException
   */
  public void preUpdate(M existingInstance) throws AccessDeniedException, ServiceException {
    crudLifecycle.broadcastEvent(BEFORE_UPDATE_EVENT, existingInstance);
  }
  
  /**
   * Override this method to perform post-processing on existing model being updated.
   * @param existingInstance An existing instance
   * @throws AccessDeniedException
   * @throws ServiceException
   */
  public void postUpdate(M existingInstance) throws AccessDeniedException, ServiceException {
    crudLifecycle.broadcastEvent(AFTER_UPDATE_EVENT, existingInstance);
  }
}
