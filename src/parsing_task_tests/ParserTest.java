package parsing_task_tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parsing_task.*;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class ParserTest {

    private static class ObjectToParseAttr{
        private static final String attributeName = "attribute_value";
        private static final String id = "123";
        private static final String type = "LINK";
        private static final String ipAddressV4 = "192.212.210.123";
        private static final int ipAddressV4Mask = 22;
        private static final String name = "ObjectToParse";
    }

    private static class ChildAttr {
        private static final String attributeName = "attribute_value_child";
        private static final String type = "PATH";
        private static final String name = "Test_child";
    }

    private static class ExpectedParsedObject {
        private static final String attributeName = "attribute_value";
        private static final String id = "123";
        private static final String type = "Link";
        private static final String ipAddress = "192.212.210.123";
        private static final int ipAddressMask = 22;
    }

    private static class ExpectedParsedChild {
        private static final String attributeName = "attribute_value_child";
        private static final String type = "PathChild";
        private static final String parentName = "ObjectToParse";
        private static final String name = "Test_child";
    }

    private ParsedObject parsedObject;

    @Before
    public void setup(){
        List<Child> childList = List.of(
                Child.builder(ChildAttr.name)
                        .attributeName(ChildAttr.attributeName)
                        .type(ChildType.valueOf(ChildAttr.type))
                        .build()
        );

        ObjectToParse objectToParse = ObjectToParse.builder(ObjectToParseAttr.name)
                .attributeName(ObjectToParseAttr.attributeName)
                .id(Optional.of(ObjectToParseAttr.id))
                .type(Type.valueOf(ObjectToParseAttr.type))
                .ipAddressV4(Optional.of(ObjectToParseAttr.ipAddressV4))
                .ipAddressV4Mask(OptionalInt.of(ObjectToParseAttr.ipAddressV4Mask))
                .childTypes(childList)
                .build();

        Parser parser = new Parser();
        parsedObject = parser.parseObject(objectToParse);
    }

    @Test
    public void isParsedObjectCorrect(){
        Assert.assertEquals(parsedObject.getAttributeName(), ExpectedParsedObject.attributeName);
        Assert.assertEquals(parsedObject.getId(), ExpectedParsedObject.id);
        Assert.assertEquals(parsedObject.getType().getDisplayType(), ExpectedParsedObject.type);
        Assert.assertEquals(parsedObject.getIpAddress(), ExpectedParsedObject.ipAddress);
        Assert.assertEquals(parsedObject.getIpAddressMask(), ExpectedParsedObject.ipAddressMask);
    }

    @Test
    public void isParsedChildCorrect() {
        ChildParsed childParsed = parsedObject.getParsedChildList().get(0);
        Assert.assertEquals(childParsed.getAttributeName(), ExpectedParsedChild.attributeName);
        Assert.assertEquals(childParsed.getName(), ExpectedParsedChild.name);
        Assert.assertEquals(childParsed.getType().getDisplayType(), ExpectedParsedChild.type);
        Assert.assertEquals(childParsed.getParentName(), ExpectedParsedChild.parentName);
    }
}
