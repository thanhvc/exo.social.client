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
package org.exoplatform.social.client.api.event;


/**
 * 
 * General event for notifying listeners of significant changes on a service
 * that implements the CRUDLifecycle interface.
 * @author thanh_vucong
 *
 * @param <M> The object which implements Model interface
 */
public final class CRUDLifecycleEvent<M> extends Event {

  /**
   * The event type this instance represents.
   */
  private String type = null;
  /**
   * The event data associated with this event.
   */
  private M data = null;
  /**
   * The CRUDLifecycle on which this event occurred.
   */
  private CRUDLifecycle<M> source = null;
  
  /**
   * Construct a new CRUDLifecycleEvent with the specified parameters.
   * 
   * @param source Component on which this event occurred
   * @param type Event type (required)
   */
  public CRUDLifecycleEvent(CRUDLifecycle<M> source, String type) {
   this(source, type, null);
  }
  
  /**
   * Construct a new LifecycleEvent with the specified parameters.
   * 
   * @param source Component on which this event occurred
   * @param type Event type (required)
   * @param data Event data (if any)
   */
  public CRUDLifecycleEvent(CRUDLifecycle<M> source, String type, M model) {
    this.source = source;
    this.type = type;
    this.data = model;
  }
  
  /**
   * Return the event type of this event.
   * @return
   */
  public String getType() {
    return type;
  }

  /**
   * Return the event data of this event.
   * @return
   */
  public M getData() {
    return data;
  }

  /**
   * Return the CRUDLifecycle on which this event occurred.
   * @return
   */
  public CRUDLifecycle<M> getSource() {
    return source;
  }
}
