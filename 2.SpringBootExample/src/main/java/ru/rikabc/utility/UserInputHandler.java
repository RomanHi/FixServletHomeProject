package ru.rikabc.utility;

import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
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

    public static String validateFile(UserFile object) {
        object.setCreateDate(Date.valueOf(LocalDate.now()));
        String fileName = object.getFileName();
        String type = object.getType();
        String file = object.getFile();

        if (fileName == null || "".equals(fileName))
            return "attribute 'fileName' can't be empty";
        if (file == null || "".equals(file))
            return "attribute 'file' can't be empty";
        if (type == null || "".equals(type)) {
            object.setType("txt");
            return "created";
        } else if ("json".equals(type)) {
            try {
                new JSONObject(file);
                return "created";
            } catch (JSONException e) {
                return "invalid json file: " + e.getMessage();
            }
        } else if ("xml".equals(type)) {
            try {
                DefaultHandler handler = new DefaultHandler();
                SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
                InputStream stream = new ByteArrayInputStream(file.getBytes("UTF-8"));
                saxParser.parse(stream, handler);
                return "created";
            } catch (SAXException e) {
                return "invalid xml file: " + e.getMessage();
            } catch (IOException | ParserConfigurationException e) {
                e.printStackTrace();
            }
        } else return "can't accept this type: " + type;

        return "unknown exception";
    }

    public static String validateFile(UserFile object, UserFile objectFromDb) {
        if (objectFromDb == null) {
            return "file not found";
        }
        object.setFileId(objectFromDb.getFileId());
        return validateFile(object);
    }
}
