package id.sch.smktelkom_mlg.privateassignment.xirpl326.aplikasisam.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl326.aplikasisam.Adapter.SourceAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl326.aplikasisam.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl326.aplikasisam.model.ResultResponse;
import id.sch.smktelkom_mlg.privateassignment.xirpl326.aplikasisam.model.Results;
import id.sch.smktelkom_mlg.privateassignment.xirpl326.aplikasisam.service.GsonGetRequest;
import id.sch.smktelkom_mlg.privateassignment.xirpl326.aplikasisam.service.VolleySingleton;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {

    ArrayList<Results> mlist = new ArrayList<>();
    RecyclerView recyclerView;
    SourceAdapter sourceAdapter;

    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        sourceAdapter = new SourceAdapter(this, mlist, getContext());
        recyclerView.setAdapter(sourceAdapter);

        LinearLayoutManager grid = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(grid);

        downloadDataSources();


        return view;

    }

    private void downloadDataSources() {
        String url = "https://api.themoviedb.org/3/movie/top_rated?api_key=c72575a25c2e38d54bb76e3edc5fce09&language=en-US&page=1";
        //String url = "https://newsapi.org/v1/sources?language=en";
        GsonGetRequest<ResultResponse> myRequest = new GsonGetRequest<ResultResponse>
                (url, ResultResponse.class, null, new Response.Listener<ResultResponse>() {
                    @Override
                    public void onResponse(ResultResponse resultsResponse) {
                        Log.d("FLOW", "onResponse: " + (new Gson().toJson(resultsResponse)));
                        //if (response.status.equals("ok"))
                        //{
                        //    fillColor(response.sources);
                        mlist.addAll(resultsResponse.results);
                        SourceAdapter.notifyDataSetChanged();
                        //}
                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("FLOW", "onErrorResponse: ", error);
                    }
                });
        VolleySingleton.getInstance(this).addToRequestQueue(myRequest);
    }
}
