package com.example.brianhsu.smack.Adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.brianhsu.smack.Model.Message
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.example.brianhsu.smack.R
import com.example.brianhsu.smack.Services.UserDataServices
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by brian on 2018/4/26.
 */
class MessageAdapter(val context: Context, val messages: ArrayList<Message>): RecyclerView.Adapter<MessageAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindMessage(context, messages[position])
    }

    override fun getItemCount(): Int {
        return messages.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.message_list_view, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val userImage = itemView?.findViewById<ImageView>(R.id.messageUserImage)
        val timeStamp = itemView?.findViewById<TextView>(R.id.timeStampLbl)
        val userName = itemView?.findViewById<TextView>(R.id.messageUserNameLbl)
        val messageBody = itemView?.findViewById<TextView>(R.id.messageBodyLbl)

        fun bindMessage(context: Context, message: Message) {
            val resourceId = context.resources.getIdentifier(message.userAvatar, "drawable", context.packageName)
            userImage?.setImageResource(resourceId)
            userImage?.setBackgroundColor(UserDataServices.returnAvatarColor(message.userAvatarColor))
            timeStamp?.text = returnDateString(message.timeStamp)
            userName?.text = message.userName
            messageBody?.text = message.message
        }

        fun returnDateString(isoString: String) : String {

            val isFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
            isFormatter.timeZone = TimeZone.getTimeZone("UTC")
            var convertedDate = Date()
            try {
               convertedDate = isFormatter.parse(isoString)
            } catch (e: ParseException) {
                Log.d("Parse", "Cannot parse date")
            }

            val outDateSFormatter = SimpleDateFormat("E, hh:mm a", Locale.getDefault())
            return outDateSFormatter.format(convertedDate)
        }
    }
}