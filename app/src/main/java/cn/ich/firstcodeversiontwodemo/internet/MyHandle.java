package cn.ich.firstcodeversiontwodemo.internet;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyHandle extends DefaultHandler {

    private String nodeName;

    private StringBuffer id;

    private StringBuffer name;

    private StringBuffer version;

    //开始xml解析是调用
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        id = new StringBuffer();
        name = new StringBuffer();
        version = new StringBuffer();
    }
    //开始解析某节点是开始调用
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        //记录当前节点名称
        nodeName = localName;
    }
    //获取节点内容时调用
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        //根据节点名称判断内容添加到哪一个StringBuffer对象中
        if("id".equals(nodeName)){
            id.append(ch,start,length);
        }else if("name".equals(nodeName)){
            name.append(ch,start,length);
        }else if("version".equals(nodeName)){
            version.append(ch,start,length);
        }
    }
    //完成某个节点解析调用
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if("app".equals(localName)){
            Log.d("ContentHandle","id:"+id.toString().trim());
            Log.d("ContentHandle","name:"+name.toString().trim());
            Log.d("ContentHandle","version:"+version.toString().trim());
            //清除StringBuffers
            id.setLength(0);
            name.setLength(0);
            version.setLength(0);
        }
    }
    //完成整个xml解析时调用
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}
