package yte.pbs2024.user.controller.response;

import yte.pbs2024.common.response.MessageType;

import java.util.List;

public record LoginResponse(
        Long userId,
        String username,
        List<String> authority,

        MessageType messageType,
        String message

        ) {}

