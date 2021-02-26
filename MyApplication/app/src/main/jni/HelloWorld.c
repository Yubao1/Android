#include<stdio.h>
#include<stdlib.h>
#include<jni.h>

jstring Java_com_example_administrator_jni_JNIC_callC(JNIEnv* eve,jobject object) {
   char* text = "你好，我在调用C语言方法";
   return (*eve)->NewStringUTF(eve,text);

}