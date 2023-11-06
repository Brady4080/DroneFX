@echo off
curl -L -o lib\javacv-1.5.7.jar https://repo1.maven.org/maven2/org/bytedeco/javacv/1.5.7/javacv-1.5.7.jar
curl -L -o lib\javacpp-1.5.7.jar https://repo1.maven.org/maven2/org/bytedeco/javacpp/1.5.7/javacpp-1.5.7.jar
curl -L -o lib\openblas-0.3.19-1.5.7.jar https://repo1.maven.org/maven2/org/bytedeco/openblas/1.5.7/openblas-0.3.19-1.5.7.jar
curl -L -o lib\opencv-4.5.5-1.5.7.jar https://repo1.maven.org/maven2/org/bytedeco/opencv/1.5.7/opencv-4.5.5-1.5.7.jar
curl -L -o lib\ffmpeg-5.0-1.5.7.jar https://repo1.maven.org/maven2/org/bytedeco/ffmpeg/1.5.7/ffmpeg-5.0-1.5.7.jar
curl -L -o lib\flycapture-2.13.3.31-1.5.7.jar https://repo1.maven.org/maven2/org/bytedeco/flycapture/1.5.7/flycapture-2.13.3.31-1.5.7.jar
curl -L -o lib\libdc1394-2.2.6-1.5.7.jar https://repo1.maven.org/maven2/org/bytedeco/libdc1394/1.5.7/libdc1394-2.2.6-1.5.7.jar
curl -L -o lib\libfreenect-0.5.7-1.5.7.jar https://repo1.maven.org/maven2/org/bytedeco/libfreenect/1.5.7/libfreenect-0.5.7-1.5.7.jar
curl -L -o lib\libfreenect2-0.2.0-1.5.7.jar https://repo1.maven.org/maven2/org/bytedeco/libfreenect2/1.5.7/libfreenect2-0.2.0-1.5.7.jar
curl -L -o lib\librealsense-1.12.4-1.5.7.jar https://repo1.maven.org/maven2/org/bytedeco/librealsense/1.5.7/librealsense-1.12.4-1.5.7.jar
curl -L -o lib\librealsense2-2.50.0-1.5.7.jar https://repo1.maven.org/maven2/org/bytedeco/librealsense2/1.5.7/librealsense2-2.50.0-1.5.7.jar
curl -L -o lib\videoinput-0.200-1.5.7.jar https://repo1.maven.org/maven2/org/bytedeco/videoinput/1.5.7/videoinput-0.200-1.5.7.jar
curl -L -o lib\artoolkitplus-2.3.1-1.5.7.jar https://repo1.maven.org/maven2/org/bytedeco/artoolkitplus/1.5.7/artoolkitplus-2.3.1-1.5.7.jar
curl -L -o lib\flandmark-1.07-1.5.7.jar https://repo1.maven.org/maven2/org/bytedeco/flandmark/1.5.7/flandmark-1.07-1.5.7.jar
curl -L -o lib\leptonica-1.82.0-1.5.7.jar https://repo1.maven.org/maven2/org/bytedeco/leptonica/1.5.7/leptonica-1.82.0-1.5.7.jar
curl -L -o lib\tesseract-5.0.1-1.5.7.jar https://repo1.maven.org/maven2/org/bytedeco/tesseract/1.5.7/tesseract-5.0.1-1.5.7.jar

set CLASSPATH=%CLASSPATH%;lib/*

java --module-path lib\javafx-sdk-20.0.2\lib --add-modules javafx.controls,javafx.fxml -jar FarmDashboard\JarLauncher\FarmDashboard.jar
