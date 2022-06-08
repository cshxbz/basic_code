package com.hxb.basicframework.sample

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.concurrent.Callable
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors
import java.util.concurrent.Future

@RequiresApi(Build.VERSION_CODES.N)
class AsyncProgramSample {

    private val urlList = listOf("a", "b", "c", "d")

    fun getImages() {
        urlList.map {
            getImageFuture(it)
        }.map {
            val mockImage = it.get()
            println(">>>>>  $mockImage")
            mockImage
        }
    }

    fun getImages2() {
        urlList.map {
            getImageCompletableFuture(it)
        }.let { futureList ->
            CompletableFuture.allOf(*futureList.toTypedArray())
                    .thenApply {
                        futureList.map {
                            val mockImage = it.get()
                            println(">>>>>  $mockImage")
                            mockImage
                        }
                    }
        }.thenAccept { mockImage ->
            
        }
    }

    private fun getImageCompletableFuture(url: String): CompletableFuture<MockImage> {
        return CompletableFuture.supplyAsync {
            Thread.sleep(1000)
            return@supplyAsync MockImage(url)
        }
    }

    private fun getImageFuture(url: String): Future<MockImage> {
        val executor = Executors.newFixedThreadPool(100)
        return executor.submit(Callable {
            Thread.sleep(1000)
            return@Callable MockImage(url)
        })
    }


    data class MockImage(var data: String?)

}