-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class com.android.vending.licensing.ILicensingService
-keep public class android.telephony.CellLocation
-keep public class android.view.View
-dontwarn android.**

# retrofit2的混淆
-dontnote retrofit2.Platform
-dontnote retrofit2.Platform$IOS$MainThreadExecutor
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions

-dontwarn okio.**

-dontwarn retrofit2.adapter.rxjava.CompletableHelper$**

-dontwarn retrofit2.adapter.rxjava.CompletableHelper$CompletableCallOnSubscribe

-dontwarn retrofit2.adapter.rxjava2.**
-dontwarn io.reactivex.**

-keep @cn.mwee.android.upgrade.common.NotProguard class * {
     <fields>;
}

-keepparameternames
-renamesourcefileattribute SourceFile
-keepattributes Exceptions,InnerClasses,Signature,Deprecated,SourceFile,LineNumberTable,*Annotation*,EnclosingMethod

-keepclasseswithmembernames,includedescriptorclasses class * {
    native <methods>;
}

-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers,allowoptimization enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

################################################
# javascript callback
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}
-keepattributes JavascriptInterface

-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
#butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

-dontwarn com.novell.**
-keep class com.novell.**{*;}
-dontwarn com.google.**
-keep class com.google.**{*;}
-dontwarn com.baidu.**
-keep class com.baidu.**{*;}
-dontwarn com.alipay.**
-keep class com.alipay.**{*;}
-dontwarn ta.utdid2.**
-keep class ta.utdid2.**{*;}
-dontwarn ut.device.**
-keep class ut.device.**{*;}
-dontwarn com.easemob.**
-keep class com.easemob.**{*;}
-dontwarn com.alibaba.**
-keep class com.alibaba.**{*;}
-dontwarn com.tencent.**
-keep class com.tencent.**{*;}
-dontwarn com.qiniu.**
-keep class com.qiniu.**{*;}
-dontwarn com.lidroid.**
-keep class com.lidroid.**{*;}
-dontwarn de.measite.**
-keep class de.measite.**{*;}
-dontwarn internal.org.**
-keep class internal.org.**{*;}
-dontwarn org.apache.**
-keep class org.apache.**{*;}
-dontwarn org.jivesoftware.**
-keep class org.jivesoftware.**{*;}
-dontwarn org.xbill.**
-keep class org.xbill.**{*;}
-dontwarn com.hp.**
-keep class com.hp.**{*;}
-dontwarn net.sourceforge.**
-keep class net.sourceforge.**{*;}
-dontwarn com.jakewharton.**
-keep class com.jakewharton.**{*;}
-dontwarn butterknife.internal.**
-keep class butterknife.internal.**{*;}

-dontwarn com.loopj.**
-keep class com.loopj.**{*;}
-dontwarn com.umeng.**
-keep class com.umeng.**{*;}
-dontwarn demo.**
-keep class demo.**{*;}

-keep class com.jiechic.library.**{*;}
-dontwarn com.jiechic.library.**

-keep class com.android.print.**{*;}
-dontwarn com.android.print.**

-keep class com.slidingmenu.lib.**{*;}
-dontwarn com.slidingmenu.lib.**

-keep class android_serialport_api.**{*;}
-dontwarn android_serialport_api.**

-keep class android_serialport_gt.**{*;}
-dontwarn android_serialport_gt.**

-keep class cn.com.geartech.**{*;}
-dontwarn cn.com.geartech.**

-keep class cn.weipass.**{*;}
-dontwarn cn.weipass.**

-keep class cn.shellinfo.wall.**{*;}
-dontwarn cn.shellinfo.wall.**

-keep class com.posin.device.**{*;}
-dontwarn com.posin.device.**

-keep class com.smartqueue.jni.**{*;}
-dontwarn com.smartqueue.jni.**
-keep class sun.misc.Unsafe { *; }


-keepclassmembers class * {
    static final %                *;
    static final java.lang.String *;
}

# -----------------
#  ASM
# -----------------
-keep class org.objectweb.asm.**
-dontwarn org.objectweb.asm.**
-keepclassmembers class * {
     @com.google.inject.Inject <fields>;
     @com.google.inject.Inject <init>(...);
}

#
#Umeng
#
-keepclassmembers class * {
   public <init>(org.json.JSONObject);
}


-keep class **.Finalizer
-keepclassmembers class ** { *** startFinalizer( ... ); }
# There's no way to keep all @Observes methods, so use the On*Event convention to identify event handlers
-keepclassmembers class * {
    void *(**On*Event);
    public void onEvent*(**);
}

-keep public class * extends com.google.inject.AbstractModule
-keep public class !roboguice.activity.RoboMapActivity, roboguice.**

# -----------------
#  RoboSpice
# -----------------

#RoboSpice requests should be preserved in most cases
-keepclassmembers class com.jaspersoft.android.sdk.client.async.request.** {
  public void set*(***);
  public *** get*();
  public *** is*();
}

#Warnings to be removed. Otherwise maven plugin stops, but not dangerous
-dontwarn com.octo.android.robospice.persistence.**


# -----------------
#  CGLib
# -----------------

-dontwarn net.sf.cglib.beans.*
-dontwarn net.sf.cglib.core.*
-dontwarn net.sf.cglib.transform.*

-dontwarn javax.annotation.**

# -----------------
#  Gson
# -----------------
-dontwarn com.google.gson.**
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.** { *; }

-keep public class * implements java.io.Serializable {*;}


#---------easyAdapter----------#
-keepclassmembers class * extends uk.co.ribot.easyadapter.ItemViewHolder {
    public <init>(...);
 }


# -----------------
#  RoboGuice
# -----------------

-dontwarn roboguice.activity.RoboMapActivity
-dontwarn roboguice.test.RoboActivityUnitTestCase
-dontwarn roboguice.test.RoboUnitTestCase
-dontwarn roboguice.test.RobolectricRoboTestRunner
-dontwarn roboguice.test.shadow.ShadowFragment
-dontwarn roboguice.test.shadow.ShadowFragmentActivity

# ------ 保护 sharesdk jar包 --------
-keep class cn.sharesdk.** { *; }
-dontwarn cn.sharesdk.**

# ------ 保护 picasso jar包 --------
-keep class com.squareup.** { *; }
-dontwarn com.squareup.**

# -----保护jni
-keep public class com.smartque.jni.** { public *; }
-keep public class com.cloudpos.apidemo.jniinterface.** { public *; }
-keep class javax.inject.** { *; }
-keep class javax.annotation.** { *; }

# My application classes used by injection framework
-keep class com.myapp.RoboGuiceModule { *; }
-keep class com.myapp.AppProvider { *; }
-keep class com.myapp.MyInjectableSingletonExample { *; }
# ... other classes that are referenced in my custom RoboGuiceModule.configure() bindings
-keep class roboguice.** { *; }

# if not using Google Maps library, safe to ignore these
-dontwarn roboguice.activity.RoboMapActivity
# safe to ignore testing classes, when proguard not being run against an instrumentation testing app
-dontwarn roboguice.test.**

-keep public class com.slidingmenu.lib.R$*{
    public static final int *;
}

-keep class com.smartqueue.member.entity.** { *; }
-dontwarn com.smartqueue.member.entity.**
-keep class com.wizarpos.paymentrouter.aidl.** { *; }
-dontwarn com.wizarpos.paymentrouter.aidl.**
-keep class com.mw.common.http.net.** { *; }
-dontwarn com.mw.common.http.net.**

# support-v4
#https://stackoverflow.com/questions/18978706/obfuscate-android-support-v7-widget-gridlayout-issue
-dontwarn android.support.v4.**
-keep class android.support.v4.app.** { *; }
-keep interface android.support.v4.app.** { *; }
-keep class android.support.v4.** { *; }

# support-v7
-dontwarn android.support.v7.**
-keep class android.support.v7.internal.** { *; }
-keep interface android.support.v7.internal.** { *; }
-keep class android.support.v7.** { *; }

# support design
-dontwarn android.support.design.**
-keep class android.support.design.** { *; }
-keep interface android.support.design.** { *; }
-keep public class android.support.design.R$* { *; }

-keep class org.dom4j.** { *; }
-dontwarn org.dom4j.**

-keep class org.jaxen.** { *; }
-dontwarn org.jaxen.**

-keep class org.w3c.dom.** { *; }
-dontwarn org.w3c.dom.**

-keep class Decoder.** { *; }
-dontwarn Decoder.**

-keep class com.ys.serviceapi.** { *; }
-dontwarn com.ys.serviceapi.**

-keep class com.ysepay.pos.deviceservice.aidl.** { *; }
-dontwarn com.ysepay.pos.deviceservice.aidl.**

# RxJava RxAndroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

-keep class android.** { *; }
-dontwarn android.**

# DroidPlugin
-keep class com.morgoo.** { *; }
-dontwarn com.morgoo.**

#基线包使用，生成mapping.txt
-printmapping mapping.txt
#生成的mapping.txt在app/buidl/outputs/mapping/release路径下，移动到/app路径下
#修复后的项目使用，保证混淆结果一致
#-applymapping mapping.txt
#hotfix
-keep class com.taobao.sophix.**{*;}
-keep class com.ta.utdid2.device.**{*;}
#防止inline
-dontoptimize