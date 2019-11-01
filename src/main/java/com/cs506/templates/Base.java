/**
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.cs506.templates;

import org.apache.wicket.Component;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.CssResourceReference;

public class Base extends WebPage {

	private Component headerPanel;
	private Component footerPanel;
	
	public Base() {
		this(new PageParameters());
	}
 
    public Base(final PageParameters pageParameters){
    	super(pageParameters);
    	
		headerPanel = new HeaderPanel("headerPanel");
		footerPanel = new FooterPanel("footerPanel");
		add(headerPanel);
		add(footerPanel);
	}
    //getters for layout areas
	protected Component getHeaderPanel() {
		return headerPanel;
	}

	protected Component getFooterPanel() {
		return footerPanel;
	}
	
	@Override
	public void renderHead(IHeaderResponse response)
	{
		response.render(CssHeaderItem.forReference(new CssResourceReference(Base.class, "style.css"),"screen"));
	}
}
