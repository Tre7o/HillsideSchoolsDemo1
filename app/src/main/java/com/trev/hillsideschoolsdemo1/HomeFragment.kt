package com.trev.hillsideschoolsdemo1

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.trev.hillsideschoolsdemo1.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        navController = findNavController()

        val webView = view.findViewById<WebView>(R.id.web_home)

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

        webView.loadUrl("https://www.hillsideschoolsnaalya.com")
        webView.settings.javaScriptEnabled = true
        webView.settings.allowContentAccess = true
        webView.settings.useWideViewPort = true
        webView.settings.domStorageEnabled = true

    }

}