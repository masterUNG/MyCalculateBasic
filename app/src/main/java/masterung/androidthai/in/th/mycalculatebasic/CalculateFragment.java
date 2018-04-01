package masterung.androidthai.in.th.mycalculatebasic;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class CalculateFragment extends Fragment{

    //    Explicit
    private String factorString;


    public static CalculateFragment calculateInstance(String factorSting) {

        CalculateFragment calculateFragment = new CalculateFragment();
        Bundle bundle = new Bundle();
        bundle.putString("Factor", factorSting);
        calculateFragment.setArguments(bundle);
        return calculateFragment;

    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Get Value from Argument
        factorString = getArguments().getString("Factor");
        Log.d("1AprilV1", "Factor ==> " + factorString);

//        Create Toolbar
        createToolbar();

//        Exchange Controller
        exchangeController();


    }   // Main Method

    private void myAlertDialog(String titleString, String messageString) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_action_alert);
        builder.setTitle(titleString);
        builder.setMessage(messageString);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();

    }



    private void exchangeController() {

        Button button = getView().findViewById(R.id.btnExchange);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Get Value From EditText
                EditText editText = getView().findViewById(R.id.edtMoney);
                String usdString = editText.getText().toString().trim();

                if (usdString.isEmpty()) {
//                    Have Space
                    myAlertDialog("Have Space",
                            "Please Fill Your Money on the Blank");
                } else {
//                    No Space
                    double usdAdouble = Double.parseDouble(usdString);
                    double factorAdouble = Double.parseDouble(factorString);
                    double thbAdouble = usdAdouble * factorAdouble;
                    String thbString = Double.toString(thbAdouble);
                    myAlertDialog("Your " + usdString + " usd",
                            thbString + "THB.");




                }   // if



            }
        });

    }

    private void createToolbar() {

        Toolbar toolbar = getView().findViewById(R.id.toolbarCalculate);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Exchange Money");
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle("Please Fill Money in USD");

//        Setup Navigator
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculate, container, false);
        return view;
    }
}   // Main Class
