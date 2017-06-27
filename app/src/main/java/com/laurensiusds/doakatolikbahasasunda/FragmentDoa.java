package com.laurensiusds.doakatolikbahasasunda;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class FragmentDoa extends Fragment {

    protected TextView tvJudulDoaSunda,tvJudulDoaIndo,tvIsiDoa;
    protected String nav_id;
    protected String nama_doa_indonesia;
    protected String nama_doa_sunda;
    protected String isi_doa;
    protected String TAG = "Doa Bahasa Sunda";

    public FragmentDoa() {}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View inflaterDoa = inflater.inflate(R.layout.fragment_doa, container, false);
        tvJudulDoaSunda = (TextView) inflaterDoa.findViewById(R.id.tvJudulDoaSunda);
        tvJudulDoaIndo= (TextView) inflaterDoa.findViewById(R.id.tvJudulDoaIndo);
        tvIsiDoa = (TextView) inflaterDoa.findViewById(R.id.tvIsiDoa);
        return inflaterDoa;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        jsonParse(ActivityUtama.nav_id);
    }

    public void jsonParse(String id){
        String str_doa = getResources().getString(R.string.doa);
        try {
            JSONObject jsonObj = new JSONObject(str_doa);
            JSONArray doa = jsonObj.getJSONArray("doa");
            for(int x=0;x<doa.length();x++){
                JSONObject doa_set = doa.getJSONObject(x);
                nav_id = doa_set.getString("nav_id");
                nama_doa_indonesia = doa_set.getString("nama_doa_indonesia");
                nama_doa_sunda = doa_set.getString("nama_doa_sunda");
                isi_doa = doa_set.getString("isi_doa");
                if(nav_id.equals(id)){
                    tvJudulDoaSunda.setText(nama_doa_sunda);
                    tvJudulDoaIndo.setText("(Indonesia : " + nama_doa_indonesia + ")");
                    tvIsiDoa.setText(isi_doa);
                }
            }
        } catch (final JSONException e) {
            Log.e(TAG, e.getMessage());
        }
    }
}
