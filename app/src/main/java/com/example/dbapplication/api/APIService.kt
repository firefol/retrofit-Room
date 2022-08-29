package com.example.dbapplication.api

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface APIService {
    /*@GET("/v1/users")
    @Headers("Content-Type: application/json")
    suspend fun getEmployees(): Response<ResponseBody>*/

    @POST("/api/v1/create")
    suspend fun createEmployee(@Body requestBody: RequestBody): Response<ResponseBody>

    // Encoded URL
    @FormUrlEncoded
    @POST("/post")
    suspend fun createEmployee(@FieldMap params: HashMap<String?, String?>): Response<ResponseBody>


    /*****************************************************************************************************************************************************/


    /*
        GET METHOD
    */

    @GET("/api/v1/employees")
    suspend fun getEmployees(): Response<ResponseBody>


    // Request using @Query (e.g https://reqres.in/api/users?page=2)
    @GET("/api/users")
    suspend fun getEmployees(@Query("page") page: String?): Response<ResponseBody>


    @GET("/api/users/{Id}")
    suspend fun getEmployee(@Path("Id") employeeId: String): Response<ResponseBody>


    /*****************************************************************************************************************************************************/

    /*
       PUT METHOD
    */

    @PUT("/api/users/2")
    suspend fun updateEmployee(@Body requestBody: RequestBody): Response<ResponseBody>


    /*****************************************************************************************************************************************************/


    /*
       DELETE METHOD
    */

    @DELETE("/typicode/demo/posts/1")
    suspend fun deleteEmployee(): Response<ResponseBody>
}