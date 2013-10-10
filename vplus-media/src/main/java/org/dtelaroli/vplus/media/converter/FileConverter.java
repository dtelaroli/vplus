package org.dtelaroli.vplus.media.converter;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.dtelaroli.vplus.media.domain.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.Converter;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;

@Convert(File.class)
public class FileConverter implements Converter<File> {

	private static final Logger logger = LoggerFactory
			.getLogger(FileConverter.class);
	
	private HttpServletRequest request;

	public FileConverter(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public File convert(String value, Class<? extends File> type,
			ResourceBundle bundle) {
		try {
			Object upload = request.getAttribute(value);
			if(upload != null) {
				logger.debug("File converted {}", upload);
				return new File((UploadedFile) upload);
			}
		} catch (IOException e) {
			logger.error("Error on convert File", e);
		}
		logger.info("File can't converted, Should return null.");
		return null;
	}

}
