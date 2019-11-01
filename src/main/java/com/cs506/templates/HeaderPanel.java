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
import org.apache.wicket.markup.html.panel.Panel;

import com.cs506.SecureSession;
import com.cs506.SignIn;
import com.cs506.SignOut;

public class HeaderPanel extends Panel {

	public HeaderPanel(String id) {
		super(id);


		setRenderBodyOnly(true);
		
		/*add(new Image("headerimage", new PackageResourceReference(
			HomePage.class, "crest.png")));*/
		if (((SecureSession)getSession()).getUser() != null && !this.getClass().equals(SignOut.class)) {
			final BookmarkablePageLink<Void> signInLink = new BookmarkablePageLink<>("signInButton", SignOut.class);
			signInLink.add(new Label("signInText", "Sign Out"));
			add(signInLink);
			
			/*
			final BookmarkablePageLink<Void> registerLink = new BookmarkablePageLink<>("reg", Register.class);
			Label registerLabel = new Label("regtext", "Register");
			registerLabel.setVisible(false);
			registerLink.add(registerLabel);
			add(registerLink);
			*/
			
			/*
			final BookmarkablePageLink<Void> accountLink = new BookmarkablePageLink<>("account", Account.class);
			Label accountLabel = new Label("accounttext", "Account");
			accountLink.add(accountLabel);
			add(accountLink);
			*/
		} else {
			final BookmarkablePageLink<Void> signInLink = new BookmarkablePageLink<>("signInButton", SignIn.class);
			signInLink.add(new Label("signInText", "Sign In"));
			add(signInLink);
			
			/*
			final BookmarkablePageLink<Void> registerLabel = new BookmarkablePageLink<>("reg", Register.class);
			registerLabel.add(new Label("regtext", "Register"));
			add(registerLabel);
			*/
			
			/*
			final BookmarkablePageLink<Void> accountLink = new BookmarkablePageLink<>("account", Account.class);
			Label accountLabel = new Label("accounttext", "Account");
			accountLabel.setVisible(false);
			accountLink.add(accountLabel);
			add(accountLink);
			*/
		}
	}
}










