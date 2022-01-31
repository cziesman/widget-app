package com.redhat.scale.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api")
public class WidgetController {

    @Autowired
    private WidgetService widgetService;

    @Operation(summary = "Get a widget by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns a widget",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Widget.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id was provided",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Widget was not found",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public Widget findById(@PathVariable long id) {

        return widgetService.findById(id);
    }

    @Operation(summary = "Gets all widgets")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns a list of widgets",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Widget.class))})
    })
    @GetMapping("/")
    public Collection<Widget> findWidgets() {

        return widgetService.findWidgets();
    }

    @Operation(summary = "Save or update widget")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Widget was saved/updated",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Widget with invalid values was provided",
                    content = @Content)
    })
    @PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Widget saveOrUpdateWidget(@Valid @RequestBody final Widget widget) {

        return widgetService.saveOrUpdateWidget(widget);
    }

    @Operation(summary = "Delete a widget by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Widget was deleted",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid id was provided",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Widget was not found",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteWidget(@PathVariable("id") final Long id) {

        widgetService.deleteWidget(id);
    }

}
