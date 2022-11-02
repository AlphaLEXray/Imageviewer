# README

This is just a simple image viewer written in java, where you select a folder with your images and it cycles between them endlessly.

## Prerequisites

- Java must be installed on your computer.
- The images you want to display must be the same resolution as the display you are displaying them on.
- The JAR-file must be allowed to execute

## Usage

This program is pretty simple to use.

1. Copy all the images you want to display into one folder.
2. Execute the jar-file. The jar-file can be downloaded here: https://github.com/AlphaLEXray/Imageviewer/releases/download/v0.3.0/Imageviewer-v0.3.0.jar
3. Select the folder with the images in the built in chooser.
4. Enjoy your images being displayed one after the other.

## Commands

Seperate the folder name and the commands with the "|" charachter.

- image resolution &rarr; ir|width|height
- frame resolution &rarr; fr|width|height
- frame and image resolution &rarr; fir|width|height
- loop time (time spent on each image) &rarr; lt|time (in milliseconds)
- force fullscreen (windowed fullscreen) &rarr; ff
- automatic cycling mode &rarr; loop
- manual cycling mode &rarr; !loop

Automatic cycling mode is the default at the moment.

**Example**:

To set the image and frame resolution to 1920x1080 pixels and a loop time of 2 seconds (2000 milliseconds):

> foldername|fir|1920|1080|lt|2000

## Modes

1. Automatic cycling mode
2. Manual cycling mode

### Automatic cycling mode

This mode automatically cycles through the images at a given rate. 

**cycling/loop time**:

This rate can be set at launch through a command in the folder name or by using keys 1 to 9 on the number row. Key '0' sets the cycling time back to the default or the amount of time set in the commands. To pause the automatic cycling press the space bar.

**windowed/ windowed fullscreen**:

Use the escape key to exit the windowed fullscreen. The 'f' key can be used to switch between windowed or windowed fullscreen depending on which is currently selected.

### Manual cycling mode

**cycling through images**:

To cycle through the images in a folder you can either use the 'a' and 'd' keys to cycle forwards and backwards respectively or you can use the back- and forward arrow keys.
