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
package org.exoplatform.social.client.core.util;


import org.exoplatform.social.client.api.event.PropertyChangeEvent;
import org.exoplatform.social.client.api.event.PropertyChangeListener;
import org.exoplatform.social.client.api.model.Model;

/**
 * This is a utility class that can be used by models that support bound
 * properties.  You can use an instance of this class as a member field
 * of your bean and delegate various work to it.
 *
 * 
 * @author thanh_vucong
 *
 */
public final class PropertyChangeSupport {

  /**
   * The source bean for property change events that we will broadcast.
   */
  private Model source = null;
  /**
   * The set of registered PropertyChangeListeners for event notifications.
   */
  private PropertyChangeListener listeners[] = new PropertyChangeListener[0];
  
  /**
   * Lock object for change to listeners
   */
  private final Object listenersLock = new Object();
  
  
  /**
   * Construct a new <code>PropertyChangeSupport</code> object associated with the specified Model object.
   * @param source
   */
  public PropertyChangeSupport(Model source) {
    this.source = source;
  }
  
  /**
   * Add a PropertyChange event listener to this component
   * @param listener The listener is added.
   */
  public void addPropertyChangeListener(PropertyChangeListener listener) {
    
    synchronized(listenersLock) {
      PropertyChangeListener results[] = new PropertyChangeListener[listeners.length + 1];
      for (int i = 0; i < listeners.length; i++) {
        results[i] = listeners[i];
      }
      //Add the PropertyChangeListener to the new position.    
      results[listeners.length] = listener;
      listeners = results;
    }
  }
  
  /**
   * Notify all PropertyChange event listeners that a particular event has 
   * occurred for this Container. The default implementation performs 
   * this notification synchronously using the calling thread.
   * 
   * @param type Event type
   * @param data Event data
   */
  public void propertyChange(String propertyName, Object oldObject, Object newObject) {
    PropertyChangeEvent event = new PropertyChangeEvent(this.source, propertyName, oldObject, newObject);
    PropertyChangeListener interested[] = listeners;
    for (int i = 0; i < interested.length; i++) {
      interested[i].propertyChange(event);
    }
  }
  
  /**
   * Remove a PropertyChange event listener which was registered to model
   * @param listener The listener will be removed.
   */
  public void removeLifecycleListener(PropertyChangeListener listener) {
    synchronized(listenersLock) {
      int n = -1;
      for (int i = 0; i < listeners.length; i++) {
          if (listeners[i] == listener) {
              n = i;
              break;
          }
      }
      //not found any listener in Listeners
      if (n < 0) {
        return;
      }
      
      /**
       * Execute to remove the listener
       */
      PropertyChangeListener results[] = new PropertyChangeListener[listeners.length - 1];
      int j = 0;
      for (int i = 0; i < listeners.length; i++) {
          if (i != n)
              results[j++] = listeners[i];
      }
      listeners = results;
    }
  }
  
  
}
