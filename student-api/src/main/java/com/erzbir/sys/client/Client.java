package com.erzbir.sys.client;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.erzbir.sys.application.DefaultApplication;
import com.erzbir.sys.client.req.*;
import com.erzbir.sys.entity.Student;
import com.erzbir.sys.entity.User;
import okhttp3.*;

import java.io.IOException;
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
    private final MediaType mediaType = MediaType.parse("application/json; charset=utf-8");

    private Client() {
        client = new OkHttpClient.Builder()
                .callTimeout(Duration.ofSeconds(5))
                .build();
    }

    private Request.Builder create0(String path) {
        return new Request.Builder()
                .header("Content-Type", "application/json; charset=utf-8")
                .url("http://" + DefaultApplication.INSTANCE.getSetting().getServer() + "/" + path);
    }

    private Request createPost(String path, String body) {
        Request.Builder builder = create0(path);
        return builder.post(RequestBody.create(body, mediaType)).build();
    }

    private Request createGet(String path, String params) {
        Request.Builder builder = create0(path);
        if (params != null && !params.isEmpty()) {
            builder.url("http://" + DefaultApplication.INSTANCE.getSetting().getServer() + "/" + path + "?" + params);
        }
        return builder.get().build();
    }

    private com.erzbir.sys.client.resp.Response<?> doCall(Call call) {
        try (okhttp3.Response response = call.execute()) {
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                return JSONUtil.toBean(responseBody.string(), com.erzbir.sys.client.resp.Response.class);
            }

        } catch (IOException e) {
            return com.erzbir.sys.client.resp.Response.error(e.getMessage());
        }
        return com.erzbir.sys.client.resp.Response.error("error");
    }

    private com.erzbir.sys.client.resp.Response<?> post(String path, String body) {
        Call call = client.newCall(createPost(path, body));
        return doCall(call);
    }

    private com.erzbir.sys.client.resp.Response<?> get(String path, String params) {
        Call call = client.newCall(createGet(path, params));
        return doCall(call);
    }

    public com.erzbir.sys.client.resp.Response<String> login(LoginReq loginReq) {
        com.erzbir.sys.client.resp.Response<?> resp = post(Apis.AUTH.LOGIN.path(), JSONUtil.toJsonStr(loginReq.user()));
        JSONObject jsonObject = JSONUtil.parseObj(resp.getData());
        if (jsonObject.isEmpty()) {
            return com.erzbir.sys.client.resp.Response.blank();
        }
        String token = jsonObject.getStr("token");
        return com.erzbir.sys.client.resp.Response.ok(token);
    }

    public com.erzbir.sys.client.resp.Response<?> addStudent(AddReqs.AddStudent addStudentReq) {
        return post(Apis.Student.ADD.path(), JSONUtil.toJsonStr(addStudentReq.student()));
    }

    public com.erzbir.sys.client.resp.Response<?> updateStudent(UpdateReqs.UpdateStudent updateStudentReq) {
        return post(Apis.Student.UPDATE.path(), JSONUtil.toJsonStr(updateStudentReq.student()));
    }

    public com.erzbir.sys.client.resp.Response<?> deleteStudent(DeleteReqs.DeleteStudent deleteStudentReq) {
        return get(Apis.Student.DELETE.path(), "id=" + deleteStudentReq.id());
    }

    public com.erzbir.sys.client.resp.Response<Student> queryStudentById(QueryReqs.QueryStudentById queryStudentByIdReq) {
        com.erzbir.sys.client.resp.Response<?> response = get(Apis.Student.Query.ONE.path(), "id=" + queryStudentByIdReq.id());
        JSONObject jsonObject = JSONUtil.parseObj(response.getData());
        if (jsonObject.isEmpty()) {
            return com.erzbir.sys.client.resp.Response.error("response is empty");
        }
        return com.erzbir.sys.client.resp.Response.ok(jsonObject.getBean("data", Student.class));
    }

    public com.erzbir.sys.client.resp.Response<List<Student>> queryAllStudents(QueryReqs.QueryAllStudents queryAllStudentsReq) {
        com.erzbir.sys.client.resp.Response<?> response = get(Apis.Student.Query.ALL.path(), null);
        return com.erzbir.sys.client.resp.Response.ok(JSONUtil.toList(JSONUtil.parseArray(response.getData()), Student.class));
    }

    public com.erzbir.sys.client.resp.Response<?> register(AddReqs.AddUser addUserReq) {
        return post(Apis.User.ADD.path(), JSONUtil.toJsonStr(addUserReq.user()));
    }

    public com.erzbir.sys.client.resp.Response<?> updateUser(UpdateReqs.UpdateUser updateUserReq) {
        return post(Apis.User.UPDATE.path(), JSONUtil.toJsonStr(updateUserReq.user()));
    }

    public com.erzbir.sys.client.resp.Response<?> deleteUser(DeleteReqs.DeleteUser deleteUserReq) {
        return get(Apis.User.DELETE.path(), "id=" + deleteUserReq.id());
    }

    public com.erzbir.sys.client.resp.Response<User> queryUserByName(QueryReqs.QueryUserByName queryUserByNameReq) {
        com.erzbir.sys.client.resp.Response<?> response = get(Apis.User.Query.ONE.path(), "username=" + queryUserByNameReq.username());
        JSONObject jsonObject = JSONUtil.parseObj(response.getData());
        if (jsonObject.isEmpty()) {
            return com.erzbir.sys.client.resp.Response.error("response is empty");
        }
        return com.erzbir.sys.client.resp.Response.ok(jsonObject.getBean("data", User.class));
    }

    public com.erzbir.sys.client.resp.Response<List<User>> queryAllUsers(QueryReqs.QueryAllUsers queryAllUsersReq) {
        com.erzbir.sys.client.resp.Response<?> response = get(Apis.User.Query.ALL.path(), null);
        return com.erzbir.sys.client.resp.Response.ok(JSONUtil.toList(JSONUtil.parseArray(response.getData()), User.class));
    }


}

interface Apis {
    record POST(String path) {
    }

    record GET(String path) {

    }

    interface AUTH {
        POST LOGIN = new POST("/auth/login/");
    }

    interface Student {
        POST UPDATE = new POST("/student/update/");

        POST ADD = new POST("/student/add/");

        GET DELETE = new GET("/student/delete/");

        interface Query {
            GET ONE = new GET("/student/get/");
            GET ALL = new GET("/student/list/");
        }

    }

    interface User {
        POST UPDATE = new POST("/user/update/");

        POST ADD = new POST("/user/add/");

        GET DELETE = new GET("/user/delete/");


        interface Query {
            GET ONE = new GET("/user/get/");
            GET ALL = new GET("/user/list/");
        }

    }
}

