package org.dtelaroli.vplus.media.converter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.dtelaroli.vplus.media.domain.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.http.InvalidParameterException;
import br.com.caelum.vraptor.http.MutableRequest;
import br.com.caelum.vraptor.interceptor.ResourceLookupInterceptor;
import br.com.caelum.vraptor.interceptor.multipart.CommonsUploadMultipartInterceptor;
import br.com.caelum.vraptor.interceptor.multipart.DefaultUploadedFile;
import br.com.caelum.vraptor.interceptor.multipart.MultipartConfig;
import br.com.caelum.vraptor.interceptor.multipart.ServletFileUploadCreator;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.ioc.RequestScoped;

@Intercepts(before = { ResourceLookupInterceptor.class,
		CommonsUploadMultipartInterceptor.class }, after = {})
@RequestScoped
public class FileUploadInterceptor extends CommonsUploadMultipartInterceptor {

	private static final Logger logger = LoggerFactory
			.getLogger(FileUploadInterceptor.class);

	private final HttpServletRequest request;
    private final MutableRequest parameters;
	private final File file;
    
	public FileUploadInterceptor(HttpServletRequest request,
			MutableRequest parameters, MultipartConfig cfg,
			Validator validator, ServletFileUploadCreator fileUploadCreator) {
		super(request, parameters, cfg, validator, fileUploadCreator);
		this.request = request;
		this.parameters = parameters;
		file = new File();
	}

	@Override
	protected void processFile(FileItem item, String name) {
		try {
			UploadedFile upload = new DefaultUploadedFile(
					item.getInputStream(), item.getName(),
					item.getContentType(), item.getSize());
			parameters.setParameter(name, name);
			
			file.parse(upload);
			
			request.setAttribute(name, file);

			logger.debug("Uploaded file: {} with {}", name, file);
		} catch (IOException e) {
			throw new InvalidParameterException("Cant parse uploaded file "
					+ item.getName(), e);
		}
	}

	@Override
	protected String getValue(FileItem item) {
		String fieldName = item.getFieldName();
		if(fieldName.endsWith(".fileDescription")) {
			file.setFileDescription(item.getString());
		}
		return super.getValue(item);
	}
}
