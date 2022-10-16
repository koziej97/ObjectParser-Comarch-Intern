package parsing_task;

import parsing_task.model.*;
import parsing_task.parser.Parser;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class Main {
    public static void main(String[] args) {

        List<Child> childList = Arrays.asList(
                Child.builder("dziecko_1")
                        .attributeName("Attribute Value")
                        .type(ChildType.ETHERNET)
                        .build(),
                Child.builder("dziecko_2")
                        .attributeName("value")
                        .type(ChildType.LINK)
                        .build(),
                Child.builder("dziecko_3")
                        .type(ChildType.PATH)
                        .build()
        );

        Parser parser = new Parser();

        // OBJECT_TO_PARSE_1
        ObjectToParse objectToParse_1 = ObjectToParse.builder("TEST")
                .attributeName("attribute value")
                .id(Optional.of("123"))
                .type(Type.LINK)
                .ipAddressV4(Optional.of("192.212.210.123"))
                .ipAddressV4Mask(OptionalInt.of(22))
                .childTypes(childList)
                .build();

        ParsedObject parsedObject_1 = parser.parseObject(objectToParse_1);

        System.out.println(parsedObject_1.displayInfo());

        // OBJECT_TO_PARSE_2
        ObjectToParse objectToParse_2 = ObjectToParse.builder("TEST_2")
                .attributeName("Attribute Value")
                .type(Type.PATH)
                .ipAddressV6(Optional.of("192.212.210.123"))
                .ipAddressV6Mask(OptionalInt.of(46))
                .childTypes(childList)
                .build();

        ParsedObject parsedObject_2 = parser.parseObject(objectToParse_2);

        System.out.println(parsedObject_2.displayInfo());
    }
}