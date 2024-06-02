package com.github.Ringoame196.managers

class GoogleDriveManager {
    fun checkGoogleLinkDriveURL(url: String): Boolean {
        val googleDriveURL = "https://drive.google.com/file/"
        return url.contains(googleDriveURL)
    }
    fun makeDownloadURL(id: String): String {
        // ダウンロードリンクを作る
        val downloadURL = "https://drive.google.com/uc?export=download&id=$id"
        return downloadURL
    }
    fun cutID(url: String): String {
        // 共有リンクからIDを抜き出す
        val l = url.indexOf("/d/") + 3
        val r = url.indexOf("/view")
        return url.substring(l, r)
    }
}
