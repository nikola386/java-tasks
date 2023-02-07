import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.List;

public class SimpleObjectSerializer {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException {
        Student s = new Student(1, "Student 1", "Some address 123, Sofia, BG");
        SimpleObjectSerializer serializer = new SimpleObjectSerializer();
        serializer.storeToXML(s, "./out.xml");
    }

    public void storeToXML(Object obj, String outputDir) throws ParserConfigurationException, TransformerException {
        Class c = obj.getClass();
        String name = c.getName();
        Field[] publicFields = c.getFields();

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement(name);
        doc.appendChild(rootElement);

        List.of(publicFields).forEach((f) -> {
            Element el = doc.createElement(f.getName());
            try {
                el.setTextContent(f.get(obj).toString());
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            rootElement.appendChild(el);
        });

        try (FileOutputStream output = new FileOutputStream(outputDir)) {
            writeXml(doc, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeXml(Document doc, OutputStream output) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);
    }
}