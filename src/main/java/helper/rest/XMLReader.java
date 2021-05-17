package helper.rest;

import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Map;

public class XMLReader extends FileReader {

    @Override
    public void readFile(String path) throws Exception {
        this.setFile(new File(path));
        this.setDocumentBuilderFactory(DocumentBuilderFactory.newInstance());
        this.setDocumentBuilder(this.getDocumentBuilderFactory().newDocumentBuilder());
        this.setDocument(this.getDocumentBuilder().parse(this.getFile()));
        this.getDocument().getDocumentElement().normalize();
    }

    public NodeList getNodeList(String nodeName) {
        NodeList nList = getDocument().getElementsByTagName(nodeName);
        return nList;
    }

    @Override
    public Map<String, String> getNode(String path, Object json) throws Exception {
        throw new Exception();
    }

    public static void main(String[] args) {
        XMLReader xr = new XMLReader();
        try {
            String[] abc = {"REPLACE 1", "REPLACE 2"};
            xr.readFileAsString("test", abc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
