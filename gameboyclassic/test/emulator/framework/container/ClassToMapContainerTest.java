package emulator.framework.container;

import junit.framework.TestCase;

import java.util.stream.IntStream;

import static org.mockito.Mockito.mock;


public class ClassToMapContainerTest extends TestCase {

    private ClassToMapContainer classToMapContainer;

    public void setUp() throws Exception {
        super.setUp();
        classToMapContainer = new ClassToMapContainer();
    }

    public void tearDown() throws Exception {
        classToMapContainer = null;
    }

    public void testPut() throws Exception {
        ClassToMapKey<String> mockClassToMapKey = mock(ClassToMapKey.class);
        String mockObject = "";
        final Object put = classToMapContainer.put(mockClassToMapKey, mockObject);
        assertNull("Not same Object", put);
    }

    public void testPutAndGet() throws Exception {
        ClassToMapKey<String> classToMapKey = ClassToMapKey.Create("class",String.class);
        String obj = "";
        classToMapContainer.put(classToMapKey, obj);
        final String result = classToMapContainer.get(classToMapKey);
        assertSame("Not same result",obj,result);
    }

    public void testGet() throws Exception {
        IntStream.range(0, 100).forEach(
                x ->
                {
                    ClassToMapKey<String> classToMapKey = ClassToMapKey.Create("" + x, String.class);
                    final String value = "object number " + x;
                    classToMapContainer.put(classToMapKey, value);
                    final String get = classToMapContainer.get(classToMapKey);

                    assertSame("Not same Object", value, get);
                });
    }
}