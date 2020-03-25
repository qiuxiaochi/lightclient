package lan.qxc.lightclient.retrofit_util.service;

import java.util.List;

import javax.annotation.PostConstruct;

import lan.qxc.lightclient.entity.Dongtai;
import lan.qxc.lightclient.result.Result;
import lan.qxc.lightclient.retrofit_util.api.DongtaiAPI;
import lan.qxc.lightclient.retrofit_util.api.UserAPI;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface DongtaiRequestService {

//    @POST(DongtaiAPI.ADD_DONGTAI)
//    Call<Result> addDongtai(@Body Dongtai dongtai);

    @POST(DongtaiAPI.DELETE_DONGTAI)
    @FormUrlEncoded
    Call<Result> deleteDongtai(@Field("dtid") Long dtid);

    @POST(DongtaiAPI.UPDATE_DONGTAI)
    Call<Result> updateDongtai(@Body Dongtai dongtai);

    @POST(DongtaiAPI.GET_DONGTAI_BACK_LIST)
    @FormUrlEncoded
    Call<Result> getDongtai_Back_List(@Field("dtid") Long dtid);

    @POST(DongtaiAPI.GET_DONGTAI_NEW_LIST)
    Call<Result> getDongtai_New_List();

    @Multipart()
    @POST(DongtaiAPI.ADD_DONGTAI)
    Call<Result> addDongtai(@Part()   List<MultipartBody.Part> body,@Query("dttext") String dttext,@Query("userid") Long userid);

    @POST(DongtaiAPI.ADD_DONGTAI_NOT_PIC)
    @FormUrlEncoded
    Call<Result> addDongtaiNotPic(@Field("dttext") String dttext,@Field("userid") Long userid);




}
