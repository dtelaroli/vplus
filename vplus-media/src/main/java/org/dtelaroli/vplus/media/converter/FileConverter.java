package org.dtelaroli.vplus.media.converter;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.dtelaroli.vplus.media.domain.File;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.Converter;

@Convert(File.class)
public class FileConverter implements Converter<File> {

	private HttpServletRequest request;

	public FileConverter(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public File convert(String value, Class<? extends File> type,
			ResourceBundle bundle) {
		Object upload = request.getAttribute(value);
		return type.cast(upload);
	}

}
