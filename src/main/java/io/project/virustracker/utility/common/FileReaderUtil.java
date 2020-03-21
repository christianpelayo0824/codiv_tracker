package io.project.virustracker.utility.common;


import com.fasterxml.jackson.core.JsonFactory;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
public class FileReaderUtil {

    ResourceLoader resourceLoader;

    @Autowired
    public FileReaderUtil(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    /**
     * Utility function for retrieving the file provided with <br>
     * path as the parameter
     *
     * @param path - path of the file
     * @return File
     */
    public Object readJSONValueByKey(String path, String key) {
        try {
            Resource resource = resourceLoader.getResource("classpath:" + path);
            InputStream is = resource.getInputStream();
            byte[] bData = FileCopyUtils.copyToByteArray(is);
            String data = new String(bData, StandardCharsets.UTF_8);
            JsonParser jsonParser = JsonParserFactory.getJsonParser();
            Map<String, Object> map = jsonParser.parseMap(data);
            return map.get(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
