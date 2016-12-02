package com.teamdev.jxbrowser.chromium.demo;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

public class JxBrowserDemo {

    public static void main(String[] arguments) {
        Browser browser = new Browser();
        BrowserView view = new BrowserView(browser);
        browser.loadURL("");

    }

}
