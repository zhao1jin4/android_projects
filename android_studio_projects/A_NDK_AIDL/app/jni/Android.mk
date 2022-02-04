LOCAL_PATH := $(call my-dir)
#,注释#必须是行中第一个字符Android.mk 必须以LOCAL_PATH开头,my-dir是NDK中已经定义的宏,返回当前目录,包括Android.mk文件的目录

include $(CLEAR_VARS)
#是NDK提供的变量,会消除很多  LOCAL_XXX 变量除了LOCAL_PATH

LOCAL_MODULE    := c-native
#模块名是唯一的,没有空格,如'foo'会生成'libfoo.so',如'libfoo',也是'libfoo.so'
LOCAL_SRC_FILES := c-native.c
#所有的C/C++ 源文件列表,不必加头文件,如果文件以 .cpp结尾,被认为C++文件,可以设置LOCAL_CPP_EXTENSION为 .cxx ,注意有.的

include $(BUILD_SHARED_LIBRARY)
#是NDK提供的变量,会读所有的LOCAL_XXX 变量,构建.so文件,BUILD_STATIC_LIBRARY生成静态