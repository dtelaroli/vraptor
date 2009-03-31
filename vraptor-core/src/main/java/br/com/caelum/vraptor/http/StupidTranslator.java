package br.com.caelum.vraptor.http;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.caelum.vraptor.resource.ResourceRegistry;

/**
 * Basic url to resource method translator.
 * 
 * @author Guilherme Silveira
 */
public class StupidTranslator implements UrlToResourceTranslator {

    private final ResourceRegistry registry;

    private static final Logger logger = LoggerFactory.getLogger(StupidTranslator.class);

    public StupidTranslator(ResourceRegistry registry) {
        this.registry = registry;
    }

    public ResourceMethod translate(HttpServletRequest request) {
        String resourceName = request.getRequestURI();
        logger.debug("trying to access " + resourceName);
        if (resourceName.length() > 1 && resourceName.indexOf('/', 1) != -1) {
            resourceName = resourceName.substring(resourceName.indexOf('/', 1));
        }
        String methodName = request.getMethod();
        ResourceMethod resource = registry.gimmeThis(resourceName, methodName);
        return resource;
    }

}
