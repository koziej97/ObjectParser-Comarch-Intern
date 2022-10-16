package parsing_task.parser;

import parsing_task.model.Child;
import parsing_task.model.ChildParsed;
import parsing_task.model.ObjectToParse;
import parsing_task.model.ParsedObject;

import java.util.ArrayList;
import java.util.List;

public class Parser implements ParserInterface{

    public ParsedObject parseObject(ObjectToParse objectToParse){

        return ParsedObject.builder()
                .attributeName(objectToParse.getAttributeName())
                .id(setId(objectToParse))
                .type(objectToParse.getType())
                .ipAddress(setIpAddress(objectToParse))
                .ipAddressMask(setIpAddressMask(objectToParse))
                .parsedChildList(setChildParsedList(objectToParse))
                .build();
    }
    private String setId(ObjectToParse objectToParse){
        if (objectToParse.getId().isPresent()){
            return objectToParse.getId().get();
        }
        else {
            return objectToParse.getUuid().get();
        }
    }

    private String setIpAddress(ObjectToParse objectToParse){
        if (objectToParse.getIpAddressV4().isPresent()){
            return objectToParse.getIpAddressV4().get();
        }
        else {
            return objectToParse.getIpAddressV6().get();
        }
    }

    private int setIpAddressMask(ObjectToParse objectToParse){
        if (objectToParse.getIpAddressV4Mask().isPresent()){
            return objectToParse.getIpAddressV4Mask().getAsInt();
        }
        else {
            return objectToParse.getIpAddressV6Mask().getAsInt();
        }
    }

    private List<ChildParsed> setChildParsedList(ObjectToParse objectToParse) {
        List <ChildParsed> childParsedList = new ArrayList<>();
        for (Child child : objectToParse.getChildTypes()) {
            ChildParsed childParsed = parseChild(child, objectToParse);
            childParsedList.add(childParsed);
        }
        return childParsedList;
    }


    private ChildParsed parseChild(Child child, ObjectToParse objectToParse){
        return ChildParsed.builder()
                .attributeName(child.getAttributeName())
                .type(child.getType())
                .parentName(objectToParse.getName())
                .name(child.getName())
                .build();
    }
}
