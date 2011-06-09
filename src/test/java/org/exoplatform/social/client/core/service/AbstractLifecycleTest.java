package org.exoplatform.social.client.core.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.exoplatform.social.client.api.event.CRUDLifecycleEvent;
import org.exoplatform.social.client.api.event.CRUDLifecycleListener;
import org.exoplatform.social.client.api.event.Lifecycle;
import org.exoplatform.social.client.api.event.LifecycleEvent;
import org.exoplatform.social.client.api.event.LifecycleListener;
import org.exoplatform.social.client.api.event.PropertyChangeEvent;
import org.exoplatform.social.client.api.event.PropertyChangeListener;
import org.exoplatform.social.client.api.model.Model;
import org.junit.After;
import org.junit.Before;

public abstract class AbstractLifecycleTest {

  protected Model mockModel = null;
  protected AbstractLifecycle<MockModel, MockService> mockService = null;
  protected MockLifecycleListener captureLifecycle = null;
  protected MockCRUDLifecycleListener captureCRUDLifecycle = null;
  protected MockPropertyChangeListener capturePropertyChange = null;
  
  @Before
  public void setUp() throws Exception {
  }
  
  @After
  public void tearDown() throws Exception {
    captureLifecycle = null;
    captureCRUDLifecycle = null;
    mockService = null;
  }
  
  /**
   * Define the MockEvent class
   * @author thanh_vucong
   *
   */
  public class MockLifecycEvent extends LifecycleEvent<MockModel, MockService> {

    public MockLifecycEvent(Lifecycle<MockModel, MockService> source, String type) {
      super(source, type);

    }
  }
  
  public class MockLifecycleListener implements LifecycleListener<MockModel, MockService> {

    Collection<String> events = new ArrayList<String>();
    @Override
    public void broadcast(LifecycleEvent<MockModel, MockService> event) {
      recordEvent(event);
    }
    /**
     * Verifying the event type which has been raised to this listener.
     * @param eventName
     * @return
     */
    public boolean hasEvent(String eventName) {
      return events.contains(eventName);
    }
    
    /**
     * Support to record the event which raise to this listener.
     * @param event
     */
    private void recordEvent(LifecycleEvent<MockModel, MockService> event) {
      events.add(event.getType());
    }
  }
  
  /**
   * Define the MockCRUDListener which uses for UnitTestCase.
   * @author thanh_vucong
   *
   */
  public class MockCRUDLifecycleListener implements CRUDLifecycleListener<MockModel> {

    Collection<String> events = new ArrayList<String>();
    @Override
    public void broadcast(CRUDLifecycleEvent<MockModel> event) {
      recordEvent(event);
      
    }
    /**
     * Verifying the event type which has been raised to this listener.
     * @param eventName
     * @return
     */
    public boolean hasEvent(String eventName) {
      return events.contains(eventName);
    }
    
    /**
     * Support to record the event which raise to this listener.
     * @param event
     */
    private void recordEvent(CRUDLifecycleEvent<MockModel> event) {
      events.add(event.getType());
    }
  }
  
  public class MockPropertyChangeListener implements PropertyChangeListener {

    Map<String, PropertyChangeEvent> eventHolder = new HashMap<String, PropertyChangeEvent>(); 
    @Override
    public void propertyChange(PropertyChangeEvent event) {
      recordEvent(event);
    }
    
    /**
     * Verifying the event type which has been raised to this listener.
     * @param eventName
     * @return
     */
    public boolean hasEvent(String propertyName) {
      return eventHolder.containsKey(propertyName);
    }
    
    /**
     * Support to record the event which raise to this listener.
     * @param event
     */
    private void recordEvent(PropertyChangeEvent event) {
      eventHolder.put(event.getPropertyName(), event);
    }
    
  }
  
}
