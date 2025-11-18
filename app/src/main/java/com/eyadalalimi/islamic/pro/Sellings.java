package com.eyadalalimi.islamic.pro;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

public class Sellings extends AppCompatActivity {


    ArrayList<SettingItem> fullsongpath =new ArrayList<SettingItem>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellings);
        MobileAds.initialize(getApplicationContext());
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        fullsongpath.add(new SettingItem(getResources().getString(R.string.separeteapp), R.drawable.network));
        fullsongpath.add(new SettingItem(getResources().getString(R.string.rateapp), R.drawable.social));
        fullsongpath.add(new SettingItem(getString(R.string.policy), R.drawable.pr));

        //intiixae items
        //  fullsongpath.add(new SettingItem(getResources().getString(R.string.Languages), R.drawable.menu));

        ListView ls= findViewById(R.id.listView);
        ls.setAdapter(new MyCustomAdapter());
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {


                    switch (position) {


                        case 0:
                        {
                            Intent sendIntent = new Intent();
                            sendIntent.setAction(Intent.ACTION_SEND);
                            sendIntent.putExtra(Intent.EXTRA_TEXT, "السلام عليكم ، أحببت أن أتشارك معكم هذا التطبيق الإسلامي الرائع"+"\n"+"  https://play.google.com/store/apps/details?id="+getPackageName());
                            sendIntent.setType("text/plain");
                            startActivity(sendIntent);
                        }
                        break;

                        case 1:
                        {
                            Uri uri = Uri.parse("market://details?id=" + getPackageName());
                            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                            // To count with Play market backstack, After pressing back button,
                            // to taken back to our application, we need to add following flags to intent.
                            goToMarket.addFlags(
                                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                            try {
                                startActivity(goToMarket);
                            } catch (ActivityNotFoundException e) {
                                startActivity(new Intent(Intent.ACTION_VIEW,
                                        Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
                            }
                            SaveSettings.IsRated = 1;
                            SaveSettings sv = new SaveSettings(getApplicationContext());
                            sv.SaveData();

                        }
                        break;




                        case 2:
                        {

                            final Dialog dialog = new Dialog(Sellings.this);
                            dialog.setContentView(R.layout.policy_dialog);

                            dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialogbg);

                            dialog.getWindow().getAttributes().windowAnimations = R.style.animation;


                            dialog.setTitle(getString(R.string.policy));
                            dialog.getWindow().setTitleColor(Color.rgb(140, 12, 13));
                            TextView text2 = dialog.findViewById(R.id.policy_tille);
                            text2.setText(getString(R.string.policy));
                            // set the custom dialog components - text, image and button
                            TextView text = dialog.findViewById(R.id.policy);
                            text.setText("\n" +
                                    "This app  has adopted this privacy policy (“Privacy Policy”) to explain how This app collects, stores, and uses the information collected in connection with This app’s Services.\n" +
                                    "BY INSTALLING, USING, REGISTERING TO OR OTHERWISE ACCESSING THE SERVICES, YOU AGREE TO THIS PRIVACY POLICY AND GIVE AN EXPLICIT AND INFORMED CONSENT TO THE PROCESSING OF YOUR PERSONAL DATA IN ACCORDANCE WITH THIS PRIVACY POLICY. IF YOU DO NOT AGREE TO THIS PRIVACY POLICY, PLEASE DO NOT INSTALL, USE, REGISTER TO OR OTHERWISE ACCESS THE SERVICES. This app reserves the right to modify this Privacy Policy at reasonable times, so please review it frequently. If This app makes material or significant changes to this Privacy Policy, This app may post a notice on This app’s website along with the updated Privacy Policy. Your continued use of Services will signify your acceptance of the changes to this Privacy Policy.\n" +
                                    "Non-personal data\n" +
                                    "For purposes of this Privacy Policy, “non-personal data” means information that does not directly identify you. The types of non-personal data This app may collect and use include, but are not limited to: application properties, including, but not limited to application name, package name and icon installed on your device. Your checkin (include like, recommendation) of a game will be disclosed to all This app users.\n" +
                                    "This app may use and disclose to This app’s partners and contractors the collected non-personal data for purposes of analyzing usage of the Services, advertisement serving, managing and providing the Services and to further develop the Services and other This app services and products.\n" +
                                    "You recognize and agree that the analytics companies utilized by This app may combine the information collected with other information they have independently collected from other services or products relating to your activities. These companies collect and use information under their own privacy policies.\n" +
                                    "Personal Data\n" +
                                    "For purposes of this Privacy Policy, “personal data” means personally identifiable information that specifically identifies you as an individual.\n" +
                                    "Personal information collected by This app is information voluntarily provided to us by you when you create your account or change your account \n" +
                                    "information. The information includes your facebook id, name, gender, location and your friends’id on facebook. This app also stores your game checkins, likes, dislikes, recommendations and messages.\n" +
                                    "This app may use collected personal data for purposes of analyzing usage of the Services, providing customer and technical support, managing and providing Services (including managing advertisement serving) and to further develop the Services and other This app services and products. This app may combine non-personal data with personal data.\n" +
                                    "Please note that certain features of the Services may be able to connect to your social networking sites to obtain additional information about you. In such cases, This app may be able to collect certain information from your social networking profile when your social networking site permits it, and when you consent to allow your social networking site to make that information available to This app. This information may include, but is not limited to, your name, profile picture, gender, user ID, email address, your country, your language, your time zone, the organizations and links on your profile page, the names and profile pictures of your social networking site “friends” and other information you have included in your social networking site profile. This app may associate and/or combine as well as use information collected by This app and/or obtained through such social networking sites in accordance with this Privacy Policy.\n" +
                                    "Disclosure and Transfer of Personal Data\n" +
                                    "This app collects and processes personal data on a voluntary basis and it is not in the business of selling your personal data to third parties. Personal data may, however, occasionally be disclosed in accordance with applicable legislation and this Privacy Policy. Additionally, This app may disclose personal data to its parent companies and its subsidiaries in accordance with this Privacy Policy.\n" +
                                    "This app may hire agents and contractors to collect and process personal data on This app’s behalf and in such cases such agents and contractors will be instructed to comply with our Privacy Policy and to use personal data only for the purposes for which the third party has been engaged by This app. These agents and contractors may not use your personal data for their own marketing purposes. This app may use third party service providers such as credit card processors, e-mail service providers, shipping agents, data analyzers and business intelligence providers. This app has the right to share your personal data as necessary for the aforementioned third parties to provide their services for This app. This app is not liable for the acts and omissions of these third parties, except as provided by mandatory law.\n" +
                                    "This app may disclose your personal data to third parties as required by law enforcement or other government officials in connection with an investigation of fraud, intellectual property infringements, or other activity that is illegal or may expose you or This app to legal liability. This app may also disclose your personal data to third parties when This app has a reason to believe that a disclosure is necessary to address potential or actual injury or interference with This app’s rights, property, operations, users or others who may be harmed or may suffer loss or damage, or This app believes that such disclosure is necessary to protect This app ’s rights, combat fraud and/or comply with a judicial proceeding, court order, or legal process served on This app. To the extent permitted by applicable law, This app will make reasonable efforts to notify you of such disclosure through This app’s website or in another reasonable manner.\n" +
                                    "Safeguards\n" +
                                    "This app follows generally accepted industry standards and maintains reasonable safeguards to attempt to ensure the security, integrity and privacy of the information in This app’s possession. Only those persons with a need to process your personal data in connection with the fulfillment of their tasks in accordance with the purposes of this Privacy Policy and for the purposes of performing technical maintenance, have access to your personal data in This app’s possession. Personal data collected by This app is stored in secure operating environments that are not available to the public. To prevent unauthorized on-line access to personal data, This app maintains personal data behind a firewall-protected server. However, no system can be 100% secure and there is the possibility that despite This app’s reasonable efforts, there could be unauthorized access to your personal data. By using the Services, you assume this risk.\n" +
                                    "Other\n" +
                                    "Please be aware of the open nature of certain social networking and other open features of the Services This app may make available to you. You may choose to disclose data about yourself in the course of contributing user generated content to the Services. Any data that you disclose in any of these forums, blogs, chats or the like is public information, and there is no expectation of privacy or confidentiality. This app is not responsible for any personal data you choose to make public in any of these forums.\n" +
                                    "If you are under 15 years of age or a minor in your country of residence, please ask your legal guardian’s permission to use or access the Services. This app takes children’s privacy seriously, and encourages parents and/or guardians to play an active role in their children's online experience at all times. This app does not knowingly collect any personal information from children below the aforementioned age and if This app learns that This app has inadvertently gathered personal data from children under the aforementioned age, This app will take reasonable measures to promptly erase such personal data from This app’s records.\n" +
                                    "This app may store and/or transfer your personal data to its affiliates and partners in and outside of EU/EEA member states and the United States in accordance with mandatory legislation and this Privacy Policy. This app may disclose your personal data to third parties in connection with a corporate merger, consolidation, restructuring, the sale of substantially all of This app’s stock and/or assets or other corporate change, including, without limitation, during the course of any due diligence process provided, however, that this Privacy Policy shall continue to govern such personal data.\n" +
                                    "This app regularly reviews its compliance with this Privacy Policy. If This app receives a formal written complaint from you, it is This app’s policy to attempt to contact you directly to address any of your concerns. This app will cooperate with the appropriate governmental authorities, including data protection authorities, to resolve any complaints regarding the collection, use, transfer or disclosure of personal data that cannot be amicably resolved between you and This app.\n" +
                                    "3rd party services\n" +
                                    "We use 3rd party services in our apps. These services collect usage data in compliance with their Privacy Policies. The services are described below.\n" +
                                    "Advertising\n" +
                                    "3rd party ad serving systems allow user data to be utilized for advertising communication purposes displayed in the form of banners and other advertisements on This app apps, possibly based on user interests.\n" +
                                    "Admob\n" +
                                    "We use Admob by Google as the main ad server. Please see Admob Privacy Policy – https://www.google.com/intl/en/policies/privacy/\n" +
                                    "Analytics\n" +
                                    "3rd party analytics services allow us to monitor and analyze app usage, better understand our audience and user behavior.\n" +
                                    "Flurry\n" +
                                    "We use Flurry Analytics to collect, monitor and analyze log data, including frequency of use, length of time spent in the app, in order to improve functionality and user-friendliness of our apps. Please see Flurry Privacy Policy – https://www.flurry.com/privacy-policy.html\n" +
                                    "Google Analytics\n" +
                                    "Google Analytics is an analysis service provided by Google Inc. Google utilizes the collected data to track and examine the use of This app Apps, to prepare reports on user activities and share them with other Google services. Google may use the data to contextualize and personalize the ads of its own advertising network. (https://www.google.com/intl/en/policies/privacy/)\n" +
                                    "Children’s Online Privacy Protection Act Compliance\n" +
                                    "We are in compliance with the requirements of COPPA, we do not collect any personal information from anyone under 13 years of age. Our products and services are all directed to people who are at least 13 years old or older.\n" +
                                    "Contact Us" );

                            dialog.show();

                            Button yesbutton = dialog.findViewById(R.id.yes);
                            // if button is clicked, close the custom dialog
                            yesbutton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {


                                    dialog.dismiss();

                                }
                            });





                        }



                        break;

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_managerdb, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.gbackmenu) { // stoped
            // Intent intent=new Intent(this,Firstt.class);
            //startActivity(intent);
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    // adapter for song list
    private class MyCustomAdapter extends BaseAdapter {


        public MyCustomAdapter() {

        }


        @Override
        public int getCount() {
            return fullsongpath.size();
        }

        @Override
        public String getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater mInflater = getLayoutInflater();

            final   SettingItem s = fullsongpath.get(position);

            if((position==20) ){

                View myView = mInflater.inflate(R.layout.setting_item_alert, null);
                final Switch swNotify= myView.findViewById(R.id.switch1);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                    swNotify.setChecked(SaveSettings.LanguageSelect == 1);
                }
                swNotify.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        SaveSettings.LanguageSelect = isChecked ? 1 : 2;
                        SaveSettings sv = new SaveSettings(getApplicationContext());
                        sv.SaveData();
                    }
                });
                return myView;
            }

            else
            {
                View myView = mInflater.inflate(R.layout.settingitem, null);
                TextView textView = myView.findViewById(R.id.textView);
                textView.setText(s.Name);
                ImageView img= myView.findViewById(R.id.imgchannel);
                img.setImageResource(s.ImageURL);
                return myView;}
        }
    }
}