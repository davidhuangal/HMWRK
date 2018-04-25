# HMWRK Dev Guide
Just some useful information for developing this.

* When making a new FXML document make sure that its controller class extends Switchable.
* When switching scenes, just create a function similar to this:

```java
@FXML
void handleGoToAbout(ActionEvent event) {
  Switchable.switchTo("AboutPage");
}
```

where there is "AboutPage" replace with whatever the name of the FXML file you want to change to, e.g. Switchable.switchTo("HomePage") if you have a HomePage.fxml

* We are using a library called JFoenix which is just JavaFX with Google's Material Design. When using Scenebuilder, in the library it will be under the "Custom" dropdown and everything should work the same, it just looks pretty.
