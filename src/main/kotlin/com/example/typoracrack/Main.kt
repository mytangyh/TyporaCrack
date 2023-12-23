package com.example.typoracrack

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.VBox
import javafx.stage.DirectoryChooser
import javafx.stage.Stage
import java.io.File

class Main : Application() {
    override fun start(primaryStage: Stage) {

        val fxmlLoader = FXMLLoader(Main::class.java.getResource("main-view.fxml"))
        val scene = Scene(fxmlLoader.load(), 320.0, 240.0)
        primaryStage.title = "TyporaCrack!"
        primaryStage.scene = scene
        primaryStage.show()

    }
}

fun main() {
    Application.launch(Main::class.java)
}