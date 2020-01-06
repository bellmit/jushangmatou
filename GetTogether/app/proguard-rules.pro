# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
# ProGuard configurations for Bugtags
-keepattributes LineNumberTable,SourceFile

-keep class com.bugtags.library.** {*;}
-dontwarn com.bugtags.library.**

-keep class io.bugtags.** {*;}
-dontwarn io.bugtags.**

-dontwarn org.apache.http.**
-dontwarn android.net.http.AndroidHttpClient
# End Bugtags

-ignorewarnings
-libraryjars libs/ YoudaoBase.jar
-libraryjars libs/ YoudaoCnDictOffline.jar
-libraryjars libs/ YoudaoTranslateOnline.jar
-libraryjars libs/ YoudaoTranslateOffline.jar

-keep class com.youdao.sdk.ydtranslate.** { *;}
-keep class com.youdao.sdk.chdict.** { *;}
-keep class com.youdao.localtransengine.** { *;}
-keep class com.youdao.sdk.ydonlinetranslate.** { *;}

-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}

-dontwarn io.rong.push.**
 -dontnote com.xiaomi.**
-keep public class com.google.firebase.* {*;}
 -dontnote io.rong.**
-keep class com.tem.gettogether.rongyun.CharNotificationReceiver {*;}

-keepattributes Exceptions,InnerClasses

-keepattributes Signature

 //RongCloud SDK
-keep class io.rong.** {*;}
-keep class cn.rongcloud.** {*;}
-keep class * implements io.rong.imlib.model.MessageContent {*;}
-dontwarn io.rong.push.**
-dontnote com.xiaomi.**
-dontnote com.google.android.gms.gcm.**
-dontnote io.rong.**

 //VoIP
-keep class io.agora.rtc.** {*;}

//红包
-keep class com.google.gson.** { *; }
-keep class com.uuhelper.Application.** {*;}
-keep class net.sourceforge.zbar.** { *; }
-keep class com.google.android.gms.** { *; }
-keep class com.alipay.** {*;}
-keep class com.jrmf360.rylib.** {*;}

-ignorewarnings