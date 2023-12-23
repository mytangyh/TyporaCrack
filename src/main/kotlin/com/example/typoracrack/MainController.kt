package com.example.typoracrack

import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.stage.DirectoryChooser
import java.io.File

class MainController {
    @FXML
    private lateinit var welcomeText: Label

    @FXML
    private fun onMainButtonClick() {
        val directoryChooser = DirectoryChooser()
        val selectedDirectory = directoryChooser.showDialog(null)
        if (selectedDirectory == null) {
            welcomeText.text = "没有选择文件夹"
            println("没有选择文件夹")
        } else {
            welcomeText.text = "破解完成！重启Typora即可！"
            editFiles(selectedDirectory.absolutePath)
        }
    }

    private fun editFiles(path: String) {
        val pathJs = "$path\\resources\\page-dist\\static\\js\\"
        val jsDirectory = File(pathJs)
        jsDirectory.walk().forEach {
            if (it.isFile && it.name.startsWith("LicenseIndex") && it.name.endsWith(".js")) {
                replaceInFile(it, "e.hasActivated=\"true\"==e.hasActivated,", "e.hasActivated=\"true\"==\"true\",")
            }
        }
        val pathHtml = "$path\\resources\\page-dist\\license.html"
        val licenseHtml = File(pathHtml)
        replaceInFile(
            licenseHtml,
            "</body></html>",
            "</body><script>window.onload=function(){setTimeout(()=>{window.close();},5);}</script></html>"
        )
        val pathJson = "$path\\resources\\locales\\zh-Hans.lproj\\Panel.json"
        val panelJson = File(pathJson)
        replaceInFile(panelJson, "\"UNREGISTERED\":\"未激活\"", "\"UNREGISTERED\":\" \"")
    }

    private fun replaceInFile(file: File, oldText: String, newText: String) {
        val content = file.readText()
        val newContent = content.replace(oldText, newText)
        file.writeText(newContent)
    }
}