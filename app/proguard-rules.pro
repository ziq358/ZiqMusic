# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/john/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-optimizationpasses 5
#-dontpreverify
#-dontobfuscate
-dontwarn javax.management.**
-dontwarn java.lang.management.**
-dontwarn android.net.**
-dontwarn org.apache.**
-dontwarn org.apache.**
#-dontwarn twitter4j.management.**
#-dontwarn com.parse.ParseApacheHttpClient