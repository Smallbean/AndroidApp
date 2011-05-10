package org.smallbean.interview

import java.util.HashMap
import java.util.Map

import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Message
import android.widget.ImageView

class DrawableManager {

	val drawableMap:Map[String, Drawable]= new HashMap[String, Drawable]

    def  fetchDrawable(urlString:String):Drawable = {
        if (!drawableMap.containsKey(urlString)) {
            val drawable = Drawable.createFromPath(urlString)
            drawableMap.put(urlString, drawable)
        }        
        drawableMap.get(urlString)
    }

    def fetchDrawableOnThread(urlString:String, imageView:ImageView) {
        if (drawableMap.containsKey(urlString)) {
            imageView.setImageDrawable(drawableMap.get(urlString))
        }

        var handler = new Handler() {
            override def handleMessage(message:Message) {
                imageView.setImageDrawable(message.obj.asInstanceOf[Drawable])
            }
        }

        var thread = new Thread() {
            override def run() {
                //TODO : set imageView to a "pending" image
                val drawable = fetchDrawable(urlString)
                val message = handler.obtainMessage(1, drawable)
                handler.sendMessage(message)
            }
        }
        thread.start()
    }


}