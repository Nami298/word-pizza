package maedeh.nami.worldpizza

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LevelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerLevels)
        recyclerView.layoutManager = GridLayoutManager(this, 4)

        // لیست مراحل
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

        // به‌روزرسانی وضعیت قفل مراحل با SharedPreferences
        val updatedLevels = updateLevelsFromSharedPreferences(levels)

        // تنظیم آداپتر
        recyclerView.adapter = LevelsAdapter(updatedLevels) { selectedLevel ->
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("level_data", selectedLevel)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerLevels)

        // لیست مراحل جدید
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
            val updatedLevels = updateLevelsFromSharedPreferences(levels)

        // تنظیم آداپتر
        recyclerView.adapter = LevelsAdapter(updatedLevels) { selectedLevel ->
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("level_data", selectedLevel)
            startActivity(intent)
        }
    }

    private fun updateLevelsFromSharedPreferences(levels: List<LevelsModel>): List<LevelsModel> {
        val sharedPreferences = getSharedPreferences("game_data", MODE_PRIVATE)
        return levels.map { level ->
            val isUnlocked = sharedPreferences.getBoolean("level_${level.id}_unlocked", !level.isLocked)
            level.copy(isLocked = !isUnlocked)
        }
    }
}