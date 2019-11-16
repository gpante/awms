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

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import com.cs506.SecureSession;
import com.cs506.SignIn;
import com.cs506.SignOut;
import com.cs506.WorkshopEditPage;
import com.cs506.database.Database;

public class HeaderPanel extends Panel {

	public HeaderPanel(String id) {
		super(id);

		setRenderBodyOnly(true);
		
		if (((SecureSession)getSession()).getUser() != null && !this.getClass().equals(SignOut.class)) {
			final BookmarkablePageLink<Void> signOutLink = new BookmarkablePageLink<>("sign", SignOut.class);
			signOutLink.add(new Label("signText", "Sign Out"));
			add(signOutLink);
		} else {
			final BookmarkablePageLink<Void> signInLink = new BookmarkablePageLink<>("sign", SignIn.class);
			signInLink.add(new Label("signText", "Sign In"));
			add(signInLink);
		}

		final Link<Void> viewLink = new Link<Void>("view") {

			@Override
			public void onClick() {
				setResponsePage(new WorkshopEditPage(new Database().getWorkshop("Test")));
			}
			
		};
		viewLink.add(new Label("viewText", "View a Workshop"));
		add(viewLink);
//		final BookmarkablePageLink<Void> homeLink = new BookmarkablePageLink<>("home", HomePage.class);
//		homeLink.add(new Label("homeText", "Home"));
//		add(homeLink);
//		final BookmarkablePageLink<Void> submitRequestLink = new BookmarkablePageLink<>("submitRequest", WorkshopRequestPage.class);
//		submitRequestLink.add(new Label("submitRequestText", "Request a Workshop"));
//		add(submitRequestLink);
//		final BookmarkablePageLink<Void> aboutALPs = new BookmarkablePageLink<>("aboutALPs", AboutALPs.class);
//		aboutALPs.add(new Label("aboutALPsText", "About ALPs"));
//		add(aboutALPs);
//		final BookmarkablePageLink<Void> aboutWorkshops = new BookmarkablePageLink<>("aboutWorkshops", AboutWorkshops.class);
//		aboutWorkshops.add(new Label("aboutWorkshopsText", "About Workshops"));
//		add(aboutWorkshops);
	}
}










