import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner
import sample.service.video.Application

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [Application::class],
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KotlinDemoApplicationTests {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun checkVideoApiCall() {
        val result = testRestTemplate
                .getForEntity("/videos", String::class.java)

        assertNotNull(result)
        assertEquals(result?.statusCode, HttpStatus.OK)
        assertEquals(result?.body, "video")
    }


    @Test
    fun checkVideoApiUpload() {
        val result = testRestTemplate
                .getForEntity("/uploadfile", String::class.java)

        assertNotNull(result)
        assertEquals(result?.statusCode, HttpStatus.OK)
        assertEquals(result?.body, "video")
    }

    @Test
    fun checkVideosApiCall() {
        val result = testRestTemplate
                .getForEntity("/getfiles", String::class.java)

        assertNotNull(result)
        assertEquals(result?.statusCode, HttpStatus.OK)
        assertEquals(result?.body, "multipartfile/uploadform.html")
    }
}