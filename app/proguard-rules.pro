# Add project specific ProGuard rules here.
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes Exceptions
-keep class com.redmi14c.optimizer.data.** { *; }
-keep class com.redmi14c.optimizer.shizuku.** { *; }
-keep class rikka.shizuku.** { *; }
-dontwarn rikka.shizuku.**
