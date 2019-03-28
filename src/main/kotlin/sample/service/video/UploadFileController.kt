package sample.service.video

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile
import sample.service.video.filestorage.FileStorage

@Controller
class UploadFileController {

    @Autowired
    lateinit var fileStorage: FileStorage

    @GetMapping("/getfiles")
    fun index(): String {
        return "multipartfile/uploadform.html"
    }

    @PostMapping("/uploadfile")
    fun uploadMultipartFile(@RequestParam("uploadfile") file: MultipartFile, model: Model): String {
        fileStorage.store(file)
        model.addAttribute("message", "File uploaded successfully! -> filename = " + file.originalFilename)
        return "multipartfile/uploadform.html"
    }
}