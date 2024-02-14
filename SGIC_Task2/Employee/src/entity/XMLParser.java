package entity;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLParser {
    public static List<Employee> parseAndFilterEmployees(String filePath) {
        List<Employee> employees = new ArrayList<>();

        try {
            File file = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("employee");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                int id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
                String name = element.getElementsByTagName("name").item(0).getTextContent();
                String position = element.getElementsByTagName("position").item(0).getTextContent();
                String department = element.getElementsByTagName("department").item(0).getTextContent();

                Employee employee = new Employee(id, name, position, department);
                employees.add(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Filter employees by position
        List<Employee> filteredEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getPosition().equals("Quality Engineer")) {
                filteredEmployees.add(employee);
            }
        }

        return filteredEmployees;
    }
}
