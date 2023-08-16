package com.trev.hillsideschoolsdemo1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient

class ContactFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val webView = view.findViewById<WebView>(R.id.web_contact)

        // Set up WebViewClient to handle page navigation and load URLs within the WebView
        webView.webViewClient = object: WebViewClient(){
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                url: String,
            ): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        //webViewClient is the object responsible for the mst actions inside the webView
        //Some of those actions include enabling javascript, security, routing

        webView.loadUrl("https://www.hillsideschoolsnaalya.com/contact-us/")
        webView.settings.javaScriptEnabled = true
        webView.settings.allowContentAccess = true
        webView.settings.useWideViewPort = true
        webView.settings.domStorageEnabled = true

    }

}