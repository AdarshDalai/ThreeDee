@echo off
"C:\\Users\\adars\\AppData\\Local\\Android\\Sdk\\ndk\\24.0.8215888\\ndk-build.cmd" ^
  "NDK_PROJECT_PATH=null" ^
  "APP_BUILD_SCRIPT=C:\\dev\\panoramaGL\\library\\src\\main\\jni\\Android.mk" ^
  "NDK_APPLICATION_MK=C:\\dev\\panoramaGL\\library\\src\\main\\jni\\Application.mk" ^
  "APP_ABI=x86" ^
  "NDK_ALL_ABIS=x86" ^
  "NDK_DEBUG=1" ^
  "APP_PLATFORM=android-21" ^
  "NDK_OUT=C:\\dev\\panoramaGL\\library\\build\\intermediates\\cxx\\Debug\\374w1274/obj" ^
  "NDK_LIBS_OUT=C:\\dev\\panoramaGL\\library\\build\\intermediates\\cxx\\Debug\\374w1274/lib" ^
  "APP_SHORT_COMMANDS=false" ^
  "LOCAL_SHORT_COMMANDS=false" ^
  -B ^
  -n
