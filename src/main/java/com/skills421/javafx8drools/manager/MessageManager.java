package com.skills421.javafx8drools.manager;

import com.skills421.javafx8drools.MainApp;
import javafx.application.Platform;
import org.controlsfx.dialog.Dialogs;

public class MessageManager
{

    private static MessageManager instance;

    private MessageManager()
    {

    }

    public static MessageManager getInstance()
    {
        if (instance == null)
        {
            instance = new MessageManager();
        }

        return instance;
    }

    public void displayMessage(String title, String message)
    {
        Platform.runLater(new Runnable()
        {
            @Override
            public void run()
            {
                Dialogs.create()
                .owner(MainApp.mainStage())
                .title(title)
                .masthead(null)
                .message(message)
                .showInformation();
            }
        });
        
    }

    public void displayError(String title, String message)
    {
        Platform.runLater(new Runnable()
        {
            @Override
            public void run()
            {
                Dialogs.create()
                        .owner(MainApp.mainStage())
                        .title(title)
                        .masthead(null)
                        .message(message)
                        .showError();
            }
        });

    }
}
