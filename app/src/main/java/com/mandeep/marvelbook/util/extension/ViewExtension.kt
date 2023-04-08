package com.mandeep.marvelbook.util.extension

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import coil.load
import com.mandeep.marvelbook.R

/**
 * App Name: Marvel Book
 * Package Name: com.mandeep.marvelbook.util.extension
 * Author : Mandeep Singh
 * Email Id: officialmandeepsp@gmail.com
 * Date: Wed 05 Apr, 2023
 *
 **/

infix fun ImageView.LoadImage(url: String) {
    load(url.generateImgUrl()){
        placeholder(R.drawable.placeholder_movie_poster)
    }

}

infix fun View.onClick(click: () -> Unit) {
    setOnClickListener { click() }
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun String.generateImgUrl(): String {
    return "https://image.tmdb.org/t/p/original/$this"
}

fun View.visible(){
    this.visibility = View.VISIBLE
}
fun View.gone(){
    this.visibility = View.GONE
}
