package com.shan.org.shan.web.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TrimConverter implements Converter<String, String>{

	@Override
	public String convert(String source) {
		try {
			if(null != source){
				source = source.trim();
				if(!"".equals(source)){
					return source;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
