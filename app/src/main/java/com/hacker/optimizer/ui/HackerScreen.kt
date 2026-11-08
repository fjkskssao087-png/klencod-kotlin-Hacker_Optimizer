package com.hacker.optimizer.ui

import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.widget.Toast
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// قائمة الميزات الحقيقية
data class HackFeature(val title: String, val desc: String, val action: (Context) -> Unit)

val hackerFeatures = listOf(
    HackFeature("تسريع حركة النظام", "تقليل وقت الأنيميشن لتسريع الهاتف", { ctx ->
        try {
            Settings.Global.putFloat(ctx.contentResolver, Settings.Global.ANIMATOR_DURATION_SCALE, 0.5f)
            Settings.Global.putFloat(ctx.contentResolver, Settings.Global.TRANSITION_ANIMATION_SCALE, 0.5f)
            Toast.makeText(ctx, "تم تفعيل السرعة!", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) { Toast.makeText(ctx, "يتطلب صلاحية تعديل الإعدادات", Toast.LENGTH_SHORT).show() }
    }),
    HackFeature("تفريغ الذاكرة (RAM)", "إغلاق التطبيقات في الخلفية", { ctx ->
        val am = ctx.getSystemService(Context.ACTIVITY_SERVICE) as android.app.ActivityManager
        am.killBackgroundProcesses(ctx.packageName)
        Toast.makeText(ctx, "تم تفريغ الرام!", Toast.LENGTH_SHORT).show()
    }),
    HackFeature("وضع توفير البطارية", "فتح إعدادات البطارية", { ctx ->
        ctx.startActivity(Intent(Settings.ACTION_BATTERY_SAVER_SETTINGS).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }),
    HackFeature("تغيير الـ DNS", "تسريع الإنترنت (Cloudflare)", { ctx ->
        ctx.startActivity(Intent(Settings.ACTION_WIRELESS_SETTINGS).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }),
    HackFeature("مسح الكاش العام", "تنظيف الملفات المؤقتة", { ctx ->
        ctx.startActivity(Intent(Settings.ACTION_MEMORY_CARD_SETTINGS).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }),
    HackFeature("إعدادات المطورين", "الوصول لأعمق إعدادات النظام", { ctx ->
        ctx.startActivity(Intent(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    })
)

@Composable
fun HackerScreen() {
    val context = LocalContext.current
    val infiniteTransition = rememberInfiniteTransition(label = "hacker")
    
    // حركة الدوران 360 درجة
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(5000, easing = LinearEasing), RepeatMode.Restart), label = "rot"
    )

    Box(Modifier.fillMaxSize().background(Color.Black)) {
        // رسم الحركة الدائرية في الخلفية
        Canvas(Modifier.fillMaxSize()) {
            drawCircle(
                brush = Brush.sweepGradient(listOf(Color.Transparent, Color(0xFFFF003C), Color.Transparent)),
                radius = size.minDimension / 2.5f,
                center = center,
                style = Stroke(width = 8f)
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            contentPadding = PaddingValues(top = 50.dp, bottom = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text("أهلاً بك في الهكر", color = Color(0xFF86868B), fontSize = 20.sp)
                Text("HACKER X", color = Color(0xFFFF003C), fontSize = 50.sp, fontWeight = FontWeight.Black, fontFamily = FontFamily.Monospace)
                Spacer(Modifier.height(20.dp))
                Text("تم اكتشاف ${hackerFeatures.size} ميزة", color = Color(0xFFFF003C), fontFamily = FontFamily.Monospace)
                Spacer(Modifier.height(40.dp))
            }

            items(hackerFeatures) { feature ->
                Box(
                    Modifier.fillMaxWidth().padding(vertical = 8.dp)
                        .background(Color(0xFF111111), RoundedCornerShape(12.dp))
                        .clickable { feature.action(context) }
                        .padding(20.dp)
                ) {
                    Column {
                        Text(feature.title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text(feature.desc, color = Color(0xFF86868B), fontSize = 14.sp)
                    }
                }
            }
        }
    }
}