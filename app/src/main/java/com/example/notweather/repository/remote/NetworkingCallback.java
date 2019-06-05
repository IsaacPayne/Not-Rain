package com.example.notweather.repository.remote;

public interface NetworkingCallback<T> {

    void onSuccess(T response);

    void onFailure(Throwable error);
}
