package com.example.musicinkotlin.service.musicService

import android.net.Uri
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import java.net.URI

/**
 * Created by john on 08/12/2017.
 */
class MusicInfo() : Parcelable {

    val KEY_NAME = "name"
    val KEY_URI = "uri"

    var name: String? = null
    var uri: Uri? = null

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        val bundle = Bundle()
        bundle.putString(KEY_NAME, this.name)
        bundle.putParcelable(KEY_URI, this.uri)
        dest?.writeBundle(bundle)
    }

    override fun describeContents(): Int {
        return 0
    }

    constructor(parcel: Parcel) : this() {
        val bundle = parcel.readBundle(javaClass.classLoader)
        this.name = bundle.getString(KEY_NAME)
        this.uri = bundle.getParcelable(KEY_URI)
    }

    constructor(name: String, uri: Uri): this(){
        this.name = name
        this.uri = uri
    }

    companion object CREATOR : Parcelable.Creator<MusicInfo> {
        override fun createFromParcel(parcel: Parcel): MusicInfo {
            return MusicInfo(parcel)
        }

        override fun newArray(size: Int): Array<MusicInfo?> {
            return arrayOfNulls(size)
        }
    }
}