package net.example.test_rss_reader.view

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import net.example.test_rss_reader.R
import net.newstyleservice.common_ktx.extension.createFullScreen

class ProgressDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        super.onCreateDialog(savedInstanceState).apply {
            createFullScreen(
                layoutRes = R.layout.fragment_dialog_progress,
                isCancelable = false,
                isCanceledOnTouchOutside = false
            )
        }

    companion object {
        fun newInstance(): ProgressDialogFragment = ProgressDialogFragment()
    }
}
