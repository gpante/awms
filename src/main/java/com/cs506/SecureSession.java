/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cs506;

import java.util.LinkedList;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

import com.cs506.database.Database;


public final class SecureSession extends AuthenticatedWebSession
{
    private User user;

    public SecureSession(Request request)
    {
        super(request);
    }

    @Override
    public final boolean authenticate(final String username, final String password) {
    	
    	
    	Database db = new Database();
    	
    	LinkedList<String[]> list = null;
    	try {	
    		list = db.getUser(username, password);
    		System.out.println(list.getFirst()[0] + list.getFirst()[1] + list.getFirst()[2]);
    	}catch(Exception e) {
    		System.out.print(e);
    		db.closeConn();
    		return false;
    	}
    	
    	db.closeConn();
    	setUser(new User(username));
		return true;
    	
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    @Override
    public void invalidate() {
        super.invalidate();
        user = null;
    }

	@Override
	public Roles getRoles() {
		// TODO Auto-generated method stub
		return null;
	}
}