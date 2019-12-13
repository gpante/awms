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

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import com.cs506.AboutALPs;
import com.cs506.AboutWorkshops;
import com.cs506.HomePage;
import com.cs506.SecureSession;
import com.cs506.SignIn;
import com.cs506.SignOut;
import com.cs506.WorkshopCalendarPage;
import com.cs506.WorkshopRequestPage;
import com.cs506.WorkshopTable;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarComponents;

public class HeaderPanel extends Panel {

	public HeaderPanel(String id) {
		super(id);

		//setRenderBodyOnly(true);

		Bootstrap.install(getApplication());
		Navbar navbar = new Navbar("navbar");
		navbar.setInverted(true);
		//navbar.fluid();
		navbar.setBrandName(null);
		    
		navbar.addComponents(NavbarComponents.transform(Navbar.ComponentPosition.LEFT,
				new NavbarButton<HomePage>(HomePage.class, Model.of("Home")),
				new NavbarButton<WorkshopRequestPage>(WorkshopRequestPage.class, Model.of("Request a Workshop")),
				new NavbarButton<AboutWorkshops>(AboutWorkshops.class, Model.of("Workshops")),
				new NavbarButton<AboutALPs>(AboutALPs.class, Model.of("About Us")),
				new NavbarButton<WorkshopCalendarPage>(WorkshopCalendarPage.class, Model.of("Calendar"))));
		
		if (((SecureSession)getSession()).getUser() != null && !this.getClass().equals(SignOut.class)) {
			navbar.addComponents(NavbarComponents.transform(Navbar.ComponentPosition.LEFT, 
					new NavbarButton<SignOut>(SignOut.class, Model.of("Sign Out"))));
		} else {
			navbar.addComponents(NavbarComponents.transform(Navbar.ComponentPosition.LEFT, 
					new NavbarButton<SignOut>(SignIn.class, Model.of("Sign In"))));
		}
		
		NavbarButton<WorkshopTable> table = new NavbarButton<WorkshopTable>(WorkshopTable.class, Model.of("Workshop table"));
		table.setEnabled(false);
		table.setVisible(false);
		if (((SecureSession)getSession()).getUser() != null) {
			table.setEnabled(true);
			table.setVisible(true);
		}
		navbar.addComponents(NavbarComponents.transform(Navbar.ComponentPosition.LEFT, 
				table));
		add(navbar);
	}
}










