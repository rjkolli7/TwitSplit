package com.demo.twitsplit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.demo.twitsplit.helper.CommonHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mMessageDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tweet_msg_edt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                tweet_send_tv.isEnabled = !s.toString().trim { it <= ' ' }.isEmpty() // enable if tweet_msg_edt is not empty, disable tweet_msg_edt is empty
            }
        })
    }

    /**
     * Using this method in XML file.
     * When tweet_send_tv clicked this method will execute.
     *
    * @param v
    * */
    fun sendTweet(v: View) {
        val messages = CommonHelper.splitMessage(tweet_msg_edt.text.toString().trim { it <= ' ' })
        if (messages != null && messages.isNotEmpty()) {
            val builder = StringBuilder()
            for (s in messages) { // append messages to builder with 2 new lines
                builder.append(s) //
                builder.append("\n")
                builder.append("\n")
            }
            // show dialog with messages
            showMessageDialog("Tweet Sent Successfully!", builder.toString(), false)
        } else {
            showMessageDialog(
                "Tweeting failed!",
                "Can not send your tweet. Make sure your tweet message should have 50 characters. \n\n Do you want to edit message?",
                true
            )
        }
    }

    /**
     * This method shows a dialog with status of tweet success or fail
     * @param error
     * @param title
     * @param message
     * */
    private fun showMessageDialog(title: String, message: String, error: Boolean) {
        if(mMessageDialog?.isShowing == true) // dismiss dialog if it is already showing
            mMessageDialog?.dismiss()   // dismissing dialog will prevent multiple dialog overlaps

        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
        builder.setTitle(title)
        builder.setCancelable(false) // only buttons click closes dialog


        builder.setPositiveButton(
            android.R.string.ok
        ) { _, _ ->
            // if not error make tweet_msg_edt empty
            // if error edit message based on user request
            if (!error) tweet_msg_edt.setText("")
        }

        if (error) { // if only error show negative button
            builder.setNegativeButton(
                android.R.string.no
            ) { _, _ ->
                // make tweet_msg_edt empty
                tweet_msg_edt.setText("")
            }
        }

        mMessageDialog = builder.create() // create a dialog
        mMessageDialog?.show() // show dialog
    }
}
