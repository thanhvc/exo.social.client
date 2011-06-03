package org.exoplatform.social.client.api.event;

import org.exoplatform.core.LifecycleException;
import org.exoplatform.core.LifecycleListener;

public interface Lifecycle {

  /**
   * The LifecycleEvent type for the "component start" event.
   */
  public static final String START_EVENT = "start";

  /**
   * The LifecycleEvent type for the "component before start"
   */
  public static final String BEFORE_START_EVENT = "before_start";

  /**
   * The LifecycleEvent type for the "component after start"
   */
  public static final String AFTER_START_EVENT = "after_start";

  public static final String STOP_EVENT = "stop";

  public static final String BEFORE_STOP_EVENT = "before_stop";
  public static final String AFTER_STOP_EVENT = "after_stop";

  public static final String DESTROY_EVENT = "destroy";
  /**
   * Get the lifecycle listeners associated with this lifecycle. If this
   * Lifecycle has no listeners registered, a zero-length array is returned.
   */
  public LifecycleListener[] findLifecycleListeners();


  /**
   * Remove a LifecycleEvent listener from this component.
   *
   * @param listener The listener to remove
   */
  public void removeLifecycleListener(LifecycleListener listener);


  /**
   * Prepare for the beginning of active use of the public methods of this
   * component.  This method should be called before any of the public
   * methods of this component are utilized.  It should also send a
   * LifecycleEvent of type START_EVENT to any registered listeners.
   *
   * @throws LifecycleException if this component detects a fatal error
   *                            that prevents this component from being used
   */
  public void start() throws LifecycleException;


  /**
   * Gracefully terminate the active use of the public methods of this
   * component.  This method should be the last one called on a given
   * instance of this component.  It should also send a LifecycleEvent
   * of type STOP_EVENT to any registered listeners.
   *
   * @throws LifecycleException if this component detects a fatal error
   *                            that needs to be reported
   */
  public void stop() throws LifecycleException;
  
}
