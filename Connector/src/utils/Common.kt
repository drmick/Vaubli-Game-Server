package utils

import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.FileReader
import java.io.IOException
import java.util.*


/**
 * Created by Maks on 20.04.2019.
 */

object Common {
    fun readFile(filename: String): String {
        val reader: BufferedReader
        val sb = StringBuilder()

        try {
            reader = BufferedReader(FileReader(filename))
            var line: String? = reader.readLine()
            while (line != null) {
                line = reader.readLine()
                sb.append(line)
            }
            reader.close()
            return sb.toString()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return ""
    }


    fun getResourceFile(resource : String): String {
        val scanner = Scanner(javaClass.getResourceAsStream(resource), "UTF-8")
        val sb = StringBuilder()
        try {
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine())
            }
            scanner.close()
            return sb.toString()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        return ""
    }

}