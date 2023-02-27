# ds-ptde-pc-saveviewer

A dark souls file viewer, and chaacter import/Export
the goal of this fork is to port the code to more modern versions of java, and better support save files for the  non-PTDE edition of the game, (the one using Games For Windows Live).

This fork will be based on Java **17**, you'll java of this version to run the jar correctly once i make it.

## How to get this project to run locally for development (on Arch Linux)
**Without Maven**
- Install a bunch of dependencies: ` pacman -S jdk17-openjdk jre17-openjdk openjdk17-doc openjdk17-src java17-openjfx java17-openjfx-doc java17-openjfx-src`
- (If you already installed a different java version) Set the default java environment to the new version: `archlinux-java set java-17-openjdk`
- Paste this line under VM Arguments in your run configuration: `--module-path /usr/lib/jvm/default/lib/ --add-modules javafx.controls,javafx.fxml` If you get an error about some conflict, point the first argument to a list of individual jars by pasting in this

`--module-path /usr/lib/jvm/default/lib/javafx.controls.jar:/usr/lib/jvm/default/lib/javafx.fxml.jar:/usr/lib/jvm/default/lib/javafx.graphics.jar:/usr/lib/jvm/default/lib/javafx.base.jar --add-modules javafx.controls,javafx.fxml`

This process implied that you need the JavaFX libraries on the host system, and its not enough to import them through maven

**With Maven**
Create a new Maven run configuration, set goal to `javafx:run`.
You will notice, if you log bedug output, that the javafx jars from maven are used.
Im not sure if you even need the javafx package installed on arch with this, probably not
