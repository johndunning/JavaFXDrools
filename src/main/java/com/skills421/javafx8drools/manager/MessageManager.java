package com.skills421.javafx8drools.manager;

import org.controlsfx.dialog.Dialogs;


public class MessageManager
{
	private static MessageManager instance;
	
	private MessageManager()
	{
		
	}
	
	public static MessageManager getInstance()
	{
		if(instance==null)
		{
			instance = new MessageManager();
		}
		
		return instance;
	}
	
	public void displayMessage(String title, String message)
	{
		Dialogs.create()
	      .owner( null)
	      .title(title)
	      .masthead(null)
	      .message( message)
	      .showInformation();
	}
}
