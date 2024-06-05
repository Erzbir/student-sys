package com.erzbir.sys.client;


import cn.hutool.core.util.StrUtil;
import com.erzbir.sys.application.DefaultApplication;
import com.erzbir.sys.client.req.*;
import com.erzbir.sys.client.resp.Response;
import com.erzbir.sys.entity.Student;
import com.erzbir.sys.entity.User;
import com.erzbir.sys.util.JSONUtil;
import com.erzbir.sys.util.JWTUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;

import java.time.Duration;
import java.util.List;

/**
 * @author Erzbir
 * @version 1.0
 * @since 2024/5/31
 */
public class Client {
    public static final Client INSTANCE = new Client();
    private final OkHttpClient client;
    private String token = "";

    private Client() {
        client = new OkHttpClient.Builder().callTimeout(Duration.ofSeconds(5)).build();
    }

    private Request.Builder create0(String path) {
        Request.Builder requestBuilder = new Request.Builder();
        if (StrUtil.isNotBlank(token) && !JWTUtil.isTokenExpired(token)) {
            requestBuilder.header("Authorization", "Bearer " + token);
        }
        return requestBuilder.url("http://" + DefaultApplication.INSTANCE.getSetting().getServer() + path);
    }

    private Request createPost(String path, String body) {
        Request.Builder builder = create0(path).header("Content-Type", "application/json; charset=utf-8");
        return builder.post(RequestBody.create(body, MediaType.parse("application/json; charset=utf-8"))).build();
    }

    private Request createGet(String path, String params) {
        Request.Builder builder = create0(path).header("Content-Type", "text/plain; charset=utf-8");
        if (params != null && !params.isEmpty()) {
            builder.url("http://" + DefaultApplication.INSTANCE.getSetting().getServer() + path + "?" + params);
        }
        return builder.get().build();
    }

    private Response<?> doCall(Call call) {
        try (okhttp3.Response response = call.execute()) {
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                String string = responseBody.string();
                if (StrUtil.isNotBlank(string)) {
                    return JSONUtil.toBean(string, Response.class);
                }
            }

        } catch (Exception e) {
            return Response.error("", e.getMessage());
        }
        return Response.error("", "error");
    }

    private Response<?> post(String path, String body) {
        Call call = client.newCall(createPost(path, body));
        return doCall(call);
    }

    private Response<?> get(String path, String params) {
        Call call = client.newCall(createGet(path, params));
        return doCall(call);
    }

    public Response<String> login(LoginReq loginReq) {
        Response<?> resp = post(Apis.AUTH.LOGIN.path(), JSONUtil.toJsonStr(loginReq.user()));
        Object data = resp.getData();
        if (data == null) {
            return Response.error("Null data");
        }
        String string = data.toString();
        if (StrUtil.isBlank(string)) {
            return Response.error("Null data");
        }
        JsonObject jsonObject = JsonParser.parseString(string).getAsJsonObject();
        if (jsonObject.isEmpty()) {
            return Response.error("Parse json error");
        }
        String token = jsonObject.get("token").getAsString();
        this.token = token;
        return Response.ok(token);
    }

    public Response<?> addStudent(AddReqs.AddStudent addStudentReq) {
        return post(Apis.Student.ADD.path(), JSONUtil.toJsonStr(addStudentReq.student()));
    }

    public Response<?> updateStudent(UpdateReqs.UpdateStudent updateStudentReq) {
        return post(Apis.Student.UPDATE.path(), JSONUtil.toJsonStr(updateStudentReq.student()));
    }

    public Response<?> deleteStudent(DeleteReqs.DeleteStudent deleteStudentReq) {
        return get(Apis.Student.DELETE.path(), "id=" + deleteStudentReq.id());
    }

    public Response<Student> queryStudentById(QueryReqs.QueryStudentById queryStudentByIdReq) {
        Response<?> response = get(Apis.Student.Query.ONE.path(), "id=" + queryStudentByIdReq.id());
        Student student = JSONUtil.toBean(response.getData().toString(), Student.class);
        if (student == null) {
            return Response.error("response is empty");
        }
        return Response.ok(student);
    }

    public Response<List<Student>> queryAllStudents(QueryReqs.QueryAllStudents queryAllStudentsReq) {
        Response<?> response = get(Apis.Student.Query.ALL.path(), null);
        Object data = response.getData();
        if (data == null) {
            return Response.blank();
        }
        String jsonStr = JSONUtil.toJsonStr(data);
        Gson gson = new Gson();
        List<Student> list = gson.fromJson(jsonStr, new TypeToken<List<Student>>() {
        }.getType());
        return Response.ok(list, response.getMsg());
    }

    public Response<?> register(AddReqs.AddUser addUserReq) {
        return post(Apis.User.ADD.path(), JSONUtil.toJsonStr(addUserReq.user()));
    }

    public Response<?> updateUser(UpdateReqs.UpdateUser updateUserReq) {
        return post(Apis.User.UPDATE.path(), JSONUtil.toJsonStr(updateUserReq.user()));
    }

    public Response<?> deleteUser(DeleteReqs.DeleteUser deleteUserReq) {
        return get(Apis.User.DELETE.path(), "id=" + deleteUserReq.id());
    }

    public Response<User> queryUserByName(QueryReqs.QueryUserByName queryUserByNameReq) {
        Response<?> response = get(Apis.User.Query.ONE.path(), "username=" + queryUserByNameReq.username());
        User user = JSONUtil.toBean(response.getData().toString(), User.class);
        if (user == null) {
            return Response.error("response is empty");
        }
        return Response.ok(user);
    }

    public Response<List<User>> queryAllUsers(QueryReqs.QueryAllUsers queryAllUsersReq) {
        Response<?> response = get(Apis.User.Query.ALL.path(), null);
        Object data = response.getData();
        String jsonStr = JSONUtil.toJsonStr(data);
        Gson gson = new Gson();
        List<User> list = gson.fromJson(jsonStr, new TypeToken<List<User>>() {
        }.getType());
        return Response.ok(list, response.getMsg());
    }


}

interface Apis {
    record POST(String path) {
    }

    record GET(String path) {

    }

    interface AUTH {
        POST LOGIN = new POST("/auth/login");
    }

    interface Student {
        POST UPDATE = new POST("/student/update");

        POST ADD = new POST("/student/add");

        GET DELETE = new GET("/student/delete");

        interface Query {
            GET ONE = new GET("/student/get");
            GET ALL = new GET("/student/list");
        }

    }

    interface User {
        POST UPDATE = new POST("/user/update");

        POST ADD = new POST("/user/add");

        GET DELETE = new GET("/user/delete");


        interface Query {
            GET ONE = new GET("/user/get");
            GET ALL = new GET("/user/list");
        }

    }
}

