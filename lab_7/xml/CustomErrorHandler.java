package lab_7.xml;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import static java.lang.System.exit;

class CustomErrorHandler implements ErrorHandler {
    // метод для обробки попереджень
    @Override
    public void warning(SAXParseException e) throws SAXException {
        System.out.println("Рядок " + e.getLineNumber() + ":");
        System.out.println(e.getMessage());
        exit(-1);
    }
    // метод для обробки помилок
    @Override
    public void error(SAXParseException e) throws SAXException {
        System.out.println("Рядок " + e.getLineNumber() + ":");
        System.out.println(e.getMessage());
        exit(-1);
    }
    // метод для обробки критичних помилок
    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        System.out.println("Рядок " + e.getLineNumber() + ":");
        System.out.println(e.getMessage());
        exit(-1);
    }
}
