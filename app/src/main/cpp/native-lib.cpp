//
// Created by Ashutosh on 23/01/23.
//
#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_ashu_ocotopus_util_Keys_webClientKey(JNIEnv *env, jobject thiz) {
    std::string web_client_key = "482515387278-0i3ckv6orr20fqn6lrqjqt5lnlrrgl9j.apps.googleusercontent.com";
    return env->NewStringUTF(web_client_key.c_str());
}