package com.example.gateway.api.converter;

import com.example.gateway.api.spec.model.ActionGW;
import com.example.tradelog.api.spec.model.Action;

import java.util.Optional;
import java.util.function.Function;

public class ActionConverter {

    static Function<Action, ActionGW> toActionGW = action -> {
        Optional<ActionGW> actionGWOptional = ActionGW.fromValue(action.name());
        if (actionGWOptional.isEmpty()) {
            return null;
        }
        return actionGWOptional.get();
    };

    static Function<ActionGW, Action> toAction = action -> Action.Companion.lookup(action.name());



}
