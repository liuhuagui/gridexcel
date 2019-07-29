package com.github.liuhuagui.gridexcel.eventmodel;

import org.apache.poi.ooxml.util.SAXHelper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFReader.SheetIterator;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.SheetContentsHandler;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/**
 * A XLSX -> Bean processor, The advantage provided is that you can read an XLSX with a relatively small memory footprint.
 * Basing on POI examples. https://poi.apache.org/components/spreadsheet/how-to.html
 * @author KaiKang
 */
public class XLSXEventModel extends EventModel{
	private static Logger log = LoggerFactory.getLogger(XLSXEventModel.class);
	private OPCPackage pkg;
	private XSSFReader reader;
	/**
	 * Creates a new XLSX -> Bean converter
	 * @param pkg Represents a container that can store multiple data objects.
	 * @param startRow the starting line number as a condition on enabling consumer function, 0-based
	 * @param readConsumer Each time a row of data is read, the function interface is called to execute the custom logic.
	 * @throws OpenXML4JException 
	 * @throws IOException 
	 */
	public XLSXEventModel(OPCPackage pkg, int startRow, Consumer<List<String>> readConsumer) throws IOException, OpenXML4JException{
		super(startRow,readConsumer);
		init(pkg);
	}
	
	/**init relevant information*/
	public void init(OPCPackage pkg) throws IOException, OpenXML4JException{
		this.pkg = pkg;
		this.reader = new XSSFReader(pkg);
	}

	/**
	 * Creates a new XLSX -> Bean converter
	 * @param stream the input stream of .xlsx file.
	 * @param startRow the starting line number as a condition on enabling consumer function, 0-based
	 * @param readConsumer Each time a row of data is read, the function interface is called to execute the custom logic.
	 * @throws IOException 
	 * @throws OpenXML4JException 
	 */
	public XLSXEventModel(InputStream stream,int startRow,Consumer<List<String>> readConsumer) throws OpenXML4JException, IOException{
		this(OPCPackage.open(stream),startRow,readConsumer);
	}
	
	/**
	 * Creates a new XLSX -> Bean converter，default <code>startRow=0</code>
	 * @param stream the input stream of .xlsx file.
	 * @param readConsumer Each time a row of data is read, the function interface is called to execute the custom logic.
	 * @throws OpenXML4JException 
	 * @throws IOException 
	 */
	public XLSXEventModel(InputStream stream,Consumer<List<String>> readConsumer) throws OpenXML4JException, IOException{
		this(stream,0,readConsumer);
	}
	
	/**
	 * Creates a new XLSX -> Bean converter，default <code>startRow=0</code>
	 * @param stream the input stream of .xlsx file.
	 * @throws OpenXML4JException 
	 * @throws IOException 
	 */
	public XLSXEventModel(InputStream stream) throws OpenXML4JException, IOException{
		this(stream,null);
	}

    /**
     * Processing input stream of all sheets.
     * @throws Exception
     */
    private void processAllSheets() throws SAXException, InvalidFormatException, ParserConfigurationException, IOException {
        XMLReader parser = createSheetParser();
        Iterator<InputStream> sheets = reader.getSheetsData();
        while (sheets.hasNext()) {
        	InputStream sheet = sheets.next();
            log.info("Processing new sheet: {}\n",((SheetIterator)sheets).getSheetName());
            InputSource sheetSource = new InputSource(sheet);
            parser.parse(sheetSource);
            sheet.close();
        }
    }

    /**
     * Create a Parser that parses sheet.
     * @return
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws InvalidFormatException
     * @throws IOException
     */
    protected XMLReader createSheetParser() throws SAXException, ParserConfigurationException, InvalidFormatException, IOException{
        XMLReader parser = SAXHelper.newXMLReader();
        ContentHandler handler = createSheetXMLHandler();
        parser.setContentHandler(handler);
        return parser;
    }

	/**
	 * Create a Handler that handles xml.
	 * @return
	 * @throws IOException
	 * @throws SAXException
	 * @throws InvalidFormatException
	 */
	protected XSSFSheetXMLHandler createSheetXMLHandler() throws IOException, SAXException, InvalidFormatException{
		ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(this.pkg);
		DataFormatter formatter = new DataFormatter();
		XSSFSheetXMLHandler handler = new XSSFSheetXMLHandler(
                reader.getStylesTable(), null, strings, createSheetContentsHandler(), formatter, false);
		return handler;
	}
	
	/**
	 * Create a Handler that handles contents of sheet.
	 * @return
	 */
	protected SheetContentsHandler createSheetContentsHandler() {
        return new SheetContentsHandler() {
            @Override
            public void startRow(int rowNum) {
            }
            @Override
            public void endRow(int rowNum) {
            	// End the row
            	//If the current line number≥ startRow, then the consumer function is enabled, 
            	//otherwise the entire line record is directly discarded and is not processed.
    			if(rowNum >=startRow){
    				readConsumer.accept(rowCellValues);
    			}
    			// init new row
    			rowCellValues = new ArrayList<String>();
            }
			@Override
			public void cell(String cellReference, String formattedValue, XSSFComment comment) {
				rowCellValues.add(formattedValue);
			}
        };
    }
	
	@Override
    public void process() throws SAXException, InvalidFormatException, ParserConfigurationException, IOException {
       processAllSheets();
    }
}