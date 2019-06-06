package com.example.notrain.repository.remote;

public interface NetworkingCallback<T> {

    void onSuccess(T response);

    void onFailure();
}
