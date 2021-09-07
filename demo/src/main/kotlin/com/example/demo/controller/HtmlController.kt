package com.example.demo.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Tag(name = "test interface")
@Controller
class HtmlController {

    @GetMapping("/")
    @Operation(
        summary = "request demo",
        description = "spring boot request demo"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "")
        ]
    )
    fun demo(model: Model): String {
        model["title"] = "Demo"
        return "demo"
    }
}