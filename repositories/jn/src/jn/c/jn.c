#include <jni.h>
#include <stdio.h>

int fibonacci(int n) {
    if(n <= 0) {
        return 0;
    }
    if(n == 1||n == 2){
        return 1;
    }
    return fibonacci(n-1) + fibonacci(n-2);
}

int fibonacciJna(int n) {
    return fibonacci(n);
}

JNIEXPORT jint JNICALL Java_jn_Jn_fibonacciJni
  (JNIEnv *env, jobject jo, jint n){
    return fibonacci(n);
}
