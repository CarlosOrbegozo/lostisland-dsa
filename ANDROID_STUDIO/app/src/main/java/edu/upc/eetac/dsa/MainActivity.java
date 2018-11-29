package edu.upc.eetac.dsa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private GameAPI gameApi;
    //fakedata
    private List<GameObject> objects  = Arrays.asList(
            new GameObject("w001","cuchillo"),
            new GameObject("w002","pistola"),
            new GameObject("f001","pocion"),
            new GameObject("c001","armadura")
    );


    //

    private RecyclerView listObjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listObjects = (RecyclerView) findViewById(R.id.listObjects);
        listObjects.setHasFixedSize(true);
        listObjects.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        listObjects.setAdapter(new RecyclerViewAdapter(objects));

        /*createTracksAPI();
        gameApi.getAllObjects().enqueue(objectsCallBack);*/
    }

    Callback<List<GameObject>> objectsCallBack = new Callback<List<GameObject>>() {

        @Override
        public void onResponse(Call<List<GameObject>> call, Response<List<GameObject>> response) {

            if (response.isSuccessful()) {

                List<GameObject> objectsList = new ArrayList<>();
                objectsList.addAll(response.body());
                //poner en una tabla o algo

                listObjects.setAdapter(new RecyclerViewAdapter(objectsList));
            } else {
                Log.d("QuestionsCallback", "Code: " + response.code() + " Message: " + response.message());
            }

        }

        @Override
        public void onFailure(Call<List<GameObject>> call, Throwable t) {

        }
    };

    private void createTracksAPI() {
        Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(gameApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        gameApi = retrofit.create(GameAPI.class);
    }

}
