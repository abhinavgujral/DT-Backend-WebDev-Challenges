package com.DTedutech.task1.Exception;

import com.DTedutech.task1.Entity.Event;

import java.util.List;

public class ResourceNotFoundExceptinon  extends RuntimeException {


    public ResourceNotFoundExceptinon(String message) {
        super(message);
    }
}
