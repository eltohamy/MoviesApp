package com.movies.data.models.movieslist

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Tohamy on 29/09/2019
 */
data class Movie(
    val popularity: Double,
    val vote_count: Int,
    val video: Boolean,
    val poster_path: String,
    val id: Int,
    val adult: Boolean,
    val backdrop_path: String,
    val original_language: String,
    val original_title: String,
    val genre_ids: List<String>,
    val title: String,
    val vote_average: Double,
    val overview: String,
    val release_date: String
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readDouble(),
        source.readInt(),
        1 == source.readInt(),
        source.readString()!!,
        source.readInt(),
        1 == source.readInt(),
        source.readString()!!,
        source.readString()!!,
        source.readString()!!,
        source.createStringArrayList()!!,
        source.readString()!!,
        source.readDouble(),
        source.readString()!!,
        source.readString()!!
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeDouble(popularity)
        writeInt(vote_count)
        writeInt((if (video) 1 else 0))
        writeString(poster_path)
        writeInt(id)
        writeInt((if (adult) 1 else 0))
        writeString(backdrop_path)
        writeString(original_language)
        writeString(original_title)
        writeStringList(genre_ids)
        writeString(title)
        writeDouble(vote_average)
        writeString(overview)
        writeString(release_date)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Movie> = object : Parcelable.Creator<Movie> {
            override fun createFromParcel(source: Parcel): Movie = Movie(source)
            override fun newArray(size: Int): Array<Movie?> = arrayOfNulls(size)
        }
    }
}