package com.example.apprender.data

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.TextInputEditText
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import java.util.*

class Recognition (val context: Context?){

    @SuppressLint("ClickableViewAccessibility")
    fun startSpeechToText(btnMic : FloatingActionButton, editText: EditText){

        val speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)
        val speechRecognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())

        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(params: Bundle?) {

            }

            override fun onRmsChanged(rmsdB: Float) {

            }

            override fun onBufferReceived(buffer: ByteArray?) {

            }

            override fun onPartialResults(partialResults: Bundle?) {

            }

            override fun onEvent(eventType: Int, params: Bundle?) {

            }

            override fun onBeginningOfSpeech() {

            }

            override fun onEndOfSpeech() {

            }

            override fun onError(error: Int) {

            }

            override fun onResults(results: Bundle?) {

                val matches = results!!.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)

                if (matches != null){
                    editText.setText(matches[0])
                }
            }
        })

        btnMic.setOnTouchListener(View.OnTouchListener { view, motionEvent ->
            when(motionEvent.action){
                MotionEvent.ACTION_UP ->{
                    speechRecognizer.stopListening()
                    editText.hint = "..."
                }
                MotionEvent.ACTION_DOWN ->{
                    speechRecognizer.startListening(speechRecognizerIntent)
                    editText.setText("")
                    editText.hint = "Escuchando..."
                }
            }
            false
        })
    }
}