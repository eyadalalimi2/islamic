package com.eyadalalimi.islamic.sonya7rvreview.other;

import android.content.Context;
import android.util.Log;

import com.eyadalalimi.islamic.sonya7rvreview.R;
import com.eyadalalimi.islamic.sonya7rvreview.nbsahihboukhari.DefaultIndex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by anwar_se on 6/20/2019
 * Email: anwar.dev.96@gmail.com.
 */
public class Arrays {

    public static final String TAG = "Arrays_Log";

    /*-------------------->Home<--------------------*/


    public static List<Category> getQuranItemList() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category().setCategoryId(R.id.cat_quran_item_read).setTitle(R.string.cat_quran_item_read));
        categories.add(new Category().setCategoryId(R.id.cat_quran_item_tafsir).setTitle(R.string.cat_quran_item_tafsir));
        categories.add(new Category().setCategoryId(R.id.cat_quran_item_e3rab).setTitle(R.string.cat_quran_item_e3rab));
        categories.add(new Category().setCategoryId(R.id.cat_quran_item_m3ani).setTitle(R.string.cat_quran_item_m3ani));
        return categories;
    }

    public static List<Category> getQuranOptions() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category().setCategoryId(R.id.cat_quran_item_index).setTitle(R.string.cat_quran_item_index));
        categories.add(new Category().setCategoryId(R.id.cat_quran_item_read_virtue).setTitle(R.string.cat_quran_item_read_virtue));
        categories.add(new Category().setCategoryId(R.id.cat_quran_item_doaa).setTitle(R.string.cat_quran_item_doaa));
        categories.add(new Category().setCategoryId(R.id.cat_quran_item_tafsir).setTitle(R.string.cat_quran_item_tafsir));
        categories.add(new Category().setCategoryId(R.id.cat_quran_item_m3ani).setTitle(R.string.cat_quran_item_m3ani));
        categories.add(new Category().setCategoryId(R.id.cat_quran_item_save_mark).setTitle(R.string.cat_quran_item_save_mark));
        categories.add(new Category().setCategoryId(R.id.cat_quran_item_go_mark).setTitle(R.string.cat_quran_item_go_mark));
        return categories;
    }








    public static String LoadData(Context context, String inFile) {
        String tContents = "";

        try {
            InputStream stream = context.getAssets().open(inFile);

            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            tContents = new String(buffer);
        } catch (IOException e) {
            // Handle exceptions here
        }

        return tContents;

    }

    public static List<DefaultIndex> getNawaiya40List(Context context, String fileName) {
        List<DefaultIndex> nawawiya40 = new ArrayList<>();
        InputStream fIn = null;
        InputStreamReader isr = null;

        BufferedReader input = null;
        try {
            fIn = context.getResources().getAssets().open(fileName, Context.MODE_WORLD_READABLE);
            isr = new InputStreamReader(fIn);
            input = new BufferedReader(isr);
            String line;
            while ((line = input.readLine()) != null) {
                nawawiya40.add(new DefaultIndex()
                        .setTitle(line)
                        .print(TAG, "getNawaiya40List: "));
            }
        } catch (Exception e) {
            Log.e(TAG, "getNawaiya40List: ", e);
        } finally {
            try {
                if (isr != null)
                    isr.close();
                if (fIn != null)
                    fIn.close();
                if (input != null)
                    input.close();
            } catch (Exception e2) {
                e2.getMessage();
            }
        }
        Log.d(TAG, "nawawia: ");
        return nawawiya40;
    }

    public static String getReadQuran() {
        return "        فضل قراءة القرآن الكريم يوميَّاً صفاء الذهن، حيث يسترسل المسلم بشكل يومي مع القرآن الكريم، فيتتبع آياته وأحكامه، وعظمة الله في خلقه. قوَّة الذاكرة، فخير ما تنتظم به ذاكرة المسلم هو آيات القرآن الكريم، تأملاً، وحفظاً، وتدبراً. طمأنينة القلب، حيث يعيش من يحافظ على تلاوة القرآن الكريم وحفظ آياته بطمأنينة عجيبة، يقوى من خلالها على مواجهة الصعاب التي تواجهه، فقد قال تعالى:(الَّذِينَ آمَنُوا وَتَطْمَئِنُّ قُلُوبُهُم بِذِكْرِ اللَّهِ ۗ أَلَا بِذِكْرِ اللَّهِ تَطْمَئِنُّ الْقُلُوبُ) [الرعد: 28]. الشعور بالفرح والسعادة، وهي ثمرة أصيلة لتعلُّق قلب المسلم بخالقه، بترديده لآياته وتعظيمه له. الشعور بالشجاعة وقوَّة النفس، والتخلُّص من الخوف والحزن والتوتر والقلق. قوة في اللغة، فالذي يعيش مع آيات القرآن، وما فيها من بلاغة محكمة، وبيان عذب، ولغة قوية، تقوى بذلك لغته، وتثرى مفرداته، ولا سيَّما متى عاش مع القرآن متدبراً لمعانيه، ومن خلال كتب التفسير المتنوعة، كظلال القرآن الكريم لسيد قطب، الذي يركِّز على الجوانب التربويَّة والبلاغيَّة في القرآن الكريم. انتظام علاقات قارئ القرآن الاجتماعيَّة مع النَّاس من حوله، حيث ينعكس نور القرآن على سلوكه، قولاً وعملاً فيحبب الناس به ويشجعهم على بناء علاقات تواصليَّة معه، فيألف بهم، ويألفون به. التخلُّص من الأمراض المزمنة، حيث ثبت علمياً أنَّ المحافظة على تلاوة القرآن الكريم والاستماع لآياته، يقوي المناعة لدى الإنسان بما يمكِّنه من مواجهة الكثير من الأمراض المزمنة. رفع لقدرة الإنسان الإدراكيَّة في مجال الفهم والاستيعاب، فالمسلم المنتظم بعلاقته مع كتاب الله دائم البحث والتدبر في معانيه، مقلباً لكتب التفسير يتعلم كل ما هو جديد من معاني القرآن العظيمة. نيل رضى الله وتوفيقه له في شؤون الدنيا، يجده بركة في الرزق، ونجاة من المكروه. الفوز بالجنَّة يوم القيامة، حيث يأتي القرآن الكريم يوم القيامة يحاجّ عن صاحبه الذي كان يقرؤه، شفيعاً له.\n";
    }

    public static String getDoaaQuran() {
        return "\n" +
                "    اللَّهُمَّ ارْحَمْنِي بالقُرْءَانِ وَاجْعَلهُ لِي إِمَاماً وَنُوراً وَهُدًى وَرَحْمَةً *\n" +
                "\n" +
                "    اللَّهُمَّ ذَكِّرْنِي مِنْهُ مَانَسِيتُ وَعَلِّمْنِي مِنْهُ مَاجَهِلْتُ وَارْزُقْنِي تِلاَوَتَهُ\n" +
                "    آنَاءَ اللَّيْلِ وَأَطْرَافَ النَّهَارِ وَاجْعَلْهُ لِي حُجَّةً يَارَبَّ العَالَمِينَ *\n" +
                "\n" +
                "    اللَّهُمَّ أَصْلِحْ لِي دِينِي الَّذِي هُوَ عِصْمَةُ أَمْرِي، وَأَصْلِحْ لِي دُنْيَايَ الَّتِي\n" +
                "    فِيهَا مَعَاشِي، وَأَصْلِحْ لِي آخِرَتِي الَّتِي فِيهَا مَعَادِي، وَاجْعَلِ الحَيَاةَ زِيَادَةً\n" +
                "    لِي فِي كُلِّ خَيْرٍ وَاجْعَلِ المَوْتَ رَاحَةً لِي مِنْ كُلِّ شَرٍّ *\n" +
                "\n" +
                "    اللَّهُمَّ اجْعَلْ خَيْرَ عُمْرِي آخِرَهُ وَخَيْرَ عَمَلِي خَوَاتِمَهُ وَخَيْرَ أَيَّامِي يَوْمَ\n" +
                "    أَلْقَاكَ فِيهِ *\n" +
                "\n" +
                "    اللَّهُمَّ إِنِّي أَسْأَلُكَ عِيشَةً هَنِيَّةً وَمِيتَةً سَوِيَّةً وَمَرَدًّا غَيْرَ مُخْزٍ\n" +
                "    وَلاَ فَاضِحٍ *\n" +
                "\n" +
                "    اللَّهُمَّ إِنِّي أَسْأَلُكَ خَيْرَ المَسْأَلةِ وَخَيْرَ الدُّعَاءِ وَخَيْرَ النَّجَاحِ وَخَيْرَ\n" +
                "    العِلْمِ وَخَيْرَ العَمَلِ وَخَيْرَ الثَّوَابِ وَخَيْرَ الحَيَاةِ وَخيْرَ المَمَاتِ وَثَبِّتْنِي\n" +
                "    وَثَقِّلْ مَوَازِينِي وَحَقِّقْ إِيمَانِي وَارْفَعْ دَرَجَتِي وَتَقَبَّلْ صَلاَتِي وَاغْفِرْ\n" +
                "    خَطِيئَاتِي وَأَسْأَلُكَ العُلَا مِنَ الجَنَّةِ *\n" +
                "\n" +
                "    اللَّهُمَّ إِنِّي أَسْأَلُكَ مُوجِبَاتِ رَحْمَتِكَ وَعَزَائِمِ مَغْفِرَتِكَ وَالسَّلاَمَةَ مِنْ\n" +
                "    كُلِّ إِثْمٍ وَالغَنِيمَةَ مِنْ كُلِّ بِرٍّ وَالفَوْزَ بِالجَنَّةِ وَالنَّجَاةَ مِنَ النَّارِ *\n" +
                "\n" +
                "    اللَّهُمَّ أَحْسِنْ عَاقِبَتَنَا فِي الأُمُورِ كُلِّهَا، وَأجِرْنَا مِنْ خِزْيِ الدُّنْيَا\n" +
                "    وَعَذَابِ الآخِرَةِ *\n" +
                "\n" +
                "    اللَّهُمَّ اقْسِمْ لَنَا مِنْ خَشْيَتِكَ مَاتَحُولُ بِهِ بَيْنَنَا وَبَيْنَ مَعْصِيَتِكَ وَمِنْ\n" +
                "    طَاعَتِكَ مَاتُبَلِّغُنَا بِهَا جَنَّتَكَ وَمِنَ اليَقِينِ مَاتُهَوِّنُ بِهِ عَلَيْنَا مَصَائِبَ\n" +
                "    الدُّنْيَا وَمَتِّعْنَا بِأَسْمَاعِنَا وَأَبْصَارِنَا وَقُوَّتِنَا مَاأَحْيَيْتَنَا وَاجْعَلْهُ\n" +
                "    الوَارِثَ مِنَّا وَاجْعَلْ ثَأْرَنَا عَلَى مَنْ ظَلَمَنَا وَانْصُرْنَا عَلَى مَنْ عَادَانَا\n" +
                "    وَلاَ تجْعَلْ مُصِيبَتَنَا فِي دِينِنَا وَلاَ تَجْعَلِ الدُّنْيَا أَكْبَرَ هَمِّنَا وَلَا\n" +
                "    مَبْلَغَ عِلْمِنَا وَلاَ تُسَلِّطْ عَلَيْنَا مَنْ لَا يَرْحَمُنَا *\n" +
                "\n" +
                "    اللَّهُمَّ لَا تَدَعْ لَنَا ذَنْبًا إِلَّا غَفَرْتَهُ وَلَا هَمَّا إِلَّا فَرَّجْتَهُ وَلَا\n" +
                "    دَيْنًا إِلَّا قَضَيْتَهُ وَلَا حَاجَةً مِنْ حَوَائِجِ الدُّنْيَا وَالآخِرَةِ إِلَّا قَضَيْتَهَا\n" +
                "    يَاأَرْحَمَ الرَّاحِمِينَ *\n" +
                "\n" +
                "    رَبَّنَا آتِنَا فِي الدُّنْيَا حَسَنَةً وَفِي الآخِرَةِ حَسَنَةً وَقِنَا عَذَابَ النَّارِ\n" +
                "    وَصَلَّى اللهُ عَلَى سَيِّدِنَا وَنَبِيِّنَا مُحَمَّدٍ وَعَلَى آلِهِ وَأَصْحَابِهِ الأَخْيَارِ\n" +
                "    وَسَلَّمَ تَسْلِيمًا كَثِيراً.\n" +
                "\n" +
                "    وردك اليوم\n" +
                "    أَخْبَرَنَا مُحَمَّدُ بْنُ سَعِيدٍ أَخْبَرَنَا شَرِيكٌ عَنْ لَيْثٍ عَنْ مُجَاهِدٍ عَنْ عَبْدِ\n" +
                "    اللَّهِ بْنِ عَمْرٍو قَالَ مَا يُرَغِّبُنِي فِي الْحَيَاةِ إِلَّا الصَّادِقَةُ وَالْوَهْطُ\n" +
                "    فَأَمَّا الصَّادِقَةُ فَصَحِيفَةٌ كَتَبْتُهَا مِنْ رَسُولِ اللَّهِ صَلَّى اللَّهُ عَلَيْهِ\n" +
                "    وَسَلَّمَ وَأَمَّا الْوَهْطُ فَأَرْضٌ تَصَدَّقَ بِهَا عَمْرُو بْنُ الْعَاصِ كَانَ يَقُومُ\n" +
                "    عَلَيْهَا.\n" +
                "    حَدَّثَنَا مُحَمَّدُ بْنُ الْقَاسِمِ حَدَّثَنَا مُوسَى بْنُ عُبَيْدَةَ عَنْ مُحَمَّدِ بْنِ\n" +
                "    إِبْرَاهِيمَ عَنْ يُحَنَّسَ مَوْلَى الزُّبَيْرِ عَنْ سَالِمٍ أَخِي أُمِّ الدَّرْدَاءِ فِي\n" +
                "    اللَّهِ عَنْ أُمِّ الدَّرْدَاءِ عَنْ أَبِي الدَّرْدَاءِ عَنْ النَّبِيِّ صَلَّى اللَّهُ عَلَيْهِ\n" +
                "    وَسَلَّمَ قَالَ مَنْ قَرَأَ بِمِائَةِ آيَةٍ فِي لَيْلَةٍ لَمْ يُكْتَبْ مِنْ الْغَافِلِينَ قَالَ\n" +
                "    أَبُو مُحَمَّد مِنْهُمْ مَنْ يَقُولُ مَكَانَ سَالِمٍ رَاشِدُ بْنُ سَعْدٍ.";
    }

}
