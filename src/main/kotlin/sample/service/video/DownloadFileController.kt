package sample.service.video

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder
import sample.service.video.filestorage.FileStorage
import java.util.stream.Collectors

@RestController
class DownloadFileController {
    @Autowired
    lateinit var fileStorage: FileStorage

    @RequestMapping("/jnsffjnsdfjkndsfjkndjkn")
    fun getListFiles(): List<FileInfo> {
        val fileInfos: List<FileInfo> = fileStorage.loadFiles().map { path ->
            FileInfo(path.fileName.toString(),
                    MvcUriComponentsBuilder.fromMethodName(DownloadFileController::class.java,
                            "downloadFile", path.fileName.toString()).build().toString())
        }
                .collect(Collectors.toList())

        return fileInfos
    }

    @GetMapping("/files/{filename}")
    fun downloadFile(@PathVariable filename: String): ResponseEntity<Resource> {
        val file = fileStorage.loadFile(filename)
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.filename + "\"")
                .body(file)
    }
}