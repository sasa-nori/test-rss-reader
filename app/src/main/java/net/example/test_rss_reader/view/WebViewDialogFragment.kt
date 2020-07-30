package net.example.test_rss_reader.view

import android.app.Dialog
import android.os.Bundle
import android.webkit.WebView
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.google.android.material.button.MaterialButton
import net.example.test_rss_reader.R
import net.newstyleservice.common_ktx.extension.createFullScreen
import net.newstyleservice.common_ktx.extension.setOnSingleClickListener

class WebViewDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        super.onCreateDialog(savedInstanceState).apply {
            createFullScreen(R.layout.fragment_dialog_webview)
        }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val url = arguments?.getString(ARG_URL)
        dialog?.window?.decorView?.let { view ->
            val closeButton = view.findViewById<MaterialButton>(R.id.closeButton)
            closeButton.setOnSingleClickListener {
                dismiss()
            }
            val webView = view.findViewById<WebView>(R.id.webView)
            url?.let { webView.loadUrl(it) }
        }
    }

    companion object {
        fun newInstance(url: String) = WebViewDialogFragment().apply {
            arguments = bundleOf(ARG_URL to url)
        }

        private const val ARG_URL = "arg_url"
    }
}
