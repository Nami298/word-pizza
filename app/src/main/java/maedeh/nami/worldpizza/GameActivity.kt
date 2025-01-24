package maedeh.nami.worldpizza

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
class GameActivity : AppCompatActivity() {
    private lateinit var validWordTextView: TextView
    private val selectedLetters = StringBuilder() // ذخیره حروف انتخابی کاربر
    private lateinit var levelWords: List<String> // کلمات معتبر سطح
    private var currentLevel: LevelsModel? = null // ذخیره مرحله فعلی

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.letter_item)

        validWordTextView = findViewById(R.id.validWord)
        val lettersContainer = findViewById<ConstraintLayout>(R.id.lettersContainer)

        // دریافت اطلاعات مرحله از Intent
        currentLevel = intent.getParcelableExtra("level_data")
        currentLevel?.let { level ->
            levelWords = level.validWords

            // حروف را به صورت دایره‌ای داخل ConstraintLayout اضافه می‌کنیم
            addLettersToContainer(level.letters, lettersContainer)
        } ?: run {
            Toast.makeText(this, "خطا در بارگذاری مرحله!", Toast.LENGTH_SHORT).show()
            finish() // بازگشت به صفحه قبلی در صورت خطا
        }
    }

    private fun addLettersToContainer(letters: List<Char>, container: ConstraintLayout) {
        for ((index, letter) in letters.withIndex()) {
            val textView = TextView(this).apply {
                text = letter.toString()
                textSize = 18f
                setTextColor(resources.getColor(android.R.color.black, theme))
                gravity = Gravity.CENTER
                background = resources.getDrawable(R.drawable.level_bg, theme)
                layoutParams = ConstraintLayout.LayoutParams(150, 150).apply {
                    // تنظیم موقعیت برای نمایش دایره‌ای
                    circleConstraint = container.id
                    circleRadius = 300
                    circleAngle = (index * (360 / letters.size)).toFloat()
                }

                // استفاده از setOnClickListener
                setOnClickListener {
                    handleLetterSelection(letter)
                }
            }
            container.addView(textView)
        }
    }

    private fun handleLetterSelection(letter: Char) {
        // اضافه کردن حرف انتخاب‌شده
        selectedLetters.append(letter)
        validWordTextView.text = selectedLetters.toString()

        // بررسی اینکه آیا کلمه انتخاب‌شده معتبر است
        if (levelWords.contains(selectedLetters.toString())) {
            Toast.makeText(this, "موفق شدی!", Toast.LENGTH_SHORT).show()
            unlockNextLevel() // باز کردن قفل مرحله بعدی
            goToNextLevel() // رفتن به مرحله بعدی
        }
    }

    private fun unlockNextLevel() {
        currentLevel?.let { level ->
            val nextLevelId = level.id + 1 // محاسبه شماره مرحله بعدی
            // ذخیره وضعیت باز بودن مرحله بعدی در SharedPreferences
            val sharedPreferences = getSharedPreferences("game_data", MODE_PRIVATE)
            sharedPreferences.edit().apply {
                putBoolean("level_${nextLevelId}_unlocked", true)
                apply()
            }
            Toast.makeText(this, "مرحله ${nextLevelId} باز شد!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun goToNextLevel() {
        // باز کردن مرحله بعدی
        val nextLevel = getNextLevel()
        if (nextLevel != null) {
            val nextLevelIntent = Intent(this, GameActivity::class.java)
            nextLevelIntent.putExtra("level_data", nextLevel)
            startActivity(nextLevelIntent)
            finish() // پایان فعالیت فعلی
        } else {
            Toast.makeText(this, "مرحله بعدی وجود ندارد!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getNextLevel(): LevelsModel? {
        // لیستی از مراحل را به عنوان داده‌های ثابت تعریف می‌کنیم
        val levels = listOf(
            LevelsModel(1, listOf('م', 'د', 'ا', 'د'), listOf("داد", "دام", "مداد", "دما"), false),
            LevelsModel(2, listOf('گ', 'ر', 'گ', 'م'), listOf("گرگ", "گرم", "مرگ", "رگ"), true),
            LevelsModel(3, listOf('ش','ب', 'ر', 'ت'), listOf("شب", "شر", "بر", "تر"), true),
            LevelsModel(4, listOf('ن', 'ی', 'م', 'ر'), listOf("نیم", "میر", "رم", "رین"), true),
            LevelsModel(5, listOf('پ', 'س', 'ت', 'و'), listOf("پست", "تو", "سپ", "ستو"), true),
            LevelsModel(6, listOf('ق', 'ص', 'م', 'ه'), listOf("قصه", "قم", "مه"), true),
            LevelsModel(7, listOf('ر', 'ص', 'د', 'ا'), listOf("رصد", "دار", "ردا"), true),
            LevelsModel(8, listOf('گ', 'ر', 'ح', 'م'), listOf("گرم", "حجر", "رگ", "محجر"), true),
            LevelsModel(9, listOf('م', 'ل', 'ق', 'ت'), listOf("ملق", "لقم", "قل", "تلق"), true),
            LevelsModel(10, listOf('ک', 'س', 'ا', 'ر'), listOf("ساک", "رک", "کار", "ساکر"), true),
            LevelsModel(11, listOf('گ', 'ق', 'م', 'ر'), listOf("رگ", "قرم", "مقر", "قر"), true),
            LevelsModel(12, listOf('د', 'ت', 'ا', 'ق'), listOf("تاد", "دق", "قد", "تق"), true),
            LevelsModel(13, listOf('س', 'ا', 'ل', 'ح'), listOf("سال", "سلاح", "لاح", "سال"), true),
            LevelsModel(14, listOf('ک', 'ح', 'م', 'ش'), listOf("شکم", "حکم", "شم", "کم"), true),
            LevelsModel(15, listOf('ر', 'ش', 'د', 'ا'), listOf("درا", "شرا", "شرد", "دراش"), true),
            LevelsModel(16, listOf('ل', 'ق', 'س', 'ت'), listOf("سلق", "تقل", "لق", "قل"), true),
            LevelsModel(17, listOf('م', 'گ', 'ر', 'ش'), listOf("مرگ", "گرم", "رگ", "شمر"), true),
            LevelsModel(18, listOf('ک', 'س', 'د', 'ح'), listOf("سک", "کد", "سح", "کسد"), true),
            LevelsModel(19, listOf('گ', 'ر', 'م', 'ت'), listOf("گرم", "رگ", "تم", "گرت"), true),
            LevelsModel(20, listOf('ح', 'ل', 'س', 'ک'), listOf("لحک", "سک", "کل", "سلاح"), true)
        )

        // پیدا کردن مرحله فعلی
        val currentLevelIndex = levels.indexOfFirst { it.id == currentLevel?.id }
        return if (currentLevelIndex != -1 && currentLevelIndex + 1 < levels.size) {
            // بازگرداندن مرحله بعدی
            levels[currentLevelIndex + 1].copy(isLocked = false) // باز کردن قفل مرحله
        } else {
            // اگر مرحله بعدی وجود ندارد، مقدار null برگردانید
            null
        }
    }
}