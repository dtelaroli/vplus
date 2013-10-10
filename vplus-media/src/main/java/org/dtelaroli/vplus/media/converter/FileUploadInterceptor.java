package org.dtelaroli.vplus.media.converter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.dtelaroli.vplus.media.domain.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.http.InvalidParameterException;
import br.com.caelum.vraptor.http.MutableRequest;
import br.com.caelum.vraptor.interceptor.ResourceLookupInterceptor;
import br.com.caelum.vraptor.interceptor.multipart.DefaultUploadedFile;
import br.com.caelum.vraptor.interceptor.multipart.MultipartConfig;
import br.com.caelum.vraptor.interceptor.multipart.MultipartInterceptor;
import br.com.caelum.vraptor.interceptor.multipart.ServletFileUploadCreator;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validations;

import com.google.common.base.Strings;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;

@Intercepts(before = { ResourceLookupInterceptor.class }, after = {})
@RequestScoped
public class FileUploadInterceptor implements MultipartInterceptor {

	private static final Logger logger = LoggerFactory
			.getLogger(FileUploadInterceptor.class);

	private final HttpServletRequest request;
	private final MutableRequest parameters;
	
    private final MultipartConfig config;
    private final Validator validator;
    private final ServletFileUploadCreator fileUploadCreator;
    private Multiset<String> indexes;

	private final File file;
    private String path;
    private String description;

	private Multimap<String, String> params;
	
    public FileUploadInterceptor(HttpServletRequest request, MutableRequest parameters, MultipartConfig cfg,
            Validator validator, ServletFileUploadCreator fileUploadCreator) {
    	this.request = request;
        this.parameters = parameters;
        this.validator = validator;
        this.config = cfg;
        this.fileUploadCreator = fileUploadCreator;
        file = new File();
    }

    /**
     * Will intercept the request if apache file upload says that this request is multipart
     */
    public boolean accepts(ResourceMethod method) {
        return ServletFileUpload.isMultipartContent(request);
    }

    public void intercept(InterceptorStack stack, ResourceMethod method, Object instance)
        throws InterceptionException {
        logger.info("Request contains multipart data. Try to parse with commons-upload.");

        FileItemFactory factory = createFactoryForDiskBasedFileItems(config.getDirectory());
        indexes = HashMultiset.create();
        
        ServletFileUpload uploader = fileUploadCreator.create(factory);
        uploader.setSizeMax(config.getSizeLimit());

        try {
            final List<FileItem> items = uploader.parseRequest(request);
            logger.debug("Found {} attributes in the multipart form submission. Parsing them.", items.size());

            params = LinkedListMultimap.create();

            for (FileItem item : items) {
                String name = item.getFieldName();
                name = fixIndexedParameters(name);
                
                if (item.isFormField()) {
                    logger.debug("{} is a field", name);
                    params.put(name, getValue(item));

                } else if (isNotEmpty(item)) {
                    logger.debug("{} is a file", name);
                    processFile(item, name);

                } else {
                    logger.debug("A file field was empty: {}", item.getFieldName());
                }
            }

            for (String paramName : params.keySet()) {
                Collection<String> paramValues = params.get(paramName);
                parameters.setParameter(paramName, paramValues.toArray(new String[paramValues.size()]));
            }

        } catch (final SizeLimitExceededException e) {
            reportSizeLimitExceeded(e);

        } catch (FileUploadException e) {
            logger.warn("There was some problem parsing this multipart request, "
                    + "or someone is not sending a RFC1867 compatible multipart request.", e);
        }

        stack.next(method, instance);
    }

    private boolean isNotEmpty(FileItem item) {
        return item.getName().length() > 0;
    }

    /**
     * This method is called when the {@link SizeLimitExceededException} was thrown. By default, add the key
     * file.limit.exceeded using {@link Validations}.
     * 
     * @param e
     */
    protected void reportSizeLimitExceeded(final SizeLimitExceededException e) {
        validator.add(new I18nMessage("upload", "file.limit.exceeded", e.getActualSize(), e.getPermittedSize()));
        logger.warn("The file size limit was exceeded.", e);
    }

    protected void processFile(FileItem item, String name) {
        try {
            UploadedFile upload = new DefaultUploadedFile(item.getInputStream(), item.getName(), item.getContentType(), item.getSize());
            file.parse(upload);
            path = name.replaceFirst("^(.+)\\..+", "$1");
            if(!Strings.isNullOrEmpty(description)) {
            	file.setDescription(description);
            }
			request.setAttribute(path, file);

			logger.debug("File parsed: {} with {}", name, file);
        } catch (IOException e) {
            throw new InvalidParameterException("Cant parse uploaded file " + item.getName(), e);
        }
    }

    protected FileItemFactory createFactoryForDiskBasedFileItems(java.io.File temporaryDirectory) {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(temporaryDirectory);

        logger.debug("Using repository {} for file upload", factory.getRepository());
        return factory;
    }

    protected String getValue(FileItem item) {
        String encoding = request.getCharacterEncoding();
        if (!Strings.isNullOrEmpty(encoding)) {
            try {
            	checkDescription(item);
                return item.getString(encoding);
            } catch (UnsupportedEncodingException e) {
                logger.warn("Request have an invalid encoding. Ignoring it");
            }
        }
        checkDescription(item);
        return item.getString();
    }

	private void checkDescription(FileItem item) {
		if(item.getFieldName().equals(path + ".description")) {
        	file.setDescription(item.getString());
        }
		else if(item.getFieldName().endsWith(".description")) {
			description = item.getString();
		}
	}

    protected String fixIndexedParameters(String name) {
        if (name.contains("[]")) {
            String newName = name.replace("[]", "[" + (indexes.count(name)) + "]");
            indexes.add(name);
            logger.debug("{} was renamed to {}", name, newName);
            name = newName;
        }
        return name;
    }

}
