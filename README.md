# README

This is just a simple image viewer written in java, where you select a folder with your images and it cycles between them endlessly.

## Prerequisites

- Java must be installed on your computer.
- The images you want to display must be the same resolution as the display you are displaying them on.
- The JAR-file must be allowed to execute

## Usage

This program is pretty simple to use.

1. Copy all the images you want to display into one folder.
2. Execute the jar-file. The jar-file can be downloaded here: https://github.com/AlphaLEXray/Imageviewer/releases/download/v0.2.0/Imageviewer-v0.2.0.jar
3. Select the folder with the images in the built in chooser.
4. Enjoy your images being displayed one after the other.

### Commands

Seperate the folder name and the commands with the "|" charachter.

- image resolution &rarr; ir|width|height
- frame resolution &rarr; fr|width|height
- frame and image resolution &rarr; fir|width|height
- loop time (time spent on each image) &rarr; lt|time (in milliseconds)

**Example**:

To set the image and frame resolution to 1920x1080 pixels and a loop time of 2 seconds (2000 milliseconds):

> foldername|fir|1920|1080|lt|2000 