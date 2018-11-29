package edu.upc.eetac.dsa;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface GameAPI {
    String BASE_URL = "http://147.83.7.205:8080/dsaApp/";

    /* ******************************************************************************************************************************
    authentication
    ****************************************************************************************************************************** */

    // autenticar usuario
    @GET("auth/login/{userId}")
    Call<ResponseBody> authUser(@Path("userId") String userId,@Body String pass);

    // añadir usuario
    @POST("auth/sign-up")
    Call<ResponseBody> createUser(@Body User user);

    /* ******************************************************************************************************************************
    users
    ****************************************************************************************************************************** */

    // listado de objetos de un usuario
    @GET("users/{userId}/my-objects")
    Call<List<GameObject>> getItemsByUser(@Path("userId") String userId);

    //estadisticas de un usuario
    @GET("users/{userId}/my-stats")
    Call<List<Statistics>> getStatsByUser(@Path("userId") String id) ;

    //añadir un objeto a un usuario
    @POST("users/{userId}/my-objects/new-object")
    Call<ResponseBody> addItemToUser(@Path("userId") String userId,@Body GameObject gameObject);

    //cambiar objeto en uso
    @PUT("users/{userId}/my-objects/{gameObjectId}")
    Call<ResponseBody> changeItemUser(@Path("userId") String userId, @Path("gameObjectId") String id);

    //cambiar usuario y contraseña
    @PUT("users/{userId}/credentials")
    Call<ResponseBody> changeCredentialsOfUser(@Path("userId") String userId);

    //eliminar un objeto del inventario del usuario
    @DELETE("users/{userId}/my-objects/{gameObjectId}")
    Call<ResponseBody> deleteItemOfUser(@Path("userId") String id, @Path("gameObjectId") String gameObjectId);

    //eliminar cuenta
    @DELETE("users/{userId}")
    Call<ResponseBody> deleteUser(@Path("userId") String id);



    /* ******************************************************************************************************************************
    objects
    ****************************************************************************************************************************** */

    //obtener lista de objetos
    @GET("objects/all-objects")
    Call<List<GameObject>> getAllObjects();

    //obtener un objeto
    @GET("objects/{gameObjectId}")
    Call<GameObject> getItem(@Path("gameObjectId") String gameObjectId);

    /*
    //añadir un objeto
    @POST("items/new-item")
    Call<ResponseBody> addItem(@Body GameObject item);

    //actualizar un objeto
    @PUT("{itemId}")
    Call<ResponseBody> updateItem(@Path("itemId") String id);

    //eliminar un objeto
    @DELETE("{itemId}")
    Call<ResponseBody> deleteItem(@Path("itemId") String id);
    */

    /* ******************************************************************************************************************************
    maps
    ****************************************************************************************************************************** */

    //obtener la lista de los mapas
    @GET("maps/allmaps")
    Call<List<MapGame>> getMaps();

    // obtener un mapa
    @GET("maps/{mapId}")
    Call<MapGame> getMap();

    //pieza a encontrar en cada mapa
    @GET("maps/{mapId}/objective")
    Call<String> getObjective(@Path("mapId") String id);


}
