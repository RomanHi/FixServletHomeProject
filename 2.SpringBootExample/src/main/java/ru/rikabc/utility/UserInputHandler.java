package ru.rikabc.utility;

import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.rikabc.dto.UserInputResult;
import ru.rikabc.models.UserFile;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.time.LocalDate;

/**
 * @Author Roman Khayrullin on 20.04.2018
 * @Version 1.0
 */
public class UserInputHandler {

    public static UserInputResult validateFile(UserFile object) {
        object.setCreateDate(Date.valueOf(LocalDate.now()));
        String fileName = object.getFileName();
        String type = object.getType();
        String file = object.getFile();

        if (fileName == null || "".equals(fileName))
            return new UserInputResult("attribute 'fileName' can't be empty",
                    false);

        if (file == null || "".equals(file))
            return new UserInputResult("attribute 'file' can't be empty",
                    false);

        if (type == null || "".equals(type)) {
            object.setType("txt");
            return new UserInputResult("created like txt file", true);
        } else if ("json".equals(type)) {
            try {
                new JSONObject(file);
                return new UserInputResult("json file created", true);
            } catch (JSONException e) {
                return new UserInputResult("invalid json file: " + e.getMessage(),
                        false);
            }
        } else if ("xml".equals(type)) {
            try {
                DefaultHandler handler = new DefaultHandler();
                SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
                InputStream stream = new ByteArrayInputStream(file.getBytes("UTF-8"));
                saxParser.parse(stream, handler);
                return new UserInputResult("xml file created", true);
            } catch (SAXException e) {
                return new UserInputResult("invalid xml file: " + e.getMessage(),
                        false);
            } catch (IOException | ParserConfigurationException e) {
                e.printStackTrace();
            }
        } else return new UserInputResult("can't accept this type: " + type, false);

        return new UserInputResult("unknown exception", false);
    }

    public static UserInputResult validateFile(UserFile object, UserFile objectFromDb) {
        if (objectFromDb == null) {
            return new UserInputResult("file not found", false);
        }
        object.setFileId(objectFromDb.getFileId());
        return validateFile(object);
    }
}
