package com.icss.base.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.icss.j2ee.util.URLDecoder;

public class DBConfig {
	private static String PFILE=
		Thread.currentThread().getContextClassLoader()
		.getResource("db_config.xml").getPath();
	
	private File mFile=null;
	private long mLastModifyTime=0;
	
    public static final String CONFIG_KEY = "defaultdb";

	private static DBConfig instance = new DBConfig();

	private Map configMap=null;

	private Document doc;
	private Element element;
	private Element childElement;
	private List elements;
	private List childElements;

	private DBConfig(){
		//记录文件的时间
		try {
			PFILE  = URLDecoder.decode(PFILE ,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		mFile=new File(PFILE);
		mLastModifyTime=mFile.lastModified();
		if(mLastModifyTime==0){
			System.err.println(PFILE+" 不存在！");
		}	
		//加载配置文件中的所有参数
		loadConfig();
	}

    public static DBConfig getInstance() {
        return instance;
    }
    
    public Map loadConfig(){
    	configMap=new HashMap();
    	String dbName="";
    	try {
			doc=getDocument();
			element = doc.getRootElement();
			List elist = element.elements();
			for(int i = 0; i < elist.size(); i++){
				dbName=((Element)elist.get(i)).getName();
				//System.out.println("loadConfig:"+dbName);
				configMap.put(dbName, setDBMap(doc,new HashMap(),dbName) );
			}
			//configMap.put(dbName, dbName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		return configMap;
    }

    /**
     * 获取指定的配置参数
     * @param dbName 指定的xml中子元素标识
     * @return
     * @author wangyu
     * @since Jun 16, 2009
     */
    public Map getConfig(String dbName){
    	if("".equals(dbName))dbName=CONFIG_KEY;
    	
    	long newTime=mFile.lastModified();
    	
    	Map dbMap = null;
    	if(newTime>mLastModifyTime){
    		loadConfig();	
    	}
    	//System.out.println(configMap.get(dbName));
    	dbMap=(Map)configMap.get(dbName);
    	mLastModifyTime=newTime;
    	
    	return dbMap;
    }

    /**
     * 将需要读取的参数写入到Map中
     * @param configMap 配置参数Map
     * @param dbName 配置文件中所要读取的标识
     * @return
     * @author wangyu
     * @since Jun 16, 2009
     */
    private Map setDBMap(Document doc, Map configMap,String dbName){
    	try {
			element = doc.getRootElement();
			element = element.element(dbName);
			elements = element.elements();
			//System.out.println("elements size:"+elements.size());
		    for (int i = 0; i < elements.size(); i++) {
		    	configMap.put(((Element)elements.get(i)).getName(),((Element)elements.get(i)).getName());
		    	//System.out.println("elements:"+((Element)elements.get(i)).getName());
	            childElement=element.element(((Element)elements.get(i)).getName());
	            childElements = childElement.elements();
	            //System.out.println("childElements size:"+childElements.size());
	            for(int j=0;j<childElements.size();j++){
	            	//System.out.println(((Element)childElements.get(j)).getText());
	            	configMap.put(((Element)childElements.get(j)).getName(),((Element)childElements.get(j)).getText());
	            }

	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return configMap;
    }

    /**
     * 读取配置文件放入Document对象中
     * @return
     * @throws DocumentException
     * @author wangyu
     * @throws FileNotFoundException 
     * @since Jun 16, 2009
     */
    private Document getDocument() throws DocumentException, FileNotFoundException{
    	mFile=new File(PFILE);
    	InputStream in=new FileInputStream(mFile);  	
        SAXReader reader = new SAXReader();
    	doc = reader.read(in);
    	return doc;
    }
    
	public static void main(String[] args) {
		Map config = DBConfig.getInstance().getConfig("");
		Iterator it = config.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			System.out.println("key:"+entry.getKey()+" value:"+entry.getValue());
		}

		System.out.println("==================================================");

//		Map m =DBConfig.getInstance().getConfig("stmadc");
//		Iterator i = m.entrySet().iterator();
//		while (i.hasNext()) {
//			Map.Entry entry = (Map.Entry) i.next();
//			System.out.println("key:"+entry.getKey()+" value:"+entry.getValue());
//		}
	}
}
