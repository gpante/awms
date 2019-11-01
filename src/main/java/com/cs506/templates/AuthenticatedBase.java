package com.cs506.templates;

import com.cs506.SecureSession;

/**
 * Ensures that user is authenticated in session. If no user is signed in, a sign in is forced by
 * redirecting the browser to the SignIn page.
 * @author AJ
 */
public class AuthenticatedBase extends Base {
	
	public SecureSession getSecureSession() {
		return (SecureSession) getSession();
	}
}
