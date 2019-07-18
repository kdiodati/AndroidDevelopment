package com.gmail.kdiodati.tideappv1;

//Written by Kyle Diodati July 17 2019

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

public class ParseHandler extends DefaultHandler {
    private TideItems tideItems;
    private TideItem item;

    String elementValue = null;
    Boolean elementOn = false;

    public TideItems getItems() {
        return tideItems;
    }

    @Override
    public void startDocument() throws SAXException {
        tideItems = new TideItems(); //start a new arrayList of tideItems
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        elementOn = true;
        if (localName.equals("date")) { //when date tag is opened
            item = new TideItem(); //start a new
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        elementOn = false;
        if (localName.equalsIgnoreCase("date")) { //when date tag is closed
            item.setDate(elementValue);
        }
        else if (localName.equalsIgnoreCase("day")) { //when day tag is closed
            item.setDay(elementValue);
        }
        else if (localName.equalsIgnoreCase("time")) { //when time tag is closed
            item.setTime(elementValue);
        }
        else if (localName.equalsIgnoreCase("pred")) { //when prediciton tag is closed
            item.setPred(elementValue);
        }
        else if (localName.equalsIgnoreCase("highLow")) { //when highLow tag is closed
            item.setHighLow(elementValue);
            item.setHighLowTime();
            tideItems.add(item);//since is last item add to arrayList
        }
    }

    //allows for parsing of value of xml tag
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (elementOn) {
            elementValue = new String(ch, start, length);
            elementOn = false;
        }
    }
}

