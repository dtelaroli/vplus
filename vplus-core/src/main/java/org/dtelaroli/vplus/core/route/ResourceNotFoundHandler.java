package org.dtelaroli.vplus.core.route;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.RequestInfo;
import br.com.caelum.vraptor.http.route.ResourceNotFoundException;
import br.com.caelum.vraptor.http.route.Router;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.resource.DefaultResourceNotFoundHandler;
import br.com.caelum.vraptor.resource.HttpMethod;

@RequestScoped
public class ResourceNotFoundHandler extends DefaultResourceNotFoundHandler {
	
	private final Router router;  
    private final Result result;  
    
    public ResourceNotFoundHandler(Router router, Result result) {  
        this.router = router;  
        this.result = result;  
    }  
      
    @Override  
    public void couldntFind(RequestInfo requestInfo) {  
        try {  
            String uri = requestInfo.getRequestedUri();  
            if (uri.endsWith("/")) {  
                tryMovePermanentlyTo(requestInfo, uri.substring(0, uri.length() - 1));  
            } else {  
                tryMovePermanentlyTo(requestInfo, uri + "/");
            }  
        } catch (ResourceNotFoundException ex) {  
            super.couldntFind(requestInfo);  
        }  
    }  
  
    private void tryMovePermanentlyTo(RequestInfo requestInfo, String newUri) {  
        router.parse(newUri, HttpMethod.of(requestInfo.getRequest()), 
            requestInfo.getRequest());  
        result.permanentlyRedirectTo(newUri);
    }
    
}