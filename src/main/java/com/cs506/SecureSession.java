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

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;


public final class SecureSession extends AuthenticatedWebSession
{
    private User user;

    protected SecureSession(Request request)
    {
        super(request);
    }

    @Override
    public final boolean authenticate(final String username, final String password)
    {
    	
    	//temp login
    	return username.equals(password) && username.equals("test");
    	
//    	User user = null;
//    	// TODO load user info from db
//    	
//    	if (user != null) {
//    		setUser(user);
//    		return true;
//    	} else {
//    		return false;
//    	}
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(final User user)
    {
        this.user = user;
    }

    @Override
    public void invalidate()
    {
        super.invalidate();
        user = null;
    }

	@Override
	public Roles getRoles() {
		// TODO Auto-generated method stub
		return null;
	}
}