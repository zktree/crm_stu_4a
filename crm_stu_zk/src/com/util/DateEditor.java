package com.util;
import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateEditor extends PropertyEditorSupport {  
    @Override  
    public void setAsText(String text) throws IllegalArgumentException { 
    	if(text!=null && !"".equals(text)){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
            Date date = null;  
            try {  
                date = format.parse(text);  
            } catch (ParseException e) {  
                format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
                try {  
                    date = format.parse(text);  
                } catch (ParseException e1) {  
                    e1.printStackTrace();  
                }  
            }  
            setValue(date); 
    	}
    }  
}