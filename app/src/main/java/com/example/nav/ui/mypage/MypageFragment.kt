package com.example.nav.ui.mypage

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.nav.BuildConfig
import com.example.nav.databinding.FragmentMypageBinding
import kotlinx.android.synthetic.main.fragment_home.*


class MypageFragment : Fragment() {

    private lateinit var mypageViewModel: MypageViewModel
    private var _binding: FragmentMypageBinding? = null

    private val binding get() = _binding!!



    val BASE_URL = BuildConfig.BASE_URL
    val url = BASE_URL + "community/page/1"


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mypageViewModel =
            ViewModelProvider(this).get(MypageViewModel::class.java)

        _binding = FragmentMypageBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val pwebView: WebView = binding.pwebView
        goToWeb(pwebView, url)

        pwebView.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (event.action != KeyEvent.ACTION_DOWN) return@OnKeyListener true
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                if (pwebView.canGoBack()) {
                    pwebView.goBack()
                }
                return@OnKeyListener true
            }
            false
        })
        return root
    }

    fun goToWeb(view: WebView, url: String) {
        view.settings.javaScriptEnabled = true // 자바 스크립트 허용
        /* 웹뷰에서 새 창이 뜨지 않도록 방지하는 구문 */
        view.webViewClient = WebViewClient()
        view.webChromeClient = WebChromeClient()
        /* 웹뷰에서 새 창이 뜨지 않도록 방지하는 구문 */
        view.loadUrl(url)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}