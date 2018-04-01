package masterung.androidthai.in.th.mycalculatebasic;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainFragment extends Fragment{



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Create Toolbar
        Toolbar toolbar = getView().findViewById(R.id.toolbarMain);


//        Show Rate
        showRate();


    }   // Main Method

    private void showRate() {

        TextView textView = getView().findViewById(R.id.txtShowRate);
        String urlAPI = "https://api.fixer.io/latest?symbols=THB&base=USD";
        try {

            GetCurrentMoneyRate getCurrentMoneyRate = new GetCurrentMoneyRate(getActivity());
            getCurrentMoneyRate.execute(urlAPI);
            String jsonString = getCurrentMoneyRate.get();
            String forDateString = jsonString;
            Log.d("31MachV1", "json0 ==> " + jsonString);

            jsonString = jsonString.substring(0, jsonString.length() - 2);
            Log.d("31MachV1", "json1 ==> " + jsonString);

            jsonString = jsonString.substring(jsonString.length() - 5, jsonString.length());
            Log.d("31MachV1", "json2 ==> " + jsonString);

            textView.setText("1 --> " + jsonString);

//            forDate
            forDateString = forDateString.substring(1, forDateString.length() - 1);
            String[] strings = forDateString.split(",");
            Log.d("1AprilV1", "string[1] ==> " + strings[1]);

            String result = "[{" + strings[1] + "}]";
            Log.d("1AprilV1", "result ==> " + result);

            JSONArray jsonArray = new JSONArray(result);
            JSONObject jsonObject = jsonArray.getJSONObject(0);

            String dateString = jsonObject.getString("date");
            TextView textView1 = getView().findViewById(R.id.txtShowDate);
            textView1.setText(dateString);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }
}   // Main Class
