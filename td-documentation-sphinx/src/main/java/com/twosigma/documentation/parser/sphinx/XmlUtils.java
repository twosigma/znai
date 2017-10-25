package com.twosigma.documentation.parser.sphinx;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

/**
 * @author mykola
 */
class XmlUtils {
    private XmlUtils() {
    }

    public static Document parseXml(String xml) {
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            builderFactory.setValidating(false);

            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            builder.setEntityResolver((publicId, systemId) -> new InputSource(new StringReader("")));

            Document doc = builder.parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8.name())));
            doc.getDocumentElement().normalize();

            return doc;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
