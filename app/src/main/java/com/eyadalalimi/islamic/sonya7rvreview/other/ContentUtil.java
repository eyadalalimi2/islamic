package com.eyadalalimi.islamic.sonya7rvreview.other;

import android.app.Activity;
import android.content.Intent;

import com.eyadalalimi.islamic.sonya7rvreview.R;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by anwar_se on 7/4/2019
 * Email: anwar.dev.96@gmail.com.
 */
public class ContentUtil {

    public static void shareText(Activity activity, String shareBody) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT,
                activity.getResources().getString(R.string.app_name));
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        activity.startActivity(Intent.createChooser(sharingIntent,
                activity.getResources().getString(R.string.share_using)));
    }

    public static String removeHarakat(String text) {
        List<String> harkat = getHarakat();

        StringBuilder builder = new StringBuilder();
        char[] chars = text.toCharArray();
        for (int i = 0; i < text.length(); i++) {
            String aChar = String.valueOf(chars[i]);
            boolean isHarakat = false;
            for (String s : harkat) {
                if (s.equals(aChar)) {
                    isHarakat = true;
                    break;
                }
            }
            if (isHarakat)
                builder.append(aChar);

        }

        return builder.toString();
    }

    public static List<String> getHarakat() {
        List<String> harkat = new ArrayList<>();
        harkat.add("َ");
        harkat.add("ً");
        harkat.add("ُ");
        harkat.add("ٌ");
        harkat.add("ِ");
        return harkat;
    }

    public static String normalize(String input) {
        input = Normalizer.normalize(input, Normalizer.Form.NFKD)
                .replaceAll("\\p{M}", "");
        return input;
    }
}
