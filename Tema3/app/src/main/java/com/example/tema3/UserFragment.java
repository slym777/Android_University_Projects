package com.example.tema3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.tema3.adapter.UserAdapter;
import com.example.tema3.helper.RequestHelper;
import com.example.tema3.model.User;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class UserFragment extends Fragment {
    private RecyclerView usersRecyclerView;
    private UserAdapter userAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View usersFragmentView = inflater.inflate(R.layout.fragment_users, container, false);

        usersRecyclerView = usersFragmentView.findViewById(R.id.users_recycler_view);
        usersRecyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getActivity(), 2);
        usersRecyclerView.setLayoutManager(layoutManager);
        userAdapter = new UserAdapter(new ArrayList<User>(), getContext());
        usersRecyclerView.setAdapter(userAdapter);

        getUsersFromJsonRequest();

        return usersFragmentView;
    }

    private void getUsersFromJsonRequest(){
        String url = "https://my-json-server.typicode.com/MoldovanG/JsonServer/users";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<User> userList = new ArrayList<>();
                for (int index = 0; index < response.length(); index++) {
                    try {
                        User user = User.fromJson(response.getJSONObject(index).toString());
                        userList.add(user);
                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    }
                }
                notifyUserRecyclerView(userList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Volley error: " + error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestHelper.getRequestHelperInstance(getContext()).addToRequestQueue(jsonArrayRequest);
    }

    private void notifyUserRecyclerView(List<User> users){
        List<User> userList = userAdapter.getUsers();
        userList.clear();
        userList.addAll(users);
        userAdapter.notifyDataSetChanged();
    }
}
