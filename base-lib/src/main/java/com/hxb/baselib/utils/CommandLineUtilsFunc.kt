package com.hxb.baselib.utils

import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader

/**
 * 执行root命名，并返回命令的结果
 */
fun exeSuCommand(command: String): String {
    var es: BufferedReader? = null
    var os: DataOutputStream? = null
    val resultSb = StringBuilder()
    try {
        val process = Runtime.getRuntime().exec("su")
        os = DataOutputStream(process.outputStream)
        os.writeBytes("$command\n")
        os.writeBytes("exit\n")
        os.flush()
        es = BufferedReader(InputStreamReader(process.inputStream))
        var line: String?
        while (es.readLine().also { line = it } != null) {
            resultSb.append(line)
        }
        process.waitFor()
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        try {
            os?.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        try {
            es?.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    return resultSb.toString()
}

