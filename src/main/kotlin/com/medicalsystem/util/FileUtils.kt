package com.medicalsystem.util

import org.springframework.web.multipart.MultipartFile
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object FileUtils {

    fun saveFile(file: MultipartFile, path: String) {
        if (file.isEmpty)
            throw IOException("File is empty")

        BufferedOutputStream(FileOutputStream(File(path))).use {
            it.write(file.bytes)
            logger().info("File saved to '$path'")
        }
    }
}