package com.mediaservices.mchartcollector;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mediaservices.model.RadioStation;
import org.jsoup.Connection.Method;

import java.util.Iterator;


public class Ar40ppalesCollector extends ChartCollector {

  public String out = "UNKNOWN";

  public Ar40ppalesCollector(){
    RadioStation station = new RadioStation();
    station.setName("40 Principales");
    station.setCountry("Argentina");
    setMedium(station);
    setBaseUrl("http://los40principales.com.ar/musica/listas/");
  }
  
  public boolean getChart(){
      boolean canDoIt = false;
      try{
        String tmpStr = "";
        int tmpInt = 0;

        Connection.Response res = Jsoup.connect(getBaseUrl())
          .userAgent("Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.114 Safari/537.36")
          .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
          .header("Origin","http://www.los40principales.com.ar")
          .referrer("http://www.los40principales.com.ar/musica/listas/")
          .data("__EVENTTARGET", "ctl00$ContentPlaceHolder1$ctr_topminiplayerlista1$repDatos")
          .data("__EVENTARGUMENT", "Page$2")
          .data("__ASYNCPOST", "true")
          .method(Method.POST).execute();
        
        Document doc = res.parse();
        Elements songsDivs = doc.select("div.listamusica");
        for (Iterator<Element> iter = songsDivs.iterator(); iter.hasNext(); ) {
          Element songDiv = iter.next();
          out += "<br>pos: "+songDiv.getElementsByTag("div").get(0).attr("pos");
          out += "<br>Titulo Cancion: "+songDiv.select("a.titulocancion").get(0).text();
          out += "<br>Autor: "+songDiv.select("div.textolista").get(0).getElementsByTag("a").get(1).text();
        }
        //Element mainTable = htmlElements.getElegetElementsBy("td").get(0); //get the important table
        
        //out = htmlElements.get(0).toString();
        //out = songsDivs.get(0).toString() + "<br><br>" + songsDivs.get(3).toString();

/*
        htmlElements = htmlElements.getElementsByTag("tr").get(3); 
        Element mainTable = htmlElements.getElementsByTag("td").get(0); //get the important table
        
        //get Region
        htmlElements = mainTable.getElementsByTag("tr").get(8);
        htmlElements = htmlElements.getElementsByTag("td").get(0); 
        setRegion(htmlElements.text());
        
        //get City
        htmlElements = mainTable.getElementsByTag("tr").get(9);
        htmlElements = htmlElements.getElementsByTag("td").get(0); 
        tmpStr = htmlElements.text().trim();
        if (tmpStr.length()>0){
          setCity(tmpStr);
        }
        
        //get CountryCode
        htmlElements = mainTable.getElementsByTag("tr").get(6);
        htmlElements = htmlElements.getElementsByTag("td").get(0); 
        tmpStr = htmlElements.text().trim();
        tmpInt = tmpStr.indexOf("(");
        if (tmpInt != -1){
          tmpStr = tmpStr.substring(tmpInt+1, tmpInt+3).trim();
          setCountryCode(tmpStr);
        }
*/        
        //return info
        //setCountryName(htmlElements.toString());
        canDoIt=true;
      }catch(Exception e){
        out=e.toString();
        e.printStackTrace();
      }
     
      return canDoIt;
   }




}
