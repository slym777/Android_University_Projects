package com.example.tema3;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.tema3.adapter.ToDoAdapter;
import com.example.tema3.adapter.UserAdapter;
import com.example.tema3.helper.RequestHelper;
import com.example.tema3.model.ToDo;
import com.example.tema3.model.User;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class ToDoFragment extends Fragment {
    private RecyclerView toDoRecyclerView;
    private ToDoAdapter toDoAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Long userId;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View toDoFragmentView = inflater.inflate(R.layout.fragment_todos, container, false);
        getIncomingIntent();

        toDoRecyclerView = toDoFragmentView.findViewById(R.id.todos_recycler_view);
        toDoRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        toDoRecyclerView.setLayoutManager(layoutManager);
        toDoAdapter = new ToDoAdapter(new ArrayList<ToDo>(), getContext());
        toDoRecyclerView.setAdapter(toDoAdapter);

        getToDosFromJsonRequest();

        return toDoFragmentView;
    }

    private void getIncomingIntent(){
        userId = getActivity().getIntent().getLongExtra("userId", 0);
    }

    private void getToDosFromJsonRequest(){
        String url = "https://jsonplaceholder.typicode.com/todos?userId=" + userId;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<ToDo> toDoList = new ArrayList<>();
                for (int index = 0; index < response.length(); index++) {
                    try {
                        ToDo toDo = ToDo.fromJson(response.getJSONObject(index).toString());
                        toDoList.add(toDo);
                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    }
                }
                notifyUserRecyclerView(toDoList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Volley error: " + error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestHelper.getRequestHelperInstance(getContext()).addToRequestQueue(jsonArrayRequest);
    }

    private void notifyUserRecyclerView(List<ToDo> toDos){
        List<ToDo> toDoList = toDoAdapter.getToDos();
        toDoList.clear();
        toDoList.addAll(toDos);
        toDoAdapter.notifyDataSetChanged();
    }


}
