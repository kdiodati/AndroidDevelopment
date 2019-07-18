package com.gmail.kdiodati.tideappv1;

//Written by Brian Bird July 2015, updated July 2017
//Updated by Kyle Diodati July 17 2019
//Only changed filename and weatherItems to tideItems

import android.content.Context;
import android.util.Log;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class FileIO {

    //private final String FILENAME = "tide.xml";
    private final String FILENAME= "tide30day.xml";
    private Context context = null;

    public FileIO (Context context) {
        this.context = context;
    }

    public TideItems readFile() {
        try {
            // get the XML reader
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader xmlreader = parser.getXMLReader();

            // set content handler
            ParseHandler handler = new ParseHandler();
            xmlreader.setContentHandler(handler);

            // read the file from internal storage
            InputStream in = context.getAssets().open(FILENAME);

            // parse the data
            InputSource is = new InputSource(in);
            xmlreader.parse(is);

            // set the feed in the activity
            TideItems items = handler.getItems();
            return items;
        }
        catch (Exception e) {
            Log.e("News reader", e.toString());
            return null;
        }
    }
}