package com.demo.twitsplit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mMessageDialog: AlertDialog? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tweet_msg_edt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                tweet_send_tv.isEnabled = !s.toString().trim { it <= ' ' }.isEmpty()
            }
        })
    }

    fun sendTweet(v: View) {
        val message = CommonHelper.splitMessage(tweet_msg_edt.text.toString().trim { it <= ' ' })
        if (message != null && message.isNotEmpty()) {
            val builder = StringBuilder()
            for (s in message) {
                builder.append(s)
                builder.append("\n")
                builder.append("\n")
            }
            showMessageDialog("Tweet Sent Successfully!", builder.toString(), false)
        } else {
            showMessageDialog(
                "Tweeting failed!",
                "Can not send your tweet. Make sure your tweet message should have 50 characters. \n\n Do you want to edit message?",
                true
            )
        }
    }

    private fun showMessageDialog(title: String, message: String, error: Boolean) {
        mMessageDialog?.isShowing
        mMessageDialog?.dismiss()

        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
        builder.setTitle(title)

        builder.setPositiveButton(
            if (error) android.R.string.yes else android.R.string.ok
        ) { _, _ -> if (!error) tweet_msg_edt.setText("") }

        if (error) {
            builder.setNegativeButton(
                android.R.string.no
            ) { _, _ -> tweet_msg_edt.setText("") }
        }

        mMessageDialog = builder.create()
        mMessageDialog?.show()
    }
}
