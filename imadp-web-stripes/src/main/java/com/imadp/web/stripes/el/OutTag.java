/*
Copyright (c) 2010, Chin Huang
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

 * Redistributions of source code must retain the above copyright notice,
   this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice,
   this list of conditions and the following disclaimer in the documentation
   and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package com.imadp.web.stripes.el;

import javax.servlet.jsp.tagext.TagSupport;

/**
 * OutTag
 * 
 * A tag library to enable/disable xml escaping, to be used in conjuction to the custom EscapeXmlElResolver.
 * 
 * @version 1.0
 * @author Anthony DePalma
 */
public class OutTag extends TagSupport {
   
	// default
    private static final boolean ESCAPE_XML_DEFAULT = false;
    
    private boolean escapeXml;
    
    // constructor
    public OutTag() {
        release();
    }
    
    public void setEscapeXml(boolean escapeXml) {
        this.escapeXml = escapeXml;
    }
    
    @Override
    public int doStartTag() {
        pageContext.setAttribute(EscapeXmlELResolver.ESCAPE_XML_ATTRIBUTE, escapeXml);
        return EVAL_BODY_INCLUDE;
    }
    
    @Override
    public int doEndTag() {
        pageContext.setAttribute(EscapeXmlELResolver.ESCAPE_XML_ATTRIBUTE, ESCAPE_XML_DEFAULT);
        return EVAL_PAGE;
    }
    
    @Override
    public void release() {
        escapeXml = ESCAPE_XML_DEFAULT;
    }
}
