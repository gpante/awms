package com.cs506;


import org.apache.wicket.Session;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.authorization.strategies.page.SimplePageAuthorizationStrategy;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.settings.RequestCycleSettings;

import com.cs506.templates.AuthenticatedBase;


/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see com.cs506.Start#main(String[])
 */
public class WicketApplication extends AuthenticatedWebApplication
{
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();
		
		getResourceSettings().setThrowExceptionOnMissingResource(false);
        getRequestCycleSettings().setRenderStrategy(RequestCycleSettings.RenderStrategy.REDIRECT_TO_RENDER);

        // Install a simple page authorization strategy, that checks all pages
        // of type AuthenticatedBase.
        IAuthorizationStrategy authorizationStrategy = new SimplePageAuthorizationStrategy(
            AuthenticatedBase.class, getSignInPageClass())
        {
            @Override
            protected boolean isAuthorized()
            {
                // check whether the user is logged on
                return (((SecureSession)Session.get()).isSignedIn());
            }
        };
        getSecuritySettings().setAuthorizationStrategy(authorizationStrategy);
	}
	
	
	
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() 
	{		
		return SecureSession.class;
	}

	
	
	protected Class<? extends WebPage> getSignInPageClass() 
	{
		return SignIn.class;
	}
	
	/**
	 * 
	 */
	protected Class<? extends WebPage> getWorkshopTable()
	{
		return WorkshopTable.class;
	}
}
