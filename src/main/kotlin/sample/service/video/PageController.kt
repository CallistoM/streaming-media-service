package sample.service.video

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.streams.toList

@RestController
class PageController(@Value("\${video.location}") val videoLocation: String) {

    @GetMapping("/")
    fun index(model: Model): String {
        val videos = Files.list(Paths.get(videoLocation)).map { it.fileName.toString() }.toList()
        model.addAttribute("videos", videos)
        return "index"
    }

    @GetMapping("/{videoName}")
    fun video(@PathVariable videoName: String, model: Model): String {
        model.addAttribute("videoName", videoName)
        return "video"
    }

}
