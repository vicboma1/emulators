package framework.container;

import emulator.framework.container.ClassToMapKey;
import junit.framework.TestCase;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class ClassToMapKeyTest extends TestCase {

    private String id = "id";
    private Class<String> classString;

    private ClassToMapKey<String> classToMapKey;

    public void setUp() throws Exception {
        super.setUp();
        classToMapKey = ClassToMapKey.Create(id,classString);
    }

    public void tearDown() throws Exception {
        classToMapKey = null;
    }

    public void testCreate() throws Exception {
        final ClassToMapKey<String> _classToMapKey = ClassToMapKey.Create(id, classString);
        assertNotSame("Not same testCreate",_classToMapKey, classToMapKey);
    }

    public void testId() throws Exception {
        final ClassToMapKey<String> _classToMapKey = ClassToMapKey.Create(id, classString);
        final ClassToMapKey<String> classToMapKeySpy =  spy(_classToMapKey);
        when(classToMapKeySpy.id()).thenReturn(id);
        assertEquals("Not same id",classToMapKeySpy.id(), id);
    }

    public void testType() throws Exception {
        final ClassToMapKey<String> _classToMapKey = ClassToMapKey.Create(id, classString);
        assertEquals("Not same id",_classToMapKey.type(), classString);
    }
}