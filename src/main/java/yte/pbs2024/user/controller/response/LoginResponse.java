package yte.pbs2024.user.controller.response;

import java.util.List;

public record LoginResponse(
        Long userId,
        String username,
        List<String> authority
) {}

