LOCAL_PATH := $(call my-dir)
#,ע��#���������е�һ���ַ�Android.mk ������LOCAL_PATH��ͷ,my-dir��NDK���Ѿ�����ĺ�,���ص�ǰĿ¼,����Android.mk�ļ���Ŀ¼

include $(CLEAR_VARS)
#��NDK�ṩ�ı���,�������ܶ�  LOCAL_XXX ��������LOCAL_PATH

LOCAL_MODULE    := c-native
#ģ������Ψһ��,û�пո�,��'foo'������'libfoo.so',��'libfoo',Ҳ��'libfoo.so'
LOCAL_SRC_FILES := c-native.c
#���е�C/C++ Դ�ļ��б�,���ؼ�ͷ�ļ�,����ļ��� .cpp��β,����ΪC++�ļ�,��������LOCAL_CPP_EXTENSIONΪ .cxx ,ע����.��

include $(BUILD_SHARED_LIBRARY)
#��NDK�ṩ�ı���,������е�LOCAL_XXX ����,����.so�ļ�,BUILD_STATIC_LIBRARY���ɾ�̬