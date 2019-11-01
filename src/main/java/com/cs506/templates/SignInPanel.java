package com.cs506.templates;

import org.apache.wicket.authentication.IAuthenticationStrategy;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;



public final class SignInPanel extends Panel {

	private String password;
	private String username;
	
	private AuthenticatedWebSession session = AuthenticatedWebSession.get();
	
	public SignInPanel(final String id) {
		super(id);
		add(new FeedbackPanel("feedback"));
		add(new SignInForm("signInForm"));
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(final String username) {
		this.username = username;
	}

	@Override
	protected void onConfigure() {
		IAuthenticationStrategy ias = getApplication().getSecuritySettings().getAuthenticationStrategy();
		String[] credentials = ias.load();

		if (credentials != null && credentials.length > 1) {
			if (session.signIn(credentials[0], credentials[1])) {
				username = credentials[0];
				password = credentials[1];
			} else
				ias.remove();
		}

		super.onConfigure();
	}

	public final class SignInForm extends StatelessForm<SignInPanel> {

		public SignInForm(final String id) {
			super(id);

			setModel(new CompoundPropertyModel<>(SignInPanel.this));
			add(new TextField<>("username").setRequired(true));
			add(new PasswordTextField("password"));
		}

		@Override
		public final void onSubmit() {
			IAuthenticationStrategy strategy = getApplication().getSecuritySettings().getAuthenticationStrategy();

			if (session.signIn(getUsername(), getPassword())) {
				strategy.remove();
				// redirect to homepage
				setResponsePage(getApplication().getHomePage());
			} else {
				error(getLocalizer().getString("signInFailed", this, "Invalid username or password"));
				strategy.remove();
			}
		}
	}
}