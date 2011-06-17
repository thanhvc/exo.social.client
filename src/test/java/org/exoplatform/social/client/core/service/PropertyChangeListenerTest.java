package org.exoplatform.social.client.core.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

/**
 * Unit Test for {@link org.exoplatform.social.client.api.event.PropertyChangeListener}.
 */
public class PropertyChangeListenerTest extends AbstractLifecycleTest {

  @Before
  public void setUp() throws Exception {
    super.setUp();
    capturePropertyChange = new MockPropertyChangeListener();
    mockModel = new MockModel();
    mockModel.addPropertyChangeListener(capturePropertyChange);
  }
  
  @After
  public void tearDown() throws Exception {
    super.tearDown();
    capturePropertyChange = null;
    mockModel = null;
    
  }
  
  @Test
  public void checkPropertyChangeListener() throws Exception {
    Assert.assertEquals(1, mockModel.findPropertyChangeListeners().length);
  }
  
  @Test
  public void handlePropertyChangeListener() throws Exception {
    mockModel.setField(MockModel.Field.ID.toString(), "newValue");
    Assert.assertTrue(capturePropertyChange.eventHolder.containsKey(MockModel.Field.ID.toString()));
    Assert.assertEquals(MockModel.Field.ID.toString(),
                        capturePropertyChange.eventHolder.get(MockModel.Field.ID.toString()).getPropertyName());
    Assert.assertEquals("newValue", capturePropertyChange.eventHolder.get(MockModel.Field.ID.toString()).getNewValue());
    Assert.assertNull(capturePropertyChange.eventHolder.get(MockModel.Field.ID.toString()).getOldValue());
  }
  
  @Test
  public void checkOldValue() throws Exception {
    mockModel.setField(MockModel.Field.ID.toString(), "oldValue");
    mockModel.setField(MockModel.Field.ID.toString(), "newValue");
    
    Assert.assertEquals(1, capturePropertyChange.eventHolder.size());
    Assert.assertTrue(capturePropertyChange.eventHolder.containsKey(MockModel.Field.ID.toString()));
    Assert.assertEquals(MockModel.Field.ID.toString(),
                        capturePropertyChange.eventHolder.get(MockModel.Field.ID.toString()).getPropertyName());
    Assert.assertEquals("newValue", capturePropertyChange.eventHolder.get(MockModel.Field.ID.toString()).getNewValue());
    Assert.assertEquals("oldValue", capturePropertyChange.eventHolder.get(MockModel.Field.ID.toString()).getOldValue());
  }
  
  @Test
  public void checkRaiseEventCountSameField() throws Exception {
    mockModel.setField(MockModel.Field.ID.toString(), "oldValue");
    mockModel.setField(MockModel.Field.ID.toString(), "newValue");
    Assert.assertEquals(1, capturePropertyChange.eventHolder.size());
  }
  @Test
  public void checkRaiseEventCountNotSameField() throws Exception {
    mockModel.setField(MockModel.Field.ID.toString(), "oldValue");
    mockModel.setField(MockModel.Field.CONTENT.toString(), "oldContentValue");
    
    Assert.assertEquals(2, capturePropertyChange.eventHolder.size());
   
  }
  
  
  
  
}
