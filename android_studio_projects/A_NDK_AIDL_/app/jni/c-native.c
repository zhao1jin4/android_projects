//#include "org_zhaojin_ndk_basic_NativeClass.h"//ͷ�ļ�����Ҳ����,��Ҳ��
#include <jni.h>

JNIEXPORT jint JNICALL Java_org_zhaojin_ndk_basic_NativeClass_max
  (JNIEnv * env, jobject obj, jint a, jint b, jint c)
{
	jint t;
	if(a>b)
		t=a;
	else
		t=b;

	if(b>c)
		t=b;
	else
		t=c;
	return t;
}
