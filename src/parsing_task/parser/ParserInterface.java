package parsing_task.parser;

import parsing_task.model.ObjectToParse;
import parsing_task.model.ParsedObject;

public interface ParserInterface {
     ParsedObject parseObject(ObjectToParse objectToParse);
}
