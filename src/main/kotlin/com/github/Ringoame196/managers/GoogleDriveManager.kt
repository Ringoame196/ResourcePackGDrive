package com.github.Ringoame196.managers

class GoogleDriveManager {
    fun checkGoogleDriveURL(url: String): Boolean {
        val googleDriveURL = "https://drive.google.com/file/"
        return url.contains(googleDriveURL) && url.contains("usp=drive_link")
    }
    fun conversionDownloadURL(linkURL: String): String {
        val id = cutID(linkURL)
        val downloadURL = "https://drive.google.com/uc?export=download&id=$id"
        return downloadURL
    }
    private fun cutID(url: String): String {
        val l = url.indexOf("/d/") + 3
        val r = url.indexOf("/view")
        return url.substring(l, r)
    }
}
