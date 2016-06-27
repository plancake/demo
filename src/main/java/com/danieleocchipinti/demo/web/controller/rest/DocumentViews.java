package com.danieleocchipinti.demo.web.controller.rest;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.danieleocchipinti.demo.entity.DocumentView;
import com.danieleocchipinti.demo.repository.DealRepository;
import com.danieleocchipinti.demo.repository.DocumentRepository;
import com.danieleocchipinti.demo.repository.DocumentViewRepository;

@RestController("RestDocumentViews")
@RequestMapping(value="/account/document-views")
public class DocumentViews {

	private final static String MODE_START = "start";
	private final static String MODE_END = "end";	
	
	@Autowired
	private DocumentRepository documentRepository;

	@Autowired
	private DocumentViewRepository documentViewRepository;	
	
    @RequestMapping(value="/{documentId}", method=RequestMethod.POST)
    public int recordDocumentView(@PathVariable int documentId, HttpServletRequest request) {
    	
    	String mode = request.getParameter("mode");
    	int viewId = new Integer(request.getParameter("view_id")).intValue();
    	
    	DocumentView documentView = null;
    	
    	if (mode.equals(MODE_START)) {
    		documentView = new DocumentView(documentRepository.findOneById(documentId));
    	} else if (mode.equals(MODE_END)) {
    		documentView = documentViewRepository.findOneById(viewId);
    		documentView.endView();
    	}
    	
    	documentViewRepository.save(documentView);
    	
    	return documentView.getId();
    }	
	
    @RequestMapping(value="/{userId}", method=RequestMethod.GET)
    public Object getUser(@PathVariable Long userId) {
        return new Object(){ public String firstName = "Firstname"; };
    }
}